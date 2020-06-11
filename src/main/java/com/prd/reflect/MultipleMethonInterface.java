package com.prd.reflect;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ruidong.pang
 * @version V1.0
 * @Description 测试多方法
 * @date 2020-06-11 12:15
 */
@Slf4j
public class MultipleMethonInterface implements DemoBInterface {
    @Override
    public void doing1() {
        log.info("我先学习中。。。。。");
    }

    @Override
    public void doing2() {
        log.info("我后工作中。。。。。");
    }
}
