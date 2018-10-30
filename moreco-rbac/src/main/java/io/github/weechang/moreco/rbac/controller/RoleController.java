package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.controller.BaseController;
import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.model.R;
import io.github.weechang.moreco.rbac.model.domain.RbacRole;
import io.github.weechang.moreco.rbac.model.dto.RoleSaveRequest;
import io.github.weechang.moreco.rbac.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
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

    @ApiOperation("分页获取角色数据")
    @GetMapping("page/{index}")
    public R<PageModel<RbacRole>> page(@ApiParam(name = "页码") @PathVariable("index") int index) {
        PageModel request = new PageModel(index);
        PageModel<RbacRole> page = roleService.findAll(request.toPageRequest());
        return R.ok(page);
    }

    @ApiOperation("获取角色列表")
    @GetMapping("list")
    public R<List<RbacRole>> list() {
       List<RbacRole> list = (List<RbacRole>) roleService.findAll();
       return R.ok(list);
    }

    @ApiOperation("保存角色信息")
    @PostMapping("save")
    public R save(RoleSaveRequest request) {
        RbacRole role = request.toRbacMenu();
        roleService.save(role);
        return R.ok();
    }

    @ApiOperation("删除角色")
    @DeleteMapping("delete")
    public R delete(@ApiParam("角色id") Long id) {
        roleService.delete(id);
        return R.ok();
    }
}
