package xyz.weechang.user.center.command.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:59.
 */
@ApiModel("用户创建")
@Data
public class UserCreateRequest {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("邮件")
    private String email;

    @ApiModelProperty("角色")
    private List<String> roles;

    @ApiModelProperty("组织机构")
    private List<String> orgs;

}
