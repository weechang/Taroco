package io.github.weechang.moreco.rbac.model.dto;

import io.github.weechang.jutil.common.util.BeanUtil;
import io.github.weechang.moreco.rbac.model.domain.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangwei
 * date 2018/11/30
 * time 13:22
 */
@Data
@ApiModel("角色查询请求")
public class RoleQueryRequest implements Serializable {
    private static final long serialVersionUID = 4452748390646340002L;

    @ApiModelProperty("角色名称")
    private String name;

    public Role toRole(){
        return BeanUtil.beanToBean(this, Role.class);
    }
}
