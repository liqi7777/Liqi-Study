package com.example.test;

import cn.hutool.core.date.DateUtil;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Sky
 * create 2018/11/06
 * email sky.li@ixiaoshuidi.com
 **/
public class UserTest {
    public static void main(String[] args) throws ParseException, NoSuchAlgorithmException, UnsupportedEncodingException {
//        SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = yyyyMMddFormat.parse("2019-01-01");
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DAY_OF_YEAR, -1);
//        String previousDay = yyyyMMddFormat.format(calendar.getTime());
//        System.out.println(previousDay);
//        calendar.clear();
//        calendar.setTime(new Date());
//        System.out.println(yyyyMMddFormat.format(calendar.getTime()));

//        String brand = "bite";
//        String s = brand.split("-")[0];
//        System.out.println(s);
//
//        BigDecimal currentReading = new BigDecimal("00021648").divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
//        System.out.println(currentReading);
//        System.out.println("COwGy4wE23NkgS8J3Jkp8PpIhMuik+fSKzEebGJ0N6r45yL29cwYxwr4PSzQ6TjQM3R6kSy/Y+byrq6Mcos67ml73vrQeSyMBdijze2LQKqPjEk8u1vISgMVWF98UZRtcp42nU7Tzg/1GSN/y1YrhYkRaraRUMCqf7JagzYEGDJo5jF+vntqdMSDUaJAeXReQq8WlV99ltbtWty/wIq0Ii4LiInNP9oBaIS87tlBAbW5sO5xL/dDV4KCv2/V/igv3ZL7W6hPgOzl9s7MN3vZd/0i1QOSFdhanyEVFfq9VjbozDvOhoeOYATSpXpBFd+rvBTi3BvQo/ah4ChQr15wSQ==".equals("COwGy4wE23NkgS8J3Jkp8PpIhMuik+fSKzEebGJ0N6r45yL29cwYxwr4PSzQ6TjQM3R6kSy/Y+byrq6Mcos67ml73vrQeSyMBdijze2LQKqPjEk8u1vISgMVWF98UZRtcp42nU7Tzg/1GSN/y1YrhYkRaraRUMCqf7JagzYEGDJo5jF+vntqdMSDUaJAeXReQq8WlV99ltbtWty/wIq0Ii4LiInNP9oBaIS87tlBAbW5sO5xL/dDV4KCv2/V/igv3ZL7W6hPgOzl9s7MN3vZd/0i1QOSFdhanyEVFfq9VjbozDvOhoeOYATSpXpBFd+rvBTi3BvQo/ah4ChQr15wSQ=="));
        System.out.println("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlsjcUZOpelTt/MHh4H1EkyWK8whFpIbLPBhCDyZtZOG2uASDLzJFLY0UIX2gWBPGcpvq5CgkeIvrhtYPUGS0LGJGZ9rCzW1aX8N9v5DEN9kZSfDiQqJMkTDffbEIwKYDlH5tqvG8XnM/voBpDgdNO5+2NBgB0ogWACekwtMlp1M1eNj8z7xn175fL33QsI8GCDy3vaRYGMHp0EGAzq88ZbHM8B+Z9izL7caFM9B/DVx5Zz3rqBGGStLNtMFz3ilMxEVO6iwebrRQL9s9UQ65NEHcP9sGS/+ZB4Tx2OjYhCCDKRgOXF8m8RJXiGIjJ1NWRZvSLjyYehIy1YzTJDj4awIDAQAB".equals("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlsjcUZOpelTt/MHh4H1EkyWK8whFpIbLPBhCDyZtZOG2uASDLzJFLY0UIX2gWBPGcpvq5CgkeIvrhtYPUGS0LGJGZ9rCzW1aX8N9v5DEN9kZSfDiQqJMkTDffbEIwKYDlH5tqvG8XnM/voBpDgdNO5+2NBgB0ogWACekwtMlp1M1eNj8z7xn175fL33QsI8GCDy3vaRYGMHp0EGAzq88ZbHM8B+Z9izL7caFM9B/DVx5Zz3rqBGGStLNtMFz3ilMxEVO6iwebrRQL9s9UQ65NEHcP9sGS/+ZB4Tx2OjYhCCDKRgOXF8m8RJXiGIjJ1NWRZvSLjyYehIy1YzTJDj4awIDAQAB"));
        System.out.println("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCWyNxRk6l6VO38weHgfUSTJYrzCEWkhss8GEIPJm1k4ba4BIMvMkUtjRQhfaBYE8Zym+rkKCR4i+uG1g9QZLQsYkZn2sLNbVpfw32/kMQ32RlJ8OJCokyRMN99sQjApgOUfm2q8bxecz++gGkOB007n7Y0GAHSiBYAJ6TC0yWnUzV42PzPvGfXvl8vfdCwjwYIPLe9pFgYwenQQYDOrzxlsczwH5n2LMvtxoUz0H8NXHlnPeuoEYZK0s20wXPeKUzERU7qLB5utFAv2z1RDrk0Qdw/2wZL/5kHhPHY6NiEIIMpGA5cXybxEleIYiMnU1ZFm9IuPJh6EjLVjNMkOPhrAgMBAAECggEASPmxV3HlpiUQgTG8hg9abvTOiMzKF9R86/q5jeeFLjvnWKwmaWMlA+Sxh3vrbHU1qCY5ym57KDRfqwRSqQ/z+z8c4AqKGS4BfTN52orPzE+v449HkgRGlkZXA9f4anRuayG9JeyUxAl3IlW3gmiaQm8Lma68ww3FyBFntjrGkHH64oB77ZabwowmfYMhxpjls7e8WHuabLgalJ8thSovFWBrxIVEeeOTW1kkJLai+Z4CfGG1V/i18PndCHL0Ehlh5L/7smt0SI90YuERC7wpiepZz0AOhmZpAAmsYcbqF0yVWck/rb+xzfQudVqT1hfI3Ke+keXjdsBmIz5Ze7mJsQKBgQD8HgI04/WWA+/+DxNOwFTJUNhWBrCnYwYralpUtTk33gL+W+O4BFl1lKS9yom3al1vYh/DsmcxJFFa8Lbn3UYDW4FvIXG6GvOZx02Ju48vNjC72YkmLjvRwb+g2L3Sw3vUhlp6vbpctPV04xdTQn/SqPwc9QOnAgXbW/wad/5V7QKBgQCZG1coJPbdPytFCrUv+F+3kfJWDuU0Dos1qtUXf8oFHVuZvONNUEYAdsPeD1ubGSIp8jrTsxxD7d0zZ6jAv3OZO0scP+3pjI86mDnb9yYqTPBYB3Pv+vyewHqO6R9ijXeG7Ds7dXQoL4F/JgjUPzGRO3mR7F2PG+jKDjMCfZ48twKBgE5GFtNK7wVbRZhwH8N0bR5vjirvOstNMC4sf80K0q/oqq9D4ihQmWGq+5a9GhKq4EVjho8ghW85YlTEpVTJWyJRt5bEIGXyVi0pNoaLh8HVpj28JRuIgSP3bkJG6HBWJsztxYuT+hcsS4S1uJkt7Td/JX7sBu1D8IORRG0HUhxlAoGBAI0Z41L97fyssMXqPl09O4lEGeAxzg4RsT3+eHA1twrLqZWfvKON7VWUHqLR6IyhtxiNQqXDlbMMziUHzUAFsRVwzaH1UN9CSOWpUPllZmjpiz61b6Wk25e6r7hxJBmBYryXjWcYsbovPdreRJjGvHmqJDnVacvv3rQILf6HooM1AoGAFJmR0RvlWv4FjkrV3fwqBA/PLr+jf5+Einbuv5Lo2aV5AWgoKL4qjBcoYU5TK8Q0PFyZos+TlEs+wnZ0z3IONQwig4piJuA9uQRMOXcODnQrLoXNc9s2In7lBFULwm0r7jmDMgftmdS0GVixPKGBSDBGfn0K+KuDWp8W/CBiwyI=".equals("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCWyNxRk6l6VO38weHgfUSTJYrzCEWkhss8GEIPJm1k4ba4BIMvMkUtjRQhfaBYE8Zym+rkKCR4i+uG1g9QZLQsYkZn2sLNbVpfw32/kMQ32RlJ8OJCokyRMN99sQjApgOUfm2q8bxecz++gGkOB007n7Y0GAHSiBYAJ6TC0yWnUzV42PzPvGfXvl8vfdCwjwYIPLe9pFgYwenQQYDOrzxlsczwH5n2LMvtxoUz0H8NXHlnPeuoEYZK0s20wXPeKUzERU7qLB5utFAv2z1RDrk0Qdw/2wZL/5kHhPHY6NiEIIMpGA5cXybxEleIYiMnU1ZFm9IuPJh6EjLVjNMkOPhrAgMBAAECggEASPmxV3HlpiUQgTG8hg9abvTOiMzKF9R86/q5jeeFLjvnWKwmaWMlA+Sxh3vrbHU1qCY5ym57KDRfqwRSqQ/z+z8c4AqKGS4BfTN52orPzE+v449HkgRGlkZXA9f4anRuayG9JeyUxAl3IlW3gmiaQm8Lma68ww3FyBFntjrGkHH64oB77ZabwowmfYMhxpjls7e8WHuabLgalJ8thSovFWBrxIVEeeOTW1kkJLai+Z4CfGG1V/i18PndCHL0Ehlh5L/7smt0SI90YuERC7wpiepZz0AOhmZpAAmsYcbqF0yVWck/rb+xzfQudVqT1hfI3Ke+keXjdsBmIz5Ze7mJsQKBgQD8HgI04/WWA+/+DxNOwFTJUNhWBrCnYwYralpUtTk33gL+W+O4BFl1lKS9yom3al1vYh/DsmcxJFFa8Lbn3UYDW4FvIXG6GvOZx02Ju48vNjC72YkmLjvRwb+g2L3Sw3vUhlp6vbpctPV04xdTQn/SqPwc9QOnAgXbW/wad/5V7QKBgQCZG1coJPbdPytFCrUv+F+3kfJWDuU0Dos1qtUXf8oFHVuZvONNUEYAdsPeD1ubGSIp8jrTsxxD7d0zZ6jAv3OZO0scP+3pjI86mDnb9yYqTPBYB3Pv+vyewHqO6R9ijXeG7Ds7dXQoL4F/JgjUPzGRO3mR7F2PG+jKDjMCfZ48twKBgE5GFtNK7wVbRZhwH8N0bR5vjirvOstNMC4sf80K0q/oqq9D4ihQmWGq+5a9GhKq4EVjho8ghW85YlTEpVTJWyJRt5bEIGXyVi0pNoaLh8HVpj28JRuIgSP3bkJG6HBWJsztxYuT+hcsS4S1uJkt7Td/JX7sBu1D8IORRG0HUhxlAoGBAI0Z41L97fyssMXqPl09O4lEGeAxzg4RsT3+eHA1twrLqZWfvKON7VWUHqLR6IyhtxiNQqXDlbMMziUHzUAFsRVwzaH1UN9CSOWpUPllZmjpiz61b6Wk25e6r7hxJBmBYryXjWcYsbovPdreRJjGvHmqJDnVacvv3rQILf6HooM1AoGAFJmR0RvlWv4FjkrV3fwqBA/PLr+jf5+Einbuv5Lo2aV5AWgoKL4qjBcoYU5TK8Q0PFyZos+TlEs+wnZ0z3IONQwig4piJuA9uQRMOXcODnQrLoXNc9s2In7lBFULwm0r7jmDMgftmdS0GVixPKGBSDBGfn0K+KuDWp8W/CBiwyI="));
        long expiredDateTime = DateUtil.parseDateTime("2019-09-06 10:54:05").getTime();
        System.out.println(expiredDateTime);

        System.out.println("\n" +
                "{\\x22dataRequestBodyVO\\x22:{\\x22dt\\x22:{\\x22dataType\\x22:\\x224\\x22,\\x22factoryType\\x22:\\x22YD\\x22,\\x22houseCatalog\\x22:\\x220\\x22,\\x22roomId\\x22:\\x22958965\\x22,\\x22thirdHomeId\\x22:\\x225da55b74df6ab962431a6d3a\\x22,\\x22thirdRoomId\\x22:\\x225da55b75755c3e61f963d734\\x22,\\x22type\\x22:\\x220\\x22,\\x22userId\\x22:\\x2244270\\x22,\\x22uuid\\x22:\\x22null,89e9b5849184293ea6583cadec6ac11d,39a1ae3c6fad23f0f9ee258c2deb6058\\x22}},\\x22headerRequestDataVO\\x22:{\\x22bid\\x22:\\x22b001\\x22,\\x22cid\\x22:\\x22c1234\\x22,\\x22crt\\x22:\\x2220150102:040506\\x22,\\x22enc\\x22:\\x221.2.3\\x22,\\x22pid\\x22:\\x22validate_token\\x22,\\x22pvr\\x22:\\x221.0\\x22,\\x22rnd\\x22:\\x22s002\\x22,\\x22tkn\\x22:\\x22XXXXXX\\x22,\\x22utk\\x22:\\x22ABC\\x22,\\x22ver\\x22:\\x221.1.1\\x22}}".replaceAll("x22", "").replaceAll("\\\\", ""));

        String[] split = "".split(",");
        System.out.println(split.length);
        System.out.println(isSameMonth(new Date(),new Date(1580969264000L)));

        System.out.println(new Date().before(new Date(1580969264000L)));


        String s = "02 1-38704537 ".replaceAll("-", "").replaceAll(" ","");
        Long aLong = Long.valueOf(s);
        System.out.println(aLong);
        System.out.println(s);
        byte[] bytes = "skip".getBytes();
        for (byte aByte : bytes) {
            String tString = Integer.toBinaryString((aByte & 0xFF) + 0x100).substring(1);
            System.out.println("tString:" + tString);
        }

    }

//    public static void testreturn() {
//        if (1 > 0) {
////            return;
//        }
//        System.out.println("hahahah");
//    }


    public static boolean isSameMonth(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameMonth(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameMonth(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(2) == cal2.get(2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

}
