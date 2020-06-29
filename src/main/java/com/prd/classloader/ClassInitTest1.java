package com.prd.classloader;

import lombok.extern.slf4j.Slf4j;

/**
 * 类加载与初始化测试
 */
@Slf4j
public class ClassInitTest1 {
  public static void main(String[] args) {

      // 分析：类在准备阶段将num1，num2,num3都赋予默认值0，在初始化阶段将num2的初始值改为1
      // 然后进行自增（自增在左，还是右都没有关系），所以要打印结果为:
      // num1:1  num2:2  num3:1
      Singleton singleton = Singleton.newInstance();
      log.info("num1:{}",Singleton.num1);
      log.info("num2:{}",Singleton.num2);
      log.info("num3:{}",Singleton.num3);

      // 分析：类在准备阶段将num1，num2,num3都赋予默认值0，初始化阶段的顺序为：
      // (1) num2赋值为1  (2) 调用构造器自增(num1,num2,num3) (3) num3赋值为0
      // 原因：在初始化阶段时按照代码顺序进行的初始化
      // num1:1  num2:2  num3:0
      SingletonA singletona = SingletonA.newInstance();
      log.info("num1:{}",SingletonA.num1);
      log.info("num2:{}",SingletonA.num2);
      log.info("num3:{}",SingletonA.num3);

      // 分析：类在准备阶段将num1，num2,num3都赋予默认值0，初始化阶段的顺序为：
      // (1) num2赋值为1  (2) num3赋值为0 (3) 调用构造器自增(num1,num2,num3)
      // 原因： 这里和SingletonA的区别就是创建类的实例时通过构造器进行的。
      // 这里在调用构造器时，num3的赋值在之前就已经完成了
      // num1:1  num2:2  num3:1
      SingletonB singletonb = new SingletonB();
      log.info("num1:{}",SingletonB.num1);
      log.info("num2:{}",SingletonB.num2);
      log.info("num3:{}",SingletonB.num3);
  }
}

class Singleton {
    public static int num1;
    public static int num2=1;
    public static int num3;

    private static Singleton singleton = new Singleton();

    private Singleton() {
        num1++;
        num2++;
        ++num3;
    }

    public static Singleton newInstance() {
        return singleton;
    }
}

class SingletonA {
    public static int num1;
    public static int num2=1;

    private static SingletonA singleton = new SingletonA();

    private SingletonA() {
        num1++;
        num2++;
        ++num3;
    }

    public static int num3=0;

    public static SingletonA newInstance() {
        return singleton;
    }
}

class SingletonB {
    public static int num1;
    public static int num2=1;

    public  SingletonB() {
        num1++;
        num2++;
        ++num3;
    }

    public static int num3=0;
}