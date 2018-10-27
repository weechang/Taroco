package io.github.weechang.moreco.rbac.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.weechang.moreco.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.redis.core.index.PathBasedRedisIndexDefinition;

import java.util.List;

/**
 * 系统用户
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDomain extends BaseDomain {
    private static final long serialVersionUID = -3083631620913551995L;

    @Id
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 用户角色
     */
    @Transient
    private List<RoleDomain> roles;
}
