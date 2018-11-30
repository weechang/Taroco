package io.github.weechang.moreco.monitor.manager.model.domain.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 11:07
 */
@ApiModel("应用类型")
public enum AppTypeEnum {

    WEB_APP(1, "Web应用"),
    BACKHAND_APP(2, "后台应用");

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

    public void setName(String value) {
        this.name = value;
    }

    AppTypeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public static String getNameByKey(Integer key) {
        for (AppTypeEnum item : AppTypeEnum.values()) {
            if (item.key == key) {
                return item.name;
            }
        }
        return null;
    }

    public static JSONArray toJsonArray() {
        JSONArray array = new JSONArray();
        for (AppTypeEnum e : AppTypeEnum.values()) {
            JSONObject item = new JSONObject();
            item.put("key", e.getKey());
            item.put("name", e.getName());
            array.add(item);
        }
        return array;
    }
}
