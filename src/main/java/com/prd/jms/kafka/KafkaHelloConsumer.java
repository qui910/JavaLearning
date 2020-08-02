package com.prd.jms.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class KafkaHelloConsumer {

    private Properties kafkaPros = new Properties();

    private KafkaConsumer<String,String> kafkaConsumer;

    public KafkaHelloConsumer() {

        kafkaPros.put("bootstrap.servers","106.54.247.103:9092");
        kafkaPros.put("group.id","test1");
        kafkaPros.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        kafkaPros.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        kafkaConsumer = new KafkaConsumer<String, String>(kafkaPros);
        kafkaConsumer.subscribe(Collections.singleton("test"));
    }

    public void get() {
        while (true) {
            ConsumerRecords<String,String> records = kafkaConsumer.poll(100);
            for(ConsumerRecord<String,String> record:records) {
                System.out.println("topic:"+record.topic()+" partition:"+record.partition()
                        +" offset:"+record.offset()+" key:"+record.key()+" value:"+record.value());
            }
        }
    }

    public static void main(String[] args) {
        KafkaHelloConsumer kafkaHelloConsumer = new KafkaHelloConsumer();
        kafkaHelloConsumer.get();
    }
}
