package io.github.moreco.monitor.core.server;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 硬件信息
 *
 * @author zhangwei
 * date 2018/12/29
 * time 15:16
 */
@Data
public class HardWare implements Serializable {
    public Cpu cpu;
    public Memory memory;
    public List<Disk> disks;
    public List<NetWork> netWorks;
}
