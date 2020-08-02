package com.prd.service;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@Configurable
@ComponentScan("com")
//@ImportResource("classpath:spring.xml") <!-- javaconfig 与 xml，注解 混合使用 -->
public class SpringJavaConfig {

}
