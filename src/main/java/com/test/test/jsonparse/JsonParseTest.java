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

    }


}
