/**
 * @module 附件明细读操作
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.gd.crud.GDAttachments", {
	extend : "App.Module",
	
	init : function(){
		GDAttachmentDS.on("beforeload", function(store){		
			store.getProxy().setExtraParam("gdId", this.params.get("id"));
		}, this);
		GDAttachmentDS.load();
		
		return Ext.create("Ext.window.Window", {
			title: Ext.String.format(this.moduleText, this.params.get("name"), this.params.get("code")),
			iconCls: this.moduleIcon,
			height: 310,
    		width: 420,
    		layout: "fit",
    		modal: true,
    		items: { 
    			id: "GDAttachReadMainGridID",
    			xtype: "grid",
    			store: GDAttachmentDS,
				tbar: this.createToolbar(),
				viewConfig: {
			        stripeRows: true,
			        forceFit: true
			    },
			    columns: this.createColumns(),
			    selModel: this.createSelectionModel()
    		}
		});
	},
	
	//创建工具栏
	createToolbar : function(){
		var me = this;
		return [
			" ", 
			{text: Msg.App.download, iconCls: Ext.ux.Icons.fugue_drive_download, handler:function(btn){
				me.handleSelectedRecord("GDAttachReadMainGridID", function(p){
					location.href = _ctxPath + "/biz/cads/myTasks/gd/fileDownload.html?attachId=" + p.sm[0].get("id");
				});
			}}
		];
	},

	
	//创建列
	createColumns : function(){
		var me = this;
		return [
		 	{text: this.name, dataIndex: "path", flex: 1, renderer: function(v){
		 		if(!v)return v;
		 		return v.replace(/^(.*)[/\\]([^/\\]*)$/, "$2");
		 		
		 	}}
		];
	},
	
	//创建选择框
	createSelectionModel : function(){
		return Ext.create("Ext.selection.CheckboxModel",{
			allowDeselect: true,
			mode: "SINGLE"
		});
	}
	
});