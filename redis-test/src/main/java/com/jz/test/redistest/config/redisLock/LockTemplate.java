package com.jz.test.redistest.config.redisLock;

import com.jz.test.redistest.config.redisLock.util.LockUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * @author Sky
 * create  2020-08-13 10:58
 * email sky.li@ixiaoshuidi.com
 */
@Slf4j
public class LockTemplate {

    private static final String PROCESS_ID = LockUtil.getLocalMAC() + LockUtil.getJvmPid();

    @Setter
    private LockExecutor lockExecutor;


    public LockInfo lock(String key, long expire, long timeout) throws Exception {
        Assert.isTrue(timeout > 0, "tryTimeout must more than 0");
        long start = System.currentTimeMillis();
        int acquireCount = 0;
        String value = PROCESS_ID + Thread.currentThread().getId();
        while (System.currentTimeMillis() - start < timeout) {
            boolean result = lockExecutor.acquire(key, value, expire);
            acquireCount++;
            if (result) {
                return new LockInfo(key, value, expire, timeout, acquireCount);
            }
            Thread.sleep(50);
        }
        log.info("lock failed, try {} times", acquireCount);
        return null;
    }

    public boolean releaseLock(LockInfo lockInfo) {
        return lockExecutor.releaseLock(lockInfo);
    }
}
