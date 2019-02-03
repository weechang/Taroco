package io.github.weechang.moreco.rbac.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangwei
 * date 2019/2/2
 * time 13:24
 */
@Data
@ApiModel("修改用户状态")
public class UserStatusChangeRequest implements Serializable {
    private static final long serialVersionUID = -315586821512956617L;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户")
    private Integer targetStatus;
}
