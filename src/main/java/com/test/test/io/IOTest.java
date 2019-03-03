package com.test.test.io;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/1/22 11:45
 */
@Slf4j
@Component
public class IOTest implements ApplicationRunner {

    @Value("${temp.path}")
    String tempPath;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("length: {}, time consume: {}", contentLength(1000000),
                System.currentTimeMillis() - startTime);
    }

    // 获取list字节数
    public int contentLength(int count) throws IOException {
        ArrayList<Product> products = new ArrayList<>();
        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            products.add(new Product(String.valueOf(i), "name" + i, i, i, 0, "desc" + i));
        }
        log.info("loop consume: {}", System.currentTimeMillis() - startTime1);
        String result = JSON.toJSONString(products);
        long startTime2 = System.currentTimeMillis();
        ByteArrayResource bar = new ByteArrayResource(result.getBytes(Charsets.UTF_8));
        int lengthByte = bar.getByteArray().length;
        log.info("length get consume: {}", System.currentTimeMillis() - startTime2);
//        InputStream is = bar.getInputStream();
//        int length = is.available();
//        log.info("lengthByte: {}, length: {}", lengthByte, length);
//        FileOutputStream fos = new FileOutputStream("testSize");
//        fos.write(bar.getByteArray());
        return lengthByte;
    }
}
