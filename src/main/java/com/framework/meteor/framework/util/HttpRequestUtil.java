package com.framework.meteor.framework.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

/** 
 * Http请求工具类 
 * @author snowfigure 
 * @since 2014-8-24 13:30:56 
 * @version v1.0.1 
 */
public class HttpRequestUtil {
    static boolean proxySet = false;
    static String proxyHost = "127.0.0.1";
    static int proxyPort = 8080;
    /** 
     * 编码
     */ 
    public static String urlEncode(String source,String encode) {  
        String result = source;  
        try {  
            result = URLEncoder.encode(source,encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "0";
        }
        return result;
    }
    public static String urlEncodeGBK(String source) {
        String result = source;
        try {
            result = URLEncoder.encode(source,"GBK");
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return "0";  
        }  
        return result;  
    }
    /** 
     * 发起http请求获取返回结果
     */ 
    public static String httpRequest(String req_url) {
        StringBuffer buffer = new StringBuffer();  
        try {  
            URL url = new URL(req_url);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  

            httpUrlConn.setDoOutput(false);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  

            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.connect();  

            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  

            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  

        } catch (Exception e) {  
            System.out.println(e.getStackTrace());  
        }  
        return buffer.toString();  
    }  

    /** 
     * 发送http请求取得返回的输入流 
     * @param requestUrl 请求地址 
     * @return InputStream 
     */ 
    public static InputStream httpRequestIO(String requestUrl) {  
        InputStream inputStream = null;  
        try {  
            URL url = new URL(requestUrl);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.connect();  
            // 获得返回的输入流  
            inputStream = httpUrlConn.getInputStream();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return inputStream;  
    }


    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     *
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param isproxy 是否使用代理模式
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param,boolean isproxy) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = null;
            if(isproxy){//使用代理模式
                @SuppressWarnings("static-access")
                Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress(proxyHost, proxyPort));
                conn = (HttpURLConnection) realUrl.openConnection(proxy);
            }else{
                conn = (HttpURLConnection) realUrl.openConnection();
            }
            // 打开和URL之间的连接

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");    // POST方法
            conn.setUseCaches(false);
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(300000);

            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Charset","UTF-8");
            conn.setRequestProperty("X-Internal-Token","499C03A5-EF22-11E9-9DB3-00163E30EF9B");
            conn.connect();

            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");

            // 发送请求参数
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String httpPostWithJSON(String url,String  jsonParam) throws Exception {

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("X-Internal-Token","499C03A5-EF22-11E9-9DB3-00163E30EF9B");
//        httpPost.addHeader("accept","application/json;charset=utf-8");
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;
        StringEntity entity = new StringEntity(jsonParam,"utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");

        httpPost.setEntity(entity);
        System.out.println();

        HttpResponse resp = client.execute(httpPost);
        if(resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he,"UTF-8");
        }
        System.out.println("******************" + jsonParam);
        System.out.println("******************" + url);
        System.out.println("******************" + respContent);
        return respContent;
    }

    public static String httpGetJsonWithJson(String url) throws IOException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("X-Internal-Token","499C03A5-EF22-11E9-9DB3-00163E30EF9B");
        httpGet.addHeader("contenty-type", "application/json");
        HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        if (httpEntity != null) {
            String result = EntityUtils.toString(httpEntity, "UTF-8");
            //System.out.println(result);
            System.out.println("******************" + url);
            System.out.println("******************" + result);
            return result;
        }
        return null;
    }

    /**
     * get
     * 微信专用
     */
    public static JSONObject httpGetJson(String url) throws IOException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        if (httpEntity != null) {
            String result = EntityUtils.toString(httpEntity, "UTF-8");
            //System.out.println(result);
            JSONObject resultJson = JSONObject.fromObject(result);
            return resultJson;
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
//        JSONObject jsonParam = new JSONObject();
//        jsonParam.put("loginName", "0016");
//        String url = "http://www.wxsnnu.com:8088/service/deviceInfo/queryByLoginName";
//        String result = httpPostWithJSON(url,jsonParam);
//        System.out.println(result);

        String request = HttpRequestUtil.httpRequest("http://f.apiplus.net/ssq-2.json");
        System.out.println(request);
        JSONObject json = JSONObject.fromObject(request);
        JSONArray data = json.getJSONArray("data");
        String s1 = data.getJSONObject(0).getString("opencode");
        System.out.println(s1.substring(0,17));
        String s2 = data.getJSONObject(1).getString("opencode");
        System.out.println(s2.substring(0,17));
    }

    public static String readRequestStr(HttpServletRequest request){
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

//    public static Object formatJson2Object(JSONObject json) {
//        Object bean = JSONObject.toBean(json, ResponseBody.class);
//        ResponseBody responseBody = (ResponseBody) bean;
//        if (!responseBody.getCode().equals("200")) {
//            return null;
//        }
//        if (responseBody.getObject() != null) {
//            return responseBody.getObject();
//        } else if (responseBody.getObjects() != null) {
//            return responseBody.getObjects();
//        } else {
//            return null;
//        }
//    }
}
