alert('app_2') {

    groupBy('app_id', 'loc_id')

    eval {
        http_failed_count = probe_http.countIf(http_code >= 400, 5 * 60)
        probe_http_success_count = probe_http.countIf(http_code < 400, 5 * 60)
        http_success_count = alert_http.sum(success, 5 * 60)
        http_count = alert_http.sum(total, 5 * 60)
        login_failed_count = probe_web.countIf(status == 1, 5 * 60)
        login_success_count = probe_web.countIf(status == 0, 5 * 60)
    }

    every(60)

    trigger {
        ((http_failed_count >= 3) && (http_success_count == 0 || http_success_count == null)) ||
                (http_failed_count == null && http_success_count == 0 && http_count > 0) || (login_failed_count >= 5)
    }

    then {
        message(http_failed_count: http_failed_count,
                http_success_count: http_success_count,
                login_failed_count: login_failed_count,
                probe_http_success_count: probe_http_success_count,
                login_success_count: login_success_count)

    }

}

alert_agg('app_2') {

    groupBy('app_id')

    eval {
        http_count = alert_http.sum(total, 5 * 60)
        http_success_count = alert_http.sum(success, 5 * 60)
        rsp_success_ratio = http_success_count / http_count

        http_failed_count = alert.app_2.min(http_failed_count, 60)
        login_failed_count = alert.app_2.min(login_failed_count, 60)
        probe_http_success_count = alert.app_2.max(probe_http_success_count, 60)
        login_success_count = alert.app_2.max(login_success_count, 60)

        success_count = alert.app_2.countIf(state == 0, 60)
        online_count = alert_tcp.last(online_count, 5 * 60)
        areas = alert.app_2.arrayIf(loc_id, state == 1, 60)
    }

    every(60)

    trigger {
        success_count == 0
    }

    recover {
        ((probe_http_success_count >= 3) || (rsp_success_ratio > 0)) && ((login_success_count == null) || (login_success_count >= 3))
    }

    then {
        message(online: online_count,
                areas: areas,
                probeHttp: http_failed_count,
                rspSuccessRatio: rsp_success_ratio,
                probeWebLogin: login_failed_count)

    }

}

#app_2

$@$;

alert('lowSuccess_2') {

    groupBy('app_id', 'loc_id')

    eval {
        http_count = probe_http.count(5 * 60)
        http_failed_count = probe_http.countIf(http_code >= 400, 5 * 60)
        http_success_count = probe_http.countIf(http_code < 400, 5 * 60)
        response_failed_count = alert_http.countIf((success_ratio < 0.8) && (success_ratio > 0), 5 * 60)
    }

    every(60)

    trigger {
        (http_failed_count >= 3) || (response_failed_count >= 3)
    }

    then {
        message(http_failed_count: http_failed_count, response_failed_count: response_failed_count, http_success_count: http_success_count)
    }

}

alert_agg('lowSuccess_2') {

    groupBy('app_id')

    eval {
        count = alert.lowSuccess_2.count(60)
        failed_count = alert.lowSuccess_2.countIf(http_failed_count >= 3, 60)
        failed_ratio = failed_count / count
        http_success_count = alert.lowSuccess_2.min(http_success_count, 60)
        response_failed_count = alert_http.countIf((success_ratio < 0.8) && (success_ratio > 0), 5 * 60)
        response_success_count = alert_http.countIf(success_ratio >= 0.8, 5 * 60)
        online_count = alert_tcp.last(online_count, 5 * 60)
        areas = alert.lowSuccess_2.arrayIf(loc_id, state == 1, 60)

    }

    every(60)

    trigger {
        (failed_ratio > 0.5 && failed_ratio < 1) || (response_failed_count >= 3)
    }
    recover {
        (response_success_count >= 3) || (http_success_count >= 3)
    }

    then {
        message(probeHttpRatio: failed_ratio, online: online_count, lowSuccessCount: response_failed_count, areas: areas)

    }

}


#lowSuccess_2

$@$;

alert('apdex_2') {

    groupBy('app_id')

    eval {
        satisfy_count = alert_http.sum(satisfy_count, 5 * 60)
        tolerate_count = alert_http.sum(tolerate_count, 5 * 60)
        success_count = alert_http.sum(success_count, 5 * 60)
        apdex = (satisfy_count + 0.5 * tolerate_count) / success_count

    }

    every(60)

    trigger {
        apdex < 0.7
    }

    then {
        message(apdex: apdex)

    }

}


alert_agg('apdex_2') {

    groupBy('app_id')

    eval {
        satisfy_count = alert_http.sum(satisfy_count, 5 * 60)
        tolerate_count = alert_http.sum(tolerate_count, 5 * 60)
        success_count = alert_http.sum(success_count, 5 * 60)
        disappoint_count = alert_http.sum(disappoint_count, 5 * 60)
        apdex = (satisfy_count + 0.5 * tolerate_count) / success_count
        online_count = alert_tcp.last(online_count, 5 * 60)
        areas = alert_http.array(loc_id, 5 * 60)
        total = alert_http.sum(total, 5 * 60)

    }

    every(60)

    level = 2

    trigger {
        apdex < 0.7
    }
    recover {
        apdex >= 0.7

    }
    then {
        message(apdex: apdex, satisfyCount: satisfy_count, tolerateCount: tolerate_count, successCount: success_count,
                disappointCount: disappoint_count, online: online_count, areas: areas, total: total)

    }

}


#apdex_2

$@$;

alert('network_2') {

    groupBy('app_id', 'loc_id')

    eval {
        tcp_timeout_count = probe_http.countIf(error_code == 9998, 5 * 60)
        tcp_success_count = probe_http.countIf(error_code == 0, 5 * 60)
        srv_no_resp_count = tcp_status.countIf(srv_no_resp_ratio > 0.3, 5 * 60)
        up_retrans_count = tcp_retransmission.countIf(up_ratio > 0.4, 5 * 60)
    }

    every(60)

    trigger {
        (tcp_timeout_count >= 3) || (srv_no_resp_count >= 3) || (up_retrans_count >= 3)
    }

    then {
        message(tcp_timeout_count: tcp_timeout_count, srv_no_resp_count: srv_no_resp_count, up_retrans_count: up_retrans_count, tcp_success_count: tcp_success_count)

    }

}

alert_agg('network_2') {

    groupBy('app_id')

    eval {
        tcp_timeout_count = alert.network_2.min(tcp_timeout_count, 60)
        tcp_success_count = alert.network_2.max(tcp_success_count, 60)
        srv_no_resp_count = tcp_status.countIf(srv_no_resp_ratio > 0.3, 5 * 60)
        srv_resp_count = tcp_status.countIf(srv_no_resp_ratio <= 0.3, 5 * 60)
        up_retrans_count = tcp_retransmission.countIf(up_ratio > 0.4, 5 * 60)
        low_up_retrans_count = tcp_retransmission.countIf(up_ratio <= 0.4, 5 * 60)
        online_count = alert_tcp.last(online_count, 5 * 60)
        areas = alert.network_2.arrayIf(loc_id, state == 1, 60)
    }

    every(60)

    trigger {
        (tcp_timeout_count >= 3) || (srv_no_resp_count >= 3) || (up_retrans_count >= 3)
    }
    recover {
        (tcp_success_count >= 3) || (srv_resp_count >= 3) || (low_up_retrans_count >= 3)
    }

    then {
        message(probeTcp: tcp_timeout_count, FailServerNoResp: srv_no_resp_count, upRetrans: up_retrans_count, online: online_count, areas: areas)

    }

}

#network_2

$@$;

alert_agg('slowResp_2') {

    groupBy('app_id')

    eval {
        slow_resp_count = http_delay.countIf(rsp_delay_avg > 4000, 10 * 60)
        fast_resp_count = http_delay.countIf(rsp_delay_avg < 4000, 10 * 60)
        online_count = alert_tcp.last(online_count, 5 * 60)

    }

    every(60)

    trigger {
        slow_resp >= 3
    }

    recover {
        fast_resp_count >= 5
    }
    then {
        message(slow_resp: slow_resp, fast_resp_count: fast_resp_count, online: online_count)

    }

}
#slowResp_2