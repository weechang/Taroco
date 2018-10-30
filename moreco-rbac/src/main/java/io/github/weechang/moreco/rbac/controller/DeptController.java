package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.model.R;
import io.github.weechang.moreco.rbac.model.domain.RbacDept;
import io.github.weechang.moreco.rbac.model.dto.DeptSaveReqeust;
import io.github.weechang.moreco.rbac.service.DeptService;
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
 * time 16:29
 */
@Api(tags = "dept", description = "部门管理")
@RequestMapping("rbac/dept")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @ApiOperation("分页获取部门数据")
    @GetMapping("page/{index}")
    public R<PageModel<RbacDept>> page(@ApiParam(name = "页码") @PathVariable("index") int index) {
        PageModel request = new PageModel(index);
        PageModel<RbacDept> page = deptService.findAll(request.toPageRequest());
        return R.ok(page);
    }

    @ApiOperation("根据上级id获取下级列表")
    @GetMapping("list")
    public R<List<RbacDept>> list(@ApiParam(name = "上级id") Long parentId) {
        List<RbacDept> list = deptService.findListByParentId(parentId);
        return R.ok(list);
    }

    @ApiOperation("保存部门信息")
    @PostMapping("save")
    public R save(DeptSaveReqeust reqeust) {
        RbacDept dept = reqeust.toRbacDept();
        deptService.save(dept);
        return R.ok();
    }

    @ApiOperation("删除部门")
    @DeleteMapping("delete")
    public R delete(@ApiParam("部门id") Long id) {
        deptService.delete(id);
        return R.ok();
    }
}
