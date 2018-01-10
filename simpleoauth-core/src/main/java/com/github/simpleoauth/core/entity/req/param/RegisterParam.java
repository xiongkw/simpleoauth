package com.github.simpleoauth.core.entity.req.param;

import javax.validation.constraints.NotNull;

public class RegisterParam {

    @NotNull(message="param [app_code] cannot be null")
    private String app_code;
    @NotNull(message="param [redirect_url] cannot be null")
    private String redirect_url;
    private String app_name;
    private String image_url;
    public String getApp_code() {
        return app_code;
    }
    public void setApp_code(String app_code) {
        this.app_code = app_code;
    }
    public String getRedirect_url() {
        return redirect_url;
    }
    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }
    public String getApp_name() {
        return app_name;
    }
    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
