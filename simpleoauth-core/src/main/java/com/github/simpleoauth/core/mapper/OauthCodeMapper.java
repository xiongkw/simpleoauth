package com.github.simpleoauth.core.mapper;

import com.github.simpleoauth.core.entity.OauthCode;

public interface OauthCodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OauthCode record);

    int insertSelective(OauthCode record);

    OauthCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OauthCode record);

    int updateByPrimaryKey(OauthCode record);
}