package com.prd.singleton;

/**
 * 懒汉式（线程不安全）【不可用】
 */
public class SingleTon3 {
    private  static SingleTon3 instance;

    private SingleTon3() {
    }

    public static SingleTon3 getInstance() {
        if (instance==null) {
            instance = new SingleTon3();
        }
        return instance;
    }
}
