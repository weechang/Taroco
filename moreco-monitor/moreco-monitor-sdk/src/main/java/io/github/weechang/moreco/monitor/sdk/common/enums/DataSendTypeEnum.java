package io.github.weechang.moreco.monitor.sdk.common.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author zhangwei
 * date 2018/12/21
 * time 17:08
 */
public enum DataSendTypeEnum {

    WEB_URL(1, "WEB_URL调用"),
    KAFKA(2, "KAFKA");

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

    DataSendTypeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public static String getNameByKey(Integer key) {
        for (DataSendTypeEnum item : DataSendTypeEnum.values()) {
            if (item.key.equals(key) ) {
                return item.name;
            }
        }
        return null;
    }

    public static JSONArray toJsonArray() {
        JSONArray array = new JSONArray();
        for (DataSendTypeEnum e : DataSendTypeEnum.values()) {
            JSONObject item = new JSONObject();
            item.put("key", e.getKey());
            item.put("name", e.getName());
            array.add(item);
        }
        return array;
    }
}
