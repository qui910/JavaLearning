package com.prd.interfaces.samemethod;

public class SameMethodParentClass {
    public String getName(){
        return getClass().getName()+" "+hashCode();
    }
}
