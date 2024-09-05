package com.example.fileuploadanddownload;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class HttpClientFileTest {
    public static void main(String[] args) {
//        HttpPostFileBodyMethod();

//         httpFileInputStreamMethod();

        httpUrlInputStreamMethod();
    }



    private static void HttpPostFileBodyMethod() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8081/fileUpload");//建立HttpPost对象,改成自己的地址
        File file = new File("F:\\fileuploadtest.png");//相对路径使用不了的话,使用图片绝对路径
        if(!file.exists()){//判断文件是否存在
            System.out.print("文件不存在");
            return;
        }

        FileBody bin = new FileBody(file, ContentType.create("image/jpeg", Consts.UTF_8));//创建图片提交主体信息
        HttpEntity entity = MultipartEntityBuilder
                .create()
                .setCharset(Charset.forName("utf-8"))
                .addPart("fileName", bin)//添加到请求
                .addTextBody("id","789")
                .build();
        httpPost.setEntity(entity);
        HttpResponse response= null;//发送Post,并返回一个HttpResponse对象
        try {
            response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode()==200) {//如果状态码为200,就是正常返回
                String result = EntityUtils.toString(response.getEntity());
                System.out.print(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(response);
        System.out.print("结束");
    }


    public  static  void httpFileInputStreamMethod() {
        //创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //构建POST请求   请求地址请更换为自己的。
        //1)
        HttpPost post = new HttpPost("http://127.0.0.1:8081/fileUpload");
        InputStream inputStream = null;
        try {
            //文件路径请换成自己的
            //2)
            inputStream = new FileInputStream("F:\\fileuploadtest.png");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            //第一个参数为 相当于 Form表单提交的file框的name值 第二个参数就是我们要发送的InputStream对象了
            //第三个参数是文件名
            //3)
            builder.addBinaryBody("fileName", inputStream, ContentType.create("image/jpeg", Consts.UTF_8), "fileuploadtest.jpeg");
            //4)构建请求参数 普通表单项
            StringBody stringBody = new StringBody("39852",ContentType.TEXT_PLAIN);
            builder.addPart("id",stringBody);
            HttpEntity entity = builder.build();
            post.setEntity(entity);
            //发送请求
            HttpResponse response = client.execute(post);
            entity = response.getEntity();
            if (entity != null) {
                inputStream = entity.getContent();
                //转换为字节输入流
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Consts.UTF_8));
                String body = null;
                while ((body = br.readLine()) != null) {
                    System.out.println(body);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static void httpUrlInputStreamMethod() {
        //创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //构建POST请求   请求地址请更换为自己的。
        //1)
        HttpPost post = new HttpPost("http://127.0.0.1:8081/fileUpload");
        InputStream inputStream = null;
        try {
            //文件路径请换成自己的
            //2)
            inputStream = new URL("https://link.bi.sensetime.com/v1/image/2/5df89ceba832a000016dc036").openStream();
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            //第一个参数为 相当于 Form表单提交的file框的name值 第二个参数就是我们要发送的InputStream对象了
            //第三个参数是文件名
            //3)
            builder.addBinaryBody("fileName", inputStream, ContentType.create("image/jpeg", Consts.UTF_8), "5df89ceba832a000016dc036.jpeg");
            //4)构建请求参数 普通表单项
            StringBody stringBody = new StringBody("39852",ContentType.TEXT_PLAIN);
            builder.addPart("id",stringBody);
            HttpEntity entity = builder.build();
            post.setEntity(entity);
            //发送请求
            HttpResponse response = client.execute(post);
            entity = response.getEntity();
            if (entity != null) {
                inputStream = entity.getContent();
                //转换为字节输入流
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Consts.UTF_8));
                String body = null;
                while ((body = br.readLine()) != null) {
                    System.out.println(body);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}