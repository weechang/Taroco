package io.github.weechang.moreco.security.custom;

import io.github.weechang.moreco.rbac.model.domain.Role;
import io.github.weechang.moreco.rbac.model.domain.User;
import io.github.weechang.moreco.rbac.model.domain.enums.UserStatusEnum;
import io.github.weechang.moreco.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * UserDetails 实现类
 *
 * @author zhangwei
 * date 2019/1/26
 * time 20:40
 */
public class MorecoUserDetails implements UserDetails {

    private User user;
    private List<Role> roles;

    @Autowired
    private RoleService roleService;

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public MorecoUserDetails() {
    }

    public MorecoUserDetails(User user) {
        this.user = user;
        this.roles = user.getRoles();
    }

    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !UserStatusEnum.LOCKED.getKey().equals(user.getStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !UserStatusEnum.FORBIDDEN.getKey().equals(user.getStatus());
    }
}
