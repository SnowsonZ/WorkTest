alert('app_3') {

    groupBy('app_id', 'loc_id')

    eval {
        http_failed_count = probe_http.countIf(http_code >= 400, 10 * 60)
        http_success_count = http_status.sum(success, 5 * 60)
        login_failed_count = probe_web.countIf(status == 1, 10 * 60)
    }

    every(60)

    trigger {
        ((http_failed_count >= 4) && (http_success_count == 0 || http_success_count == null)) || (http_failed_count == null && http_success_count == 0) || (login_failed_count >= 4)
    }

    then {
        message(http_failed_count: http_failed_count, http_success_count: http_success_count, login_failed_count: login_failed_count)

    }

}

alert_agg('app_3') {

    groupBy('app_id')

    eval {
        http_count = http_status.sum(total, 5 * 60)
        http_success_count = http_status.sum(success, 5 * 60)
        rsp_success_ratio = http_success_count / http_count

        http_failed_count = alert.app_3.min(http_failed_count, 60)
        login_failed_count = alert.app_3.min(login_failed_count, 60)

        success_count = alert.app_3.countIf(state == 0, 60)
        online_count = alert_tcp.last(online_count, 5 * 60)
        areas = alert.app_3.arrayIf(loc_id, state == 1, 60)
    }

    every(60)

    trigger {
        success_count == 0
    }

    then {
        message(online: online_count,
                areas: areas,
                probeHttp: http_failed_count,
                rspSuccessRatio: rsp_success_ratio,
                probeWebLogin: login_failed_count)

    }

}
#app_3