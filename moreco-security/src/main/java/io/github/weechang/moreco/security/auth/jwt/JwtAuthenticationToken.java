package io.github.weechang.moreco.security.auth.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * JWT token
 * @author zhangwei
 * date 2019/1/28
 * time 23:11
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 3981518947978158945L;

    private String principal;
    private String credentials;
    private DecodedJWT token;

    public JwtAuthenticationToken(DecodedJWT token) {
        super(Collections.emptyList());
        this.token = token;
    }

    public JwtAuthenticationToken(UserDetails userDetails, DecodedJWT token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = userDetails.getUsername();
        this.credentials = userDetails.getPassword();
        this.token = token;
    }

    @Override
    public void setDetails(Object details) {
        super.setDetails(details);
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public DecodedJWT getToken() {
        return token;
    }
}