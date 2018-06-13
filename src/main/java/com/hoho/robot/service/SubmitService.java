package com.hoho.robot.service;

import lombok.Cleanup;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanlf on 2018/6/13
 * email:wanlongfei007@gmail.com
 */
public class SubmitService {

    public void exceute() throws Exception {

        Connection conn2 = Jsoup.connect("http://zjjzzgl.zjsgat.gov.cn:9090/zahlw/ZzsbtLdrk/create?pageTitle=%E5%B1%85%E4%BD%8F%E7%99%BB%E8%AE%B0%E5%8F%98%E6%9B%B4");
        conn2.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        conn2.header("Accept-Encoding", "gzip, deflate");
        conn2.header("Accept-Language", "zh-CN,zh;q=0.9");
        conn2.header("Cache-Control", "max-age=0");
        conn2.header("Connection", "keep-alive");
        conn2.header("Upgrade-Insecure-Requests", "1");
        conn2.header("Host", "zjjzzgl.zjsgat.gov.cn:9090");
        conn2.header("Referer", "http://zjjzzgl.zjsgat.gov.cn:9090/zahlw/userInfo");
        conn2.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; MALC)");
        conn2.header("Cookie", "jcsid=1c9f7c30-aaee-4ed9-9219-8e9feeba3ba8;JSESSIONID=DA7BFB5D234B6DE50144F3FA9E127582;_version_key=3301;");
        Connection.Response response2 = conn2.ignoreContentType(true).method(Connection.Method.GET).execute();
        System.out.println(response2.body());
        Document doc = Jsoup.parse(response2.body());
        Elements csrfToken = doc.select("input[type=\"hidden\"]input[name=\"CSRFToken\"]");
        Elements token = doc.select("input[type=\"hidden\"]input[name=\"com.zjjcnt.core.web.taglib.form.TOKEN\"]");
        System.out.println(csrfToken.first().val());
        System.out.println(token.first().val());


        Map<String, String> map = new HashMap<String, String>();
        map.put("action", "create");
        map.put("nbbh", "");
        map.put("sbrsf", "1");
        map.put("czfwdah", "");
        map.put("dwbm", "");
        map.put("sblx", "1");
        map.put("zxbz", "0");
        map.put("sourcePath", "/zahlw/ZzsbtLdrk/saveSuccess");
        map.put("xm", "樊斌");
        map.put("xb", "1");
        map.put("sfzh", "330822199211170931");
        map.put("lxdh", "15268132137");
        map.put("hksx", "330822");
        map.put("hkxz", "華西村");
        map.put("zzcs", "01");
        map.put("zzsy", "01");
        map.put("whcd", "20");
        map.put("hyzk", "10");
        map.put("xy", "3");
        map.put("glm", "330104503001");
        map.put("xzdxz", "一组");
        map.put("fdxm", "");
        map.put("fdgmsfhm", "");
        map.put("fdlxdh", "");
        map.put("sfzzpBase64", readFile("b1.txt"));
        map.put("avatarBase64", readFile("b2.txt"));
        map.put("jzdywgzdw", "0");
        map.put("cszy", "");
        map.put("bl1", "");
        map.put("dwfzr", "");
        map.put("dwlxdh", "");
        map.put("gzdz", "");
        map.put("ldhtqk", "");
        map.put("CSRFToken", csrfToken.first().val());
        map.put("com.zjjcnt.core.web.taglib.form.TOKEN", token.first().val());

        Connection conn = Jsoup.connect("http://zjjzzgl.zjsgat.gov.cn:9090/zahlw/ZzsbtLdrk/create");
        conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        conn.header("Accept-Encoding", "gzip, deflate");
        conn.header("Accept-Language", "zh-CN,zh;q=0.9");
        conn.header("Cache-Control", "max-age=0");
        conn.header("Connection", "keep-alive");
        conn.header("Content-Type", "application/x-www-form-urlencoded");
        conn.header("Host", "zjjzzgl.zjsgat.gov.cn:9090");
        conn.header("Referer", "http://zjjzzgl.zjsgat.gov.cn:9090/zahlw/userInfo");
        conn.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; MALC)");
        conn.header("Cookie", "JSESSIONID=DCD62FE8962D4F2E1F8E1C6EBACC75FB; _version_key=3301; jcsid=21621805-314e-4984-8596-8de90130fde8");
        Connection.Response response1 = conn.ignoreContentType(true).method(Connection.Method.POST).data(map).execute();
        System.out.println(response1.body());

    }

    public static String readFile(String filePath) {
        StringBuffer sb = new StringBuffer();
        ClassPathResource resource = new ClassPathResource(filePath);
        try {
            @Cleanup InputStream is = resource.getInputStream();
            @Cleanup BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                //该行不是注释开头
                if (!line.startsWith("//")) {
                    if (line.indexOf("//") > 0) {
                        sb.append(line.substring(0, line.indexOf("//")));
                    } else {
                        sb.append(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
