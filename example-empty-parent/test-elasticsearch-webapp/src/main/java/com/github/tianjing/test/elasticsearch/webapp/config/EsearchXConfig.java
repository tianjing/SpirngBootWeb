package com.github.tianjing.test.elasticsearch.webapp.config;

import org.noear.esearchx.EsContext;
import org.noear.esearchx.model.EsData;
import org.noear.snack.ONode;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.*;

@Configuration
public class EsearchXConfig {

    public static class DemoApp {
        String tableCreateDsl = "{\n" +
                "  \"mappings\": {\n" +
                "    \"properties\": {\n" +
                "      \"log_id\": {\n" +
                "        \"type\":\"long\"\n" +
                "      },\n" +
                "      \"logger\": {\n" +
                "        \"type\":\"keyword\"\n" +
                "      },\n" +
                "      \"trace_id\": {\n" +
                "        \"type\":\"keyword\"\n" +
                "      },\n" +
                "      \"level\": {\n" +
                "        \"type\":\"long\",\n" +
                "        \"null_value\": 3\n" +
                "      },\n" +
                "      \"tag\": {\n" +
                "        \"type\":\"keyword\"\n" +
                "      },\n" +
                "      \"tag1\": {\n" +
                "        \"type\":\"keyword\"\n" +
                "      },\n" +
                "      \"tag2\": {\n" +
                "        \"type\":\"keyword\"\n" +
                "      },\n" +
                "      \"tag3\": {\n" +
                "        \"type\":\"keyword\"\n" +
                "      },\n" +
                "      \"content\": {\n" +
                "        \"type\": \"text\"\n" +
                "      },\n" +
                "      \"class_name\": {\n" +
                "        \"type\": \"keyword\"\n" +
                "      },\n" +
                "      \"thread_name\": {\n" +
                "        \"type\": \"keyword\"\n" +
                "      },\n" +
                "      \"log_fulltime\":{\n" +
                "        \"type\":   \"date\"\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"settings\" : {\n" +
                "    \"number_of_shards\":5,\n" +
                "    \"number_of_replicas\":1,\n" +
                "    \"refresh_interval\": \"2s\"\n" +
                "  }\n" +
                "}";

        //https://gitee.com/noear/esearchx/blob/main/src/test/java/features/Test1Add.java
        public void demo() throws IOException {
            EsContext esx = new EsContext("localhost:30480");
            String indice = "user_log";
            //创建索引
            esx.indiceCreate("user_log_20200101", tableCreateDsl);
            esx.indiceCreate("user_log_20200102", tableCreateDsl);
            esx.indiceCreate("user_log_20200103", tableCreateDsl);

            //构建索引别名
            esx.indiceAliases(a -> a.add("user_log_20200101", "user_log").add("user_log_20200102", "user_log").add("user_log_20200103", "user_log"));

            //删除索引（如果存在就删了；当然也可以直接删）
            if (esx.indiceExist("user_log_20200101")) {
                esx.indiceDrop("user_log_20200101");
            }

            //批量插入
            List<LogDo> list1 = new ArrayList<>();
            list1.add(new LogDo());
            esx.indice(indice).insertList(list1);

            //批量插入或更新
            Map<String, LogDo> list2 = new LinkedHashMap<>();
            list2.put("...", new LogDo());
            esx.indice(indice).upsertList(list2);

            //一个简单的查询
            LogDo result = esx.indice(indice).selectById(LogDo.class, "1");

            //一个带条件的查询
            EsData<LogDo> result1 = esx.indice("user_log").where(r -> r.term("level", 5)).orderByDesc("log_id").limit(50).selectList(LogDo.class);

            //一个复杂些的查询
            EsData<LogDo> result2 = esx.indice(indice).where(c -> c.useScore().must().term("tag", "list1").range("level", r -> r.gt(3))).orderByAsc("level").andByAsc("log_id").minScore(1).limit(50, 50).selectList(LogDo.class);

            //脚本查询
            EsData<LogDo> result3 = esx.indice(indice).where(c -> c.script("doc['tag'].value.length() >= params.len", p -> p.set("len", 2))).limit(10).selectList(LogDo.class);

            //聚合查询
            ONode result4 = esx.indice(indice).where(w -> w.term("tag", "list1")).limit(0).aggs(a -> a.terms("level", t -> t.size(20)).aggs(a1 -> a1.topHits(2, s -> s.addByAes("log_fulltime")))).selectAggs();
        }
    }

    public static class LogDo {
        public long log_id;
        public String logger;
        public String trace_id;
        public int level;
        public String tag;
        public String tag1;
        public String tag2;
        public String tag3;
        public String summary;
        public String content;
        public String class_name;
        public String thread_name;
        public String from;
        public int log_date;
        public Date log_fulltime;

        public Double _score;


    }
}
