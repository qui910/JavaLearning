package com.prd.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author prd
 * @version V1.0
 * @Description TODO:功能描述
 * @date 2020-06-13 10:26
 */
@Slf4j
public class AtomicLongTest {

    private static AtomicLong atomicLong = new AtomicLong();

    public static void main(String[] args) throws InterruptedException {

        // 测试Atomic类的CAS
        new Thread(()-> {
            for (int i=0;i<10000;i++) {
                atomicLong.compareAndSet(atomicLong.get(),atomicLong.get()+2);
            }
        }).start();
        new Thread(()-> {
            for (int i=0;i<10000;i++) {
                atomicLong.compareAndSet(atomicLong.get(),atomicLong.get()+2);
            }
        }).start();
        Thread.sleep(100000);
        log.info("atomicLong result is {}",atomicLong.get());
    }
}
