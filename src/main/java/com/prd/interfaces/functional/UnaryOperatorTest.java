package com.prd.interfaces.functional;

import lombok.extern.slf4j.Slf4j;
import java.util.function.UnaryOperator;

/**
 * Java内置方法UnaryOperator的测试
 */
@Slf4j
public class UnaryOperatorTest {
    public static void main(String[] args) {
        method( s -> "A"+s,
                i -> i,
                r -> r+"B",
                f -> f+"C",
                "Me");
    }

    public static void method(UnaryOperator<String> before,
                              UnaryOperator<String> now,
                              UnaryOperator<String> after,
                              UnaryOperator<String> after1,
                              String number) {
        String result = now.compose(before).andThen(after).andThen(after1).apply(number);
        log.info("result:{}",result);
    }
}
