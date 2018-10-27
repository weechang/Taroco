package io.github.weechang.moreco.rbac.service;

import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.domain.MenuDomain;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 15:57
 */
public interface MenuService extends BaseService<MenuDomain> {

    /**
     * 根据父Id 查询所有子列表
     *
     * @param parentId 父Id
     * @return 子列表
     */
    List<MenuDomain> findAllByParentId(Long parentId);

    /**
     * 根据用户id 获取用户目录
     *
     * @param userId 用户id
     * @return 用户目录
     */
    List<MenuDomain> findAllByUserId(Long userId);
}
