package com.prd.interfaces.cloneable;

import lombok.Data;

/**
 * 浅克隆
 */
@Data
public class ShallowCloneBean implements Cloneable {
    private int shallowCloneId;

    private String shallowCloneName;

    private ShallowInnerObjectBean innerObjectBean;

    public ShallowCloneBean(int shallowCloneId, String shallowCloneName, ShallowInnerObjectBean innerObjectBean) {
        this.shallowCloneId = shallowCloneId;
        this.shallowCloneName = shallowCloneName;
        this.innerObjectBean = innerObjectBean;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ShallowCloneBean scb = null;
        scb = (ShallowCloneBean)super.clone();
        return scb;
    }
}
