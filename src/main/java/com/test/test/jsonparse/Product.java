package com.test.test.jsonparse;

import lombok.Data;

/**
 * @Author: Snowson
 * @Date: 2019/1/7 17:34
 * @Description:
 */
@Data
public class Product {
    private String name;
    private int frequency;
    private transient String content;
}
