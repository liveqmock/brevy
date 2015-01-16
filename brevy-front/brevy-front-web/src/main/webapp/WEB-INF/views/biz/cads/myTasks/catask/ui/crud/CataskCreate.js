/**
 * @module 综合管理任务添加操作
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.catask.crud.CataskCreate", {
	extend : "App.Module",
	
	formUrl: function(){
		return this.getRequestRes("/biz/cads/myTasks/catask/saveOrUpdate.json");
	},

	beforeInit: function(){
		this.callParent();
	},
	
	init : function(){
		
		var operLv = {
			fieldLabel: this.required(this.operLv),
			name: "operLv",
			flex: 1,
			allowBlank: false,
			xtype: "textfield",
			maxLength: 8
		}
		
		var importance = {
			fieldLabel: this.required(this.importance),
			name: "importance",
			flex: 1,
			newLine: 1,
			allowBlank: false,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyImportance,
			store: dictDS_25,
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"]
		}
		
		var category = {
			fieldLabel: this.required(this.category),
			name: "category",
			flex: 1,
			allowBlank: false,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyCategory,
			store: dictDS_26,
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"]
		}
		
		
		var source = {
			fieldLabel: this.required(this.source),
			name: "source",
			flex: 1,
			newLine: 1,
			allowBlank: false,
			xtype: "textfield",
			maxLength: 40
		}
		
		var title = {
			fieldLabel: this.required(this.title),
			name: "title",
			flex: 1,
			allowBlank: false,
			xtype: "textfield",
			maxLength: 40
		}
		
		var reqFinishDate = {
			fieldLabel: this.required(this.reqFinishDate),
			name: "reqFinishDate",
			flex: 1,
			allowBlank: false,
			xtype: "datefield",
			editable: false,
			format: this.format
		}
		
		
		var jobContent = {
			fieldLabel: this.jobContent,
			name: "jobContent",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "textfield",
			maxLength: 170
		}
		
		var userId = {
			fieldLabel: this.required(this.userId),
			name: "userId",
			flex: 1,
			newLine: 1,
			allowBlank: false,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyPmName,
			store: userDS,
			displayField: "chName",
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
		
		var finishStatus = {
			fieldLabel: this.finishStatus,
			name: "finishStatus",
			flex: 1,			
			newLine: 1,
			allowBlank: true,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyFinishStatus,
			store: dictDS_27,
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"]
		}
		
		
		var usingResource = {
			fieldLabel: this.usingResource,
			name: "usingResource",
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
			maxLength: 170
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
		
		var me = this;	
		return Ext.create("Ext.window.Window", {
			id: "CataskCreate.addWin",
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
				items: [
					{
						xtype: "xform",
						bodyStyle: Pub.Style.grayBody,
						border: false,
						fieldSetConfig: {
							title: me.CataskBasicInfo,
							collapsible: false
						},
						items:[
							title, operLv, importance, category, source, reqFinishDate,	jobContent, userId							
						]
					},
					{
						xtype: "xform",
						bodyStyle: Pub.Style.grayBody,
						border: false,
						fieldSetConfig: {
							title: me.CataskResourceInfo,
							collapsible: false
						},
						items:[
							progress, finishDate, finishStatus, 
							usingResource, remark	
						]
					}
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
		var me = this;
		if (form.isValid()) {
			form.submit({ 
				//waitTitle : this.createCataskTitle,
				waitMsg : Msg.App.saving,
				success : function(form, action) {
					win.close();
					CataskDS.reload();
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