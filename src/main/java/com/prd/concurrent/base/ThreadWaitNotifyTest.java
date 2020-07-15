package com.prd.concurrent.base;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

/**
 * 线程通信测试
 */
public class ThreadWaitNotifyTest {

    public static void main(String[] args) {
//        testCreateAndConsumerModel();
//        testWaitNotifyNormalUsed();
        testNotifyAndNotifyAll();
    }



    /**
     * 测试Notify和NotifyAll
     * 3个线程，线程1和线程2首先被阻塞，线程3唤醒他们
     * start先执行不代表线程先启动
     * 结果：
     线程Thread-0got resourceA lock
     线程Thread-0waits to start
     线程Thread-1got resourceA lock
     线程Thread-1waits to start
     线程Thread-2 notifyAll
     线程Thread-1's waiting to end
     线程Thread-0's waiting to end
     */
    private static void testNotifyAndNotifyAll() {
        Object resourceA = new Object();

        Runnable runA = ()->{
            synchronized (resourceA) {
                System.out.println("线程"+Thread.currentThread().getName()+"got resourceA lock");
                try {
                    System.out.println("线程"+Thread.currentThread().getName()+"waits to start");
                    resourceA.wait();
                    System.out.println("线程"+Thread.currentThread().getName()+"'s waiting to end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread(runA);
        Thread thread2 = new Thread(runA);
        Thread thread3 = new Thread(()->{
            synchronized (resourceA){
                resourceA.notifyAll();
                System.out.println("线程"+Thread.currentThread().getName()+" notifyAll");
            }
        });
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread3.start();
    }

    /**
     * 展示wait和monity的基本用法
     * 1.研究代码执行顺序
     * 2.证明wait释放锁
     * 结果：
     线程Thread-0开始执行
     线程Thread-1调用了notify()
     线程Thread-0重新获得锁
     */
    private static void testWaitNotifyNormalUsed() {
        Object obj = new Object();

        Thread thread1 = new Thread(()->{
            synchronized (obj) {
                System.out.println("线程"+Thread.currentThread().getName()+"开始执行");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    // 响应中断
                    e.printStackTrace();
                }
                System.out.println("线程"+Thread.currentThread().getName()+"重新获得锁");
            }
        });

        Thread thread2 = new Thread(()->{
            synchronized (obj) {
                obj.notify();
                System.out.println("线程"+Thread.currentThread().getName()+"调用了notify()");
            }
        });

        //必须保证thread1先执行，否则先notify无意义
        thread1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }


    /**
     * 测试wait和notify构建
     * 生成者，消费者模式
     */
    private static void testCreateAndConsumerModel() {

        // 生产者
        class ThreadA extends Thread {

            private Deque<String> queue;
            private int maxSize;

            public ThreadA(Deque<String> queue, int maxSize) {
                this.queue = queue;
                this.maxSize = maxSize;
            }

            @Override
            public void run() {
                while (true) {
                    synchronized (queue) {
                        // 队列超过100个后，停止生产，进入等待
                        while (queue.size() == maxSize) {
                            try {
                                queue.notifyAll();
                                queue.wait();
                                System.out.println("Queue is Full");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        Random random = new Random();
                        int i = random.nextInt();
                        String msg = "test" + i;
                        queue.add(msg);
                        System.out.println("生产：" + msg);
                    }
                }
            }
        }

        class ThreadB extends Thread {

            private Deque<String> queue;
            private int maxSize;

            public ThreadB(Deque<String> queue, int maxSize) {
                this.queue = queue;
                this.maxSize = maxSize;
            }

            @Override
            public void run() {
                while (true) {
                    synchronized (queue) {
                        // 队列空后，停止消费，进入等待
                        while (queue.size() == 0) {
                            try {
                                queue.notifyAll();
                                queue.wait();
                                System.out.println("Queue is Empty");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        String msg = queue.remove();
                        System.out.println("消费：" + msg);
                    }
                }
            }
        }

        LinkedList<String> linkedList = new LinkedList<String>();
        int maxSize=2;

        ThreadA threadA = new ThreadA(linkedList,maxSize);
        ThreadB threadB = new ThreadB(linkedList,maxSize);
        threadA.start();
        threadB.start();
    }

}
