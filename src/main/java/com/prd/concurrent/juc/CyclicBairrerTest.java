package com.prd.concurrent.juc;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;

public class CyclicBairrerTest {

    public static void main(String[] args) {
        testNormal();
    }

    /**
     * 演示Cyclier的常规用法
     */
    private static void testNormal() {

        CopyOnWriteArrayList<String> copy = new CopyOnWriteArrayList<>();

        CyclicBarrier barrier = new CyclicBarrier(3,
                () -> {
                    copy.forEach(str -> System.out.println("线程："+str+"到达回栏"));
                    copy.clear();
        });

        Runnable runa =
            () -> {
                while(true) {
                    try {
                        int num = new Random().nextInt(1500);
                        Thread.sleep(num);
                        copy.add(Thread.currentThread().getName()+"-"+num);
                        System.out.println(Thread.currentThread().getName()+"执行"+num);
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            };

        for (int i=0;i<3;i++) {
            new Thread(runa).start();
        }
    }
}
