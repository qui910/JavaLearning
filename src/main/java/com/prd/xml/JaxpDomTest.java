package com.prd.xml;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * JAXP中DOM解析器测试
 */
@Slf4j
public class JaxpDomTest {
    public static void main(String[] args) throws Exception
    {
        // step 1: 获得dom解析器工厂（工作的作用是用于创建具体的解析器）
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // step 2:获得具体的dom解析器
        DocumentBuilder db = dbf.newDocumentBuilder();

        // step3: 解析一个xml文档，获得Document对象（根结点）
        // Java读取classpath下的文件 https://blog.csdn.net/jiaobuchong/article/details/52422954
        // Java中获取classpath路径下的资源文件 https://www.cnblogs.com/faunjoe88/p/8032128.html
        InputStream in = Thread.currentThread().getContextClassLoader().getResource("candidate.xml").openStream();
        Document document = db.parse(in);
        // 获得根元素
        Element root = document.getDocumentElement();
        log.info("根元素：{}",root.getTagName());

        NodeList list = root.getElementsByTagName("PERSON");
        for(int i = 0; i < list.getLength(); i++)
        {
            Element element = (Element)list.item(i);
            log.info("次级元素[{}]：{}",i,element.getTagName());
            String name = element.getElementsByTagName("NAME").item(0).getFirstChild().getNodeValue();
            log.info("name:{}",name);
            String address = element.getElementsByTagName("ADDRESS").item(0).getFirstChild().getNodeValue();
            log.info("address:{}",address);
            String tel = element.getElementsByTagName("TEL").item(0).getFirstChild().getNodeValue();
            log.info("tel:{}",tel);
            String fax = element.getElementsByTagName("FAX").item(0).getFirstChild().getNodeValue();
            log.info("fax:{}",fax);
            String email = element.getElementsByTagName("EMAIL").item(0).getFirstChild().getNodeValue();
            log.info("email:{}",email);
            log.info("--------------------------------------");
        }
    }
}
