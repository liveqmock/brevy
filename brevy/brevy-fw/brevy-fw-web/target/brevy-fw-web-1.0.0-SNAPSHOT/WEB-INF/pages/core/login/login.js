/**
 * @module 登录模块
 * @author caobin
 */
Ext.define("Brevy.module.core.Login", {
	extend : "Brevy.base.Module",
	//聚光灯效果初始化
	spot : Pub.utils.EffectUtils.createSpotlightEffect(500),
	
	//登录提交Url
	formUrl : "auth/loginAuth.json",
	
	//cookies键值username
	cookieUsernameKey : "ext.login.cookies.username",
	
	//cookies键值password
	cookiePasswordKey : "ext.login.cookies.password",
	
	buttonWidth : 80,
	
	//初始化登录UI
	init : function() {
		var loginForm = new Ext.form.Panel({
			id : Ext.id(),
			border : false,
			region : "south",
			url : this.formUrl,
			method : "POST",
			//buttonAlign : "right",
			jsonSubmit : true,
			bodyStyle : {
				paddingTop : "15px",
				background : "#DFEAF2"
			},
			defaults : {
				anchor : "80%",
				labelWidth : 110,
				labelAlign : "right",
				labelPad : 12,		
				listeners : {
					// 对所有子控件增加Enter按键监听事件
					specialkey : function(f, e) {
						if (e.getKey() == e.ENTER) {
							this.formSubmit(f.up("form").getForm());
						}
					},
					scope: this
				}	
			},		
			items : [{
						fieldLabel : this.msg.username,
						id : "username",
						name : "username",
						xtype : "icontextfield",
						iconUrl : "resources/extjs/images/fugue-icons/user.png",
						allowBlank : false,
						blankText : this.getMessage(this.msg.blankText, this.msg.username)
					},{
						fieldLabel : this.msg.password,
						id : "password",
						name : "password",
						xtype : "icontextfield",
						inputType : "password",	
						iconUrl : "resources/extjs/images/icons/key.png",
						allowBlank : false,
						blankText : this.getMessage(this.msg.blankText, this.msg.password)
					}, {
						fieldLabel : this.msg.captcha,
						id : "captcha",
						name : "captcha",
						xtype : "captchafield",
						imageUrl : "captcha/displayCodeImage.img",
						enforceMaxLength : true,
						maxLength : 4,
						allowBlank : false,
						blankText : this.getMessage(this.msg.blankText, this.msg.captcha)
						
					}, {
						fieldStyle : {
							marginLeft : "122px"
						},
						boxLabel : this.msg.rememberMe,
						name : "rememberMe",
						xtype : "checkbox",
						scope : this
					}],	
					
			dockedItems: [{
			    xtype: "toolbar",
			    dock: "bottom",
			    ui: "footer",
			    layout : {//右对齐
			    	pack : "end" 
			    },
			    style : {
			    	background: "#B3D7EE"
			    },
			    items: [{
					text : this.msg.login,			
					width : this.buttonWidth,
					formBind : true,
					handler : function(b) {
						this.formSubmit(b.up("form").getForm());
					},
					scope : this
				},{
					text : this.msg.reset,
					width : this.buttonWidth,
					handler : function(b) {
						b.up("form").getForm().reset();
						Pub.utils.EffectUtils.focus(loginForm.getId(), 0);
					}
				},{
					text : this.msg.forgetPassword,
					width : this.buttonWidth,
					handler : function(b) {
						//TODO 忘记密码回调处理
					},
					scope : this
				}]
			}]
		});
		
		var loginWin = new Ext.Window({
			id : this.moduleId,
			title : this.moduleText,
			width : 450,
			maxHeight : 550,
			modal : false,
			iconCls : this.moduleIcon,
			draggable : false,
			closable : false,
			resizable : false,
			animCollapse : false,
			constrainHeader : true,
			layout : Ext.layout.BorderLayout,
			items : [{
						region : "center",
						height : 150,
						border : false,
						html : "<img src='resources/extjs/images/logo/login-init.png'>"
					}, loginForm],
			listeners : {
				show : function(w, o) {
					this.spot.show(loginWin.getId(), function(){					
						// username field获取焦点
						Pub.utils.EffectUtils.focus(loginForm.getId(), 0);	
					});				
				},
				scope : this
			}

		});
		loginWin.show();
	},

	/**
	 * @module 表单提交
	 * @param {object} form 表单对象
	 * @author caobin
	 */
	formSubmit : function(form) {
		if (form.isValid()) {
			form.submit({
				waitTitle : this.msg.infoMsgTitle,
				waitMsg : this.msg.loginWaitMsg,
				success : function(form, action) {
					Ext.util.Cookies.set(this.cookieUsernameKey, Ext.getCmp("username").getValue());
					Ext.util.Cookies.set(this.cookiePasswordKey, Ext.getCmp("password").getValue());
					this.spot.hide(function(){
						//TODO 转到主页
					}, this);
					this.showMsgBox("info/I0101");
				},
				failure : function(form, action) {
					this.showMsgBox("error/E0107", function(){
						//刷新验证码
						Ext.getCmp("captcha").loadImage();
					}, this, this.getMixErr(action));		
				},
				scope: this
			});
		}
	}

});
