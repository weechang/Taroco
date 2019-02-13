package xyz.weechang.moreco.cloud.gateway.filter.post;

import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;
import xyz.weechang.moreco.cloud.gateway.filter.MyFilterConstants;

/**
 * xlabel标签拦截器,主要是为了XlabelHeaderInterceptor.shutdownHystrixRequestContext
 *
 * @author zhangwei
 * date 2019/2/3
 * time 22:32
 */
@Component
public class XlabelRequestPostFilter extends ZuulFilter{

    @Override
    public String filterType() {
        return MyFilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return MyFilterConstants.POST_REQUEST_XLABEL_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        XlabelMvcHeaderInterceptor.shutdownHystrixRequestContext();
        return null;
    }
}
