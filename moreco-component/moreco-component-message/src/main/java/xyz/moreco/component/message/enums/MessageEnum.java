package xyz.moreco.component.message.enums;

/**
 * 枚举类
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:18
 */
public enum MessageEnum {
    MAIL(1, "邮件"),
    WEB(2, "站内信"),
    ALIYUN_SMS(3, "阿里云短信"),
    TENCENT_SMS(4, "腾讯云短信"),
    NET_ESAY_SMS(5, "网易云短信");

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

    MessageEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
