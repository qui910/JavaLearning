package com.prd.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程安全测试
 */
public class ThreadSafeTest {

    public static int index=0;
    /**
     * 真实的统计数据
     */
    static AtomicInteger readIndexAddNum = new AtomicInteger();
    /**
     * 错误次数
     */
    static AtomicInteger errorIndexAddNum = new AtomicInteger();
    final boolean[] marked = new boolean[30000];

    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    class MyRunnable implements Runnable{
        @Override
        public void run() {
              // 这里不能使用while来做演示，因为while是不控制循环次数的
              // 只要没达到10000就可以进入循环。这样是没法判定线程安全问题的
//            while(index<10000){
//                ThreadSafeTest.index++;
//            }

            // 改用for来演示线程安全问题
            for (int i=0;i<10000;i++) {
                index++;
            }
        }
    }

    /**
     * 测试运行结果出错
     * 演示计数不准确（减少），找出具体出错的位置
     * 结果：每次结果不一样，或许可能达到20000，或许不可能
     */
    private void testAddNum() throws InterruptedException {
        Thread thread1 = new Thread(new ThreadSafeTest().new MyRunnable());
        Thread thread2 = new Thread(new ThreadSafeTest().new MyRunnable());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("最后结果："+ThreadSafeTest.index);
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadSafeTest test = new ThreadSafeTest();
//        test.testAddNum();
        test.testAddNum1();
    }


    /**
     * 记录发生异常的错误问题位置
     * 程序有问题！！！
     */
    class MyRunnable1 implements Runnable{
        @Override
        public void run() {
            marked[0] = true;
            // 改用for来演示线程安全问题
            for (int i=0;i<10000;i++) {
                try {
                    cyclicBarrier2.reset();
                    cyclicBarrier1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                index++;
                try {
                    cyclicBarrier1.reset();
                    cyclicBarrier2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                readIndexAddNum.incrementAndGet();
                synchronized (this) {
                    if (marked[index] && marked[index-1]) {
                        System.out.println("发送错误" + index);
                        errorIndexAddNum.incrementAndGet();
                    }
                    marked[index] = true;
                }
            }

        }
    }

    /**
     * 测试运行结果出错
     * 升级testAddNum，在此方法中可以记录
     * 正确++的数量和错误++的数量，并输出发生错误的位置
     */
    private void testAddNum1() throws InterruptedException {
        Thread thread1 = new Thread(new ThreadSafeTest().new MyRunnable1());
        Thread thread2 = new Thread(new ThreadSafeTest().new MyRunnable1());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("最后结果："+ThreadSafeTest.index);
        System.out.println("真正运行的次数："+ThreadSafeTest.readIndexAddNum.get());
        System.out.println("错误运行次数："+ThreadSafeTest.errorIndexAddNum.get());
    }
}
