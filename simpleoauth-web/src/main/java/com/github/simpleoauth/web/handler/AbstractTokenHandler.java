package com.cloud.ecloud.oauthweb.handler;

import com.cloud.ecloud.oauthweb.IConstants;
import com.cloud.ecloud.oauthweb.model.AccessToken;
import com.cloud.ecloud.oauthweb.filter.ITokenFilter;
import com.cloud.ecloud.oauthweb.model.TokenRequest;
import com.cloud.ecloud.oauthweb.service.IOAuthService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Created by xiongkw on 2018/1/9.
 */
public abstract class AbstractTokenHandler implements IOauthHandler<TokenRequest, AccessToken> {
    @Value("${oauth.token.length:64}")
    private int tokenStringLength;

    @Value("${oauth.token.expiresInSeconds:7200}")
    private int tokenExpiresIn;

    @Autowired
    protected IOAuthService oAuthService;

    @Autowired(required = false)
    private List<ITokenFilter> tokenFilterList;

    protected AccessToken randomToken() {
        AccessToken token = new AccessToken();
        String access_token = RandomStringUtils.randomAscii(tokenStringLength);
        token.setAccess_token(access_token);
        String refresh_token = RandomStringUtils.randomAscii(tokenStringLength);
        token.setRefresh_token(refresh_token);
        token.setToken_type(IConstants.TokenType.bearer.name());
        token.setExpires_in(tokenExpiresIn);
        return token;
    }

    protected void filterToken(AccessToken last) {
        if (tokenFilterList == null || last == null) {
            return;
        }
        for (ITokenFilter filter : tokenFilterList) {
            filter.filter(last);
        }
    }

}
