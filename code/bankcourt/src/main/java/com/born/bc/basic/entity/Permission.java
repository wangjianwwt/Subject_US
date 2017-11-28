package com.born.bc.basic.entity;

import com.born.bc.commons.entity.BaseEntity;

import java.util.Date;

/**
 * 权限类
 * @author wangjian
 */
public class Permission extends BaseEntity{
    /**
     * 权限名
     */
    private String permissionName;
    /**
     * 权限编码
     */
    private String permissionCode;
    /**
     * 启用状态
     * 1启用
     * 0未启用(默认)
     */
    private Integer enableStatus;
    /**
     * 备注
     */
    private String comments;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}