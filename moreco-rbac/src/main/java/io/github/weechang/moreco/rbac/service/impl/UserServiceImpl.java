package io.github.weechang.moreco.rbac.service.impl;

import com.google.common.collect.Lists;
import io.github.weechang.moreco.base.exception.BusinessException;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.UserDao;
import io.github.weechang.moreco.rbac.domain.RbacRoleMenu;
import io.github.weechang.moreco.rbac.domain.RbacUser;
import io.github.weechang.moreco.rbac.domain.RbacUserRole;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.service.RoleMenuService;
import io.github.weechang.moreco.rbac.service.UserRoleService;
import io.github.weechang.moreco.rbac.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:24
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, RbacUser> implements UserService {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<Long> findAllMenuIdByUserId(Long id) {
        // 先查询用户有哪些角色，再根据角色来获取目录id
        List<Long> userMenuIds = Lists.newArrayList();
        List<RbacRoleMenu> roleMenus = Lists.newArrayList();
        // 查询用户有哪些角色
        List<RbacUserRole> userRoles = userRoleService.findAllByUserId(id);
        if (CollectionUtils.isNotEmpty(userRoles)) {
            // 获取所有角色有哪些目录
            for (RbacUserRole userRole : userRoles) {
                roleMenus.addAll(roleMenuService.findAllByRoleId(userRole.getRoleId()));
            }
        }

        for (RbacRoleMenu roleMenu : roleMenus) {
            userMenuIds.add(roleMenu.getMenuId());
        }
        return userMenuIds;
    }

    @Override
    public void updatePasswordByUserId(Long id, String newPassword) {
        RbacUser user = baseDao.findOne(id);
        if (user != null) {
            user.setPassword(newPassword);
            baseDao.save(user);
        }
    }

    @Override
    public void resetPasswordByUserId(Long id) {
        RbacUser user = baseDao.findOne(id);
        if (user != null) {
            baseDao.save(user);
        }
    }

    @Override
    public RbacUser save(RbacUser user){
        RbacUser saved = baseDao.findFirstByUsername(user.getUsername());
        if (saved != null){
            if (user.getId() == null){
                throw new BusinessException(RbacError.USER_EXISTED);
            } else {
                user.setPassword(saved.getPassword());
            }
        }
        return super.save(user);
    }
}
