package com.prd.dao;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository(value = "dao")
public class SpringCoreDemoDaoImpl implements SpringCoreDemoDao,
        InitializingBean, DisposableBean {

    private String name;

    private String id;

    public SpringCoreDemoDaoImpl() {
        System.out.println("Constructor");
    }

    @Override
    public void hellworld() {
        System.out.println("hellworld:"+name+"-"+id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }


    /**
     * 对象实例化后需要执行的方法
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init");
    }

    /**
     * 对象实例化后需要执行的方法，这种方式需要在xml中配置init-method="init"
     * 或者使用@PostConstruct 注解
     */
    @PostConstruct
    public void init() {
        System.out.println("init1");
    }

    /**
     * 容器销毁后执行
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }
}
