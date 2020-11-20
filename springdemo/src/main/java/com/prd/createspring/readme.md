# 自己模拟个spring
开发个容器将dao依赖注入到service中

需要依赖关系的说明文件，spring使用xml文件，myspring.xml




# 编译spring参考
使用idea和gradle编译spring5源码
https://blog.csdn.net/baomw/article/details/83956300

【spring源码系列-0】spring源码下载、编译、debug
https://blog.csdn.net/gooaaee/article/details/104437902

遇到这种错误，说明gradle版本过低，这里重新使用6.7编译
```java
org.gradle.initialization.ReportedException: org.gradle.internal.exceptions.LocationAwareException: Execution failed for task ':buildSrc:compileJava'.
	at org.gradle.initialization.DefaultGradleLauncher.doBuildStages(DefaultGradleLauncher.java:151)
	at org.gradle.initialization.DefaultGradleLauncher.executeTasks(DefaultGradleLauncher.java:121)
```
