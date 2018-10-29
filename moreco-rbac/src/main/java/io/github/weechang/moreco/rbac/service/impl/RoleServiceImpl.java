package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.exception.BusinessException;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.RoleDao;
import io.github.weechang.moreco.rbac.domain.RbacMenu;
import io.github.weechang.moreco.rbac.domain.RbacRole;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:20
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, RbacRole> implements RoleService {

    @Override
    public RbacRole save(RbacRole role) {
        RbacRole saved = baseDao.findFirstByName(role.getName());
        if (saved != null){
            throw new BusinessException(RbacError.ROLE_EXISTED);
        }
        return super.save(role);
    }
}
