package com.prd.service;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configurable
// excludeFilters 指定不扫描哪个路径
@ComponentScan(value = "com",
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.test.*"))
//@ImportResource("classpath:spring.xml") <!-- javaconfig 与 xml，注解 混合使用 -->
@EnableAspectJAutoProxy(proxyTargetClass = false) // 开启AspectJ
public class SpringJavaConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setPassword("root");
//        driverManagerDataSource.setUsername("user");
//        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306");
//        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return driverManagerDataSource;
    }

    //这里在创建SqlSessionFactoryBean时依赖DataSource dataSource()
    //通过Bean可以自动装配
    @Bean
    @Autowired //spring5 可以不写这个，但是为了保险还是写上
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean;
    }

}
