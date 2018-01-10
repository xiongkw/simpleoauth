package com.cloud.ecloud.oauthweb.handler;

import com.cloud.ecloud.oauthweb.model.OAuthRequest;

/**
 * Created by xiongkw on 2018/1/9.
 */
public interface IOauthHandler<T extends OAuthRequest, R> {
    boolean support(T request);

    R handle(T request);
}
