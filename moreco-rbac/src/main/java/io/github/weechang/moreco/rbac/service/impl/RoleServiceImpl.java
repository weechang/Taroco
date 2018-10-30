package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.exception.BusinessException;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.RoleDao;
import io.github.weechang.moreco.rbac.domain.RbacMenu;
import io.github.weechang.moreco.rbac.domain.RbacRole;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.service.RoleDeptService;
import io.github.weechang.moreco.rbac.service.RoleMenuService;
import io.github.weechang.moreco.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:20
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, RbacRole> implements RoleService {

    @Autowired
    private RoleDeptService roleDeptService;
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public RbacRole save(RbacRole role) {
        RbacRole saved = baseDao.findFirstByName(role.getName());
        if (saved != null){
            if (role.getId() == null || !role.getId().equals(saved.getId())){
                throw new BusinessException(RbacError.ROLE_EXISTED);
            }
        }
        roleDeptService.save(role.getId(), role.getDeptIdList());
        roleMenuService.save(role.getId(), role.getMenuIdList());
        return super.save(role);
    }
}
