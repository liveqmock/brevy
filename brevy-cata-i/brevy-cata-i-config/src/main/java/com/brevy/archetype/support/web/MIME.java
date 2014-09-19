package com.brevy.archetype.support.web;

/**
 * @Description MIME TYPE
 * @author caobin
 * @date 2013-8-5
 * @version 1.0
 */
public enum MIME {
	
	TEXT_HTML ("text/html"),
	TEXT_PLAIN ("text/plain"),
	TEXT_XML ("text/xml"),
	TEXT_JSON ("text/json"),
	
	TEXT_HTML_UTF_8 ("text/html;charset=UTF-8"),
    TEXT_PLAIN_UTF_8 ("text/plain;charset=UTF-8"),
    TEXT_XML_UTF_8 ("text/xml;charset=UTF-8"),
    TEXT_JSON_UTF_8 ("text/json;charset=UTF-8"),

		      
	APPLICATION_JSON ("application/json"),
	APPLICATION_JSON_UTF_8 ("application/json;charset=UTF-8"),
	FORM_ENCODED ("application/x-www-form-urlencoded");
	
	MIME(String code){
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
