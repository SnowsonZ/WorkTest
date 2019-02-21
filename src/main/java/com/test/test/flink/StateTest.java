package com.test.test.flink;

import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import scala.Tuple2;

/**
 * @author Snowson
 * @since 2019/2/13 14:40
 */
@Slf4j
public class StateTest {
    public static void main(String[] args) {
        TypeInformation<Tuple2<String, Long>> typeInformation
                = TypeInformation.of(new TypeHint<Tuple2<String, Long>>() {
        });
        TypeInformation<List<String>> listType = TypeInformation.of(new TypeHint<List<String>>() {
        });
    }
}
