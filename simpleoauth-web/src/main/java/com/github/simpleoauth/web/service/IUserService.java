package com.cloud.ecloud.oauthweb.service;

import com.cloud.ecloud.oauthweb.model.UserInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xiongkw on 2018/1/9.
 */
@FeignClient("cloud-ecloud-oauthz")
public interface IUserService {

    @RequestMapping(value = "/api/user/login", method = RequestMethod.GET)
    UserInfo authorize(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password);

}
