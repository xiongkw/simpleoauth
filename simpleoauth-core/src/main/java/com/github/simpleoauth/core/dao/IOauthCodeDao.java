package com.github.simpleoauth.core.dao;

import com.github.simpleoauth.core.entity.OauthCode;
import com.github.simpleoauth.core.mapper.OauthCodeMapper;

import java.sql.Timestamp;

/**
 * Created by xiongkw on 2018/1/9.
 */
public interface IOauthCodeDao extends OauthCodeMapper {

    OauthCode selectByCode(String code);

    OauthCode getLatestCodeByAppKey(String app_key);

    int handleExpiredCode(Timestamp createTime);

}
