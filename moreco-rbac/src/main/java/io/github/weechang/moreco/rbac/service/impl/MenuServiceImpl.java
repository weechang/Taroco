package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.MenuDao;
import io.github.weechang.moreco.rbac.domain.MenuDomain;
import io.github.weechang.moreco.rbac.domain.RoleDomain;
import io.github.weechang.moreco.rbac.domain.UserDomain;
import io.github.weechang.moreco.rbac.service.MenuService;
import io.github.weechang.moreco.rbac.service.RoleMenuService;
import io.github.weechang.moreco.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:16
 */
public class MenuServiceImpl extends BaseServiceImpl<MenuDao, MenuDomain> implements MenuService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleMenuService roleMenuService;


    @Override
    public List<MenuDomain> findAllByParentId(Long parentId) {
        return null;
    }

    @Override
    public List<MenuDomain> findAllByUserId(Long userId) {
        List<MenuDomain> userMenus = null;
        // 获取用户角色
        UserDomain user = userService.findOne(userId);
        if (user != null) {
            userMenus = new ArrayList<>();
            List<RoleDomain> roles = user.getRoles();
        }
        return userMenus;
    }
}
