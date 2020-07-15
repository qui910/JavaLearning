package com.prd.concurrent.lock;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * 自定义先进先出的锁
 */
public class MyLockSupportLock {
    /**
     * 锁被获取的标记
     */
    private AtomicBoolean locked = new AtomicBoolean();
    /**
     * 等待锁的队列
     */
    private Queue<Thread> waiters = new ConcurrentLinkedDeque<Thread>();


    public void lock() {
        boolean wasInterrupted = false;
        //  当前线程加入等待队列
        Thread current = Thread.currentThread();
        waiters.add(current);

        // 非队列首的线程进行阻塞
        Thread tmp = waiters.peek();
        while(tmp!=current || !locked.compareAndSet(false,true)) {
            LockSupport.park(current);
            // 获取中断,并清除中断
            if (Thread.interrupted()) {
                wasInterrupted = true;
            }

            // 重新回复中断状态
            if (wasInterrupted) {
                current.interrupt();
            }
        }
        waiters.remove();
    }

    public void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }

    public static void main(String[] args) {

        MyLockSupportLock lock = new MyLockSupportLock();

        Runnable runA =
            () -> {
              try {
                TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              lock.lock();
              System.out.println("线程"+Thread.currentThread().getName()+"获得锁");
              try {
                TimeUnit.SECONDS.sleep(5);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              lock.unlock();
                System.out.println("线程"+Thread.currentThread().getName()+"释放锁");
            };

        Thread thread1 = new Thread(runA,"thread1");
        Thread thread2 = new Thread(runA,"thread2");
        thread1.start();
        thread2.start();
    }
}
