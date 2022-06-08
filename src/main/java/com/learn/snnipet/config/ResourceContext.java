package com.learn.snnipet.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 *
 * @author Snowson
 * @date 2020/5/25 18:10
 */
@Component
@ConfigurationProperties
@PropertySource("classpath:config.properties")
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
