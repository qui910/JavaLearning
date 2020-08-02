package com.prd.service;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

@Configurable
// excludeFilters 指定不扫描哪个路径
@ComponentScan(value = "com",
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.test.*"))
//@ImportResource("classpath:spring.xml") <!-- javaconfig 与 xml，注解 混合使用 -->
public class SpringJavaConfig {

}
