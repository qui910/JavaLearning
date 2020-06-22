package com.prd.reflect;

import lombok.extern.slf4j.Slf4j;

/**
 * @author prd
 * @version V1.0
 * @Description 工作实现类
 * @date 2020-06-11 11:43
 */
@Slf4j
public class WorkService implements DemoAInterface{
    @Override
    public void doing() {
        log.info("我正在工作中。。。。。");
    }
}
