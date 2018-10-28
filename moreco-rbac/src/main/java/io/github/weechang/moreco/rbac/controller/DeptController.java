package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.response.R;
import io.github.weechang.moreco.base.util.PageUtil;
import io.github.weechang.moreco.rbac.domain.DeptDomain;
import io.github.weechang.moreco.rbac.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 部门管理
 *
 * @author zhangwei
 * date 2018/10/27
 * time 16:29
 */
@RequestMapping("rbac/dept")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 分页获取部门数据
     *
     * @param request 请求
     * @return 分页结果
     */
    @GetMapping("page")
    public R<PageUtil<DeptDomain>> page(PageUtil request) {
        PageUtil<DeptDomain> page = deptService.findAll(request.toPageRequest());
        return R.ok(page);
    }

    /**
     * 获取部门树状结构
     *
     * @return 树状结构
     */
    public R<List<DeptDomain>> tree() {
        return R.ok();
    }

    /**
     * 保存部门信息
     *
     * @param dept 部门信息
     * @return 保存结果
     */
    @PostMapping("save")
    public R save(DeptDomain dept) {
        deptService.save(dept);
        return R.ok();
    }

    /**
     * 删除部门
     *
     * @param id 部门id
     * @return 删除结果
     */
    @DeleteMapping("delete")
    public R delete(Long id) {
        deptService.delete(id);
        return R.ok();
    }
}
