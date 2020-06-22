package com.prd.reflect;

import lombok.extern.slf4j.Slf4j;

/**
 * @author prd
 * @version V1.0
 * @Description 静态代理类
 * @date 2020-06-11 11:45
 */
@Slf4j
public class StaticLogProxyHandler implements DemoInterface{

    private DemoInterface demoInterface;

    public StaticLogProxyHandler(DemoInterface demoInterface) {
        this.demoInterface = demoInterface;
    }

    @Override
    public void exec() {
        log.info("开始时间{}",System.currentTimeMillis());
        demoInterface.exec();
        log.info("结束时间{}",System.currentTimeMillis());
    }
}
