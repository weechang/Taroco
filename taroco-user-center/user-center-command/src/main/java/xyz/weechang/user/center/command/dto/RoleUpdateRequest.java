package xyz.weechang.user.center.command.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:58.
 */
@ApiModel("角色更新")
@Data
public class RoleUpdateRequest {

    @ApiModelProperty("角色名称")
    @Length(min = 1, max = 10)
    private String roleName;

    @ApiModelProperty("角色标识")
    @Length(min = 1, max = 10)
    private String roleSign;

    @ApiModelProperty("备注")
    private String remark;
}
