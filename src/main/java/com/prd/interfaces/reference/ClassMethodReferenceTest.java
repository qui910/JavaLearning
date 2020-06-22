package com.prd.interfaces.reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * @author prd
 * @version V1.0
 * @Description  Java静态方法引用测试
 * @date 2020-06-09 13:00
 */
public class ClassMethodReferenceTest {

    //将值判断提取成静态方法
    public static boolean isMoreThanFifty(int n1, int n2) {
        return (n1 + n2) > 50;
    }
    //公共接口方法
    public static List<Integer> findNumbers(List<Integer> l, BiPredicate<Integer, Integer> p) {
        List<Integer> newList = new ArrayList<>();
        for (Integer i : l) {
            if (p.test(i, i + 10)) {
                newList.add(i);
            }
        }
        return newList;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(12, 5, 45, 18, 33, 24, 40);

        // 匿名内部类方式
/*        List<Integer> res = ClassMethodReferenceTest.findNumbers(list, new BiPredicate<Integer, Integer>() {
            @Override
            public boolean test(Integer integer, Integer integer2) {
                return ClassMethodReferenceTest.isMoreThanFifty(integer, integer2);
            }
        });*/

        // lambda语法方式
/*        List<Integer> res = ClassMethodReferenceTest.findNumbers(list,
                (l1,l2) -> ClassMethodReferenceTest.isMoreThanFifty(l1, l2));*/

        // 静态方法引用
        List<Integer> res = ClassMethodReferenceTest.findNumbers(list,ClassMethodReferenceTest::isMoreThanFifty);
        res.stream().forEach(System.out::println);
    }
}
