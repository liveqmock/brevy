/*** 资源文件前缀定位 ***/
var ExtRES = "/WEB-INF/views/";

/**
 * @description 样式
 * @author caobin
 */
Ext.define("Pub.Style", {
	statics : {
		grayBody : "background-color:#f1f1f1;",
		
		/**
		 * @description 带图标的field
		 * @param {string} url 图标URL 
		 * @param {string} pos 位置（left/right）
		 * @param {number} posPadding 位置间距(单位：px)
		 * @return 
		 * @static
		 * @author caobin
		 */
		fieldIcon : function(url, pos, posPadding){
			posPadding = (posPadding || 20) + "px";
			return pos == "left" ? 
				{background: "url(".concat(url).concat(") no-repeat ").concat(pos), paddingLeft: posPadding} : 
				pos == "right" ? 
				{background: "url(".concat(url).concat(") no-repeat ").concat(pos), paddingRight: posPadding} :
				null
		}
	}
});

/**
 * @description 数据
 * @author caobin
 */
Ext.define("Pub.Data", {
	statics : {
		/**
		 * @description 菜单图标数据
		 * @return 
		 * @static
		 * @author caobin
		 */
		menuIcons : function(){
			var r = [];
			Ext.Object.each(Ext.ux.BizIcons, function(k,v,me){
				if(Ext.String.startsWith(v, "icon-")){
					r.push([v, k])
				}	
			});

			return r;
		}
	}
	
});

/**
 * @description （常规）工具
 * @author caobin
 */
Ext.define("Pub.Utils", {
	statics : {
		/**
		 * @description 获取(替换占位符后)消息
		 * @param {string} msg 原始消息 (如："Hello, {0}. Hello, {1}")
		 * @param {Mixed.../Array} args 占位参数值
		 * @return
		 * @static
		 * @author caobin
		 */
		getMessage: function(msg) {
			var args;
			if(arguments.length > 1 && Array.isArray(arguments[1])){
				args = arguments[1];
			}else{
				args = Ext.Array.toArray(arguments, 1);
			}   
			return msg.replace(/\{(\d+)\}/g, function(m, i) {
			    return args[i];
		    });
	    },
	    
	    /**
		 * @description 获取UUID
		 * @param {boolean} useDash 是否以"-"分割
		 * @return
		 * @static
		 * @author caobin
		 */
		fastUUID : function(useDash){
			var uuid = Ext.data.IdGenerator.get("uuid").generate();
			return useDash ? uuid : uuid.replace(/\-/g, "");
		},
		
		/**
		 * @description 控件聚焦
		 * @param form {Ext.form.Basic} form对象
		 * @param index {number} （表单中的）控件位置
		 * @static
		 * @author caobin
		 */
		focus : function(form, index){
			form.getFields().getAt(index).focus();
		},
		
		/**
		 * @description 字段提示tips
		 * @param msgTarget {string} 可选值-"qtip/title/under/side/none/[element id]"
		 * @param dismissDelay {number} 持续时间(ms)
		 * @static
		 * @author caobin
		 */
		fieldTips : function(msgTarget){
			Ext.form.Field.prototype.msgTarget = msgTarget;
		},
		/**
		 * @description 快速提示
		 * @param dismissDelay {number} 持续时间(ms)
		 * @static
		 * @author caobin
		 */
		quickTips : function(dismissDelay){
			dismissDelay = dismissDelay || 5000;
			Ext.tip.QuickTipManager.init();
			Ext.apply(Ext.tip.QuickTipManager.getQuickTip(), {
				 dismissDelay: dismissDelay 
			});
		},
		
		/**
		 * @description Grid单元格提示
		 * @param value 值
		 * @param metadata
		 * @return
		 * @static
		 * @author caobin
		 */
		cellTips: function(value, metadata){
			if(!value)return "";
			metadata.tdAttr = 'data-qtip="' + value +'"';  
	        return value;  
		}
	}	
});

/**
 * @description 通知框
 * @author caobin
 */
Ext.define("Pub.Notification", {

	statics: {
		INFO : "info",
		
		ERROR : "error",
		
		 /**
		 * @description 显示通知
		 * @param {string} type 通知类型(info, error)
		 * @param {string} html 通知内容
		 * @param {string} pos 通知位置(br, bl, tr, tl, t, l, b, r)
		 * @static
		 * @author caobin
		 */
		showNotification : function(type, html, pos){
			var title, iconCls;
			switch(type){
				case this.INFO:
					title = Msg.Prompt.infoTitle;
					iconCls = "ux-notification-icon-information";
					break;
				case this.ERROR:
					title = Msg.Prompt.errorTitle;
					iconCls = "ux-notification-icon-error";
					break;
				default:
					throw new Error(Ext.String.format("Pub.Notification.showNotification - invalid type : {0}", type));
			}
			
			Ext.create("widget.uxNotification", {
				title: title,
				position: pos,
				paddingX: 10,
				cls: "ux-notification-light",
				iconCls: iconCls,
				minWidth: 220,
				html: html,
				autoCloseDelay: 3500,
				slideBackDuration: 500,
				slideInAnimation: "bounceOut",
				slideBackAnimation: "easeIn"
			}).show();
		}
	}
});

/**
 * @description 支持类
 * @author caobin
 */
Ext.define("Pub.Support", {

	statics: {
		 /**
		 * @description date类型转换
		 * @param {timestamp} v 日期的timestamp 
		 * @static
		 * @author caobin
		 */
		dateConvert: function(v){
	    	if(!v)return v;
	    	return new Date(v);
	    }
	}
});

/**
 * @description 消息框
 * @author caobin
 */
Ext.define("Pub.MsgBox", {

	statics: {
		
		INFO : "info",
		
		WARN : "warn",
		
		ERROR : "error",
		
		CONFIRM : "confirm",
		
		PROMPT : "prompt",
		
	    /**
		 * @description 显示交互框
		 * @param {string} msgType 消息类型(info, warn, error, confirm)
		 * @param {string} message 消息
		 * @param {string/Array} args 占位参数值
		 * @param {function} callback 回调方法
		 * @param {object} scope 作用域
		 * @static
		 * @author caobin
		 */
		showMsgBox : function(msgType, message, args, callback, scope){
			var msg = Pub.Utils.getMessage(message, Array.isArray(args) ? args : [args]);	
			switch(msgType){
				case this.INFO:
					var title = Msg.Prompt.infoTitle;
					var icon = Ext.Msg.INFO;	
					var btn = Ext.Msg.OK;
					break;
				case this.WARN:
					var title = Msg.Prompt.warnTitle;
					var icon = Ext.Msg.WARNING;
					var btn = Ext.Msg.OK;
					break;
				case this.ERROR:
					var title = Msg.Prompt.errorTitle;
					var icon = Ext.Msg.ERROR;
					var btn = Ext.Msg.OK;
					break;
				case this.CONFIRM:
					var title = Msg.Prompt.confirmTitle;
					var icon = Ext.Msg.QUESTION;
					var btn = Ext.Msg.YESNO
					break;
				default:
					throw new Error(Ext.String.format("Pub.MsgBox.showMsgBox - invalid msgType : {0}", msgType));
			}
			//显示消息框
			Ext.Msg.show({
				title : title,
				msg : msg,
				icon : icon,
				buttons : btn,
				fn : callback, 
				scope : scope
			});	
		}
	}
});


/**
 * @description 资源动态加载器
 * @author caobin
 */
Ext.define("Pub.ResLoader", {
	statics : {
		
		/**
		 * @description 初始URL加载配置
		 * @static
		 * @author caobin
		 */
		_LoadUrlCfg : {
			jsLoadUrl : _ctxPath + "/common/loadJs.jsp?js=",
			cssLoadUrl : _ctxPath + "/common/loadCss.jsp?css=",
			moduleLoadUrl : _ctxPath + "/common/loadModule.jsp?"
		},
		
		/**
		 * @description 动态(可批量)加载JS文件
		 * @param {array|string} js js文件全路径(参考packtag的src部分)
		 * @param {function} callback 回调方法
		 * @param {object} p 回调方法参数
		 * @param {object} scope 作用域
		 * @static
		 * @author caobin
		 */
		jsPack : function(js, callback, p, scope){	
			scope = scope || window;
			p = p || {};
			if(typeof js == "string"){
				this._LoadSingleJs(this._srcTagWrap(js), callback, p, scope);
			}else if(js instanceof Array && js.length > 0){
				var jsResources = "";
				for(var i in js){
					jsResources += this._srcTagWrap(js[i]);
				}
				this._LoadSingleJs(jsResources, callback, p, scope);	
			}else{
				Ext.callback(callback, scope, p instanceof Array ? p : [p]);
			}
		},
		
		/**
		 * @description 动态(可批量)加载CSS文件
		 * @param {array|string} css css文件全路径(参考packtag的src部分)
		 * @param {function} callback 回调方法
		 * @param {object} p 回调方法参数
		 * @param {object} scope 作用域
		 * @static
		 * @author caobin
		 */
		cssPack : function(css, callback, p, scope){	
			scope = scope || window;
			p = p || {};
			if(typeof css == "string"){
				this._LoadSingleCss(css, callback, p, scope);
			}else if(css instanceof Array && css.length > 0){
				//由于资源(特别是图片等资源)路径关系，需要每个css资源独立加载
				this._cssBatchPack({index:0, size:css.length, css:css, callback:callback, param:p, scope:scope, self:this});
			}else{
				Ext.callback(callback, scope, p instanceof Array ? p : [p]);
			}
		},
		
		/**
		 * @description 动态(可批量)加载CSS和JS文件
		 * @param {array|string} css css文件全路径(参考packtag的src部分)
		 * @param {array|string} js js文件全路径(参考packtag的src部分)
		 * @param {function} callback 回调方法
		 * @param {object} p 回调方法参数
		 * @param {object} scope 作用域
		 * @static
		 * @author caobin
		 */
		resourcePack : function(css, js, callback, p, scope){
			//先加载CSS资源，再加载JS资源
			this.cssPack(css, function(){
				this.jsPack(js, callback, p, scope);
			}, null, this);
		},
		

		/**
		 * @description 动态加载（业务）模块
		 * @param module {string} 模块xml全路径
		 * @param callback {function} 回调函数
		 * @param params {object} 参数
		 * @static
		 * @author caobin
		 */
		loadModule : function(module, callback, params) {
			var moduleLoadUrl = this._LoadUrlCfg.moduleLoadUrl.concat("xml=", module);
			module = module.replace(/(\w+\.xml)$/,"");
			Ext.Ajax.request({  
					method:"POST", 
		            url: moduleLoadUrl,
		            scope:this,
		            success:function(response) {
		            	var r;
		            	try{
		            		r = eval("(".concat(response.responseText, ")")); 
		            	}catch(e){
		            		Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR, Msg.Prompt.moduleLoadFailed, [response.responseText]);
		            		return;
		            	}
		            	  
		            	var config = {};  					
	   					config.moduleId = r.module.moduleId  || Pub.Utils.fastUUID(false);	
	   					config.moduleName = r.module.moduleName;
	   					if(!config.moduleName){
	   						throw new Error("value of tag[moduleName] is required.");
	   					}				
	   					config.moduleText = r.module.moduleText || "";
	   					config.moduleIcon = r.module.moduleIcon || "icon-table";
	   					
	   					var css = [], js = [];
	   					//获取js资源
		            	if(r.module.files.css && r.module.files.css.file){      		
		            		if((r.module.files.css.file) instanceof Array){				
								for(var i = 0; i < r.module.files.css.file.length; i++){
									css.push(module+r.module.files.css.file[i]);
								}
								config.css = css.join(",");
							}else{
								css.push(module+r.module.files.css.file);
								config.css = css[0];
							}
		            	}
		            	//获取css资源
		            	if(r.module.files.js && r.module.files.js.file){				
							if((r.module.files.js.file) instanceof Array){							
								for(var i = 0; i < r.module.files.js.file.length; i++){
									js.push(module+r.module.files.js.file[i]);
								}
								config.js = js.join(",");
							}else{
								js.push(module+r.module.files.js.file);
								config.js = js[0];
							}
						}
						
						config.params = params || {};
						
						if(callback){//手动初始化
							//资源批量动态加载
		            		this.resourcePack(css, js, callback, config);
						}else{//自动初始化(模块需继承App.Module)
							this.resourcePack(css, js, function(c){
								var moduleObj = Ext.create(c.moduleName, c);
								if(moduleObj.beforeInit)moduleObj.beforeInit();
								if(moduleObj.init)moduleObj.init();
								if(moduleObj.afterInit)moduleObj.afterInit();		
							}, config);
						}
						           	    
		            }, 
		            failure : function(response){
		            	Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR, Msg.Prompt.requestFailed, [response.status, response.responseText]);
		            }
		     });
		},
		
		/**
		 * @description 异步css批量递归
		 * @param {object} p 递归参数 
		 * @static
		 * @author caobin
		 */
		_cssBatchPack : function(p){
			with(p){
				if(index <= size){
					var originalIdx = index;
					index += 1;
					if(index == size){//迭代至最后一个元素，执行逻辑回调方法
						self._LoadSingleCss(css[originalIdx], callback, param, scope);
					}else{//未迭代完成时，回调动态加载css的方法
						self._LoadSingleCss(css[originalIdx], self._cssBatchPack, p, scope);
					}
				}
			}
			
		},
	
		
		
		/**
		 * @description 用packtag标签动态加载单个js文件(调用时："<src>" + js + "</src>")
		 * @param {string} js js文件路径(参考packtag的src部分)
		 * @param {function} callback 回调方法
		 * @param {object} p 回调方法参数
		 * @param {object} scope 作用域
		 * @static
		 * @author caobin
		 */
		_LoadSingleJs: function(js, callback, p, scope) {
			scope = scope || window;
			var jsURL = this._LoadUrlCfg.jsLoadUrl + js;
			//获取执行中的function对象
			var f = arguments.callee;
			if (!("queue" in f)){
				f.queue = {};	
			}			
			var queue =  f.queue;
			//已加载js则执行回调方法
			if (js in queue) {
				if (callback) {
					Ext.callback(callback, scope, p instanceof Array ? p : [p]);
				}
				return;
			}
			//值封装为数组形式
			queue[js] = callback ? [callback] : [];
			Ext.Ajax.request({   
					method:"GET", 
		            url:jsURL,
		            success:function(response) {
						var result = eval("("+response.responseText+")");             	
		            	var url = result.js.replace(/.+src=\"(.+\.pack)\".+/ig, "$1");
						var script = document.createElement("script");
						script.type = "text/javascript";
						script.charset = "UTF-8";
						//兼容IE及FF的处理
						script.onload = script.onreadystatechange = function() {
							if (script.readyState && script.readyState != "loaded" && script.readyState != "complete")return;
							script.onreadystatechange = script.onload = null;
							while (queue[js].length){
								//执行获取到的首个数组元素(回调方法)并将此元素从数组中移除
								Ext.callback(queue[js].shift(), scope, p instanceof Array ? p : [p]);
							}	
							queue[js] = null;
						};
						script.src = url;
						document.getElementsByTagName("head")[0].appendChild(script);
		            }            
		        }
		     )			
		},
		
		/**
		 * @description 用packtag标签动态加载单个css文件(调用时：css)
		 * @param {string} css css文件路径(参考packtag的src部分)
		 * @param {function} callback 回调方法
		 * @param {object} p 回调方法参数
		 * @param {object} scope 作用域
		 * @static
		 * @author caobin
		 */
		_LoadSingleCss: function(css, callback, p, scope) {
			scope = scope || window;
			var cssURL = this._LoadUrlCfg.cssLoadUrl + css;
			//获取执行中的function对象
			var f = arguments.callee;
			if (!("queue" in f)){
				f.queue = {};	
			}			
			var queue =  f.queue;
			//已加载css则执行回调方法
			if (css in queue) {
				if (callback) {
					Ext.callback(callback, scope, p instanceof Array ? p : [p]);
				}
				return;
			}
			//值封装为数组形式
			queue[css] = callback ? [callback] : [];
			Ext.Ajax.request({   
					method:"GET", 
		            url:cssURL,
		            success:function(response) {
						var result = eval("("+response.responseText+")");             	
		            	var url = result.css.replace(/.+href=\"(.+\.pack)\".+/ig, "$1");
						var link = document.createElement("link");
						link.type = "text/css";
						link.rel = "stylesheet";
						//兼容IE及FF的处理
						link.onload = link.onreadystatechange = function() {
							if (link.readyState && link.readyState != "loaded" && link.readyState != "complete")return;
							link.onreadystatechange = link.onload = null;
							while (queue[css].length){
								//执行获取到的首个数组元素(回调方法)并将此元素从数组中移除
								Ext.callback(queue[css].shift(), scope, p instanceof Array ? p : [p]);
							}	
							queue[css] = null;
						};
						link.href = url;
						document.getElementsByTagName("head")[0].appendChild(link);
		            }            
		        }
		     )			
		},
		
		/**
		 * @description 用packtag的src节点包装资源
		 * @param {string} val js/css文件路径(参考packtag的src部分)
		 * @return
		 * @static
		 * @author caobin
		 */
		_srcTagWrap : function(val){
			return "<src>" + val + "</src>";
		}
	}
});


/**
 * @description Ajax支持
 * @author caobin
 */
Ext.define("Pub.Ajax", {
	statics : {

		/**
		 * @description 自定义全局Ajax事件的处理方式
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
				if(o.jsonData){
					var reqMsg = {REQ_HEAD:{},REQ_BODY:{}};
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
			/*Ext.Ajax.on("requestcomplete", function(c,r,o,eo){
				console.log(c);
				console.log("====")
				console.log(r);
			});*/
		},
		
		/**
		 * @description Requestexception Event
		 * @static
		 */
		initRequestexceptionEvent : function(){
			var me = this;
			Ext.Ajax.on("requestexception", function(c,r,o,eo){
				var callback = function(){
					//验证码刷新
					if(Ext.getCmp("_captcha")){
						Ext.getCmp("_captcha").loadImage();
					}
				}
				if(o.failure){
					o.failure = function(form, action){
						Ext.MessageBox.hide(null, function(){
							if(r.timedout){
								Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR, Msg.Prompt.requestTimeout, null, callback);
							}else if(r.status == Msg.HttpCode.SC_SESSION_TIMEOUT){
								me._showSessionTimeoutMsgBox(r);
							}else if(r.status == Msg.HttpCode.SC_FORBIDDEN){
								me._showForbiddenMsgBox(r);
							}else{
								Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR, Msg.Prompt.requestFailed, [r.status, r.responseText], callback);
							}			
						});			
					}
				}else{
					if(r.status == Msg.HttpCode.SC_SESSION_TIMEOUT){
						me._showSessionTimeoutMsgBox(r);
					}else if(r.status == Msg.HttpCode.SC_FORBIDDEN){
						me._showForbiddenMsgBox(r);
					}else{
						Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR, Msg.Prompt.requestFailed, [r.status, r.responseText], null);
					}
					
				}
			}, this);
		},
	
		
		_showSessionTimeoutMsgBox : function(r){
			Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR, Msg.Prompt.sessionTimeout, null, function(){
				var redirectUrl = eval("(" + r.responseText + ")").RSP_BODY.loginUrl;
				window.top.location = _ctxPath + redirectUrl;
			});
		},
		
		_showForbiddenMsgBox : function(r){
			Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR, Msg.Prompt.SC_403, null, function(){
				var redirectUrl = eval("(" + r.responseText + ")").RSP_BODY.defaultUrl;
				location.href = _ctxPath + redirectUrl;
			});	
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
				    status = true;
				    if (result.RSP_HEAD && result !== true && result.RSP_HEAD.TRAN_SUCCESS == "0") {
				    	if (result.RSP_HEAD.ERROR_CODE || result.RSP_HEAD.ERROR_MESSAGE || result.RSP_HEAD.ERROR_FIELDS) {
				    		if(result.RSP_HEAD.ERROR_CODE == "_SHIRO_AUTHENTICATE_FAILED"){
				    			window.location.href = _ctxPath + result.RSP_BODY.loginUrl;
				    		}else if(result.RSP_HEAD.ERROR_CODE == "_SHIRO_AUTHORIZED_FAILED"){
				    			Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR, Msg.Prompt.SC_403, null, function(){
									window.top.location = _ctxPath + result.RSP_BODY.defaultUrl;
								});	
				    		}
				            form.markInvalid(result.errors = result.RSP_HEAD.ERROR_FIELDS);
				        }
				        this.failureType = Ext.form.action.Action.SERVER_INVALID;
				        status = false;
				    }
				    form.afterAction(this, status);
				}
			});
		}
	}
});


/** 初始化工作 **/
//请求超时设置1小时
Ext.Ajax.timeout = 3600000;

Pub.Ajax.initAllAjaxEvents();

Pub.Ajax.overrideAllResponseHandlers();
		
/** 增加JsonProxy **/
Ext.define("Ext.data.proxy.JsonAjaxProxy", {
	extend:"Ext.data.proxy.Ajax",
	alias:"proxy.jsonajax",
	
	limitParam : null, 
	 
    startParam : null,
    
    pageParam : null,
	
	actionMethods : {
		create: "POST", 
		read: "POST", 
		update: "POST", 
		destroy: "POST"
	},
	
	buildRequest:function (operation) {
	    var req = this.callParent(arguments);
	    req.jsonData = req.params || {};
	    req.params = {};
	    return req;
	},
	
	/*
	 * @override
	 * Inherit docs. We don't apply any encoding here because
	 * all of the direct requests go out as jsonData
	 */
	applyEncoding: function(value){
	    return value;
	}
});

/** 增加JsonPagingProxy，分页专用 **/
Ext.define("Ext.data.proxy.JsonPagingProxy", {
	extend:"Ext.data.proxy.JsonAjaxProxy",
	alias:"proxy.jsonpaging",
	
	limitParam : "limit", 
	 
    startParam : "start",
    
    pageParam : "page"
    
});

/**
* @description 为Ajax请求添加LoadMask
* @param options.loadMask true 开启等待蒙板
* @param options.loadMaskEl 渲染蒙板的节点，默认为Ext.getBody()
* @param options.loadMaskMsg 蒙板信息，默认为"Please wait..."
* @author caobin
*/
Ext.override(Ext.data.Connection, {
	
	request : function(options){
		if(options.loadMask){		
			if(!options.loadMaskEl)options.loadMaskEl = Ext.getBody();
			if(!options.loadMaskMsg) options.loadMaskMsg = "Please wait...";			
			options.loadMaskEl.mask(options.loadMaskMsg);
		}
		//设置默认Content-Type
		options.headers = {"Content-Type": "application/json"};
		this.callParent(arguments);
	},
	
	onComplete : function(request, xdrResult) {
		if(request.options.loadMaskEl){
			with(request.options){
				if(loadMaskEl && loadMaskEl.isMasked()){
					loadMaskEl.unmask();
				}
			}
		}	
		this.callParent(arguments);
	}
});
