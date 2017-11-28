package com.born.bc.basic.dao;

import com.born.bc.basic.entity.Role;
import com.born.bc.basic.entity.RolePermissionRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 角色Mapper
 * @author wangjian
 */
@Repository
public interface RoleMapper {
    /**
     * 插入
     * @param record
     * @return
     */
    int insert(Role record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    int insertSelective(Role record);

    /**
     * 根据登录名获取用户角色列表
     * @param loginName
     * @return
     * @throws Exception
     */
    public HashSet<String> getRoleByLoginName(@Param("loginName") String loginName);


    /**
     * 查询角色列表
     * @param role
     * @return
     */
    public List<Role> selectRoleList(@Param("entity") Role role);

    /**
     * 选择性修改
     * @param record
     * @return
     */
    public int updateSelective(Role record);

    /**
     * 逻辑删除角色信息
     * @param id
     * @return
     */
    public int updateDelStatus(@Param("id") String id);

    /**
     * 新建角色权限关系
     * @param list
     * @return
     */
    public int insertRoleAndPermission(@Param("list")List<RolePermissionRelation> list);

    /**
     * 根据角色ID逻辑删除角色权限关系
     * @param roleId
     * @return
     */
    public int updateRoleAndPermissionDelStatus(@Param("roleId") String roleId);

    /**
     * 查询角色是否被关联
     * @param roleId
     * @return
     */
    Integer checkRoleIsLinked(@Param("roleId") String roleId);

    /**
     * 根据用户ID获取该用户关联的角色
     * @param userId
     * @return
     */
    List<Role> selectLinkedRoleByUserId(@Param("userId") String userId);

    // TODO 添加查询xxx角色关联的权限
}