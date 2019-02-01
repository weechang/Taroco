package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.exception.AppException;
import io.github.weechang.moreco.base.model.dto.PageModel;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.RoleDao;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.model.domain.Role;
import io.github.weechang.moreco.rbac.service.RoleService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:20
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, Role> implements RoleService {

    @Override
    public Role save(Role role) {
        Role saved = baseDao.findFirstByName(role.getName());
        if (saved != null) {
            if (role.getId() == null || !role.getId().equals(saved.getId())) {
                throw new AppException(RbacError.ROLE_EXISTED);
            }
        }
        role = super.save(role);
        return role;
    }

    @Override
    public PageModel<Role> findAll(Role param, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name" ,ExampleMatcher.GenericPropertyMatchers.contains());
        return new PageModel<>( baseDao.findAll(Example.of(param, matcher), pageable));
    }

    @Override
    public Role detail(Long id) {
        return  baseDao.findOne(id);
    }
}
