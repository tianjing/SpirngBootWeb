package com.github.tianjing.test.elasticsearch.webapp.service;
//

//import org.apache.commons.io.FileUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import tgtools.tasks.Task;
//import tgtools.tasks.TaskContext;
//import tgtools.tasks.TaskRunner;
//import tgtools.util.*;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.concurrent.ArrayBlockingQueue;
//
///**
// * @author 田径
// * @date 2020-03-05 14:58
// * @desc
// **/
//@Service
//public class FileService {
//
//    File file = new File("C:\\Users\\tian_\\Desktop\\111.txt");
//    String[] hosts = new String[]{
//            "192.168.1.130", "192.168.1.131", "192.168.1.132", "192.168.1.133", "192.168.1.134",
//            "192.168.1.135", "192.168.1.136", "192.168.1.137", "192.168.1.138", "192.168.1.139"
//    };
//    int[] ports = new int[]{
//            20, 21, 22, 23, 24, 25, 26, 27, 28, 29
//    };
//
//    String[] paths = new String[]{
//            "/home/tianjing", "/home/fda", "/fd1", "/fdsa2", "/fdf32f3",
//            "/fdsa4", "/fafda5", "/fdaf36", "/fda37", "/fdfds38"
//    };
//
//    String[] protocols = new String[]{
//            "sftp", "ftp", "samba", "sftp", "ftp",
//            "sftp", "ftp", "samba", "sftp", "samba"
//    };
//    @Autowired
//    private FileRepository fileRepository;
//
//
//    private ArrayBlockingQueue<FileDO> data = new ArrayBlockingQueue<FileDO>(200000);
//    private String res;
//
//    public void testWrite() {
//
//        String vContent = FileUtil.readFile("C:\\Users\\tian_\\Desktop\\baike_qa_train.csv");
//        String[] vRows = vContent.split("\t\n");
//        System.out.println(vRows.length);
//        int contentIndex = 0;
//        for (int i = 0; i < vRows.length; i++) {
//            if (i == 0) {
//                continue;
//            }
//
//            try {
//
//                String[] vColumn = vRows[i].split(",");
//                FileDO vFileDO = new FileDO();
//                vFileDO.setId(GUID.newGUID());
//                vFileDO.setName(vColumn[0]);
//                vFileDO.setContent(StringUtil.replace(vColumn[1], " ", ""));
//                vFileDO.setHost(hosts[i % 10]);
//                vFileDO.setEditTime(DateUtil.getCurrentDate());
//                vFileDO.setPath(paths[i % 10]);
//                vFileDO.setPort(ports[i % 10]);
//                vFileDO.setProtocol(protocols[i % 10]);
//                contentIndex = buildContent(contentIndex, vFileDO);
//                data.add(vFileDO);
//
//
//            } catch (Throwable e) {
//                e.printStackTrace();
//            }
//
//            contentIndex++;
//        }
//        TaskRunner vRuner = new TaskRunner();
//        for (int i = 0; i < 1; i++) {
//            vRuner.add(new FileSaveTask(fileRepository, data));
//        }
//        vRuner.runThreadTillEndWithOutLock();
//        System.out.println("=============================== testWrite end ===========================");
//    }
//
//    public int buildContent(int pIndex, FileDO pFileDO) {
//        int contentLength = 10000;
//        if (StringUtil.isNullOrEmpty(res)) {
//            try {
//                res = FileUtils.readFileToString(file);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//
//        int start = pIndex * contentLength;
//        int end = start + contentLength;
//        if (res.length() > end) {
//            pFileDO.setContent(pFileDO.getContent() + res.substring(start, end));
//            return pIndex;
//        }
//        pFileDO.setContent(pFileDO.getContent() + res.substring(0, contentLength));
//        return 1;
//
//    }
//
//    public static class EsSaveTask extends Task {
//        FileRepository fileRepository;
//        ArrayBlockingQueue<FileDO> data;
//
//        public EsSaveTask(FileRepository pFileRepository, ArrayBlockingQueue<FileDO> pData) {
//            fileRepository = pFileRepository;
//            data = pData;
//        }
//
//        @Override
//        protected boolean canCancel() {
//            return false;
//        }
//
//        @Override
//        public void run(TaskContext p_Param) {
//            while (true) {
//                FileDO vFileDO = data.poll();
//                if (null == vFileDO) {
//                    return;
//                }
//                if (data.size() % 1000 == 0) {
//                    System.out.println("data.size()" + data.size());
//                }
//                fileRepository.save(vFileDO);
//            }
//        }
//    }
//
//    public static class FileSaveTask extends Task {
//        ArrayBlockingQueue<FileDO> data;
//        File file = new File("C:\\Users\\tian_\\Desktop\\out.txt");
//
//        public FileSaveTask(FileRepository pFileRepository, ArrayBlockingQueue<FileDO> pData) {
//            data = pData;
//        }
//
//        @Override
//        protected boolean canCancel() {
//            return false;
//        }
//
//        @Override
//        public void run(TaskContext p_Param) {
//            while (true) {
//                FileDO vFileDO = data.poll();
//                if (null == vFileDO) {
//                    return;
//                }
//                if (data.size() % 1000 == 0) {
//                    System.out.println("data.size()" + data.size());
//                }
//                try {
//                    FileUtils.write(file, JsonParseHelper.parseToJson(vFileDO, false), "utf-8", true);
//                    FileUtils.write(file, System.lineSeparator(), "utf-8", true);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
