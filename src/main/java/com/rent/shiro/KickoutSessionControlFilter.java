package com.rent.shiro;

import java.io.Serializable;
import java.util.Deque;








import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;




public class KickoutSessionControlFilter  extends AccessControlFilter{
	    @SuppressWarnings("unused")
		private String kickoutUrl; //踢出后到的地址  
	    @SuppressWarnings("unused")
		private boolean kickoutAfter; //踢出之前登录的/之后登录的用户 默认踢出之前登录的用户  
	    @SuppressWarnings("unused")
		private int maxSession; //同一个帐号最大会话数 默认1  
	    @SuppressWarnings("unused")
		private SessionManager sessionManager;  
	    @SuppressWarnings("unused")
		private Cache<String, Deque<Serializable>> cache;  

	    public void setKickoutUrl(String kickoutUrl) {  
	        this.kickoutUrl = kickoutUrl;  
	    }  
	  
	    public void setKickoutAfter(boolean kickoutAfter) {  
	        this.kickoutAfter = kickoutAfter;  
	    }  
	  
	    public void setMaxSession(int maxSession) {  
	        this.maxSession = maxSession;  
	    }  
	  
	    public void setSessionManager(SessionManager sessionManager) {  
	        this.sessionManager = sessionManager;  
	    }  
	  
	    public void setCacheManager(CacheManager cacheManager) {  
	        this.cache = cacheManager.getCache("shiro-activeSessionCache");  
	    } 
	    /** 
	      * 是否允许访问，返回true表示允许 
	      */
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	 /** 
     * 表示访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，返回false表示自己已经处理了（比如重定向到另一个页面）。 
     */  
	
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		   Subject subject = getSubject(request, response);  
	        if(!subject.isAuthenticated() ) {  
	            //如果没有登录，直接进行之后的流程  
	            return true;  
	        }
	        @SuppressWarnings("unused")
			Session session = subject.getSession();  
	       
	        return true;   
	        
	        
	}

}
