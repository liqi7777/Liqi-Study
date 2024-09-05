package com.example.nginxLogChange;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * @author Sky
 * create 2020/06/30
 * email sky.li@ixiaoshuidi.com
 **/
public class NginxLogChange {
    public static String str2Hex(String str) throws UnsupportedEncodingException {
        String hexRaw = String.format("%x", new BigInteger(1, str.getBytes("UTF-8")));
        char[] hexRawArr = hexRaw.toCharArray();
        StringBuilder hexFmtStr = new StringBuilder();
        final String SEP = "\\x";
        for (int i = 0; i < hexRawArr.length; i++) {
            hexFmtStr.append(SEP).append(hexRawArr[i]).append(hexRawArr[++i]);
        }
        return hexFmtStr.toString();
    }

    public static String hex2Str(String str) throws UnsupportedEncodingException {
        String strArr[] = str.split("\\\\"); // 分割拿到形如 xE9 的16进制数据
        byte[] byteArr = new byte[strArr.length - 1];
        for (int i = 1; i < strArr.length; i++) {
            Integer hexInt = Integer.decode("0" + strArr[i]);
            byteArr[i - 1] = hexInt.byteValue();
        }

        return new String(byteArr, "UTF-8");
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        System.out.println(str2Hex("中国1a23"));
        System.out.println(hex2Str(str2Hex("中国1a23")));
        System.out.println(hex2Str("\\xE6\\xB5\\x8B\\xE8\\xAF\\x95\\xE7\\x94\\xA8\\xE6\\x88\\xB7"));
    }
}
