package com.prd.colletctions;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.Iterator;
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
        // 根据Comparator 算法不同，ddd的大小也是3，所以无法写入
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

        // ceiling(大于)
        log.info("higher bbb: {}", tSet.higher("bb"));
        // subSet()
        log.info("subSet(bb, true, aaa, true): %s\n", tSet.subSet("bb", true, "aaa", true));
        log.info("subSet(bb, true, aaa, false): %s\n", tSet.subSet("bb", true, "aaa", false));
        log.info("subSet(bb, false, aaa, true): %s\n", tSet.subSet("bb", false, "aaa", true));
        log.info("subSet(bb, false, aaa, false): %s\n", tSet.subSet("bb", false, "aaa", false));
        // headSet()
        log.info("headSet(aaa, true):{}", tSet.headSet("aaa", true));
        log.info("headSet(aaa, false): {}", tSet.headSet("aaa", false));
        // tailSet()
        System.out.printf("tailSet(ccc, true): %s\n", tSet.tailSet("ccc", true));
        System.out.printf("tailSet(ccc, false): %s\n", tSet.tailSet("ccc", false));


        // 删除“ccc”
        tSet.remove("ccc");
        // 将Set转换为数组
        String[] arr = (String[])tSet.toArray(new String[0]);
        for (String str:arr)
            System.out.printf("for each : %s\n", str);

        // 打印TreeSet
        System.out.printf("TreeSet:%s\n", tSet);

        // 遍历TreeSet
        for(Iterator iter = tSet.iterator(); iter.hasNext(); ) {
            System.out.printf("iter : %s\n", iter.next());
        }

        // 删除并返回第一个元素
        val = (String)tSet.pollFirst();
        System.out.printf("pollFirst=%s, set=%s\n", val, tSet);

        // 删除并返回最后一个元素
        val = (String)tSet.pollLast();
        System.out.printf("pollLast=%s, set=%s\n", val, tSet);

        // 清空HashSet
        tSet.clear();

        // 输出HashSet是否为空
        System.out.printf("%s\n", tSet.isEmpty()?"set is empty":"set is not empty");
    }
}
