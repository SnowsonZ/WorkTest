alert('slowResp_1') {

    groupBy('app_id')

    eval {
        http_slow_resp_count = http_delay.countIf(online > 1 && count > 50 && rsp_delay_avg > 4000, 5 * 60)
        tcp_slow_resp_count = tcp_delay.countIf(online > 1 && count > 50 && avg_response_time > 4000, 5 * 60)

        http_fast_resp_count = http_delay.countIf(count > 50 && rsp_delay_avg <= 4000, 5 * 60)
        tcp_fast_resp_count = tcp_delay.countIf(count > 50 && avg_response_time <= 4000, 5 * 60)
    }

    every(60)

    level = 2

    trigger {
        (config.app[app_id].urlType == 'HTTP' && http_slow_resp_count >= 3) ||
                (config.app[app_id].urlType == 'HTTPS' && tcp_slow_resp_count >= 3) ||
                (config.app[app_id].urlType == 'TCP' && tcp_slow_resp_count >= 3)
    }

    recover {
        (config.app[app_id].urlType == 'HTTP' && http_fast_resp_count >= 5) ||
                (config.app[app_id].urlType == 'HTTPS' && tcp_fast_resp_count >= 5) ||
                (config.app[app_id].urlType == 'TCP' && tcp_fast_resp_count >= 5)
    }

}


alert_agg('slowResp_1') {

    groupBy('app_id')

    eval {
        http_slow_resp_count = http_delay.countIf(online > 1 && count > 50 && rsp_delay_avg > 4000, 5 * 60)
        tcp_slow_resp_count = tcp_delay.countIf(online > 1 && count > 50 && avg_response_time > 4000, 5 * 60)

        http_fast_resp_count = http_delay.countIf(count > 50 && rsp_delay_avg <= 4000, 5 * 60)
        tcp_fast_resp_count = tcp_delay.countIf(count > 50 && avg_response_time <= 4000, 5 * 60)

        online_count = alert_tcp.last(online_count, 5 * 60)
        http_areas = http_delay.uniqueArray(loc_id, 5 * 60)
        tcp_areas = tcp_delay.uniqueArray(loc_id, 5 * 60)

    }

    every(60)

    level = 2

    trigger {
        (config.app[app_id].urlType == 'HTTP' && http_slow_resp_count >= 3) ||
                (config.app[app_id].urlType == 'HTTPS' && tcp_slow_resp_count >= 3) ||
                (config.app[app_id].urlType == 'TCP' && tcp_slow_resp_count >= 3)
    }

    recover {
        (config.app[app_id].urlType == 'HTTP' && http_fast_resp_count >= 5) ||
                (config.app[app_id].urlType == 'HTTPS' && tcp_fast_resp_count >= 5) ||
                (config.app[app_id].urlType == 'TCP' && tcp_fast_resp_count >= 5)
    }

    then {
        message(online: online_count,
                httpAreas: http_areas,
                tcpAreas: tcp_areas,

                httpSlowRespCount: http_slow_resp_count,
                tcpSlowRespCount: tcp_slow_resp_count)

    }

}

