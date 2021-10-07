package com.github.tianjing.test.tensorflow.webapp.util;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import java.util.List;

/**
 * @author 田径
 * @date 2021-10-05 9:19
 * @desc
 **/
public class MatHelper {
    public static byte[] toBytes(Mat pMat) {
        byte[] grayData = new byte[pMat.cols() * pMat.rows()];
        pMat.get(0, 0, grayData);
        return grayData;
    }

    public static byte[] toBytes(Mat pMat, String pFileExtension, Rect pRect) {
        MatOfByte vMatOfByte = new MatOfByte();
        Imgcodecs.imencode(pFileExtension,pMat.submat(pRect),vMatOfByte);
        return vMatOfByte.toArray();
    }

    public static byte[] toBytes(Mat pMat, String pFileExtension) {
        MatOfByte vMatOfByte = new MatOfByte();
        Imgcodecs.imencode(pFileExtension, pMat, vMatOfByte);
        return vMatOfByte.toArray();
    }

    public static Mat toMat(byte[] pBytes) {
        return Imgcodecs.imdecode(new MatOfByte(pBytes), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
    }

    public static Mat toMat(List<Byte> bs) {
        Mat vRes = new Mat();
        int count = (bs != null) ? bs.size() : 0;
        if (count > 0) {
            byte[] buff = new byte[count];
            for (int i = 0; i < count; i++) {
                byte b = bs.get(i);
                buff[i] = b;
            }
            return toMat(buff);
        }
        return vRes;
    }
}
