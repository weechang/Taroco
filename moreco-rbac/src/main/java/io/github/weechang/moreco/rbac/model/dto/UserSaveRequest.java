package io.github.weechang.moreco.rbac.model.dto;

import io.github.weechang.jutil.common.util.BeanUtil;
import io.github.weechang.moreco.rbac.model.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

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

    public User toUser(){
        return BeanUtil.beanToBean(this, User.class);
    }
}
