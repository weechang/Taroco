package io.github.weechang.moreco.rbac.error;

import io.github.weechang.moreco.base.error.IError;

/**
 * RBAC 错误
 *
 * @author zhangwei
 * date 2018/10/27
 * time 18:18
 */
public enum RbacError implements IError {
    /**
     * 用户已存在
     */
    USER_EXISTED("0001", "user is existed"),

    ;

    String errorCode;
    String errorMessage;
    private static final String ns = "RBAC";

    RbacError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    RbacError(String errorCode, String errorMessage, String zh_errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getNameSpace() {
        return ns;
    }

    @Override
    public String getErrorCode() {
        return ns + "." + this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
