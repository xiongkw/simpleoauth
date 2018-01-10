package com.github.simpleoauth.core.controller;

import com.github.simpleoauth.core.entity.OauthAccessToken;
import com.github.simpleoauth.core.service.IAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiongkw on 2018/1/9.
 */
@RestController
@RequestMapping("/api/oauthz")
public class AccessTokenController {

    @Autowired
    private IAccessTokenService accessTokenService;

    @GetMapping("/tokens/latest")
    public OauthAccessToken getLastTokenByAppKey(@RequestParam(name = "app_key") String app_key){
        return accessTokenService.getLastTokenByAppKey(app_key);
    }

    @PostMapping("/tokens")
    public void saveToken(@RequestBody OauthAccessToken token){
        accessTokenService.save(token);
    }

    @PostMapping("/tokens/expiration")
    public OauthAccessToken expireToken(@PathVariable(name = "refresh_token") String refresh_token){
        return accessTokenService.expireToken(refresh_token);
    }

}
