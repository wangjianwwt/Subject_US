package com.born.bc.basic.service;

import com.alibaba.druid.util.StringUtils;
import com.born.bc.basic.dao.PermissionMapper;
import com.born.bc.basic.entity.Permission;
import com.born.bc.basic.service.api.PermissionServiceApi;
import com.born.bc.commons.entity.CurrentLoginUser;
import com.born.bc.commons.utils.IdGenerator;
import com.born.bc.commons.utils.LoginUserInfoUtil;
import com.born.bc.commons.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限Service
 * @author wangjian
 */
@Service
public class PermissionServiceImpl implements PermissionServiceApi {

    @Autowired
    private PermissionMapper mapper;

    @Override
    public ResultJson list(Permission permission) {
        try {
            List<Permission> permissions = mapper.selectPermissionList(permission);
            return ResultJson.buildSuccess("查询成功!", permissions);
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.buildSuccess("查询系统错误!");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultJson add(Permission permission) {
        // 校验参数
        String msg = validatePermission(permission);
        if(!StringUtils.isEmpty(msg)){
            return ResultJson.buildParamError(msg);
        }
        // 获取当前登录的用户
        CurrentLoginUser user = LoginUserInfoUtil.getCurrentLoginUser(permission.getUserToken());
        permission.setId(IdGenerator.getUUID());
        permission.setFormmakerId(user.getUserId());
        permission.setFormmakerName(user.getLoginName());

        mapper.insertSelective(permission);
        return ResultJson.buildSuccess("新建权限成功!");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultJson update(Permission permission) {
        if(StringUtils.isEmpty(permission.getId())){
            return ResultJson.buildParamError("缺乏权限主键!");
        }
        String msg = validatePermission(permission);
        if(!StringUtils.isEmpty(msg)){
            return ResultJson.buildParamError(msg);
        }

        mapper.updateSelective(permission);
        return ResultJson.buildSuccess("修改权限成功!");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultJson del(String id) {
        if(StringUtils.isEmpty(id)){
            return ResultJson.buildParamError("参数异常!");
        }
        // 校验权限是否被占用
        Integer linkedLine = mapper.checkPermissionIsLinked(id);
        if(linkedLine != null && linkedLine > 0){
            return ResultJson.buildError("权限已经被占用,无法被删除!");
        }
        // 执行删除
        mapper.updateDelStatus(id);

        return ResultJson.buildSuccess("删除成功!");
    }

    /**
     * 验证权限参数
     * @param permission
     * @return
     */
    private String validatePermission(Permission permission) {
        if(permission == null){
            return "参数异常!";
        }
        if(StringUtils.isEmpty(permission.getUserToken())){
            return "用户token为空!";
        }
        if(StringUtils.isEmpty(permission.getPermissionName())){
            return "权限名为空!";
        }
        return null;
    }
}
