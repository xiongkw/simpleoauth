package com.cloud.ecloud.oauthweb.model;

import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

/**
 * Created by xiongkw on 2018/1/9.
 */
public class OauthCode extends CodeRequest {
    private String code;

    private int expires_in;

    private Timestamp create_time;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public static OauthCode fromCodeRequest(CodeRequest request){
        OauthCode target = new OauthCode();
        BeanUtils.copyProperties(request, target);
        return target;
    }
}
