package com.cloud.ecloud.oauthweb.filter;

import com.cloud.ecloud.oauthweb.model.OauthCode;

/**
 * Created by xiongkw on 2018/1/9.
 */
public interface ICodeFilter {
    void filter(OauthCode code);
}
