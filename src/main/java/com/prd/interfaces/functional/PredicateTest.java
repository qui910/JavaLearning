package com.prd.interfaces.functional;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;

/**
 * Java内置函数Predicate测试
 */
@Slf4j
public class PredicateTest {

    public static void main(String[] args) {
        method( i->i>10,
                j->j<20,
                k->k<30,
                11);
    }

    public static void method(Predicate<Integer> current,
                       Predicate<Integer> and,
                       Predicate<Integer> or,
                       int num) {
        boolean ret = current.and(and).or(or).negate().test(num);
        log.info("测试结果:{}",ret);
    }
}
