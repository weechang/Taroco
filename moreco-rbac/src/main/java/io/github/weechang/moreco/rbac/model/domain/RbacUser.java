package io.github.weechang.moreco.rbac.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.weechang.moreco.base.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:56
 */
@ApiModel("用户")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate()
@Where(clause = "yn = 1")
public class RbacUser extends BaseDomain {
    private static final long serialVersionUID = -3083631620913551995L;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ApiModelProperty("盐")
    private String salt;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("状态  0：禁用   1：正常")
    private Integer status;

    @Transient
    private List<Long> deptIds;

    @Transient
    private List<Long> roleIds;
}
