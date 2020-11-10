package com.prd.proxy;

/**
 * JavaLearning
 * 2020-11-10 22:43
 * 通过继承方式实现日志增强
 *
 * @author ruidong.pang
 * @since
 **/
public class UserLogDaoImpl extends  UserDaoImpl{
    @Override
    public void query() {
        System.out.println("增强日志开始");
        super.query();
        System.out.println("增强日志结束");
    }
}
