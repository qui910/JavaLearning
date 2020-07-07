package com.prd.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author prd
 * @version V1.0
 * @Description 测试原子操作
 * @date 2020-06-13 10:26
 */
@Slf4j
public class AtomicLongTest {

    private static AtomicLong atomicLong = new AtomicLong();

    public static void main(String[] args) throws InterruptedException {

//        bothCas();

        bothSet();
    }

    /**
     * 线程A和线程B 同时CAS一个原子类
     * 结果：4000
     * 正确
     */
    private static void bothCas() throws InterruptedException {
        // 测试Atomic类的CAS
        new Thread(()-> {
            for (int i=0;i<10;i++) {
                long k = atomicLong.get();
                atomicLong.compareAndSet(k,k+1);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()-> {
            for (int i=0;i<10;i++) {
                long k = atomicLong.get();
                atomicLong.compareAndSet(k,k+1);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(5000);
        log.info("atomicLong result is {}",atomicLong.get());
    }

    /**
     * 线程A和线程B 同时直接set值
     * long k = atomicLong.get();
     * atomicLong.set(k+1);
     * 结果：100000  正常，这里通过volatile实现原子操作
     *
     * 当修改为 long k = atomicLong.get()+1;
     * atomicLong.set(k);
     * 结果： 99113 错误，此时 K = atomicLong.get()+1; 不能保证是原子的，所以异常
     * @throws InterruptedException
     */
    private static void bothSet() throws InterruptedException {
        // 测试Atomic类的SET
        for (int j=0;j<100;j++) {
            new Thread(()-> {
                for (int i=0;i<1000;i++) {
                    long k = atomicLong.get()+1;
                    atomicLong.set(k);
                }
            }).start();
        }
        Thread.sleep(5000);
        log.info("atomicLong result is {}",atomicLong.get());
    }


}
