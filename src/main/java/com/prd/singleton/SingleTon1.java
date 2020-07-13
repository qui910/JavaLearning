package com.prd.singleton;

/**
 * 饿汉式（静态常量）【可用】
 */
public class SingleTon1 {
    private  final static SingleTon1 INSTANCE = new SingleTon1();

    private SingleTon1() {
    }

    public static SingleTon1 getInstance() {
        return INSTANCE;
    }
}
