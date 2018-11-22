package io.github.weechang.moreco.rbac.controller;

import com.google.common.collect.Maps;
import io.github.weechang.moreco.base.controller.BaseController;
import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.model.R;
import io.github.weechang.moreco.rbac.model.domain.RbacDept;
import io.github.weechang.moreco.rbac.model.domain.RbacMenu;
import io.github.weechang.moreco.rbac.model.domain.enums.MenuTypeEnum;
import io.github.weechang.moreco.rbac.model.dto.MenuSaveRequest;
import io.github.weechang.moreco.rbac.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
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

    @ApiOperation("页面初始化参数")
    @GetMapping("toPage")
    public R toPage() {
        Map<String, Object> result = Maps.newHashMap();
        result.put("menuTypes", MenuTypeEnum.toJsonArray());
        return R.okR(result);
    }

    @ApiOperation("分页获取目录数据")
    @GetMapping("page/{parentId}/{index}")
    public R<PageModel<RbacMenu>> page(
            @ApiParam(name = "上级id") @PathVariable("parentId") Long parentId,
            @ApiParam(name = "页码") @PathVariable("index") int index) {
        PageModel request = new PageModel(index);
        Sort sort = new Sort(Sort.Direction.ASC, "orderNum");
        PageModel<RbacMenu> page = menuService.findAllByParentId(parentId, request.toPageRequest(sort));
        MenuService.convert2String(page.getList());
        return R.ok(page);
    }

    @ApiOperation("获取详情")
    @GetMapping("/detail/{id}")
    public R<RbacMenu> detail(
            @ApiParam(name = "id") @PathVariable("id") Long id) {
        RbacMenu menu = menuService.findOne(id);
        return R.ok(menu);
    }

    @ApiOperation("获取树形结构")
    @GetMapping("tree")
    public R<List<RbacMenu>> tree() {
        List<RbacMenu> menus = menuService.tree();
        return R.ok(menus);
    }

    @ApiModelProperty("保存目录信息")
    @PostMapping("save")
    public R save(@RequestBody MenuSaveRequest request) {
        RbacMenu menu = request.toRbacMenu();
        menuService.save(menu);
        return R.ok();
    }

    @ApiModelProperty("删除目录信息")
    @DeleteMapping("delete/{id}")
    public R delete(@ApiParam("目录id") @PathVariable("id") Long id) {
        menuService.delete(id);
        return R.ok();
    }
}
