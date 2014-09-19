/********** 公共支持模块  ************/

/**
 * @description Ajax支持
 * @author caobin
 */
Ext.define("Pub.support.Ajax", {
	statics : {
		/**
		 * @description 自定义所有Ajax事件的处理方式
		 * @static
		 */
		initAllAjaxEvents : function(){
			this.initBeforerequestEvent();
			this.initRequestcompleteEvent();
			this.initRequestexceptionEvent();
		},
		
		/**
		 * @description beforerequest Event
		 * @static
		 */
		initBeforerequestEvent: function(){
			Ext.Ajax.on("beforerequest", function(c,o,eo){
				if(o.jsonData && o.method && o.method.toUpperCase() == "POST"){
					var reqMsg = {REQ_HEAD:{SESSION_TIMEOUT:false},REQ_BODY:{}};
					reqMsg.REQ_BODY = o.jsonData;
					o.jsonData = reqMsg;
				}
			});
		},
		
		/**
		 * @description Requestcomplete Event
		 * @static
		 */
		initRequestcompleteEvent: function(){
			Ext.Ajax.on("requestcomplete", function(c,r,o,eo){

			});
		},
		
		/**
		 * @description Requestexception Event
		 * @static
		 */
		initRequestexceptionEvent : function(){
			Ext.Ajax.on("requestexception", function(c,r,o,eo){
				var callback = function(){
					if(Ext.getCmp("captcha")){
						Ext.getCmp("captcha").loadImage();
					}
				}
				if(o.failure){
					o.failure = function(form, action){
						Ext.MessageBox.hide(null, function(){
							if(r.timedout){
								Pub.utils.MessageSourceUtils.showMsgBox("error/E0104", callback, null, null);
							}else{
								Pub.utils.MessageSourceUtils.showMsgBox("error/E0108", callback, null, [r.status, r.responseText]);
							}			
						});			
					}
				}
			}, this);
		},
	
		
		/**
		 * @description 重构响应报文的格式处理方式
		 * @static
		 */
		overrideAllResponseHandlers : function(){
			this.overrideSubmitResponseHandler();
		},
		
		/**
		 * @description Override Ext.form.action.Submit.onSuccess
		 * @static
		 */
		overrideSubmitResponseHandler : function(){
			Ext.override(Ext.form.action.Submit, {
				onSuccess: function(response) {
				    var form = this.form,		   
				    result = this.processResponse(response),
				    success = true;
				    if (result.RSP_HEAD && result !== true && result.RSP_HEAD.TRAN_SUCCESS == "0") {
				    	if (result.RSP_HEAD.ERROR_CODE || result.RSP_HEAD.ERROR_MESSAGE || result.RSP_HEAD.ERROR_FIELDS) {
				    		if(result.RSP_HEAD.ERROR_CODE == "_SHIRO_AUTHENTICATE_FAILED"){
				    			window.location.href = result.RSP_BODY.loginUrl;
				    		}
				            form.markInvalid(result.errors = result.RSP_HEAD.ERROR_FIELDS);
				        }
				        this.failureType = Ext.form.action.Action.SERVER_INVALID;
				        success = false;
				    }
				    form.afterAction(this, success);
				}
			});
		}
	}
});



/** 系统初始化工作 **/
Pub.support.Ajax.initAllAjaxEvents();

Pub.support.Ajax.overrideAllResponseHandlers();
