package com.prd.singleton;

/**
 * 饿汉式（静态代码块）【可用】
 */
public class SingleTon2 {
    private  final static SingleTon2 INSTANCE;

    static {
        INSTANCE = new SingleTon2();
    }

    private SingleTon2() {
    }

    public static SingleTon2 getInstance() {
        return INSTANCE;
    }
}
