package com.cloud.ecloud.oauthweb.service;

import com.cloud.ecloud.oauthweb.model.OauthCode;
import com.cloud.ecloud.oauthweb.model.AccessToken;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiongkw on 2018/1/9.
 */
@FeignClient("cloud-ecloud-oauthz")
public interface IOAuthService {

    @GetMapping("/api/oauthz/codes/latest")
    OauthCode getLatestCodeByAppKey(@RequestParam(name = "app_key") String app_key);

    @PostMapping("/api/oauthz/codes")
    void saveCode(@RequestBody OauthCode code);

    @PostMapping("/api/oauthz/codes/expiration")
    OauthCode expireCode(@RequestParam(name = "code") String code);

    @GetMapping("/api/oauthz/tokens/latest")
    AccessToken getLastTokenByAppKey(@RequestParam(name = "app_key") String app_key);

    @PostMapping("/api/oauthz/tokens")
    void saveToken(@RequestBody AccessToken token);

    @PostMapping("/api/oauthz/tokens/expiration")
    AccessToken expireToken(@PathVariable(name = "refresh_token") String refresh_token);

}
