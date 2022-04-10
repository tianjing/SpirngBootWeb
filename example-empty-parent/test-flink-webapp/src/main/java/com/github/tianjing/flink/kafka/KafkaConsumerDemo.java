package com.github.tianjing.flink.kafka;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerDemo {
    public static void main(String[] args) throws Exception {
//        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.enableCheckpointing(5000); // 非常关键，一定要设置启动检查点！！
//        //env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
//        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
//
//        Properties props = new Properties();
//        props.setProperty("bootstrap.servers", "192.168.88.35:9093");
//        props.setProperty("group.id", "flink-group");
//
//
//        DataStreamSource vDataStreamSource = env.addSource(new FlinkKafkaConsumer<String>("test", new SimpleStringSchema(), props)).setParallelism(1);
//
//        vDataStreamSource.addSink(new MyConsumerSink());
//
//        //vDataStreamSource.print();
//
//        env.execute("KafkaConsumer demo");
        consumer();
    }


    public static void consumer() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.88.35:9093");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");

        KafkaConsumer consumer = new KafkaConsumer(props);
        // 订阅消息
        consumer.subscribe(Collections.singletonList("test"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(String.format("topic:%s,offset:%d,消息:%s", //
                        record.topic(), record.offset(), record.value()));
            }
        }
    }
}
