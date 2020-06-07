package com.prd.interfaces.cloneable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShallowCloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        ShallowCloneBean bean1 = new ShallowCloneBean(
                1,"A",
                new ShallowInnerObjectBean(11,"A11"));
        ShallowCloneBean bean2 = (ShallowCloneBean)bean1.clone();
        bean2.setShallowCloneId(2);
        bean2.setShallowCloneName("B");
        log.info("是否相等:{}",bean1.equals(bean2));
        log.info("bean1:{}",bean1);
        log.info("bean2:{}",bean2);
        log.info("引用对象是否相等:{}",bean1.getInnerObjectBean()
                .equals(bean2.getInnerObjectBean()));
        log.info("bean1:{}",bean1.getInnerObjectBean());
        log.info("bean2:{}",bean2.getInnerObjectBean());
        bean2.getInnerObjectBean().setInneerObjectId(12);
        bean2.getInnerObjectBean().setInnerObjectName("B12");
        log.info("修改后引用对象是否相等:{}",bean1.getInnerObjectBean()
                .equals(bean2.getInnerObjectBean()));
        log.info("修改后bean1:{}",bean1.getInnerObjectBean());
        log.info("修改后bean2:{}",bean2.getInnerObjectBean());
    }
}
