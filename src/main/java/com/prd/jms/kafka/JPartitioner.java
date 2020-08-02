package com.prd.jms.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class JPartitioner implements Partitioner {

    /**
     * 实现Kafka主题分区索引算法
     * @param s
     * @param o
     * @param bytes
     * @param o1
     * @param bytes1
     * @param cluster
     * @return
     */
    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        int partition = 0;
        String k = (String)o;
        partition = Math.abs(k.hashCode())%cluster.partitionCountForTopic(s);
        return partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
