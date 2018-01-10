package com.cloud.ecloud.oauthweb.model;

import java.util.List;

/**
 * Created by xiongkw on 2018/1/8.
 */
public class UserInfo {
    private Long userId;
    private String username;
    private List<Tenant> tenantList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Tenant> getTenantList() {
        return tenantList;
    }

    public void setTenantList(List<Tenant> tenantList) {
        this.tenantList = tenantList;
    }
}
