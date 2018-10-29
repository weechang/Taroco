package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.UserRoleDao;
import io.github.weechang.moreco.rbac.domain.RbacUserRole;
import io.github.weechang.moreco.rbac.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:23
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleDao, RbacUserRole> implements UserRoleService {

    @Override
    public List<RbacUserRole> findAllByUserId(Long id) {
        return baseDao.findAllByUserId(id);
    }
}
