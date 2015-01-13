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
		
		var gdType = {
			fieldLabel: this.gdType,
			name: "gdType",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyStatus,
			store: dictDS_22,
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"]
		}
		
		var prjType = {
			fieldLabel: this.prjType,
			name: "prjType",
			flex: 1,
			allowBlank: true,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyStatus,
			store: dictDS_23,
			displayField: "name",
			valueField: "id",
			plugins: ["clearbutton"]
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
			emptyText: this.emptyExecType,
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
			emptyText: this.emptyType,
			store: dictDS_14,
			displayField: "name",
			valueField: "id"
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
		
		var estimateJob = {
			fieldLabel: this.estimateJob,
			name: "estimateJob",
			flex: 1,
			allowBlank: true,
			xtype: "textfield",
			maxLength: 12
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
		
		
		var pmName = {
			fieldLabel: this.pmName,
			name: "pmNameIds",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "combo",
			multiSelect: true,
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyPmName,
			store: userDS,
			displayField: "chName",
			valueField: "id",
			plugins: ["clearbutton"],
			listeners: {
				change: function(c){
					Ext.getCmp(pmNameText.id).setValue(c.getRawValue());
				}
			}
		}
		
		var pmNameText = {
			id: Ext.id(),
			xtype: "hidden",
			name: "pmName"
		}
		
		var startDate = {
			fieldLabel: this.startDate,
			name: "startDate",
			flex: 1,
			allowBlank: true,
			xtype: "datefield",
			editable: false,
			format: this.format
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
			store: getDictDS_20(),
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
			store: getDictDS_20(),
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
			store: getDictDS_20(),
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
			store: getDictDS_20(),
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
			store: getDictDS_20(),
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
			store: getDictDS_20(),
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
			store: getDictDS_20(),
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
			store: getDictDS_20(),
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
			allowBlank: true,
			xtype: "textfield",
			maxLength: 12
		}
		
		var sitFinishDate = {
			fieldLabel: this.sitFinishDate,
			name: "sitFinishDate",
			flex: 1,
			newLine: 1,
			allowBlank: true,
			xtype: "datefield",
			editable: false,
			format: this.format
		}
		
		var status = {
			fieldLabel: this.status,
			name: "status",
			flex: 1,
			allowBlank: true,
			xtype: "combo",
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyStatus,
			store: dictDS_24,
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
		
		var assignToDepts = {
			id: "GDCreate.assignToDepts",
			fieldLabel: this.required(this.assignToDepts),
			name: "assignToDept",
			flex: 1,
			allowBlank: false,
			xtype: "combo",
			multiSelect: true,
			triggerAction: "all",
			forceSelection: true,
			editable: false,
			emptyText: this.emptyAssignToDeptsCond,
			store: dictDS_2,
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
			id: "GDCreate.createAndAttachBtn",
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
			id: "GDCreate.addWin",
		    title: this.moduleText,
		    iconCls: this.moduleIcon,
		    height: 550,
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
							title: me.GDBasicInfo,
							collapsible: false
						},
						items:[
							name, briefName, gdType, prjType, recvDate, execType, type, priority, 
							requireFinishTime, estimateJob, preCond, preCondText, implTeam, 
							implTeamText, pmName, pmNameText, startDate
						]
					},
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
							ini, rdp, ad, scp, sit, uat, pip, smp
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
							finishDate, usingResource, usingTime, assignToDepts
						]
					}
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
				waitTitle : this.createGDTitle,
				waitMsg : Msg.App.saving,
				success : function(form, action) {
					if(win){
						win.close();
						GDDS.reload();
						Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.saveSuccess, "br");
					}else{
						var b = Ext.getCmp("GDCreate.createAndAttachBtn");
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
		    	id: "GDCreate.Upload",
		    	xtype: "uploadpanel",
		    	border: false,
		    	addFileBtnText : this.addFileBtnText,
			   	uploadBtnText : this.uploadBtnText,
			   	removeBtnText : this.removeBtnText,
			   	cancelBtnText : this.cancelBtnText,
			   	file_size_limit : 30,//MB
			   	upload_url :  this.getRequestRes('/biz/cads/myTasks/gd/fileUpload.json'),
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
							Ext.getCmp("GDCreate.Upload").getStore().each(function(r){
								if(r.get("status") - 0 == -1){
									complete = false;
								}
							});
							if(!complete){
								Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, Msg.Prompt.confirmUpload, null, function(r){
									if(r == "yes"){
										b.up("window").close();
										Ext.getCmp("GDCreate.addWin").close();
										GDDS.reload();
										Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.saveSuccess, "br");
										
									}
								});
							}else{
								b.up("window").close();
								Ext.getCmp("GDCreate.addWin").close();
								GDDS.reload();
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