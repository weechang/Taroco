package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.response.R;
import io.github.weechang.moreco.rbac.domain.DeptDomain;
import io.github.weechang.moreco.rbac.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:29
 */
@RequestMapping("rbac/dept")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("page")
    public R page() {
        PageRequest request = new PageRequest(0, 10);
        Page<DeptDomain> page = deptService.findAll(request);
        return R.ok().put("page", page);
    }

}
