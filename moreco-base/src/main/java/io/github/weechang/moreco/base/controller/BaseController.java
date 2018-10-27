package io.github.weechang.moreco.base.controller;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 23:13.
 */
public class BaseController {

    protected String getCurrentUser() {
//        if (SecurityContextHolder.getContext().getAuthentication() != null) {
//            return SecurityContextHolder.getContext().getAuthentication().getName();
//        }
        return null;
    }

}
