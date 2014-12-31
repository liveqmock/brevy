/**
 * @module 技术中心工单读操作
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.gd.crud.GDRead", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "GDReadMainGridID",
		    title: this.moduleText,
			iconCls: this.moduleIcon,
			store: GDDS,
			tbar: this.createToolbar(),
			viewConfig: {
		        stripeRows: true,
		        forceFit: true
		    },
		    columns: this.createColumns(),
		    selModel: this.createSelectionModel(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: GDDS,
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
			{text: Msg.App.add, iconCls: Ext.ux.Icons.page_add, handler: function(btn){
				Pub.ResLoader.jsPack(me.getExtRes("biz/cads/appSettings/dictMgr/ui/crud/DictMgrCreate.js"), function(){
					me.createInstance("App.biz.cads.appSettings.dictMgr.crud.DictMgrCreate", Msg.App.add, btn.iconCls).show(btn);
				});
			}},
			"-",
			{text: Msg.App.edit, iconCls: Ext.ux.Icons.page_edit, handler:function(btn){
				me.handleSelectedRecord("DictMgrReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/appSettings/dictMgr/ui/crud/DictMgrUpdate.js"), function(){
						me.createInstance("App.biz.cads.appSettings.dictMgr.crud.DictMgrUpdate", Msg.App.edit, btn.iconCls, p.sm[0]).show(btn);
					});
				});
			}},
			"-",
			{text: Msg.App.archive, iconCls: Ext.ux.Icons.page_go, handler:function(btn){
				me.handleSelectedRecord("DictMgrReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/appSettings/dictMgr/ui/crud/DictMgrDelete.js"), function(){
						Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, Msg.Prompt.confirmDelRec, null, function(r){
							if(r == "yes"){
								me.createInstance("App.biz.cads.appSettings.dictMgr.crud.DictMgrDelete", Msg.App.del, btn.iconCls, p.sm);
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
				store: GDDS
			}
		];
	},

	
	//创建列
	createColumns : function(){
		var me = this;
		return [
		 	{text: this.name, dataIndex: "name", flex: 9.6, renderer: function(value, metadata) {  
                metadata.tdAttr = 'data-qtip="' + value +'"';  
                return value;  
            }},  
	    	{text: this.recvDate, dataIndex: "recvDate", flex: 3.7},
	    	{text: this.execType, dataIndex: "execType", flex: 1.8},
	    	{text: this.type, dataIndex: "type", flex: 1.8},
	    	{text: this.briefName, dataIndex: "briefName", flex: 5.2, renderer: function(value, metadata) {  
                metadata.tdAttr = 'data-qtip="' + value +'"';  
                return value;  
            }},
	    	{text: this.priority, dataIndex: "priority", flex: 2.5},
	    	{text: this.requireFinishTime, dataIndex: "requireFinishTime", flex: 4.5},
	    	{text: this.estimateJob, dataIndex: "estimateJob", flex: 3.8},
	    	{text: this.preCond, dataIndex: "preCond", flex: 4, renderer: function(value, metadata) {  
                metadata.tdAttr = 'data-qtip="' + value +'"';  
                return value;  
            }},
	    	{text: this.implTeam, dataIndex: "implTeam", flex: 4, renderer: function(value, metadata) {  
                metadata.tdAttr = 'data-qtip="' + value +'"';  
                return value;  
            }},
	    	{text: this.pmName, dataIndex: "pmName", flex: 3.5},
	    	{text: this.startDate, dataIndex: "startDate", flex: 3.2},
	    	{text: this.ini, dataIndex: "ini", width: 30},
	    	{text: this.rdp, dataIndex: "rdp", width: 30},
	    	{text: this.ad, dataIndex: "ad", width: 30},
	    	{text: this.scp, dataIndex: "scp", width: 30},
	    	{text: this.sit, dataIndex: "sit", width: 30},
	    	{text: this.uat, dataIndex: "uat", width: 30},
	    	{text: this.pip, dataIndex: "pip", width: 30},
	    	{text: this.smp, dataIndex: "smp", width: 30},
	    	{text: this.progress, dataIndex: "progress", flex: 2},
	    	{text: this.finishDate, dataIndex: "finishDate", flex: 3},
	    	{text: this.usingResource, dataIndex: "usingResource", flex: 3},
	    	{text: this.usingTime, dataIndex: "usingTime", flex: 3},
	    	{text: this.attachType, dataIndex: "attachType", flex: 3}
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