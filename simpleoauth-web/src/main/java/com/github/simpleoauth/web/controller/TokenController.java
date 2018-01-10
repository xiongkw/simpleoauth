package com.cloud.ecloud.oauthweb.controller;

import com.cloud.ecloud.oauthweb.CodedException;
import com.cloud.ecloud.oauthweb.IConstants;
import com.cloud.ecloud.oauthweb.handler.IOauthHandler;
import com.cloud.ecloud.oauthweb.model.AccessToken;
import com.cloud.ecloud.oauthweb.model.TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by xiongkw on 2018/1/8.
 */
//@RestController
public class TokenController {

    @Autowired
    private List<IOauthHandler<TokenRequest, AccessToken>> tokenHandlers;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Object token(TokenRequest request) {
        for (IOauthHandler<TokenRequest, AccessToken> handler : tokenHandlers) {
            if (handler.support(request)) {
                return handler.handle(request);
            }
        }
        throw new CodedException(IConstants.CODE_UNSUPPORTED_GRANT_TYPE, "Unsupported request [" + request + "]");
    }

}
