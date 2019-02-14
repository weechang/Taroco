package xyz.weechang.moreco.monitor.agent.annation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法监控注解
 *
 * @author zhangwei
 * date 2018/12/3
 * time 13:34
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Method {

    String key();

    boolean success() default true;

    long totalTime() default 0L;

    long usedTime() default 0L;
}
