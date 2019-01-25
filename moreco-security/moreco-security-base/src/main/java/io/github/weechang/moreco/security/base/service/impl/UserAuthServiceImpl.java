package io.github.weechang.moreco.security.base.service.impl;

import io.github.weechang.moreco.rbac.model.domain.User;
import io.github.weechang.moreco.security.base.service.UserAuthService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangwei
 * date 2019/1/25
 * time 13:35
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {


    @Override
    public User user(String account) {
        return null;
    }

    @Override
    public List<String> findPermissionsByRoleId(Long roleId) {
        return null;
    }

    @Override
    public String findRoleNameByRoleId(Long roleId) {
        return null;
    }

}
