package com.born.bc.basic.service;

import com.alibaba.druid.util.StringUtils;
import com.born.bc.basic.dao.PermissionMapper;
import com.born.bc.basic.dao.RoleMapper;
import com.born.bc.basic.dao.UserMapper;
import com.born.bc.basic.entity.Permission;
import com.born.bc.basic.entity.Role;
import com.born.bc.basic.entity.User;
import com.born.bc.basic.entity.UserRoleRelation;
import com.born.bc.basic.service.api.UserServiceApi;
import com.born.bc.commons.entity.CurrentLoginUser;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.service.RedisClientTemplate;
import com.born.bc.commons.utils.IdGenerator;
import com.born.bc.commons.utils.LoginUserInfoUtil;
import com.born.bc.commons.utils.ResultJson;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 用户业务逻辑实现类
 * @author wangjian
 */
@Service
public class UserServiceImpl implements UserServiceApi {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RedisClientTemplate redisClientTemplate;
    
    @Override
    public User getUserByLoginName(String loginName){
        return userMapper.getUserByLoginName(loginName);
    }

    @Override
    public int updateUserDelStatus(String id){
        return userMapper.updateUserDelStatus(id);
    }

    @Override
    public HashSet<String> getRoleByLoginNameCache(String loginName){

        String roleStr = redisClientTemplate.get(loginName+"_roles");
        if(StringUtils.isEmpty(roleStr)){
            return roleMapper.getRoleByLoginName(loginName);
        }

        String [] roleArray = roleStr.split(",");
        HashSet<String> roles = new HashSet<String>();
        for(String item : roleArray){
            roles.add(item);
        }

        return roles;

    }

    @Override
    public HashSet<String> getPermissionByRoleCodesCache(String loginName, List<String> roleCodes) {
        String permissionsStr = redisClientTemplate.get(loginName+"_permissions");
        if(StringUtils.isEmpty(permissionsStr)){
            return permissionMapper.getPermissionByRoleCodes(roleCodes);
        }

        String [] permissionArray = permissionsStr.split(",");
        HashSet<String> permissions = new HashSet<String>();
        for(String item : permissionArray){
            permissions.add(item);
        }

        return permissions;
    }

    @Override
    public HashSet<String> getRoleByLoginName(String loginName) {
        return roleMapper.getRoleByLoginName(loginName);
    }

    @Override
    public HashSet<String> getPermissionByRoleCodes(List<String> roleCodes){
        return permissionMapper.getPermissionByRoleCodes(roleCodes);
    }

    @Override
    public ResultJson findUserByPage(User user, PageParamVO page){
        PageHelper.startPage(page.getPageIndex(), page.getPageSize());
        ArrayList<User> list = userMapper.findUserList(user);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return ResultJson.buildSuccess("查询成功",pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultJson updateUserEnableStatus(String id, Integer enableStatus){
        if(StringUtils.isEmpty(id)){
            return ResultJson.buildParamError("參數异常");
        }
        User user = new User();
        user.setId(id);
        user.setEnableStatus(enableStatus);
        userMapper.updateByPrimaryKeySelective(user);
        return ResultJson.buildSuccess("操作成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultJson updateUser(User user){
        // 参数校验
        if(StringUtils.isEmpty(user.getId())){
            return ResultJson.buildParamError("缺乏用户主键!");
        }
        String msg = validateUser(user);
        if(!StringUtils.isEmpty(msg)){
            return ResultJson.buildParamError(msg);
        }

        // 校验用户名和手机号码
        ArrayList<User> users = userMapper.validateUserByLoginName(user.getId(), user.getLoginName(), user.getPhone());
        if(!users.isEmpty()){
            return ResultJson.buildError("登录名或者手机号码已被占用!");
        }

        // 修改操作，不能修改登录名
        user.setLoginName(null);
        userMapper.updateByPrimaryKeySelective(user);

        // 删除旧的用户角色关系
        userMapper.updateUserAndRoleDelStatus(user.getId());

        // 新建用户角色关系
        createUserAndRoleRelation(user);

        return ResultJson.buildSuccess("修改用户成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultJson insertUser(User user){
        // 参数校验
        String msg = validateUser(user);
        if(!StringUtils.isEmpty(msg)){
            return ResultJson.buildParamError("参数异常:"+msg+"不能为空!");
        }
        // 校验登录名和手机号码的唯一
        ArrayList<User> users = userMapper.validateUserByLoginName(null, user.getLoginName(), user.getPhone());
        if(!users.isEmpty()){
            return ResultJson.buildError("登录名或者手机号码已被占用!");
        }

        CurrentLoginUser loginUser = LoginUserInfoUtil.getCurrentLoginUser(user.getUserToken());
        user.setFormmakerId(loginUser.getUserId());
        user.setFormmakerName(loginUser.getLoginName());
        user.setId(IdGenerator.getUUID());

        // 创建用户
        userMapper.insertSelective(user);
        // 新建用户角色关系
        createUserAndRoleRelation(user);

        return ResultJson.buildSuccess("新增用户成功");
    }

    @Override
    public ResultJson queryUserInfo(String userId) {
        if(StringUtils.isEmpty(userId)){
            return ResultJson.buildError("参数异常!");
        }

        // 用户信息
        User user = userMapper.selectById(userId);
        // 用户角色关系
        List<Role> linkedRols = roleMapper.selectLinkedRoleByUserId(userId);
        // 获取所有角色做对比
        List<Role> freeRoles = roleMapper.selectRoleList(new Role());

        Map<String, Object> userInfoMap = new HashMap<String, Object>();
        userInfoMap.put("user", user);
        userInfoMap.put("linkedRols", linkedRols);
        userInfoMap.put("freeRoles", freeRoles);

        return ResultJson.buildSuccess("查询成功!", userInfoMap);
    }

    /**
     * 创建用户角色关系
     * @param user
     */
    private void createUserAndRoleRelation(User user) {

        List<Role> roles = user.getRoles();

        Set<String> relationSet = new HashSet<String>();

        for (Role role : roles){
            relationSet.add(role.getId());
        }

        CurrentLoginUser loginUser = LoginUserInfoUtil.getCurrentLoginUser(user.getUserToken());

        List<UserRoleRelation> relations = new ArrayList<UserRoleRelation>();

        for(String roleId : relationSet){
            UserRoleRelation relation = new UserRoleRelation();
            relation.setId(IdGenerator.getUUID());
            relation.setUserId(user.getId());
            relation.setRoleId(roleId);
            relation.setFormmakerId(loginUser.getUserId());
            relation.setFormmakerName(loginUser.getLoginName());

            relations.add(relation);
        }

        // 新建用户角色关联关系
        userMapper.batchInsertUserRoleRelation(relations);
    }

    /**
     * 用户参数校验
     * @param user
     * @return
     */
    public String validateUser(User user){

        if(user == null){
            return "参数异常";
        }
        if(StringUtils.isEmpty(user.getUserToken())){
            return "为空token为空!";
        }

        if(StringUtils.isEmpty(user.getLoginName())){
            return "登录名为空!";
        }
        if(StringUtils.isEmpty(user.getPhone())){
            return "手机号码为空!";
        }

        List<Role> roles = user.getRoles();
        if(roles == null || roles.size() < 1){
            return "用户必须指定角色!";
        }else{
            for(Role role : roles){
                if(StringUtils.isEmpty(role.getId())){
                   return "被关联的角色缺乏主键!";
                }
            }
        }
        return null;

    }


}
