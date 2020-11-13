/**
 * JavaLearning
 * 2020/11/10 22:26
 *
 * @author ruidong.pang
 * @version
 * @since
 **/
package com.prd.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 模拟测试controller
 */
public class TestController {

    public static void main(String[] args) {
//        UserDao impl = new UserDaoImpl();

//        UserDao impl = new UserLogDaoImpl();

//        UserDao target = new UserDaoImpl();
//        UserDao impl = new UserDaoLogZh(target);

//        UserDao impl = new UserDaoLogAndTimeImpl();

//        UserDao target = new UserDaoLogZh(new UserDaoImpl());
//        UserDao impl = new UserDaoTimeZh(target);
//        impl.query();

//          UserDao target = new UserDaoImpl();
//          UserDao impl = (UserDao) ProxyUtil.newInstance(target);
//          impl.query();

        final UserDaoImpl impl = new UserDaoImpl();
        UserDao proxy = (UserDao) Proxy.newProxyInstance(TestController.class.getClassLoader(),
                new Class[]{UserDao.class}, (proxy1, method, args1) -> {
                    System.out.println("-----log-----");
                    return method.invoke(impl, args1);
                });
        proxy.query();
    }
}
