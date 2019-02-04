package io.github.weechang.moreco.oss.enums;

/**
 * 枚举类
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:18
 */
public enum OSSEnum {
    QINIU(1, "七牛"),
    ALIYUN(2, "阿里云"),
    TENCENT(3, "腾讯云"),
    UPYUN(4, "又拍云"),
    LOCAL(5, "本机存储");

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

    OSSEnum(Integer key, String value){
        this.key = key;
        this.value = value;
    }
}
