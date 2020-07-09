package com.prd.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA解决问题测试
 */
public class AtomicStampReferenceTest {

    private static AtomicInteger atomicInt = new AtomicInteger(100);
    private static AtomicStampedReference atomicStampedRef = new AtomicStampedReference(100, 0);

    public static void main(String[] args) {

        testABACASByNormal();

        testABACAS();
    }

    /**
     * 测试使用AtomicInteger时ABA问题发生后是否CAS成功
     * 结果：可以更新成功，不会处理ABA问题
     */
    private static void testABACASByNormal(){
        Thread intT1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInt.compareAndSet(100, 101);
                atomicInt.compareAndSet(101, 100);
            }
        });

        Thread intT2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                boolean c3 = atomicInt.compareAndSet(100, 101);
                System.out.println("使用AtomicInteger后ABA是否更新："+c3);
            }
        });

        intT1.start();
        intT2.start();
        try {
            intT1.join();
            intT2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     * 测试ABA后是否可以更新CAS
     * 结果：不可更新成功，ABA后 Pair对象发生了变化，不能更新
     */
    private static void testABACAS() {
        Thread refT1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
                atomicStampedRef.compareAndSet(101, 100, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            }
        });

        Thread refT2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedRef.getStamp();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                }
                boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
                System.out.println("使用AtomicStampedReference后ABA是否更新："+c3);
            }
        });

        refT1.start();
        refT2.start();
    }
}
