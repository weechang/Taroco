package xyz.weechang.moreco.cloud.gateway.filter;

import cn.taroco.gateway.filter.pre.PreRequestLogFilter;
import cn.taroco.gateway.filter.pre.XlabelRequestPreFilter;
import com.netflix.zuul.ZuulFilter;

/**
 *
 * @author liuht
 * @date 2017/12/17
 */
public interface MyFilterConstants {

    // ORDER constant -----------------------------------

    /**
     * Filter Order for {@link XlabelRequestPreFilter#filterOrder()}
     */
    int PRE_REQUEST_XLABEL_ORDER = 7;

    /**
     * Filter Order for {@link PreRequestLogFilter#filterOrder()}
     */
    int PRE_REQUEST_LOG_ORDER = Integer.MAX_VALUE;

    /**
     * Filter Order for {@link PreRequestLogFilter#filterOrder()}
     */
    int POST_REQUEST_XLABEL_ORDER = 0;

    // Zuul Filter TYPE constant -----------------------------------

    /**
     * {@link ZuulFilter#filterType()} error type.
     */
    String ERROR_TYPE = "error";

    /**
     * {@link ZuulFilter#filterType()} post type.
     */
    String POST_TYPE = "post";

    /**
     * {@link ZuulFilter#filterType()} pre type.
     */
    String PRE_TYPE = "pre";

    /**
     * {@link ZuulFilter#filterType()} route type.
     */
    String ROUTE_TYPE = "route";

}
