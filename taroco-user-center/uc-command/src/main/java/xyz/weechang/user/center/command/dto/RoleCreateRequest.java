package xyz.weechang.user.center.command.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:58.
 */
@ApiModel("角色创建")
@Data
public class RoleCreateRequest {

    @ApiModelProperty("所属机构")
    @NotNull
    @NotEmpty
    private String orgId;

    @ApiModelProperty("角色名称")
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 10)
    private String roleName;

    @ApiModelProperty("角色标识")
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 10)
    private String roleSign;

    @ApiModelProperty("备注")
    private String remark;
}
