package io.github.weechang.moreco.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import io.github.weechang.moreco.rbac.model.domain.Menu;
import io.github.weechang.moreco.rbac.model.domain.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @ApiModelProperty("类型     0：目录   1：菜单   2：按钮")
    private Integer type;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("是否显示")
    private Integer show;

    @ApiModelProperty("排序")
    private Integer orderNum;

    @ApiModelProperty("资源id")
    private List<Long> resourceIds;

    public Menu toMenu(){
        orderNum = orderNum == null ? 0 : orderNum;
        Menu menu = BeanUtil.toBean(this, Menu.class);
        // 资源
        if (CollectionUtil.isNotEmpty(resourceIds)){
            List<Resource> resources = new ArrayList<>();
            for (Long resourceId : resourceIds){
                Resource resource = new Resource();
                resource.setId(resourceId);
                resources.add(resource);
            }
            menu.setResources(resources);
        }
        // 上级
        parentId = parentId == null ? 0 : parentId;
        menu.setParent(new Menu(parentId));
        return menu;
    }
}
