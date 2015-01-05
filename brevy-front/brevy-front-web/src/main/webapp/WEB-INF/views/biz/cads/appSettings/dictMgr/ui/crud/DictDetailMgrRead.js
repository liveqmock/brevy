/**
 * @module 字典明细读操作
 * @author caobin
 */
Ext.define("App.biz.cads.appSettings.dictMgr.crud.DictDetailMgrRead", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.window.Window", {
			title: Ext.String.format(this.moduleText, this.params.get("name"), this.params.get("code")),
			iconCls: this.moduleIcon,
			height: 400,
    		width: 900,
    		layout: "fit",
    		modal: true,
    		items: { 
    			id: "DictDetailMgrReadMainGridID",
    			xtype: "grid",
    			store: dictDetailMgrDS,
				tbar: this.createToolbar(),
				viewConfig: {
			        stripeRows: true,
			        forceFit: true
			    },
			    columns: this.createColumns(),
			    selModel: this.createSelectionModel(),
			    dockedItems: [{
			        xtype: "pagingtoolbar",
			        store: dictDetailMgrDS,
			        dock: "bottom",
			        displayInfo: true,
			        plugins : [Ext.create("Ext.ux.PagingToolbarResizer", {options : this.pageSizeOptions, width: 70})]
			    }]
    		}
		});
	},
	
	//创建工具栏
	createToolbar : function(){
		var me = this;
		return [
			" ", 
			{text: Msg.App.add, iconCls: Ext.ux.Icons.page_add, handler: function(btn){
				Pub.ResLoader.jsPack(me.getExtRes("biz/cads/appSettings/dictMgr/ui/crud/DictDetailMgrCreate.js"), function(){
					me.createInstance("App.biz.cads.appSettings.dictMgr.crud.DictDetailMgrCreate", Msg.App.add, btn.iconCls, me.params.get("id")).show(btn);
				});
			}},
			"-",
			{text: Msg.App.edit, iconCls: Ext.ux.Icons.page_edit, handler:function(btn){
				me.handleSelectedRecord("DictDetailMgrReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/appSettings/dictMgr/ui/crud/DictDetailMgrUpdate.js"), function(){
						me.createInstance("App.biz.cads.appSettings.dictMgr.crud.DictDetailMgrUpdate", Msg.App.edit, btn.iconCls, p.sm[0]).show(btn);
					});
				});
			}},
			"-",
			{text: Msg.App.del, iconCls: Ext.ux.Icons.page_delete, handler:function(btn){
				me.handleSelectedRecord("DictDetailMgrReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/appSettings/dictMgr/ui/crud/DictDetailMgrDelete.js"), function(){
						Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, Msg.Prompt.confirmDelRec, null, function(r){
							if(r == "yes"){
								me.createInstance("App.biz.cads.appSettings.dictMgr.crud.DictDetailMgrDelete", Msg.App.del, btn.iconCls, p.sm);
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
				store: dictDetailMgrDS
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
	}
	
});