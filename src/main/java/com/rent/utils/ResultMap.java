package com.rent.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回result数据
 * @author chenyihui
   2018年3月25日
 */
public class ResultMap extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;
	
	public ResultMap() {
		//code==0,数据正常
		put("code", 0);
	}
	
	public static ResultMap error() {
		return error();
	}
	
	public static ResultMap error(String msg) {
		ResultMap r = new ResultMap();
		r.put("code", -1);
		r.put("msg", msg);
		return r;
	}
	/**
	 * 指定异常code值，和异常描述
	 * @param code
	 * @param msg
	 * @return
	 */
	public static ResultMap error(BusCode busCode) {
		ResultMap r = new ResultMap();
		r.put("code", busCode.getCode());
		r.put("msg", busCode.getMsg());
		return r;
	}

	public static ResultMap success(String msg) {
		ResultMap r = new ResultMap();
		r.put("msg", msg);
		return r;
	}
	
	public static ResultMap success(Map<String, Object> map) {
		ResultMap r = new ResultMap();
		r.putAll(map);
		return r;
	}
	
	public static ResultMap success() {
		return new ResultMap();
	}

	/**
	 * 将需要返回的数据put入
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public ResultMap put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	
	
	

}
