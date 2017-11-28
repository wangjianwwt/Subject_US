package com.born.bc.commons.controller;

import com.born.bc.commons.utils.ResultJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/msg")
public class ConfigController {

    /**
     * 404 配置请求
     * @return
     */
    @RequestMapping(value="/outOfContact")
    public ResultJson requestOutOfContact(){
        return ResultJson.buildError("您的请求失联了......</ br> 错误码：404");
    }

    /**
     * 500 配置请求
     * @return
     */
    @RequestMapping(value="/error")
    public ResultJson requestError(){
        return ResultJson.buildError("当前网络环境异常,请稍后再试......</ br> 错误码：500");
    }

    /**
     * 没有权限
     * @return
     */
    @RequestMapping(value="/unauthorized")
    public ResultJson unauthorized() { return ResultJson.buildError("您沒有权限请求该页面......</ br> 错误码：403"); }
}
