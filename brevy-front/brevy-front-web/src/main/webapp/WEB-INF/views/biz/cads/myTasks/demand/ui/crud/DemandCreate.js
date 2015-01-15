/**
 * @module 需求评估单添加操作
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.demand.crud.DemandCreate", {
	extend : "App.Module",
	
	formUrl: function(){
		return this.getRequestRes("/biz/cads/myTasks/demand/saveOrUpdate.json");
	},
	
	beforeInit: function(){
		this.callParent();
		dictDS_2.load();
	},
	
	init : function(){
		
		var prjName = {
			fieldLabel: this.required(this.prjName),
			name: "prjName",
			flex: 1,
			allowBlank: false,
			xtype: "textfield",
			maxLength: 80
		}
		
		var recvDate = {
			fieldLabel: this.required(this.recvDate),
			name: "recvDate",
			flex: 1,
			newLine: 1,
			allowBlank: false,
			xtype: "datefield",
			editable: false,
			format: this.format
		}
		
	
		var priority = {
			fieldLabel: this.required(this.priority),
			name: "priority",
			flex: 1,
			allowBlank: false,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyPriority,
			store: dictDS_15,
			displayField: "name",
			valueField: "id"
		}
		
		var requireFinishTime = {
			fieldLabel: this.requireFinishTime,
			name: "requireFinishTime",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "datefield",
			editable: false,
			format: this.format
		}
		
		var estimateDev = {
			fieldLabel: this.estimateDev,
			name: "estimateDev",
			flex: 1,
			allowBlank: true,
			xtype: "textfield",
			maxLength: 12
		}
		
		var estimateTest = {
			fieldLabel: this.estimateTest,
			name: "estimateTest",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "textfield",
			maxLength: 12
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
		
		var preCond = {
			fieldLabel: this.preCond,
			name: "preCondIds",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "combo",
			multiSelect: true,
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyPreCond,
			store: dictDS_16,
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"],
			listeners: {
				change: function(c){
					Ext.getCmp(preCondText.id).setValue(c.getRawValue());
				}
			}
		}
		
		var preCondText = {
			id: Ext.id(),
			xtype: "hidden",
			name: "preCond"
		}
		
		var implTeam = {
			fieldLabel: this.required(this.implTeam),
			name: "implTeamIds",
			flex: 1,
			allowBlank: false,
			xtype: "combo",
			multiSelect: true,
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyImplTeam,
			store: dictDS_2,
			queryMode: "local",
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"],
			listeners: {
				change: function(c){
					Ext.getCmp(implTeamText.id).setValue(c.getRawValue());
				}
			}
			
		}
		
		var implTeamText = {
			id: Ext.id(),
			xtype: "hidden",
			name: "implTeam"
		}
		
		var startDate = {
			fieldLabel: this.startDate,
			name: "startDate",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "datefield",
			editable: false,
			format: this.format
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
			store: dictDS_21,
			displayField: "name",
			valueField: "id"
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
		
		var assignToDepts = {
			id: "DemandCreate.assignToDepts",
			fieldLabel: this.required(this.assignToDepts),
			name: "assignToDept",
			flex: 1,
			newLine:1,
			allowBlank: false,
			xtype: "combo",
			multiSelect: true,
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyAssignToDeptsCond,
			store: dictDS_2,
			queryMode: "local",
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
		
		var createAndAttachBtn = {
			id: "DemandCreate.createAndAttachBtn",
			text: Msg.App.addAndAttach,
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
		
		var me = this;	
		return Ext.create("Ext.window.Window", {
			id: "DemandCreate.addWin",
		    title: this.moduleText,
		    iconCls: this.moduleIcon,
		    height: 450,
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
					prjName, recvDate, priority, requireFinishTime,
					estimateDev, estimateTest, devFinishDate, sitWorkload, sitFinishDate, 
					preCond, preCondText, implTeam, implTeamText, 
					startDate, status, assignToDepts, remark
				],
				buttonAlign: "center",
				buttons: [createBtn, createAndAttachBtn, resetBtn]
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
				//waitTitle : this.createDemandTitle,
				waitMsg : Msg.App.saving,
				success : function(form, action) {
					if(win){
						win.close();
						DemandDS.reload();
						Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.saveSuccess, "br");
					}else{
						var b = Ext.getCmp("DemandCreate.createAndAttachBtn");
						me.createUploadPanelWin(b, action.result.RSP_BODY.id).show(b);
					}		
				},
				failure : function(form, action) {				
					Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR, action.result.RSP_HEAD.ERROR_MESSAGE);
				},
				scope: this
			});
		}
	},
	
	//创建上传窗体
	createUploadPanelWin : function(formBtn, rid){
		return Ext.create("Ext.window.Window", {
		    title: this.addAttachments,
		    height: 420,
		    width: 760,
		    resizable: false,
		    iconCls: Ext.ux.Icons.page_attach,
		  	layout: "fit",
		  	closable : false,
		    modal: true,
		    items: {  
		    	id: "DemandCreate.Upload",
		    	xtype: "uploadpanel",
		    	border: false,
		    	addFileBtnText : this.addFileBtnText,
			   	uploadBtnText : this.uploadBtnText,
			   	removeBtnText : this.removeBtnText,
			   	cancelBtnText : this.cancelBtnText,
			   	file_size_limit : 30,//MB
			   	upload_url :  this.getRequestRes('/biz/cads/myTasks/demand/fileUpload.json'),
			   	post_params : {sid: Pub.httpOnlySession, id: rid},//防止非IE内核浏览器session丢失,
			   	file_types: "*.doc;*.docx;*.pdf;*.zip;*.rar"
		    },
		    dockedItems : [
				{
				    xtype: "toolbar",
				    dock: "bottom",
				    ui: "footer",
				    height: 30,
				    layout : {
				    	pack : "center" 
				    },
				    items: [{
						text : this.confirmFinish,			
						width : 100,
						handler : function(b) {
							var complete = true;
							Ext.getCmp("DemandCreate.Upload").getStore().each(function(r){
								if(r.get("status") - 0 == -1){
									complete = false;
								}
							});
							if(!complete){
								Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, Msg.Prompt.confirmUpload, null, function(r){
									if(r == "yes"){
										b.up("window").close();
										Ext.getCmp("DemandCreate.addWin").close();
										DemandDS.reload();
										Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.saveSuccess, "br");	
									}
								});
							}else{
								b.up("window").close();
								Ext.getCmp("DemandCreate.addWin").close();
								DemandDS.reload();
								Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.saveSuccess, "br");
							}
							
							
							
						},
						scope : this
					}]
				}
			]
		});
	}
});