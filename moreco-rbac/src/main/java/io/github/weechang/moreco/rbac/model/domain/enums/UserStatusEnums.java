package io.github.weechang.moreco.rbac.model.domain.enums;

import io.swagger.annotations.ApiModel;

/**
 * 用户状态
 *
 * @author zhangwei
 * date 2018/10/30
 * time 20:04
 */
@ApiModel("用户状态")
public enum UserStatusEnums {
    FORBIDDEN(0, "禁用"),
    AVAILABLE(1, "可用"),
    LOCKED(2, "锁定");

    private Integer key;
    private String value;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    UserStatusEnums(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
