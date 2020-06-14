package com.prd.colletctions;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 链表列表测试
 */
@Slf4j
public class LinkedListTest {

    public static void main(String[] args) throws InterruptedException {

        List<String> staff = new LinkedList<>();
        staff.add("A");
        staff.add("B");
        staff.add("C");
        staff.add("D");

        // fast-fast异常 java.util.ConcurrentModificationException
/*        new Thread(()->{
            ListIterator<String> listIterator1 = staff.listIterator();
            listIterator1.next();
            listIterator1.remove();
        }).start();

        new Thread(()->{
            ListIterator<String> listIterator2 = staff.listIterator();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            listIterator2.next();
        }).start();*/

        // set操作必须紧跟next或previous操作，add或remove后lastReturned被置为null
        // 此时再set，则会提示错误IllegalStateException
/*        ListIterator<String> lit3 = staff.listIterator();
        log.info(lit3.next());
        lit3.add("E");
        lit3.set("F");*/

        // 10000个并发同时写ArrayList，导致部分数据丢失。实际写入数量不足10000
        // 而且会出现部分数据为空的情况
/*        LinkedList<String> integers = new LinkedList<>();
        for (int i=0;i<10000;i++) {
            new Thread(()->{
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
        }*/


        // 多种循环测试
        LinkedList<Integer> intlist = new LinkedList<>();
        for (int i=0;i<10000000;i++) {
            intlist.add(i);
        }
        iteratorThroughList(intlist);
        ///ranDomThroughList(intlist); //时间太久，无法循环出
        foreachThroughList(intlist);
        streamThroughList(intlist);

    }

    public static void iteratorThroughList(List list) {
        long startTime = System.currentTimeMillis();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            iter.next();
        }
        long endTime = System.currentTimeMillis();
        log.info("第1种迭代器访问,耗时{}",endTime-startTime);
    }

    public static void ranDomThroughList(List list) {
        long startTime = System.currentTimeMillis();
        for (int i=0;i<list.size();i++) {
            list.get(i);
        }
        long endTime = System.currentTimeMillis();
        log.info("第2种随机访问,耗时{}",endTime-startTime);
    }

    public static void foreachThroughList(List list) {
        long startTime = System.currentTimeMillis();
        for (Object obj:list);
        long endTime = System.currentTimeMillis();
        log.info("第3种for循环遍历访问,耗时{}",endTime-startTime);
    }

    public static void streamThroughList(List list) {
        long startTime = System.currentTimeMillis();
        list.forEach(l -> {});
        long endTime = System.currentTimeMillis();
        log.info("第8种forEach流式访问,耗时{}",endTime-startTime);
    }
}
