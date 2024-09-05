package com.jz.test.redistest.test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @author liqi
 * create  2021/11/23 4:17 下午
 */
public class SwapStr {

        public static void swap(String str1, String str2)
        {
            try{
                Object temp;

                Field fv = String.class.getDeclaredField("value");
                Field fh = String.class.getDeclaredField("hash");

                fv.setAccessible(true);
                fh.setAccessible(true);

                temp = fv.get(str1);
                fv.set(str1, fv.get(str2));
                fv.set(str2, temp);

                fh.setInt(str1, 0);
                fh.setInt(str2, 0);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        public static void main(String[] args)
        {
            String str1 = "hello";
            String str2 = "world";

            swap(str1, str2);

            System.out.println(str1 + " " + str2);

            BigDecimal divide = BigDecimal.valueOf(2).divide(BigDecimal.ZERO, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(divide);
        }
}
