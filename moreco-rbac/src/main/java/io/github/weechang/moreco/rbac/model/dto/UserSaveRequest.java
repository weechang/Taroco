package io.github.weechang.moreco.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import io.github.weechang.moreco.rbac.model.domain.Resource;
import io.github.weechang.moreco.rbac.model.domain.Role;
import io.github.weechang.moreco.rbac.model.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/30
 * time 17:52
 */
@ApiModel("用户保存请求")
@Data
public class UserSaveRequest implements Serializable {
    private static final long serialVersionUID = 9183078070802426174L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("部门id")
    private Long deptId;

    @ApiModelProperty("角色id")
    private List<Long> roleIds;

    public User toUser(){
        User user = BeanUtil.toBean(this, User.class);
        if (CollectionUtil.isNotEmpty(roleIds)){
            List<Role> roles = new ArrayList<>();
            for (Long roleId : roleIds){
                Role role = new Role();
                role.setId(roleId);
                roles.add(role);
            }
            user.setRoles(roles);
        }
        return user;
    }
}
