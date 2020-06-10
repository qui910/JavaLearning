package com.prd.reflect;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;

/**
 * 反射数组测试
 */
@Slf4j
public class ReflectArrayTest {

    public static void main(String[] args) {
        String[] st = {"a","b","c"};
        log.info("st ComponentType={}",st.getClass().getComponentType());

        //提示错误
        /*
        Exception in thread "main" java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.String;
	at com.prd.reflect.ReflectArrayTest.main(ReflectArrayTest.java:13)
         */
//        String[] stcopy = (String[]) badCopyof(st,10);
//        log.info("error stcopy.length=",stcopy.length);

        // 强制转为String报错，但是不转时原始类型就是Object,因为newArray 是通过new Object[newlength]创建的。
        Object[] stcopy = badCopyof(st,10);
        log.info("Object stcopy ComponentType={}",stcopy.getClass().getComponentType());

        Object realcopy = goodCopyof(st,10);
        log.info("Object realcopy ComponentType={}",realcopy.getClass().getComponentType());

        String[] realcopyArr = (String[]) goodCopyof(st,10);
        log.info("Object realcopyArr length={}",realcopyArr.length);
    }

    public static Object[] badCopyof(Object[] a,int newlength) {
        Object[] newArray = new Object[newlength];
        System.arraycopy(a,0,newArray,0,Math.min(a.length,newlength));
        return newArray;
    }

    public static Object goodCopyof(Object[] a,int newlength) {
        Class componentType = a.getClass().getComponentType();
        Object newArray = Array.newInstance(componentType,newlength);
        System.arraycopy(a,0,newArray,0,Math.min(a.length,newlength));
        return newArray;
    }
}
