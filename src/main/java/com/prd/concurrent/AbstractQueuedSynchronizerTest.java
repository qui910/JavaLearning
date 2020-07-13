package com.prd.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试AQS同步器，
 * 并根据AQS实现自己的锁
 */
public class AbstractQueuedSynchronizerTest {

    public static void main(String[] args) {
        testNormalReentrantLock();
    }

    /**
     * 测试ReentrantLock
     * 并断点跟踪分析 lock和 AQS原理
     */
    private static void testNormalReentrantLock() {
        ReentrantLock lock = new ReentrantLock();
        Runnable runA =
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.lock();
                    System.out.println("线程"+Thread.currentThread().getName()+"开始");
//          for(;;){
//
//          }
                    try {
                        TimeUnit.SECONDS.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();
                    System.out.println("线程"+Thread.currentThread().getName()+"结束");
                };
        Thread thread1 = new Thread(runA,"thread1");
        Thread thread2 = new Thread(runA,"thread2");
        thread1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}
