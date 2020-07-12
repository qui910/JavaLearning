package com.prd.concurrent;

import java.util.HashMap;
import java.util.Map;

/**
 * 对象发布逸出测试
 * 构造函数中运行线程
 * 结果：
 * Exception in thread "main" java.lang.NullPointerException
 * 分析：就是在构造器中子线程是否完成初始化工作，无法控制
 * 所以不能再构造器中新开线程。
 * 生产环境中：就是在构造器中无意中进行获取数据库连接池等操作
 * 如果过早获取对象，连接池可能未初始好，会导致空点异常。
 */
public class ThreadSafeTest3 {

    public Map<String,String> states;

    public ThreadSafeTest3() {
        new Thread(()->{
            states = new HashMap<>();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            states.put("a","1");
        }).start();
    }

    public static void main(String[] args) {
        ThreadSafeTest3 test = new ThreadSafeTest3();
        test.states.get("a");
    }
}
