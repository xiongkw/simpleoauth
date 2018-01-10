package com.cloud.ecloud.oauthweb.filter;

import com.cloud.ecloud.oauthweb.model.AccessToken;

/**
 * Created by xiongkw on 2018/1/9.
 */
public interface ITokenFilter {
    void filter(AccessToken token);
}
