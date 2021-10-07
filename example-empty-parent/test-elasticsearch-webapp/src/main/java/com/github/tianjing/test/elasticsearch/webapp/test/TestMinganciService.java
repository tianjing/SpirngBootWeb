package com.github.tianjing.test.elasticsearch.webapp.test;

//import org.elasticsearch.index.query.MultiMatchQueryBuilder;
//import org.elasticsearch.index.query.Operator;
//import org.elasticsearch.index.query.QueryStringQueryBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ResourceUtils;
//import tgtools.util.FileUtil;
//import tgtools.util.GUID;
//import tgtools.util.LogHelper;
//
//import java.net.URL;
//import java.net.URLDecoder;
//
///**
// * @author 田径
// * @date 2020-03-05 15:07
// * @desc 实践后发现es 对于敏感词的处理比较弱只能是简单的搜索匹配 无法进行定位和替换（分词问题）。
// * 正常处理应该是用敏感词去es库里搜索内容
// **/
////@Component
//public class TestMinganciService implements ApplicationRunner {
//
//    @Autowired
//    MinGanCiRepository minGanCiRepository;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        LogHelper.infoForce("", "TestItemService test", "main");
//        searchData();
//        System.out.println("");
//    }
//
//    private void searchData() {
//        QueryStringQueryBuilder fd = new QueryStringQueryBuilder("ci:习近平最近跑了一次马拉松");
//        fd.minimumShouldMatch("30%");
//        fd.defaultOperator(Operator.AND);
//        //fd.field("ci");
//        //fd.analyzer("");
//        Iterable<MinGanCiDO> vTitem = minGanCiRepository.search(fd);
//        vTitem.forEach((item)->{
//            System.out.println(item);
//        });
//        System.out.println(vTitem);
//
//
//
//    }
//
//    private void initData() {
//        try {
//            String[] vFileNames = new String[]{"反动词库.txt", "敏感词.txt", "暴恐词库.txt", "色情词库.txt"};
//            for (String vFileName : vFileNames) {
//                URL vUrl = ResourceUtils.getURL("classpath:minganci/" + vFileName);
//                if (null != vUrl) {
//                    String vContent = FileUtil.readFile(URLDecoder.decode(vUrl.getFile(), "utf-8"));
//                    String[] cis = vContent.split("\t\n");
//                    for (String vCi : cis) {
//                        MinGanCiDO vItem = new MinGanCiDO();
//                        vItem.setId(GUID.newGUID());
//                        vItem.setContent(vCi);
//                        vItem.setCi(vCi);
//                        vItem.setType(vFileName);
//                        minGanCiRepository.save(vItem);
//                    }
//                }
//
//            }
//
//
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//    }
//}
