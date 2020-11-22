# 通过xml自己模拟个spring
开发个容器将dao依赖注入到service中

需要依赖关系的说明文件，spring使用xml文件，myspring.xml
BeanFactory


# 通过注解模拟spring
AnnotationConfigApplicationContext


# 编译spring参考
* [gradle下载地址](https://services.gradle.org/distributions/)
* [使用idea和gradle编译spring5源码](https://blog.csdn.net/baomw/article/details/83956300)
* [使用IDEA+Gradle构建Spring5源码并调试（手把手教程全图解）](https://www.cnblogs.com/mazhichu/p/13163979.html)
* [【spring源码系列-0】spring源码下载、编译、debug](https://blog.csdn.net/gooaaee/article/details/104437902)

* [spring5.0x github地址](https://github.com/spring-projects/spring-framework/tree/5.0.x)


(1). 遇到这种错误，说明gradle版本过低，这里重新使用6.7编译
```shell
org.gradle.initialization.ReportedException: org.gradle.internal.exceptions.LocationAwareException: Execution failed for task ':buildSrc:compileJava'.
	at org.gradle.initialization.DefaultGradleLauncher.doBuildStages(DefaultGradleLauncher.java:151)
	at org.gradle.initialization.DefaultGradleLauncher.executeTasks(DefaultGradleLauncher.java:121)
```

(2). 
尝试使用 JDK1.8_271以上编译，不行，查找资料提示为gradle编译版本过高

* [jdk-8u271下载列表](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* [jdk-8u271-windows-x64.exe下载地址](https://download.oracle.com/otn/java/jdk/8u271-b09/61ae65e088624f5aaa0b1d2d801acb16/jdk-8u271-windows-x64.exe?AuthParam=1605936680_5c649cb5751f1b22e12e637b147be608)

```shell
org.gradle.internal.exceptions.LocationAwareException: Failed to notify build listener.
	at org.gradle.initialization.exception.DefaultExceptionAnalyser.transform(DefaultExceptionAnalyser.java:103)
	at org.gradle.initialization.exception.DefaultExceptionAnalyser.collectFailures(DefaultExceptionAnalyser.java:67)
	at org.gradle.initialization.exception.MultipleBuildFailuresExceptionAnalyser.transform(MultipleBuildFailuresExceptionAnalyser.java:40)
	
java.lang.AbstractMethodError: org.jetbrains.plugins.gradle.tooling.util.ModuleComponentIdentifierImpl.getModuleIdentifier()Lorg/gradle/api/artifacts/ModuleIdentifier	
```

(3). 后续发现是从github地址下载的非5.0x，而是master。5.0.x版本需要从zip下载。重新下载后，使用gradle4.9编译提示错误。
```shell
org.gradle.initialization.ReportedException: org.gradle.internal.exceptions.LocationAwareException: Build file 'F:\GitWorkspace\spring-framework-5.0.x\spring-beans\spring-beans.gradle' line: 29
A problem occurred evaluating project ':spring-beans'.
	at org.gradle.initialization.DefaultGradleLauncher.doBuildStages(DefaultGradleLauncher.java:151)
Caused by: org.gradle.internal.exceptions.LocationAwareException: Build file 'F:\GitWorkspace\spring-framework-5.0.x\spring-beans\spring-beans.gradle' line: 29
A problem occurred evaluating project ':spring-beans'.
	at org.gradle.initialization.DefaultExceptionAnalyser.transform(DefaultExceptionAnalyser.java:74)
	at org.gradle.initialization.MultipleBuildFailuresExceptionAnalyser.transform(MultipleBuildFailuresExceptionAnalyser.java:49)
	at org.gradle.initialization.StackTraceSanitizingExceptionAnalyser.transform(StackTraceSanitizingExceptionAnalyser.java:30)
Caused by: org.gradle.api.GradleScriptException: A problem occurred evaluating project ':spring-beans'.
	at org.gradle.groovy.scripts.internal.DefaultScriptRunnerFactory$ScriptRunnerImpl.run(DefaultScriptRunnerFactory.java:92)
	at org.gradle.configuration.DefaultScriptPluginFactory$ScriptPluginImpl$2.run(DefaultScriptPluginFactory.java:204)
	... 67 more
Caused by: groovy.lang.MissingPropertyException: No such property: values for class: org.gradle.api.internal.tasks.DefaultTaskDependency
Possible solutions: values
	at spring_beans_sy56a9ydlh9cjxyc4zdjcsen.run(F:\GitWorkspace\spring-framework-5.0.x\spring-beans\spring-beans.gradle:29)
	at org.gradle.groovy.scripts.internal.DefaultScriptRunnerFactory$ScriptRunnerImpl.run(DefaultScriptRunnerFactory.java:90)
	... 100 more
```
解决方法：修改spring-beans.gradle
```gradle
//注释掉这行代码
//compileGroovy.dependsOn = compileGroovy.taskDependencies.values - "compileJava"
```

## Gradle4.9配置阿里云加速仓库
在${GRADLE_HOME}/init.d目录下(C:\develop\gradle-5.1\init.d) ，新建repo.gradle
```gradle
allprojects {
    repositories {
         maven {
             name "aliyunmaven"
             url "http://maven.aliyun.com/nexus/content/groups/public/"
         }
    }
}
```

