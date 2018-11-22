package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.model.R;
import io.github.weechang.moreco.rbac.model.domain.RbacDept;
import io.github.weechang.moreco.rbac.model.domain.RbacMenu;
import io.github.weechang.moreco.rbac.model.domain.enums.MenuTypeEnum;
import io.github.weechang.moreco.rbac.model.dto.DeptSaveReqeust;
import io.github.weechang.moreco.rbac.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:29
 */
@Api(tags = "dept", description = "部门管理")
@RequestMapping("rbac/dept")
@RestController
@CrossOrigin
public class DeptController {

    @Autowired
    private DeptService deptService;

    @ApiOperation("分页获取部门数据")
    @GetMapping("page/{parentId}/{index}")
    public R<PageModel<RbacDept>> page(
            @ApiParam(name = "上级id") @PathVariable("parentId") Long parentId,
            @ApiParam(name = "页码") @PathVariable("index") int index) {
        PageModel request = new PageModel(index);
        Sort sort = new Sort(Sort.Direction.ASC, "orderNum");
        PageModel<RbacDept> page = deptService.findAllByParentId(parentId, request.toPageRequest(sort));
        DeptService.convert2String(page.getList());
        return R.ok(page);
    }

    @ApiOperation("获取详情")
    @GetMapping("/detail/{id}")
    public R<RbacMenu> detail(
            @ApiParam(name = "id") @PathVariable("id") Long id) {
        RbacDept dept = deptService.findOne(id);
        return R.ok(dept);
    }

    @ApiOperation("获取树形结构")
    @GetMapping("tree")
    public R<List<RbacDept>> tree() {
        List<RbacDept> depts = deptService.tree();
        return R.ok(depts);
    }

    @ApiOperation("保存部门信息")
    @PostMapping("save")
    public R save(@RequestBody DeptSaveReqeust reqeust) {
        RbacDept dept = reqeust.toRbacDept();
        deptService.save(dept);
        return R.ok();
    }

    @ApiOperation("删除部门")
    @DeleteMapping("delete/{id}")
    public R delete(@ApiParam("部门id") @PathVariable("id") Long id) {
        deptService.delete(id);
        return R.ok();
    }
}
