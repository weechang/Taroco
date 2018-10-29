package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.controller.BaseController;
import io.github.weechang.moreco.base.response.R;
import io.github.weechang.moreco.base.util.PageUtil;
import io.github.weechang.moreco.rbac.domain.RbacRole;
import io.github.weechang.moreco.rbac.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 *
 * @author zhangwei
 * date 2018/10/27
 * time 16:30
 */
@Api(tags = "role", description = "角色管理")
@RequestMapping("rbac/role")
@RestController
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 分页获取角色数据
     *
     * @param index 请求
     * @return 分页结果
     */
    @ApiOperation("分页获取角色数据")
    @GetMapping("page/{index}")
    public R<PageUtil<RbacRole>> page(@PathVariable("index") int index) {
        PageUtil request = new PageUtil(index);
        PageUtil<RbacRole> page = roleService.findAll(request.toPageRequest());
        return R.ok(page);
    }

    /**
     * 获取角色列表
     *
     * @return 角色列表
     */
    @ApiOperation("获取角色列表")
    @GetMapping("list")
    public R<List<RbacRole>> list() {
       List<RbacRole> list = (List<RbacRole>) roleService.findAll();
       return R.ok(list);
    }

    /**
     * 保存角色信息
     *
     * @param role 角色信息
     * @return 保存结果
     */
    @PostMapping("save")
    public R save(RbacRole role) {
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
