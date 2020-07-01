package com.prd.concurrent;

public class ThreadYieldTest {

    public ThreadYieldTest() {
        new Thread(()->{
            for (int i=0;i<5;i++) {
                System.out.println(Thread.currentThread().getName()+":"+i);
                if ((i % 5) == 0) {
                   System.out.println(Thread.currentThread().getName()+"让出CPU时间片");

                   //当前线程让出CPU执行权，放弃时间片，进行下一轮调度
                   // Thread.yield();
                }
            }
            System.out.println(Thread.currentThread().getName()+"is over");
        }).start();
    }

    public static void main(String[] args) {
        new ThreadYieldTest();
        new ThreadYieldTest();
        new ThreadYieldTest();
    }
}
