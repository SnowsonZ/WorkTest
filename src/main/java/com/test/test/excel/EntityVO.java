package com.test.test.excel;

import java.io.Serializable;

import lombok.Data;

/**
 * @Author: Snowson
 * @Date: 2018/12/20 14:50
 * @Description:
 */
@Data
public class EntityVO implements Serializable {
    private static final long serialVersionUID = 3106774001435335161L;

    private String nameOrigin;
    private String name;
    private boolean need;
    private int dataType;
    private int dataRule;
}
