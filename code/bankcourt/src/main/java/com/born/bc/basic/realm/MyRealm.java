package com.born.bc.basic.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.util.StringUtils;
import com.born.bc.basic.entity.Permission;
import com.born.bc.basic.entity.Role;
import com.born.bc.basic.entity.User;
import com.born.bc.basic.service.api.UserServiceApi;

/**
 * 认证realm
 * @author wangjian
 */
public class MyRealm extends AuthorizingRealm{

	@Autowired
	private UserServiceApi userApi;

	/**
	 * 为当前登录成功的用户授予角色和权限
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		try {
			// 拿到登录名
			String loginName = (String)principals.getPrimaryPrincipal();
			// 校验loginName是否为空
			if(StringUtils.isEmpty(loginName)){
				return null;
			}
			// 获得loginName关联的角色列表
			HashSet<String> roles = userApi.getRoleByLoginNameCache(loginName);
			if(roles.isEmpty()){
				return null;
			}
			// 根据角色ID列表获取角色对应的权限
			HashSet<String> permissions = userApi.getPermissionByRoleCodesCache(loginName, new ArrayList<String>(roles));
			if(permissions.isEmpty()){
				return null;
			}

			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			// 设置角色的Code作为角色取证
			authorizationInfo.setRoles(roles);
			authorizationInfo.setStringPermissions(permissions);
			return authorizationInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	/**
	 * 验证当前登录的用户
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		try {
			String loginName = (String)token.getPrincipal();
			
			User resultUser = userApi.getUserByLoginName(loginName);
			
			if(resultUser != null){
				String realmName = resultUser.getLoginName() + ","+ resultUser.getPassword();
				AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(resultUser.getLoginName(),resultUser.getPassword(), realmName);
				return authcInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
