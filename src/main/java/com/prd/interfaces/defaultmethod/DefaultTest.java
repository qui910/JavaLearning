package com.prd.interfaces.defaultmethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultTest {
    public static void main(String[] args) {
        DefaultInterfaces interfaces = new DefaultInterfacesService();
        log.info(interfaces.getTestNum());
    }
}
