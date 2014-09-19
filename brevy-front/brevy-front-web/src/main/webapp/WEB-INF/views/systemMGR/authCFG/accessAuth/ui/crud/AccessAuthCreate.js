/**
 * @module 访问权限添加操作维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.accessAuth.crud.AccessAuthCreate", {
	extend : "App.Module",
	
	formUrl: function(){
		return this.getRequestRes("/maintenance/accessAuth/saveOrUpdate.json");
	},
	
	init : function(){
		
		var appId = {
			name: "appId",
			xtype: "hidden",
			value: Ext.getCmp("tb.tbar").getValue()
		}
		
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
		
		
		var urlPattern = {
			fieldLabel: this.required(this.urlPattern),
			name: "urlPattern",
			flex: 1,
			newLine: 1,
			allowBlank : false,
			xtype: "textfield",
			vtype: "wildcard",
			maxLength: 512
		}
		
		var authenFilter = {
			fieldLabel: this.required(this.authenFilter),
			name: "authenticationFilter",
			flex: 1,
			newLine: 1,
			allowBlank: false,
			xtype: "combo",
			forceSelection: true,
			editable: false,
			store: authenFilterDS,
    		queryMode: "local",
   			displayField: "text",
    		valueField: "name",
    		plugins: ["clearbutton"],
    		listeners: {
    			select: function(c, r){
    				switch(r[0].get("name")){
    					case "authcCustom":
    						Ext.getCmp(authorFilter.id).setValue("roles");
    						break;
    					default:
    						Ext.getCmp(authorFilter.id).clearValue();
    				}
    			},
    			change: function(c, newValue, oldValue){
    				if(!newValue){
    					Ext.getCmp(authorFilter.id).clearValue();
    				}
    			}
    		}
		}
		
		var authorFilter = {
			id: Ext.id(),
			fieldLabel: this.authenFilter,
			name: "authorizationFilter",
			flex: 1,
			allowBlank: true,
			xtype: "combo",
			forceSelection: true,
			editable: false,
			store: authorFilterDS,
    		queryMode: "local",
   			displayField: "text",
    		valueField: "name",
    		plugins: ["clearbutton"]
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
		
		var sort = {
			fieldLabel: this.required(this.sort),
			name: "sort",
			flex: 1,
			allowBlank : false,
			xtype: "numberfield",
			minValue: 1,
			maxValue: 999999999,
			allowDecimals: false
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
		    height: 240,
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
					appId, name, code, urlPattern, authenFilter,
					authorFilter, status, sort
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
				waitTitle : this.createAccessPermTitle,
				waitMsg : Msg.App.saving,
				success : function(form, action) {
					win.close();
					accessAuthDS.reload();
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