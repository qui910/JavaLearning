package com.prd.interfaces.defaultmethod;

public interface DefaultInterfaces {
    String TEST_NUM="1";

    /**
     * v1.1
     * @return
     */
    default String getTestNum() {
        return TEST_NUM;
    }

    /**
     * v1.0
     * @return
     */
    String getPassWd();
}
