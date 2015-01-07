/**
 * @module 需求评估单进度更新操作
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.demand.crud.DemandUpdate", {
	extend : "App.Module",
	
	formUrl: function(){
		return this.getRequestRes("/biz/cads/myTasks/demand/saveOrUpdate.json");
	},

	
	getPhasesStore: function(){	
		Ext.apply(dictDSConfig, {autoLoad: true});
		Ext.apply(dictDSConfig.proxy.extraParams, {"dictId": 21});
		return Ext.create("Ext.data.Store", dictDSConfig);
	},
	
	init : function(){	
		var id = {
			name: "id",
			xtype: "hidden",
			value: this.params.get("id")
		}
		
		var estimateDev = {
			fieldLabel: this.estimateDev,
			name: "estimateDev",
			flex: 1,
			allowBlank: true,
			xtype: "textfield",
			maxLength: 12,
			value: this.params.get("estimateDev")
		}
		
		var estimateTest = {
			fieldLabel: this.estimateTest,
			name: "estimateTest",
			flex: 1,
			allowBlank: true,
			xtype: "textfield",
			maxLength: 12,
			value: this.params.get("estimateTest")
		}
		
		var startDate = {
			fieldLabel: this.startDate,
			name: "startDate",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "datefield",
			editable: false,
			format: this.format,
			value: this.params.get("startDate") ? new Date(this.params.get("startDate")) : null
		}

		var status = {
			fieldLabel: this.required(this.status),
			name: "status",
			flex: 1,
			allowBlank: false,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyStatus,
			store: this.getPhasesStore(),
			displayField: "name",
			valueField: "id",
			listeners: {
				boxready: {
					fn: function(c){
						c.getStore().on("load", function(store){
							c.setValue(this.params.get("status")-0);
						}, this);
						c.fireEvent("select");
					},
					scope: this
				}
			}
		}
		
		var remark = {
			fieldLabel: this.remark,
			name: "remark",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "textareafield",
			maxLength: 256,
			value: this.params.get("remark")
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
			id: "DemandUpdate.addWin",
		    title: this.moduleText,
		    iconCls: this.moduleIcon,
		    height: 300,
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
				xtype: "xform",
				labelWidth: 100,				
				fieldSetConfig: {
					title: me.DemandBasicInfo,
					collapsible: false
				},
				items:[
					id, estimateDev, estimateTest, startDate, status, remark
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
				waitTitle : this.UpdateDemandTitle,
				waitMsg : Msg.App.updating,
				success : function(form, action) {
					win.close();
					Ext.getCmp("DemandReadMainGridID").getSelectionModel().deselectAll();
					DemandDS.reload();
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