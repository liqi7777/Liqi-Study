package com.jz.test.redistest.config.redisLock.util;

import cn.hutool.core.util.ArrayUtil;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * @author Sky
 * create  2020-08-13 10:35
 * email sky.li@ixiaoshuidi.com
 */
public class LockUtil {
    /**
     * 获取本机网卡地址
     *
     * @return macAddress
     */
    public static String getLocalMAC() {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
            byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            // 下面代码是把mac地址拼装成String
            StringBuffer sb = new StringBuffer();
            if (ArrayUtil.isNotEmpty(mac)) {
                for (int i = 0; i < mac.length; i++) {
                    if (i != 0) {
                        sb.append("-");
                    }
                    // mac[i] & 0xFF 是为了把byte转化为正整数
                    String s = Integer.toHexString(mac[i] & 0xFF);
                    sb.append(s.length() == 1 ? 0 + s : s);
                }
            }
            // 把字符串所有小写字母改为大写成为正规的mac地址并返回
            return sb.toString().toUpperCase().replaceAll("-", "");
        } catch (Exception e) {
            throw new IllegalStateException("getLocalMAC error");
        }
    }

    /**
     * 获取jvmPId
     *
     * @return jvmPid
     */
    public static String getJvmPid() {
        String pid = ManagementFactory.getRuntimeMXBean().getName();
        int indexOf = pid.indexOf('@');
        if (indexOf > 0) {
            pid = pid.substring(0, indexOf);
            return pid;
        }
        throw new IllegalStateException("ManagementFactory error");
    }
}
