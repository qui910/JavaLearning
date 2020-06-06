package com.prd.interfaces.samemethod;

public interface SameMethodInterfaces {
    default String getName() {
        return getClass().getName()+" "+hashCode();
    }
}
