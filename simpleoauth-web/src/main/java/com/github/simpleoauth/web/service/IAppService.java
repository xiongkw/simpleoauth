package com.cloud.ecloud.oauthweb.service;

import com.cloud.ecloud.oauthweb.model.AppInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiongkw on 2018/1/8.
 */
@FeignClient("cloud-ecloud-oauthz")
public interface IAppService {

    @GetMapping("/api/oauthz/apps/{app_key}")
    AppInfo getAppInfoByKey(@PathVariable(name = "app_key") String app_key);

    @PostMapping("/api/oauthz/apps/authorize")
    boolean authApp(@RequestParam(name = "app_key") String app_key, @RequestParam(name = "app_secret") String app_secret);
}
