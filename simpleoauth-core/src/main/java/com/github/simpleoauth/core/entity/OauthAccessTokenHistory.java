package com.github.simpleoauth.core.entity;

import java.util.Date;

public class OauthAccessTokenHistory {
    private Long id;

    private String access_token;

    private String oauth_code;

    private String app_key;

    private Long user_id;

    private String user_name;

    private Long tenant_id;

    private String tenant_code;

    private String token_type;

    private Date create_time;

    private Integer expires_in;

    private String refresh_token;

    private Integer refresh_token_expire_seconds;

    private Boolean has_been_refreshed;

    private String refresh_from;

    private Byte refresh_or_expire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token == null ? null : access_token.trim();
    }

    public String getOauth_code() {
        return oauth_code;
    }

    public void setOauth_code(String oauth_code) {
        this.oauth_code = oauth_code == null ? null : oauth_code.trim();
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key == null ? null : app_key.trim();
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }

    public Long getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(Long tenant_id) {
        this.tenant_id = tenant_id;
    }

    public String getTenant_code() {
        return tenant_code;
    }

    public void setTenant_code(String tenant_code) {
        this.tenant_code = tenant_code == null ? null : tenant_code.trim();
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type == null ? null : token_type.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token == null ? null : refresh_token.trim();
    }

    public Integer getRefresh_token_expire_seconds() {
        return refresh_token_expire_seconds;
    }

    public void setRefresh_token_expire_seconds(Integer refresh_token_expire_seconds) {
        this.refresh_token_expire_seconds = refresh_token_expire_seconds;
    }

    public Boolean getHas_been_refreshed() {
        return has_been_refreshed;
    }

    public void setHas_been_refreshed(Boolean has_been_refreshed) {
        this.has_been_refreshed = has_been_refreshed;
    }

    public String getRefresh_from() {
        return refresh_from;
    }

    public void setRefresh_from(String refresh_from) {
        this.refresh_from = refresh_from == null ? null : refresh_from.trim();
    }

    public Byte getRefresh_or_expire() {
        return refresh_or_expire;
    }

    public void setRefresh_or_expire(Byte refresh_or_expire) {
        this.refresh_or_expire = refresh_or_expire;
    }
}