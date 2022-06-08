package com.learn.snnipet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author Snowson
 * @date 2020/5/25 22:59
 */
@Configuration
@PropertySource("classpath:config.properties")
public class ConfigurationContext {
    private final Environment env;

    @Autowired
    public ConfigurationContext(Environment env) {
        this.env = env;
    }

    public String getOnMore() {
        return env.getProperty("oneMore");
    }

}
