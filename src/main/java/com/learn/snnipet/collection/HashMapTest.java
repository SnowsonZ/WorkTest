package com.learn.snnipet.collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/3/6 9:54
 */
@Slf4j
public class HashMapTest {
    public static void main(String[] args) {
        ImmutableList<Product> products = ImmutableList
                .of(new Product("10000", "name_1", 12.34f),
                        new Product("10001", "name_1", 12.34f));
        ImmutableMap<String, Product> productMap = ImmutableMap.copyOf(products);
        log.info("productMap: {}", productMap);
        HashMap<String, Product> map = new HashMap<>();
        productMap.forEach(map::put);
        map.get("10000").setPrice(1234.42f);
        log.info("map: {}", map);

        // 序列化Map.Entry 栈溢出
        log.info("map: {}", JSON.toJSONString(map));

    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Product implements Map.Entry<String, Product> {
    private String id;
    private String name;
    private float price;

    @Override
    public String getKey() {
        return id;
    }

    @Override
    public Product getValue() {
        return this;
    }

    @Override
    public Product setValue(Product value) {
        return null;
    }
}
