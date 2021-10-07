package com.github.tianjing.test.elasticsearch.webapp.test;

import com.github.tianjing.test.elasticsearch.webapp.dao.DeviceRepository;
import com.github.tianjing.test.elasticsearch.webapp.model.DeviceDO;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import tgtools.tasks.Task;
import tgtools.tasks.TaskContext;
import tgtools.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author 田径
 * @date 2020-07-02 16:17
 * @desc
 **/
@Component
public class DeviceService implements ApplicationRunner {

    @Autowired
    DeviceRepository deviceRepository;
    long index = 0;
    ConcurrentLinkedQueue<DeviceDO> data = new ConcurrentLinkedQueue<>();
    @Autowired
    private RestHighLevelClient client;

    public static void main(String[] args) throws Exception {
        new DeviceService().run(null);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("================================================开始处理 文件 =====================================");

//        try (BufferedReader vBufferedReader = new BufferedReader(new FileReader("C:\\Work\\DQ\\jsepcjgwork\\eureka\\cloud-develop-web-security-parent\\example\\example-other-parent\\test-security-elasticsearch-webapp\\src\\main\\resources\\device.csv"))) {
//            String vLine = null;
//            while (null != (vLine = vBufferedReader.readLine())) {
        //index++;
        // System.out.println("当前行：" + index);

//                try {
//                    String[] vContent = vLine.split(",");
//                    DeviceDO vDeviceDO = new DeviceDO();
//
//                    vContent[0] = StringUtil.replace(vContent[0], "\t", "");
//                    vContent[0] = StringUtil.replace(vContent[0], "\r\n", "");
//                    vContent[0] = StringUtil.replace(vContent[0], "\r", "");
//                    vContent[0] = StringUtil.replace(vContent[0], "\n", "");
//
//
//                    vContent[1] = StringUtil.replace(vContent[1], "\r\n", "");
//                    vContent[1] = StringUtil.replace(vContent[1], "\r", "");
//                    vContent[1] = StringUtil.replace(vContent[1], "\n", "");
//                    vContent[1] = StringUtil.replace(vContent[1], ",", "");
//                    vDeviceDO.setId(vContent[0]);
//                    vDeviceDO.setDeviceName(vContent[1]);
//                    data.add(vDeviceDO);
//                } catch (Exception e) {
//                    System.out.println("错误的内容：" + vLine);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("================================================文件处理完毕 总大小：" + data.size() + "=====================================");
//        TaskRunner vRunner = new TaskRunner();
//        for (int i = 0; i < 20; i++) {
//            ProcessTask vProcessTask = new ProcessTask();
//            vProcessTask.deviceRepository = deviceRepository;
//            vProcessTask.data = data;
//            vRunner.add(vProcessTask);
//        }
//        System.out.println("================================================开始处理es=====================================");
//        vRunner.runThreadTillEnd();


        searchDcAndUserList();

        System.out.println("================================================运行结束=====================================");

    }

    public QueryBuilder createQueryBuilder1() {
        String vText = "东吴500kVV I母";

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        {
            if (StringUtil.isNotEmpty(vText)) {

                queryBuilder
                        .should(QueryBuilders.regexpQuery("deviceName", ".*东吴+.*"))
                        .should(QueryBuilders.matchQuery("deviceName", "东吴500kVV I母"))
                ;

            }
        }
        return queryBuilder;
    }

    public QueryBuilder createQueryBuilder2() {
        String vText = "220kV第一套母差保护停用";

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        {
            if (StringUtil.isNotEmpty(vText)) {

                queryBuilder
                        .should(QueryBuilders.matchQuery("deviceName", vText))
                        .should(QueryBuilders.regexpQuery("deviceName", ".*220kV+.*"))
                        .should(QueryBuilders.regexpQuery("deviceName", ".*第一套.*"))
                        .should(QueryBuilders.regexpQuery("deviceName", ".*保护.*"))
                ;

            }
        }
        return queryBuilder;
    }


    public List<DeviceDO> searchDcAndUserList() {

        QueryBuilder queryBuilder = createQueryBuilder2();

        List<DeviceDO> vList = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest();
        {
            searchRequest.indices(DeviceDO.INDEX_NAME);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(10);
            searchSourceBuilder.query(queryBuilder);
            searchRequest.source(searchSourceBuilder);
        }
        try {
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            System.out.println("hit TotalHits:" + hits.getTotalHits());
            System.out.println("hit MaxScore:" + hits.getMaxScore());
            hits.forEach((hit) -> {
                System.out.println("Score:" + hit.getScore() + ";;;hit:" + hit.getSourceAsString());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vList;
    }

    public static class ProcessTask extends Task {
        DeviceRepository deviceRepository;
        Queue<DeviceDO> data;

        @Override
        protected boolean canCancel() {
            return false;
        }

        @Override
        public void run(TaskContext p_Param) {
            DeviceDO vBdz = null;
            while (data.size() > 0) {
                vBdz = data.poll();
                if (null == vBdz) {
                    return;
                }
                try {
                    deviceRepository.save(vBdz);
                } catch (Exception e) {
                    System.out.println("错误的内容：Czdw:" + vBdz.getId() + ";;Cznr:" + vBdz.getDeviceName() + "原因：" + e.getMessage());
                }

            }
        }
    }

}
