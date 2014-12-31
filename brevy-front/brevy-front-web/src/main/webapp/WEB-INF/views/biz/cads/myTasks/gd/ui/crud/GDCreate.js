/**
 * @module 工单添加操作
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.gd.crud.GDCreate", {
	extend : "App.Module",
	
	formUrl: function(){
		return this.getRequestRes("/biz/cads/myTasks/gd/saveOrUpdate.json");
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
		
		var briefName = {
			fieldLabel: this.required(this.briefName),
			name: "briefName",
			flex: 2,
			newLine: 1,
			allowBlank: false,
			xtype: "textfield",
			maxLength: 32
		}
		
		
		var recvDate = {
			fieldLabel: this.required(this.recvDate),
			name: "recvDate",
			flex: 1,
			allowBlank: false,
			xtype: "datefield",
			editable: false,
			format: this.format
		}
		
		var execType = {
			fieldLabel: this.execType,
			name: "execType",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyExecTypeName,
			store: dictDS_13,
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"]
		}
		
		
		var type = {
			fieldLabel: this.required(this.type),
			name: "type",
			flex: 1,
			allowBlank: false,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyTypeName,
			store: dictDS_14,
			displayField: "name",
			valueField: "id"
		}
		
		var priority = {
			fieldLabel: this.required(this.priority),
			name: "type",
			flex: 1,
			allowBlank: false,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyPriorityName,
			store: dictDS_15,
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
		    height: 550,
		    width: 860,
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
					title: this.GDBasicInfo,
					collapsible: false
				},
				items:[
					name, briefName, recvDate, execType, type, priority
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
				waitTitle : this.createGDTitle,
				waitMsg : Msg.App.saving,
				success : function(form, action) {
					win.close();
					GDDS.reload();
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