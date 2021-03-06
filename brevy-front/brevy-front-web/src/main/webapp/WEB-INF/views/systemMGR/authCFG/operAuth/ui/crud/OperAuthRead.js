/**
 * @module 操作权限操作维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.operAuth.crud.OperAuthRead", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "OperAuthReadMainGridID",
		    title: this.moduleText,
			iconCls: this.moduleIcon,
			store: operAuthDS,
			tbar: this.createToolbar(),
			viewConfig: {
		        stripeRows: true,
		        forceFit: true
		    },
		    columns: this.createColumns(),
		    selModel: this.createSelectionModel(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: operAuthDS,
		        dock: "bottom",
		        displayInfo: true,
		        plugins : [Ext.create("Ext.ux.PagingToolbarResizer", {options : this.pageSizeOptions, width: 70})]
		    }]
		});
	},
	
	//创建工具栏
	createToolbar : function(){
		var me = this;
		return [
			{
				id: "tb.tbar",
			  	fieldLabel: this.currentAppname,
			  	labelWidth: Ext.isChrome ? 80 : 70,
				name: "appid",
				xtype: "combo",
				triggerAction: "all",
				forceSelection: true,
				editable: false,
				queryMode: "local",
				store: this.getTbarStore(),
				displayField: "name",
				valueField: "id",
				listeners: {
					change: {
						fn: function(o, newVal, oldVal){
							operAuthDS.getProxy().setExtraParam("appId", newVal)
							operAuthDS.load();						
						},
						scope: this		
					}
				}
			},
			" ", "-",
			{text: Msg.App.add, iconCls: Ext.ux.Icons.lock_add, handler: function(btn){
				Pub.ResLoader.jsPack(me.getExtRes("systemMGR/authCFG/operAuth/ui/crud/OperAuthCreate.js"), function(){
					me.createInstance("App.systemMGR.authCFG.operAuth.crud.OperAuthCreate", Msg.App.add, btn.iconCls).show(btn);
				});
			}},
			"-",
			{text: Msg.App.edit, iconCls: Ext.ux.Icons.lock_edit, handler:function(btn){
				me.handleSelectedRecord("OperAuthReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("systemMGR/authCFG/operAuth/ui/crud/OperAuthUpdate.js"), function(){
						me.createInstance("App.systemMGR.authCFG.operAuth.crud.OperAuthUpdate", Msg.App.edit, btn.iconCls, p.sm[0]).show(btn);
					});
				});
			}},
			"-",
			{text: Msg.App.del, iconCls: Ext.ux.Icons.lock_delete, handler:function(btn){
				me.handleSelectedRecord("OperAuthReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("systemMGR/authCFG/operAuth/ui/crud/OperAuthDelete.js"), function(){
						Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, Msg.Prompt.confirmDelRec, null, function(r){
							if(r == "yes"){
								me.createInstance("App.systemMGR.authCFG.operAuth.crud.OperAuthDelete", Msg.App.del, btn.iconCls, p.sm);
							}	
						});		
					});
				}, null, null, true);
			}},
			"->",
			{
				fieldLabel: this.keywordSearch,
				labelWidth: Ext.isChrome ? 70 : 60,
				xtype: "searchfield",
				width: 260,
				store: operAuthDS
			}
		];
	},
		
	
	//获取tbar的store
	getTbarStore : function(){
		var store = App.login.LoginStore.getAppStore(true);
		store.on("load", function(){
			Ext.getCmp("tb.tbar").setValue(this.params.appId - 0);
		}, this);
		return store;
	},
	
	//创建列
	createColumns : function(){
		var me = this;
		return [
		 	{text: this.name, dataIndex: "name", flex: 3},  
	    	{text: this.code, dataIndex: "code", flex: 3},
	    	{text: this.authorizedOper, dataIndex: "authorizedOper", flex: 5},
	    	{text: this.status, dataIndex: "status", flex: 1,  renderer: function(value) {
	            return value == 0 ? me.invalid : me.valid;
	        }},
	    	{text: this.sort, dataIndex: "sort", flex: 1}
		];
	},
	
	//创建选择框
	createSelectionModel : function(){
		return Ext.create("Ext.selection.CheckboxModel",{
			//allowDeselect: true,
			mode: "MULTI"
		});
	}
	
});