package io.github.weechang.moreco.monitor.agent.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时任务配置
 *
 * @author zhangwei
 * date 2018/12/29
 * time 17:14
 */
@Component
public class TaskUtil {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    public void schedule(Runnable task, int period) {
        long periodLong = period * 1000;
        if (periodLong == 0) {
            periodLong = 60 * 1000L;
        }
        threadPoolTaskScheduler.scheduleAtFixedRate(task, new Date(System.currentTimeMillis() + 50000L), periodLong);
    }

    public void reset() {
        threadPoolTaskScheduler.shutdown();
        threadPoolTaskScheduler.initialize();
    }

    public void resetSchedule(Runnable task, int period) {
        shutdown();
        threadPoolTaskScheduler.initialize();
        schedule(task, period);
    }

    public void shutdown() {
        threadPoolTaskScheduler.shutdown();
    }
}
