package com.prd.singleton;

/**
 * 枚举【推荐，生成环境中推荐】
 */
public enum SingleTon8 {
    INSTANCE;

    public void dosomething() {
       // 与单例无关，只是在调用单例后可以执行的方法
        System.out.println("dosomething");
    }

    public static void main(String[] args) {
        SingleTon8.INSTANCE.dosomething();
    }
}
