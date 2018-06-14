package com.hoho.robot.web;

import com.hoho.robot.service.SubmitService;
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
 * Created by wanlf on 2018/6/14
 * email:wanlongfei007@gmail.com
 */
@RestController
@RequestMapping("/v1/robto/business/")
@Api(value = "business", tags = "business", description = "business")
public class SubmitController {

    @Autowired
    SubmitService submitService;

    @PostMapping(value = "/createNew")
    @ApiOperation(value = "提交", httpMethod = "POST", response = Map.class)
    public Map createNew(@ApiParam(value = "action") @RequestParam(name = "action", required = false) String action,
                         @ApiParam(value = "nbbh") @RequestParam(name = "nbbh", required = false) String nbbh,
                         @ApiParam(value = "sbrsf") @RequestParam(name = "sbrsf", required = false) String sbrsf,
                         @ApiParam(value = "czfwdah") @RequestParam(name = "czfwdah", required = false) String czfwdah,
                         @ApiParam(value = "dwbm") @RequestParam(name = "dwbm", required = false) String dwbm,
                         @ApiParam(value = "sblx") @RequestParam(name = "sblx", required = false) String sblx,
                         @ApiParam(value = "zxbz") @RequestParam(name = "zxbz", required = false) String zxbz,
                         @ApiParam(value = "sourcePath") @RequestParam(name = "sourcePath", required = false) String sourcePath,
                         @ApiParam(value = "姓名") @RequestParam(name = "xm", required = false) String xm,
                         @ApiParam(value = "性别") @RequestParam(name = "xb", required = false) String xb,
                         @ApiParam(value = "身份证号") @RequestParam(name = "sfzh", required = false) String sfzh,
                         @ApiParam(value = "联系电话") @RequestParam(name = "lxdh", required = false) String lxdh,
                         @ApiParam(value = "hksx") @RequestParam(name = "hksx", required = false) String hksx,
                         @ApiParam(value = "hkxz") @RequestParam(name = "hkxz", required = false) String hkxz,
                         @ApiParam(value = "zzcs") @RequestParam(name = "zzcs", required = false) String zzcs,
                         @ApiParam(value = "zzsy") @RequestParam(name = "zzsy", required = false) String zzsy,
                         @ApiParam(value = "whcd") @RequestParam(name = "whcd", required = false) String whcd,
                         @ApiParam(value = "hyzk") @RequestParam(name = "hyzk", required = false) String hyzk,
                         @ApiParam(value = "xy") @RequestParam(name = "xy", required = false) String xy,
                         @ApiParam(value = "glm") @RequestParam(name = "glm", required = false) String glm,
                         @ApiParam(value = "xzdxz") @RequestParam(name = "xzdxz", required = false) String xzdxz,
                         @ApiParam(value = "fdxm") @RequestParam(name = "fdxm", required = false) String fdxm,
                         @ApiParam(value = "fdgmsfhm") @RequestParam(name = "fdgmsfhm", required = false) String fdgmsfhm,
                         @ApiParam(value = "fdlxdh") @RequestParam(name = "fdlxdh", required = false) String fdlxdh,
                         @ApiParam(value = "sfzzpBase64") @RequestParam(name = "sfzzpBase64", required = false) String sfzzpBase64,
                         @ApiParam(value = "avatarBase64") @RequestParam(name = "avatarBase64", required = false) String avatarBase64,
                         @ApiParam(value = "jzdywgzdw") @RequestParam(name = "jzdywgzdw", required = false) String jzdywgzdw,
                         @ApiParam(value = "cszy") @RequestParam(name = "cszy", required = false) String cszy,
                         @ApiParam(value = "bl1") @RequestParam(name = "bl1", required = false) String bl1,
                         @ApiParam(value = "dwfzr") @RequestParam(name = "dwfzr", required = false) String dwfzr,
                         @ApiParam(value = "dwlxdh") @RequestParam(name = "dwlxdh", required = false) String dwlxdh,
                         @ApiParam(value = "gzdz") @RequestParam(name = "gzdz", required = false) String gzdz,
                         @ApiParam(value = "ldhtqk") @RequestParam(name = "ldhtqk", required = false) String ldhtqk

    ) {
        Map<String, String> map = new HashMap<>();
        try {
            submitService.exceute();
            map.put("msg", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("errorinfo", e.getMessage());

        }
        return map;
    }
}
