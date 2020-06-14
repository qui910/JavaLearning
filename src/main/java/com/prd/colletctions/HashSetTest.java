package com.prd.colletctions;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class HashSetTest {

    private static Set<Integer> set1 = new HashSet<>();

    public static void main(String[] args) throws InterruptedException {

        // 多线程同时写入,会导致数据不一致
/*        new Thread(()->{
            try {
                for (int i=0;i<100;i++) {
                    Thread.sleep(100);
                    set1.add(i);
                }
            } catch (InterruptedException e) {
                log.error(e.getMessage(),e);
            }
            log.info("thread1 down");
        }).start();
        new Thread(()->{
            for (int i=0;i<100;i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                set1.add(i);
            }
            log.info("thread2 down");
        }).start();
        Thread.sleep(20000);
        log.info("HashSet 最后大小为:{}",set1.size());*/


    }
}
