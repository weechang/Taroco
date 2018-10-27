package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.controller.BaseController;
import io.github.weechang.moreco.rbac.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:29
 */
@RequestMapping("rbac/dept")
@RestController
public class DeptController extends BaseController {

    @Autowired
    private DeptService deptService;

}
