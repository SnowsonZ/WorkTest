package com.test.test.io;

import com.google.common.base.Charsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/1/22 11:45
 */
@Slf4j
//@Component
public class IOTest implements ApplicationRunner {

    @Value("${temp.path}")
    String tempPath;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileInputStream fis = new FileInputStream(tempPath + "/systemResource.json");
        byte[] bufer = new byte[fis.available()];
        fis.read(bufer);
        log.info("result: {}", new String(bufer, Charsets.UTF_8));
        fis.close();
        Thread.sleep(2000);
        fis.close();
    }
}
