package com.example.base64UtilTest;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Base64Utils {

    public static void main(String[] args) throws Exception {

        //本地图片地址
        String url = "C:/Users/Administrator/Desktop/628947887489084892.jpg";
        //在线图片地址
        String string = "http://bpic.588ku.com//element_origin_min_pic/17/03/03/7bf4480888f35addcf2ce942701c728a.jpg";

//        String str = Base64Utils.ImageToBase64ByLocal(url);
        String str="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";
//        String ste = Base64Utils.ImageToBase64ByOnline(string);

        Base64Utils.Base64ToImage(str, "I:\\BASEtEST\\test1.jpg");

//        Base64Utils.Base64ToImage(ste, "C:/Users/Administrator/Desktop/test2.jpg");
    }

    /**
     * 本地图片转换成base64字符串
     *
     * @param imgFile 图片本地路径
     * @return
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:40:46
     */
    public static String ImageToBase64ByLocal(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理


        InputStream in = null;
        byte[] data = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);

            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }


    /**
     * 在线图片转换成base64字符串
     *
     * @param imgURL 图片线上路径
     * @return
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:43:18
     */
    public static String ImageToBase64ByOnline(String imgURL) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(imgURL);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 关闭流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.toByteArray());
    }


    /**
     * base64字符串转换成图片
     *
     * @param imgStr      base64字符串
     * @param imgFilePath 图片存放路径
     * @return
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:42:17
     */
    public static boolean Base64ToImage(String imgStr, String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片

        if (StringUtils.isEmpty(imgStr)) // 图像数据为空
            return false;

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }


}