package com.github.tianjing.tgtools.web.security.autoconfigure.util.jvm;


import tgtools.exceptions.APPErrorException;
import tgtools.util.StringUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 田径
 * @date 2020-02-11 11:26
 * @desc
 **/
public class JvmInfoHelper {
    public static String getJvmInfo() {
        try {
            if (!isUnix()) {
                return JvmUtil.getJvmInfo().toString();
            }

            SystemProcessMemoryInfo vResult = getLinuxPidMem(getJvmPid());

            return JvmUtil.getJvmInfo().toString() + vResult.toStringByMB();
        } catch (Exception e) {
            tgtools.util.LogHelper.error("JVMInfoHelper", "error:" + e.toString(), "getJvmInfo", e);
        }
        return JvmUtil.getJvmInfo().toString();
    }

    public static void main(String[] args) {
        String fd1 = "tianjing 16958  1.8  5.0 9652976 1031384 ?     Sl   May27 156:35 /home/tianjing/wcpt-cloud/wcpt/jdk/bin/java_wcpt_core -Xms256m\n" +
                "-Xmx1024m -Djava.rmi.server.hostname=192.168.1.135 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=11341 -Dc\n" +
                "m.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -jar wcpt-webapp-3.0.0-SNAPSHOT.war --sp\n" +
                "ing.profiles.active=gs -port 1341 -contextpath / -debug true";
        String fd2 = "tianjing   767  2.2  3.6 8754968 761044 ?      Sl   Jun01  21:45 /home/tianjing/wcpt-cloud/wcpt/jdk/bin/java_wcpt_orgchat -Xms2\n" +
                "6m -Xmx512m -Djava.rmi.server.hostname=192.168.1.135 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=11348 -\n" +
                "com.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -jar wcpt-module-orgchat-webapp-3.0.0-\n" +
                "NAPSHOT.war --spring.profiles.active=gs -port 1348 -contextpath / -debug false";

        ArrayList<String> vList1 = parsePidInfo(fd1);
        ArrayList<String> vList2 = parsePidInfo(fd2);
        System.out.println(vList1);
        System.out.println(vList2);

    }

    public static ArrayList<String> parsePidInfo(String pInfo) {
        ArrayList<String> vParams = new ArrayList<>();
        if (StringUtil.isNullOrEmpty(pInfo)) {
            return vParams;
        }


        pInfo = StringUtil.replace(pInfo, "  ", " ");
        String[] vInfo = pInfo.split(" ");
        for (String vItem : vInfo) {
            if (!StringUtil.isNullOrEmpty(vItem)) {
                vParams.add(vItem);
            }
        }
        return vParams;
    }

    public static SystemProcessMemoryInfo getLinuxPidMem(int pPid) throws APPErrorException {
        String vPidInfo = getLinuxPidInfo(pPid);
        ArrayList<String> vInfos = parsePidInfo(vPidInfo);

        SystemProcessMemoryInfo vSystemProcessMemoryInfo = new SystemProcessMemoryInfo();
        if (vInfos.size() > 5) {
            vSystemProcessMemoryInfo.setVirtualMemoryUsage(Long.parseLong(vInfos.get(4)));
            vSystemProcessMemoryInfo.setResidentMemoryUsage(Long.parseLong(vInfos.get(5)));
        }
        return vSystemProcessMemoryInfo;
    }

    public static boolean isUnix() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);

    }

    public static String getLinuxPidInfo(int pPid) throws APPErrorException {
        String vPid = String.valueOf(pPid);
        String vCommand = "ps aux|grep " + vPid;
        String[] vCommands = new String[]{"/bin/sh", "-c", vCommand};
        List<String> vResult = execLinuxCommand(vCommands);
        String vPidInfo = null;
        for (String vItem : vResult) {
            if (vItem.indexOf(" " + vPid + " ") > 0) {
                vPidInfo = vItem;
                break;
            }
        }
        return vPidInfo;
    }

    public static List<String> execLinuxCommand(String[] pCommands) throws APPErrorException {
        String vLine = null;
        List<String> vResultBuilder = new ArrayList<>();
        StringBuilder vErrorBuilder = new StringBuilder();
        try {
            Process vProcess = Runtime.getRuntime().exec(pCommands);
            //由于是短命令 则不使用线程
            try (BufferedReader vInputReader = new BufferedReader
                    (new InputStreamReader(vProcess.getInputStream())); BufferedReader vErrorReader = new BufferedReader
                    (new InputStreamReader(vProcess.getErrorStream()))) {
                while ((vLine = vInputReader.readLine()) != null) {
                    vResultBuilder.add(vLine);
                }

                while ((vLine = vErrorReader.readLine()) != null) {
                    vErrorBuilder.append(vLine + "\n");
                }
            }
            vProcess.waitFor();
        } catch (Exception e) {
            throw new APPErrorException("command exec " + StringUtil.join(pCommands, " ") + " error: " + e.toString(), e);
        }

        if (vErrorBuilder.length() > 0) {
            throw new APPErrorException("command exec " + StringUtil.join(pCommands, " ") + " result error: " + vErrorBuilder.toString());
        }

        return vResultBuilder;
    }

    public static int getJvmPid() {
        try {
            RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
            String vProcessIdName = runtime.getName();
            if (vProcessIdName.indexOf("@") >= 0) {
                return Integer.parseInt(vProcessIdName.split("@")[0]);
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    public static class SystemProcessMemoryInfo {
        /**
         * 这个内存使用就是一个应用占有的地址空间，只是要应用程序要求的，就全算在这里，而不管它真的用了没有。写程序怕出错，又不在乎占用的时候，多开点内存也是很正常的。
         */
        private long VirtualMemoryUsage;
        /**
         * 常驻内存。这个值就是该应用程序真的使用的内存，但还有两个小问题，一是有些东西可能放在交换盘上了（SWAP），二是有些内存可能是共享的。
         */
        private long ResidentMemoryUsage;


        public long getVirtualMemoryUsage() {
            return VirtualMemoryUsage;
        }

        public void setVirtualMemoryUsage(long pVirtualMemoryUsage) {
            VirtualMemoryUsage = pVirtualMemoryUsage;
        }

        public long getResidentMemoryUsage() {
            return ResidentMemoryUsage;
        }

        public void setResidentMemoryUsage(long pResidentMemoryUsage) {
            ResidentMemoryUsage = pResidentMemoryUsage;
        }

        public String toStringByMB() {
            if (isUnix()) {
                return "v:" + (VirtualMemoryUsage / 1024) + "MB; r:" + (ResidentMemoryUsage / 1024) + "MB";
            }
            return "v:" + (VirtualMemoryUsage / 1024) + "MB; r:" + (ResidentMemoryUsage / 1024) + "MB";
        }
    }
}
