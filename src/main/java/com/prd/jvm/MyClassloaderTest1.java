package com.prd.jvm;

/**
 * 测试类初始化过程测试
 */
public class MyClassloaderTest1 {

  public static void main(String[] args) {

    // （1）只调用子类或父类常量不会导致其他的初始化
    // 只打印5，说明常量时存储在类的常量池中的。
    //     System.out.println(MyChild.j1);


    // （2）调用子类的final静态变量，会导致父类与子类静态代码块初始化
    // 父类静态变量初始化
    // 父类静态块初始化
    // 子类final静态变量初始化
    // 子类静态变量初始化
    // 子类静态块初始化
    // 2
//    System.out.println(MyChild.j2);

    // （3）调用子类的静态变量，与（2）类似
      System.out.println(MyChild.k1);



  }
}

class MyParent{
    public int i = geti();

    public static final int j = 5;

    public static int k = getk();

    static {
        System.out.println("父类静态块初始化");
    }

    {
        System.out.println("父类实例块初始化");
    }


    public int geti(){
        System.out.println("父类实例变量初始化");
        return 0;
    }

    public static int getk(){
        System.out.println("父类静态变量初始化");
        return 0;
    }

    public MyParent() {
        System.out.println("父类构造器初始化");
    }
}

class MyChild extends MyParent {
    public int i1 = geti1();

    public static final int j1 = 5;

    public static final int j2 = getj2();

    public static int k1 = getk1();

    static {
        System.out.println("子类静态块初始化");
    }

    {
        System.out.println("子类实例块初始化");
    }


    public int geti1(){
        System.out.println("子类实例变量初始化");
        return 1;
    }

    public static int getj2() {
        System.out.println("子类final静态变量初始化");
        return 2;
    }

    public static int getk1(){
        System.out.println("子类静态变量初始化");
        return 3;
    }

    public MyChild() {
        System.out.println("子类构造器初始化");
    }
}