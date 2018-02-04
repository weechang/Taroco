package xyz.weechang.user.center.command.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:59.
 */
@ApiModel("用户更新")
@Data
public class UserUpdateRequest {

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("邮件")
    private String email;
}
