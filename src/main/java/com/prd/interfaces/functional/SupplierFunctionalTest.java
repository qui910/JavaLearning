package com.prd.interfaces.functional;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * Java内置函数式接口Supplier测试
 */
@Slf4j
public class SupplierFunctionalTest {

    public static void main(String[] args) {
        String msgA = "A";
        String msgB = "B";
        log.info("getNewString:"+getNewString(()->msgA+msgB));
    }

    public static String getNewString(Supplier<String> function) {
        return function.get();
    }
}
