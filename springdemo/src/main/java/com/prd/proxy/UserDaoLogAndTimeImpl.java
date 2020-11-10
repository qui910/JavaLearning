package com.prd.proxy;

/**
 * JavaLearning
 * 2020-11-10 23:48
 * 通过链式继承实现增强日志和时间
 *
 * @author ruidong.pang
 * @since
 **/
public class UserDaoLogAndTimeImpl extends UserLogDaoImpl {
    @Override
    public void query() {
        System.out.println("------time--------------");
        super.query();
    }
}
