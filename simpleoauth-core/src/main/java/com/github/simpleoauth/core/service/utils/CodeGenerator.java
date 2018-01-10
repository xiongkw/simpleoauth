package com.github.simpleoauth.core.service.utils;

import java.util.UUID;

import javax.annotation.PostConstruct;

import com.github.simpleoauth.core.entity.OauthAppInfo;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.simpleoauth.core.dao.IOauthAppInfoDao;

@Component
public class CodeGenerator {

    @Autowired
    private IOauthAppInfoDao oauthAppInfoDao;

    private static CodeGenerator codeGenerator;

    @PostConstruct
    public void init() {
        codeGenerator = this;
        codeGenerator.oauthAppInfoDao = this.oauthAppInfoDao;
    }

    public static String generate8() {
        String res = RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz1234567890");
        OauthAppInfo info = codeGenerator.oauthAppInfoDao.selectByAppkey(res);
        while(info != null) { // 有冲突则重新生成
            res = RandomStringUtils.random(8);
            info = codeGenerator.oauthAppInfoDao.selectByAppkey(res);
        }
        return res;
    }

    public static String generate32() {
        UUID id = UUID.randomUUID();
        return id.toString().replace("-", "");
    }

    private CodeGenerator() {};
}
