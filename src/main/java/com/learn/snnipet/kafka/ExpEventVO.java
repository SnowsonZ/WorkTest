package com.learn.snnipet.kafka;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ExpEventVO implements Serializable {
	private static final long serialVersionUID = 1405491181999249686L;
	private String appId;
	private String appName;
	private int interval;
	private String startTime;
	private String statTime;
	private long apdexT;
	private float expDegree;
	private List<ExpEventAreaVO> areas=new ArrayList<>();
}
