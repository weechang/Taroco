package xyz.weechang.user.center.command.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:58.
 */
@ApiModel("目录创建")
@Data
public class MenuCreateRequest {

    @ApiModelProperty("上级目录ID")
    private String parentId;

    @ApiModelProperty("目录名称")
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 50)
    private String name;

    @ApiModelProperty("资源路径")
    @NotNull
    @NotEmpty
    @Length(min = 0, max = 255)
    private String uri;

    @ApiModelProperty("授权标识(多个用逗号分隔，如：user:list,user:create)")
    @NotNull
    @NotEmpty
    @Length(min = 1)
    private String perms;

    @ApiModelProperty("类型(0：目录   1：菜单   2：按钮)")
    @NotNull
    @Range(min = 0, max = 2)
    private Integer type;

    @ApiModelProperty("菜单图标")
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 255)
    private String icon;

    @ApiModelProperty("排序")
    @NotNull
    private Integer orderNum;

    @ApiModelProperty("ztree属性")
    private Boolean open;
}
