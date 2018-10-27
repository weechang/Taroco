package io.github.weechang.moreco.base.error;


/**
 * 说明：
 *
 * @author zhangwei
 * @date 2017年11月18日23:45:26
 */
public enum SysError implements IError {
    /**
     * 系统内部错误
     */
    SYSTEM_INTERNAL_ERROR(500, "System Internal Error"),
    /**
     * 无效参数
     */
    INVALID_PARAMETER(1, "Invalid Parameter"),
    /**
     * 服务不存在
     */
    SERVICE_NOT_FOUND(2, "Service Not Found"),
    /**
     * 参数不全
     */
    PARAMETER_REQUIRED(3, "Parameter required"),
    /**
     * 参数过长
     */
    PARAMETER_MAX_LENGTH(4, "Parameter max length limit"),
    /**
     * 参数过短
     */
    PARAMETER_MIN_LENGTH(5, "Parameter min length limit"),
    /**
     * 参数出错
     */
    PARAMETER_ANNOTATION_NOT_MATCH(6, "Parameter annotation not match"),
    /**
     * 参数验证失败
     */
    PARAMETER_NOT_MATCH_RULE(7, "Parameter not match validation rule"),
    /**
     * 请求方法出错
     */
    METHOD_NOT_SUPPORTED(8, "method not supported"),
    /**
     * 不支持的content类型
     */
    CONTENT_TYPE_NOT_SUPPORT(9, "content type is not support"),
    /**
     * json格式化出错
     */
    JSON_FORMAT_ERROR(10, "json format error"),
    /**
     * 远程调用出错
     */
    CALL_REMOTE_ERROR(11, "call remote error");

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
