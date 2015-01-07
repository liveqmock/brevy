/**
 * @module 表重置读操作
 * @author caobin
 */
Ext.define("App.biz.cads.appSettings.maintenance.crud.TableResetRead", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "TableResetReadMainGridID",
		    title: this.moduleText,
			iconCls: this.moduleIcon,
			store: permittedTablesDS,
			tbar: this.createToolbar(),
			viewConfig: {
		        stripeRows: true,
		        forceFit: true
		    },
		    columns: this.createColumns(),
		    selModel: this.createSelectionModel()
		});
	},
	
	//创建工具栏
	createToolbar : function(){
		var me = this;
		return [
			" ",
			{text: me.del, iconCls: Ext.ux.Icons.table_delete, handler:function(btn){
				me.handleSelectedRecord("TableResetReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/appSettings/maintenance/ui/crud/TableResetDelete.js"), function(){
						Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, Msg.Prompt.confirmEmptyTable, null, function(r){
							if(r == "yes"){
								me.createInstance("App.biz.cads.appSettings.maintenance.crud.TableReset", me.del, btn.iconCls, p.sm);
							}	
						});		
					});
				}, null, null, true);
			}}
		];
	},

	
	//创建列
	createColumns : function(){
		return [
		 	{text: this.name, dataIndex: "name", flex: 2},  
	    	{text: this.code, dataIndex: "code", flex: 1}
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