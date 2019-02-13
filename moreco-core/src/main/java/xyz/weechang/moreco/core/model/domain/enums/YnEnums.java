package xyz.weechang.moreco.core.model.domain.enums;

/**
 * 状态枚举
 *
 * @author zhangwei
 * date 2018/10/27
 * time 0:23
 */
public enum YnEnums {

    N(0, "无效"),
    Y(1, "有效");

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

    YnEnums(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

}
