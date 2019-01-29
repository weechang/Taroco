package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.exception.AppException;
import io.github.weechang.moreco.base.model.dto.PageModel;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.UserDao;
import io.github.weechang.moreco.rbac.model.domain.User;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.model.domain.enums.UserStatusEnum;
import io.github.weechang.moreco.rbac.service.UserService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:24
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {

    @Override
    public PageModel<User> findAll(User param, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("realName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("mobile", ExampleMatcher.GenericPropertyMatchers.contains());
        return new PageModel<>(baseDao.findAll(Example.of(param, matcher), pageable));
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
        }
        user = super.save(user);
        return user;
    }
}
