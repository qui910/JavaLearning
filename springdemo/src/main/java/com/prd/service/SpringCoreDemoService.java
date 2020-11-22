package com.prd.service;

import com.prd.anno.Luban;
import com.prd.dao.SpringCoreDemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "service")
@Scope("singleton")
//@Scope("prototype")
public class SpringCoreDemoService implements ISpringCoreDemoService {

//    @Autowired  //Autowired是默认byType方式自动装配,如果byType有多个，那接下来根据byName匹配属性名称
    @Resource
    private SpringCoreDemoDao dao;

//    @Autowired
//    @Qualifier("luban") //限定符，指定加载接口的某个实现类
    @Resource  //这里Resource 默认通过byName方式自动装配，但是与xml配置不一样，这时匹配属性名称，而非set方法名称
    private SpringCoreDemoDao luban;

    public SpringCoreDemoService(SpringCoreDemoDao dao) {
        this.dao = dao;
    }

    public void Hello() {
        dao.hellworld();
    }

    @Luban("")
    public void Hello1(String str) {
        luban.hellworld();
    }

    public void setDao(SpringCoreDemoDao dao) {
        this.dao = dao;
    }

    public void setLuban(SpringCoreDemoDao dao) {
        this.luban = dao;
    }
}
