package com.github.simpleoauth.core.dao;

import com.github.simpleoauth.core.entity.OauthAppInfo;
import com.github.simpleoauth.core.mapper.OauthAppInfoMapper;

public interface IOauthAppInfoDao extends OauthAppInfoMapper {

    OauthAppInfo selectByAppkey(String appKey);

}
