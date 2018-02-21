package xyz.weechang.user.center.error;

import xyz.weechang.taroco.core.common.error.IError;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 9:53.
 */
public enum UCError implements IError {
    ORG_IS_EXIST("1001", "org is exist"),
    ORG_IS_NOT_EXIST("1002", "org is not exist"),
    MENU_IS_EXIST("1003", "menu is exist"),
    MENU_IS_NOT_EXIST("1004", "menu is not exist"),
    ROLE_IS_EXIST("1005", "role is exist"),
    ROLE_IS_NOT_EXIST("1006", "role is not exist"),
    USER_IS_EXIST("1007", "user is exist"),

    ;
    private String errorCode;
    private String errorMessage;
    private static final String ns = "UserCenter-Query";

    UCError(String errorCode, String errorMessage) {
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
