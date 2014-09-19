package com.brevy.core.shiro;

/**
 * @Description 角色枚举
 * @author caobin
 * @date 2013-12-24
 * @version 1.0
 */
public enum RoleType {

	/**
	 * 功能角色
	 */
	FUNC_ROLE ("1"),
	
	/**
	 * 访问权限角色
	 */
	ACCESS_PERM_ROLE ("2"),
	
	/**
	 * 操作权限角色
	 */
	OPER_PERM_ROLE ("3");
	
	RoleType(String code){
		this.code = code;
	}
	
	private final String code;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	
}
