package io.github.weechang.moreco.monitor.sdk.util;

import io.github.weechang.moreco.monitor.core.server.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务器工具类
 *
 * @author zhangwei
 * date 2018/12/3
 * time 16:19
 */
@Slf4j
public class ServerUtil {

    /**
     * 获取服务器信息
     *
     * @return 服务器信息
     */
    public static final ServerInfo getServerInfo() {
        ServerInfo serverInfo = new ServerInfo();
        String os = System.getProperty("os.name");
        if (os != null && !os.startsWith("Windows")) {
            // 获取linux 磁盘信息
            serverInfo.setDiskInfos(getDiskInfo());
            // 获取linux top信息
            TopInfo topInfo = getTopInfo();
            if (topInfo != null) {
                serverInfo.setTaskInfo(topInfo.getTaskInfo());
                serverInfo.setCpuInfo(topInfo.getCpuInfo());
                serverInfo.setMemInfo(topInfo.getMemInfo());
                serverInfo.setSwapInfo(topInfo.getSwapInfo());
            }
        }
        return serverInfo;
    }

    /**
     * 获取top 命令信息
     *
     * @return top 信息
     */
    public static final TopInfo getTopInfo() {
        TopInfo topInfo = null;
        String os = System.getProperty("os.name");
        if (os != null && !os.startsWith("Windows")) {
            // 获取linux top信息
            topInfo = new TopInfo();
            try {
                String procCmd = "top -bn 1 -i -c";
                String data = getShellCmdInfo(procCmd);
                if (data != null) {
                    String[] datas = data.split("\n");
                    if (datas[0] != null) {
                        TaskInfo taskInfo = new TaskInfo(datas[0]);
                        topInfo.setTaskInfo(taskInfo);
                    }
                    if (datas[1] != null) {
                        CpuInfo cpuInfo = new CpuInfo(datas[1]);
                        topInfo.setCpuInfo(cpuInfo);
                    }
                    if (datas[2] != null) {
                        MemInfo memInfo = new MemInfo(datas[2]);
                        topInfo.setMemInfo(memInfo);
                    }
                    if (datas[3] != null) {
                        SwapInfo swapInfo = new SwapInfo(datas[3]);
                        topInfo.setSwapInfo(swapInfo);
                    }
                }
            } catch (Exception e) {
                log.error("获取top信息失败", e);
            }
        }
        return topInfo;
    }

    /**
     * 获取磁盘信息
     *
     * @return 磁盘信息
     */
    public static final List<DiskInfo> getDiskInfo() {
        List<DiskInfo> diskInfos = null;
        String os = System.getProperty("os.name");
        if (os != null && !os.startsWith("Windows")) {
            diskInfos = new ArrayList<DiskInfo>();
            try {
                // 获取linux 磁盘信息
                String procCmd = "df";
                String data = getShellCmdInfo(procCmd);
                if (data != null){
                    String[] datas = data.split("\n");
                    if (datas.length > 1){
                        for (int i = 1; i < datas.length; i++){
                            diskInfos.add(new DiskInfo(datas[i]));
                        }
                    }
                }
            } catch (Exception e){
                log.error("获取磁盘信息失败", e);
            }
        }
        return diskInfos;
    }


    /**
     * shell 脚本执行器
     *
     * @param procCmd shell脚本
     * @return 返回值
     * @throws IOException Io
     */
    private static String getShellCmdInfo(String procCmd) throws IOException {
        Process proc = Runtime.getRuntime().exec(procCmd);
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            int ptr;
            while ((ptr = bReader.read()) != -1) {
                sb.append((char) ptr);
            }
            String result = sb.toString();
            return result;
        } finally {
            proc.destroy();
        }
    }
}
