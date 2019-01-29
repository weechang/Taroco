package io.github.weechang.moreco.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import io.github.weechang.moreco.rbac.model.domain.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangwei
 * date 2018/10/30
 * time 17:42
 */
@Data
@ApiModel("目录保存请求")
public class MenuSaveRequest implements Serializable {
    private static final long serialVersionUID = 2095226325999802428L;

    @ApiModelProperty("Id")
    private Long id;

    @ApiModelProperty("父菜单ID，一级菜单为0")
    private Long parentId;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("菜单URL")
    private String url;

    @ApiModelProperty("授权(多个用逗号分隔，如：/rbac/permission/list,/rbac/permission/edit)")
    private String perms;

    @ApiModelProperty("类型     0：目录   1：菜单   2：按钮")
    private Integer type;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("排序")
    private Integer orderNum;

    public Menu toMenu(){
        orderNum = orderNum == null ? 0 : orderNum;
        return BeanUtil.toBean(this, Menu.class);
    }
}
