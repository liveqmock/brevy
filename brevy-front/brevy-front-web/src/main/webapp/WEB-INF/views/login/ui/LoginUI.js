/**
 * @module 登录模块UI
 * @author caobin
 */
Ext.define("App.login.LoginUI", {
	
	extend : "App.Module",
		
	//登录表单Url
	formUrl : "auth/doLogin.json",
	//验证码图片Url
	captchaUrl : "login/captcha.image",
	
	buttonWidth : 80,
	
	fieldIconRES : "resources/extjs/images/icons/",

	
	//初始化登录UI
	init : function() {
		this.loginWin = Ext.create("Ext.window.Window", {
			title : this.loginTitle,
			width : 450,
			height : 345,
			maxHeight : 550,
			modal : false,
			iconCls : this.moduleIcon,
			draggable : false,
			closable : false,
			resizable : false,
			animCollapse : false,
			constrainHeader : true,
			layout : "border",
			items : [{
						region : "center",
						//height : 150,
						border : false,
						html : "<img width='100%' src='resources/extjs/images/background/login.png'>"
					}, this.createLoginForm()],
			listeners : {
				show : function() {
					Pub.Utils.focus(this.items.getAt(1).getForm(), 1);
				}
			}

		});
	},

	/**
	 * @module 表单提交
	 * @param {object} form 表单对象(Ext.form.Basic)
	 * @param {object} win window对象
	 * @author caobin
	 */
	formSubmit : function(form, win) {
		if (form.isValid()) {			
			form.submit({
				waitTitle : this.loginTitle,
				waitMsg : this.loginWaitMsg,
				success : function(form, action) {
					win.close();
					var appid = action.response.request.options.jsonData.REQ_BODY.appid;
					Pub.ResLoader.loadModule(ExtRES + "entry/module.xml", null, {appid: appid});	
				},
				failure : function(form, action) {
					
					Pub.MsgBox.showMsgBox(
						Pub.MsgBox.ERROR, action.result.RSP_HEAD.ERROR_MESSAGE,
						null, function(){
							//刷新验证码
							Ext.getCmp("_captcha").loadImage();
						}
					);
				},
				scope: this
			});
		}
	},
	
	
	/**
	 * @description  创建登录表单
	 */
	createLoginForm : function(){
		return Ext.create("Ext.form.Panel", {
			border : false,
			region : "south",
			height : 145,
			url : this.formUrl,
			method : "POST",
			jsonSubmit : true,
			defaults : {
				anchor : "80%",
				//关闭自适应提示错误位置
				autoFitErrors: false,
				labelWidth : 110,
				labelAlign : "right",
				labelPad : 12,		
				listeners : {
					// 对所有子控件增加Enter按键监听事件
					specialkey : function(f, e) {
						if (e.getKey() == e.ENTER) {
							this.formSubmit(f.up("form").getForm(), f.up("window"));
						}
					},
					scope: this
				}	
			},		
			items : [
				{
					fieldLabel: this.appname,
					id : "login.appSelect",
					name : "appid",
					xtype : "combo",
					triggerAction: "all",
					forceSelection: true,
					editable: false,
					emptyText: this.emptyAppname,
					allowBlank : false,
				    store: this.getAppStore(),
				    displayField: "name",
				    valueField: "id",
				    blankText : this.blankAppname,
				    fieldStyle: Pub.Style.fieldIcon(this.fieldIconRES + "computer.png", "left")
				},
				{
					fieldLabel : this.username,
					name : "username",
					xtype : "textfield",
					//emptyText : this.emptyUsername,
					allowBlank : false,
					blankText : this.blankUsername,
					fieldStyle: Pub.Style.fieldIcon(this.fieldIconRES + "user.png", "left")
				},{
					fieldLabel : this.password,
					name : "password",
					xtype : "uxpasswordfield",
					minLength : 6,
					showVirtualKeyboard : true,
					enableCapsLockWarning : true,
					//emptyText : this.emptyUsername,
					allowBlank : false,
					//blankText : this.blankUsername,
					fieldStyle: Pub.Style.fieldIcon(this.fieldIconRES + "key.png", "left")
				}, {
					fieldLabel : this.captcha,
					id : "_captcha",
					name : "captcha",
					xtype : "captchafield",
					//emptyText : this.emptyCaptcha,
					imageUrl : this.captchaUrl,
					enforceMaxLength : true,
					maxLength : 5,
					allowBlank : false,
					blankText : this.blankCaptcha,
					fieldStyle: Pub.Style.fieldIcon(this.fieldIconRES + "images.png", "left")
				}
			],			
			dockedItems : [
				{
				    xtype: "toolbar",
				    dock: "bottom",
				    ui: "footer",
				    height: 30,
				    layout : {//右对齐
				    	pack : "end" 
				    },
				    items: [{
						text : this.login,			
						width : this.buttonWidth,
						formBind : true,
						handler : function(b) {
							this.formSubmit(b.up("form").getForm(), b.up("window"));
						},
						scope : this
					},{
						text : this.reset,
						width : this.buttonWidth,
						handler : function(b) {
							b.up("form").getForm().reset();
							Pub.Utils.focus(b.up("form").getForm(), 1);
						}
					}]
				}
			]
		});
	},
	
	afterInit : function(){
		this.loginWin.show();
	},
	
	getAppStore : function(){
		var store = App.login.LoginStore.getAppStore(true);
		store.on("load", function(){
			Ext.getCmp("login.appSelect").setValue(1000);
		}, this);
		return store;
	}
	
});