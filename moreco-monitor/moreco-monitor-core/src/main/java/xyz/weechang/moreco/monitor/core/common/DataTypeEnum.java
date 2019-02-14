package xyz.weechang.moreco.monitor.core.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 数据类型
 *
 * @author zhangwei
 * date 2018/12/30
 * time 20:34
 */
public enum DataTypeEnum {

    SERVER(1, "服务器"),
    JVM(2, "JVM"),
    JVM_START(3, "JVM启动"),
    METRIC(4, "自定义数据"),
    METHOD(5, "方法"),
    ALARM(6, "告警");

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

    DataTypeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public static String getNameByKey(Integer key) {
        for (DataTypeEnum item : DataTypeEnum.values()) {
            if (item.key == key) {
                return item.name;
            }
        }
        return null;
    }

    public static DataTypeEnum getEnumByKey(Integer key) {
        for (DataTypeEnum item : DataTypeEnum.values()) {
            if (item.key == key) {
                return item;
            }
        }
        return null;
    }

    public static JSONArray toJsonArray() {
        JSONArray array = new JSONArray();
        for (DataTypeEnum e : DataTypeEnum.values()) {
            JSONObject item = new JSONObject();
            item.put("key", e.getKey());
            item.put("name", e.getName());
            array.add(item);
        }
        return array;
    }
}
