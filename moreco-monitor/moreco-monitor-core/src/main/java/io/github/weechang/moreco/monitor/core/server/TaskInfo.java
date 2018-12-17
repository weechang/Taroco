package io.github.weechang.moreco.monitor.core.server;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei
 * date 2018/12/3
 * time 14:04
 */
@NoArgsConstructor
@Data
public class TaskInfo {

    private int total;
    private int running;
    private int sleeping;
    private int stopped;
    private int zombie;

    private static String getValue(String data, String t) {
        return data.replace(" " + t, "").trim();
    }

    public TaskInfo(String data) {
        String taskStr = data.substring("Tasks:".length());
        String[] datas = taskStr.split(",");
        this.total = Integer.valueOf(getValue(datas[0], "total"));
        this.running = Integer.valueOf(getValue(datas[1], "running"));
        this.sleeping = Integer.valueOf(getValue(datas[2], "sleeping"));
        this.stopped = Integer.valueOf(getValue(datas[3], "stopped"));
        this.zombie = Integer.valueOf(getValue(datas[4], "zombie"));
    }
}
