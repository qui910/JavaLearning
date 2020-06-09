package com.prd.interfaces.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author ruidong.pang
 * @version V1.0
 * @Description Java类型实例方法引用测试
 *    隐式传递一个参数num
 * @date 2020-06-09 14:10
 */
public class ClassInstanceMethodReferenceTest {
    public static void main(String[] args) {
        List<People> list = new ArrayList<>();
        People p1 = new People(10);
        People p2 = new People(15);
        list.add(p1);
        list.add(p2);
        // 匿名内部类方式
/*        List<Double> allWeight = People.calculateAllWeight(list, new BiFunction<People,Double,Double>() {
            @Override
            // The object
            public Double apply(People people,Double num) {
                // The method
                return people.calculateWeight(num);
            }
        });*/

        // lambda表达式方式
//        List<Double> allWeight = People.calculateAllWeight(list, (people,num) -> people.calculateWeight(num));

        // 类型实例方法引用模式
        List<Double> allWeight = People.calculateAllWeight(list, People::calculateWeight);

        allWeight.stream().forEach(System.out::println);
    }
}

class People {

    private  double weight = 0;

    public People(double weight) {
        this.weight = weight;
    }

    public double calculateWeight(Double num) {
        return weight*num;
    }

    public static List<Double> calculateAllWeight(List<People> l, BiFunction<People,Double,Double> f) {
        List<Double> results = new ArrayList<>();
        for (People s : l) {
            results.add(f.apply(s,0.8));
        }
        return results;
    }

    @Override
    public String toString() {
        return "People{" +
                "weight=" + weight +
                '}';
    }
}