package com.github.tianjing.test.tensorflow.webapp.util;

/**
 * @author 田径
 * @date 2021-10-05 9:17
 * @desc
 **/
public class OpenCVLibrary {
    private static final String NATIVE_FULL_PATH = "/nu/pattern/opencv/";
    private static final String OS_ARCH = "x86";
    private static final String JAR_PREFIX = "opencv_java320";

    static {
        try {
            String vModel = System.getProperty("sun.arch.data.model");

            String vFullPath = "";
            if (OSInfo.isWindows()) {
                vFullPath = NATIVE_FULL_PATH + "windows/" + OS_ARCH + "_" + vModel + "/" + JAR_PREFIX + ".dll";
            } else if (OSInfo.isLinux()) {
                vFullPath = NATIVE_FULL_PATH + "linux/" + OS_ARCH + "_" + vModel + "/lib" + JAR_PREFIX + ".so";
            } else if (OSInfo.isMacOSX()) {
                vFullPath = NATIVE_FULL_PATH + "osx/" + OS_ARCH + "_" + vModel + "/lib" + JAR_PREFIX + ".dylib";
            }

            NativeUtils.loadLibraryFromJar(vFullPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void load()
    {}
}
