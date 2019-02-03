package io.github.weechang.moreco.rbac.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import io.github.weechang.moreco.base.exception.AppException;
import io.github.weechang.moreco.base.model.dto.PageModel;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.UserDao;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.model.domain.Role;
import io.github.weechang.moreco.rbac.model.domain.User;
import io.github.weechang.moreco.rbac.model.domain.enums.UserStatusEnum;
import io.github.weechang.moreco.rbac.service.UserService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:24
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {

    @Override
    public void doConvertDataMap(User... users) {
        for (User user : users) {
            user.addDataMap("createdDate", DateUtil.formatDateTime(user.getCreatedDate()));
            user.addDataMap("status", UserStatusEnum.getNameByKey(user.getStatus()));
            if (user.getDept() != null) {
                user.addDataMap("deptId", user.getDept().getId());
                user.addDataMap("deptName", user.getDept().getName());
            }
            if (CollectionUtil.isNotEmpty(user.getRoles())) {
                user.addDataMap("roleIds", user.getRoles().stream().map(Role::getId).collect(Collectors.toList()));
            }
        }
    }

    @Override
    public PageModel<User> findAll(User param, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("realName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("mobile", ExampleMatcher.GenericPropertyMatchers.contains());
        PageModel<User> page = new PageModel<>(baseDao.findAll(Example.of(param, matcher), pageable));
        if (CollectionUtil.isNotEmpty(page.getList())) {
            for (User user : page.getList()) {
                user.setRoles(null);
            }
        }
        return page;
    }

    @Override
    public void updatePasswordByUsername(String username, String newPassword) {
        User user = baseDao.findFirstByUsername(username);
        if (user != null) {
            String encodePwd = new String(DigestUtil.sha256(username, newPassword));
            user.setPassword(encodePwd);
            baseDao.save(user);
        }
    }

    @Override
    public void resetPasswordByUserId(Long id) {
        User user = baseDao.findOne(id);
        if (user != null) {
            String encodePwd = new String(DigestUtil.sha256(user.getUsername(), "123456"));
            user.setPassword(encodePwd);
            baseDao.save(user);
        }
    }

    @Override
    public void changeStatus(Long userId, int status) {
        User user = baseDao.findOne(userId);
        if (user != null) {
            if (status == UserStatusEnum.FORBIDDEN.getKey()) {
                user.setStatus(UserStatusEnum.FORBIDDEN.getKey());
            } else if (status == UserStatusEnum.AVAILABLE.getKey()) {
                user.setStatus(UserStatusEnum.AVAILABLE.getKey());
            } else if (status == UserStatusEnum.LOCKED.getKey()) {
                user.setStatus(UserStatusEnum.LOCKED.getKey());
                user.setLockedTime(new Date());
            }
        }
    }

    @Override
    public User detail(Long id) {
        return baseDao.findOne(id);
    }

    @Override
    public User save(User user) {
        User saved = baseDao.findFirstByUsername(user.getUsername());
        if (saved != null) {
            if (user.getId() == null) {
                throw new AppException(RbacError.USER_EXISTED);
            } else {
                user.setPassword(saved.getPassword());
            }
        } else {
            String encodePwd = new String(DigestUtil.sha256(user.getUsername(), "123456"));
            user.setPassword(encodePwd);
        }
        user = super.save(user);
        return user;
    }
}
