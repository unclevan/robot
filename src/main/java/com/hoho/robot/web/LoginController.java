package com.hoho.robot.web;

import com.hoho.robot.service.CreateCookieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanlf on 2018/6/13
 * email:wanlongfei007@gmail.com
 */
@RestController
@RequestMapping("/v1/robto/login/")
@Api(value = "login", tags = "login", description = "login")
public class LoginController {

    @Autowired
    private CreateCookieService createCookieService;

    @PostMapping(value = "/createCookie")
    @ApiOperation(value = "登录", httpMethod = "POST", response = Map.class)
    public Map detail(@ApiParam(value = "用户名") @RequestParam(name = "username", required = false) String username,
                      @ApiParam(value = "密码") @RequestParam(name = "password", required = false) String password
    ) {
        Map<String, String> map = null;
        try {
            map = createCookieService.login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            map = new HashMap<String, String>();
            map.put("errorInfo", e.getMessage());

        }
        return map;
    }

}
