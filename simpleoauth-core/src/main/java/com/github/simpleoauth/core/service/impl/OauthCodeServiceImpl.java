package com.github.simpleoauth.core.service.impl;

import com.github.simpleoauth.core.dao.IOauthCodeDao;
import com.github.simpleoauth.core.entity.OauthCode;
import com.github.simpleoauth.core.service.IOauthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * Created by xiongkw on 2018/1/9.
 */
@Service
public class OauthCodeServiceImpl implements IOauthCodeService {

    @Autowired
    private IOauthCodeDao oauthCodeDao;

    @Override
    public OauthCode getLatestCodeByAppKey(String app_key) {
        return oauthCodeDao.getLatestCodeByAppKey(app_key);
    }

    @Override
    public void save(OauthCode code) {
        oauthCodeDao.insert(code);
    }

    @Override
    @Transactional
    public OauthCode expireCode(String code) {
        OauthCode c = oauthCodeDao.selectByCode(code);
        OauthCode entity = new OauthCode();
        entity.setId(c.getId());
        entity.setHas_been_used(true);
        oauthCodeDao.updateByPrimaryKeySelective(entity);
        return c;
    }

    @Override
    public void handleExpiredCode(Timestamp createTime) {
        oauthCodeDao.handleExpiredCode(createTime);
    }
}
