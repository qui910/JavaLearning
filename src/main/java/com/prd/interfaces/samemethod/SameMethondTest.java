package com.prd.interfaces.samemethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SameMethondTest {

    public static void main(String[] args) {
        SubClass1 subClass1 = new SubClass1();
        log.info("1=",subClass1.getName());
    }
}

class SubClass1
        extends SameMethodParentClass
        implements SameMethodInterfaces
{

}
