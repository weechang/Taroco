package xyz.weechang.moreco.cloud.gateway.filter.pre;

import cn.hutool.core.io.IoUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;

import static xyz.weechang.moreco.cloud.gateway.filter.MyFilterConstants.PRE_REQUEST_LOG_ORDER;
import static xyz.weechang.moreco.cloud.gateway.filter.MyFilterConstants.PRE_TYPE;


/**
 * 请求日志记录
 *
 * @author liuht
 * @date 2017/12/17
 */
@Component
public class PreRequestLogFilter extends ZuulFilter {

    private static final Log log = LogFactory.getLog(PreRequestLogFilter.class);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_REQUEST_LOG_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletRequest request = ctx.getRequest();
        log.info(String.format("send %s request to %s", request.getMethod(), request.getRequestURL().toString()));
        return null;
    }

    /**
     * 获取请求body
     */
    private String queryBody(HttpServletRequest request) {
        try {
            ServletInputStream stream = request.getInputStream();
            if (stream != null) {
                return IoUtil.read(stream, Charset.defaultCharset());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取get 参数
     */
    private String queryParam(HttpServletRequest request) {
        StringBuilder params = new StringBuilder();
        final Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            params.append(name);
            params.append("=");
            if ("password".equals(name)) {
                params.append("******");
            } else {
                params.append(request.getParameter(name));
            }
            params.append("&");
        }
        if (params.length() > 0) {
            params.delete(params.length()-1, params.length());
            return params.toString();
        }
        return null;
    }
}
