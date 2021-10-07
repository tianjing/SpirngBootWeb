package com.github.tianjing.tgtools.web.security.autoconfigure.util.jvm;

import java.lang.management.*;
import java.util.List;

/**
 * @author 田径
 * @date 2021-10-04 21:58
 * @desc
 **/
public class JvmUtil {
    private static MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
    private static List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
    private static ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    public JvmUtil() {
    }

    public static void main(String[] args) {
        System.out.println("totalMemory:" + Runtime.getRuntime().totalMemory() / 1024L / 1024L);
        System.out.println("maxMemory:" + Runtime.getRuntime().maxMemory() / 1024L / 1024L);
        System.out.println("freeMemory:" + Runtime.getRuntime().freeMemory() / 1024L / 1024L);
        JvmUtil.JvmInfoEntity vInfo = getJvmInfo();
        List<MemoryPoolMXBean> vlist = ManagementFactory.getMemoryPoolMXBeans();
        System.out.println("");
    }

    public static JvmUtil.JvmInfoEntity getJvmInfo() {
        JvmUtil.JvmInfoEntity vMemoryInfoEntity = new JvmUtil.JvmInfoEntity();
        vMemoryInfoEntity.setHeapMemory(memoryMXBean.getHeapMemoryUsage());
        vMemoryInfoEntity.setNoneHeapMemory(memoryMXBean.getNonHeapMemoryUsage());
        vMemoryInfoEntity.setAllGc(garbageCollectorMXBeans);
        vMemoryInfoEntity.setThreadCount(threadMXBean.getThreadCount());
        return vMemoryInfoEntity;
    }

    public static class JvmInfoEntity {
        private MemoryUsage heapMemory;
        private MemoryUsage noneHeapMemory;
        private List<GarbageCollectorMXBean> allGc;
        private int threadCount;

        public JvmInfoEntity() {
        }

        protected static double getMB(long pData) {
            return Double.valueOf((double) (pData / 1024L / 1024L));
        }

        public MemoryUsage getHeapMemory() {
            return this.heapMemory;
        }

        public void setHeapMemory(MemoryUsage heapMemory) {
            this.heapMemory = heapMemory;
        }

        public MemoryUsage getNoneHeapMemory() {
            return this.noneHeapMemory;
        }

        public void setNoneHeapMemory(MemoryUsage noneHeapMemory) {
            this.noneHeapMemory = noneHeapMemory;
        }

        public List<GarbageCollectorMXBean> getAllGc() {
            return this.allGc;
        }

        public void setAllGc(List<GarbageCollectorMXBean> allGc) {
            this.allGc = allGc;
        }

        public int getThreadCount() {
            return this.threadCount;
        }

        public void setThreadCount(int threadCount) {
            this.threadCount = threadCount;
        }

        public String toString() {
            StringBuilder vBuild = new StringBuilder();
            vBuild.append("heapMemory:");
            vBuild.append(getMB(this.heapMemory.getUsed()));
            vBuild.append("/");
            vBuild.append(getMB(this.heapMemory.getMax()));
            vBuild.append("(");
            vBuild.append(getMB(this.heapMemory.getCommitted()));
            vBuild.append("/");
            vBuild.append(getMB(this.heapMemory.getInit()));
            vBuild.append(")");
            vBuild.append(";;");
            vBuild.append("no-heapMemory:");
            vBuild.append(getMB(this.noneHeapMemory.getUsed()));
            vBuild.append("/");
            vBuild.append(getMB(this.noneHeapMemory.getMax()));
            vBuild.append("(");
            vBuild.append(getMB(this.noneHeapMemory.getCommitted()));
            vBuild.append("/");
            vBuild.append(getMB(this.noneHeapMemory.getInit()));
            vBuild.append(")");
            vBuild.append(";;");
            vBuild.append("thread:");
            vBuild.append(this.threadCount);
            vBuild.append(";;");
            this.allGc.stream().forEach((vItem) -> {
                vBuild.append(vItem.getName());
                vBuild.append(":");
                vBuild.append(vItem.getCollectionCount());
                vBuild.append(":");
                vBuild.append(vItem.getCollectionTime());
                vBuild.append(";;");
            });
            return vBuild.toString();
        }
    }
}
