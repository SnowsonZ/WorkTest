alert_agg('apdex_1') {

    groupBy('app_id')

    eval {
        satisfy_count = http_delay.sum(satisfy_count, 5 * 60)
        tolerate_count = http_delay.sum(tolerate_count, 5 * 60)
        success_count = http_delay.sum(success_count, 5 * 60)
        apdex = (satisfy_count + 0.5*tolerate_count)/success_count
        online_count = alert_tcp.last(online_count, 5 * 60)

    }

    every(60)

    trigger {
        apdex < 0.7
    }

    then {
        message(apdex: apdex, online_count: online_count)

    }

}
#apdex_1