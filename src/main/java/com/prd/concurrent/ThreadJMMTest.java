package com.prd.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 演示重排序（OutOfOrderExecution）的现象
 * 因为重排序不是每次都发生，需要直到某个条件才停止，测试小概率事件
 */
public class ThreadJMMTest {
    private static int x=0,y=0;
    private static int a=0,b=0;

    public static void main(String[] args) throws InterruptedException {

        int i = 0;

        // 这里使用循环就是测试小概率事件
        for(;;) {
            i++;
            x=0;
            y=0;
            a=0;
            b=0;

            // 如果要演示第3种情况，需要2个线程同时开始，这里就使用CountDownLatch
            CountDownLatch latch = new CountDownLatch(1);

            Thread one = new Thread(()->{
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a=1;
                x=b;
            });
            Thread two = new Thread(()->{
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b=1;
                y=a;
            });
            one.start();
            two.start();
            latch.countDown();
            one.join();
            two.join();

            String result = "第"+i+"次(x="+x+",y="+y+")";
            if (x==0 && y==0) {
                System.out.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }
}
