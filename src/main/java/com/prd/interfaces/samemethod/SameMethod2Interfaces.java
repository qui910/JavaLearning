package com.prd.interfaces.samemethod;

public interface SameMethod2Interfaces {
    default String getName() {
        return getClass().getName()+" "+hashCode();
    }
}
