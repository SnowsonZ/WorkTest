package com.test.test.util;

import com.google.common.base.Splitter;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * author: SnowsonZ
 * created on: 2018/10/11 18:30
 * description:
 */
@Slf4j
public class ParserUtils {

    public static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendLiteral(' ')
            .append(DateTimeFormatter.ISO_LOCAL_TIME)
            .toFormatter();
    public static final Splitter SPLITTER = Splitter.on(',');

    public static int parseInt(String content) {
        if (StringUtils.isEmpty(content)) {
            return 0;
        }
        try {
            return Integer.parseInt(content);
        } catch (Exception e) {
            log.warn("parseInt fail, content: [{}], cause: {}" , content, e.getMessage());
            return 0;
        }
    }

    public static long parseLong(String content) {
        if (StringUtils.isEmpty(content)) {
            return 0L;
        }
        try {
            return Long.parseLong(content);
        } catch (Exception e) {
            log.warn("parseLong fail, content: [{}], cause: {}", content, e.getMessage());
            return 0L;
        }
    }

    public static float parseFloat(String content) {
        if (StringUtils.isEmpty(content)) {
            return 0F;
        }
        try {
            return Float.parseFloat(content);
        } catch (Exception e) {
            log.warn("parseFloat fail, content: [{}], cause: {}", content, e.getMessage());
            return 0F;
        }
    }

    public static long timeToTimestamp(String time) {
        if (StringUtils.isEmpty(time)) {
            return 0L;
        }
        try {
            final LocalDateTime dateTime = LocalDateTime.parse(time, FORMATTER);
            return dateTime.toEpochSecond(ZoneOffset.ofHours(8)) * 1000
                    + dateTime.getLong(ChronoField.MILLI_OF_SECOND);
        } catch (Exception e) {
            log.warn("timeToTimestamp fail, time: [{}], cause: {}", time, e.getMessage());
            return 0L;
        }
    }

    public static List<String> toList(String str) {
        List<String> ls;
        if (str == null || str.isEmpty()) {
            ls = Collections.emptyList();
        } else {
            ls = SPLITTER.splitToList(str);
        }
        return ls;
    }
}
