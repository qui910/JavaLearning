package com.prd.concurrent.juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch的用法类似join，但是比Join更为灵活
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        testNormalUsed();
    }

    /**
     * 测试CountDownLatch一般用法
     */
    private static void testNormalUsed() throws InterruptedException {
        CountDownLatch count = new CountDownLatch(2);
        System.out.println("主线程开始");
        new Thread(
                () -> {
                  System.out.println("线程"+Thread.currentThread().getName()+"开始执行");
                    try {
                        Thread.sleep(1000);
                        System.out.println("线程"+Thread.currentThread().getName()+"执行完成");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally{
                        count.countDown();
                    }
                },"A")
            .start();
        new Thread(
                () -> {
                    System.out.println("线程"+Thread.currentThread().getName()+"开始执行");
                    try {
                        Thread.sleep(2000);
                        System.out.println("线程"+Thread.currentThread().getName()+"执行完成");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally{
                        count.countDown();
                    }
                },"B")
                .start();
        count.await();
        System.out.println("主线程执行完成");
    }
}
