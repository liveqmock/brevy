/**
 * @module 访问权限读操作维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.accessAuth.crud.AccessAuthRead", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "AccessAuthReadMainGridID",
		    title: this.moduleText,
			iconCls: this.moduleIcon,
			store: accessAuthDS,
			tbar: this.createToolbar(),
			viewConfig: {
		        stripeRows: true,
		        forceFit: true
		    },
		    columns: this.createColumns(),
		    selModel: this.createSelectionModel(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: accessAuthDS,
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
							accessAuthDS.getProxy().setExtraParam("appId", newVal)
							accessAuthDS.load();						
						},
						scope: this		
					}
				}
			},
			" ", "-",
			{text: Msg.App.add, iconCls: Ext.ux.Icons.world_add, handler: function(btn){
				Pub.ResLoader.jsPack(me.getExtRes("systemMGR/authCFG/accessAuth/ui/crud/AccessAuthCreate.js"), function(){
					me.createInstance("App.systemMGR.authCFG.accessAuth.crud.AccessAuthCreate", Msg.App.add, btn.iconCls).show(btn);
				});
			}},
			"-",
			{text: Msg.App.edit, iconCls: Ext.ux.Icons.world_edit, handler:function(btn){
				me.handleSelectedRecord("AccessAuthReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("systemMGR/authCFG/accessAuth/ui/crud/AccessAuthUpdate.js"), function(){
						me.createInstance("App.systemMGR.authCFG.accessAuth.crud.AccessAuthUpdate", Msg.App.edit, btn.iconCls, p.sm[0]).show(btn);
					});
				});
			}},
			"-",
			{text: Msg.App.del, iconCls: Ext.ux.Icons.world_delete, handler:function(btn){
				me.handleSelectedRecord("AccessAuthReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("systemMGR/authCFG/accessAuth/ui/crud/AccessAuthDelete.js"), function(){
						Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, Msg.Prompt.confirmDelRec, null, function(r){
							if(r == "yes"){
								me.createInstance("App.systemMGR.authCFG.accessAuth.crud.AccessAuthDelete", Msg.App.del, btn.iconCls, p.sm);
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
				store: accessAuthDS
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
	    	{text: this.urlPattern, dataIndex: "urlPattern", flex: 5},
	    	{text: this.authenFilter, dataIndex: "authenFilter", flex: 2},
	    	{text: this.authorFilter, dataIndex: "authorFilter", flex: 2},
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