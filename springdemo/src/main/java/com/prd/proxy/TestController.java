/**
 * JavaLearning
 * 2020/11/10 22:26
 *
 * @author ruidong.pang
 * @version
 * @since
 **/
package com.prd.proxy;

/**
 * 模拟测试controller
 */
public class TestController {

    public static void main(String[] args) {
//        UserDao impl = new UserDaoImpl();

//        UserDao impl = new UserLogDaoImpl();

//        UserDao target = new UserDaoImpl();
//        UserDao impl = new UserDaoLogZh(target);

        UserDao impl = new UserDaoLogAndTimeImpl();
        impl.query();
    }
}
