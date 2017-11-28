package com.born.bc.basic.service.api;

import com.born.bc.basic.entity.Role;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.utils.ResultJson;

/**
 * 角色逻辑API
 * @author wangjian
 */
public interface RoleServiceApi {

    /**
     * 获取角色列表
     * @param role
     * @return
     */
    public ResultJson list(Role role);

    /**
     * 添加角色
     * @param role
     * @return
     */
    public ResultJson add(Role role);

    /**
     * 修改角色
     * @param role
     * @return
     */
    public ResultJson update(Role role);

    /**
     * 删除角色
     * @param id
     * @return
     */
    public ResultJson del(String id);


}
