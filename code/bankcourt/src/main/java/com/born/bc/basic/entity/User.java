package com.born.bc.basic.entity;

import com.born.bc.commons.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 用户类
 * @author wangjian
 */
public class User extends BaseEntity{

    /**
     * 登录名
     */
    private String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 手机号(多个使用","隔开)
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 启用状态
     * 1启用（默认）
     * 0未启用
     */
    private Integer enableStatus;

    /**
     * 关联角色列表
     */
    private List<Role> roles;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}