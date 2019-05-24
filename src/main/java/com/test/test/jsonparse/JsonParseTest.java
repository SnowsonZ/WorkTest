package com.test.test.jsonparse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2019/1/7 17:29
 * @Description:
 */
@Slf4j
public class JsonParseTest {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "name");
        map.put("frequency", 12);
        map.put("content", "content");
        JSONObject product = JSON.parseObject(JSON.toJSONString(map));
//        Product product = JSON.parseObject(map.toString(), Product.class);
        log.info("product: {}", product);
        Product product1 = new Product();
        product1.setName("name");
        product1.setContent("content");
        product1.setFrequency(10);
        HashMap describe = JSON.parseObject(JSON.toJSONString(product1), HashMap.class);
//        Map describe = BeanUtils.describe(product1);
        log.info("describe: {}", describe);

        log.info("result: {}", JSON.toJSONString(null));
    }


}
