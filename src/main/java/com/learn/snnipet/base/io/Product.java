package com.learn.snnipet.base.io;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Snowson
 * @since 2019/2/26 18:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = -3398969197000643946L;

    private String id;
    private String name;
    private double price;
    private long rest;
    private int type;
    private String desc;
}
