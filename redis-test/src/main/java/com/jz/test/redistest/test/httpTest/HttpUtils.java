/**
 * All rights Reserved, Designed By 流浪王者
 *
 * @Title: HttpUtils.java
 * @Package com.bootdo.common.utils
 * @Description: TODO
 * @author: 流浪王者
 * @date: 2020年4月2日 下午1:38:55
 * @version V1.0
 * @Copyright: 2020 流浪王者 Inc. All rights reserved.
 */
package com.jz.test.redistest.test.httpTest;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author 流浪王者 s 2020年4月2日
 */
@Slf4j
public class HttpUtils {

    /**
     * 通过http协议发送post
     *
     * @param url
     * @param param
     * @param accessToken
     */
    public static Map<String, Object> sendPostHttp(String url, Map<String, Object> param, String accessToken) {
        try {
            RestTemplate restTemplate = new RestTemplate(SSL.getInstance());
            // 编码为utf-8
            restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            HttpHeaders headers = new HttpHeaders();
            // 定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
            headers.setContentType(MediaType.APPLICATION_JSON);
            //设置token请求头内容
            HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(param, headers);
            ResponseEntity<String> entity = restTemplate.postForEntity(url, request, String.class);
            // 获取3方接口返回的数据通过entity.getBody();它返回的是一个字符串;
            return JSONObject.parseObject(entity.getBody());
        } catch (Exception e) {
            log.error("url：" + e.getMessage());
            Map<String, Object> map = Maps.newHashMap();
            map.put("errormesg", "url:" + url + "-" + e.getMessage());
            return map;
        }
    }

    /**
     * 通过http协议发送post
     *
     * @param url
     * @param param
     * @param accessToken
     */
//    public static Map<String, Object> sendPostHttp(String url, Map<String, Object> param, String accessToken) {
//        try {
//            HttpResponse httpResponse = HttpRequest.post(url)
//                    .keepAlive(false)
//                    .header(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                    .body(JSONObject.toJSONString(param))
//                    .execute();
//            // 获取3方接口返回的数据通过entity.getBody();它返回的是一个字符串；
//            return JSONObject.parseObject(httpResponse.body());
//        } catch (Exception e) {
//            log.error("url：" + e.getMessage());
//            Map<String, Object> map = Maps.newHashMap();
//            map.put("errormesg", "url:" + url + "-" + e.getMessage());
//            return map;
//        }
//    }

}
