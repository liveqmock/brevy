/**
 * @module 应用读操作维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.app.crud.AppRead", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "AppReadMainGridID",
		    title: this.moduleText,
			iconCls: this.moduleIcon,
			store: appDS,
			tbar: this.createToolbar(),
			viewConfig: {
		        stripeRows: true,
		        forceFit: true
		    },
		    columns: this.createColumns(),
		    selModel: this.createSelectionModel(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: appDS,
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
			" ",
			{text: Msg.App.add, iconCls: Ext.ux.Icons.application_add, handler: function(btn){
				Pub.ResLoader.jsPack(me.getExtRes("systemMGR/authCFG/app/ui/crud/AppCreate.js"), function(){
					me.createInstance("App.systemMGR.authCFG.app.crud.AppCreate", Msg.App.add, btn.iconCls).show(btn);
				});
			}},
			"-",
			{text: Msg.App.edit, iconCls: Ext.ux.Icons.application_edit, handler:function(btn){
				me.handleSelectedRecord("AppReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("systemMGR/authCFG/app/ui/crud/AppUpdate.js"), function(){
						me.createInstance("App.systemMGR.authCFG.app.crud.AppUpdate", Msg.App.edit, btn.iconCls, p.sm[0]).show(btn);
					});
				});
			}},
			"-",
			{text: Msg.App.del, iconCls: Ext.ux.Icons.application_delete, handler:function(btn){
				me.handleSelectedRecord("AppReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("systemMGR/authCFG/app/ui/crud/AppDelete.js"), function(){
						Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, Msg.Prompt.confirmDelRec, null, function(r){
							if(r == "yes"){
								me.createInstance("App.systemMGR.authCFG.app.crud.AppDelete", Msg.App.del, btn.iconCls, p.sm);
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
				store: appDS
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
	    	{text: this.status, dataIndex: "status", flex: 1,  renderer: function(value) {
	            return value == 0 ? me.invalid : me.valid;
	        }},
	    	{text: this.desc, dataIndex: "desc", flex: 8, renderer: function(value, metadata) {  
                metadata.tdAttr = 'data-qtip="' + value +'"';  
                return value;  
            }}
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