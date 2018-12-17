package io.github.weechang.moreco.monitor.core.server;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei
 * date 2018/12/3
 * time 15:04
 */
@NoArgsConstructor
@Data
public class DiskInfo {

    private String filesystem;
    private long blocks;
    private long used;
    private long available;
    private float use;
    private String mounted;

    public DiskInfo(String data) {
        String[] ss = data.split("\\s+");
        this.filesystem = ss[0];
        this.blocks = Long.parseLong(ss[1]);
        this.used = Long.parseLong(ss[2]);
        this.available = Long.parseLong(ss[3]);
        this.use = Float.valueOf(ss[4].replaceAll("%", ""));
        this.mounted = ss[5];
    }
}
