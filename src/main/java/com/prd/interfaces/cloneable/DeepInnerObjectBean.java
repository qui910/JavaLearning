package com.prd.interfaces.cloneable;

import lombok.Data;

/**
 * 深克隆内部引用对象
 */
@Data
public class DeepInnerObjectBean implements Cloneable{
    private int deepInnerObjectId;

    private String deepInnerObjectName;

    public DeepInnerObjectBean(int deepInnerObjectId,
                               String deepInnerObjectName) {
        this.deepInnerObjectId = deepInnerObjectId;
        this.deepInnerObjectName = deepInnerObjectName;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        DeepInnerObjectBean scb = null;
        scb = (DeepInnerObjectBean)super.clone();
        return scb;
    }
}
