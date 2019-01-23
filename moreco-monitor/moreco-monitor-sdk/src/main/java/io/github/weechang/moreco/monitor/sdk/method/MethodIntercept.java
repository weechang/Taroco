package io.github.weechang.moreco.monitor.sdk.method;

import io.github.weechang.moreco.monitor.sdk.annation.MethodMonitor;
import javassist.bytecode.SignatureAttribute;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author zhangwei
 * date 2018/12/21
 * time 14:00
 */
@Aspect
@Order(1)
public class MethodIntercept {

//    @Around("@annotation(io.github.weechang.moreco.monitor.sdk.annation.MethodMonitor)")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        MethodMonitor method = (MethodMonitor)this.getLogAnnotation(pjp, MethodMonitor.class);
//        if (method != null) {
//            MethodInfo mi = Monitor.methodStart(method.key());
//
//            Object var5;
//            try {
//                Object result = pjp.proceed();
//                var5 = result;
//            } catch (Throwable var9) {
//                if (method.success()) {
//                    Monitor.methodFail(mi);
//                }
//
//                throw var9;
//            } finally {
//                Monitor.methodFinish(mi);
//            }
//
//            return var5;
//        } else {
//            return pjp.proceed();
//        }
//    }
//
//    private <T extends Annotation> T getLogAnnotation(JoinPoint jp, Class<T> type) {
//        SignatureAttribute.MethodSignature methodSignature = (SignatureAttribute.MethodSignature)jp.getSignature();
//        Method targetMethod = methodSignature.getMethod();
//        return targetMethod.getAnnotation(type);
//    }
}
