package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.controller.BaseController;
import io.github.weechang.moreco.base.response.R;
import io.github.weechang.moreco.base.util.PageUtil;
import io.github.weechang.moreco.rbac.domain.RbacUser;
import io.github.weechang.moreco.rbac.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author zhangwei
 * date 2018/10/27
 * time 16:29
 */
@Api(tags = "user", description = "用户管理")
@RequestMapping("rbac/user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 分页获取用户数据
     *
     * @param index 请求
     * @return 分页结果
     */
    @ApiOperation("分页获取用户数据")
    @GetMapping("page/{index}")
    public R<PageUtil<RbacUser>> page(@PathVariable("index") int index) {
        PageUtil request = new PageUtil(index);
        PageUtil<RbacUser> page = userService.findAll(request.toPageRequest());
        return R.ok(page);
    }

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     * @return 保存结果
     */
    @ApiOperation("保存用户信息")
    @PostMapping("save")
    public R save(RbacUser user) {
        userService.save(user);
        return R.ok();
    }

    /**
     * 保存用户角色信息
     *
     * @return 保存结果
     */
    @ApiOperation("保存用户角色信息")
    @PostMapping("saveUserRoles")
    public R saveUserRoles() {
        return R.ok();
    }

    /**
     * 保存用户部门信息
     *
     * @return 保存结果
     */
    @ApiOperation("保存用户部门信息")
    @PostMapping("saveUserDepts")
    public R saveUserDepts() {
        return R.ok();
    }

    /**
     * 修改密码
     *
     * @return 修改结果
     */
    @ApiOperation("修改密码")
    @PostMapping("updatePassword")
    public R updatePassword(String newPassword) {
        Long userId = 0L;
        userService.updatePasswordByUserId(userId, newPassword);
        return R.ok();
    }

    /**
     * 重置密码
     *
     * @return 重置结果
     */
    @ApiOperation("重置密码")
    @PostMapping("restPassword")
    public R resetPassword() {
        Long userId = 0L;
        userService.resetPasswordByUserId(userId);
        return R.ok();
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 删除结果
     */
    @ApiOperation("删除用户")
    @DeleteMapping("delete")
    public R delete(Long id) {
        userService.delete(id);
        return R.ok();
    }
}
