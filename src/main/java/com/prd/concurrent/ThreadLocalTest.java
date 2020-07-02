package com.prd.concurrent;

/**
 * ThreadLocal测试
 */
public class ThreadLocalTest {
    public static final ThreadLocal<String> localVarable = new ThreadLocal();

    private static void print(String str) {
        // 打印当前线程本地内存中localVarable变量的值
        System.out.println(str+":"+localVarable.get());

        // 清除当前线程本地内存中的localVarable变量
        // localVarable.remove();
    }

    public static void main(String[] args) {
        new Thread(()->{
            // 设置当前线程本地变量localVarable
            localVarable.set(Thread.currentThread().getName()+"-localVarable");

            print(Thread.currentThread().getName());
            localVarable.remove();
            System.out.println("remove after"+":"+localVarable.get());
        }).start();

        new Thread(()->{
            // 设置当前线程本地变量localVarable
            localVarable.set(Thread.currentThread().getName()+"-localVarable");

            print(Thread.currentThread().getName());

            System.out.println("remove after"+":"+localVarable.get());
        }).start();
    }
}
