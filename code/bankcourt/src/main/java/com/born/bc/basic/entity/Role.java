package com.born.bc.basic.entity;

import com.born.bc.commons.entity.BaseEntity;

import java.util.List;

/**
 * 角色类
 * @author wangjian
 */
public class Role extends BaseEntity{
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
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

    /**
     * 关联权限列表
     */
    private List<Permission> permissions;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
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
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}