alert('low_success') {

    groupBy('app_id', 'loc_id')

    eval {
        http_count = probe_http.count(5 * 60)
        http_failed_count = probe_http.countIf(error_code != null, 5 * 60)
        response_failed_count = http_status.countIf(success_ratio < 0.8, 5 * 60)
    }

    every(60)

    trigger {
        (http_failed_count >= 3) || (http_success_count >= 3)
    }

    then {
        message(http_failed_count: http_failed_count, response_failed_count: response_failed_count)
    }

}

alert_agg('low_success') {

    groupBy('app_id')

    eval {
        count = alert.low_success.count(60)
        failed_count = alert.low_success.countIf(state == 1, 60)
        failed_ratio = failed_count / count
        response_failed_count = http_status.countIf(success_ratio < 0.8, 5 * 60)
        online_count = alert_tcp.last(online_count, 5 * 60)

    }

    every(60)

    trigger {
        (failed_ratio > 0.5) || (response_failed_count >= 3)
    }

    then {
        message(success_ratio: success_ratio, online_count: online_count, response_failed_count: response_failed_count)

    }

}

