package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.exception.AppException;
import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.RoleDao;
import io.github.weechang.moreco.rbac.dao.RoleMenuDao;
import io.github.weechang.moreco.rbac.model.domain.Role;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.model.domain.RoleMenu;
import io.github.weechang.moreco.rbac.service.RoleMenuService;
import io.github.weechang.moreco.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:20
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, Role> implements RoleService {

    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public Role save(Role role) {
        Role saved = baseDao.findFirstByName(role.getName());
        if (saved != null) {
            if (role.getId() == null || !role.getId().equals(saved.getId())) {
                throw new AppException(RbacError.ROLE_EXISTED);
            }
        }
        role = super.save(role);
        roleMenuService.save(role.getId(), role.getMenuIdList());
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
        Role role = baseDao.findOne(id);
        if (role != null) {
            List<Long> menuIds = roleMenuDao.findAllByRoleId(id).stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
            role.setMenuIdList(menuIds);
        }
        return role;
    }
}
