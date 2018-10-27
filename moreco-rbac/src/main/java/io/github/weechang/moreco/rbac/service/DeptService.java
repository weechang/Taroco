package io.github.weechang.moreco.rbac.service;

import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.domain.DeptDomain;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 15:56
 */
public interface DeptService extends BaseService<DeptDomain> {

    /**
     * 查询子部门ID列表
     *
     * @param parentId 上级部门ID
     */
    List<Long> queryDeptIdList(Long parentId);

    /**
     * 获取子部门ID，用于数据过滤
     */
    List<Long> getSubDeptIdList(Long deptId);
}
