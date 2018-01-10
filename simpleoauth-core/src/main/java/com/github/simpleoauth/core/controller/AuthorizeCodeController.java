package com.github.simpleoauth.core.controller;

import com.github.simpleoauth.core.entity.OauthCode;
import com.github.simpleoauth.core.service.IOauthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiongkw on 2018/1/9.
 */
@RestController
@RequestMapping("/api/oauthz")
public class AuthorizeCodeController {

    @Autowired
    private IOauthCodeService oauthCodeService;

    @RequestMapping("/codes/latest")
    public OauthCode getLatestCodeByAppKey(@RequestParam(name = "app_key") String app_key) {
        return oauthCodeService.getLatestCodeByAppKey(app_key);
    }

    @PostMapping("/codes")
    void saveCode(@RequestBody OauthCode code) {
        oauthCodeService.save(code);
    }

    @PostMapping("/codes/expiration")
    public OauthCode expireCode(@RequestParam(name = "code") String code) {
        return oauthCodeService.expireCode(code);
    }

}
