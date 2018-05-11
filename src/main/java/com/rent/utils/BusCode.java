package com.rent.utils;

/**
 * 封装常量信息
 * @author chenyihui
   2018年3月25日
 */
public enum BusCode {
	
	ERR_UNKONWN(500,"未知异常，请联系管理员"),
	FAIL_RECODE_ADD(1001,"新增应用记录失败"),
	FAIL_RECODE_DEL(1002,"删除应用记录失败"),
	FAIL_RECODE_QUERRY(1003,"查询应用记录失败"),
	FAIL_RECODE_UPDATE(1004,"修改应用记录失败"),;
	
	private Integer code;//消息代码
	
	private String msg;
	
	BusCode(Integer code, String msg)
	{
		this.code=code;
		this.msg=msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	
	

}
