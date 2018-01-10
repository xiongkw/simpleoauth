package com.github.simpleoauth.core.service.impl;

import com.github.simpleoauth.core.service.IAccessTokenService;
import com.github.simpleoauth.core.service.IOauthCodeService;
import com.github.simpleoauth.core.service.IOauthScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by xiongkw on 2018/1/10.
 */
@Component
public class OauthSchedulerImpl implements IOauthScheduler {

    @Autowired
    private IOauthCodeService oauthCodeService;

    @Autowired
    private IAccessTokenService accessTokenService;

    @Override
    @Scheduled(cron = "0 0 2 * * *")
    public void schedule() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        oauthCodeService.handleExpiredCode(now);
        accessTokenService.handleExpiredToken(now);
    }

}
