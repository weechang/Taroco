package io.github.weechang.moreco.monitor.manager.error;

import io.github.weechang.moreco.base.error.IError;

/**
 * @author zhangwei
 * date 2018/11/27
 * time 14:21
 */
public enum MonitorManagerError implements IError {
    USER_EXISTED(1, "用户已存在");

    int code;
    String msg;
    private static final String ns = "MONITOR_MANAGER";

    MonitorManagerError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getNs() {
        return ns;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
