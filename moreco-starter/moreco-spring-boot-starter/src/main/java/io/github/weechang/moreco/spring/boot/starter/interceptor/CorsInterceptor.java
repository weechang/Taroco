package io.github.weechang.moreco.spring.boot.starter.interceptor;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        corsHandle(request, response);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        corsHandle(request, response);
    }

    /**
     * 跨域处理
     *
     * @param request
     * @param response
     */
    protected void corsHandle(HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader("Origin");
        if (StringUtils.isNotBlank(origin)) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods","GET, POST, OPTIONS, DELETE");
            response.setHeader("Access-Control-Allow-Headers", "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type, Accept-Language, Origin, Accept-Encoding");
            response.setCharacterEncoding("UTF-8");
        }
    }

}

