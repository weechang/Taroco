package xyz.weechang.user.center.command.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 23:23.
 */
@ApiModel("组织机构修改")
@Data
public class OrgUpdateRequest {

    @ApiModelProperty("机构编码")
    @NotEmpty
    private String code;

    @ApiModelProperty("机构名称")
    @NotEmpty
    private String name;

    @ApiModelProperty(value = "排序序号")
    private Integer orderNum;

    @ApiModelProperty(value = "是否可用")
    private Boolean enable;

    @ApiModelProperty(value = "树状结构是否打开")
    private Boolean open;
}
