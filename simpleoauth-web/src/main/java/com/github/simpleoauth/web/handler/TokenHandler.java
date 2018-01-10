package com.cloud.ecloud.oauthweb.handler;

import com.cloud.ecloud.oauthweb.CodedException;
import com.cloud.ecloud.oauthweb.IConstants;
import com.cloud.ecloud.oauthweb.model.AccessToken;
import com.cloud.ecloud.oauthweb.model.OauthCode;
import com.cloud.ecloud.oauthweb.model.TokenRequest;
import com.cloud.ecloud.oauthweb.service.IAppService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiongkw on 2018/1/9.
 */
@Component
public class TokenHandler extends AbstractTokenHandler {
    @Autowired
    private IAppService appService;

    @Override
    public boolean support(TokenRequest request) {
        return IConstants.GrantType.authorization_code.name().equalsIgnoreCase(request.getGrant_type());
    }

    @Override
    public AccessToken handle(TokenRequest request) {
        String code = request.getCode();
        if (StringUtils.isEmpty(code)) {
            throw new CodedException(IConstants.CODE_INVALID_REQUEST, "Invalid parameter code: [" + code + "]");
        }
        OauthCode c = oAuthService.expireCode(code);
        if (c == null) {
            throw new CodedException(IConstants.CODE_INVALID_GRANT, "Invalid parameter code: [" + code + "]");
        }
        if (codeExpired(c)) {
            throw new CodedException(IConstants.CODE_INVALID_GRANT, "Expired code: [" + code + "]!");
        }
        String app_key = request.getApp_key();
        String app_secret = request.getApp_secret();
        boolean authorized = appService.authApp(app_key, app_secret);
        if (!authorized) {
            throw new CodedException(IConstants.CODE_INVALID_CLIENT, "App authorize failed!, app key: [" + app_key + "], app secret: [" + app_secret + "]");
        }
        AccessToken lastToken = oAuthService.getLastTokenByAppKey(app_key);
        filterToken(lastToken);
        AccessToken token = randomToken();
        oAuthService.saveToken(token);
        return token;
    }

    private boolean codeExpired(OauthCode c) {
        return (System.currentTimeMillis() - c.getCreate_time().getTime()) > c.getExpires_in() * 1000;
    }

}
