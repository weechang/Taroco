package xyz.weechang.moreco.monitor.agent;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 应用启动类
 *
 * @author zhangwei
 * date 2018/12/29
 * time 15:01
 */
@EnableScheduling
@SpringBootApplication
public class McmAgentApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(McmAgentApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
//        JSON json = (JSON) JSONObject.toJSON(ServerUtil.getServer());
////        System.out.println(json);
//        TpcSender sender = TpcSender.getInstance();
//        sender.sendData(json.toJSONString());
//        new Monitor().init();;
        Monitor monitor = initMethod();
        monitor.init("http://127.0.0.1:8080/api/agent", "mcm-app-group-default", "demo_app", "DemoApp_1");
    }

    @Bean
    Monitor initMethod(){
        Monitor monitor = new Monitor();
        return monitor;
    }

}
