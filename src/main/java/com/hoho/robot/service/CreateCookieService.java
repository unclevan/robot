package com.hoho.robot.service;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by wanlf on 2018/6/13
 * email:wanlongfei007@gmail.com
 */
@SuppressWarnings("ALL")
@Service
public class CreateCookieService {


    public Map<String, String> login(String username, String password) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(12000, TimeUnit.SECONDS);
        Selenium selenium = new WebDriverBackedSelenium(driver, "http://zjjzzgl.zjsgat.gov.cn:9090");
        selenium.setTimeout("120000");

        WebDriver.Navigation navigation = driver.navigate();
        navigation.to("http://zjjzzgl.zjsgat.gov.cn:9090/zahlw/login");
        WebElement loginInput = driver.findElement(By.id("username"));
        loginInput.sendKeys(username);
        WebElement pwdInput = driver.findElement(By.id("password"));
        pwdInput.sendKeys(password);
        WebElement vcodeInput = driver.findElement(By.name("captcha"));
        //下载验证码图片
        WebElement ele = driver.findElement(By.id("img_captcha"));
        // 获取整个页面截图
        java.io.File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(screenshot);
        // 获取页面上元素的位置
        Point point = ele.getLocation();
        // 获取元素高度和宽度
        int eleWidth = ele.getSize().getWidth();
        int eleHeight = ele.getSize().getHeight();
        // 裁剪
        BufferedImage eleScreenshot = fullImg.getSubimage(point.getX() + 1, point.getY(),
                60, 26);
        ImageIO.write(eleScreenshot, "png", screenshot);
        // 将截取的验证码图片输出到指定磁盘路径（注意命名，便于外部平台识别追踪）
        String name = createPngName();
        File screenshotLocation = new File(name);
        FileUtils.copyFile(screenshot, screenshotLocation);
        //TODO 这里是模拟人工识别测试,该处需要调用识别工具对文件进行识别
        Scanner in = new Scanner(System.in);
        String captcha = in.nextLine();
        vcodeInput.sendKeys(captcha);
        //登录
        WebElement loginBtn = driver.findElement(By.className("col-md-6"));
        loginBtn.click();

        driver.manage().getCookies();
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("cookie: " + cookies);
        Iterator<Cookie> itr = cookies.iterator();
        CookieStore cookieStore = new BasicCookieStore();
        StringBuffer jcsid = new StringBuffer();
        StringBuffer jsessionid = new StringBuffer();
        while (itr.hasNext()) {
            Cookie cookie = itr.next();
            String str = cookie.toString();
            String[] arr = str.split(";");
            for (int i = 0; i < arr.length; i++) {
                String[] srr = arr[i].split("=");
                if (srr[0].equals("jcsid")) {
                    jcsid.append("jcsid=").append(srr[1]);
                }
                if (srr[0].equals("JSESSIONID")) {
                    jsessionid.append("JSESSIONID=").append(srr[1]);
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append(jcsid.toString()).append(";").append(jsessionid.toString()).append("_version_key=3301;");
        System.out.println(sb.toString());
        // 保存到文件
        Writer out = new FileWriter(new File("D://vcode//cookie//" + username + ".txt"));
        out.write(sb.toString());
        out.close();
        driver.close();

        Map<String, String> map = new HashMap<>();
        map.put("png", name);
        map.put("cookiefilepath", username + ".txt");
        return map;

    }

    private String createPngName() {
        Random random = new Random();
        StringBuffer sf = new StringBuffer();
        sf.append("D:/vcode/png/");
        sf.append(System.currentTimeMillis());
        sf.append(random.nextInt());
        sf.append(".png");
        return sf.toString();
    }


}
