package com.prd.service;

import com.prd.dao.SpringCoreDemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "service")
@Scope("singleton")
//@Scope("prototype")
public class SpringCoreDemoService {

//    @Autowired  //Autowired是默认byType方式自动装配,如果byType有多个，那接下来根据byName匹配属性名称
    @Resource
    private SpringCoreDemoDao dao;

//    @Autowired
    @Resource  //这里Resource 默认通过byName方式自动装配，但是与xml配置不一样，这时匹配属性名称，而非set方法名称
    private SpringCoreDemoDao dao1;

    public SpringCoreDemoService(SpringCoreDemoDao dao) {
        this.dao = dao;
    }

    public void Hello() {
        dao.hellworld();
    }

    public void Hello1() {
        dao1.hellworld();
    }

    public void setDao(SpringCoreDemoDao dao) {
        this.dao = dao;
    }

    public void setLuban(SpringCoreDemoDao dao) {
        this.dao1 = dao;
    }
}
