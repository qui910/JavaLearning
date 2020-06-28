package com.prd.colletctions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 同步容器测试
 */
public class CollectionsTest {
    public static void main(String[] args) {
        List<String> arrayList = Collections.synchronizedList(new ArrayList<String>());
        while(true){
            for(int i=0;i<10;i++){
                arrayList.add(i+"");
            }
            Thread removeThread=new Thread(()->{
                //获取vector的大小
                for(int i=0;i<arrayList.size();i++){
                    //当前线程让出CPU,使例子中的错误更快出现
                    Thread.yield();
                    //移除第i个数据
                    arrayList.remove(i);
                }
            });
            Thread printThread=new Thread(()->{
                //获取vector的大小
                for(int i=0;i<arrayList.size();i++){
                    //当前线程让出CPU,使例子中的错误更快出现
                    Thread.yield();
                    //获取第i个数据并打印
                    System.out.println(arrayList.get(i));
                }
            });
            removeThread.start();
            printThread.start();
            //避免同时产生过多线程
            while(Thread.activeCount()>20);
        }
    }
}
