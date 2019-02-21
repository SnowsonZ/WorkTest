package com.test.test.kafka;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableSet;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
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

    private static final String TOPIC = "topic_data_exp_analysis";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        KafkaTest instance = KafkaTest.of();
        instance.registerConsumer();
        instance.sendMessage(TOPIC);
    }

    public static KafkaTest of() {
        return new KafkaTest(new KafkaProducer<>(createProducerProps()),
                new KafkaConsumer<>(createConsumerProps()));
    }

    public void registerConsumer() {
        consumer.subscribe(ImmutableSet.of(TOPIC));
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records)
                    log.info("offset = {}, key = {}, value = {}", record.offset(), record.key(), record.value());
            }
        });
    }

    public static Properties createConsumerProps() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.17.163.119:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }

    public static Properties createProducerProps() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.17.163.119:9092");
        props.put("acks", "all");
        props.put("delivery.timeout.ms", 30000);
        props.put("batch.size", 16384);
        /**
         * 数据发送等待时间
         */
        props.put("linger.ms", 1);
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
            producer.send(new ProducerRecord<>(topic,
                    0, LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond(),
                    "exp", JSON.toJSONString(expEvents)));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.warn("sendMessage error, thread sleep: {}", e.getMessage());
                producer.close();
            }
        }
    }
}
