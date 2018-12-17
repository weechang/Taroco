package io.github.weechang.moreco.monitor.core.server;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei
 * date 2018/12/3
 * time 13:40
 */
@Data
@NoArgsConstructor
public class CpuInfo {
    private float us;
    private float sy;
    private float ni;
    private float id;
    private float wa;
    private float hi;
    private float si;
    private float st;

    private static String getValue(String data, String t) {
        return data.replace(t, "").trim();
    }

    public CpuInfo(String data) {
        String cpuStr = data.substring("Cpu(s):".length());
        String[] datas = cpuStr.split(",");
        this.us = Float.valueOf(getValue(datas[0], "%us"));
        this.sy = Float.valueOf(getValue(datas[1], "%sy"));
        this.ni = Float.valueOf(getValue(datas[2], "%ni"));
        this.id = Float.valueOf(getValue(datas[3], "%id"));
        this.wa = Float.valueOf(getValue(datas[4], "%wa"));
        this.hi = Float.valueOf(getValue(datas[5], "%hi"));
        this.si = Float.valueOf(getValue(datas[6], "%si"));
        this.st = Float.valueOf(getValue(datas[7], "%st"));
    }
}
