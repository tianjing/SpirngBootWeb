package com.github.tianjing.flink.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

public class FlinkKafkaProducerDemo {
    public static void main(String[] args) throws Exception {
        producer();
    }


    public static void producer() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.88.35:9093");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        try (KafkaProducer vKafkaProducer = new KafkaProducer(props)) {
            Future<RecordMetadata > vFuture = vKafkaProducer.send(new ProducerRecord("test", "tianjing"));
            System.out.println(vFuture.get());
        }catch (Throwable e)
        {
            System.out.println(e);
        }
    }
}
