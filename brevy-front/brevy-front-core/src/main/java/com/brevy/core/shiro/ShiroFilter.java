package com.brevy.core.shiro;

/**
 * @Description Shiro过滤器枚举
 * @author caobin
 * @date 2013-12-23
 * @version 1.0
 */
public enum ShiroFilter {
	
	/** 认证类过滤器 **/
	
	/**
	 * 匿名认证过滤器
	 */
	AnonymousFilter ("anon"),
	
	/**
	 * 身份认证过滤器
	 */
	FormAuthenticationFilter ("authc"),
	
	/**
	 * 自定义身份认证过滤器
	 */
	CustomFormAuthenticationFilter ("authcCustom"),
	
	/**
	 * HTTP基本认证过滤器
	 */
	BasicHttpAuthenticationFilter ("authcBasic"),
	
	/**
	 * 用户认证过滤器
	 */
	UserFilter ("user"),
	
	/**
	 * 登出认证过滤器
	 */
	LogoutFilter ("logout"),
	
	/**
	 * 不创建Session过滤器
	 */
	NoSessionCreationFilter ("noSessionCreation"),

	/** 鉴权类过滤器 **/
	
	
	/**
	 * 许可授权过滤器
	 */
	PermissionsAuthorizationFilter ("perms"),
	
	/**
	 * 角色授权过滤器
	 */
	RolesAuthorizationFilter ("roles"),
	
	/**
	 * REST服务授权过滤器
	 */
	HttpMethodPermissionFilter ("rest"),
	
	/**
	 * 端口授权过滤器
	 */
	PortFilter ("port"),
	
	/**
	 * SSL授权过滤器
	 */
	SslFilter ("ssl");
	
	
	ShiroFilter(String code){
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
