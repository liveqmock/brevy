/**
 * @module 用户更新操作维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.user.crud.UserUpdate", {
	extend : "App.Module",
	
	formUrl: function(){
		return this.getRequestRes("/maintenance/user/saveOrUpdate.json");
	},
	
	initStore: function(){
		userDictPositionDS.load();
		userDictPositionDS.on("load", function(){
			Ext.getCmp("user.positionId").setValue(this.params.get("positionId"));
		}, this);
		userDictDeptDS.load();
		userDictDeptDS.on("load", function(){
			Ext.getCmp("user.deptId").setValue(this.params.get("deptId"));
		}, this);
	},

	init: function(){
	
		this.initStore();
		
		var id = {
			name: "id",
			xtype: "hidden",
			value: this.params.get("id")
		}
		
		var chName = {
			fieldLabel: this.required(this.chName),
			name: "chName",
			flex: 1,
			allowBlank: false,
			xtype: "textfield",
			maxLength: 32,
			value: this.params.get("chName")
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
			value: this.params.get("gender")
		}
		
		var username = {
			fieldLabel: this.required(this.username),
			name: "username",
			newLine: 1,
			flex: 1,
			allowBlank: false,
			xtype: "textfield",
			maxLength: 32,
			value: this.params.get("username")
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
			maxLength: 32,
			value: this.params.get("password")/*,
			listeners : {
				afterrender : function(p){
					
				}
			}*/
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
			initialPassField : "password",
			value: this.params.get("password")
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
			value: this.params.get("status")
		}
		
		var position = {
			fieldLabel: this.required(this.position),
			id: "user.positionId",
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
			id: "user.deptId",
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
		
		var updateBtn = {
			text: Msg.App.update,
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
				Ext.getCmp("user.positionId").setValue(this.params.get("positionId"));
				Ext.getCmp("user.deptId").setValue(this.params.get("deptId"));
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
					id, chName, gender, username, password, confirmPassword, position, dept, status
				],
				buttonAlign: "center",
				buttons: [updateBtn, resetBtn]
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