package com.cloud.ecloud.oauthweb.handler;

import com.cloud.ecloud.oauthweb.CodedException;
import com.cloud.ecloud.oauthweb.IConstants;
import com.cloud.ecloud.oauthweb.model.AccessToken;
import com.cloud.ecloud.oauthweb.model.TokenRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by xiongkw on 2018/1/9.
 */
@Component
public class RefreshTokenHandler extends AbstractTokenHandler {
    @Override
    public boolean support(TokenRequest request) {
        return IConstants.GrantType.refresh_token.name().equalsIgnoreCase(request.getGrant_type());
    }

    @Override
    public AccessToken handle(TokenRequest request) {
        String refresh_token = request.getRefresh_token();
        if (StringUtils.isEmpty(refresh_token)) {
            throw new CodedException(IConstants.CODE_INVALID_REQUEST, "Invalid parameter refresh_token: [" + refresh_token + "]");
        }
        AccessToken origin = oAuthService.expireToken(refresh_token);
        if (origin == null) {
            throw new CodedException(IConstants.CODE_INVALID_GRANT, "Invalid parameter refresh_token: [" + refresh_token + "]");
        }
        filterToken(origin);
        AccessToken token = randomToken();
        oAuthService.saveToken(token);
        return token;
    }
}
