package com.born.bc.basic.controller;

import com.born.bc.basic.entity.Role;
import com.born.bc.basic.service.api.RoleServiceApi;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色Controller
 * @author wangjian
 */
@RequestMapping(value="/role")
@RestController
public class RoleController {

    @Autowired
    private RoleServiceApi api;

    /**
     * 查询角色List
     * @param role
     * @return
     */
    @RequestMapping(value="/list")
    @ResponseBody
    public ResultJson list(Role role){

        return api.list(role);
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson add(@RequestBody Role role){

        try {
            return api.add(role);
        } catch (Exception e){
            e.printStackTrace();
            return ResultJson.buildError("添加角色出现系统错误");
        }

    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson update(@RequestBody Role role){

        try {
            return api.update(role);
        } catch (Exception e){
            e.printStackTrace();
            return ResultJson.buildError("修改角色出现系统错误");
        }
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping(value="/del", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson del(@RequestParam("id") String id){

        try {
            return api.del(id);
        } catch (Exception e){
            e.printStackTrace();
            return ResultJson.buildError("删除角色出现系统错误");
        }
    }





}
