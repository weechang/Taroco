package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.controller.BaseController;
import io.github.weechang.moreco.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:30
 */
@RequestMapping("rbac/role")
@RestController
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;
}
