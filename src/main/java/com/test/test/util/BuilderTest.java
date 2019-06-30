package com.test.test.util;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/6/21 10:19
 */
@Slf4j
public class BuilderTest {

    public static void main(String[] args) {
        Product.ProductBuilder product = Product.builder()
                .name("tomcat")
                .rest(100)
                .price(12.3f);
        log.info("product: {}", product);

    }
}

@Builder
@Data
class Product {
    private String name;
    private long rest;
    private float price;
    private int type = 2;
}
