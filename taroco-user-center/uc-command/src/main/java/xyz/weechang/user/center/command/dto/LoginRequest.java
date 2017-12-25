package xyz.weechang.user.center.command.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/12/18 10:44.
 */
@Data
@ApiModel("登录")
public class LoginRequest {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;
}
