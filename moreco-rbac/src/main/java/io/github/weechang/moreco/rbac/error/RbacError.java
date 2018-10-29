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
    USER_EXISTED(1, "user is existed"),
    /**
     * 部门已存在
     */
    DEPT_EXISTED(2, "dept is existed"),
    /**
     * 目录已存在
     */
    MENU_EXISTED(3, "menu is existed"),
    /**
     * 角色已存在
     */
    ROLE_EXISTED(4, "role is existed"),
    ;

    int code;
    String msg;
    private static final String ns = "RBAC";

    RbacError(int code, String msg) {
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
