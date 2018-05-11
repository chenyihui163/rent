package com.rent.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rent.redis.RedisService;


/**
 * 认证Realm
 * @author chenyihui
   2018年3月31日
 */
@Component
public class UserRealm extends AuthorizingRealm{
	



	
	@Autowired
    private RedisService redisService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();

		// TODO Auto-generated method stub
/*		Account account =(Account) principals.getPrimaryPrincipal();
		
		User user=accountService.selectUserCard_byAccount(account.getAid()).getUser();//通过一对一映射获取User
		
		System.err.println(user.toString());
		//存放权限信息
		System.err.println("被授予的角色："+user.getIdentityType());
		switch (user.getIdentityType()) {
		case 0:
			info.addRole("user");//普通用户
			break;
		case 1:	
			info.addRole("operator");//普通管理员
			break;
		case 2:
			info.addRole("admin");//超级管理员
			break;
		default:
			info.addRole(null);
			break;
		}
			*/	
		return info;
	}
    
	/**
	 * 认证,登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		/*Integer aid =Integer.valueOf(token.getPrincipal().toString());
		
		//获取账号信息登录
		Account account =this.accountService.selectByPrimaryKey(aid);
		
		System.err.println("登录的用户为:"+account.toString());
			
		//访问计数
		redisService.computeLoginCount(aid.toString());
		
		//5次密码错误，将冻结账号1小时
		if(redisService.getLoginCount(aid.toString(),5))
		{
			//账号冻结
			if(redisService.bindLock(aid.toString(),1))
				
				throw new DisabledAccountException("由于密码输入错误次数大于5次，账号将冻结一个小时！");
		}
		
	
		
		//账号不存在
		if(account==null)
			
			throw new UnknownAccountException("账号或密码不正确");
	
		
		else if(account.getState()==0||account.getState()==-1)
			
			
			throw new LockedAccountException("账号已被禁止使用");
		else {
			
			redisService.unbindLock(aid.toString());//清空登陆计数
	}
		
		
		//参数分别是对象，凭证(会拿去和登陆逻辑参数比较)，真实姓名
        return new SimpleAuthenticationInfo(account,account.getPassword(),getName());*/
		return null;
	}

}
