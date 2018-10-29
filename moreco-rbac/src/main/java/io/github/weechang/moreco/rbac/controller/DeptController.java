package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.response.R;
import io.github.weechang.moreco.base.util.PageUtil;
import io.github.weechang.moreco.rbac.domain.RbacDept;
import io.github.weechang.moreco.rbac.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 部门管理
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

    /**
     * 分页获取部门数据
     *
     * @param index 页码
     * @return 分页结果
     */
    @ApiOperation("分页获取部门数据")
    @GetMapping("page/{index}")
    public R<PageUtil<RbacDept>> page(@PathVariable("index") int index) {
        PageUtil request = new PageUtil(index);
        PageUtil<RbacDept> page = deptService.findAll(request.toPageRequest());
        return R.ok(page);
    }

    /**
     * 根据上级id获取下级列表
     *
     * @return 下级列表
     */
    @ApiOperation("根据上级id获取下级列表")
    @GetMapping("list")
    public R<List<RbacDept>> list(Long parentId) {
        List<RbacDept> list = deptService.findListByParentId(parentId);
        return R.ok(list);
    }

    /**
     * 保存部门信息
     *
     * @param dept 部门信息
     * @return 保存结果
     */
    @ApiOperation("保存部门信息")
    @PostMapping("save")
    public R save(RbacDept dept) {
        deptService.save(dept);
        return R.ok();
    }

    /**
     * 删除部门
     *
     * @param id 部门id
     * @return 删除结果
     */
    @ApiOperation("删除部门")
    @DeleteMapping("delete")
    public R delete(Long id) {
        deptService.delete(id);
        return R.ok();
    }
}
