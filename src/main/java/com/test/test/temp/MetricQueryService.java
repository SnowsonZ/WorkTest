package com.test.test.temp;

/**
 * @author Snowson
 * @since 2019/11/10 16:41
 */
public class MetricQueryService {
    private Logger logger;
    private AlarmMonitor alarmMonitor;
    private MetricQueryMapper mapper;

    public MetricQueryService(Logger logger, AlarmMonitor alarmMonitor,
                              MetricQueryMapper mapper) {
        this.logger = logger;
        this.alarmMonitor = alarmMonitor;
        this.mapper = mapper;
    }

    public MetricResult queryMetric(MetricQueryParam param) {
        paramCheck(param);
        try {
            logger.info("queryMetric, metricId: " + param.getMetricId());
            return mapper.queryMetric(param);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alarmMonitor.sendMsg(e);
            return null;
        }
    }


    private void paramCheck(MetricQueryParam param) {

    }
}
