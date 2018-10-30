package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.controller.BaseController;
import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.model.R;
import io.github.weechang.moreco.rbac.model.domain.RbacDept;
import io.github.weechang.moreco.rbac.model.domain.RbacMenu;
import io.github.weechang.moreco.rbac.model.dto.MenuSaveRequest;
import io.github.weechang.moreco.rbac.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
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

    @ApiOperation("分页获取目录数据")
    @GetMapping("page/{index}")
    public R<PageModel<RbacMenu>> page(@ApiParam(name = "页码") @PathVariable("index") int index) {
        PageModel request = new PageModel(index);
        PageModel<RbacMenu> page = menuService.findAll(request.toPageRequest());
        return R.ok(page);
    }

    @ApiOperation("根据上级id获取下级列表")
    @GetMapping("list")
    public R<List<RbacMenu>> findAllByParentId(@ApiParam(name = "上级id") Long parentId) {
        List<RbacMenu> list = menuService.findAllByParentId(parentId);
        return R.ok(list);
    }

    @ApiOperation("获取用户授权树")
    @GetMapping("tree")
    public R<List<RbacDept>> userTree() {
        Long userId = 1L;
        return R.ok();
    }

    @ApiModelProperty("保存目录信息")
    @PostMapping("save")
    public R save(MenuSaveRequest request) {
        RbacMenu menu = request.toRbacMenu();
        menuService.save(menu);
        return R.ok();
    }

    @ApiModelProperty("删除目录信息")
    @DeleteMapping("delete")
    public R delete(@ApiParam("目录id") Long id) {
        menuService.delete(id);
        return R.ok();
    }
}
