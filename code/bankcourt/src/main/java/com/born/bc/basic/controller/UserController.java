package com.born.bc.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.born.bc.basic.entity.User;
import com.born.bc.basic.entity.cons.UserCons;
import com.born.bc.basic.service.api.UserServiceApi;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.utils.ResultJson;

/**
 * 用户管理Controller
 * @author wangjian
 */
@RequestMapping(value="/user")
@Controller
public class UserController {

    @Autowired
    private UserServiceApi userApi;


    /**
     * 分页查询用户列表
     * @param user
     * @param page
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultJson list(User user, PageParamVO page){
        try {
            return userApi.findUserByPage(user, page);
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.buildError("查询失败");
        }
    }

    /**
     * 启用
     * @param id
     * @return
     */
    @RequestMapping(value="/enable", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson enable(@RequestParam("id") String id){

        try {
            return userApi.updateUserEnableStatus(id, UserCons.EnableStatus_Enable);
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.buildError("操作失败");
        }
    }

    /**
     * 停用
     * @param id
     * @return
     */
    @RequestMapping(value="/disable", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson disable(@RequestParam("id") String id){

        try {
            return userApi.updateUserEnableStatus(id, UserCons.EnableStatus_Disable);
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.buildError("操作失败");
        }

    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson update(@RequestBody User user){
        try {
            return userApi.updateUser(user);
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.buildError("修改用户失败");
        }
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson add(@RequestBody User user){
        try {
            return userApi.insertUser(user);
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.buildError("新增用户失败");
        }
    }

    /**
     * 查询用户详情
     * @param userId
     * @return
     */
    @RequestMapping(value="/userInfo/{userId}")
    @ResponseBody
    public ResultJson getUserInfo(@PathVariable("userId") String userId){
        try {
            return userApi.queryUserInfo(userId);
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.buildError("查询用户失败");
        }
    }

    /**
     * 跳转用户添加页面
     * @return
     */
    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String goUserjsp(){
        return "user";
    }


}
