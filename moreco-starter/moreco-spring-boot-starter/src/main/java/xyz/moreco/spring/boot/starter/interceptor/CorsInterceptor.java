package xyz.moreco.spring.boot.starter.interceptor;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 跨域设定，设定为cros方式跨域并限定referer为dmall、设定为json输出，设定编码，限定请求方式为get、post
 * <p>
 * 后续需遵照下面原则： 1. 安全地实现CSRF方式调用JSON文件：限制Referer、部署一次性token等。 2.
 * 按照JSON格式标准输出Content-Type及编码（Content-Type: application/json;
 * charset=utf-8）。 3. 过滤callback函数名及JSON数据的输出、限制对JSONP输出callback函数名的长度。
 */
public class CorsInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        corsHandle(req, res);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) throws Exception {
        corsHandle(req, res);
    }

    /**
     * 跨域处理
     *
     * @param req 请求
     * @param res 相应
     */
    protected void corsHandle(HttpServletRequest req, HttpServletResponse res) {
        String origin = req.getHeader("Origin");
        if (StrUtil.isNotBlank(origin)) {
            res.setHeader("Access-Control-Allow-Origin", "*");
            res.setHeader("Access-Control-Allow-Methods","GET, POST, OPTIONS, DELETE");
            res.setHeader("Access-Control-Allow-Headers", "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type, Accept-Language, Origin, Accept-Encoding");
            res.setCharacterEncoding("UTF-8");
        }
    }

}

