package com.prd.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("luban")
@Scope("prototype")
//@Profile("oracle") //表示生产，测试环境切换
public class SpringCoreDemoDaoImpl1 implements SpringCoreDemoDao{
    private String name;

    private String id;

    @Override
    public void hellworld() {
        System.out.println("hellworld1:"+name+"-"+id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}
