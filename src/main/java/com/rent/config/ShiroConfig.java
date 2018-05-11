package com.rent.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
import com.rent.shiro.KickoutSessionControlFilter;
import com.rent.shiro.RedisShiroSessionDao;
import com.rent.shiro.UserRealm;

/**
 * 
 * @author chenyihui
   2018年3月25日
 */
@Configuration
public class ShiroConfig {

    /**
     * 注入sessionManager
     * @param redisShiroSessionDAO
     * @param redisOpen
     * @param shiroRedis
     * @return
     */
	

	@Bean("sessionManager")
	public SessionManager sessionManager(RedisShiroSessionDao redisShiroSessionDao, @Value("${rent.redis.open}") boolean redisOpen,@Value("${rent.shiro.redis}") boolean shiroRedis )
	{
		 DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		 //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
		 sessionManager.setGlobalSessionTimeout(60*60*1000);  
		 sessionManager.setSessionValidationSchedulerEnabled(true);
		 //去除地址栏JSESSIONID参数
		 sessionManager.setSessionIdUrlRewritingEnabled(false);
	     //如果开启redis缓存且ayy.shiro.redis=true，则shiro session存到redis里
		 if(redisOpen&&shiroRedis)
		 {
			 sessionManager.setSessionDAO(redisShiroSessionDao);
		 }
		 
		 return sessionManager;

	}
	
	/**
	 * shiro监听器
	* @Title: sessionListener   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param mySessionListener
	* @param @return    设定文件   
	* @return SessionListener    返回类型   
	* @throws
	 */
	/*@Bean("sessionListener")
	public SessionListener sessionListener(MysessionListenr mySessionListener )
	{
			
		return  sessionListener(mySessionListener);
	}*/
	
	
	 /**
     * 注入securityManager
     * @param userRealm
     * @param sessionManager
     * @return
     */
	@Bean("securityManager")
	public SecurityManager securityManager(UserRealm userRealm ,  SessionManager sessionManager)
	{
		DefaultWebSecurityManager securityManager =new DefaultWebSecurityManager();
	     securityManager.setRealm(userRealm);
		securityManager.setSessionManager(sessionManager);
		return securityManager;
		
	}
	
	/**
	 * 用于控制并发登录人数的 
	* @Title: kickoutSessionControlFilter   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param kickoutSessionControlFilter
	* @param @param sessionManager
	* @param @return    设定文件   
	* @return KickoutSessionControlFilter    返回类型   
	* @throws
	 */
	@Bean("kickoutSessionControlFilter")
	public KickoutSessionControlFilter kickoutSessionControlFilter(SessionManager sessionManager)
	{
		KickoutSessionControlFilter kickoutSessionControlFilter=new KickoutSessionControlFilter();
		kickoutSessionControlFilter.setSessionManager(sessionManager);
		kickoutSessionControlFilter.setKickoutAfter(false);
		kickoutSessionControlFilter.setMaxSession(2);
		kickoutSessionControlFilter.setKickoutUrl("/login.html?kickout=1");
		return kickoutSessionControlFilter;
	}
	/**
	 * 注入shiroFilter
	 */
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager)
	{
		ShiroFilterFactoryBean shiroFilter =new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		shiroFilter.setLoginUrl("/login.html");
		shiroFilter.setUnauthorizedUrl("/unauthorizedurl.html");
		Map<String, String> filterMap =new LinkedHashMap<String, String>();
		 //放权请求
		filterMap.put("/static/**", "anon");
		filterMap.put("/templates/**", "anon");
		filterMap.put("/login.jhtml", "anon");
		filterMap.put("/Sign_in", "anon");
		filterMap.put("/saveAccount", "anon");
        filterMap.put("/listCard", "anon");

		//拦截请求
		filterMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
	}
	
	/**
	 * Shiro 生命周期处理器 
	* @Title: lifecycleBeanPostProcessor   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @return    设定文件   
	* @return LifecycleBeanPostProcessor    返回类型   
	* @throws
	 */
	 @Bean("lifecycleBeanPostProcessor")
	 public LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
	 {
		 return new LifecycleBeanPostProcessor();
	 }
	 
	 
	 @Bean
	 public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator()
	 {
		 DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
	     proxyCreator.setProxyTargetClass(true);
	     return proxyCreator;
	 }
	 
	   @Bean
	    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
	        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
	        advisor.setSecurityManager(securityManager);
	        return advisor;
	    }
	

}
