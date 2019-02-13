package xyz.weechang.moreco.core.error;


/**
 * 说明：
 *
 * @author zhangwei
 * @date 2017年11月18日23:45:26
 */
public enum SysError implements IError {
    SYSTEM_INTERNAL_ERROR(500, "系统内部错误"),
    INVALID_PARAMETER(1, "无效参数"),
    SERVICE_NOT_FOUND(2, "服务不存在"),
    PARAMETER_REQUIRED(3, "参数不全"),
    PARAMETER_MAX_LENGTH(4, "参数过长"),
    PARAMETER_MIN_LENGTH(5, "参数过短"),
    PARAMETER_ANNOTATION_NOT_MATCH(6, "参数出错"),
    PARAMETER_NOT_MATCH_RULE(7, "参数验证失败"),
    METHOD_NOT_SUPPORTED(8, "请求方法出错"),
    CONTENT_TYPE_NOT_SUPPORT(9, "不支持的content类型"),
    JSON_FORMAT_ERROR(10, "json格式化出错"),
    CALL_REMOTE_ERROR(11, "远程调用出错");

    private static final String ns = "SYS";
    int code;
    String msg;

    SysError(int code, String msg) {
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
