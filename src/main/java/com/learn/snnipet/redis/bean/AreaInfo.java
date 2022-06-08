package com.learn.snnipet.redis.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Snowson
 * @since 2019/4/10 15:57
 */
@Data
public class AreaInfo implements Serializable {
    private static final long serialVersionUID = -2889757357871105513L;

    private String id;
    private String name;
}
