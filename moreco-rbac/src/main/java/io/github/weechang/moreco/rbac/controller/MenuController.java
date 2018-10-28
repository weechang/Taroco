package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.controller.BaseController;
import io.github.weechang.moreco.base.response.R;
import io.github.weechang.moreco.base.util.PageUtil;
import io.github.weechang.moreco.rbac.domain.DeptDomain;
import io.github.weechang.moreco.rbac.domain.MenuDomain;
import io.github.weechang.moreco.rbac.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 目录管理
 *
 * @author zhangwei
 * date 2018/10/27
 * time 16:31
 */
@RequestMapping("rbac/menu")
@RestController
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    /**
     * 分页获取目录数据
     *
     * @param request 请求
     * @return 分页结果
     */
    @GetMapping("page")
    public R<PageUtil<MenuDomain>> page(PageUtil request) {
        PageUtil<MenuDomain> page = menuService.findAll(request.toPageRequest());
        return R.ok(page);
    }

    /**
     * 获取目录树状结构
     *
     * @return 树状结构
     */
    public R<List<DeptDomain>> tree() {
        return R.ok();
    }

    /**
     * 获取用户授权目录树状结构
     *
     * @return 授权树状结构
     */
    public R<List<DeptDomain>> userTree() {
        return R.ok();
    }

    /**
     * 保存目录信息
     *
     * @param menu 目录信息
     * @return 保存结果
     */
    @PostMapping("save")
    public R save(MenuDomain menu) {
        menuService.save(menu);
        return R.ok();
    }

    /**
     * 删除目录
     *
     * @param id 目录id
     * @return 删除结果
     */
    @DeleteMapping("delete")
    public R delete(Long id) {
        menuService.delete(id);
        return R.ok();
    }
}
