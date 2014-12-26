/**
 * @module 用户读操作维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.user.crud.UserRead", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "UserReadMainGridID",
		    title: this.moduleText,
			iconCls: this.moduleIcon,
			store: userDS,
			tbar: this.createToolbar(),
			viewConfig: {
		        stripeRows: true,
		        forceFit: true
		    },
		    columns: this.createColumns(),
		    selModel: this.createSelectionModel(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: userDS,
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
			{text: Msg.App.add, iconCls: Ext.ux.Icons.user_add, handler: function(btn){
				Pub.ResLoader.jsPack(me.getExtRes("systemMGR/authCFG/user/ui/crud/UserCreate.js"), function(){
					me.createInstance("App.systemMGR.authCFG.user.crud.UserCreate", Msg.App.add, btn.iconCls).show(btn);
				});
			}},
			"-",
			{text: Msg.App.edit, iconCls: Ext.ux.Icons.user_edit, handler:function(btn){
				me.handleSelectedRecord("UserReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("systemMGR/authCFG/user/ui/crud/UserUpdate.js"), function(){
						me.createInstance("App.systemMGR.authCFG.user.crud.UserUpdate", Msg.App.edit, btn.iconCls, p.sm[0]).show(btn);
					});
				});
			}},
			"-",
			{text: Msg.App.del, iconCls: Ext.ux.Icons.user_delete, handler:function(btn){
				me.handleSelectedRecord("UserReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("systemMGR/authCFG/user/ui/crud/UserDelete.js"), function(){
						Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, Msg.Prompt.confirmDelRec, null, function(r){
							if(r == "yes"){
								me.createInstance("App.systemMGR.authCFG.user.crud.UserDelete", Msg.App.del, btn.iconCls, p.sm);
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
				store: userDS
			}
		];
	},

	
	//创建列
	createColumns : function(){
		var me = this;
		return [
		 	{text: this.username, dataIndex: "username", flex: 3},  
	    	{text: this.chName, dataIndex: "chName", flex: 3},
	    	{text: this.position, dataIndex: "position", flex: 6},
	    	{text: this.dept, dataIndex: "dept", flex: 6},
	    	{text: this.status, dataIndex: "status", flex: 1,  renderer: function(value) {
	            return value == 0 ? me.invalid : me.valid;
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