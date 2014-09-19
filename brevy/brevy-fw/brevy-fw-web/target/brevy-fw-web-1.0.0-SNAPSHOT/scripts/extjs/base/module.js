/**
 * @module 基类模块
 * @author caobin
 */
Ext.define("Brevy.base.Module", {
	constructor: function (config){
		//属性应用于当前对象
		Ext.apply(this, config);
    },
	initParms : {},
    init : Ext.emptyFn,
    beforeInit : function(){
    	var msg = {};
    	//应用locale message
    	Ext.apply(msg, Message.resource.LocaleText.Common);
    	Ext.apply(msg, Message.resource.LocaleText[this.moduleName]);
    	Ext.applyIf(this, {msg: msg});	
    	Ext.applyIf(this, Pub.utils.MessageSourceUtils)
    },
    afterInit : Ext.emptyFn,
    
    /**
     * 标识必填字段
	 * @param fieldLabelValue 字段标签值
	 * @param symbol 必填标识
	 * @return
	 * @author caobin
	 */
    required: function(fieldLabelValue, symbol){
    	symbol = symbol || "*";
    	return fieldLabel.concat("<font color='red'>", symbol, "</font>");
    },
    
    /**
     * 从form action中获取错误代码
	 * @param action formAction
	 * @return
	 * @author caobin
	 */
    getErrCode : function(action){
    	return action.result.RSP_HEAD.ERROR_CODE || "";
    },
    
    /**
     * 从form action中获取错误消息
	 * @param action formAction
	 * @return
	 * @author caobin
	 */
    getErrMsg : function(action){
    	return action.result.RSP_HEAD.ERROR_MESSAGE || "";
    },
    
     /**
     * 从form action中获取错误代码|错误消息
	 * @param action formAction
	 * @param seperator 分隔符
	 * @return
	 * @author caobin
	 */
    getMixErr : function(action, seperator){
    	seperator = seperator || "|";
    	var code = this.getErrCode(action);
    	if(Ext.isEmpty(code)){
    		seperator = "";
    	}
    	return code.concat(seperator, this.getErrMsg(action));
    }
});




