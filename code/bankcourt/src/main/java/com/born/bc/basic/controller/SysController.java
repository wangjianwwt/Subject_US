package com.born.bc.basic.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.born.bc.basic.entity.User;
import com.born.bc.basic.service.api.UserServiceApi;
import com.born.bc.commons.service.RedisClientTemplate;
import com.born.bc.commons.utils.CryptographyUtil;
import com.born.bc.commons.utils.ResultJson;

/**
 * 用户登录
 *
 * @author wangjian
 */
@Controller
public class SysController {

    private static Logger logger = LoggerFactory.getLogger(SysController.class);

    @Autowired
    private RedisClientTemplate redisClientTemplate;

    @Autowired
    private UserServiceApi userApi;

    /**
     * 跳转至登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 跳转
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String forworld() {
        return "index";
    }


    /**
     * 用户登录
     *
     * @param user
     * @param req
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson login(@RequestBody User user, HttpServletRequest req, HttpServletResponse response) {
        try {

            if (StringUtils.isEmpty(user.getLoginName()) || StringUtils.isEmpty(user.getPassword())) {
                return ResultJson.buildParamError("用户名或者密码为空");
            }

            //开始进行用户认证
            Subject currentUser = SecurityUtils.getSubject();
            String md5Pwd = CryptographyUtil.md5(user.getPassword(), user.getLoginName());
            UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), md5Pwd);
            currentUser.login(token);

            // 使用MD5方式生成token
            String userToken = CryptographyUtil.md5(user.getPassword() + user.getLoginName(), new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

            // 登录日志
            Session session = currentUser.getSession();
            logger.debug("sessionId:" + session.getId());
            logger.debug("sessionHost:" + session.getHost());
            logger.debug("sessionTimeOut:" + session.getTimeout());

            // 获取用户基本信息
            User resultUser = userApi.getUserByLoginName(user.getLoginName());
            JSONObject userJson = convertUserToJsonObject(resultUser);

            // 将用户登录信息存放到reids中,存放时间为10小时
            String result = redisClientTemplate.set(userToken, userJson.toJSONString(), (10 * 60 * 60));
            if (!"ok".equalsIgnoreCase(result.trim())) {
                return ResultJson.buildError("添加用户信息到redis中失败了");
            }

            // 得到用户信息JSON
            JSONObject userInfoJson = getUserInfoJson(user.getLoginName());

            JSONObject userInfo = new JSONObject();
            userInfo.put("loginUser", userJson);
            userInfo.put("userInfo", userInfoJson);
            userInfo.put("userToken", userToken);

            // 将用户信息放入cookie中
            Cookie userCookie = new Cookie("userInfo", userInfo.toJSONString());

            //会话级cookie，关闭浏览器失效
            userCookie.setMaxAge(-1);

            response.setCharacterEncoding("UTF-8");

            response.setContentType("text/html;charset=UTF-8");

            response.setLocale(new java.util.Locale("zh","CN"));

            response.addCookie(userCookie);

            logger.debug("用户:" + user.getLoginName() + "登录成功");
            return ResultJson.buildSuccess("登录成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("用户:" + user.getLoginName() + "登录失败", e);
            return ResultJson.buildError("登录失败，用户名或密码错误!");
        }
    }

    /**
     * 根据登录名将用户的角色和权限封装到一个统一的Json对象中
     *
     * @param loginName
     * @return
     */
    private JSONObject getUserInfoJson(String loginName) {

        // 获取用户权限角色信息
        HashSet<String> roles = userApi.getRoleByLoginName(loginName);
        // 判断是否存在角色信息
        if (roles.isEmpty()) {
            return null;
        }

        String roleStr = "";
        for (String item : roles) {
            roleStr += "," + item;
        }
        roleStr = (StringUtils.isEmpty(roleStr)) ? "" : roleStr.substring(1);
        // 角色存redis
        redisClientTemplate.set(loginName + "_roles", roleStr, (10 * 60 * 60));

        // 根据角色ID列表获取角色对应的权限
        HashSet<String> permissions = userApi.getPermissionByRoleCodes(new ArrayList<String>(roles));

        String permissionStr = "";
        for (String item : permissions) {
            permissionStr += "," + item;
        }
        permissionStr = (StringUtils.isEmpty(permissionStr)) ? "" : permissionStr.substring(1);
        // 权限存redis
        redisClientTemplate.set(loginName + "_permissions", roleStr, (10 * 60 * 60));

        // 将用户角色权限信息封装
        JSONObject userJson = new JSONObject();
        userJson.put("roles", roleStr);
        userJson.put("permissions", permissionStr);
        return userJson;
    }

    /**
     * 注销操作
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/layout")
    @ResponseBody
    public ResultJson layout(User user) {
        if (StringUtils.isEmpty(user.getLoginName())) {
            return ResultJson.buildParamError("参数异常");
        }
        // 缓存中清除对应用户的登录信息
        redisClientTemplate.del(user.getLoginName());
        return ResultJson.buildSuccess("退出登录成功");
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }


    /**
     * 将用户信息转成json
     *
     * @param user
     * @return
     */
    private JSONObject convertUserToJsonObject(User user) {
        JSONObject json = new JSONObject();
        json.put("userId", user.getId());
        json.put("loginName", user.getLoginName());
        json.put("phone", user.getPhone());
        json.put("realName", user.getRealName());
        return json;
    }


}
