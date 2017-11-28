package com.born.bc.basic.service.api;

import com.born.bc.basic.entity.Permission;
import com.born.bc.basic.entity.Role;
import com.born.bc.basic.entity.User;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.utils.ResultJson;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 用户业务API
 * @author wangjian
 */
public interface UserServiceApi {


    /**
     * 根据loginName匹配用户登录名或者手机号码
     * @param loginName
     * @return
     * @throws Exception
     */
    public User getUserByLoginName(String loginName)throws Exception;

    /**
     * 根据用户ID将用户逻辑删除
     * @param id
     * @return
     * @throws Exception
     */
    public int updateUserDelStatus(String id)throws Exception;

    /**
     * 根据登录名获取用户角色
     * @param loginName
     * @return
     * @throws Exception
     */
    public HashSet<String> getRoleByLoginNameCache(String loginName);


    /**
     * 根据角色ID集合获取对应角色的权限
     * @param roleCodes
     * @return
     * @throws Exception
     */
    public HashSet<String> getPermissionByRoleCodesCache(String loginName, List<String> roleCodes);

    /**
     * 根据登录名获取用户角色(登录专用)
     * @param loginName
     * @return
     * @throws Exception
     */
    public HashSet<String> getRoleByLoginName(String loginName);


    /**
     * 根据角色ID集合获取对应角色的权限(登录专用)
     * @param roleCodes
     * @return
     * @throws Exception
     */
    public HashSet<String> getPermissionByRoleCodes(List<String> roleCodes);


    /**
     * 查询用户列表
     * @param user
     * @param page
     * @return
     * @throws Exception
     */
    public ResultJson findUserByPage(User user, PageParamVO page);

    /**
     * 修改用户启用状态
     * @param id
     * @param enableStatus
     * @return
     * @throws Exception
     */
    public ResultJson updateUserEnableStatus(String id, Integer enableStatus);

    /**
     * 修改用户
     * @param user
     * @return
     * @throws Exception
     */
    public ResultJson updateUser(User user);

    /**
     * 新增用户
     * @return
     * @throws Exception
     */
    public ResultJson insertUser(User user);

    /**
     * 获取用户详情
     * @param userId
     * @return
     */
    public ResultJson queryUserInfo(String userId);


}
