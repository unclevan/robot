package com.hoho.robot.service;

import lombok.Cleanup;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by wanlf on 2018/6/14
 * email:wanlongfei007@gmail.com
 */
@Component
public class KeepConnectionAliveTask {


    public static final String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E)";

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    @Scheduled(fixedDelay = 60000)
    public void execute() {
        keepConnectionAlive();
    }

    /**
     * 保持连接
     */
    public static void keepConnectionAlive() {
        String[] cookies = getCookiesFromFile();
        for (String cookie : cookies) {
            Request request = new Request.Builder()
                    .url("http://zjjzzgl.zjsgat.gov.cn:9090/zahlw/index")
                    .header("User-Agent", USER_AGENT)
                    .header("Cookie", cookie)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                response.close();
            } catch (IOException e) {
                //不做处理
                //LOGGER.info("保持连接异常");
            }
        }
    }

    /**
     * 读取Cookie
     *
     * @return
     */
    public static String[] getCookiesFromFile() {
        //讀取所有cookie文件
        File file = new File("D:\\vcode\\cookie\\");
        File[] array = file.listFiles();
        String[] arr = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            try {
                @Cleanup FileReader fileReader = new FileReader(array[i]);
                @Cleanup BufferedReader bufferedReader = new BufferedReader(fileReader);
                arr[i] = bufferedReader.readLine();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arr;
    }
}
