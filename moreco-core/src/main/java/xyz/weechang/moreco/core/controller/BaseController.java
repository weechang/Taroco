package xyz.weechang.moreco.core.controller;

import xyz.weechang.moreco.core.security.MorecoSecurityUtil;
import xyz.weechang.moreco.core.exception.AppException;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 23:13.
 */
public class BaseController {

    protected String getUsername() {
        String username = MorecoSecurityUtil.getUsername();
        if (username == null) {
            throw new AppException();
        }
        return MorecoSecurityUtil.getUsername();
    }

}
