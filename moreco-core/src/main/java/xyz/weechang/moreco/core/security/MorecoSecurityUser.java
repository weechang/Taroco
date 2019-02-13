package xyz.weechang.moreco.core.security;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangwei
 * date 2019/2/12
 * time 17:17
 */
@Data
public class MorecoSecurityUser implements Serializable {

    private static final long serialVersionUID = 1799308727986154396L;

    private String username;

    private String password;
}
