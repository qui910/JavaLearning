package com.prd.singleton;

/**
 * 懒汉式（线程安全）【不推荐】
 */
public class SingleTon4 {
    private  static SingleTon4 instance;

    private SingleTon4() {
    }

    public synchronized static SingleTon4 getInstance() {
        if (instance==null) {
            instance = new SingleTon4();
        }
        return instance;
    }
}
