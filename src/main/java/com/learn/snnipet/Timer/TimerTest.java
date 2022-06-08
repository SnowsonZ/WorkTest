package com.learn.snnipet.Timer;

import com.learn.snnipet.util.MockUtils;
//import com.test.test.Timer.bean.ExpEvent;
//import com.test.test.util.MockUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2019/1/1 13:13
 * @Description:
 */
@Slf4j
//@Component
public class TimerTest implements ApplicationRunner {

    @Autowired
    private MockUtils mockUtil;

    @Autowired
    private KafkaTemplate kafkaTemplate;
    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendLiteral(' ')
            .append(DateTimeFormatter.ISO_LOCAL_TIME)
            .toFormatter();

    public static final String EXP_EVENT_ALARM = "topic_data_exp_analysis";

    @Scheduled(cron = "15 * * * * ? ")
    public void timerTest() {
        log.info("executed...");
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("TimerTest Runner start....");
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void sendExpEvent() {
        long startTime = System.currentTimeMillis();
        LocalDateTime selectedTime = LocalDateTime.now()
                .minusMinutes(3)
                .truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime endTime = selectedTime.plusSeconds(59);
//        List<ExpEvent> result = mockUtil.alarmExp(FORMATTER.format(selectedTime), FORMATTER.format(endTime));
//        result.forEach(item -> kafkaTemplate.send(EXP_EVENT_ALARM, JSON.toJSONString(item))
//                .addCallback((msg) -> log.info("send success"),
//                        (error) -> log.warn("error msg: {}", error.getMessage())));
//        log.info("time consume: {} ms, size: {}", System.currentTimeMillis() - startTime, result.size());
    }
}
