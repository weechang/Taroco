package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.controller.BaseController;
import io.github.weechang.moreco.base.response.R;
import io.github.weechang.moreco.base.util.PageUtil;
import io.github.weechang.moreco.rbac.domain.MenuDomain;
import io.github.weechang.moreco.rbac.domain.RoleDomain;
import io.github.weechang.moreco.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理
 *
 * @author zhangwei
 * date 2018/10/27
 * time 16:30
 */
@RequestMapping("rbac/role")
@RestController
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 分页获取角色数据
     *
     * @param request 请求
     * @return 分页结果
     */
    @GetMapping("page")
    public R<PageUtil<RoleDomain>> page(PageUtil request) {
        PageUtil<RoleDomain> page = roleService.findAll(request.toPageRequest());
        return R.ok(page);
    }

    /**
     * 保存角色信息
     *
     * @param role 角色信息
     * @return 保存结果
     */
    @PostMapping("save")
    public R save(RoleDomain role) {
        roleService.save(role);
        return R.ok();
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return 删除结果
     */
    @DeleteMapping("delete")
    public R delete(Long id) {
        roleService.delete(id);
        return R.ok();
    }
}
