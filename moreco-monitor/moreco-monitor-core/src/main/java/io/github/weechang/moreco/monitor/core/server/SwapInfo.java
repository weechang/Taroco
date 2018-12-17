package io.github.weechang.moreco.monitor.core.server;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei
 * date 2018/12/3
 * time 14:07
 */
@NoArgsConstructor
@Data
public class SwapInfo {

    private long total;
    private long used;
    private long free;
    private long cached;

    private static String getValue(String data, String t) {
        return data.replace("k " + t, "").trim();
    }

    public SwapInfo(String data) {
        String swapStr = data.substring("Swap:".length());
        String[] datas = swapStr.split(",");
        this.total = Long.valueOf(getValue(datas[0], "total"));
        this.used = Long.valueOf(getValue(datas[1], "used"));
        this.free = Long.valueOf(getValue(datas[2], "free"));
        this.cached = Long.valueOf(getValue(datas[3], "cached"));
    }
}
