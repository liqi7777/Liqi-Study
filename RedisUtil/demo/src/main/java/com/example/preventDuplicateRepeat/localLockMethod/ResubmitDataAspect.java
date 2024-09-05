package com.example.preventDuplicateRepeat.localLockMethod;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.test.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Sky
 * create 2020/03/18
 * email sky.li@ixiaoshuidi.com
 **/
@Slf4j
@Aspect
@Component
public class ResubmitDataAspect {

    private final static String DATA = "username";
    private final static Object PRESENT = new Object();

    @Around("@annotation(com.example.preventDuplicateRepeat.localLockMethod.Resubmit)")
    public Object handleResubmit(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取注解信息
        Resubmit annotation = method.getAnnotation(Resubmit.class);
        int delaySeconds = annotation.delaySeconds();
        Object[] pointArgs = joinPoint.getArgs();
        String key = "";
        //获取第一个参数
        Object firstParam = pointArgs[0];
        if (firstParam instanceof User) {
            //解析参数
            Map<String, Object> data = BeanUtil.beanToMap(firstParam);
            if (data != null) {
                StringBuffer sb = new StringBuffer();
                data.forEach((k, v) -> {
                    sb.append(v);
                });
                //生成加密参数 使用了content_MD5的加密方式
                key = ResubmitLock.handleKey(sb.toString());
            }
        }
        //执行锁
        boolean lock = false;
        try {
            //设置解锁key
            lock = ResubmitLock.getInstance().lock(key, PRESENT);
            if (lock) {
                //放行
                return joinPoint.proceed();
            } else {
                //响应重复提交异常
                return "请勿重复提交";
            }
        } finally {
            //设置解锁key和解锁时间
            ResubmitLock.getInstance().unLock(lock, key, delaySeconds);
        }
    }
}
