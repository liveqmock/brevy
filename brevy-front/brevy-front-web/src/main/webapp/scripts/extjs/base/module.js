/**
 * @module 基类模块
 * @author caobin
 */
Ext.define("App.Module", {
	constructor: function (config){
		//属性应用于当前对象
		Ext.apply(this, config);
    },
	initParms : {},
    init : Ext.emptyFn,
    beforeInit : function(){
    	//apply message
    	Ext.applyIf(this, Msg[this.moduleName]);
    	Ext.apply(this, {basePath: _ctxPath});
    },
    afterInit : Ext.emptyFn,
    
    pageSizeOptions : [20, 50, 100, 200, 500],
    
    /**
     * 创建对象
	 * @param moduleName 模块名称
	 * @param moduleText 模块文本/标题
	 * @param moduleIcon 模块图标
	 * @param params 模块参数
	 * @return
	 * @author caobin
	 */
    createInstance : function(moduleName, moduleText, moduleIcon, params){
    	var config = {
    		moduleId: Pub.Utils.fastUUID(false),
			moduleName: moduleName,
			moduleText: moduleText,
			moduleIcon: moduleIcon,
			params: params
		};	
		var moduleInstance = null;
		var moduleObj = Ext.create(config.moduleName, config);
		if(moduleObj.beforeInit)moduleObj.beforeInit();
		if(moduleObj.init)moduleInstance = moduleObj.init();
		if(moduleObj.afterInit)moduleObj.afterInit();
		return moduleInstance;
    },
    
    /**
     * 创建对象(简化版)
	 * @param moduleName 模块名称
	 * @param me 继承App.Module的对象
	 * @return
	 * @author caobin
	 */
    createSimpleInstance : function(moduleName, me){
    	return this.createInstance(moduleName, me.moduleText, me.moduleIcon, me.params)
    },
    
    /**
     * 标识必填字段
	 * @param fieldLabelValue 字段标签值
	 * @param symbol 必填标识
	 * @return
	 * @author caobin
	 */
    required: function(fieldLabelValue, symbol){
    	symbol = symbol || "*";
    	return fieldLabelValue.concat("<font color='red'>", symbol, "</font>");
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
    getMixedErr : function(action, seperator){
    	seperator = seperator || "|";
    	var code = this.getErrCode(action);
    	if(Ext.isEmpty(code)){
    		seperator = "";
    	}
    	return code.concat(seperator, this.getErrMsg(action));
    },
    
    /**
     * 获取页面资源路径
	 * @param relativePath 相对于WEB-INF/views的文件路径
	 * @return
	 * @author caobin
	 */
    getExtRes : function(relativePath){
    	return ExtRES + relativePath;
    },
    
     /**
     * 获取页面请求路径
	 * @param relativePath 相对于Servlet上下文的请求路径
	 * @return
	 * @author caobin
	 */
    getRequestRes : function(relativePath){
    	return _ctxPath + relativePath;
    },
    
    /**
     * 处理Grid的选中记录
	 * @param gridID Grid ID
	 * @param callback 回调方法
	 * @param scope 作用域
	 * @param unselectedMsg 未选中提示
	 * @param allowMultiSelection 是否允许多选(默认false)
	 * @return
	 * @author caobin
	 */
	handleSelectedRecord : function(gridID, callback, scope, unselectedMsg, allowMultiSelection){
		if(Ext.getCmp(gridID).getSelectionModel().hasSelection()){
			if(!allowMultiSelection){
				if(Ext.getCmp(gridID).getSelectionModel().getCount() == 1){
					Ext.callback(callback, scope, [{"sm":Ext.getCmp(gridID).getSelectionModel().getSelection()}]);
				}else{
					Pub.MsgBox.showMsgBox(Pub.MsgBox.WARN, unselectedMsg || Msg.Prompt.warnCheck);
				}	
				return;
			}else{		
				Ext.callback(callback, scope, [{"sm": Ext.getCmp(gridID).getSelectionModel().getSelection()}]);
				return;		
			}			
		}
		Pub.MsgBox.showMsgBox(Pub.MsgBox.WARN, unselectedMsg || Msg.Prompt.warnChecks);
		
	}
});




