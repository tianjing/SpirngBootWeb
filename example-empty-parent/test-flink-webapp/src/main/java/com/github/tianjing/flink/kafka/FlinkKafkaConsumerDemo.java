package com.github.tianjing.flink.kafka;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;

public class FlinkKafkaConsumerDemo {
    // 创建Logger对象
    private static final Logger log = LoggerFactory.getLogger(FlinkKafkaConsumerDemo.class);

    public static void main(String[] args) throws Exception {
        log.info("000000000000000000000000000001" );

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(5000); // 非常关键，一定要设置启动检查点！！
        //env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);

        Properties props = new Properties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.setProperty("bootstrap.servers", "192.168.88.35:9093");
        props.setProperty("group.id", "test");

        DataStreamSource vDataStreamSource = env.addSource(new FlinkKafkaConsumer("test", new SimpleStringSchema(), props)).setParallelism(1);

        vDataStreamSource.addSink(new MyConsumerSink());
        log.info("2222222222222222222222222" );
        env.execute("FlinkKafkaConsumerDemo demo");
    }

    public static class MyConsumerSink extends RichSinkFunction<String> {
        private static final Logger log = LoggerFactory.getLogger(MyConsumerSink.class);

        @Override
        public void open(Configuration parameters) throws Exception {
            // super.open(parameters);
            log.info("MyConsumerSink: open" );
        }

        @Override
        public void close() throws Exception {
            log.info("MyConsumerSink: close" );
        }

        @Override
        public void invoke(String content, Context context) throws Exception {
            //一次性写入
            log.info("MyConsumerSink:" + content);
        }

    }

}
