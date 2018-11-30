package io.github.weechang.moreco.monitor.manager.model.domain.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 10:55
 */
@ApiModel("服务器状态")
public enum ServerStatusEnum {

    UNKNOWN(-1, "未知"),
    STOP(0, "停止"),
    RUNNING(1, "运行中");

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

    ServerStatusEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public static String getNameByKey(Integer key) {
        for (ServerStatusEnum item : ServerStatusEnum.values()) {
            if (item.key == key) {
                return item.name;
            }
        }
        return null;
    }

    public static JSONArray toJsonArray() {
        JSONArray array = new JSONArray();
        for (ServerStatusEnum e : ServerStatusEnum.values()) {
            JSONObject item = new JSONObject();
            item.put("key", e.getKey());
            item.put("name", e.getName());
            array.add(item);
        }
        return array;
    }
}
