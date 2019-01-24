package demo;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestYIMEI {
    public static final String GET_TOKEN_URL = "http://opensdk.emay.cn:9080/HD_GetAccess_Token.asmx";
    public static final String URL = "http://opensdk.emay.cn:9080/MADE_API/EMDA_AT_Service.asmx";
    public static final String GET_TOKEN_METHOD = "GetACCESS_TOKEN";
    public static final String MD5_METHOD = "GetEmda_AT_Info_MD5";
    public static final String SHA256_METHOD = "GetEmda_AT_Info_SHA256";
    public static final String CHARSET = "UTF-8";
    private static final Integer DEFAULT_TIMEOUT = 2000;
    private static final PoolingHttpClientConnectionManager CM = new PoolingHttpClientConnectionManager();
    private static final String AppID = "0F3445F1WCE84W45A7W81B1W7C0FAFE5938F";
    private static final String AppSecret = "252B6FDEL6614L4C47LB8CFL6B6B94699ACE";
    private static final String Key = "3398D19EHC184H4BF4HBDC5HD4EE497F9037";


    public static void main(String[] args) {
        get_token(AppID, AppSecret, Key);
        //get_md5();
    }

    //get调用
    public static void get_md5() {
        Map<String, String> params = new HashMap<>();
        params.put("Phone","005de447ad73e3e7d072997590babd62");
        params.put("cycle","12");
        params.put("ACCESS_TOKEN","81AF0F8D7BAC4F9F9461FC32ED9C526FD40B13A275852EC8364AC717E7CFA220ECF648715E6D43F286A9EEDF90B394F6");
        params.put("Platform","0");
        System.out.println(get(URL+"/"+MD5_METHOD,params));
    }

    //获取token
    public static String get_token(String AppID, String AppSecret, String Key) {
        Map<String, String> params = new HashMap<>();
        params.put("AppID", AppID);
        params.put("AppSecret", AppSecret);
        params.put("Key", Key);
        //JSONObject get_token_result = JSONObject.parseObject();
        System.out.println(get(GET_TOKEN_URL + "/" + GET_TOKEN_METHOD, params));
        //
        //System.out.println(get_token_result.toJSONString());
        //return get_token_result.getString("access_token");
        return null;
    }

    public static String get(String url, final Map<String, String> params) {
        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            url = setGetParam(url, params);
            System.out.println("url:" + url);
            HttpGet httpget = new HttpGet(url);

            // 执行get请求.
            response = httpClient.execute(httpget);
            // 获取响应实体
            entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && entity != null) {
                String entityInfoStr = EntityUtils.toString(entity, CHARSET);
                return entityInfoStr;
            } else {

            }
        } catch (Exception e) {

        } finally {
            // 关闭连接,释放资源
            try {
                if (response != null) {
                    response.close();
                }
                EntityUtils.consumeQuietly(entity);
            } catch (Exception e) {

            }
        }
        return null;
    }

    public static CloseableHttpClient getHttpClient() {
        return HttpClients.custom().setConnectionManager(CM).setDefaultRequestConfig(requestConfig()).build();
    }

    private static RequestConfig requestConfig() {
        return RequestConfig.custom().setStaleConnectionCheckEnabled(true)
                // 分配的 socket 的 soTimeout, 后续处理过程使用这个超时时间
                .setSocketTimeout(DEFAULT_TIMEOUT)
                // socket 建立网络连接, 超时时间
                .setConnectTimeout(DEFAULT_TIMEOUT)
                // 从连接池获取连接, 最长的等待时间
                .setConnectionRequestTimeout(DEFAULT_TIMEOUT).build();
    }

    private static String setGetParam(final String url, final Map<String, String> params)
            throws UnsupportedEncodingException {
        // params 存在的话,拼接params中的参数
        StringBuffer stbURL = new StringBuffer(url);
        if (params != null) {
            if (url != null) {
                if (url.lastIndexOf("?") != -1) { // 判断URL中是否已经有"?"(即已经在url中有参数)
                    for (Map.Entry<String, String> entrySet : params.entrySet()) {
                        stbURL.append("&").append(entrySet.getKey()).append("=")
                                .append(URLEncoder.encode(entrySet.getValue(), CHARSET));
                    }
                } else {
                    stbURL.append("?");
                    for (Map.Entry<String, String> entrySet : params.entrySet()) {
                        stbURL.append(entrySet.getKey()).append("=")
                                .append(URLEncoder.encode(entrySet.getValue(), CHARSET)).append("&");
                    }
                    // 删除末尾多余的&
                    stbURL.delete(stbURL.length() - 1, stbURL.length());
                }
            }
        }
        return stbURL.toString();
    }


}
