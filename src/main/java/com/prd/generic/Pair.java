package com.prd.generic;

import lombok.extern.slf4j.Slf4j;

/**
 * @author prd
 * @version V1.0
 * @Description 泛型类
 * @date 2020-06-11 19:36
 */
@Slf4j
public class Pair<T extends Comparable> {

      // 静态成员和方法 无法泛型
//    private static T staticThird;
//    public static T getThirdValue() {
//    }

    private T first;

    private T second;

    public Pair() {
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public <T> void getAdd(T t1,T t2) {
        log.info("t1 is {},t2 is {}",t1,t2);
    }

    public <T> void printMsg(T...tarr) {
        for (T t:tarr) {
            log.info("T is {}",t);
        }
    }

    public boolean equals(T value) {
        return first.equals(value) && second.equals(value);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
