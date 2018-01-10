package com.github.simpleoauth.core.entity;

import java.util.Date;

public class SysParam {
    private Long sys_param_id;

    private String sys_param_name;

    private String sys_param_value;

    private Date create_time;

    private Byte enable;

    private String description;

    public Long getSys_param_id() {
        return sys_param_id;
    }

    public void setSys_param_id(Long sys_param_id) {
        this.sys_param_id = sys_param_id;
    }

    public String getSys_param_name() {
        return sys_param_name;
    }

    public void setSys_param_name(String sys_param_name) {
        this.sys_param_name = sys_param_name == null ? null : sys_param_name.trim();
    }

    public String getSys_param_value() {
        return sys_param_value;
    }

    public void setSys_param_value(String sys_param_value) {
        this.sys_param_value = sys_param_value == null ? null : sys_param_value.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Byte getEnable() {
        return enable;
    }

    public void setEnable(Byte enable) {
        this.enable = enable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}