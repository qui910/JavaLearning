package com.prd.interfaces.comparable;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 对象排序测试
 */
@Slf4j
public class ComparableTest {
    public static void main(String[] args) {
        // 数组不能定义为泛型
        ComparableBean[] cbeans = new ComparableBean[3];
        cbeans[0] = new ComparableBean("a");
        cbeans[1] = new ComparableBean("ab");
        cbeans[2] = new ComparableBean("abc");
        Arrays.sort(cbeans);

        for (ComparableBean bean:cbeans) {
            log.info(bean.getUserName());
        }
    }
}
