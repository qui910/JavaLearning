package com.prd.concurrent.arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ruidong.pang
 * @version V1.0
 * @Description ArrayList在并发下问题分析
 * @date 2020-06-06 14:35
 */
public class ArrayListTest {
    private static List<String> threadList = new ArrayList<String>();

    public static void main(String[] args) throws InterruptedException {
        testWriteArrayListError();
    }

    private static void testWriteArrayListError() throws InterruptedException {

        for (int i=0;i<10000;i++) {
            new Thread(()->{
                    threadList.add(Thread.currentThread().getName());
            },i+"").start();
        }

        Thread.sleep(10000);

        System.out.println("-------------最后大小："+threadList.size());
//        for (String s:threadList) {
//            System.out.println("real:"+s);
//        }
    }
}
