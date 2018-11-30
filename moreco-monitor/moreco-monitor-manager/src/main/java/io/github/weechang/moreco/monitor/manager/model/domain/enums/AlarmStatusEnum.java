package io.github.weechang.moreco.monitor.manager.model.domain.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 10:58
 */
@ApiModel("报警状态")
public enum AlarmStatusEnum {

    CLOSED(0, "关闭"),
    OPENED(1, "开启");

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

    AlarmStatusEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public static String getNameByKey(Integer key) {
        for (AlarmStatusEnum item : AlarmStatusEnum.values()) {
            if (item.key == key) {
                return item.name;
            }
        }
        return null;
    }

    public static JSONArray toJsonArray() {
        JSONArray array = new JSONArray();
        for (AlarmStatusEnum e : AlarmStatusEnum.values()) {
            JSONObject item = new JSONObject();
            item.put("key", e.getKey());
            item.put("name", e.getName());
            array.add(item);
        }
        return array;
    }
}
