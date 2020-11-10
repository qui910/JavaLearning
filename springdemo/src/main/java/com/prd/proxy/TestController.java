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
        UserDaoImpl impl = new UserDaoImpl();
        impl.query();
    }
}
