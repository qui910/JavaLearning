package com.prd.service;

import com.prd.dao.SpringCoreDemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "service")
public class SpringCoreDemoService {

    @Autowired
    private SpringCoreDemoDao dao;

    public SpringCoreDemoService(SpringCoreDemoDao dao) {
        this.dao = dao;
    }

    public void Hello() {
        dao.hellworld();
    }

    public void setDao(SpringCoreDemoDao dao) {
        this.dao = dao;
    }
}
