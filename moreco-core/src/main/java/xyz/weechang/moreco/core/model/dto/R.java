package xyz.weechang.moreco.core.model.dto;

import xyz.weechang.moreco.core.error.IError;
import xyz.weechang.moreco.core.error.SysError;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求响应
 *
 * @author zhangwei
 * date 2018/10/27
 * time 0:32
 */
@ApiModel("请求相应")
@Data
public class R<T extends Object> {
    private static final long serialVersionUID = -6778838468426551277L;

    @ApiModelProperty("请求结果")
    private boolean success = false;
    @ApiModelProperty("namespace")
    private String ns;
    @ApiModelProperty("响应码")
    private int code;
    @ApiModelProperty("响应消息")
    private String msg = "success";
    @ApiModelProperty("结果")
    private T result;

    public R() {
    }

    public static R error() {
        return error(SysError.SYSTEM_INTERNAL_ERROR);
    }

    public static R error(IError error) {
        return error(error.getNs(), error.getCode(), error.getMsg());
    }

    public static R error(String msg) {
        return error(SysError.SYSTEM_INTERNAL_ERROR.getNs(), SysError.SYSTEM_INTERNAL_ERROR.getCode(), msg);
    }

    public static R error(String ns, int code, String msg) {
        R r = new R();
        r.ns = ns;
        r.code = code;
        r.msg = msg;
        return r;
    }

    public static R ok(Object t) {
        R r = new R();
        r.success = true;
        r.result = t;
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.success = true;
        r.msg = msg;
        return r;
    }

    public static R ok() {
        R r = new R();
        r.success = true;
        return r;
    }
}
