package io.github.weechang.moreco.monitor.core.server;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei
 * date 2018/12/3
 * time 14:10
 */
@NoArgsConstructor
@Data
public class LoadInfo {

    private float oneLoad;
    private float fiveLoad;
    private float fifteenLoad;

    public LoadInfo(String data) {
        int index = data.indexOf("load average:");
        String value = data.substring(index + "load average:".length()).trim();
        String[] datas = value.split(",");
        this.oneLoad = Float.valueOf(datas[0]);
        this.fiveLoad = Float.valueOf(datas[1]);
        this.fifteenLoad = Float.valueOf(datas[2]);
    }
}
