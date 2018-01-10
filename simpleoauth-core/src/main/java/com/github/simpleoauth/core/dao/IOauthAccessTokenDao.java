package com.github.simpleoauth.core.dao;

import com.github.simpleoauth.core.entity.OauthAccessToken;
import com.github.simpleoauth.core.mapper.OauthAccessTokenMapper;

import java.sql.Timestamp;

public interface IOauthAccessTokenDao extends OauthAccessTokenMapper {

    OauthAccessToken selectByAccessTokenCode(String access_token);

    OauthAccessToken getLastTokenByAppKey(String app_key);

    OauthAccessToken selectByRefreshToken(String refresh_token);

    void handleExpiredToken(Timestamp createTime);
}
