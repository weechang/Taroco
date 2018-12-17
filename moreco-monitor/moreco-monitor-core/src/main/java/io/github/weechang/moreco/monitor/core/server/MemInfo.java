package io.github.weechang.moreco.monitor.core.server;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei
 * date 2018/12/3
 * time 13:57
 */
@NoArgsConstructor
@Data
public class MemInfo {

    private long total;
    private long used;
    private long free;
    private long buffers;

    private static String getValue(String data, String t) {
        return data.replace("k " + t, "").trim();
    }

    public MemInfo(String data) {
        String memStr = data.substring("Mem:".length());
        String[] datas = memStr.split(",");
        this.total = Long.valueOf(getValue(datas[0], "total"));
        this.used = Long.valueOf(getValue(datas[1], "used"));
        this.free = Long.valueOf(getValue(datas[2], "free"));
        this.buffers = Long.valueOf(getValue(datas[3], "buffers"));
    }
}
