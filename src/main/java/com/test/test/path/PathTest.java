package com.test.test.path;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Path test.
 *
 * @author Snowson
 * @since 2019 /1/15 17:59 <p> TODO URLResource, ServletContextResource, InputStreamResource
 */
@Component
@Slf4j
public class PathTest implements ApplicationRunner {

    @Value("${temp.path}")
    private String tempPath;

    /**
     * Of path test.
     *
     * @return the path test
     */
    public static PathTest of() {
        return new PathTest();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        PathTest.of().classPathResource();
//        PathTest.of().fileSystemResource(tempPath);
//        PathTest.of().byteArrayResource();
//        PathTest.of().guavaResource();
    }

    /**
     * ClassPathResource = getClass().getClassLoader().getResource
     *
     * @throws IOException the io exception
     */
    public void classPathResource() {
        // JDK classpath
        String filePath = getClass().getClassLoader().getResource("application.yml").getPath();
        File file = new File(filePath);
        // spring classpath
        //ClassPathResource resource = new ClassPathResource("application.yml");
        //File file = resource.getFile();
        char[] b = new char[1024];
        try(FileReader fr = new FileReader(file)) {
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = fr.read(b)) != -1) {
                sb.append(new String(b, 0, len));
            }
            log.info("result: {}", sb.toString());
        } catch (IOException e) {
            log.error("error msg: {}", e.getMessage());
        }
    }

    /**
     * File system resource. == File, 操作系统文件
     *
     * @param destDir the dest dir
     * @throws IOException the io exception
     */
    public void fileSystemResource(String destDir) throws IOException {
        String filePath = destDir + "/" + "systemResource.json";
        FileSystemResource resource = new FileSystemResource(filePath);
        OutputStream os = resource.getOutputStream();
        Map<String, String> map = new HashMap<>();
        map.put("userName", "admin");
        map.put("password", "123456");
        map.put("role", "dbOwner");
        String result = JSON.toJSONString(map);
        os.write(result.getBytes(Charsets.UTF_8));
        os.flush();
        os.close();
    }

    /**
     * Byte array resource. byteArray转stream
     *
     * @throws IOException the io exception
     */
    public void byteArrayResource() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("userName", "admin");
        map.put("password", "123456");
        map.put("role", "dbOwner");
        String origin = JSON.toJSONString(map);
        ByteArrayResource bar = new ByteArrayResource(origin.getBytes(Charsets.UTF_8));
        InputStream is = bar.getInputStream();
        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        String result = new String(buffer, Charsets.UTF_8);
        log.info("result: {}", result);
    }

    /**
     * Guava resource. == ClassPathResource
     */
    public void guavaResource() throws IOException {
        String pathFile = Resources.getResource("res/test.json").getPath();
        File file = new File(pathFile);
        FileInputStream fs = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fs);
        byte[] b = new byte[fs.available()];
        bis.read(b);
        String content = new String(b, Charsets.UTF_8);
        log.info("content: {}", content);
    }

    @Data
    public static class Member implements Serializable {
        private static final long serialVersionUID = 6530096525193809465L;
        private String id;
        private String name;
    }

}
