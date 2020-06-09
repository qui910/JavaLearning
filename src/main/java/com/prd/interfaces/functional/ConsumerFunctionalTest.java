package com.prd.interfaces.functional;

import java.util.function.Consumer;

/**
 * Java内置函数式接口Consumer测试
 */
public class ConsumerFunctionalTest {

    public static void main(String[] args) {
        // 这里->后面必须是一个无返回值的表达式（因accept无返回）
        testAccept(s->System.out.println("testAccept:"+s),"Hello");
        // testAccept(s->s+" World","Hello"); // 提示错误

        testAndTHen(s->System.out.println("testAndTHen:"+s+"1"),
                s->System.out.println("testAndTHen:"+s+"2"),
                "Hello");
    }

    /**
     * accept 抽象方法测试
     * @param function 函数接口
     * @param str 需要处理的字符串
     */
    public static void testAccept(Consumer<String> function,
                                  String str) {
        function.accept(str);
    }

    public static void testAndTHen(Consumer<String> function1,
                                   Consumer<String> function2,
                                   String str) {
        function1.andThen(function2)
                .andThen(s->System.out.println("testAndTHen:"+s+"3"))
                .andThen(s->System.out.println("testAndTHen:"+s+"4"))
                .accept(str);
    }
}
