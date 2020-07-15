package com.prd.concurrent.lock;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试AQS同步器，并跟踪分析ReentrantLock和Condition
 */
public class AbstractQueuedSynchronizerTest {

    public static void main(String[] args) {
//        testNormalReentrantLock();
        testNormalReentrantLockAndCondition();
    }

    /**
     * 测试ReentrantLock
     * 并断点跟踪分析 lock和 AQS原理。
     * 分析：
     * AQS本身就是一个线程队列，将等待锁的队列都添加到队列中来。
假设这里使用的是非公平锁
（1） thread1
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

    /**
     * 测试ReentrantLock和Condition
     * 并断点跟踪分析 lock和 AQS原理
     */
    private static void testNormalReentrantLockAndCondition() {
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        Queue<String> queue = new ArrayDeque<>();
    Thread thread1 =
        new Thread(
            () -> {
              try {
                TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              lock.lock();
              System.out.println("线程" + Thread.currentThread().getName() + "开始");
              try {
                for (; ; ) {
                  if (queue.isEmpty()) {
                    condition.signalAll();
                    condition.await();
                  }
                  String num = queue.remove();
                  System.out.println("消费元素"+num);
                }
              } catch (InterruptedException e) {
                e.printStackTrace();
              } finally {
                lock.unlock();
                System.out.println("线程" + Thread.currentThread().getName() + "结束");
              }
            },
            "thread1");
    Thread thread2 =
        new Thread(
            () -> {
              try {
                TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              lock.lock();
              System.out.println("线程" + Thread.currentThread().getName() + "开始");
              try {
                for (; ; ) {
                  if (queue.size() == 2) {
                    condition.signalAll();
                    condition.await();
                  }
                   int num= new Random().nextInt();
                  queue.add(num + "");
                  System.out.println("生成元素"+num);
                }
              } catch (InterruptedException e) {
                e.printStackTrace();
              } finally {
                lock.unlock();
                System.out.println("线程" + Thread.currentThread().getName() + "结束");
              }
            },
            "thread2");
        thread1.start();
        thread2.start();
    }
}
