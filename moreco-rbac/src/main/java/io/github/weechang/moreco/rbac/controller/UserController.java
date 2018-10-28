package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.controller.BaseController;
import io.github.weechang.moreco.base.response.R;
import io.github.weechang.moreco.base.util.PageUtil;
import io.github.weechang.moreco.rbac.domain.RoleDomain;
import io.github.weechang.moreco.rbac.domain.UserDomain;
import io.github.weechang.moreco.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author zhangwei
 * date 2018/10/27
 * time 16:29
 */
@RequestMapping("rbac/user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 分页获取用户数据
     *
     * @param request 请求
     * @return 分页结果
     */
    @GetMapping("page")
    public R<PageUtil<UserDomain>> page(PageUtil request) {
        PageUtil<UserDomain> page = userService.findAll(request.toPageRequest());
        return R.ok(page);
    }

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     * @return 保存结果
     */
    @PostMapping("save")
    public R save(UserDomain user) {
        userService.save(user);
        return R.ok();
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 删除结果
     */
    @DeleteMapping("delete")
    public R delete(Long id) {
        userService.delete(id);
        return R.ok();
    }
}
