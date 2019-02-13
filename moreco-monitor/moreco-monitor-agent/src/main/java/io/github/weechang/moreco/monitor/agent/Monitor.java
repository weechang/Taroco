package io.github.weechang.moreco.monitor.agent;


import sun.management.Agent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collector;

/**
 * 监控主方法
 *
 * @author zhangwei
 * date 2018/12/29
 * time 14:16
 */
@Slf4j
public class Monitor {

    /**
     * 配置文件初始化URL
     */
    private String configURL;

    private String groupKey;

    private String appKey;

    private String appSecret;

    @Autowired
    private TaskUtil taskUtil;

    public void init(String configURL, String groupKey, String appKey, String appSecret) {
        log.info("mcm agent starting..., please waiting");
        this.configURL = configURL;
        this.groupKey = groupKey;
        this.appKey = appKey;
        this.appSecret = appSecret;
        if (StringUtils.isBlank(configURL)) {
            throw new IllegalArgumentException("configURL can not be blank");
        }
        this.configInit();
        this.taskInit();
    }

    /**
     * 配置文件初始化
     */
    private void configInit() {
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            String connUrl = String.format("%s?groupKey=%s&appKey=%s&appSecret=%s", configURL, groupKey, appKey, appSecret);
            URL mURL = new URL(connUrl);
            conn = (HttpURLConnection) mURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.addRequestProperty("token", appSecret);
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                String result = sb.toString();
                Agent agent = (Agent) SerializeUtil.deserialize(result);
                Agent.getInstance().setGroupKey(groupKey);
                Agent.getInstance().setAppKey(appKey);
                Agent.getInstance().setServerRate(agent.getServerRate());
                Agent.getInstance().setJvmRate(agent.getJvmRate());
                Agent.getInstance().setJvmStartRate(agent.getJvmStartRate());
                Agent.getInstance().setHeartBeatRate(agent.getHeartBeatRate());
                Agent.getInstance().setSendRate(agent.getSendRate());
                Agent.getInstance().setNodes(agent.getNodes());
                if (CollectionUtils.isEmpty(Agent.getInstance().getNodes())){
                    throw new IllegalArgumentException("there is no tcp server node");
                }
                log.info("get mcm agent config success");
            } else {
                log.error("mcm agent get config failed, please check mcm-home");
                throw new IllegalMonitorStateException("mcm can not get right agent config");
            }
        } catch (Exception e) {
            log.error("mcm agent get config failed", e);
            throw new IllegalMonitorStateException("mcm can not get right agent config");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * 定时任务初始化
     */
    private void taskInit() {
        taskUtil.schedule(new Runnable() {
            @Override
            public void run() {
                Collector.collectServer();
            }
        }, Agent.getInstance().serverRate);
        taskUtil.schedule(new Runnable() {
            @Override
            public void run() {
                Collector.collectJvm();
            }
        }, Agent.getInstance().jvmRate);
        taskUtil.schedule(new Runnable() {
            @Override
            public void run() {
                Collector.collectJvmStart();
            }
        }, Agent.getInstance().jvmStartRate);
        taskUtil.schedule(new Runnable() {
            @Override
            public void run() {
                Collector.collectHeartBeat();
            }
        }, Agent.getInstance().heartBeatRate);
        taskUtil.schedule(new Runnable() {
            @Override
            public void run() {
                String msg = Collector.getMessage();
                SenderFactory.getSender().sendData(msg);
            }
        }, Agent.getInstance().sendRate);
        log.info("mcm started success");
    }
}
