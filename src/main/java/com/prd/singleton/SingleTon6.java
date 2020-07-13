package com.prd.singleton;

/**
 * 双重检查（推荐,面试中推荐介绍）
 */
public class SingleTon6 {
    private volatile static SingleTon6 instance;

    private SingleTon6() {
    }

    public static SingleTon6 getInstance() {
        if (instance==null) {
            synchronized (SingleTon6.class) {
                if (instance==null) {
                    instance = new SingleTon6();
                }
            }
        }
        return instance;
    }
}
