package io.github.weechang.moreco.rbac.service;

import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.model.domain.RbacRoleDept;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 15:58
 */
public interface RoleDeptService extends BaseService<RbacRoleDept> {

    /**
     * 根据角色ID，获取部门ID列表
     */
    List<Long> queryDeptIdList(Long[] roleIds);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);

    /**
     * 保存角色与部门关系
     *
     * @param roleId  角色id
     * @param deptIds 部门ids
     */
    void save(Long roleId, List<Long> deptIds);
}
