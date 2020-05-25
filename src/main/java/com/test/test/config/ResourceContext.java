package com.test.test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author Snowson
 * @date 2020/5/25 18:10
 */
// @ConfigurationProperties 重点在bind
@Component
@ConfigurationProperties
@PropertySource("file:config.properties")
@Data
public class ResourceContext {

    private Integer oneMore;

    public Integer getOneMore() {
        if (oneMore == null) {
            return 10;
        }
        return oneMore;
    }
}
