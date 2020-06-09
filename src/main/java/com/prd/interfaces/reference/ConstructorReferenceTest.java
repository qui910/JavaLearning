package com.prd.interfaces.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Java构造方法引用测试
 */
public class ConstructorReferenceTest {

    /**
     * 无参
     */
    public void noParams() {
        // 匿名内部类模式
/*        Supplier<List<String>> s = new Supplier() {
            public List<String> get() {
                return new ArrayList<String>();
            }
        };*/

        // lambda表达式模式
//        Supplier<List<String>> s = () -> new ArrayList<String>();

        // 构造器引用模式
        Supplier<List<String>> s = ArrayList::new;
        List<String> l = s.get();
    }

    /**
     * 有参
     */
    public void params() {
        // 匿名内部类模式
/*        BiFunction<String, String, Locale> f = new BiFunction<String, String, Locale>() {
            public Locale apply(String lang, String country) {
                return new Locale(lang, country);
            }
        };*/

        // lambda表达式模式
//        BiFunction<String, String, Locale> f = (lang, country) -> new Locale(lang, country);

        // 构造器引用模式
        BiFunction<String, String, Locale> f = Locale::new;
        Locale loc = f.apply("en","UK");
    }
}
