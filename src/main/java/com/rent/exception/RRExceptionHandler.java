package com.rent.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rent.utils.ResultMap;


/**
 * 异常处理器

 */
@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public ResultMap handleRRException(RRException e){
		ResultMap r = new ResultMap();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());

		return r;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResultMap handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return ResultMap.error("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResultMap handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return ResultMap.error("没有权限，请联系管理员授权");
	}

	@ExceptionHandler(Exception.class)
	public ResultMap handleException(Exception e){
		logger.error(e.getMessage(), e);
		return ResultMap.error();
	}
}
