package com.born.bc.basic.dao;

import com.born.bc.basic.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

import java.util.ArrayList;

/**
 * 权限Mapper
 * @author wangjian
 */
@Repository
public interface PermissionMapper {
    /**
     * 插入
     * @param record
     * @return
     */
    int insert(Permission record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    int insertSelective(Permission record);

    /**
     * 根据一组角色ID获取角色对的权限列表
     * @param roleIds
     * @return
     * @throws Exception
     */
    public HashSet<String> getPermissionByRoleCodes(@Param("roleIds") List<String> roleIds);

    /**
     * 查询权限列表
     * @param entity
     * @return
     */
    public List<Permission> selectPermissionList(Permission entity);

    /**
     * 修改权限
     * @param record
     * @return
     */
    public int updateSelective(Permission record);


    /**
     * 查询权限占用记录
     * @param id
     * @return
     */
    Integer checkPermissionIsLinked(@Param("permissionId") String permissionId);

    /**
     * 逻辑删除权限
     * @param id
     * @return
     */
    int updateDelStatus(String id);
}