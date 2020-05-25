package com.test.test.io.nio.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @date 2020/5/6 12:23
 */
@Slf4j
public class FileUtils {

    public static String read(String filePath, String fileName) throws IOException {
        try(FileChannel channel = new FileInputStream(new File("180")).getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
            channel.read(buffer);
            byte[] bytes = buffer.array();
            return new String(bytes, 0, bytes.length);
        }catch (Exception e) {
            log.info("msg: {}, exception: {}", e.getMessage(), e);
        }
        return null;
    }

    public static boolean write(String filePath, String fileName) {
        return true;
    }

    public static void main(String[] args) throws IOException {
        String result = read("./", "180");
        log.info("{}", result);
    }
}
