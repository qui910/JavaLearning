package com.prd.interfaces.functional;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

/**
 * Java内置函数接口Function测试
 */
@Slf4j
public class FunctionTest {

    public static void main(String[] args) {
        method(s -> Integer.parseInt(s),
                i -> i*i,
                r -> String.valueOf(r),
                f -> f+"个",
                "10");
    }

    public static void method(Function<String,Integer> before,
                       Function<Integer,Integer> now,
                       Function<Integer,String> after,
                       Function<String,String> after1,
                       String number) {
        String result = now.compose(before).andThen(after).andThen(after1).apply(number);
        log.info("result:{}",result);
    }
}
