package io.github.weechang.moreco.monitor.sdk.common;

import com.alibaba.fastjson.JSON;
import io.github.weechang.moreco.monitor.sdk.alarm.AlarmInfo;
import io.github.weechang.moreco.monitor.sdk.common.enums.DataSendTypeEnum;
import io.github.weechang.moreco.monitor.sdk.handler.DataHandler;
import io.github.weechang.moreco.monitor.sdk.hearbeat.HeartbeatInfo;
import io.github.weechang.moreco.monitor.sdk.jvm.JvmInfo;
import io.github.weechang.moreco.monitor.sdk.jvm.JvmStartInfo;
import io.github.weechang.moreco.monitor.sdk.method.MethodInfo;
import io.github.weechang.moreco.monitor.sdk.util.JvmUtil;
import io.github.weechang.moreco.monitor.sdk.util.MonitorThreadFactory;
import io.github.weechang.moreco.monitor.sdk.util.MonitorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 监控主体
 *
 * @author zhangwei
 * date 2018/12/3
 * time 16:21
 */
@Slf4j
public class Monitor {

    private static final ScheduledExecutorService MONITOR_POOL = createScheduledExecutor();
    private static volatile boolean startup = false;
    private static MonitorConfig CONFIG = MonitorConfig.getInstance();
    private static volatile DataHandler dataHandler;

    public static final ScheduledExecutorService getScheduledExecutor() {
        return MONITOR_POOL;
    }

    private static final ScheduledExecutorService createScheduledExecutor() {
        final ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(4, new MonitorThreadFactory("monitor", true));
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                pool.shutdown();
                log.info("app start shutdown, monitor scheduledExecutor shutdown");
            }
        }, "ShutdownHook"));
        return pool;
    }

    public static synchronized void init() {
        if (startup) {
            if (StringUtils.isBlank(CONFIG.getProCode())
                    || StringUtils.isBlank(CONFIG.getAppCode())) {
                log.error("proCode and appCode can not be blank");
                throw new IllegalArgumentException("proCode and appCode can not be blank");
            } else {
                if (DataSendTypeEnum.WEB_URL.getKey().equals(CONFIG.getDataSendType())
                        && StringUtils.isBlank(CONFIG.getDataReportUrl())) {
                    log.error("dataReportUrl can not be blank");
                    throw new IllegalArgumentException("dataReportUrl can not be blank");
                }
            }
        }
    }

    public static void alarm(String alarmKey, String alarmMsg) {
        if (startup) {
            if (StringUtils.isNotEmpty(alarmKey) || StringUtils.isNotEmpty(alarmMsg)) {
                AlarmInfo alarmInfo = new AlarmInfo(CONFIG.getProCode(), CONFIG.getAppCode(), alarmKey, alarmMsg);
//                dataHandle.addData("alarm", JSON.toJSONString(alarmInfo));
            } else {
                log.warn("alarmKey or alarmMsg is empty, can not send alarm data");
            }
        }
    }

    private static void regJVMMonitor() {
        if (startup) {
            MONITOR_POOL.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    try {
                        log.info("start collecting the application startup information....");
                        JvmStartInfo jvmStartInfo = JvmUtil.getJvmStartInfo();
                        String data = JSON.toJSONString(jvmStartInfo);
                        dataHandler.addData("jvmStart", data);
                        log.info("collection of application startup information success");
                    } catch (Exception e) {
                        log.warn("collection of application startup failed:", e);
                    }

                }
            }, 15000L, 3600000L, TimeUnit.MILLISECONDS);
            MONITOR_POOL.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    try {
                        log.debug("starts collecting current JVM runtime performance monitoring data...");
                        JvmInfo jvmInfo = JvmUtil.getJvmInfo();
                        String data = JSON.toJSONString(jvmInfo);
                        dataHandler.addData("jvm", data);
                        log.debug("capture current JVM runtime performance monitoring data successfully");
                    } catch (Exception e) {
                        log.warn("JVM runtime performance monitoring startup failed:", e);
                    }

                }
            }, 20000L, 60000L, TimeUnit.MILLISECONDS);
            MONITOR_POOL.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    try {
                        log.debug("begins to startup heartbeat data monitor data...");
                        HeartbeatInfo heartbeatInfo = new HeartbeatInfo();
                        heartbeatInfo.setPid(MonitorUtil.getPid());
                        heartbeatInfo.setStartTime(JvmUtil.getStartTime());
                        String data = JSON.toJSONString(heartbeatInfo);
                        dataHandler.addData("jvm_hb", data);
                        log.debug("startup heartbeat data monitor success :{}", data);
                    } catch (Exception e) {
                        log.debug("startup heartbeat data monitor failed", e);
                    }

                }
            }, 10000L, 20000L, TimeUnit.MILLISECONDS);
        }
    }

    public static MethodInfo methodStart(String methodKey) {
        if (startup) {
            MethodInfo m = new MethodInfo(CONFIG.getProCode(), CONFIG.getAppCode(), methodKey);
            return m;
        } else {
            return null;
        }
    }

    public static void methodFail(MethodInfo m) {
        if (m != null) {
            m.setSuccess(0);
        }

    }

    public static void methodFinish(MethodInfo m) {
        if (m != null) {
            m.completed();
//            dataHandle.addMethodData(m);
        }

    }
}
