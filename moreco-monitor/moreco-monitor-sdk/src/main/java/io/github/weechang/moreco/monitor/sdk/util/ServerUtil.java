package io.github.weechang.moreco.monitor.sdk.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;


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
     * 获取系统信息
     *
     * @return 系统信息
     */
    private static SystemInfo getSystemInfo() {
        return new SystemInfo();
    }

    /**
     * 获取硬件信息
     *
     * @return 硬件信息
     */
    public static HardwareAbstractionLayer getHardWare() {
        return getSystemInfo().getHardware();
    }

    /**
     * 获取操作系统信息
     *
     * @return 操作系统
     */
    public static OperatingSystem getSoftWare() {
        return getSystemInfo().getOperatingSystem();
    }

    public static void main(String[] args) {
        JSON json  = (JSON) JSONObject.toJSON(getSoftWare());
        System.out.println(json);
    }
}
