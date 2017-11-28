package com.born.bc.basic.controller;

import com.born.bc.basic.entity.Permission;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.utils.ResultJson;
import org.springframework.web.bind.annotation.*;

/**
 * 权限Controller
 * @author wangjian
 */
public class PermissionController {

    /**
     * 查询权限List
     * @param Permission
     * @param page
     * @return
     */
    @RequestMapping(value="/list")
    @ResponseBody
    public ResultJson list(Permission Permission, PageParamVO page){

        return ResultJson.buildError("查询失败!");
    }

    /**
     * 添加权限
     * @param Permission
     * @return
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson add(@RequestBody Permission Permission){

        return ResultJson.buildError("操作失败!");
    }

    /**
     * 修改权限
     * @param Permission
     * @return
     */
    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson update(@RequestBody Permission Permission){

        return ResultJson.buildError("操作失败!");
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @RequestMapping(value="/del", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson del(@RequestParam("id") String id){

        return ResultJson.buildError("操作失败!");
    }

}
