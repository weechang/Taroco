package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.RoleMenuDao;
import io.github.weechang.moreco.rbac.domain.RoleMenuDomain;
import io.github.weechang.moreco.rbac.service.RoleMenuService;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:20
 */
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuDao, RoleMenuDomain> implements RoleMenuService {

    @Override
    public List<RoleMenuDomain> findAllByRoleId(Long id) {
        return baseDao.findAllByRoleId(id);
    }
}
