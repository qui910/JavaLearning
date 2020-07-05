package com.prd.xml;

import org.w3c.dom.Document;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

/**
 * Jaxp SAX解析器测试
 */
public class JaxpSaxTest {

    public static void main(String[] args) throws ParserConfigurationException,
            SAXException, IOException {
        // step1 获取SAX解析器工厂实例
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // step2 获得SAX解析器实例
        SAXParser parser = factory.newSAXParser();

        // step3 开始进行解析
        InputStream in = Thread.currentThread().getContextClassLoader().
                getResource("candidate.xml").openStream();
        parser.parse(in,new JaxpSaxTest().new MyXmlHandler());

    }


    class MyXmlHandler extends DefaultHandler
    {
        private Stack<String> stack = new Stack<>();

        private String name;

        private String address;

        private String tel;

        private String fax;

        private String email;

        @Override
        public void startDocument() throws SAXException {
            System.out.println("parse begin");
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("parse finished");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            //System.out.println("start element:"+qName);
            stack.push(qName);
            for (int i=0;i<attributes.getLength();i++) {
                String attrName = attributes.getQName(i);
                String attrValue = attributes.getValue(i);
                System.out.println(attrName+"="+attrValue);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            //System.out.println("end element:"+qName);
            // 表示该元素已经解析完毕，需要从栈中移除
            stack.pop();
            if("PERSON".equals(qName)) {
                System.out.println("name="+name);
                System.out.println("address="+address);
                System.out.println("tel="+tel);
                System.out.println("fax="+fax);
                System.out.println("email="+email);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String tag = stack.peek();
            if ("NAME".equals(tag)) {
                name = new String(ch,start,length);
            }
            if ("ADDRESS".equals(tag)) {
                address = new String(ch,start,length);
            }
            if ("TEL".equals(tag)) {
                tel = new String(ch,start,length);
            }
            if ("FAX".equals(tag)) {
                fax = new String(ch,start,length);
            }
            if ("EMAIL".equals(tag)) {
                email = new String(ch,start,length);
            }
        }
    }
}
