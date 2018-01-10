package com.cloud.ecloud.oauthweb;

/**
 * Created by xiongkw on 2018/1/9.
 */
public interface IConstants {
    String CODE_INVALID_REQUEST = "invalid_request";
    String CODE_UNSUPPORTED_GRANT_TYPE = "unsupported_grant_type";
    String CODE_INVALID_CLIENT = "invalid_client";
    String CODE_INVALID_GRANT = "invalid_grant";
    String CODE_UNAUTHORIZED_CLIENT = "unauthorized_client";

    String SESSION_KEY_USER_INFO = "userInfo";
    String SESSION_KEY_APP_INFO = "appInfo";
    String SESSION_KEY_OAUTH_REQUEST = "oauthRequest";

    int ACCESS_TOKEN_EXPIRES_IN = 7200;

    enum TokenType{
        bearer, mac
    }

    enum GrantType {
        authorization_code,refresh_token
    }

    enum ResponseType {
        code, token
    }
}
