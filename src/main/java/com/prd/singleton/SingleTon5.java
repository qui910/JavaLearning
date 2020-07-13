package com.prd.singleton;

/**
 * 懒汉式（线程不安全,同步代码块）【不可用】
 */
public class SingleTon5 {
    private  static SingleTon5 instance;

    private SingleTon5() {
    }

    public static SingleTon5 getInstance() {
        if (instance==null) {
            synchronized (SingleTon5.class) {
                instance = new SingleTon5();
            }
        }
        return instance;
    }
}
