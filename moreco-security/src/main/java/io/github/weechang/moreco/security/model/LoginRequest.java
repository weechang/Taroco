package io.github.weechang.moreco.security.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangwei
 * date 2019/1/26
 * time 21:29
 */
@ApiModel("登录请求")
@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 2328802641143116774L;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;
}
