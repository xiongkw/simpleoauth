package com.cloud.ecloud.oauthweb.model;

/**
 * Created by xiongkw on 2018/1/9.
 */
public class CodeRequest extends OAuthRequest {
    private String response_type;
    private String redirect_url;
    private String scope;
    private String state;

    public CodeRequest() {
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return new StringBuilder("CodeRequest{")
                .append("response_type='").append(response_type).append('\'')
                .append(", app_key='").append(super.getApp_key()).append('\'')
                .append(", redirect_url='").append(redirect_url).append('\'')
                .append(", scope='").append(scope).append('\'')
                .append(", state='").append(state).append('\'')
                .append('}').toString();
    }
}
