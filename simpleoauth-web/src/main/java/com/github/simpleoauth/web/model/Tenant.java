package com.cloud.ecloud.oauthweb.model;

/**
 * Created by xiongkw on 2018/1/8.
 */
public class Tenant {
    private Long tenantId;
    private String tenantCode;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }
}
