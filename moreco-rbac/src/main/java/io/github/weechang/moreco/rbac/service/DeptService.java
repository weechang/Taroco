package io.github.weechang.moreco.rbac.service;

import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.domain.RbacDept;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 15:56
 */
public interface DeptService extends BaseService<RbacDept> {

    /**
     * 查询子部门列表
     *
     * @param parentId 上级部门ID
     */
    List<RbacDept> findListByParentId(Long parentId);
}
