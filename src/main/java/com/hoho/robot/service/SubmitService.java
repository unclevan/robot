package com.hoho.robot.service;

import lombok.Cleanup;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanlf on 2018/6/13
 * email:wanlongfei007@gmail.com
 */
@Service
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
        //TODO 此处需要读取登录时保存的cookie信息
        conn2.header("Cookie", getCookiesFromFile("蔡马人家社区"));
        Connection.Response response2 = conn2.ignoreContentType(true).method(Connection.Method.GET).execute();
//        System.out.println(response2.body());
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
        map.put("xm", "斌斌冯");
        map.put("xb", "1");
        map.put("sfzh", "110101200006012613");
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
        //TODO 此处需要读取登录时保存的cookie信息
        conn.header("Cookie", getCookiesFromFile("蔡马人家社区"));
        Connection.Response response1 = conn.ignoreContentType(true).method(Connection.Method.POST).data(map).execute();
        System.out.println(response1.body());
        Document doc1 = Jsoup.parse(response1.body());
        Elements success1 = doc1.select("h4[class=\"block\"]");
        Elements success2 = doc1.select("h5[class=\"block\"]");
        Elements success3 = doc1.select("p[style=\"font-size: 14px; line-height: 25px;\"]");
        Elements success4 = doc1.select("div[id=\"formValidationErrorsId319010\"]");

        String succ = "<h4 class=\"block\">操作成功！</h4>";


        if (success1 != null && success1.first() != null) {
            System.out.println(success1.first().toString());
            if (!succ.equals(success1.first().toString())) {
                throw new Exception("提交失败！");
            }
        }else{
            throw new Exception("提交失败！");
        }
//        if (success2 != null && success2.first() != null) {
//            System.out.println(success2.first().toString());
//            System.out.println(success2.first().text());
//        }
//        if (success3 != null && success3.first() != null) {
//            System.out.println(success3.first().toString());
//            System.out.println(success3.first().text());
//        }
//        if (success4 != null ) {
//            System.out.println(success4.toString());
//            System.out.println(success4.text());
//        }

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

    /**
     * 读取Cookie
     *
     * @return
     */
    public static String getCookiesFromFile(String username) {
        //讀取所有cookie文件
        File file = new File("D:\\vcode\\cookie\\" + username + ".txt");
        String c = null;
        try {
            @Cleanup FileReader fileReader = new FileReader(file);
            @Cleanup BufferedReader bufferedReader = new BufferedReader(fileReader);
            c = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c;
    }

}
