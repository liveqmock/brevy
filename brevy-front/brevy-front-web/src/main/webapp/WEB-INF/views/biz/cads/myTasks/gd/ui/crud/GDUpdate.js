/**
 * @module 工单进度更新操作
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.gd.crud.GDUpdate", {
	extend : "App.Module",
	
	formUrl: function(){
		return this.getRequestRes("/biz/cads/myTasks/gd/saveOrUpdate.json");
	},
	
	beforeInit: function(){
		this.callParent();
		dictDS_20.load();
		dictDS_24.load();
	},


	init : function(){	
		var id = {
			name: "id",
			xtype: "hidden"
		}

		var ini = {
			fieldLabel: this.ini,
			name: "ini",
			flex: 1,
			allowBlank: true,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyPhase,
			store: dictDS_20,
			queryMode: "local",
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"]
		}
		
		var rdp = {
			fieldLabel: this.rdp,
			name: "rdp",
			flex: 1,
			allowBlank: true,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyPhase,
			store: dictDS_20,
			queryMode: "local",
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"]
		}
		
		var ad = {
			fieldLabel: this.ad,
			name: "ad",
			flex: 1,
			allowBlank: true,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyPhase,
			store: dictDS_20,
			queryMode: "local",
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"]
		}
		
		var scp = {
			fieldLabel: this.scp,
			name: "scp",
			flex: 1,
			allowBlank: true,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyPhase,
			store: dictDS_20,
			queryMode: "local",
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"]
		}
		
		var sit = {
			fieldLabel: this.sit,
			name: "sit",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyPhase,
			store: dictDS_20,
			queryMode: "local",
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"]
		}
		
		var uat = {
			fieldLabel: this.uat,
			name: "uat",
			flex: 1,
			allowBlank: true,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyPhase,
			store: dictDS_20,
			queryMode: "local",
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"]
		}
		
		var pip = {
			fieldLabel: this.pip,
			name: "pip",
			flex: 1,
			allowBlank: true,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyPhase,
			store: dictDS_20,
			queryMode: "local",
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"]
		}
		
		
		var smp = {
			fieldLabel: this.smp,
			name: "smp",
			flex: 1,
			allowBlank: true,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyPhase,
			store: dictDS_20,
			queryMode: "local",
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"]
		}
		
		var devFinishDate = {
			fieldLabel: this.devFinishDate,
			name: "devFinishDate",
			flex: 1,
			allowBlank: true,
			xtype: "datefield",
			editable: false,
			format: this.format
		}
		
		var sitWorkload = {
			fieldLabel: this.sitWorkload,
			name: "sitWorkload",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "textfield",
			maxLength: 12
		}
		
		var sitFinishDate = {
			fieldLabel: this.sitFinishDate,
			name: "sitFinishDate",
			flex: 1,
			allowBlank: true,
			xtype: "datefield",
			editable: false,
			format: this.format
		}
		
		var status = {
			fieldLabel: this.status,
			name: "status",
			flex: 1,		
			newLine: 1,
			allowBlank: true,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyStatus,
			store: dictDS_24,
			queryMode: "local",
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"]
		}
		
		var progress = {
			fieldLabel: this.progress,
			fieldStyle: Pub.Style.fieldIcon(this.fieldFugueIconRES + "edit-percent.png", "right"),
			name: "progress",
			flex: 1,
			allowBlank: true,
			xtype: "numberfield",
			hideTrigger: true,
			value: 0,
	        maxValue: 100,
	        minValue: 0
		}
		
		var finishDate = {
			fieldLabel: this.finishDate,
			name: "finishDate",
			flex: 1,
			allowBlank: true,
			xtype: "datefield",
			editable: false,
			format: this.format
		}
		
		var usingResource = {
			fieldLabel: this.usingResource,
			name: "usingResource",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "textfield",
			maxLength: 12
		}
		
		var usingTime = {
			fieldLabel: this.usingTime,
			name: "usingTime",
			flex: 1,
			allowBlank: true,
			xtype: "textfield",
			maxLength: 12
		}
		
		var remark = {
			fieldLabel: this.remark,
			name: "remark",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "textareafield",
			maxLength: 256
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

		
		var me = this;	
		return Ext.create("Ext.window.Window", {
			id: "GDUpdate.addWin",
		    title: this.moduleText,
		    iconCls: this.moduleIcon,
		    height: 460,
		    width: 830,
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
				xtype: "form",
				labelWidth: 100,
				listeners:{
			    	afterrender: function(f){		    		
			    		f.getForm().loadRecord(me.params)
			    	}
			    },
				items: [
					{
						xtype: "xform",
						labelWidth: 50,
						bodyStyle: Pub.Style.grayBody,
						border: false,
						fieldSetConfig: {
							title: me.phases,
							collapsible: false
						},
						items:[
							id, ini, rdp, ad, scp, sit, uat, pip, smp
						]
					},
					{
						xtype: "xform",
						//labelWidth: 50,
						bodyStyle: Pub.Style.grayBody,
						border: false,
						fieldSetConfig: {
							title: me.resources,
							collapsible: false
						},
						items:[
							progress, devFinishDate, sitWorkload, sitFinishDate, status,
							finishDate, usingResource, usingTime, remark
						]
					}
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
		var me = this;
		if (form.isValid()) {
			form.submit({ 
				waitTitle : this.UpdateGDTitle,
				waitMsg : Msg.App.updating,
				success : function(form, action) {
					win.close();
					Ext.getCmp("GDReadMainGridID").getSelectionModel().deselectAll();
					GDDS.reload();
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