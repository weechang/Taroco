package io.github.weechang.moreco.monitor.manager.model.domain.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
/**
 * @author zhangwei
 * date 2018/11/29
 * time 11:26
 */
@ApiModel("心跳状态")
public enum HeartBeatStatusEnum {

    LOST(0, "失联"),
    NORMAL(1, "正常");

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

    HeartBeatStatusEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public static String getNameByKey(Integer key) {
        for (HeartBeatStatusEnum item : HeartBeatStatusEnum.values()) {
            if (item.key == key) {
                return item.name;
            }
        }
        return null;
    }

    public static JSONArray toJsonArray() {
        JSONArray array = new JSONArray();
        for (HeartBeatStatusEnum e : HeartBeatStatusEnum.values()) {
            JSONObject item = new JSONObject();
            item.put("key", e.getKey());
            item.put("name", e.getName());
            array.add(item);
        }
        return array;
    }
}
