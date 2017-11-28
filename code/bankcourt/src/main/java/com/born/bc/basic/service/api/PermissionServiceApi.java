package com.born.bc.basic.service.api;

import com.born.bc.basic.entity.Permission;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.utils.ResultJson;
import org.springframework.web.bind.annotation.*;

/**
 * 权限逻辑API
 * @author wangjian
 */
public interface PermissionServiceApi {

    /**
     * 查询权限List
     * @param Permission
     * @return
     */
    public ResultJson list(Permission Permission);

    /**
     * 添加权限
     * @param Permission
     * @return
     */
    public ResultJson add(Permission Permission);

    /**
     * 修改权限
     * @param Permission
     * @return
     */
    public ResultJson update(Permission Permission);

    /**
     * 删除权限
     * @param id
     * @return
     */
    public ResultJson del(String id);

}
