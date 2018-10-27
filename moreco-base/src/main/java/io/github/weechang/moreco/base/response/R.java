package io.github.weechang.moreco.base.response;

import io.github.weechang.moreco.base.error.SysError;
import io.github.weechang.moreco.base.error.IError;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求响应
 *
 * @author zhangwei
 * date 2018/10/27
 * time 0:32
 */
public class R extends HashMap<String, Object> {

    private static final long serialVersionUID = -6778838468426551277L;

    public R() {
        put("code", 0);
        put("msg", "success");
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
        r.put("ns", ns);
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
