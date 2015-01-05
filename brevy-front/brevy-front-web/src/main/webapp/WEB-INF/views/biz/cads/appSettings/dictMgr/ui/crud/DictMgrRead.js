/**
 * @module 字典读操作
 * @author caobin
 */
Ext.define("App.biz.cads.appSettings.dictMgr.crud.DictMgrRead", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "DictMgrReadMainGridID",
		    title: this.moduleText,
			iconCls: this.moduleIcon,
			store: dictMgrDS,
			tbar: this.createToolbar(),
			viewConfig: {
		        stripeRows: true,
		        forceFit: true
		    },
		    columns: this.createColumns(),
		    selModel: this.createSelectionModel(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: dictMgrDS,
		        dock: "bottom",
		        displayInfo: true,
		        plugins : [Ext.create("Ext.ux.PagingToolbarResizer", {options : this.pageSizeOptions, width: 70})]
		    }],
		    listeners: {
		    	itemdblclick : {
			    	fn : function(grid, record, item, index){
			    		this.createDictDetailUI(record);
			    	},
			    	scope: this
		    	}
		    }
		});
	},
	
	//创建工具栏
	createToolbar : function(){
		var me = this;
		return [
			" ", 
			{text: Msg.App.add, iconCls: Ext.ux.Icons.book_add, handler: function(btn){
				Pub.ResLoader.jsPack(me.getExtRes("biz/cads/appSettings/dictMgr/ui/crud/DictMgrCreate.js"), function(){
					me.createInstance("App.biz.cads.appSettings.dictMgr.crud.DictMgrCreate", Msg.App.add, btn.iconCls).show(btn);
				});
			}},
			"-",
			{text: Msg.App.edit, iconCls: Ext.ux.Icons.book_edit, handler:function(btn){
				me.handleSelectedRecord("DictMgrReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/appSettings/dictMgr/ui/crud/DictMgrUpdate.js"), function(){
						me.createInstance("App.biz.cads.appSettings.dictMgr.crud.DictMgrUpdate", Msg.App.edit, btn.iconCls, p.sm[0]).show(btn);
					});
				});
			}},
			"-",
			{text: Msg.App.del, iconCls: Ext.ux.Icons.book_delete, handler:function(btn){
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
			"-",
			{id: "DictMgrRead.showDetailBtn", text: me.showDetail, iconCls: Ext.ux.Icons.book_link, handler:function(btn){
				me.handleSelectedRecord("DictMgrReadMainGridID", function(p){
					me.createDictDetailUI(p.sm[0]);
				});
			}
			},
			"->",
			{
				fieldLabel: this.keywordSearch,
				labelWidth: Ext.isChrome ? 70 : 60,
				xtype: "searchfield",
				width: 260,
				store: dictMgrDS
			}
		];
	},

	
	//创建列
	createColumns : function(){
		var me = this;
		return [
			{text: this.id, dataIndex: "id", flex: 1, hidden: true},
		 	{text: this.name, dataIndex: "name", flex: 3},  
	    	{text: this.code, dataIndex: "code", flex: 3},
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
	},
	
	//创建数据字典明细窗口
	createDictDetailUI : function(sm, event){
		var me = this;
		Pub.ResLoader.jsPack(
			[
				me.getExtRes("biz/cads/appSettings/dictMgr/store/DictDetailMgrStore.js"),
				me.getExtRes("biz/cads/appSettings/dictMgr/ui/crud/DictDetailMgrRead.js")
			], function(event){
				var win = me.createInstance("App.biz.cads.appSettings.dictMgr.crud.DictDetailMgrRead", me.dictDetailMgrTitle, Ext.ux.Icons.book_link, sm);			
				win.show(Ext.getCmp("DictMgrRead.showDetailBtn"));
				dictDetailMgrDS.on("beforeload", function(store){
					store.getProxy().setExtraParam("dictId", sm.get("id"))
				}, this);
				dictDetailMgrDS.load();
			}
		);
		
	}
	
});