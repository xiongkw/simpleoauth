package com.cloud.ecloud.oauthweb;

/**
 * Created by xiongkw on 2018/1/9.
 */
public class CodedException extends RuntimeException {
    private String code;

    public CodedException(String code, String message) {
        super(message);
        this.code  = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
