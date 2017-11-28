package com.born.bc.basic.entity;

import com.born.bc.commons.entity.BaseEntity;

/**
 * 用户角色关系VO
 */
public class RolePermissionRelation extends BaseEntity {

    private String roleId;
    private String permissionId;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "RolePermissionRelation{" +
                "roleId='" + roleId + '\'' +
                ", permissionId='" + permissionId + '\'' +
                '}';
    }
}
