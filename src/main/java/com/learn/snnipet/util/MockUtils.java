package com.learn.snnipet.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/3/6 15:18
 */
@Component
@Slf4j
public class MockUtils {

    private String[] appIds = {"0f129cc6-3a3d-11e9-9841-02420a0a0a05",
            "2871b990-3a3d-11e9-9841-02420a0a0a05", "385b560e-3a3d-11e9-9841-02420a0a0a05"};

    private String[] areaIds = {"0f129cc6-3a3d-11e9-4212-02420a0a0a05",
            "2871b990-3a3d-11e9-4323-02420a0a0a05", "385b560e-3a3d-11e9-5232-02420a0a0a05"};

    private Map<String, Double> buildMetrics() {
        Map<String, Double> metrics = new HashMap<>();
        metrics.put(MetricsTable.DNS.DELAY_AVG, 201D);
        metrics.put(MetricsTable.DNS.SUCCESS_RATIO, 0.6);
        metrics.put(MetricsTable.HTTP.AP_DEX, 0.6);
        metrics.put(MetricsTable.HTTP.RSP_DELAY_AVG, 203D);
        metrics.put(MetricsTable.HTTP.SUCCESS_RATIO, 0.97);
        metrics.put(MetricsTable.HTTP.REQUEST_TOTAL_COUNT, 1000D);
        metrics.put(MetricsTable.HTTP.REQUEST_CONCURRENT_COUNT, 1000D);
        metrics.put(MetricsTable.HTTP.ONLINE, 10D);
        metrics.put(MetricsTable.HTTP.COUNT_4XX, 4D);
        metrics.put(MetricsTable.HTTP.COUNT_5XX, 1D);
        metrics.put(MetricsTable.HTTP.RES_DELAY_UNSATISFIED, 1200D);
        metrics.put(MetricsTable.TCP.TOTAL_CONNECT, 12D);
        metrics.put(MetricsTable.TCP.CON_CONNECT, 5D);
        metrics.put(MetricsTable.TCP.CLI_NO_RESP, 0.2);
        metrics.put(MetricsTable.TCP.CLI_REFUSE, -1D);
        metrics.put(MetricsTable.TCP.SRV_NO_RESP, -1D);
        metrics.put(MetricsTable.TCP.SRV_REFUSE, -1D);
        metrics.put(MetricsTable.TCP.RTT_UP, 78D);
        metrics.put(MetricsTable.TCP.RTT_DOWN, 60D);
        metrics.put(MetricsTable.TCP.DELAY_AVERAGE, 102D);
        metrics.put(MetricsTable.TCP.SUCCESS_RATIO, 0.76);
        metrics.put(MetricsTable.TCP.DOWN_TCP_RETRANSMISSION_RATIO, 0.3);
        metrics.put(MetricsTable.TCP.UP_TCP_RETRANSMISSION_RATIO, 0.2);
        metrics.put(MetricsTable.TCP.UP_BIT_SPEED, 324123D);
        metrics.put(MetricsTable.TCP.DOWN_BIT_SPEED, 0.2);
        metrics.put(MetricsTable.TCP.DOWN_BANDWIDTH, 321490382D);
        metrics.put(MetricsTable.TCP.UP_BANDWIDTH, 2345029342D);

        return metrics;
    }

//    public List<ExpEvent> alarmExp(String startTime, String endTime) {
//        List<ExpEvent> expEvents = new ArrayList<>();
//        for (int i = 0; i < appIds.length; i++) {
//            ExpEvent expEvent = new ExpEvent();
//            expEvent.setAppId(appIds[i]);
//            expEvent.setStartTime(startTime);
//            expEvent.setEndTime(endTime);
//            List<ExpData> areas = new ArrayList<>();
//            for (String areaId : areaIds) {
//                ExpData expData = new ExpData();
//                expData.setAreaId(areaId);
//                expData.setMetrics(buildMetrics());
//                areas.add(expData);
//            }
//            expEvent.setAreas(areas);
//            expEvents.add(expEvent);
//        }
//        return expEvents;
//    }

    public static void main(String[] args) {
//        log.info("{}", JSON.toJSONString(new MockUtils().alarmExp("2019-01-01 12:00:00", "2019-01-01 12:00:59")));
    }
}
