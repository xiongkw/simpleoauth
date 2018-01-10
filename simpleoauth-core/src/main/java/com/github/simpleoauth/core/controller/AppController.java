package com.github.simpleoauth.core.controller;

import com.github.simpleoauth.core.entity.OauthAppInfo;
import com.github.simpleoauth.core.service.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiongkw on 2018/1/8.
 */
@RestController
@RequestMapping("/api/oauthz")
public class AppController {

    @Autowired
    private IAppService appService;

    @GetMapping("/apps/{app_key}")
    public OauthAppInfo getAppInfoByKey(@PathVariable(name = "app_key") String app_key){
        return appService.getAppInfoByKey(app_key);
    }

    @PostMapping("/apps/authorize")
    public boolean authApp(@RequestParam(name = "app_key") String app_key, @RequestParam(name = "app_secret") String app_secret){
        return appService.authApp(app_key, app_secret);
    }

}
