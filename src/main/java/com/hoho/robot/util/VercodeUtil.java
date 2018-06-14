package com.hoho.robot.util;

import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证码图片-->验证码
 */
public class VercodeUtil {
    public static String crackVercode(File file) {
        String code = "";
        String imageStr = GetImageStr(file);
        HashMap dataMap = new HashMap();
        dataMap.put("user", "wanlongfei007");
        dataMap.put("pass", "*******");
        dataMap.put("softid", "896692");
        dataMap.put("codetype", "1004");
        dataMap.put("len_min", "4");
        dataMap.put("time_add", "0");
        dataMap.put("str_debug", "0");
        dataMap.put("file_base64", imageStr);
        try {
            String result = HttpUtil.doPost("http://upload.chaojiying.net/Upload/Processing.php", dataMap);
            Map map = (Map) JSONObject.parse(result);
            code = (String) map.get("pic_str");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

    public static String GetImageStr(File imgFile) {
        byte[] data = null;
        try {
            FileInputStream encoder = new FileInputStream(imgFile);
            data = new byte[encoder.available()];
            encoder.read(data);
            encoder.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder64 = new BASE64Encoder();
        return encoder64.encode(data);
    }

}

