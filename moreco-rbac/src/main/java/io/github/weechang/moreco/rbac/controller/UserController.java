package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.controller.BaseController;
import io.github.weechang.moreco.base.model.dto.R;
import io.github.weechang.moreco.base.model.dto.PageModel;
import io.github.weechang.moreco.rbac.model.domain.Role;
import io.github.weechang.moreco.rbac.model.domain.User;
import io.github.weechang.moreco.rbac.model.dto.UserQueryRequest;
import io.github.weechang.moreco.rbac.model.dto.UserSaveRequest;
import io.github.weechang.moreco.rbac.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:29
 */
@Api(tags = "permission", description = "用户管理")
@RequestMapping("rbac/user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation("分页获取用户数据")
    @GetMapping("page")
    public R<PageModel<User>> page(
            @ApiParam(name = "查询条件") UserQueryRequest queryRequest) {
        PageModel<User> page = userService.findAll(queryRequest.toUser(), queryRequest.toPageRequest());
        UserService.convert2String(page.getList());
        return R.ok(page);
    }

    @ApiOperation("获取详情")
    @GetMapping("detail/{id}")
    public R<Role> detail(
            @ApiParam(name = "id") @PathVariable("id") Long id) {
        User user = userService.detail(id);
        return R.ok(user);
    }

    @ApiOperation("保存用户信息")
    @PostMapping("save")
    public R save(@RequestBody UserSaveRequest request) {
        User user = request.toUser();
        userService.save(user);
        return R.ok();
    }

    @ApiOperation("修改密码")
    @PostMapping("updatePassword")
    public R updatePassword(@ApiParam("新密码") String newPassword) {
        Long userId = 0L;
        userService.updatePasswordByUserId(userId, newPassword);
        return R.ok();
    }

    @ApiOperation("重置密码")
    @PostMapping("restPassword")
    public R resetPassword() {
        Long userId = 0L;
        userService.resetPasswordByUserId(userId);
        return R.ok();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("delete/{id}")
    public R delete(@ApiParam("用户id") @PathVariable("id") Long id) {
        userService.delete(id);
        return R.ok();
    }
}
