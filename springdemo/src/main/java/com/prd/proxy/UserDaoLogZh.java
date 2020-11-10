/**
 * JavaLearning
 * 2020/11/10 22:25
 * 测试动态代理
 *
 * @author ruidong.pang
 * @version
 * @since
 **/
package com.prd.proxy;

/**
 * 通过聚合模式实现日志增强
 * 使用装饰器模式
 */
public class UserDaoLogZh implements UserDao {

    // 目标对象
    private UserDao userDao;

    public UserDaoLogZh(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void query() {
        System.out.println("---------log--------");
        userDao.query();
    }
}
