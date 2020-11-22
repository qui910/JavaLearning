package com.prd.createspring;

import com.prd.anno.Luban;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * JavaLearning
 * 2020-11-22 13:24
 * 模拟注解版spring
 *
 * @author ruidong.pang
 * @since
 **/
public class AnnotationConfigApplicationContext {

    private Map<String,Object> beanMap= new HashMap<String,Object>();

    public void scan(String basePackage){
        String rootPath = this.getClass().getResource("/").getPath();
        String  basePackagePath =basePackage.replaceAll("\\.","\\\\");
        File file = new File(rootPath+"//"+basePackagePath);
        String names[]=file.list();
        for (String name : names) {
            name=name.replaceAll(".class","");
            try {

                Class clazz =  Class.forName(basePackage+"."+name);
                //判斷是否是屬於@servi@xxxx
                if(clazz.isAnnotationPresent(Luban.class)){
                    Luban luban = (Luban) clazz.getAnnotation(Luban.class);
                    beanMap.put(luban.value(),clazz.newInstance());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public <T> T getBean(String beanName) {
        Object obj = beanMap.get(beanName);
       return (T)obj;
    }
}
