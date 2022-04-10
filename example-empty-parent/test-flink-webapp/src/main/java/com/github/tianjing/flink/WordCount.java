package com.github.tianjing.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;


public class WordCount {
    public static void main(String[] args) throws Exception {
        //get runtime environment
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //read content into dataset from file
        DataStream<String> stringDataSet = env.fromCollection(new ArrayList() {
            {
                add("111");
                add("222");
                add("222");
            }
        });

        //print content of dataset
        stringDataSet.print();
        env.execute("test word");

    }
    // ------------------------------------------------------------------------

    /**
     * Data type for words with count.
     */
    public static class WordWithCount {
        public String word;
        public long count;

        public WordWithCount() {
        }

        public WordWithCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return word + " : " + count;
        }
    }

}
