package com.test.test.charset;

import com.google.common.io.Resources;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/1/28 10:45
 * System.out.println(System.getProperty("sun.jnu.encoding")); //文件名编码
 * System.out.println(System.getProperty("file.encoding"));  //文件编码
 */
@Slf4j
//@Component
public class CharsetTest implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String path = Resources.getResource("charset.properties").getPath();
        Properties props = new Properties();
        props.load(inputStream(path));
        String fileName = props.getProperty("name");
        log.info("fileName Encoding: {}", System.getProperty("sun.jnu.encoding"));
        log.info("fileContent Encoding: {}", System.getProperty("file.encoding"));
        String result = new String(fileName.getBytes("UTF-8"), "UTF-8");
        log.info("filename: {}", result);
    }

    public Reader inputStream(String path) throws Exception {
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, "GBK");
        return isr;
    }

    public Reader fileReader (String path) throws FileNotFoundException {
        return new FileReader(path);
    }
}
