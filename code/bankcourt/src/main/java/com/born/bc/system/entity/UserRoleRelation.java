package com.born.bc.system.entity;

import com.born.bc.commons.entity.BaseEntity;

/**
 * 用户角色关系VO
 */
public class UserRoleRelation extends BaseEntity {

    private String userId;
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRoleRelation{" +
                "userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
