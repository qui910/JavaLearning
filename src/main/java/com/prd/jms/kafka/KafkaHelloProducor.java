package com.prd.jms.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Future;

public class KafkaHelloProducor {
    private Properties kafkaPros = new Properties();

    private KafkaProducer<String,String> kafkaProducer;

    public KafkaHelloProducor() {
        kafkaPros.put("bootstrap.servers","106.54.247.103:9092");
        kafkaPros.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        kafkaPros.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        kafkaProducer = new KafkaProducer<String,String>(kafkaPros);
    }

    /**
     * 发送后不管是否成功
     * @param topic
     * @param key
     * @param value
     */
    public void sendAndForget(String topic,String key,String value) {
        ProducerRecord<String,String> record = new ProducerRecord(topic,key,value);
        try {
            kafkaProducer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步发送 等待结果
     * @param topic
     * @param key
     * @param value
     */
    public void syncSend(String topic,String key,String value) {
        ProducerRecord<String,String> record = new ProducerRecord(topic,key,value);
        try {
            Future future= kafkaProducer.send(record);
            System.out.println("发送结果："+future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步发送
     * @param topic
     * @param key
     * @param value
     */
    public void asyncSend(String topic,String key,String value) {
        ProducerRecord<String,String> record = new ProducerRecord(topic,key,value);
        try {
            kafkaProducer.send(record,new DefaultCallBack());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class DefaultCallBack implements Callback {

        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            System.out.println("返回结果："+metadata);
            if (exception!=null) {
                exception.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        KafkaHelloProducor helloProducor = new KafkaHelloProducor();
        int i=0;
        while (true){
            i++;
            helloProducor.asyncSend("test","b"+i,"testasyncSend"+ Math.random());
            if (i>20) {
                break;
            }
        }
        // 第1种和第3种发送方式，在发送完成后必须等待，否则直接退出JVM到导致发送失败。
        for(;;){

        }
    }
}
