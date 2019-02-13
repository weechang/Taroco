package xyz.weechang.moreco.component.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import xyz.weechang.moreco.component.rbac.model.domain.User;
import xyz.weechang.moreco.core.model.dto.QueryRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangwei
 * date 2018/11/30
 * time 13:26
 */
@ApiModel("用户查询请求")
@Data
public class UserQueryRequest extends QueryRequest {
    private static final long serialVersionUID = -1632895627523323729L;

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

    @ApiModelProperty("状态  0：禁用 1：正常  2：锁定")
    private Integer status;

    public User toUser(){
        return BeanUtil.toBean(this, User.class);
    }
}
