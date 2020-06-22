package com.prd.reflect;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * @author prd
 * @version V1.0
 * @Description 反射类使用测试
 * @date 2020-06-10 12:05
 */
@Slf4j
public class ReflectClassTest {
    public static void main(String[] args) {
        String className = "";
        if (args.length>0) {
            className = args[0];
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter class name (eg:java.util.Date):");
            className = scanner.next();
        }

        try {
            // 获得类对象
            Class cl = Class.forName(className);
            log.info("类或接口为:{},简写:{}",cl.getName(),cl.getSimpleName());
            // 获得父类的类对象
            Class suppercl = cl.getSuperclass();
            log.info("类或接口的父类或接口为:{},简写:{}",suppercl.getName(),suppercl.getSimpleName());
            // 返回类或接口的修饰符
            String modifiers = Modifier.toString(cl.getModifiers());
            log.info("类或接口的修饰符为:{}",modifiers);

            // 构造器信息
            printConstructors(cl);

            // 方法信息
            printMethods(cl);

            // 字段信息
            printFields(cl);

        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(),e);
        }
    }

    public static void printConstructors(Class cl) {
          Constructor[] constructors = cl.getDeclaredConstructors();
          for (Constructor ct:constructors) {
              // 构造器名称
              String constructorName = ct.getName();
              // 修饰符
              String modifiers = Modifier.toString(ct.getModifiers());
              //　参数
              Class[] paramTypes = ct.getParameterTypes();

              StringBuffer constructorSB = new StringBuffer();
              constructorSB.append(modifiers).append(" ");
              constructorSB.append(constructorName).append("(");
              for (int j = 0; j < paramTypes.length; j++)
              {
                  constructorSB.append(paramTypes[j].getSimpleName());
                  if (j<paramTypes.length-1) {
                      constructorSB.append(",");
                  }
              }
              constructorSB.append(");");
              log.info("构造器为：{} ",constructorSB.toString());
          }
    }

    public static void printMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();
        for (Method method:methods) {
            method.setAccessible(true);
            // 返回类型
            Class returnType = method.getReturnType();
            // 方法名
            String methodName = method.getName();
            // 参数
            Class[] paramTypes = method.getParameterTypes();
            // 修饰符
            String modifiers = Modifier.toString(method.getModifiers());

            StringBuffer methodSB = new StringBuffer();
            methodSB.append(modifiers).append(" ").append(returnType).append(" ");
            methodSB.append(methodName).append("(");
            for (int j = 0; j < paramTypes.length; j++)
            {
                methodSB.append(paramTypes[j].getSimpleName());
                if (j<paramTypes.length-1) {
                    methodSB.append(",");
                }
            }
            methodSB.append(");");
            log.info("方法为：{} ",methodSB.toString());
        }
    }

    public static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();
        for (Field field:fields) {
            Class fieldType = field.getType();
            String filedName = field.getName();
            String modifiers = Modifier.toString(field.getModifiers());
            StringBuffer fieldSB = new StringBuffer();
            fieldSB.append(modifiers).append(" ").append(fieldType).append(" ");
            fieldSB.append(filedName).append(";");
            log.info("字段为：{} ",fieldSB.toString());
        }
    }
}
