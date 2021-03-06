package com.prd.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport测试
 */
public class LockSupportTest {

    public static void main(String[] args) {
//        testNormalUsed();
        testNormalInterrupte();
//        testUnBeforPark();

//        testParkJStack();
    }

    /**
     * 测试常规用法
     * 线程test1 调用park时，因无许可证，被阻塞为WAITING
     * 阻塞后必须由其他线程调用unpark才能解除阻塞
     *
     */
    private static void testNormalUsed() {
        Thread test1 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("park前 线程状态:"+Thread.currentThread().getState().name());
            LockSupport.park();
            System.out.println("park后 线程状态:"+Thread.currentThread().getState().name());
        });
        test1.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始unpark前，park中线程状态："+test1.getState().name());
        LockSupport.unpark(test1);
        System.out.println("结束unpark");
    }

    /**
     * 测试线程test2被park阻塞后，可以被中断
     * 且中断后 run方法执行完成。
     * 并测试park时，使用循环条件判断
     */
    private  static void testNormalInterrupte() {
        Thread test2 = new Thread(()->{
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("park前，线程中断状态："+Thread.currentThread().isInterrupted());
                LockSupport.park();
                System.out.println("park后，线程中断状态："+Thread.currentThread().isInterrupted());
            }
            System.out.println("dosome things");
        });
        test2.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 因为使用while循环判断，这里unpark后，线程马上又重新进入park阻塞状态，只有中断才能最终真正的唤醒线程
        System.out.println("开始unpark前，park中线程中断状态："+test2.isInterrupted());
        LockSupport.unpark(test2);
        System.out.println("结束unpark，线程的状态："+test2.getState().name());

        System.out.println("中断前，park中线程中断状态："+test2.isInterrupted());
        test2.interrupt();
        System.out.println("结束中断");
    }

    /**
     * 线程3 先调用unpark获取许可证，park时不会阻塞直接跳过
     */
    private static void testUnBeforPark(){
        Thread test3 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(Thread.currentThread());
            System.out.println("park前 线程状态:"+Thread.currentThread().getState().name());
            LockSupport.park();
            System.out.println("park后 线程状态:"+Thread.currentThread().getState().name());
        });
        test3.start();
    }

    /**
     * 使用park(thread)可以在jstack等工具中显示阻塞的对象
     * 结果：该例实现错误？？未显示哪个对象？？
     */
    private static void testParkJStack() {
        new Thread(()->{
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("park this");
                LockSupport.park(Thread.currentThread());
            }
        }).start();

        new Thread(()->{
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("park");
                LockSupport.park();
            }
        }).start();
    }


}
