package com.born.bc.basic.dao;

import com.born.bc.basic.entity.Role;
import com.born.bc.basic.entity.User;
import com.born.bc.basic.entity.UserRoleRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户Mapper
 * @author wangjian
 */
@Repository
public interface UserMapper {

    /**
     * 选择性插入
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 选择性修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);



    /**
     * 根据loginName匹配用户登录名或者手机号码
     * @param loginName
     * @return
     * @
     */
    public User getUserByLoginName(@Param("loginName") String loginName);

    /**
     * 验证用的登录名和手机号码是否同时存在
     * @param id
     * @param loginName
     * @param phone
     * @return
     * @
     */
    public ArrayList<User> validateUserByLoginName(@Param("id") String id, @Param("loginName") String loginName, @Param("phone") String phone);

    /**
     * 根据用户ID将用户逻辑删除
     * @param id
     * @return
     * @
     */
    public int updateUserDelStatus(@Param("id") String id);

    /**
     * 查询用户列表
     * @param user
     * @return
     * @
     */
    public ArrayList<User> findUserList(@Param("entity") User user);

    /**
     * 增加用户角色关系
     * @param list
     * @return
     */
    public int batchInsertUserRoleRelation(@Param("list") List<UserRoleRelation> list);

    /**
     * 逻辑删除用户角色关系
     * @param userId
     */
    int updateUserAndRoleDelStatus(@Param("userId") String userId);

    /**
     * 根据ID查询用户详情
     * @param id
     * @return
     */
    User selectById(@Param("id") String id);



}