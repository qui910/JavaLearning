package com.prd.reflect;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author prd
 * @version V1.0
 * @Description 日志功能代理处理器
 * @date 2020-06-11 11:44
 */
@Slf4j
public class DynamicLogProxyHandler implements InvocationHandler {

    private Object target;

    public DynamicLogProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("开始时间{}",System.currentTimeMillis());
        log.info("代理实例 类名:{}",proxy.getClass().getName());
        Object returnO = method.invoke(target,args);
        log.info("结束时间{}",System.currentTimeMillis());
        return returnO;
    }
}
