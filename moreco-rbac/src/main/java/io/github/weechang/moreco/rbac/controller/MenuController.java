package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.controller.BaseController;
import io.github.weechang.moreco.base.response.R;
import io.github.weechang.moreco.base.util.PageUtil;
import io.github.weechang.moreco.rbac.domain.RbacDept;
import io.github.weechang.moreco.rbac.domain.RbacMenu;
import io.github.weechang.moreco.rbac.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "menu", description = "目录管理")
@RequestMapping("rbac/menu")
@RestController
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    /**
     * 分页获取目录数据
     *
     * @param index 请求
     * @return 分页结果
     */
    @ApiOperation("分页获取目录数据")
    @GetMapping("page/{index}")
    public R<PageUtil<RbacMenu>> page(@PathVariable("index") int index) {
        PageUtil request = new PageUtil(index);
        PageUtil<RbacMenu> page = menuService.findAll(request.toPageRequest());
        return R.ok(page);
    }

    /**
     * 根据上级id获取下级列表
     *
     * @return 下级列表
     */
    @ApiOperation("分页获取目录数据")
    @GetMapping("list")
    public R<List<RbacMenu>> findAllByParentId(Long parentId) {
        List<RbacMenu> list = menuService.findAllByParentId(parentId);
        return R.ok(list);
    }

    /**
     * 获取用户授权目录树状结构
     *
     * @return 授权树状结构
     */
    @ApiOperation("分页获取目录数据")
    @GetMapping("tree")
    public R<List<RbacDept>> userTree() {
        Long userId = 1L;
        return R.ok();
    }

    /**
     * 保存目录信息
     *
     * @param menu 目录信息
     * @return 保存结果
     */
    @PostMapping("save")
    public R save(RbacMenu menu) {
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
