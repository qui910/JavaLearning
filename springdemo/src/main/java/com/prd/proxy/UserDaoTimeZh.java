package com.prd.proxy;

/**
 * JavaLearning
 * 2020-11-10 23:57
 * 通过聚合模式实现日志增强
 *
 * @author ruidong.pang
 * @since
 **/
public class UserDaoTimeZh implements UserDao {

    // 目标对象
    private UserDao userDao;

    public UserDaoTimeZh(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void query() {
        System.out.println("---------time--------");
        userDao.query();
    }
}
