package com.brevy.core.shiro;

/**
 * @Description Shiro常量类
 * @author caobin
 * @date 2013-12-18
 * @version 1.0
 */
public class ShiroConstants {
	
	/**
	 * 功能角色
	 */
	public final static String FUNC_ROLE = "1";
			
	/**
	 * 资源访问角色
	 */
	public final static String ACCESS_PERM_ROLE = "2";
	
	/**
	 * 操作角色
	 */
	public final static String OPER_PERM_ROLE = "3";	
	
	public final static String AUTHENTICATE_FAILED = "_SHIRO_AUTHENTICATE_FAILED";
	
	public final static String AUTHORIZED_FAILED = "_SHIRO_AUTHORIZED_FAILED";
	
	public final static String AUTHENTICATE_FAILED_MESSAGE = "身份验证失败";
	
	public final static String AUTHORIZED_FAILED_MESSAGE = "鉴权失败";
	
	/**
	 * 无效用户状态
	 */
	public final static String INVALID_USER_STATUS = "0";
	
	/**
	 * 无效应用状态
	 */
	public final static String INVALID_APP_STATUS = "0";
	
	/**
	 * 验证码错误
	 */
	public final static String INCORRECT_CAPTCHA = "验证码错误";
	
	/**
	 * 证书或密码错误
	 */
	public final static String INCORRECT_CREDENTIAL = "用户名或密码错误";
	
	/**
	 * 用户不存在于当前系统中
	 */
	public final static String USER_NOT_EXIST = "不存在用户：[%s]";
	
	/**
	 * 应用无效
	 */
	public final static String INVALID_APP = "当前应用[%s]下的用户不存在";
	
	/**
	 * 应用禁用
	 */
	public final static String APP_DISABLED = "应用[%s]已禁用";
	
	/**
	 * 账号无效或禁用
	 */
	public final static String ACCOUNT_DISABLED = "账号[%s]已禁用";	
	
	/**
	 * 访问权限已关联角色
	 */
	public final static String ACCESS_PERM_RELATED = "访问权限[%s]已关联角色[%s]";
	
	/**
	 * 禁止多个资源访问角色关联一个访问权限
	 */
	public final static String MULTIPLE_ROLES_RELATED_ONE_ACCESS_PERM_IS_DENIED = "禁止多个资源访问角色关联一个访问权限，当前关联角色[%s]";


	/**
	 * 功能菜单叶子菜单
	 */
	public final static String FUNC_MENU_LEAF = "1";
	
	/**
	 * 通用无效状态
	 */
	public final static String STATUS_INVALID = "0";
	
	/**
	 * 通用有效状态
	 */
	public final static String STATUS_VALID = "1";
}
