package com.test.test;

import com.test.test.base.CloneObject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// 开启定时允许
@EnableScheduling
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
        System.out.println("Hello World!!!");
        System.out.println(String.class == new String().getClass());
        System.out.println(void.class);

        CloneObject obj1 = new CloneObject();
    }
}
