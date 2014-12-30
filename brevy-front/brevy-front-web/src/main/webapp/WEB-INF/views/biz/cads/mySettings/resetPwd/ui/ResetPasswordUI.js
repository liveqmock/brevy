/**
 * @module 密码重置
 * @author caobin
 */
Ext.define("App.biz.cads.mySettings.resetPwd.ResetPasswordUI", {
	extend : "App.Module",

	formUrl: function(){
		return this.getRequestRes("/biz/cads/mySettings/resetPwd/updatePassword.json");
	},
	
	init : function(){

		
		var oldPassword = {
			fieldLabel: this.required(this.oldPassword),
			name: "oldPassword",
			newLine: 1,
			flex: 1,
			allowBlank: false,	
			xtype: "textfield",
			inputType: "password",
			maxLength: 32
		}
		
		var newPassword = {
			fieldLabel: this.required(this.newPassword),
			id: "password",
			name: "password",
			newLine: 1,
			flex: 1,
			allowBlank: false,
			xtype: "ux.passwordmeterfield",
			vtype: "alphanum",
			minLength : 6,
			maxLength: 32
		}
		
		var confirmNewPassword = {
			fieldLabel: this.required(this.confirmNewPassword),
			newLine: 1,
			flex: 1,
			allowBlank: false,	
			xtype: "textfield",
			inputType: "password",
			maxLength: 32,
			vtype: "password",
			initialPassField : "password"
		}
	
		
		var updateBtn = {
			text: Msg.App.update,
			width : 100,
			style : {marginRight: "30px;", marginBottom: "10px;"},
			formBind : true,
			handler : function(b) {
				this.formSubmit(b.up("form").getForm());
			},
			scope: this
		};
						
		var resetBtn = {
			text: Msg.App.reset,	
			width : 100,
			handler : function(b) {
				b.up("form").getForm().reset();
			},
			scope : this
		}
		
		
		return {
			layout : "fit",
			xtype: "xform",
			bodyStyle: {
				backgroundColor: "#f1f1f1",
				padding: "50px"
			},
			title: this.moduleText,
			iconCls: this.moduleIcon,
			url: this.formUrl(),
			method: "POST",
			jsonSubmit: true,
			trackResetOnLoad: true,
			flex: 1,
			border: true,
			frame: false,
			labelWidth: 130,
			fieldSetConfig: {
				title: this.resetPasswordBasicInfo,
				collapsible: false,
				minHeight: 200,
				minWidth: 420
			},
			items:[
				oldPassword, newPassword, confirmNewPassword
			],
			buttonAlign: "center",
			buttons: [updateBtn, resetBtn]
		};	
	},
	
	/**
	 * @module 表单提交
	 * @param {object} form 表单对象(Ext.form.Basic)
	 * @param {object} win window对象
	 * @author caobin
	 */
	formSubmit : function(form) {
		if (form.isValid()) {
			form.submit({ 
				waitTitle : this.resetPasswordTitle,
				waitMsg : Msg.App.updating,
				success : function(form, action) {
					form.reset();
					Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");
				},
				failure : function(form, action) {				
					Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR, action.result.RSP_HEAD.ERROR_MESSAGE);
				},
				scope: this
			});
		}
	}
});