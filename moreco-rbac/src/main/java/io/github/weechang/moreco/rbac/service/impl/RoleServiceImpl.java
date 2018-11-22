package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.exception.BusinessException;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.RoleDao;
import io.github.weechang.moreco.rbac.dao.RoleMenuDao;
import io.github.weechang.moreco.rbac.model.domain.RbacRole;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.model.domain.RbacRoleMenu;
import io.github.weechang.moreco.rbac.service.RoleMenuService;
import io.github.weechang.moreco.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:20
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, RbacRole> implements RoleService {

    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public RbacRole save(RbacRole role) {
        RbacRole saved = baseDao.findFirstByName(role.getName());
        if (saved != null) {
            if (role.getId() == null || !role.getId().equals(saved.getId())) {
                throw new BusinessException(RbacError.ROLE_EXISTED);
            }
        }
        role = super.save(role);
        roleMenuService.save(role.getId(), role.getMenuIdList());
        return role;
    }

    @Override
    public RbacRole detail(Long id) {
        RbacRole role = baseDao.findOne(id);
        if (role != null) {
            List<Long> menuIds = roleMenuDao.findAllByRoleId(id).stream().map(RbacRoleMenu::getMenuId).collect(Collectors.toList());
            role.setMenuIdList(menuIds);
        }
        return role;
    }
}
