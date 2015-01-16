/**
 * @module 综合管理任务进度更新操作
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.catask.crud.CataskUpdate", {
	extend : "App.Module",
	
	formUrl: function(){
		return this.getRequestRes("/biz/cads/myTasks/catask/saveOrUpdate.json");
	},
	
	beforeInit: function(){
		this.callParent();
		dictDS_27.load();
		dictDS_28.load();
	},


	init : function(){	
		var id = {
			name: "id",
			xtype: "hidden"
		}

		var operLv = {
			fieldLabel: this.required(this.operLv),
			name: "operLv",
			flex: 1,
			allowBlank: false,
			xtype: "textfield",
			maxLength: 8
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
			queryMode: "local",
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
		
		var updateAndAttachBtn = {
			id: "CataskCreate.updateAndAttachBtn",
			text: this.updateAndAttach,
			width : 130,
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
			id: "CataskUpdate.addWin",
		    title: this.moduleText,
		    iconCls: this.moduleIcon,
		    height: 380,
		    width: 780,
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
						bodyStyle: Pub.Style.grayBody,
						border: false,
						fieldSetConfig: {
							title: me.CataskBasicInfo,
							collapsible: false
						},
						items:[
							id, operLv, jobContent
						]
					},
					{
						xtype: "xform",
						//labelWidth: 50,
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
				buttons: [updateBtn, updateAndAttachBtn, resetBtn]				
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
				//waitTitle : this.UpdateCataskTitle,
				waitMsg : Msg.App.updating,
				success : function(form, action) {
					if(win){
						win.close();
						Ext.getCmp("CataskReadMainGridID").getSelectionModel().deselectAll();
						CataskDS.reload();
						Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");
					}else{
						var b = Ext.getCmp("CataskCreate.updateAndAttachBtn");
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
		    	id: "CataskUpdate.Upload",
		    	xtype: "uploadpanel",
		    	border: false,
		    	addFileBtnText : this.addFileBtnText,
			   	uploadBtnText : this.uploadBtnText,
			   	removeBtnText : this.removeBtnText,
			   	cancelBtnText : this.cancelBtnText,
			   	file_size_limit : 30,//MB
			   	upload_url :  this.getRequestRes('/biz/cads/myTasks/catask/fileUpload.json'),
			   	post_params : {sid: Pub.httpOnlySession, id: rid},//防止非IE内核浏览器session丢失,
			   	file_types: "*.doc;*.docx;*.pdf;*.xls;*.xlsx"
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
							Ext.getCmp("CataskUpdate.Upload").getStore().each(function(r){
								if(r.get("status") - 0 == -1){
									complete = false;
								}
							});
							if(!complete){
								Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, Msg.Prompt.confirmUpload, null, function(r){
									if(r == "yes"){
										b.up("window").close();
										Ext.getCmp("CataskUpdate.addWin").close();
										Ext.getCmp("CataskReadMainGridID").getSelectionModel().deselectAll();
										CataskDS.reload();
										Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");
										
									}
								});
							}else{
								b.up("window").close();
								Ext.getCmp("CataskUpdate.addWin").close();
								Ext.getCmp("CataskReadMainGridID").getSelectionModel().deselectAll();
								CataskDS.reload();
								Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");
							}
						},
						scope : this
					}]
				}
			]
		});
	}
});