package com.github.tianjing.test.elasticsearch.webapp.util;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedCardinality;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import tgtools.exceptions.APPErrorException;
import tgtools.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author 田径
 * @date 2021-06-16 17:16
 * @desc
 **/
public class EsUtil {
    private RestHighLevelClient restHighLevelClient;
    private Client client;
    private IndicesAdminClient adminClient;

    /**
     * 判断集群中{index}是否存在
     *
     * @param index
     * @return 存在（true）、不存在（false）
     */
    public boolean indexExists(String index) {
        IndicesExistsRequest request = new IndicesExistsRequest(index);
        IndicesExistsResponse response = adminClient.exists(request).actionGet();
        if (response.isExists()) {
            return true;
        }
        return false;
    }

    /**
     * 读取es配置信息
     *
     * @return
     */
    private Properties readElasticsearchConfig() {
        Properties properties = new Properties();
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("elasticsearch.properties");
            properties.load(new InputStreamReader(is, "UTF-8"));
        } catch (IOException e) {
            System.out.println("readEsConfig exception!");
            e.printStackTrace();
        }
        return properties;
    }


    /**
     * 创建Index
     */
    public void CreateIndex(Index pIndex) throws JSONException {
        int i = 0;

        IndicesAdminClient adminClient = client.admin().indices();
        if (indexExists(pIndex.getIndex())) {
            return;
        }

        adminClient.prepareCreate(pIndex.getIndex())
                .setSettings(Settings.builder().put("index.number_of_shards", pIndex.getNumber_of_shards()).put("index.number_of_replicas", pIndex.getNumber_of_replicas()))
                .addMapping(pIndex.getType(), pIndex.getFieldJson())
                .get();


        System.out.println("index creation success! create " + i + " index");

    }

    public long total(String pIndexName, String pField) {
        SearchRequest searchRequest = new SearchRequest(pIndexName);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .from(0)
                .size(1)
                .sort(pField, SortOrder.DESC).trackTotalHits(true);
        searchRequest.source(sourceBuilder);
        ActionFuture<SearchResponse> vResult = client.search(searchRequest);
        return vResult.actionGet().getHits().getTotalHits().value;
    }

    public long total1(String pIndexName) throws APPErrorException {
        try {
            CountRequest vCountRequest = new CountRequest(pIndexName);
            CountResponse vCountResponse = null;
            vCountResponse = restHighLevelClient.count(vCountRequest, RequestOptions.DEFAULT);
            return vCountResponse.getCount();
        } catch (IOException e) {
            throw new APPErrorException("ES查询总数有误！", e);
        }

    }


    public long count(String pIndexName, String pField) throws APPErrorException {         // 创建查询请求对象
        SearchRequest searchRequest = new SearchRequest(pIndexName);
        // 创建查询资源对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 查询条件-分组
        CardinalityAggregationBuilder cardinalityAggregationBuilder = AggregationBuilders.cardinality(pField).field(pField);
        sourceBuilder.aggregation(cardinalityAggregationBuilder);
        sourceBuilder.size(0);
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = null;
        int size = 0;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if (null == searchResponse) {
                return 0;
            }
            // 这个field就是上面起的别名
            ParsedCardinality parsedCardinality = searchResponse.getAggregations().get(pField);
            size = (int) parsedCardinality.getValue();
        } catch (IOException e) {
            throw new APPErrorException("ES查询分组条数有误！", e);
        }
        return size;
    }

    public static class Index {
        private String index;                   //索引名
        private String type;                    //type表名
        private Integer number_of_shards;        //分片数
        private Integer number_of_replicas;        //备份数
        private String fieldJson;                //字段类型

        public Index() {
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFieldJson() {
            return fieldJson;
        }

        public void setFieldJson(String fieldJson) {
            this.fieldJson = fieldJson;
        }

        public Integer getNumber_of_shards() {
            return number_of_shards;
        }

        public void setNumber_of_shards(Integer number_of_shards) {
            this.number_of_shards = number_of_shards;
        }

        public Integer getNumber_of_replicas() {
            return number_of_replicas;
        }

        public void setNumber_of_replicas(Integer number_of_replicas) {
            this.number_of_replicas = number_of_replicas;
        }

    }

}
