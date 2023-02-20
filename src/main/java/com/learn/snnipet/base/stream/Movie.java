package com.learn.snnipet.base.stream;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;

/**
 *
 * @author Snowson
 * @since 2019/3/12 22:29
 */
@Data
public class Movie {
    private double m1 = -1.0;
    private int m2 = -1;
    private String type;

    public static void main(String[] args) {

        Predicate<Map.Entry<String, Object>> judge = (x) ->
                x.getKey().equals("type") || (!x.getKey().equals("type")
                        && ((Number) x.getValue()).doubleValue() >= 0);

        Movie m = new Movie();
        m.setM1(30);
        m.setM2(-1);
        m.setType("1");
        Movie n = new Movie();
        n.setM1(10);
        n.setM2(-1);
        n.setType("2");
        Movie p = new Movie();
        p.setM1(-1);
        p.setM2(100);
        p.setType("1");
        Movie q = new Movie();
        q.setM1(-1);
        q.setM2(1000);
        q.setType("2");

        List<Movie> ms = Arrays.asList(m, p);
        List<Movie> mm = Arrays.asList(n, q);

        Stream.of(ms.stream(), mm.stream()).flatMap(ele -> ele)
                .collect(Collectors.groupingBy(Movie::getType)).entrySet().stream()
                .map(entry -> {
                    Map<String, Object> ret =
                            entry.getValue().stream().map(obj -> JSON.parseObject(JSON.toJSONString(obj), Map.class))
                                    .flatMap(md -> ((Set<Map.Entry<String, Object>>) md.entrySet()).stream().filter(judge))
                                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a));
                    return ret;
                }).map(dict -> JSON.parseObject(JSON.toJSONString(dict), Movie.class))
                .forEach(System.out::println);
    }
}
