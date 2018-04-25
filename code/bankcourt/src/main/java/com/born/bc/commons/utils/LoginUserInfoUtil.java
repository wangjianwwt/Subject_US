package com.born.bc.commons.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.born.bc.commons.entity.CurrentLoginUser;
import com.born.bc.commons.service.RedisClientTemplate;

/**
 * 获取登录信息的工具类
 * @author wangjian
 */
@Service
public class LoginUserInfoUtil {

    @Autowired
    private RedisClientTemplate redisClientTemplate;

    private static LoginUserInfoUtil loginUserInfoUtil;

    @PostConstruct
    public void init(){
        loginUserInfoUtil = this;
        loginUserInfoUtil.redisClientTemplate = this.redisClientTemplate;
    }


    private LoginUserInfoUtil(){

    }

    public static CurrentLoginUser getCurrentLoginUser(String userToken){

        try {
            if(StringUtils.isEmpty(userToken)){
                // 返回空对象
                return new CurrentLoginUser();
            }

            String userInfoStr = loginUserInfoUtil.redisClientTemplate.get(userToken);

            if(StringUtils.isEmpty(userInfoStr)){
                // 返回空对象
                return new CurrentLoginUser();
            }

            // 转成JSON对象
            JSONObject userInfoJson = JSONObject.parseObject(userInfoStr);

            String userId = userInfoJson.getString("userId");

            String loginName = userInfoJson.getString("loginName");

            String realName = userInfoJson.getString("realName");

            String phone = userInfoJson.getString("phone");

            return new CurrentLoginUser(userId, loginName, realName, phone);
        } catch (Exception e){
            e.printStackTrace();
            // 返回空对象
            return new CurrentLoginUser();
        }
    }

}
