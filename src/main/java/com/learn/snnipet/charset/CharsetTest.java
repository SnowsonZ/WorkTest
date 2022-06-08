package com.learn.snnipet.charset;

import com.google.common.io.Resources;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
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
@Component
public class CharsetTest implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        final CharsetTest test = new CharsetTest();
        test.charBuffer();
    }

    public static void main(String[] args) {
        final CharsetTest test = new CharsetTest();
        test.charBuffer();
    }

    public void charsetTest() throws Exception {
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

    public Reader fileReader(String path) throws FileNotFoundException {
        return new FileReader(path);
    }

    public void charBuffer() {
        System.out.println(Charset.defaultCharset());
        log.info("default: {}", Charset.defaultCharset());
        String content = "hello, 飞飞";
        final ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(content);
        final byte[] bytes = byteBuffer.array();

        final ByteBuffer bb = ByteBuffer.wrap(bytes);
        final CharBuffer cb = StandardCharsets.UTF_8.decode(bb);
        log.info("{}", cb);
        log.info("{}", cb.toString());
    }
}
