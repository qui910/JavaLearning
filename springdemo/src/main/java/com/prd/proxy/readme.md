目标实现对query方法添加日志等

1. 原始接口 UserDaoImpl
   测试类 TestController

2. 通过继承方式实现日志增强 UserLogDaoImpl 

3. 通过聚合模式实现日志增强 UserDaoLogZh

4. 通过链式继承实现增强日志和时间 UserDaoLogAndTimeImpl

