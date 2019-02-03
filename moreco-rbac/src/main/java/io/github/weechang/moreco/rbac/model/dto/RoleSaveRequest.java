package io.github.weechang.moreco.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import io.github.weechang.moreco.rbac.model.domain.Menu;
import io.github.weechang.moreco.rbac.model.domain.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/30
 * time 17:48
 */
@Data
@ApiModel("角色保存请求")
public class RoleSaveRequest implements Serializable {
    private static final long serialVersionUID = -4925095979631776817L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("授权目录权限列表")
    private List<Long> menuIds;

    public Role toRole(){
        Role role = BeanUtil.toBean(this, Role.class);
        // 目录
        if (CollectionUtil.isNotEmpty(menuIds)){
            List<Menu> menus = new ArrayList<>();
            for (Long menuId : menuIds){
                Menu menu = new Menu();
                menu.setId(menuId);
                menus.add(menu);
            }
            role.setMenus(menus);
        }
        return role;
    }
}
