package com.test.test.Timer.bean;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Exp data.
 *
 * @author Snowson
 * @since 2019 /3/11 9:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpData implements Serializable{
    private static final long serialVersionUID = -8030919755225802122L;
    /**
     * 区域ID
     */
    private String areaId;


    /**
     * 指标
     * The Metrics.
     */
    public Map<String, Double> metrics;
}
