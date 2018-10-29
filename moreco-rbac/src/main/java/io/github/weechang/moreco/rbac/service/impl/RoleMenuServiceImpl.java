package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.RoleMenuDao;
import io.github.weechang.moreco.rbac.domain.RbacRoleMenu;
import io.github.weechang.moreco.rbac.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:20
 */
@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuDao, RbacRoleMenu> implements RoleMenuService {

    @Override
    public List<RbacRoleMenu> findAllByRoleId(Long id) {
        return baseDao.findAllByRoleId(id);
    }
}
