package io.github.weechang.moreco.rbac.service;

import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.domain.RbacUserRole;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:01
 */
public interface UserRoleService extends BaseService<RbacUserRole> {

    /**
     * 根据用户id 获取所有对应关系
     *
     * @param id 用户id
     * @return 所有对应关系
     */
    List<RbacUserRole> findAllByUserId(Long id);
}
