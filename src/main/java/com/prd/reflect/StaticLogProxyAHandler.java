package com.prd.reflect;

import lombok.extern.slf4j.Slf4j;

/**
 * @author prd
 * @version V1.0
 * @Description 静态代理类
 * @date 2020-06-11 12:08
 */
@Slf4j
public class StaticLogProxyAHandler implements DemoAInterface {

    private DemoAInterface demoAInterface;

    public StaticLogProxyAHandler(DemoAInterface demoAInterface) {
        this.demoAInterface = demoAInterface;
    }

    @Override
    public void doing() {
        log.info("开始时间{}",System.currentTimeMillis());
        demoAInterface.doing();
        log.info("结束时间{}",System.currentTimeMillis());
    }
}
