package com.brevy.archetype.support;

/**
 * @Description support包常量类
 * @author caobin
 * @date 2013-7-17
 * @version 1.0
 */
public class SupportConstants {

	/**
	 * json报文请求头
	 */
	public static final String WEB_REQ_HEAD = "REQ_HEAD";
	
	/**
	 * json报文请求头-流程码
	 */
	public static final String WEB_REQ_HEAD_TRAN_PROCESS = "TRAN_PROCESS";
	
	/**
	 * json报文响应头-超时标识
	 */
	public static final String WEB_RSP_HEAD_SESSION_TIMEOUT = "SESSION_TIMEOUT";
	
	/**
	 * json报文请求体
	 */
	public static final String WEB_REQ_BODY = "REQ_BODY";
	
	/**
	 * json报文响应头
	 */
	public static final String WEB_RSP_HEAD = "RSP_HEAD";
	
	/**
	 * json报文响应头 - 交易是否成功标识
	 */
	public static final String WEB_RSP_HEAD_TRAN_SUCCESS = "TRAN_SUCCESS";
	
	/**
	 * ERROR FIELDS KEY
	 */
	public static final String WEB_RSP_HEAD_ERROR_FIELDS_KEY = "ERROR_FIELDS_KEY";
	
	/**
	 * json报文响应头 - 错误字段{字段ID：字段错误信息, 字段2ID：字段2错误信息, ....}	
	 */
	public static final String WEB_RSP_HEAD_ERROR_FIELDS = "ERROR_FIELDS";
	
	/**
	 * json报文响应头 - 错误代码	
	 */
	public static final String WEB_RSP_HEAD_ERROR_CODE = "ERROR_CODE";
	
	/**
	 * json报文响应头 - 错误描述
	 */
	public static final String WEB_RSP_HEAD_ERROR_MESSAGE = "ERROR_MESSAGE";
	
	/**
	 * json报文响应体 - 登录url
	 */
	public static final String WEB_RSP_BODY_LOGIN_URL_KEY = "loginUrl";
	
	/**
	 * json报文响应体 - 首页
	 */
	public static final String WEB_RSP_BODY_DEFAULT_URL_KEY = "defaultUrl";

	
	/**
	 * json报文响应体
	 */
	public static final String WEB_RSP_BODY = "RSP_BODY";
	
	
	/**
	 * JSON视图成功/失败键值
	 */
	public static final String WEB_VIEW_RESULT_KEY = "_SUCCESS";
	
	/**
	 * JSON视图成功值
	 */
	public static final String WEB_VIEW_RESULT_SUCCESS = "1";
	
	/**
	 * JSON视图失败值
	 */
	public static final String WEB_VIEW_RESULT_FAILURE = "0";
	
	/**
	 * 控制器异常所映射的ModelAndView键值
	 */
	public static final String WEB_CONTROLLER_THROWABLE_KEY = "_CONTROLLER_THROWABLE_KEY";
	
	/**
	 * Session超时
	 */
	public static final int SC_SESSION_TIMEOUT = 505;
	
	
	/**
	 * 404
	 */
	public static final String PAGE_ERR_404 = "页面未找到";
	
	/**
	 * 403
	 */
	public static final String PAGE_ERR_403 = "拒绝访问";
	
	/**
	 * 500
	 */
	public static final String PAGE_ERR_500 = "服务器内部错误";
	
	
}
