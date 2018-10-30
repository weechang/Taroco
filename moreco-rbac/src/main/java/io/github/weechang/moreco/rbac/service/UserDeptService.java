package io.github.weechang.moreco.rbac.service;

import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.model.domain.RbacUserDept;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:00
 */
public interface UserDeptService extends BaseService<RbacUserDept> {

    /**
     * 保存用户与部门关系
     *
     * @param userId  用户id
     * @param deptIds 部门ids
     */
    void save(Long userId, List<Long> deptIds);
}
