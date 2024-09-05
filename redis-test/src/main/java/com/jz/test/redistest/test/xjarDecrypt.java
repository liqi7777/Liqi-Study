package com.jz.test.redistest.test;

import io.xjar.XKit;
import io.xjar.boot.XBoot;
import io.xjar.key.XKey;

/**
 * @author liqi
 * create  2024/5/9 8:14 下午
 */
public class xjarDecrypt {
    public static void main(String[] args) {
        // Spring-Boot Jar包解密
        try {
            String password = "clouddream";
            XKey xKey = XKit.key(password);
            XBoot.decrypt("/Users/liqi/Desktop/未命名文件夹/cms-production-platform-web-1.0.0-RELEASE-exec-final.jar", "/Users/liqi/Desktop/未命名文件夹/cms-production-platform-web-1.0.0-RELEASE-exec-decrpyt.jar", xKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
