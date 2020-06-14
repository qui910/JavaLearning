package com.prd.colletctions;

import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

@Slf4j
public class VectorTest {

    private static Vector<Integer> vector=new Vector();

    public static void main(String[] args) {

        // 多线程情况下使用Vector 出现线程不安全的情况。
        // removeThread 中同时使用了size() 和 remove(i) 两个同步方式，导致操作不是原子的。
        // printThread 类似。如果要解决不安全情况，需要在for 循环外加把锁
        while(true){
            for(int i=0;i<10;i++){
                vector.add(i); //往vector中添加元素
            }
            Thread removeThread=new Thread(()->{
                //获取vector的大小
                for(int i=0;i<vector.size();i++){
                    //当前线程让出CPU,使例子中的错误更快出现
                    Thread.yield();
                    //移除第i个数据
                    vector.remove(i);
                }
            });
            Thread printThread=new Thread(()->{
                //获取vector的大小
                for(int i=0;i<vector.size();i++){
                    //当前线程让出CPU,使例子中的错误更快出现
                    Thread.yield();
                    //获取第i个数据并打印
                    System.out.println(vector.get(i));
                }
            });
            removeThread.start();
            printThread.start();
            //避免同时产生过多线程
            while(Thread.activeCount()>20);
        }


    }
}
