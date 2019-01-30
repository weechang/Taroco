package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.model.dto.PageModel;
import io.github.weechang.moreco.base.model.dto.R;
import io.github.weechang.moreco.rbac.model.domain.Dept;
import io.github.weechang.moreco.rbac.model.domain.Menu;
import io.github.weechang.moreco.rbac.model.dto.DeptQueryRequest;
import io.github.weechang.moreco.rbac.model.dto.DeptSaveReqeust;
import io.github.weechang.moreco.rbac.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @GetMapping("page")
    public R<PageModel<Dept>> page(
            @ApiParam(name = "查询条件") DeptQueryRequest queryRequest) {
        Sort sort = new Sort(Sort.Direction.ASC, "orderNum");
        PageModel<Dept> page = deptService.findAll(queryRequest.toDept(), queryRequest.toPageRequest(sort));
        DeptService.convert2String(page.getList());
        return R.ok(page);
    }

    @ApiOperation("获取详情")
    @GetMapping("/detail/{id}")
    public R<Menu> detail(
            @ApiParam(name = "id") @PathVariable("id") Long id) {
        Dept dept = deptService.findOne(id);
        return R.ok(dept);
    }

    @ApiOperation("获取树形结构")
    @GetMapping("tree")
    public R<List<Dept>> tree() {
        List<Dept> depts = deptService.tree();
        return R.ok(depts);
    }

    @ApiOperation("保存部门信息")
    @PostMapping("save")
    public R save(@RequestBody DeptSaveReqeust reqeust) {
        Dept dept = reqeust.toDept();
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
