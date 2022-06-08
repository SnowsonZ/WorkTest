package com.learn.snnipet.util;

import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

//@Slf4j
public class HttpUtil {

    private static final Logger logger = LoggerFactory
            .getLogger(HttpUtil.class);
    public static String JSESSIONID;

    public static String http(String url, Map<String, String> params, Boolean flag) throws UnsupportedEncodingException {

        URL u = null;
        HttpURLConnection con = null;
        // 构建请求参数
//        StringBuffer sb = new StringBuffer();
//        String param = "";
//        if (params != null) {
//            for (Entry<String, String> e : params.entrySet()) {
//                sb.append(e.getKey());
//                sb.append("=");
//                sb.append(URLEncoder.encode(e.getValue(), "UTF-8"));
//                sb.append("&");
//            }
//            param = sb.substring(0, sb.length() - 1);
//        }
//        logger.debug("send_url: " + url);
//        logger.debug("send_data: " + param);
        // 尝试发送请求
        try {
            u = new URL(url);
            logger.info("request url: {}", url);
            con = (HttpURLConnection) u.openConnection();
            if (flag && !StringUtils.isEmpty(JSESSIONID)) {
                con.setRequestProperty("Cookie", JSESSIONID);
            }
            //// POST 只能为大写，严格限制，post会不识别
            con.setRequestMethod("POST");
            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            if(params != null && params.size() > 0) {
                DataOutputStream dos = new DataOutputStream(con.getOutputStream());
                //fastJson库
                String param = JSON.toJSONString(params);
                logger.info("request param: {}", param);
                dos.writeBytes(param);
            }

            // 读取返回内容
            StringBuffer buffer = new StringBuffer();
            try {
                //一定要有返回值，否则无法把请求发送给server端。
                InputStream inputStream = con.getInputStream();
                //TODO 若cookie失效，请重新登录
                if (StringUtils.isEmpty(JSESSIONID)) {
                    String id = con.getHeaderField("Set-Cookie");
                    JSESSIONID = id.substring(0, id.indexOf(';'));
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String temp;
                while ((temp = br.readLine()) != null) {
                    buffer.append(temp);
                    buffer.append("\n");
                }
            } catch (Exception e) {
                logger.error("error_url:" + url);
                logger.error(e.toString());
                e.printStackTrace();
            }
            return buffer.toString();
        } catch (Exception e) {
            logger.error("error_url:" + url);
            logger.error(e.toString());
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }

//    public static String doGet(String url, String charset) throws Exception {
//        /* 1 生成 HttpClinet 对象并设置参数 */
//        HttpClient httpClient = new HttpClient();
//        // 设置 Http 连接超时为5秒
//        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
//
//        /* 2 生成 GetMethod 对象并设置参数 */
//        GetMethod getMethod = new GetMethod(url);
//        // 设置 get 请求超时为 5 秒
//        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
//        // 设置请求重试处理，用的是默认的重试处理：请求三次
//        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
//        String response = "";
//        /* 3 执行 HTTP GET 请求 */
//        try {
//            int statusCode = httpClient.executeMethod(getMethod);
//            /* 4 判断访问的状态码 */
//            if (statusCode != HttpStatus.SC_OK) {
//                logger.error("Method failed: " + getMethod.getStatusLine());
//            }
//            // 读取 HTTP 响应内容，这里简单打印网页内容
//            byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
//            response = new String(responseBody, charset);
//            // 读取为 InputStream，在网页内容数据量大时候推荐使用
//            // InputStream response = getMethod.getResponseBodyAsStream();
//
//        } catch (HttpException e) {
//            // 发生致命的异常，可能是协议不对或者返回的内容有问题
//            logger.error("error_url:"+url);
//            e.printStackTrace();
//        } catch (IOException e) {
//            // 发生网络异常
//            e.printStackTrace();
//        } finally {
//            /* 6 .释放连接 */
//            getMethod.releaseConnection();
//        }
//        return response;
//    }
//
//    public static String doPost(String url,String req) {
//        String result="";
//        HttpClient httpClient = new HttpClient();
//        String header = "application/json; charset=UTF-8";
//        try{
//            PostMethod postMethod = new PostMethod(url);
//            byte[] bytes = req.getBytes("utf-8");
//            InputStream inputStream = new ByteArrayInputStream(bytes, 0, bytes.length);
//            RequestEntity requestEntity = new InputStreamRequestEntity(
//                    inputStream,
//                    bytes.length,
//                    header
//            );
//            postMethod.setRequestEntity(requestEntity);
//            int statusCode = httpClient.executeMethod(postMethod);
//            String ResponseData = postMethod.getResponseBodyAsString();
//            if (statusCode == 200) {
//                result=ResponseData;
//                System.out.println(ResponseData);
////                JSONObject json = JSONObject.fromObject(ResponseData);
////
////                JSONObject res=json.getJSONObject("result");
//            }
//        }catch (Exception e)
//        {
//
//        }
//        return result;
//    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "8888");
//        String s ="JSESSIONID=333";
//        System.out.println(s.substring(11,s.length()));
        String login = HttpUtil.http("http://172.17.161.94/frame/api/v1/session/login", new HashMap<String, String>() {{
            put("username", "admin");
            put("password", "admin");
        }}, false);
        logger.info(login);
        String trendRequest = HttpUtil.http("http://172.17.161.94/frame/api/base_web_server/v1/exhibition/trends/request", new HashMap<String, String>() {{
            put("startTime", "1544714280000");
            put("endTime", "1544717880000");
        }}, true);
        logger.info(trendRequest);
        String appList = HttpUtil.http("http://172.17.161.94/frame/api/base_web_server/v1/exhibition/businessList", null, true);
        logger.info(appList);

        String appsIndicator = HttpUtil.http("http://172.17.161.94/frame/api/base_web_server/v1/exhibition/area/all/experience", null, true);
        logger.info(appsIndicator);

        String appIndicator = HttpUtil.http("http://172.17.161.94/frame/api/base_web_server/v1/exhibition/area/experience", new HashMap<String, String>() {{
            put("appId", "e19c8e8e-f96c-11e8-aa18-0242ac170002");
        }}, true);
        logger.info(appIndicator);
    }
}