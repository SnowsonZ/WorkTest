package com.learn.snnipet.excel;

import java.io.Serializable;

import lombok.Data;

/**
 * @Author: Snowson
 * @Date: 2018/12/20 15:05
 * @Description:
 */
@Data
public class relationVO implements Serializable {
    private static final long serialVersionUID = 3969008408750043473L;

    private int resRelation;
    private String resName;
    private String resId;
}
