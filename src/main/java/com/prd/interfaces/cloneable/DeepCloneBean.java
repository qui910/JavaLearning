package com.prd.interfaces.cloneable;

import lombok.Data;

/**
 * 深克隆
 */
@Data
public class DeepCloneBean implements Cloneable{
    private int deepCloneId;

    private String deepCloneName;

    private DeepInnerObjectBean innerObjectBean;

    public DeepCloneBean(int deepCloneId, String deepCloneName,
                         DeepInnerObjectBean innerObjectBean) {
        this.deepCloneId = deepCloneId;
        this.deepCloneName = deepCloneName;
        this.innerObjectBean = innerObjectBean;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        DeepCloneBean scb = null;
        scb = (DeepCloneBean)super.clone();
        scb.setInnerObjectBean((DeepInnerObjectBean)scb.getInnerObjectBean().clone());
        return scb;
    }
}
