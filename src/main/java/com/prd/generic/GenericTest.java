package com.prd.generic;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ruidong.pang
 * @version V1.0
 * @Description 泛型测试
 * @date 2020-06-11 19:38
 */
@Slf4j
public class GenericTest {

    public static void main(String[] args) {

        // 泛型类
        Pair<String> stringPair = new Pair<>("A","B");
        log.info("stringPair={}",stringPair);
        Pair<Integer> integerPair = new Pair<>(1,2);
        log.info("integerPair={}",integerPair);

        // 泛型方法
        stringPair.getAdd("C","D");
        integerPair.getAdd(1,2);


        // 类型变量限定，Demo类无实现Comparable
        // Pair<Demo> integerPair = new Pair<>(new Demo(),new Demo());


        // 运行时类型检查只适用于原始类型
        // Error
//        if (stringPair instanceof  Pair<String>) {
//            log.info("instanceof  Pair<String> true");
//        }
        // Error
//        if (stringPair instanceof  Pair<T>) {
//            log.info("instanceof  Pair<T> true");
//        }
        // Right
        if (stringPair instanceof  Pair) {
            log.info("instanceof  Pair true");
        }


        // 不能创建参数化类型的数组
        // Error
//        Pair<String>[] pairs = new Pair<String>[10];
        // Right
        Pair<String>[] pairs = new Pair[10];


        // 数组会记住类型，写入其他类型，会提示错误
        // Error
//        pairs[1] = integerPair;
        // Righ
        pairs[0] = stringPair;


        // 可变参数支持泛型
        stringPair.printMsg("A","B","C");

        // 泛型擦除后引起的冲突
        // 这里因为使用类型边界，不会和Object的equals冲突
        // 去掉extends Comparable后，编译报错
        Pair<String> stringPair1 = new Pair<>("A","B");
        log.info("stringPair equals {}",stringPair.equals(stringPair1));

    }
}
