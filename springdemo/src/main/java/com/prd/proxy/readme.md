目标实现对query方法添加日志等

> 静态代理
1. 原始接口 UserDaoImpl
   测试类 TestController

2. 通过继承方式实现日志增强 UserLogDaoImpl 

3. 通过链式继承实现增强日志和时间 UserDaoLogAndTimeImpl

4. 通过聚合模式实现日志增强 UserDaoLogZh

5. 通过使用多次聚合实现增强日志和时间 UserDaoTimeZh
```$java
        UserDao target = new UserDaoLogZh(new UserDaoImpl());
        UserDao impl = new UserDaoTimeZh(target);
        impl.query();
```

        
> 动态代理
1. 手动编写代理类方式实现增强日志
```$java
          UserDao target = new UserDaoImpl();
          UserDao impl = (UserDao) ProxyUtil.newInstance(target);
          impl.query();

```
