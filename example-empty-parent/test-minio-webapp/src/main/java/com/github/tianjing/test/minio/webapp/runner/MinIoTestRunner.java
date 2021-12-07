package com.github.tianjing.test.minio.webapp.runner;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.PutObjectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author 田径
 * @date 2021-12-07 14:14
 * @desc
 **/
public class MinIoTestRunner implements ApplicationRunner {

    private static final String MINIO_BUCKET = "miniotest";
    private static final String fileName = "test.txt";


    @Autowired
    private MinioClient minioClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        saveFile();
        getFile();
        deleteFile();
    }

    protected void saveFile() {
        String file = "C:\\tianjing\\github\\SpirngBootWeb\\example-empty-parent\\test-minio-webapp\\src\\main\\resources\\test\\test.txt";

        try {
            FileInputStream vFileInputStream = new FileInputStream(file);
            minioClient.putObject(MINIO_BUCKET, fileName, vFileInputStream, new PutObjectOptions(vFileInputStream.available(), -1));

            System.out.println("save end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void getFile() {
        try {
            ObjectStat stat = minioClient.statObject(MINIO_BUCKET, fileName);
            System.out.println(stat);
            //Socket 数据 注意内存控制。
            InputStream vInputStream = minioClient.getObject(MINIO_BUCKET, fileName);
            System.out.println(vInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void deleteFile() {
        try {
            minioClient.removeObject(MINIO_BUCKET, fileName);
            System.out.println("delete end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
