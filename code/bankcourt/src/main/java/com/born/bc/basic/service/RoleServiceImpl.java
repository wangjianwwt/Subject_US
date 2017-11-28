package com.born.bc.basic.service;

import com.alibaba.druid.util.StringUtils;
import com.born.bc.basic.dao.RoleMapper;
import com.born.bc.basic.entity.Permission;
import com.born.bc.basic.entity.Role;
import com.born.bc.basic.entity.RolePermissionRelation;
import com.born.bc.basic.service.api.RoleServiceApi;
import com.born.bc.commons.entity.CurrentLoginUser;
import com.born.bc.commons.utils.IdGenerator;
import com.born.bc.commons.utils.LoginUserInfoUtil;
import com.born.bc.commons.utils.ResultJson;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.rmic.iiop.IDLGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * role业务实现类
 *
 * @author wangjian
 */
@Service
public class RoleServiceImpl implements RoleServiceApi {

    @Autowired
    private RoleMapper mapper;

    @Override
    public ResultJson list(Role role) {
        try {
            List<Role> list = mapper.selectRoleList(role);
            return ResultJson.buildSuccess("查询成功!", list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultJson.buildError("查询失败!");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultJson add(Role role) {
        String msg = validateRole(role);
        if (!StringUtils.isEmpty(msg)) {
            return ResultJson.buildParamError(msg);
        }

        CurrentLoginUser user = LoginUserInfoUtil.getCurrentLoginUser(role.getUserToken());
        role.setFormmakerId(user.getUserId());
        role.setFormmakerName(user.getLoginName());
        role.setId(IdGenerator.getUUID());
        // 创建角色
        mapper.insertSelective(role);
        // 创建角色权限关系
        insertRoleAndPermission(role);

        return ResultJson.buildSuccess("添加角色关系成功!");
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultJson update(Role role) {
        // 校验数据
        if(StringUtils.isEmpty(role.getId())){
            return ResultJson.buildParamError("缺乏角色主键!");
        }
        String msg = validateRole(role);
        if (!StringUtils.isEmpty(msg)) {
            return ResultJson.buildParamError(msg);
        }

        // 修改角色基本信息
        mapper.updateSelective(role);

        // 删除角色和权限关联的所有关系
        mapper.updateRoleAndPermissionDelStatus(role.getId());
        // 创建角色权限关系
        insertRoleAndPermission(role);

        return ResultJson.buildSuccess("修改角色关系成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultJson del(String id) {
        if(StringUtils.isEmpty(id)){
            return ResultJson.buildParamError("参数异常!");
        }
        // 校验角色是否被角色关联
        Integer linkedLine = mapper.checkRoleIsLinked(id);
        if(linkedLine != null && linkedLine > 0){
            return ResultJson.buildError("角色已经被使用，无法被删除!");
        }

        // 删除角色
        mapper.updateDelStatus(id);
        // 删除角色和权限关联的所有关系
        mapper.updateRoleAndPermissionDelStatus(id);

        return ResultJson.buildError("删除角色关系成功");
    }


    /**
     * 新建角色权限关系
     * @param role
     */
    private void insertRoleAndPermission(Role role){
        // 插入角色所关联的权限到关系表中
        List<Permission> permissions = role.getPermissions();
        // 拿到登录用户
        CurrentLoginUser loginUser = LoginUserInfoUtil.getCurrentLoginUser(role.getUserToken());
        // 数据转换
        List<RolePermissionRelation> relations = convertFoots(role.getId(), permissions, loginUser);
        // 入库操作
        mapper.insertRoleAndPermission(relations);
    }

    /**
     * 转角色和权限的关系
     *
     * @param roleId
     * @param permissions
     * @param user
     * @return
     */
    private List<RolePermissionRelation> convertFoots(String roleId, List<Permission> permissions, CurrentLoginUser user) {
        Set<String> permissionIdSet = new HashSet<String>();
        for (Permission permission : permissions) {
            permissionIdSet.add(permission.getId());
        }

        List<RolePermissionRelation> relations = new ArrayList<RolePermissionRelation>();
        for (String permissionId : permissionIdSet) {
            RolePermissionRelation relation = new RolePermissionRelation();
            relation.setRoleId(roleId);
            relation.setPermissionId(permissionId);
            relation.setId(IdGenerator.getUUID());
            relation.setFormmakerId(user.getUserId());
            relation.setFormmakerName(user.getLoginName());

            relations.add(relation);
        }

        return relations;
    }

    /**
     * 校验Role
     *
     * @param role
     * @return
     */
    private String validateRole(Role role) {
        if (role == null) {
            return "参数异常!";
        }
        if (StringUtils.isEmpty(role.getUserToken())) {
            return "用户token为空!";
        }
        if (StringUtils.isEmpty(role.getRoleName())) {
            return "角色名不能为空!";
        }

        List<Permission> permissions = role.getPermissions();
        if (permissions == null || permissions.size() < 1) {
            return "角色未关联权限!";
        }
        for (Permission permission : permissions) {
            if (StringUtils.isEmpty(permission.getId())) {
                return "被关联的权限缺乏主键!";
            }
        }
        return null;
    }
}
