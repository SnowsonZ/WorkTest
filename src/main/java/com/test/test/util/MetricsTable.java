package com.test.test.util;

/**
 * The type Metrics table.
 *
 * @author Snowson
 * @since 2019 /5/14 10:07
 */
public class MetricsTable {
    /**
     * The type Dns.
     */
    public class DNS {
        /**
         * dns查询总数
         * The constant TOTAL.
         */
        public static final String TOTAL = "dns_status.total";
        /**
         * dns查询时延最小值
         * The constant MIN_DELAY.
         */
        public static final String MIN_DELAY = "dns_status.min_delay";
        /**
         * dns查询时延最大值
         * The constant MAX_DELAY.
         */
        public static final String MAX_DELAY = "dns_status.max_delay";
        /**
         * dns查询响应错误数
         * The constant RSP_WRONG.
         */
        public static final String RSP_WRONG = "dns_status.rsp_wrong";
        /**
         * dns查询无响应数
         * The constant NO_RSP.
         */
        public static final String NO_RSP = "dns_status.no_resp";
        /**
         * dns查询响应成功数
         * The constant RSP_SUCCESS.
         */
        public static final String RSP_SUCCESS = "dns_status.rsp_success";
        /**
         * dns查询响应平均时延
         * The constant DELAY_AVG.
         */
        public static final String DELAY_AVG = "dns_status.delay_avg";
        /**
         * dns查询响应成功率
         * The constant SUCCESS_RATIO.
         */
        public static final String SUCCESS_RATIO = "dns_status.success_ratio";
    }

    /**
     * The type Http.
     */
    public class HTTP {

        /**
         * 体验失望用户的http响应时延
         * The constant RES_DELAY_UNSATISFIED.
         */
        public static final String RES_DELAY_UNSATISFIED = "http_delay.rsp_delay_unsatisfied";

        /**
         * 在线用户数
         * The constant ONLINE.
         */
        public static final String ONLINE = "http_status.online";
        /**
         * http响应状态为1xx的统计数
         * The constant COUNT_1xx.
         */
        public static final String COUNT_1XX = "http_status.count1xx";
        /**
         * http响应状态为2xx的统计数量
         * The constant COUNT_2xx.
         */
        public static final String COUNT_2XX = "http_status.count2xx";
        /**
         * http响应状态为3xx的统计数量
         * The constant COUNT_3xx.
         */
        public static final String COUNT_3XX = "http_status.count3xx";
        /**
         * http响应状态为4xx的统计数量
         * The constant COUNT_4xx.
         */
        public static final String COUNT_4XX = "http_status.count4xx";
        /**
         * http响应状态为5xx的统计数量
         * The constant COUNT_5xx.
         */
        public static final String COUNT_5XX = "http_status.count5xx";
        /**
         * http所有响应状态的统计数量
         * The constant TOTAL.
         */
        public static final String TOTAL = "http_status.total";
        /**
         * http响应成功率
         * The constant SUCCESS_RATIO.
         */
        public static final String SUCCESS_RATIO = "http_status.success_ratio";

        /**
         * 用户体验值
         * The constant AP_DEX.
         */
        public static final String AP_DEX = "http_delay.apdex";

        /**
         * http请求传输时延
         * The constant REQ_TRANS_DELAY_AVG.
         */
        public static final String REQ_TRANS_DELAY_AVG = "http_delay.req_trans_delay_avg";
        /**
         * http响应时延
         * The constant RSP_DELAY_AVG.
         */
        public static final String RSP_DELAY_AVG = "http_delay.rsp_delay_avg";
        /**
         * http响应传输时延
         * The constant RSP_TRANS_DELAY_AVG.
         */
        public static final String RSP_TRANS_DELAY_AVG = "http_delay.rsp_trans_delay_avg";
        /**
         * http总时延
         * The constant DELAY_AVG.
         */
        public static final String DELAY_AVG = "http_delay.average";

        /**
         * http并发请求数
         * The constant REQUEST_CONCURRENT_COUNT.
         */
        public static final String REQUEST_CONCURRENT_COUNT = "http_con_requests.count";
        /**
         * http并发请求总数
         * The constant REQUEST_TOTAL_COUNT.
         */
        public static final String REQUEST_TOTAL_COUNT = "http_con_requests.total_count";


    }

    /**
     * The type Tcp.
     */
    public class TCP {

        /**
         * 建连成功率
         * The constant SUCCESS_RATIO.
         */
        public static final String SUCCESS_RATIO = "tcp_status.success_ratio";
        /**
         * TCP连接成功统计数量
         * The constant SUCCESS.
         */
        public static final String SUCCESS = "tcp_status.success";
        /**
         * TCP连接失败统计数量
         * The constant FAILED.
         */
        public static final String FAILED = "tcp_status.failed";
        /**
         * TCP连接--服务器无响应状态百分比
         * The constant SRV_NO_RESP.
         */
        public static final String SRV_NO_RESP = "tcp_status.srv_no_resp_ratio";
        /**
         * TCP连接--客户端无响应状态百分比
         * The constant CLI_NO_RESP.
         */
        public static final String CLI_NO_RESP = "tcp_status.cli_no_resp_ratio";
        /**
         * TCP连接--服务器RST百分比
         * The constant SRV_REFUSE.
         */
        public static final String SRV_REFUSE = "tcp_status.srv_refuse_ratio";
        /**
         * TCP连接--客户端RST百分比
         * The constant CLI_REFUSE.
         */
        public static final String CLI_REFUSE = "tcp_status.cli_refuse_ratio";
        /**
         * TCP左右连接状态的统计数量
         * The constant STATUS_TOTAL.
         */
        public static final String STATUS_TOTAL = "tcp_status.total";

        /**
         * TCP上行有效载荷统计数
         * The constant UP_PKT_PAYLOAD.
         */
        public static final String UP_PKT_PAYLOAD = "tcp_retransmission.up_pkt_payload";
        /**
         * TCP上行重传包统计数
         * The constant UP_TCP_RETRANSMISSION.
         */
        public static final String UP_TCP_RETRANSMISSION = "tcp_retransmission.up_tcp_retransmission";
        /**
         * TCP下行有效载荷统计数
         * The constant DOWN_PKT_PAYLOAD.
         */
        public static final String DOWN_PKT_PAYLOAD = "tcp_retransmission.down_pkt_payload";
        /**
         * TCP下行重传包统计数
         * The constant DOWN_TCP_RETRANSMISSION.
         */
        public static final String DOWN_TCP_RETRANSMISSION = "tcp_retransmission.down_tcp_retransmission";
        /**
         * TCP上行重传率
         * The constant UP_TCP_RETRANSMISSION_RATIO.
         */
        public static final String UP_TCP_RETRANSMISSION_RATIO = "tcp_retransmission.up_tcp_retransmission_ratio";
        /**
         * TCP下行重传率
         * The constant DOWN_TCP_RETRANSMISSION_RATIO.
         */
        public static final String DOWN_TCP_RETRANSMISSION_RATIO = "tcp_retransmission.down_tcp_retransmission_ratio";

        /**
         * TCP建连时延平均值
         * The constant DELAY_AVERAGE.
         */
        public static final String DELAY_AVERAGE = "tcp_delay.average";
        /**
         * TCP建连时延最小值
         * The constant min_delay.
         */
        public static final String MIN_DELAY = "tcp_delay.min";
        /**
         * TCP建连时延最小大值
         * The constant max_delay.
         */
        public static final String MAX_DELAY = "tcp_delay.max";
        /**
         * 上行RTT
         * The constant RTT_UP.
         */
        public static final String RTT_UP = "tcp_delay.rtt_up";
        /**
         * 下行RTT
         * The constant RTT_DOWN.
         */
        public static final String RTT_DOWN = "tcp_delay.rtt_down";

        /**
         * 上行流量
         * The constant UP_BIT.
         */
        public static final String UP_BIT = "tcp_flow.up_bit";
        /**
         * 下行流量
         * The constant DOWN_BIT.
         */
        public static final String DOWN_BIT = "tcp_flow.down_bit";
        /**
         * 上行流速
         * The constant UP_BIT_SPEED.
         */
        public static final String UP_BIT_SPEED = "tcp_flow.up_bit_speed";
        /**
         * 下行流速
         * The constant DOWN_BIT_SPEED.
         */
        public static final String DOWN_BIT_SPEED = "tcp_flow.down_bit_speed";
        /**
         * 下行带宽
         * The constant DOWN_BANDWIDTH.
         */
        public static final String DOWN_BANDWIDTH = "tcp_flow.down_bandwidth";
        /**
         * 上行带宽
         * The constant UP_BANDWIDTH.
         */
        public static final String UP_BANDWIDTH = "tcp_flow.up_bandwidth";
        /**
         * 并发连接数
         * The constant CON_CONNECT.
         */
        public static final String CON_CONNECT = "tcp_flow.con_connect";
        /**
         * 连接总数
         * The constant TOTAL_CONNECT.
         */
        public static final String TOTAL_CONNECT = "tcp_flow.total_connect";
    }

    /**
     * The type Traffic.
     */
    public class TRAFFIC {
        /**
         * udp上行流量
         * The constant UP_BIT.
         */
        public static final String UP_BIT = "traffic_flow.up_bit";
        /**
         * udp下行流量
         * The constant DOWN_BIT.
         */
        public static final String DOWN_BIT = "traffic_flow.down_bit";
    }
}
