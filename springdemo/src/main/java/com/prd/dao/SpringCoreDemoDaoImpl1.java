package com.prd.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository(value = "dao1")
@Scope("prototype")
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
