/**
 * @module 用户添加操作维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.user.crud.UserCreate", {
	extend : "App.Module",
	
	formUrl: function(){
		return this.getRequestRes("/maintenance/user/saveOrUpdate.json");
	},

	init: function(){
		
		var chName = {
			fieldLabel: this.required(this.chName),
			name: "chName",
			flex: 1,
			allowBlank: false,
			xtype: "textfield",
			maxLength: 32
		}
		
		var gender = {
			fieldLabel: this.required(this.gender),
			name: "gender",
			flex: 1,
			newLine: 1,
			allowBlank: false,
			xtype: "combo",
			forceSelection: true,
			editable: false,
			store: genderDS,
    		queryMode: "local",
   			displayField: "text",
    		valueField: "name",
			value: "M"
		}
		
		var username = {
			fieldLabel: this.required(this.username),
			name: "username",
			newLine: 1,
			flex: 1,
			allowBlank: false,
			xtype: "textfield",
			maxLength: 32
		}
		
		
		var password = {
			fieldLabel: this.required(this.password),
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
		
		var confirmPassword = {
			fieldLabel: this.required(this.confirmPassword),
			newLine: 1,
			flex: 1,
			allowBlank: false,	
			xtype: "textfield",
			inputType: "password",
			maxLength: 32,
			vtype: "password",
			initialPassField : "password"
		}
	
		
		var status = {
			fieldLabel: this.required(this.status),
			name: "status",
			flex: 1,
			newLine: 1,
			allowBlank: false,
			xtype: "combo",
			forceSelection: true,
			editable: false,
			store: statusDS,
    		queryMode: "local",
   			displayField: "text",
    		valueField: "name",
			value: "1"
		}
		
		var position = {
			fieldLabel: this.required(this.position),
			name: "positionId",
			flex: 1,
			newLine: 1,
			allowBlank: false,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyPositionName,
			allowBlank : false,
			store: userDictPositionDS,
			displayField: "name",
			valueField: "id"
		}
		
		var dept = {
			fieldLabel: this.required(this.dept),
			name: "deptId",
			flex: 1,
			newLine: 1,
			allowBlank: false,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyDeptName,
			allowBlank : false,
			store: userDictDeptDS,
			displayField: "name",
			valueField: "id"
		}
		
		var createBtn = {
			text: Msg.App.add,
			width : 100,
			style : {marginRight: "30px;", marginBottom: "10px;"},
			formBind : true,
			handler : function(b) {
				this.formSubmit(b.up("form").getForm(), b.up("window"));
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
		
		return Ext.create("Ext.window.Window", {
		    title: this.moduleText,
		    iconCls: this.moduleIcon,
		    height: 360,
		    width: 480,
		    layout: "fit",
		    modal: true,
		    resizable: false,
		    items: { 
		    	url: this.formUrl(),
				method: "POST",
				jsonSubmit: true,
				trackResetOnLoad: true,
				flex: 1,
				border: false,
				width: "100%",
				frame: false,
				bodyStyle: Pub.Style.grayBody,
				xtype: "xform",
				labelWidth: 100,
				fieldSetConfig: {
					title: this.userBasicInfo,
					collapsible: false
				},
				items:[
					chName, gender, username, password, confirmPassword, position, dept, status
				],
				buttonAlign: "center",
				buttons: [createBtn, resetBtn]
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
				waitTitle : this.createUserTitle,
				waitMsg : Msg.App.saving,
				success : function(form, action) {
					win.close();
					userDS.reload();
					Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.saveSuccess, "br");
				},
				failure : function(form, action) {				
					Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR, action.result.RSP_HEAD.ERROR_MESSAGE);
				},
				scope: this
			});
		}
	}
});