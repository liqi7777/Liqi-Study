package com.example.jwtDemo.fapiao;

/**
 *
 */
import com.alibaba.fastjson.JSONObject;
import com.google.gson.GsonBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.compression.CompressionCodecs;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.util.*;
import java.util.Map.Entry;
/**
 * @author Sky
 * create  2020-09-21 15:39
 * email sky.li@ixiaoshuidi.com
 */
public class InsertWithArray {

    //测试环境有测试appid和证书，正式环境有正式appid和证书，请务必对应使用
    //测试环境appid就用这个，正式环境需要替换成正式的
    private static String APPID = "commontesterCA";
    //这个是测试环境的域名，正式环境为https://fapiao.yonyoucloud.com
    private static String DOMAIN = "https://yesfp.yonyoucloud.com";
    private static String URL = DOMAIN + "/invoiceclient-web/api/invoiceApply/insertWithArray?appid=" + APPID;
    //pro22.pfx为测试环境通讯证书，正式环境需要替换成正式的
    private static String KEYPATH = "/Users/liqi/IdeaProjects/RedisUtil/demo/src/main/resources/certificate/pro22.pfx";
    //证书密码
    private static String PASSWORD = "password";

    public static void main(String[] args) {
        try {
            new InsertWithArray().callInvoiceApply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }


    public void callInvoiceApply() throws Exception {
//        提供两种构建HttpClient实例的方法，如果使用被注释掉的方法构建实例报证书不被信任的错误，那么请使用未被注释的构建方法
//        HttpClient httpClient = HttpClients.custom().build();
        HttpClient httpClient = createSSLClientDefault();    //信任所有https证书
        HttpPost httpPost = new HttpPost(URL);
        // 构造POST表单Map
        Map<String, String> paramsMap = buildPostParam();
        System.out.println("构造POST表单Map:"+ JSONObject.toJSONString(paramsMap));
        // 签名
        String sign = this.sign(paramsMap);
        System.out.println("sign:"+ sign);

        httpPost.addHeader("sign", sign);
        // 转换POST表单参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator<Entry<String, String>> iterator = paramsMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> elem = iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }
        if (list.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
            httpPost.setEntity(entity);
        }
        // 发送http post请求，并得到响应结果
        HttpResponse response = httpClient.execute(httpPost);
        String result;
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, "UTF-8");
                System.out.println(result);
            }
        }
    }

    /**
     * 签名
     *
     * @param paramsMap 表单参数
     * @return 签名值
     * @throws Exception
     */
    private String sign(Map<String, String> paramsMap) throws Exception {

        PrivateKey privateKey = loadPrivateKeyOfCA();
        Map<String, Object> claims =
                JwtParamBuilder.build().setSubject("tester").setIssuer("einvoice").setAudience("einvoice")
                        .addJwtId().addIssuedAt().setExpirySeconds(3600).setNotBeforeSeconds(3600).getClaims();
        // 需要将表单参数requestdatas的数据进行md5加密，然后放到签名数据的requestdatas中。
        // 此签名数据必须存在，否则在验证签名时会不通过。
        String value = paramsMap.get("requestdatas");
        claims.put("requestdatas", getMD5(value));
        System.out.println("claims打印："+JSONObject.toJSONString(claims));
        // 使用jdk1.6版本时，删除下面代码的中.compressWith(CompressionCodecs.DEFLATE)
        String compactJws = Jwts.builder().signWith(SignatureAlgorithm.RS512, privateKey)
                .setClaims(claims).compressWith(CompressionCodecs.DEFLATE).compact();
        return compactJws;
    }
//    /**
//     * 当在linux环境下运行代码，签名方法报空指针异常的时候，采用该签名方法可以避免这个问题
//     * 使用该方法需要添加新的maven依赖，如下：
//     * <dependency>
//     *     <groupId>com.auth0</groupId>
//     *     <artifactId>java-jwt</artifactId>
//     *     <version>3.3.0</version>
//     * </dependency>
//     * @param paramsMap
//     * @return
//     * @throws Exception
//     */
//    private String sign(Map<String, String> paramsMap) throws Exception {
//
//        RSAPrivateKey privateKey = loadPrivateKeyOfCA();
//        Map<String, Object> claims =
//                JwtParamBuilder.build().setSubject("tester").setIssuer("einvoice").setAudience("einvoice")
//                        .addJwtId().addIssuedAt().setExpirySeconds(300).setNotBeforeSeconds(300).getClaims();
//        String token;
//        try {
//            Algorithm algorithm = Algorithm.RSA512(null, privateKey);
//            token = JWT.create()
//                    .withClaim("requestdatas",getMD5(paramsMap.get("requestdatas")))
//                    .withHeader(claims)
//                    .sign(algorithm);
//        } catch (JWTCreationException exception){
//            //Invalid Signing configuration / Couldn't convert Claims.
//            return null;
//        }
//
//        return token;
//    }
    /**
     * 计算参数MD5
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    private static String getMD5(String str) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {
        byte[] buf = null;
        buf = str.getBytes("utf-8");
        MessageDigest md5 = null;
        md5 = MessageDigest.getInstance("MD5");
        md5.update(buf);
        byte[] tmp = md5.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : tmp) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
    /**
     * 读取证书私钥
     *
     * @return
     * @throws UnrecoverableKeyException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws IOException
     */
    protected RSAPrivateKey loadPrivateKeyOfCA() throws UnrecoverableKeyException, KeyStoreException,
            NoSuchAlgorithmException, CertificateException, IOException {
        FileInputStream in = new FileInputStream(KEYPATH);
        KeyStore ks = KeyStore.getInstance("pkcs12");
        ks.load(in, PASSWORD.toCharArray());
        String alias = ks.aliases().nextElement();
        RSAPrivateKey caprk = (RSAPrivateKey) ks.getKey(alias, PASSWORD.toCharArray());
        return caprk;
    }
    /**
     * post表单数据
     *
     * @return
     */
    private Map<String, String> buildPostParam() {
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("requestdatas", this.buildRequestDatas());
        paramsMap.put("email", this.buildEmailConfigs());
        paramsMap.put("sms", this.buildSmsConfigs());
        paramsMap.put("url", this.buildUrlConfigs());
        paramsMap.put("autoAudit", "false");
        return paramsMap;
    }
    /**
     * url回掉配置
     *
     * @return
     */
    private String buildUrlConfigs() {
        List<Object> datas = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("fpqqlsh", buildFpqqlsh());
        data.put("url", "http://117.12.12.3:7787/EinvoiceRESTService/CallBackEInvoices/");
        datas.add(data);
        GsonBuilder builder = new GsonBuilder();
        return builder.create().toJson(datas);
    }
    /**
     * 构造短信发送信息
     *
     * @return
     */
    private String buildSmsConfigs() {
        List<Object> datas = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("fpqqlsh", buildFpqqlsh());
        data.put("address", "123123123123");
        datas.add(data);
        GsonBuilder builder = new GsonBuilder();
        return builder.create().toJson(datas);
    }
    /**
     * 构造email发送信息
     *
     * @return
     */
    private String buildEmailConfigs() {
        List<Object> datas = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("fpqqlsh", buildFpqqlsh());
        data.put("address", "123@163.com");
        datas.add(data);
        GsonBuilder builder = new GsonBuilder();
        return builder.create().toJson(datas);
    }
    /**
     * 构造requestdatas
     *
     * @return
     */
    private String buildRequestDatas() {
        List<Object> datas = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("FPQQLSH", buildFpqqlsh());
        //测试环境请一定要使用测试纳税人识别号
        data.put("XSF_NSRSBH", "201609140000001");
        data.put("GMF_MC", "购买方名称");
        data.put("GMF_DZDH", "购买方地址电话");
        //组织编码，测试环境请一定使用测试环境的组织编码
        data.put("ORGCODE", "20160914001");
        data.put("JSHJ", 117);
        data.put("items", buildItems());
        datas.add(data);
        GsonBuilder builder = new GsonBuilder();
        return builder.create().toJson(datas);
    }
    /**
     * 构造request发票明细
     *
     * @return
     */
    private List<Object> buildItems() {
        List<Object> items = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("XMJSHJ", "117");
        data.put("XMMC", "项目名称");
        //税率16%需要写成0.16的格式
        data.put("SL", 0.17);
        //SPBM字段为商品税收分类编码，不同的商品会有不同的编码，不对应的话会影响报税，需要咨询下公司财务
        data.put("SPBM", "1010101020000000000");
        items.add(data);
        return items;
    }
    /**
     * 获取发票请求流水号
     * 长度不超过20位，长度在1到20位的字母和数字组合，不可以重复的，不要包含window系统文件名限制的特殊字符
     *
     * @return 发票请求流水号
     */
    private String buildFpqqlsh() {
        return "12345678901234567890";
    }
}
