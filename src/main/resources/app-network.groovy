alert('appNetwork_1') {

    groupBy('app_id', 'loc_id')

    eval {
        tcp_timeout_count = probe_http.countIf(error_code == 7, 5 * 60)
        srv_no_resp_count = tcp_status.countIf(srv_no_resp_ratio > 0.3, 10 * 60)
        up_retrans_count = tcp_retransmission.countIf(up_ratio > 0.4, 10 * 60)
    }

    every(60)

    trigger {
        (tcp_timeout_count >= 3) || (srv_no_resp_count >= 3) || (up_retrans_count >= 3)
    }

    then {
        message(tcp_timeout_count: tcp_timeout_count, srv_no_resp_count: srv_no_resp_count, up_retrans_count: up_retrans_count)

    }

}

alert_agg('appNetwork_1') {

    groupBy('app_id')

    eval {
        tcp_timeout_count = alert.appNetwork_1.sum(tcp_timeout_count, 60)
        srv_no_resp_count = tcp_status.countIf(srv_no_resp_ratio > 0.3, 10 * 60)
        up_retrans_count = tcp_retransmission.countIf(up_ratio > 0.4, 10 * 60)
    }

    every(60)

    trigger {
        (tcp_timeout_count >= 3) || (srv_no_resp_count >= 3) || (up_retrans_count >= 3)
    }

    then {
        message(tcp_timeout_count: tcp_timeout_count, srv_no_resp_count: srv_no_resp_count, up_retrans_count: up_retrans_count)

    }

}
#appNetwork_1

