package com.prd.interfaces.cloneable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeepCloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        DeepCloneBean bean1 = new DeepCloneBean(1,"A1",
                new DeepInnerObjectBean(11,
                        "A11"));
        DeepCloneBean bean2 = (DeepCloneBean)bean1.clone();
        bean2.setDeepCloneId(2);
        bean2.setDeepCloneName("B");
        log.info("是否相等:{}",bean1.equals(bean2));
        log.info("bean1:{}",bean1);
        log.info("bean2:{}",bean2);
        log.info("引用对象是否相等:{}",bean1.getInnerObjectBean()
                .equals(bean2.getInnerObjectBean()));
        log.info("bean1:{}",bean1.getInnerObjectBean());
        log.info("bean2:{}",bean2.getInnerObjectBean());
        bean2.getInnerObjectBean().setDeepInnerObjectId(12);
        bean2.getInnerObjectBean().setDeepInnerObjectName("B12");
        log.info("修改后引用对象是否相等:{}",bean1.getInnerObjectBean()
                .equals(bean2.getInnerObjectBean()));
        log.info("修改后bean1:{}",bean1.getInnerObjectBean());
        log.info("修改后bean2:{}",bean2.getInnerObjectBean());
    }
}
