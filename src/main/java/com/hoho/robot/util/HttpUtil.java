package com.hoho.robot.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil {

    public static String doPost(String url, Map dataMap) throws Exception {
        return doPost(url, dataMap, (Map) null, "UTF-8");
    }

    public static String doPost(String url, Map dataMap, String encoding) throws Exception {
        return doPost(url, dataMap, (Map) null, encoding);
    }

    public static String doPost(String url, Map dataMap, Map header, String encoding) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";

        try {
            HttpPost e = getHttpPost(url, header);
            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            if (dataMap != null && !dataMap.isEmpty()) {
                Iterator it = dataMap.entrySet().iterator();
                while (it.hasNext()) {
                    Entry entity = (Entry) it.next();
                    params.add(new BasicNameValuePair((String) entity.getKey(), (String) entity.getValue()));
                }
            }
            e.setEntity(new UrlEncodedFormEntity(params, encoding));
            httpClient = getHttpClient();
            response = httpClient.execute(e);
            HttpEntity entity1 = response.getEntity();
            result = getResult(entity1, encoding);
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpClient != null) {
                httpClient.close();
            }
            if (response != null) {
                EntityUtils.consume(response.getEntity());
                response.close();
            }
        }
        return result;
    }

    private static String getResult(HttpEntity entity, String encoding) {
        StringBuilder sbResult = new StringBuilder();

        try {
            BufferedReader e = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
            String line;
            while ((line = e.readLine()) != null) {
                sbResult.append(line);
            }
            e.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sbResult.toString();
    }

    public static CloseableHttpClient getHttpClient() {
        return HttpClients.createDefault();
    }

    private static HttpPost getHttpPost(String url, Map header) {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout('\uea60').setConnectTimeout('\uea60').setConnectionRequestTimeout('\uea60').build();
        httpPost.setConfig(requestConfig);
        if (header != null && !header.isEmpty()) {
            Iterator it = header.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                httpPost.setHeader((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return httpPost;
    }
}

