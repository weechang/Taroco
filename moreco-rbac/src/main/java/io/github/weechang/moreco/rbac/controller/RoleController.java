package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.controller.BaseController;
import io.github.weechang.moreco.base.model.dto.PageModel;
import io.github.weechang.moreco.base.model.dto.R;
import io.github.weechang.moreco.rbac.model.domain.Role;
import io.github.weechang.moreco.rbac.model.dto.RoleQueryRequest;
import io.github.weechang.moreco.rbac.model.dto.RoleSaveRequest;
import io.github.weechang.moreco.rbac.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
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
    @GetMapping("page")
    public R<PageModel<Role>> page(
            @ApiParam(name = "查询参数") RoleQueryRequest queryRequest) {
        PageModel<Role> page = roleService.findAll(queryRequest.toRole(), queryRequest.toPageRequest());
        RoleService.convert2String(page.getList());
        return R.ok(page);
    }

    @ApiOperation("获取所有角色")
    @GetMapping("list")
    public R<List<Role>> list() {
        List<Role> list = (List<Role>) roleService.findAll();
        return R.ok(list);
    }

    @ApiOperation("获取详情")
    @GetMapping("/detail/{id}")
    public R<Role> detail(
            @ApiParam(name = "id") @PathVariable("id") Long id) {
        Role role = roleService.detail(id);
        return R.ok(role);
    }

    @ApiOperation("保存角色信息")
    @PostMapping("save")
    public R save(@RequestBody RoleSaveRequest request) {
        Role role = request.toRole();
        roleService.save(role);
        return R.ok();
    }

    @ApiOperation("删除角色")
    @DeleteMapping("delete/{id}")
    public R delete(@ApiParam("角色id") @PathVariable("id") Long id) {
        roleService.delete(id);
        return R.ok();
    }
}
