package com.jz.test.redistest.iecsTsdb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * @author liqi
 * create  2021-10-12 16:15
 */
@Slf4j
@Component
public class CommonDownLoadUtil {
    /**
     * @param response 客户端响应
     */
    public void downLoad(HttpServletResponse response, String downloadUrl) throws Throwable {

        if (Objects.isNull(downloadUrl)) {
            // 如果接收参数为空则抛出异常，由全局异常处理类去处理。
            throw new NullPointerException("下载地址为空");
        }
        // 读文件
        File file = new File(downloadUrl);
        if (!file.exists()) {
            log.error("下载文件的地址不存在:{}", file.getPath());
            // 如果不存在则抛出异常，由全局异常处理类去处理。
            throw new HttpMediaTypeNotAcceptableException("文件不存在");
        }
        // 获取用户名
        String fileName = file.getName();
        // 重置response
        response.reset();
        // ContentType，即告诉客户端所发送的数据属于什么类型
        response.setContentType("application/octet-stream; charset=UTF-8");
        // 获得文件的长度
        response.setHeader("Content-Length", String.valueOf(file.length()));
        // 设置编码格式
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        // 发送给客户端的数据
        OutputStream outputStream = response.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取文件
        bis = new BufferedInputStream(new FileInputStream(new File(downloadUrl)));
        int i = bis.read(buff);
        // 只要能读到，则一直读取
        while (i != -1) {
            // 将文件写出
            outputStream.write(buff, 0, buff.length);
            // 刷出
            outputStream.flush();
            i = bis.read(buff);
        }
    }
}