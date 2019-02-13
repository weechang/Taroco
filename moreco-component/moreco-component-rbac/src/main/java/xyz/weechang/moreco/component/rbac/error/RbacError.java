package xyz.weechang.moreco.component.rbac.error;

import xyz.weechang.moreco.core.error.IError;

/**
 * RBAC 错误
 *
 * @author zhangwei
 * date 2018/10/27
 * time 18:18
 */
public enum RbacError implements IError {
    USER_EXISTED(1, "用户已存在"),
    DEPT_EXISTED(2, "部门已存在"),
    MENU_EXISTED(3, "目录已存在"),
    ROLE_EXISTED(4, "角色已存在"),
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
