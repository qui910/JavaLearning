package com.prd.reflect;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ruidong.pang
 * @version V1.0
 * @Description 学习实现类
 * @date 2020-06-11 11:42
 */
@Slf4j
public class StudyService implements DemoInterface {
    @Override
    public void exec() {
        log.info("我正在学习中。。。。");
    }
}
