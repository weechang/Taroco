package io.github.weechang.moreco.base.controller;

import io.github.weechang.moreco.base.core.MorecoSecurityUtil;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 23:13.
 */
public class BaseController {

    protected String getUsername() {
        return MorecoSecurityUtil.getUsername();
    }

}
