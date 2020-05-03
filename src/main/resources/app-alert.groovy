alert('lowSuccess_1') {

    groupBy('app_id', 'loc_id')

    eval {
        http_failed_count = probe_http.countIf(http_code >= 400, 5 * 60)
        http_success_count = probe_http.countIf((http_code > 0) && (http_code < 400), 5 * 60)
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

alert_agg('lowSuccess_1') {

    groupBy('app_id')

    eval {
        count = alert.lowSuccess_1.countIf(http_failed_count != null, 60)
        failed_count = alert.lowSuccess_1.countIf(http_failed_count >= 3, 60)
        failed_ratio = failed_count / count
        http_success_count = alert.lowSuccess_1.min(http_success_count, 60)
        http_count = alert_http.sum(total, 5 * 60)
        response_failed_count = alert_http.countIf((success_ratio < 0.8) && (success_ratio > 0), 5 * 60)
        response_success_count = alert_http.countIf(success_ratio >= 0.8, 5 * 60)
        online_count = alert_tcp.last(online_count, 5 * 60)
        areas = alert.lowSuccess_1.arrayIf(loc_id, state == 1, 60)

    }

    every(60)

    trigger {
        (failed_ratio > 0.5 && failed_ratio < 1) || (response_failed_count >= 3)
    }
    recover {
        (http_count > 0 && response_success_count >= 3) || ((http_count == null || http_count == 0) && (http_success_count >= 3))
    }

    then {
        message(probeHttpRatio: failed_ratio, http_success_count: http_success_count, lowSuccessCount: response_failed_count, online: online_count, areas: areas)

    }

}