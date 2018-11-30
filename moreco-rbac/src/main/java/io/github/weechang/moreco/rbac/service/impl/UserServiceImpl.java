package io.github.weechang.moreco.rbac.service.impl;

import com.google.common.collect.Lists;
import io.github.weechang.moreco.base.exception.AppException;
import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.UserDao;
import io.github.weechang.moreco.rbac.model.domain.RoleMenu;
import io.github.weechang.moreco.rbac.model.domain.User;
import io.github.weechang.moreco.rbac.model.domain.UserRole;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.model.domain.enums.UserStatusEnum;
import io.github.weechang.moreco.rbac.service.RoleMenuService;
import io.github.weechang.moreco.rbac.service.UserRoleService;
import io.github.weechang.moreco.rbac.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:24
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public PageModel<User> findAll(User param, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username" ,ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("realName" ,ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("email" ,ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("mobile" ,ExampleMatcher.GenericPropertyMatchers.contains());
        return new PageModel<>(baseDao.findAll(Example.of(param, matcher), pageable));
    }

    @Override
    public List<Long> findAllMenuIdByUserId(Long id) {
        // 先查询用户有哪些角色，再根据角色来获取目录id
        List<Long> userMenuIds = Lists.newArrayList();
        List<RoleMenu> roleMenus = Lists.newArrayList();
        // 查询用户有哪些角色
        List<UserRole> userRoles = userRoleService.findAllByUserId(id);
        if (CollectionUtils.isNotEmpty(userRoles)) {
            // 获取所有角色有哪些目录
            for (UserRole userRole : userRoles) {
                roleMenus.addAll(roleMenuService.findAllByRoleId(userRole.getRoleId()));
            }
        }

        for (RoleMenu roleMenu : roleMenus) {
            userMenuIds.add(roleMenu.getMenuId());
        }
        return userMenuIds;
    }

    @Override
    public void updatePasswordByUserId(Long id, String newPassword) {
        User user = baseDao.findOne(id);
        if (user != null) {
            user.setPassword(newPassword);
            baseDao.save(user);
        }
    }

    @Override
    public void resetPasswordByUserId(Long id) {
        User user = baseDao.findOne(id);
        if (user != null) {
            baseDao.save(user);
        }
    }

    @Override
    public void changeStatus(Long userId, int status) {
        User user = baseDao.findOne(userId);
        if (user != null){
            if (status == UserStatusEnum.FORBIDDEN.getKey()){
                user.setStatus(UserStatusEnum.FORBIDDEN.getKey());
            } else if (status == UserStatusEnum.AVAILABLE.getKey()){
                user.setStatus(UserStatusEnum.AVAILABLE.getKey());
            } else if (status == UserStatusEnum.LOCKED.getKey()){
                user.setStatus(UserStatusEnum.LOCKED.getKey());
                user.setLockedTime(new Date());
            }
        }
    }

    @Override
    public User detail(Long id) {
        User user = baseDao.findOne(id);
        if (user != null){
            List<Long> roleIds = userRoleService.findAllByUserId(id).stream().map(UserRole::getRoleId).collect(Collectors.toList());
            user.setRoleIds(roleIds);
        }
        return user;
    }

    @Override
    public User save(User user){
        User saved = baseDao.findFirstByUsername(user.getUsername());
        if (saved != null){
            if (user.getId() == null){
                throw new AppException(RbacError.USER_EXISTED);
            } else {
                user.setPassword(saved.getPassword());
            }
        }
        user = super.save(user);
        userRoleService.save(user.getId(), user.getRoleIds());
        return user;
    }
}
