package com.prd.colletctions;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author ruidong.pang
 * @version V1.0
 * @Description TODO:功能描述
 * @date 2020-06-15 10:45
 */
@Slf4j
public class TreeSetTest {

    TreeSet<Integer> integers = new TreeSet<>();

    public static void main(String[] args) {

        // 新建TreeSet
//        TreeSet tSet = new TreeSet();
        TreeSet tSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String s1 = (String)o1;
                String s2 = (String)o2;
                if (s1.length()>s2.length()) {
                    return 1;
                } else if (s1.length()<s2.length()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        // 将元素添加到TreeSet中
        tSet.add("aaa");
        tSet.add("a");
        tSet.add("bb");
        tSet.add("ddd");
        tSet.add("cccc");
        tSet.add("eeeee");
        log.info("TreeSet is {},size is {}",tSet,tSet.size());


        // 导航方法
        // floor(小于、等于)
        log.info("floor bb: {}", tSet.floor("bb"));
        // lower(小于)
        log.info("lower bb: {}", tSet.lower("bb"));
        // ceiling(大于、等于)
        log.info("ceiling bb: {}", tSet.ceiling("bb"));
        log.info("ceiling eeeee: {}", tSet.ceiling("eeeee"));


    }
}
