package com.prd.dao;

import org.springframework.stereotype.Component;

@Component(value = "dao")
public class SpringCoreDemoDaoImpl implements SpringCoreDemoDao {

    private String name;

    private String id;

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
}
