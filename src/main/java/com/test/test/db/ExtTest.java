//package com.test.test.db;
//
//import com.alibaba.fastjson.JSON;
//import com.riil.baymax.manager.UserExpEventMapper;
//import com.riil.baymax.manager.bean.ExpEvent;
//import com.riil.baymax.repository.po.AreaInfo;
//import com.riil.baymax.repository.po.DelayAvg;
//import com.riil.baymax.repository.po.DelayAvgStd;
//import com.riil.baymax.repository.po.ExpEventAreaVO;
//import com.riil.baymax.repository.po.ExpEventVO;
//import com.riil.baymax.repository.po.ExpQueryParam;
//import com.riil.baymax.service.entities.UserApp;
//
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.ExecutorType;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.util.CollectionUtils;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeFormatterBuilder;
//import java.time.temporal.ChronoUnit;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import lombok.extern.slf4j.Slf4j;
//import redis.clients.jedis.Jedis;
//
///**
// * @Author: Snowson
// * @Date: 2018/12/28 18:28
// * @Description:
// */
//@Slf4j
//public class ExtTest {
//
//    @Autowired
//    public static StringRedisTemplate redis;
//
//    public static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
//            .append(DateTimeFormatter.ISO_LOCAL_DATE)
//            .appendLiteral(' ')
//            .append(DateTimeFormatter.ISO_LOCAL_TIME)
//            .toFormatter();
//
//    public static void main(String[] args) throws IOException {
//        expQuery();
//    }
//
//    public static void expPrevious() throws IOException {
//        long startTime = System.currentTimeMillis();
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
//                .build(Resources.getResourceAsReader("mybatis.xml"));
//        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.SIMPLE, true);
//        UserExpEventMapper mapper = sqlSession.getMapper(UserExpEventMapper.class);
//        ExpQueryParam param = new ExpQueryParam();
//        LocalDateTime selectedTime = LocalDateTime.now().minusMinutes(3).truncatedTo(ChronoUnit.MINUTES);
//        LocalDateTime endTime = selectedTime.plusSeconds(59);
//        param.setSelectedTime(selectedTime);
//        List<ExpEventVO> result = mapper.expEvent(param);
//        log.info("result json: {}", JSON.toJSONString(result));
//        log.info("size: {}", result.size());
//        param.setTableName("model_http_delay_1m");
//        List<DelayAvg> httpDelay = mapper.delayAvg(param);
//        log.info("httpDelay json: {}", JSON.toJSONString(httpDelay));
//        log.info("size: {}", result.size());
//        param.setTableName("model_tcp_delay_1m");
//        List<DelayAvg> tcpDelay = mapper.delayAvg(param);
//        log.info("tcpDelay json: {}", JSON.toJSONString(tcpDelay));
//        log.info("size：{}", result.size());
//        param.setTableName("model_dns_delay_1m");
//        List<DelayAvg> dnsDelay = mapper.delayAvg(param);
//        log.info("dnsDelay json: {}", JSON.toJSONString(dnsDelay));
//        log.info("size: {}", result.size());
//        //1.appInfo, areaInfo, delayStandard
//        Jedis jedis = new Jedis("172.16.3.74", 6379);
//        List<UserApp> appsInfo = keyMatch(jedis, "mem:userapp-*", UserApp.class);
//        log.info("app json: {}", JSON.toJSONString(appsInfo));
//        log.info("size: {}", appsInfo.size());
//        List<AreaInfo> areasInfo = keyMatch(jedis, "mem:location-*", AreaInfo.class);
//        log.info("area json: {}", JSON.toJSONString(areasInfo));
//        log.info("size: {}", areasInfo.size());
////        List<UserApp> appsInfo = JSONArray.parseArray(redis.opsForValue().multiGet(redis.keys("mem:userapp-*")).toString(), UserApp.class);
////        List<AreaInfo> areasInfo = JSONArray.parseArray(redis.opsForValue().multiGet(redis.keys("mem:location-*")).toString(), AreaInfo.class);
//        int hour = selectedTime.getHour();
////        Map<String, String> dnsThreshold = jedis.hgetAll("flink:dynamic_threshold_dns:" + hour);
//        Map<String, String> dnsThreshold = jedis.hgetAll("flink:dynamic_threshold_dns");
//        HashMap<String, DelayAvgStd> mapDnsThreshold = new HashMap<>();
//        dnsThreshold.forEach((key, value) -> {
//            mapDnsThreshold.put(key, new DelayAvgStd().ofDns(value));
//        });
//
////        Map<String, String> tcpThreshold = jedis.hgetAll("flink:dynamic_threshold_tcp:" + hour);
//        Map<String, String> tcpThreshold = jedis.hgetAll("flink:dynamic_threshold_tcp");
//        HashMap<String, DelayAvgStd> mapTcpThreshold = new HashMap<>();
//        tcpThreshold.forEach((key, value) -> {
//            mapTcpThreshold.put(key, new DelayAvgStd().ofHttp(value));
//        });
////        Map<String, String> httpThreshold = jedis.hgetAll("flink:dynamic_threshold_http:" + hour);
//        Map<String, String> httpThreshold = jedis.hgetAll("flink:dynamic_threshold_http");
//        HashMap<String, DelayAvgStd> mapHttpThreshold = new HashMap<>();
//        httpThreshold.forEach((key, value) -> {
//            mapHttpThreshold.put(key, new DelayAvgStd().ofHttp(value));
//        });
////        Map<Object, Object> dnsThreshold = redis.opsForHash().entries("flink:dynamic_threshold_dns:" + hour);
////        Map<Object, Object> tcpThreshold = redis.opsForHash().entries("flink:dynamic_threshold_tcp:" + hour);
////        Map<Object, Object> httpThreshold = redis.opsForHash().entries("flink:dynamic_threshold_http:" + hour);
//
//        HashMap<String, UserApp> mapApps = new HashMap<>();
//        appsInfo.forEach((item) -> mapApps.put(item.getId(), item));
//        HashMap<String, AreaInfo> mapAreas = new HashMap<>();
//        areasInfo.forEach((item) -> mapAreas.put(item.getAreaId(), item));
//        HashMap<String, Double> mapHttpDelay = new HashMap<>();
//        httpDelay.forEach((item) -> mapHttpDelay.put(item.getAppId() + ":" + item.getAreaId(), item.getDelayAvg()));
//        HashMap<String, Double> mapDnsDelay = new HashMap<>();
//        dnsDelay.forEach((item) -> mapDnsDelay.put(item.getAppId() + ":" + item.getAreaId(), item.getDelayAvg()));
//        HashMap<String, Double> mapTcpDelay = new HashMap<>();
//        tcpDelay.forEach((item) -> mapTcpDelay.put(item.getAppId() + ":" + item.getAreaId(), item.getDelayAvg()));
//
//        result.forEach((item) -> {
//            UserApp app = mapApps.get(item.getAppId());
//            item.setAppName(app.getName());
//            item.setApdexT(Long.parseLong(app.getPreferRespTime()));
//            item.setStartTime(FORMATTER.format(selectedTime));
//            item.setStatTime(FORMATTER.format(endTime));
//            item.setInterval(1);
//            HashMap<String, ExpEventAreaVO> mapEvent = new HashMap<>();
//            item.getAreas().forEach((areaEventItem) -> mapEvent.put(areaEventItem.getAreaId(), areaEventItem));
//            areasInfo.forEach((area) -> {
//                ExpEventAreaVO event = mapEvent.get(area.getAreaId());
//                if (event == null) {
//                    //补充没有体验值的区域数据
//                    event = new ExpEventAreaVO();
//                    event.setAreaId(area.getAreaId());
//                    item.getAreas().add(event);
//                }
//                event.setArea(area.getAreaName());
//                Double dns = mapDnsDelay.get(item.getAppId() + ":" + area.getAreaId());
//                event.setCurrentDnsDelay(dns == null ? 0 : dns);
//                Double tcp = mapTcpDelay.get(item.getAppId() + ":" + area.getAreaId());
//                event.setCurrentNetDelay(tcp == null ? 0 : tcp);
//                Double http = mapHttpDelay.get(item.getAppId() + ":" + area.getAreaId());
//                event.setCurrentAppDelay(http == null ? 0 : http);
//                DelayAvgStd dnsDelayStd = mapDnsThreshold.get(item.getAppId() + ":" + area.getAreaId());
//                event.setNormalDnsDelay(dnsDelayStd == null ? 0 : dnsDelayStd.getThreshold());
//                DelayAvgStd netDelayStd = mapTcpThreshold.get(item.getAppId() + ":" + area.getAreaId());
//                event.setNormalNetDelay(netDelayStd == null ? 0 : netDelayStd.getThreshold());
//                DelayAvgStd appDelayStd = mapHttpThreshold.get(item.getAppId() + ":" + area.getAreaId());
//                event.setNormalAppDelay(appDelayStd == null ? 0 : appDelayStd.getThreshold());
//            });
//        });
//        log.info("result: {}", result);
//        log.info("time consume: {} ms, size: {}", System.currentTimeMillis() - startTime, result.size());
//        log.info("result json: {}", JSON.toJSONString(result));
//    }
//
//    public static void expQuery() throws IOException {
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
//                .build(Resources.getResourceAsReader("mybatis.xml"));
//        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.SIMPLE, true);
//        UserExpEventMapper mapper = sqlSession.getMapper(UserExpEventMapper.class);
//        ExpQueryParam param = new ExpQueryParam();
//        LocalDateTime selectedTime = LocalDateTime.of(2019, 3, 4,12, 0);
//        LocalDateTime endTime = selectedTime.plusSeconds(59);
//        param.setSelectedTime(selectedTime);
//        long startTime = System.currentTimeMillis();
//        List<ExpEvent> tcpConnect = mapper.queryTcpConnect(param);
//        List<ExpEvent> tcpFlow = mapper.queryTcpFlow(param);
//        List<ExpEvent> dnsMetrics = mapper.queryDns(param);
//        List<ExpEvent> httpStatus = mapper.queryHttpStatus(param);
//        List<ExpEvent> httpRequest = mapper.queryHttpRequest(param);
//        List<ExpEvent> httpExperience = mapper.queryHttpExperience(param);
//        log.info("tcpConnect: {}\ntcpFlow:{}\ndnsMetrics:{}\nhttpStatus:{}\nhttpRequest:{}\nhttpExperience:{}\n",
//                toJsonString(tcpConnect), toJsonString(tcpFlow), toJsonString(dnsMetrics), toJsonString(httpStatus), toJsonString(httpRequest), toJsonString(httpExperience));
////        log.info("httpExperience: {}", JSON.toJSONString(httpExperience));
//        log.info("time consume: {}", System.currentTimeMillis() - startTime);
//    }
//
//    private static String toJsonString(List content) {
//        return JSON.toJSONString(content);
//    }
//
//
//    public static <T> List<T> keyMatch(Jedis jedis, String key, Class<T> clazz) {
//        List<T> infos = Collections.emptyList();
//        Set<String> keys = jedis.keys(key);
//        if (!CollectionUtils.isEmpty(keys)) {
//            infos = new ArrayList<>();
//            for (String item : keys) {
//                String json = jedis.get(item);
//                T t = JSON.parseObject(json, clazz);
//                infos.add(t);
//            }
//        }
//        return infos;
//    }
//}
