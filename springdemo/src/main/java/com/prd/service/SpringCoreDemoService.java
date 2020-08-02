package com.prd.service;

import com.prd.dao.SpringCoreDemoDao;

public class SpringCoreDemoService {

    private SpringCoreDemoDao dao;

    public void Hello() {
        dao.hellworld();
    }

    public void setDao(SpringCoreDemoDao dao) {
        this.dao = dao;
    }
}
