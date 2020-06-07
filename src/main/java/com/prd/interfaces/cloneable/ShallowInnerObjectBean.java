package com.prd.interfaces.cloneable;

import lombok.Data;

/**
 * 克隆对象的内部引用对象
 */
@Data
public class ShallowInnerObjectBean {

    private int inneerObjectId;

    private String innerObjectName;

    public ShallowInnerObjectBean(int inneerObjectId, String innerObjectName) {
        this.inneerObjectId = inneerObjectId;
        this.innerObjectName = innerObjectName;
    }
}
