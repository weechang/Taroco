package io.github.weechang.moreco.monitor.sdk.util;

import com.sun.jmx.mbeanserver.JmxMBeanServer;
import lombok.extern.slf4j.Slf4j;
import sun.security.action.GetPropertyAction;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.QueryExp;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.net.URLDecoder;
import java.security.AccessController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhangwei
 * date 2018/12/17
 * time 18:09
 */
@Slf4j
public class MonitorUtil {

    private static String INSTANCE_CODE = null;
    private static final String dateTemple = "yyyyMMddHHmmss";
    private static final String dateTimeTemple = "yyyy-MM-dd HH:mm:ss";


    public static String formatDate(long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(new Date(date));
    }

    public static String getNowTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(new Date(System.currentTimeMillis()));
    }

    public static String getNowDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date(System.currentTimeMillis()));
    }

    public static String getDateTime(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date(time));
    }

    public static int getPid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();

        try {
            return Integer.parseInt(name.substring(0, name.indexOf(64)));
        } catch (Exception var2) {
            return -1;
        }
    }

    public static String getTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }

    public static Integer getTomcatPortByMBean() {
        MBeanServer mBeanServer = null;

        try {
            ArrayList<MBeanServer> mBeanServers = MBeanServerFactory.findMBeanServer((String)null);
            if (mBeanServers.size() > 0) {
                Iterator i$ = mBeanServers.iterator();

                while(i$.hasNext()) {
                    MBeanServer _mBeanServer = (MBeanServer)i$.next();
                    if (_mBeanServer instanceof JmxMBeanServer) {
                        mBeanServer = _mBeanServer;
                        break;
                    }
                }
            }

            if (mBeanServer == null) {
                return null;
            } else {
                Integer port = getTomcatPort("Catalina:type=Connector,*", mBeanServer);
                return port != null ? port : getTomcatPort("Tomcat:type=Connector,*", mBeanServer);
            }
        } catch (Throwable var4) {
            log.warn("dmc get app web port fail:{}", var4.getMessage());
            return null;
        }
    }

    private static Integer getTomcatPort(String name, MBeanServer mBeanServer) throws Exception {
        String schema = "http";
        Set<ObjectName> objectNames = mBeanServer.queryNames(new ObjectName(name), (QueryExp)null);
        if (objectNames != null && objectNames.size() > 0) {
            Iterator i$ = objectNames.iterator();

            while(i$.hasNext()) {
                ObjectName objectName = (ObjectName)i$.next();
                String protocol = (String)mBeanServer.getAttribute(objectName, "protocol");
                if (protocol != null) {
                    protocol = protocol.toLowerCase();
                    if (protocol.startsWith("http")) {
                        return (Integer)mBeanServer.getAttribute(objectName, "port");
                    }
                }
            }
        }

        return null;
    }

//    public static String getJvmInstanceCode() {
//        if (INSTANCE_CODE == null) {
//            INSTANCE_CODE = getInstanceCode();
//        }
//
//        return INSTANCE_CODE;
//    }

//    private static String getInstanceCode() {
//        Integer port = getTomcatPortByMBean();
//        if (port != null) {
//            return getLocalIP() + ":" + port.toString();
//        } else {
//            int instanceValue = (getStartPath() + getApplicationPath()).hashCode();
//            if (instanceValue < 0) {
//                instanceValue = Math.abs(instanceValue);
//            }
//
//            String instanceCode = String.valueOf(instanceValue);
//            return getLocalIP() + ":" + instanceCode;
//        }
//    }

    private static String getStartPath() {
        String startPath = System.getProperties().get("user.dir").toString();
        startPath = startPath.replaceAll("\\\\", "/");
        return startPath;
    }

    private static String getApplicationPath() {
        String classPath = null;

        try {
            ClassLoader classLoader = MonitorUtil.class.getClassLoader();
            if (classLoader != null) {
                URL url = classLoader.getResource("");
                if (url != null) {
                    String path = url.getPath();
                    if (path != null) {
                        classPath = URLDecoder.decode(path, (String) AccessController.doPrivileged(new GetPropertyAction("file.encoding")));
                    } else {
                        log.warn("get getApplicationPath on error,path is null");
                    }
                } else {
                    log.warn("get getApplicationPath on error,url is null");
                }
            } else {
                log.warn("get getApplicationPath on error,classLoader is null");
            }
        } catch (Exception var4) {
            log.warn("dmc get application path on error:", var4);
        }

        if (classPath == null) {
            classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        }

        log.info("dmc get application path,classPath is:{}", classPath);
        classPath = classPath.replaceAll("\\\\", "/");
        return classPath.matches(".*(/|\\\\)WEB-INF(/|\\\\)classes(/|\\\\)$") ? classPath.replaceAll("(/|\\\\)WEB-INF(/|\\\\)classes(/|\\\\)$", "") : classPath;
    }
}
