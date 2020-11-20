package com.prd.createspring;


/**
 * JavaLearning
 * 2020-11-20 21:16
 *
 * @author ruidong.pang
 * @since
 **/
public class UserServiceImpl implements UserService {

    UserDao userDao;



//    public UserServiceImpl(UserDao userDao) {
//        this.userDao = userDao;
//    }

    @Override
    public void find() {
        System.out.println("service find");
        userDao.query();
    }
}
