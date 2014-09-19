/************** 公共资源模块 ***************/
/**
 * @description 资源动态加载器
 * @author caobin
 */
Ext.define("Pub.resource.Loader", {
	//static methods
	statics : {
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
		 * @static
		 * @author caobin
		 */
		loadModule: function(module, callback) {
			var moduleLoadUrl = this._LoadUrlCfg.moduleLoadUrl.concat("xml=", module);
			module = module.replace(/(\w+\.xml)$/,"");
			Ext.Ajax.request({  
					method:"POST", 
		            url: moduleLoadUrl,
		            scope:this,
		            success:function(response) {
		            	var r = eval("(".concat(response.responseText, ")"));   
		            	var config = {};  					
	   					config.moduleId = r.module.moduleId  || Pub.utils.IdentityUtils.getUUID(false);	
	   					config.moduleName = r.module.moduleName;
	   					if(!config.moduleName){
	   						 Ext.MessageBox.show({
						        title: "脚本错误",
						        msg: "在已加载的module文件中，标签moduleName是必填的。",
						        buttons: Ext.MessageBox.OK,
						        icon: Ext.MessageBox.ERROR
						    });
	   						return;
	   					}				
	   					config.moduleText = r.module.moduleText  || "";
	   					config.moduleIcon = r.module.moduleIcon  || "icon-table";
	   					
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
						if(callback){//手动初始化
							//资源批量动态加载
		            		this.resourcePack(css, js, callback, config);
						}else{//自动初始化(模块必须继承Brevy.base.Module)
							this.resourcePack(css, js, function(c){
								var moduleObj = Pub.utils.ReflectionUtils.newInstance(c.moduleName, c);
								moduleObj.beforeInit();
								moduleObj.init();
								moduleObj.afterInit();		
							}, config);
						}
						           	    
		            }	
		     });
		},
		
		/************************* 以下为私有静态方法 ****************************/
		
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
		 * @description 初始URL加载配置
		 * @static
		 * @author caobin
		 */
		_LoadUrlCfg : {
			jsLoadUrl : "common/loadJs.jsp?js=",
			cssLoadUrl : "common/loadCss.jsp?css=",
			moduleLoadUrl : "common/loadModule.jsp?"
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
					method:"POST", 
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
					method:"POST", 
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
