package xyz.weechang.moreco.component.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import xyz.weechang.moreco.component.rbac.model.domain.Role;
import xyz.weechang.moreco.core.model.dto.QueryRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangwei
 * date 2018/11/30
 * time 13:22
 */
@Data
@ApiModel("角色查询请求")
public class RoleQueryRequest extends QueryRequest {
    private static final long serialVersionUID = 4452748390646340002L;

    @ApiModelProperty("角色名称")
    private String name;

    public Role toRole(){
        return BeanUtil.toBean(this, Role.class);
    }
}
