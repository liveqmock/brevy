/**
 * @module 应用系统添加操作维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.app.crud.AppCreate", {
	extend : "App.Module",
	
	formUrl: function(){
		return this.getRequestRes("/maintenance/app/saveOrUpdate.json");
	},
	
	init : function(){
		
		var name = {
			fieldLabel: this.required(this.name),
			name: "name",
			flex: 1,
			allowBlank: false,
			xtype: "textfield",
			maxLength: 32
		}
		
		
		var code = {
			fieldLabel: this.required(this.code),
			name: "code",
			flex: 1,
			allowBlank: false,
			xtype: "textfield",
			vtype: "alphanum",
			maxLength: 32
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
		
		var desc = {
			fieldLabel: this.desc,
			name: "desc",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "textareafield",
			maxLength: 256
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
		    height: 270,
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
					title: this.accessPermBasicInfo,
					collapsible: false
				},
				items:[
					name, code, status, desc
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
				waitTitle : this.createAppTitle,
				waitMsg : Msg.App.saving,
				success : function(form, action) {
					win.close();
					appDS.reload();
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