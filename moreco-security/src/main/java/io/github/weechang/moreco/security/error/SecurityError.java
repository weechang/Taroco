package io.github.weechang.moreco.security.error;

import io.github.weechang.moreco.base.error.IError;

/**
 * security 错误
 *
 * @author zhangwei
 * date 2019/1/26
 * time 21:11
 */
public enum SecurityError implements IError {
    ACCESS_FORBIDDEN(1, "权限不足"),
    USER_NOT_FOUNT(2, "用户不存在"),
    USER_NOT_LOGIN(3, "用户未登录"),
    LOGIN_ERROR(4, "登录失败"),
    ;

    int code;
    String msg;
    private static final String ns = "SECURITY";

    SecurityError(int code, String msg) {
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