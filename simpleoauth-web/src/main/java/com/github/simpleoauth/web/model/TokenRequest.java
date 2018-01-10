package com.cloud.ecloud.oauthweb.model;

/**
 * Created by xiongkw on 2018/1/9.
 */
public class TokenRequest extends OAuthRequest {
    private String grant_type;
    private String code;

    private String app_secret;

    private String refresh_token;

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    @Override
    public String toString() {
        return new StringBuilder("TokenRequest{")
                .append("grant_type='").append(grant_type).append('\'')
                .append(", code='").append(code).append('\'')
                .append(", app_key='").append(super.getApp_key()).append('\'')
                .append(", app_secret='").append(app_secret).append('\'')
                .append(", refresh_token='").append(refresh_token).append('\'')
                .append('}').toString();
    }
}
