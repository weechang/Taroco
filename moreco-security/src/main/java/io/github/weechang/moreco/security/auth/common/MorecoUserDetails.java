package io.github.weechang.moreco.security.auth.common;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * UserDetails 实现类
 *
 * @author zhangwei
 * date 2019/1/26
 * time 20:40
 */
public class MorecoUserDetails implements UserDetails {

    private String username;
    private String password;

    public MorecoUserDetails() {
    }

    public MorecoUserDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
//        return !UserStatusEnum.LOCKED.getKey().equals(user.getStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
//        return !UserStatusEnum.FORBIDDEN.getKey().equals(user.getStatus());
    }
}
