package com.test.test.kafka;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

import lombok.Data;

@Data
public class ExpEventAreaVO implements Serializable {
    private static final long serialVersionUID = 5769355244323120006L;
    private String area;
    private int onlineNum;
    private float expDegree = -1;
    private String areaId;
    private double currentDnsDelay;
    private double currentNetDelay;
    private double currentAppDelay;
    private double normalDnsDelay;
    private double normalNetDelay;
    private double normalAppDelay;
    @JSONField(name = "satifiedCount")
    private int satisfiedCount;
    private int tolerateCount;
    private int disCount;
}
