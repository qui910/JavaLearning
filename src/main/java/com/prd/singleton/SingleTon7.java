package com.prd.singleton;

/**
 * 静态内部类【推荐】
 */
public class SingleTon7 {

    private SingleTon7() {
    }

    private static class SingleTonInstence {
        private static final SingleTon7 INSTANCE= new SingleTon7();
    }

    public static SingleTon7 getInstance() {
       return SingleTonInstence.INSTANCE;
    }
}
