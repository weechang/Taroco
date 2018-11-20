package io.github.weechang.moreco.base.model;

import com.google.common.collect.Maps;
import io.github.weechang.moreco.base.error.SysError;
import io.github.weechang.moreco.base.error.IError;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

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

    @ApiModelProperty("namespace")
    private String ns;
    @ApiModelProperty("响应码")
    private int code;
    @ApiModelProperty("响应消息")
    private String msg = "success";
    @ApiModelProperty("结果")
    private T result;
    @ApiModelProperty("扩展信息")
    private Map<String, Object> ext = Maps.newHashMap();

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
        r.result = t;
        return r;
    }

    public static R okR(Object t) {
        R r = new R();
        r.result = t;
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.getExt().putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        ext.put(key, value);
        return this;
    }
}
