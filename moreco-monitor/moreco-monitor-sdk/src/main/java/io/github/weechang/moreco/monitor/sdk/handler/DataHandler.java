package io.github.weechang.moreco.monitor.sdk.handler;

import io.github.weechang.moreco.monitor.sdk.method.MethodInfo;

/**
 * @author zhangwei
 * date 2018/12/21
 * time 17:40
 */
public interface DataHandler {

    String DATA_TYPE_METHOD = "method";
    String DATA_TYPE_JVM_HB = "jvm_hb";
    String DATA_TYPE_JVM = "jvm";
    String DATA_TYPE_JVM_START = "jvmStart";
    String DATA_TYPE_ALARM = "alarm";
    String DATA_TYPE_BUSINESS = "business";
    String DATA_TYPE_SERVER = "server";
    String DATA_TYPE_SERVER_INFO = "serverInfo";
    String DATA_TYPE_LOG = "log";

    void addData(String var1, String var2);

    void addMethodData(MethodInfo var1);

    void finishData();
}
