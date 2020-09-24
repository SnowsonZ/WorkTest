package com.test.test;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * @author Snowson
 * @since 2019/3/29 15:52
 */
@Slf4j
public class Test {

    private static final Double NUM_FLAG = 100.6d;

    public static void main(String[] args) {

//        final String result = new String("\\n\"123\"abck".getBytes());
//        log.info("{}", result);
//        String ip_167 = "172.16.2.95,172.16.2.26,61.233.9.9,202.106.196.115,114.114.114.114,192.168.58.95,202.106.0.20,172.16.2.29,172.20.10.1,192.168.253.201,101.198.198.198,172.30.44.20,180.76.76.76,10.52.5.1,192.168.58.110,223.5.5.5,10.52.5.2,101.198.199.200,140.207.198.6,123.125.81.6,119.29.29.29,192.168.1.1,211.98.4.1,223.6.6.6,114.114.115.115,192.168.58.111,178.79.131.110,199.91.73.222,192.168.3.1,192.168.14.35,140.205.29.116,172.30.2.1,192.168.0.1,203.119.159.122,10.100.106.2,116.211.173.142,10.10.10.10";
//        String ip_180 = "14.14.14.14,15.15.15.15,93.93.93.93,13.13.13.13,12.12.12.12,253.17.2.59,253.17.2.24,253.17.2.20,253.17.2.39,253.17.2.48,253.17.2.25,253.17.2.1,253.17.2.55,253.17.2.9,253.17.2.53,253.17.2.56,253.17.2.5,253.17.2.10,253.17.2.41,253.17.2.40,253.17.2.2,253.17.2.52,253.17.2.17,253.17.2.19,253.17.2.11,253.17.2.7,253.17.2.28,253.17.2.57,253.17.2.12,253.17.2.6,253.17.2.4,253.17.2.13,253.17.2.27,253.17.2.58,253.17.2.46,253.17.2.47,253.17.2.51,253.17.2.21,253.17.2.23,253.17.2.33,253.17.2.30,253.17.2.32,253.17.2.18,253.17.2.14,253.17.2.60,253.17.2.37,253.17.2.36,253.17.2.34,253.17.2.49,253.17.2.54,253.17.2.50,253.17.2.42,253.17.2.43,253.17.2.38,253.17.2.16,253.17.2.31,253.17.2.3,253.17.2.45,253.17.2.8,253.17.2.29";
//        String ip_173 = "14.14.14.14,13.13.13.13,12.12.12.12,15.15.15.15,16.16.16.16,253.17.2.49,253.17.2.2,253.17.2.32,253.17.2.14,253.17.2.8,253.17.2.45,253.17.2.19,253.17.2.17,253.17.2.20,253.17.2.34,253.17.2.41,253.17.2.24,253.17.2.28,253.17.2.40,253.17.2.33,253.17.2.44,253.17.2.27,253.17.2.18,253.17.2.54,253.17.2.10,253.17.2.60,253.17.2.52,253.17.2.1,253.17.2.9,253.17.2.39,253.17.2.55,253.17.2.38,253.17.2.25,253.17.2.51,253.17.2.31,253.17.2.29,253.17.2.16,253.17.2.6,253.17.2.36,253.17.2.30,253.17.2.15,253.17.2.21,253.17.2.3,253.17.2.4,253.17.2.5,253.17.2.12,253.17.2.43,253.17.2.46,253.17.2.57,253.17.2.35,253.17.2.59,253.17.2.42,253.17.2.47,253.17.2.7,253.17.2.48,253.17.2.11,253.17.2.23,253.17.2.26,253.17.2.58,253.17.2.37";
//        String app_173 = "b173e2aa-a94e-11ea-bf72-02420a0a0005, e09fa1fe-a94e-11ea-b051-02420a0a0005, 084dc9a6-a94f-11ea-bf72-02420a0a0005, 85343d98-b448-11ea-9055-02420a0a0005, 300b464e-a94f-11ea-bf72-02420a0a0005, 2c46b2ce-b448-11ea-8dbf-02420a0a0005, 723838d4-b448-11ea-9055-02420a0a0005, 872cba30-b448-11ea-9995-02420a0a0005, 8858aa04-b448-11ea-9055-02420a0a0005, 8a0006fe-b448-11ea-9055-02420a0a0005, 8cc7bf1c-b448-11ea-9055-02420a0a0005, 8deafa3a-b448-11ea-9055-02420a0a0005, 9731413a-b448-11ea-9055-02420a0a0005, 98ccb3e4-b448-11ea-9055-02420a0a0005, 9a5d937c-b448-11ea-9055-02420a0a0005, 9c20097e-b448-11ea-9055-02420a0a0005, 89315bce-b448-11ea-9995-02420a0a0005, 8adbf24a-b448-11ea-9995-02420a0a0005, 8bcd87d6-b448-11ea-9995-02420a0a0005, 90736af8-b448-11ea-9995-02420a0a0005, 92410458-b448-11ea-9995-02420a0a0005, 931c7dee-b448-11ea-9995-02420a0a0005, b90604bc-b448-11ea-9995-02420a0a0005, ba578516-b448-11ea-9995-02420a0a0005, 8ecde7be-b448-11ea-8dbf-02420a0a0005, 8f9d4f40-b448-11ea-8dbf-02420a0a0005, 916b7216-b448-11ea-8dbf-02420a0a0005, 93e67950-b448-11ea-8dbf-02420a0a0005, 94c745d4-b448-11ea-8dbf-02420a0a0005, 95a29e54-b448-11ea-8dbf-02420a0a0005, 98030a9e-b448-11ea-8dbf-02420a0a0005, 9971a48a-b448-11ea-8dbf-02420a0a0005, 9b7f8e7c-b448-11ea-8dbf-02420a0a0005, 9d44d7bc-b448-11ea-8dbf-02420a0a0005, b2716858-b448-11ea-8dbf-02420a0a0005, b3fece36-b448-11ea-8dbf-02420a0a0005, b54a0d1e-b448-11ea-8dbf-02420a0a0005, b5f230c0-b448-11ea-8dbf-02420a0a0005, b745ca22-b448-11ea-8dbf-02420a0a0005, b9d380cc-b448-11ea-8dbf-02420a0a0005, b0bad68e-b448-11ea-9055-02420a0a0005, b1dc919c-b448-11ea-9055-02420a0a0005, b3414f82-b448-11ea-9055-02420a0a0005, b4a7a452-b448-11ea-9055-02420a0a0005, b6a55966-b448-11ea-9055-02420a0a0005, b8548be2-b448-11ea-9055-02420a0a0005, c9b4613c-b448-11ea-9055-02420a0a0005, caf0187a-b448-11ea-9055-02420a0a0005, bafe1f7a-b448-11ea-9995-02420a0a0005, bba907be-b448-11ea-9995-02420a0a0005, bc4acde2-b448-11ea-9995-02420a0a0005, bd854af2-b448-11ea-9995-02420a0a0005, be9ff900-b448-11ea-9995-02420a0a0005, bfcd3ab8-b448-11ea-9995-02420a0a0005, c12ab778-b448-11ea-9995-02420a0a0005, c27a389c-b448-11ea-9995-02420a0a0005, bce6695a-b448-11ea-8dbf-02420a0a0005, be0fe450-b448-11ea-8dbf-02420a0a0005, bf29c5ae-b448-11ea-8dbf-02420a0a0005, c0810764-b448-11ea-8dbf-02420a0a0005, c1cc45f2-b448-11ea-8dbf-02420a0a0005, c45a9df0-b448-11ea-8dbf-02420a0a0005, c535fe5e-b448-11ea-8dbf-02420a0a0005, c66c0aa2-b448-11ea-8dbf-02420a0a0005, c318a16c-b448-11ea-9995-02420a0a0005, c3c327b8-b448-11ea-9995-02420a0a0005, c5d6906c-b448-11ea-9995-02420a0a0005, c71097ac-b448-11ea-9995-02420a0a0005, c86728a0-b448-11ea-9995-02420a0a0005, ca507c0c-b448-11ea-9995-02420a0a0005, cb98e8d8-b448-11ea-9995-02420a0a0005, cc4b0fea-b448-11ea-9995-02420a0a0005, c7b61a06-b448-11ea-8dbf-02420a0a0005, c9027b5c-b448-11ea-8dbf-02420a0a0005, cf87029a-b448-11ea-8dbf-02420a0a0005, cd095ae0-b448-11ea-9055-02420a0a0005, ce312b28-b448-11ea-9055-02420a0a0005, cd9032ae-b448-11ea-9995-02420a0a0005, ced5eb72-b448-11ea-9995-02420a0a0005, d02aab84-b448-11ea-a044-02420a0a0005, d0b11516-b448-11ea-a044-02420a0a0005, d1559190-b448-11ea-9bd9-02420a0a0005, d2086158-b448-11ea-a044-02420a0a0005, d2de5dc6-b448-11ea-9bd9-02420a0a0005, d35eac4c-b448-11ea-a044-02420a0a0005, d3fb6744-b448-11ea-a044-02420a0a0005, d505a1c2-b448-11ea-9bd9-02420a0a0005, d5a77e84-b448-11ea-9bd9-02420a0a0005, ecf7fcbc-b448-11ea-a044-02420a0a0005, edbb0392-b448-11ea-9bd9-02420a0a0005, ee722950-b448-11ea-a044-02420a0a0005, ef17793c-b448-11ea-9bd9-02420a0a0005, efefe6aa-b448-11ea-a044-02420a0a0005, f0f312d4-b448-11ea-a044-02420a0a0005, f0f31d4c-b448-11ea-9bd9-02420a0a0005, f1aeacd8-b448-11ea-9055-02420a0a0005, f2715de6-b448-11ea-9bd9-02420a0a0005, f35d55de-b448-11ea-9bd9-02420a0a0005, f3fcb304-b448-11ea-9055-02420a0a0005, f4f36834-b448-11ea-9bd9-02420a0a0005, f6185e9a-b448-11ea-9055-02420a0a0005, f6f96548-b448-11ea-9bd9-02420a0a0005, f8221244-b448-11ea-9bd9-02420a0a0005, f97dc05c-b448-11ea-9bd9-02420a0a0005, fa3b7e6c-b448-11ea-9995-02420a0a0005, fb150966-b448-11ea-9995-02420a0a0005, fe13fa78-b448-11ea-9bd9-02420a0a0005, da4da5e6-ba81-11ea-a792-02420a0a0005, f6751d80-ba81-11ea-b816-02420a0a0005, 1664f746-ba82-11ea-a792-02420a0a0005, 37be018a-ba82-11ea-a972-02420a0a0005, 53a130d4-ba82-11ea-a972-02420a0a0005, 72c22f54-ba82-11ea-a972-02420a0a0005, a0b0b2ec-ba86-11ea-b816-02420a0a0005";
//        String app_180 = "";
//        String ip_103 = "8.8.8.8,114.114.114.114,172.16.2.26,202.99.96.68,172.17.2.26,202.99.104.68,101.198.198.198,223.5.5.5,123.125.81.6,223.6.6.6,119.29.29.29,117.50.10.10,192.168.31.1,140.207.198.6";

//        log.info("{}", new Test().paramTpDbFormat(ip_103));
//        new Test().transientTest();

        final List<Compare> data = Arrays.asList(new Compare(3), new Compare(2), new Compare(8), new Compare(10), new Compare(0));
        data.sort(Compare::compareTo);
        log.info("{}", data);
    }

    public void transientTest() {
        Transient b = new Transient(Arrays.asList("172.17.16.1", "192.168.1.1"), Arrays.asList(".jpg", ".png"));
        log.info("{}, {}", b.ip, b.suffix);
    }

    @Data
    @AllArgsConstructor
    class Transient {
        public transient List<String> ip;
        public List<String> suffix;

    }

    @Data
    class HttpMsg {
        private String url;
        private byte agent;

        public HttpMsg(String url) {
            this.url = url;
        }
    }

    private void endsWithBM() {
        final List<String> filter = Arrays.asList(".png", ".js", ".ico", ".jpg ");
        final List<HttpMsg> http = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 20_000; i++) {
            final int num = random.nextInt(filter.size());
            http.add(new HttpMsg(i + filter.get(num)));
        }

        final Stopwatch watch = Stopwatch.createStarted();
        final List<HttpMsg> ret = http.stream().peek(msg -> {
            final String url = msg.getUrl();
            if (filter.stream().anyMatch(url::endsWith)) {
                msg.setAgent((byte) 2);
            }else {
                msg.setAgent((byte) 0);
            }
        }).collect(Collectors.toList());
        log.info("time consume: {}", watch);
        log.info("{}", JSON.toJSONString(http));
    }

    private List<String> paramTpDbFormat(String src) {
        final List<String> temp = Splitter.on(',').splitToList(src);
        return temp.stream().map(str ->
                ("'"+str.trim() +"'")
        ).collect(Collectors.toList());
    }

    private void regexMatch() {
        String content = "Let's go to a movie.";
        String[] ret = content.split("\\W+");
        log.info("{}", Arrays.toString(ret));
    }

    /**
     * decimal小数位数自定义
     */
    private void decimalScale() {
        BigDecimal decimal = new BigDecimal("100.6664");
        // scale为小数保留位数,RoundingMode为多余小数舍去规则
        BigDecimal result = decimal.setScale(3, RoundingMode.UP);
        log.info("{}", result);
        BigDecimal flag = new BigDecimal(String.valueOf(NUM_FLAG));
        log.info("{}", flag);
    }
    /**
     * 迭代删除
     */
    private void updateIterator() {
        List<String> contents = new ArrayList<>();
        contents.add("a");
        contents.add("b");
        contents.add("c");
        contents.add("d");
        Iterator<String> iterator = contents.iterator();
        while (iterator.hasNext()) {
            contents.remove(iterator.next());
        }
        log.info("complete");
    }

    private void toIntPms() {
        long startTime = System.currentTimeMillis();
        double a, b;
        for (int i = 0; i < 10000; i++) {
            a = Math.random();
            b = Math.random();
            int result = (int) Math.floor(a + b);
            log.info("a: {}, b: {}, result: {}", a, b, result);
        }
        log.info("time consume: {} ms", System.currentTimeMillis() - startTime);

        int result = (int) Math.floor(1.23455);
        log.info("result: {}", result);

        List<String> content = Arrays.asList("3", "2", "1");
        Collections.sort(content);
        log.info("{}", JSON.toJSONString(content));
    }

    /**
     * 优先级队列
     */
    private void priorityQueue() throws NoSuchAlgorithmException {
        val data = new ArrayList<String>();
        data.add("one");
        data.add("two");
        log.info("{}", JSON.toJSONString(data));
        data.clear();
        Random random = SecureRandom.getInstanceStrong();
        double v = random.nextDouble() * 400;
        log.info("{}", v);
        v = random.nextDouble() * 400;
        log.info("{}", v);
        v = random.nextDouble() * 400;
        log.info("{}", v);

    }

    public static String targetField(String source, char split) {
        int bounds = 5;
        int index = 0;
        int start = 0, end = 0;
        while (end < source.length()) {
            if (source.charAt(end) == split) {
                if (++index == bounds) {
                    break;
                }
                start = end;
            }
            end++;
        }
        return source.substring(start + 2, end).trim();
    }

    @Data
    @AllArgsConstructor
    static class Compare implements Comparable<Compare> {
        private int num;

        @Override
        public int compareTo(Compare o) {
            log.info("{}, {}", num, o.getNum());
            int ret = 1;
            if (num > o.getNum()) {
                ret =  -1;
            } else if (num == o.getNum()) {
                ret =  0;
            }
            log.info("{}", ret);
            return ret;
        }
    }
}
