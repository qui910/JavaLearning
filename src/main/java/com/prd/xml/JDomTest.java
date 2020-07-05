package com.prd.xml;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;

public class JDomTest {

    public static void main(String[] args) throws IOException, JDOMException {
//        createSimpleXml();
        readXMLFromSax();
    }

    /**
     * 创建简单XML对象
     * @throws FileNotFoundException
     */
    private static  void createSimpleXml() throws IOException {
        Document document = new Document();
        Element element = new Element("root");
        document.addContent(element);
        Comment comment = new Comment("这是一个备注");
        element.addContent(comment);

        Element sube = new Element("hello");
        sube.setAttribute("name","1");
        element.addContent(sube);

        Element sube1 = new Element("world");
        Attribute attr1 = new Attribute("pass","2");
        sube1.setAttribute(attr1);
        sube.addContent(sube1);

        // 设置格式
        Format format = Format.getPrettyFormat();
        // 每行2个空格
        format.setIndent("  ");
        format.setEncoding("gbk");

        XMLOutputter out = new XMLOutputter(format);
        out.output(document,new FileOutputStream("simpleXml.xml"));
    }

    private static void readXMLFromSax() throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
//        Document doc = builder.build(new File("candidate.xml"));
        InputStream in = Thread.currentThread().getContextClassLoader().getResource("candidate.xml").openStream();
        Document doc =  builder.build(in);
        Element element = doc.getRootElement();

        System.out.println(element.getName());
    }
}
