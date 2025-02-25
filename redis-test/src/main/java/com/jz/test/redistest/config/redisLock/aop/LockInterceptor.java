package com.jz.test.redistest.config.redisLock.aop;


import com.jz.test.redistest.config.redisLock.LockInfo;
import com.jz.test.redistest.config.redisLock.LockKeyGenerator;
import com.jz.test.redistest.config.redisLock.LockTemplate;
import com.jz.test.redistest.config.redisLock.annoation.Lock4j;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Sky
 * create  2020-08-13 10:30
 * email sky.li@ixiaoshuidi.com
 */
@Slf4j
public class LockInterceptor implements MethodInterceptor {


    @Setter
    private LockTemplate lockTemplate;

    private LockKeyGenerator lockKeyGenerator = new LockKeyGenerator();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        LockInfo lockInfo = null;
        try {
            Lock4j lock4j = invocation.getMethod().getAnnotation(Lock4j.class);
            String keyName = lockKeyGenerator.getKeyName(invocation, lock4j);
            lockInfo = lockTemplate.lock(keyName, lock4j.expire(), lock4j.timeout());
            if (null != lockInfo) {
                return invocation.proceed();
            }
            return null;
        } finally {
            if (null != lockInfo) {
                lockTemplate.releaseLock(lockInfo);
            }
        }
    }

}
