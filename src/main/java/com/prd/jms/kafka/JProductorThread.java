package com.prd.jms.kafka;

import java.util.Properties;

public class JProductorThread {

    public Properties configure() {
        Properties props = new Properties();
        // 指定kafka集群地址
        props.put("bootstrap.servers","127.0.0.1:9092");
        // 设置应答模式，1表示有一个Kafka代理节点返回结果
        props.put("acks","1");
        // 重试次数
        props.put("retries","0");
        // 批量提交代销
        props.put("batch.size",16384);
        // 延时提交
        props.put("linger.ms",1);
        // 缓冲大小
        props.put("buffer.memory",33554432);

        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");

        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        // 指定自定义分区类
        props.put("partitioner.class","com.prd.jms.kafka.JPartitioner");

        return props;
    }
}
