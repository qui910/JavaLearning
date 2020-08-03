package com.prd.model;

import com.prd.anno.Entry;

@Entry("xxx")  //默认只有value时,可以简写为"xxx",而如果方法是name时，必须写为name="xxx"
public class CityEntry {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
