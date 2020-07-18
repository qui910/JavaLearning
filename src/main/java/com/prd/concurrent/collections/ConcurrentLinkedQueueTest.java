package com.prd.concurrent.collections;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueTest {
    /**
     * 使用队列中的内部锁实现生产者消费者
     * 同时测试ConcurrentLinkedQueue的多读，多写时是否会发生bug
     */
    private static void testProduceAndCustom() {

        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

        Thread producer = new Thread();
    }
}
