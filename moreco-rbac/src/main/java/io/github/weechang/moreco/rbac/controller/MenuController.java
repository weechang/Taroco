package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.controller.BaseController;
import io.github.weechang.moreco.rbac.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:31
 */
@RequestMapping("rbac/menu")
@RestController
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;
}
