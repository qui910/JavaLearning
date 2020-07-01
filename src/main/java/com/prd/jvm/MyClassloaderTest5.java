package com.prd.jvm;

/**
 * 测试类初始化时，类实现的接口不会初始化
 */
public class MyClassloaderTest5 {
  public static void main(String[] args) {
    System.out.println(MyChild5.NUM);
  }
}

interface MyParent5 {

}

class MyChild5 implements MyParent5 {
    public static final int NUM=5;

    {
        System.out.println("实例块");
    }

    public MyChild5() {
        System.out.println("实例构造器");
    }
}