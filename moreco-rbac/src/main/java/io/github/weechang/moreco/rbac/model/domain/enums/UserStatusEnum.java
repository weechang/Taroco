package io.github.weechang.moreco.rbac.model.domain.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;

/**
 * 用户状态
 *
 * @author zhangwei
 * date 2018/10/30
 * time 20:04
 */
@ApiModel("用户状态")
public enum UserStatusEnum {
    FORBIDDEN(0, "禁用"),
    AVAILABLE(1, "可用"),
    LOCKED(2, "锁定");

    private Integer key;
    private String name;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    UserStatusEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public static String getNameByKey(Integer key) {
        for (UserStatusEnum item : UserStatusEnum.values()) {
            if (item.key == key) {
                return item.name;
            }
        }
        return null;
    }

    public static JSONArray toJsonArray() {
        JSONArray array = new JSONArray();
        for (UserStatusEnum e : UserStatusEnum.values()) {
            JSONObject item = new JSONObject();
            item.put("key", e.getKey());
            item.put("name", e.getName());
            array.add(item);
        }
        return array;
    }
}
