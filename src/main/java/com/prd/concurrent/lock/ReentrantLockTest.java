package com.prd.concurrent.lock;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {
//        testNormalUsed();
        testCondition();
    }

    /**
     * 演示可中断的非公平锁用法
     */
    private static void testNormalUsed() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Runnable run =
            () -> {
              System.out.println("线程"+Thread.currentThread().getName()+"准备获得锁");
              // lock不响应中断
//              lock.lock();
                try {
                    // 更换lockInterruptibly 响应中断
                    lock.lockInterruptibly();
                    System.out.println("线程"+Thread.currentThread().getName()+"获得锁");
                    for(;;) {

                    }
                } catch (InterruptedException e) {
                    System.out.println("线程"+Thread.currentThread().getName()+"响应中断");
                } finally{
                    // 这里必须添加此判断，因为线程B被阻塞从而响应中断后，如果不加此判断的话
                    // 会提示错误java.lang.IllegalMonitorStateException，是因为线程B并未获得锁，而unlock的话就提示错误
                    if (lock.hasQueuedThread(Thread.currentThread())) {
                        lock.unlock();
                    }
                }
            };

        Thread threada = new Thread(run,"A");
        Thread threadb = new Thread(run,"B");

        threada.start();
        Thread.sleep(1000);
        threadb.start();
        Thread.sleep(1000);

        System.out.println("线程"+threadb+"中断前状态"+threadb.getState().name());
        threadb.interrupt();
        System.out.println("线程"+threadb+"中断后状态"+threadb.getState().name());

        Thread threadc = new Thread(()->{
            System.out.println("线程"+Thread.currentThread().getName()+"准备获得锁");
            if (lock.tryLock()) {
                try{
                    System.out.println("线程"+Thread.currentThread().getName()+"获得锁");
                } finally{
                    lock.unlock();
                }
            } else {
                System.out.println("线程"+Thread.currentThread().getName()+"获得锁失败");
            }
        },"C");
        threadc.start();
    }


    /**
     * 这种使用condition的生产者消费者模式，存在问题
     * 就是只能是仓库生产慢后，才能消费。消费的同时是无法生产的。
     * 消费者的情况同理
     */
    private static void testCondition() {
        ReentrantLock lock = new ReentrantLock();
        AtomicInteger num = new AtomicInteger();
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        Condition con1 = lock.newCondition();

    Thread threadA =
        new Thread(
            () -> {
              while (true) {
                lock.lock();
                try {
                  if (num.get() > 10) {
                    con1.await();
                  }
                  String nums = new Random().nextInt() + "";
                  queue.offer(nums);
                  Thread.sleep(1000);
                  num.incrementAndGet();
                  System.out.println("线程"+Thread.currentThread().getName()+"生成"+nums);
                  con1.signal();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                } finally {
                  lock.unlock();
                }
              }
            },
            "A");

        Thread threadB = new Thread(()->{
            while(true){
                lock.lock();
                try{
                    if (num.get()<1) {
                        con1.await();
                    }
                    String nums = queue.poll();
                    Thread.sleep(1000);
                    num.decrementAndGet();
                    System.out.println("线程"+Thread.currentThread().getName()+"消费"+nums);
                    con1.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    lock.unlock();
                }
            }
        },"B");

        threadA.start();
        threadB.start();
    }


}
