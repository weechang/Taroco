package xyz.weechang.user.center.command.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 23:19.
 */
@ApiModel("组织机构创建")
@Data
public class OrgCreateRequest {

    @ApiModelProperty("上级id")
    private String parentId;

    @ApiModelProperty("机构名称")
    @NotNull
    @NotEmpty
    private String name;
}
