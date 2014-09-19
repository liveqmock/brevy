/************ 公共工具模块 ************/
/**
 * @class Pub.utils.EffectUtils
 * @description 效果工具
 * @author caobin
 */
Ext.define("Pub.utils.EffectUtils", {
	statics : {
		/**
		 * @description 控件聚焦
		 * @param cid {string} 控件id
		 * @param index {number} （表单中的）控件位置
		 * @static
		 * @author caobin
		 */
		focus : function(cid, index){
			Ext.getCmp(cid).getForm().getFields().getAt(index).focus();
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
		 * @param v 值
		 * @param p 单元格
		 * @static
		 * @author caobin
		 */
		cellTips: function(v, p){
			p.attr += 'ext:qtip="' + v + '"';
			return v;
		}, 
		
		/**
		 * @description 创建聚光灯效果
		 * @param duration 持续时间(ms)
		 * @return
		 * @static
		 * @author caobin
		 */
		createSpotlightEffect : function(duration){
			return new Ext.ux.Spotlight({
		        easing: "easeOut",
		        duration: duration || 300
		    });
		}
	}	
});

/**
 * @description 标识工具
 * @author caobin
 */
Ext.define("Pub.utils.IdentityUtils", {
	//static methods
	statics : {
		/**
		 * @description 获取UUID
		 * @param {boolean} dash 是否以"-"分割
		 * @return
		 * @static
		 * @author caobin
		 */
		getUUID : function(dash){
			var uuid = Ext.data.IdGenerator.get("uuid").generate();
			return dash ? uuid : uuid.replace(/\-/g, "");
		}
	}
});

/**
 * @description 消息处理工具
 * @author caobin
 */
Ext.define("Pub.utils.MessageSourceUtils", {
	
	statics : {
		/**
		 * @description 获取(替换占位符后)消息
		 * @param {string} msg 原始消息 (如："Hello, {0}. Hello, {1}")
		 * @param {object...} args 占位参数值
		 * @return
		 * @static
		 * @author caobin
		 */
		getMessage : function(msg){
			if(msg){
				if(arguments.length > 1){
					for(var i = 1; i < arguments.length; i++){
						msg = msg.replace(new RegExp("\\{".concat(i - 1,"\\}"), "g"), arguments[i]);
					}
				}
				return msg;
			}
			return null;
		},
		
		/**
		 * @description 显示消息框
		 * @param {string} messageCode 消息代码
		 * 					(继承module后的调用，如：showMsgBox("info/success"),可用前缀info,warn,error,confirm)
		 * @param {function} callback 回调方法
		 * @param {object} scope 作用域
		 * @param {object...} args 占位参数值
		 * @static
		 * @author caobin
		 */
		showMsgBox : function(messageCode, callback, scope){
			if(messageCode){
				var msgCode = Message.resource.LocaleCode.MsgCode;
				if(messageCode.indexOf("/") == -1){
					throw new Error(this.getMessage(msgCode, messageCode));
				}
				var m = messageCode.split("/");
				var level = m[0], code = m[1];
				var title = msgCode[level]._title;
				var msg = msgCode[level][code];
				var args = [];
				if(arguments.length > 3){
					for(var i = 3; i < arguments.length; i++){
						args.push(arguments[i]);
					}
					msg = this.getMessage(msg, args);
				}
				var icon, btn;
			
				switch(level){
					case "info":
						icon = Ext.Msg.INFO;	
						btn = Ext.Msg.OK;
						break;
					case "warn":
						icon = Ext.Msg.WARNING;
						btn = Ext.Msg.OK;
						break;
					case "error":
						icon = Ext.Msg.ERROR;
						btn = Ext.Msg.OK;
						break;
					case "confirm":
						icon = Ext.Msg.QUESTION;
						btn = Ext.Msg.YESNO
						break;
					default:
						throw new Error(this.getMessage(msgCode, messageCode));
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
	}
	
});

/**
 * @description 反射工具
 * @author caobin
 */
Ext.define("Pub.utils.ReflectionUtils", {
	statics: {
		/**
		 * @description 创建对象并初始化
		 * @param {string} moduleName 模块名称
		 * @param {object} config 模块初始化配置
		 * @return
		 * @static
		 */
		newInstance: function(moduleName, config){
			eval("var obj = ".concat(moduleName, ".create(config);"));
			return obj;
		}
	}
});



