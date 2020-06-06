package com.prd.interfaces.defaultmethod;

public interface DefaultInterfaces {
    String TEST_NUM="1";

    default String getTestNum() {
        return TEST_NUM;
    }
}
