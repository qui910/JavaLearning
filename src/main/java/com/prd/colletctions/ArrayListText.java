package com.prd.colletctions;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author ruidong.pang
 * @version V1.0
 * @Description 数组列表测试
 * @date 2020-06-12 17:13
 */
@Slf4j
public class ArrayListText {


    public static void main(String[] args) throws InterruptedException {

        ArrayList<Integer> stringArrayList = new ArrayList<>();
        stringArrayList.add(1);
        stringArrayList.add(2);
        stringArrayList.add(3);

        // forEach操作列表时，操作业务只能是不相干的。
        // ERROR   totalNum 必须时final
//        int totalNum=0;
//        stringArrayList.forEach(s -> {totalNum=totalNum+s});

        // 获得数组类型
        String[] strings = new String[10];
        log.info("String[].class={},{}",strings.getClass(),String[].class);
        Object[] objs = new String[10];
        log.info("String[].class={},{}",objs.getClass(),Object[].class);

        // fast-fast事件
        // 当使用迭代器循环时，另外的线程删除List中的数据，则会导致ConcurrentModificationException异常
/*        ArrayList<Integer> integers = new ArrayList<>();
        for (int i=0;i<20;i++) {
            integers.add(i);
        }
        new Thread(()->{
            try {
                Thread.sleep(1000);
                integers.remove(2);
            } catch (InterruptedException e) {
                log.error(e.getMessage(),e);
            }
        }).start();
        new Thread(()->{
            for (int j:integers) {
                log.info("j={}",j);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(),e);
                }
            }
        }).start();*/

        // LinkedList也会导致ConcurrentModificationException异常
/*        LinkedList<Integer> integers = new LinkedList<>();
        for (int i=0;i<20;i++) {
            integers.add(i);
        }
        new Thread(()->{
            try {
                Thread.sleep(1000);
                integers.remove(2);
            } catch (InterruptedException e) {
                log.error(e.getMessage(),e);
            }
        }).start();
        new Thread(()->{
            for (int j:integers) {
                log.info("j={}",j);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(),e);
                }
            }
        }).start();*/


        // 同样一个线程新增列表，另外的使用迭代器循环，也会提示ConcurrentModificationException异常
/*        ArrayList<Integer> integers = new ArrayList<>();
        new Thread(()->{
            for (int i=0;i<20;i++) {
                integers.add(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(),e);
                }
            }
        }).start();
        Thread.sleep(3000);
        new Thread(()->{
            for (int j:integers) {
                log.info("j={}",j);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(),e);
                }
            }
        }).start();*/


        // 10000个并发同时写ArrayList，导致部分数据丢失。实际写入数量不足10000
        // 而且会出现部分数据为空的情况
        ArrayList<String> integers = new ArrayList<>();
        for (int i=0;i<10000;i++) {
            new Thread(()->{
                integers.add(Thread.currentThread().getName());
            },i+"").start();
        }
        Thread.sleep(10000);
        log.info("integers size={}",integers.size());
        for (int i=0;i<integers.size();i++) {
            String s = integers.get(i);
            if (null==s||"".equals(s)) {
                log.info("integers index i={}, s={}",i,s);
            }
        }

    }
}
