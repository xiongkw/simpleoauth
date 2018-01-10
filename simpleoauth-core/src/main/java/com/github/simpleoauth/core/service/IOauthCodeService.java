package com.github.simpleoauth.core.service;

import com.github.simpleoauth.core.entity.OauthCode;

import java.sql.Timestamp;

/**
 * Created by xiongkw on 2018/1/9.
 */
public interface IOauthCodeService {

    OauthCode getLatestCodeByAppKey(String app_key);

    void save(OauthCode code);

    OauthCode expireCode(String code);

    void handleExpiredCode(Timestamp createTime);
}
