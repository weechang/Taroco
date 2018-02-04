package xyz.weechang.user.center.common.error;

import xyz.weechang.taroco.core.error.IError;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 9:53.
 */
public enum UCError implements IError {
    SUCCESS(1000, "success"),
    FAILED(9999, "failed"),
    ORG_IS_EXIST(1001, "org is exist"),
    ORG_IS_NOT_EXIST(1002, "org is not exist"),
    PARENT_ORG_NOT_EXIST(1002, "parent org not exist"),
    ROLE_IS_EXIST(1003, "role is exist"),
    USERNAME_IS_EXIST(1004, "username is exist"),
    ;
    private Integer errorCode;
    private String errorMessage;
    private static final String ns = "UserCenter-Query";

    UCError(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    UCError(Integer code){
        this.errorCode = code;
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
