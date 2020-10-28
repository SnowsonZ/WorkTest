package com.test.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
//        System.out.println("Hello World!!!");
//        System.out.println(String.class == new String().getClass());
//        System.out.println(void.class);
//
//        CloneObject obj1 = new CloneObject();
    }
}
