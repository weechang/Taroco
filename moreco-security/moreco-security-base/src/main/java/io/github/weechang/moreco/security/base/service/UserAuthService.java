package io.github.weechang.moreco.security.base.service;

import io.github.weechang.moreco.rbac.model.domain.User;

import java.util.List;

/**
 * @author zhangwei
 * date 2019/1/25
 * time 13:34
 */
public interface UserAuthService {

    /**
     * 根据账号获取登录用户
     *
     * @param account 账号
     */
    User user(String account);

    /**
     * 获取权限列表通过角色id
     *
     * @param roleId 角色id
     */
    List<String> findPermissionsByRoleId(Long roleId);

    /**
     * 根据角色id获取角色名称
     *
     * @param roleId 角色id
     */
    String findRoleNameByRoleId(Long roleId);

}
