package com.github.tianjing.test.elasticsearch.webapp.test;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//import tgtools.tasks.Task;
//import tgtools.tasks.TaskContext;
//import tgtools.util.GUID;
//import tgtools.util.StringUtil;
//
//import java.io.*;
//import java.util.LinkedList;
//import java.util.PriorityQueue;
//import java.util.Queue;
//import java.util.concurrent.ConcurrentLinkedQueue;
//
///**
// * @author 田径
// * @date 2020-07-02 16:17
// * @desc
// **/
//@Component
//public class BdzService implements ApplicationRunner {
//
//    @Autowired
//    BdzRepository bdzRepository;
//
//    long index = 0;
//
//    //ConcurrentLinkedQueue<BdzDO> data = new ConcurrentLinkedQueue<>();
//    LinkedList<BdzDO> data = new LinkedList<>();
//
//    public static void main(String[] args) throws Exception {
//        new BdzService().run(null);
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("================================================开始处理 文件 =====================================");
//        try (BufferedReader vBufferedReader = new BufferedReader(new FileReader("C:\\Users\\tian_\\Desktop\\baike_qa_train.csv"))) {
//            String vLine = null;
//            while (null != (vLine = vBufferedReader.readLine())) {
//                //index++;
//                // System.out.println("当前行：" + index);
//
//                try {
//                    String[] vContent = vLine.split(",");
//                    BdzDO vBdz = new BdzDO();
//                    vBdz.setId(GUID.newGUID());
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
//                    vBdz.setCzdw(vContent[0]);
//                    vBdz.setCznr(vContent[1]);
//                    data.add(vBdz);
//                    // bdzRepository.save(vBdz);
//                } catch (Exception e) {
//                    System.out.println("错误的内容：" + vLine);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("================================================文件处理完毕 总大小：" + data.size() + "=====================================");
////        TaskRunner vRunner = new TaskRunner();
////        for (int i = 0; i < 20; i++) {
////            ProcessTask vProcessTask = new ProcessTask();
////            vProcessTask.bdzRepository = bdzRepository;
////            vProcessTask.data = data;
////            vRunner.add(vProcessTask);
////        }
////        System.out.println("================================================开始处理es=====================================");
////        vRunner.runThreadTillEnd();
//        SaveFileTask vSaveFileTask = new SaveFileTask();
//        vSaveFileTask.data = data;
//        vSaveFileTask.run(null);
//        System.out.println("================================================运行结束=====================================");
//
//    }
//
//    public static class ProcessTask extends Task {
//        BdzRepository bdzRepository;
//        ConcurrentLinkedQueue<BdzDO> data;
//
//        @Override
//        protected boolean canCancel() {
//            return false;
//        }
//
//        @Override
//        public void run(TaskContext p_Param) {
//            BdzDO vBdz = null;
//            while (data.size() > 0) {
//                vBdz = data.poll();
//                if (null == vBdz) {
//                    return;
//                }
//                try {
//                    bdzRepository.save(vBdz);
//                } catch (Exception e) {
//                    System.out.println("错误的内容：Czdw:" + vBdz.getCzdw() + ";;Cznr:" + vBdz.getCznr() + "原因：" + e.getMessage());
//                }
//
//            }
//        }
//
//
//    }
//
//    public static class SaveFileTask extends Task {
//        Queue<BdzDO> data;
//
//        @Override
//        protected boolean canCancel() {
//            return false;
//        }
//
//        @Override
//        public void run(TaskContext p_Param) {
//            String file1 = "C:\\Users\\tian_\\Desktop\\baike_qa_train1.csv";
//            StringBuilder vStringBuilder = new StringBuilder();
//            BdzDO vBdz = null;
//            try (BufferedWriter vBufferedWriter = new BufferedWriter(new FileWriter(file1))) {
//                while (data.size() > 0) {
//                    vBdz = data.poll();
//                    if (null == vBdz) {
//                        return;
//                    }
//                    try {
//                        if (StringUtil.isNotEmpty(vBdz.getCzdw()) && StringUtil.isNotEmpty(vBdz.getCznr())) {
//                            vBufferedWriter.write(vBdz.getCzdw() + "," + vBdz.getCznr());
//                            vBufferedWriter.write(System.lineSeparator());
//                            //vStringBuilder.append(vBdz.getCzdw() + "," + vBdz.getCznr());
//                            //vStringBuilder.append(System.lineSeparator());
//                        }
//                    } catch (Exception e) {
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }
//}
