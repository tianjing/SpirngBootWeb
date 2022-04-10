package com.github.tianjing.flink.kafka;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

/**
 * todo 数据库连接 和释放 这个demo 不能满足
 */
public class MyConsumerSink extends RichSinkFunction<String> {


    @Override
    public void open(Configuration parameters) throws Exception {
        // super.open(parameters);
    }

    @Override
    public void close() throws Exception {
    }

    @Override
    public void invoke(String content, Context context) throws Exception {
        //一次性写入
        System.out.println("MyConsumerSink:" + content);
    }

}
