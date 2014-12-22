/**
 * @module 用户组编辑操作维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.userGroup.crud.UserGroupUpdate", {
	extend : "App.Module",
	
	formUrl: function(){
		return this.getRequestRes("/maintenance/userGroup/saveOrUpdate.json");
	},
	
	init : function(){
		
		var id = {
			name: "id",
			xtype: "hidden",
			value: this.params.get("id")
		}
		
		var appId = {
			name: "appId",
			xtype: "hidden",
			value: this.params.get("appId")
		}
		
		var name = {
			fieldLabel: this.required(this.name),
			name: "name",
			flex: 1,
			allowBlank: false,
			xtype: "textfield",
			maxLength: 32,
			value: this.params.get("name")
		}
		
		
		var code = {
			fieldLabel: this.required(this.code),
			name: "code",
			flex: 1,
			allowBlank: false,
			xtype: "textfield",
			vtype: "alphanum",
			maxLength: 32,
			value: this.params.get("code")
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
		
		var desc = {
			fieldLabel: this.desc,
			name: "desc",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "textareafield",
			maxLength: 256,
			value: this.params.get("desc")
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
			},
			scope : this
		}
		
		return Ext.create("Ext.window.Window", {
		    title: this.moduleText,
		    iconCls: this.moduleIcon,
		    height: 280,
		    width: 660,
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
					title: this.userGroupBasicInfo,
					collapsible: false
				},
				items:[
					id, appId, name, code, status, desc
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
				waitTitle : this.editUserGroupTitle,
				waitMsg : Msg.App.updating,
				success : function(form, action) {
					win.close();
					Ext.getCmp("UserGroupReadMainGridID").getSelectionModel().deselectAll();
					userGroupDS.reload();
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