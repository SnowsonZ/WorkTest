package com.test.test.kafka;

import com.alibaba.fastjson.JSON;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/1/15 22:46
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
//@Component
public class KafkaTest implements ApplicationRunner {

    private Producer<String, String> producer;
    private Consumer<String, String> consumer;

    private static final String TOPIC_ONE = "topic_test_one";
    private static final String TOPIC_TWO = "topic_test_two";
    private static final String TOPIC_THREE = "topic_test_three";
    private static final String TOPIC_FIVE = "topic_test_five";
    private static final String TOPIC_EIGHT = "topic_test_eight";
    private static final String TOPIC_EXP_DATA = "topic_data_exp_analysis";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        KafkaTest instance = KafkaTest.of();
        instance.registerConsumer();
//        log.info("send msg start...");
//        instance.sendMessage(TOPIC_ONE);
    }

    public static KafkaTest of() {
        return new KafkaTest(new KafkaProducer<>(createProducerProps()),
                new KafkaConsumer<>(createConsumerProps()));
    }

    public void registerConsumer() {
        ArrayList<TopicPartition> partitions = new ArrayList<>();
        partitions.add(new TopicPartition(TOPIC_EXP_DATA, 0));
        partitions.add(new TopicPartition(TOPIC_EXP_DATA, 1));
        consumer.assign(partitions);
        Map<TopicPartition, OffsetAndMetadata> offest = new HashMap<>();
        offest.put(partitions.get(0), new OffsetAndMetadata(0, "reset"));
        offest.put(partitions.get(1), new OffsetAndMetadata(0, "reset"));
//        consumer.beginningOffsets(partitions);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records)
                    log.info("offset = {}, key = {}, value = {}", record.offset(), record.key(), record.value());
            }
        });
    }

    public static Properties createConsumerProps() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.17.161.7:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }

    public static Properties createProducerProps() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.110.128:9092");
        props.put("acks", "all");
        props.put("delivery.timeout.ms", 30000);
        props.put("batch.size", 1000);
        /**
         * 数据发送等待时间
         */
        props.put("linger.ms", 5000);
        /**
         * 一条消息的大小设置
         */
        props.put("buffer.memory", 102400);
        props.put("request.timeout.ms", 5000);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    public void sendMessage(String topic) {
        List<ExpEventVO> expEvents = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExpEventVO expEventVO = new ExpEventVO();
            ArrayList<ExpEventAreaVO> expAreas = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                ExpEventAreaVO expArea = new ExpEventAreaVO();
                expAreas.add(expArea);
            }
            expEventVO.setAreas(expAreas);
            expEvents.add(expEventVO);
        }
        while (true) {
            int partition = (int) (Math.random());
            producer.send(new ProducerRecord<>(topic,
                            partition, LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
                    "test", JSON.toJSONString(expEvents)),
                    (metadata, exception) -> log.info("timestamp: {}", metadata.timestamp()));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.warn("sendMessage error, thread sleep: {}", e.getMessage());
                producer.close();
            }
        }
    }
}
