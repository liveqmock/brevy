/**
 * @module 技术中心需求评估单读操作
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.demand.crud.DemandRead", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "DemandReadMainGridID",
		    title: this.moduleText,
			iconCls: this.moduleIcon,
			store: DemandDS,
			tbar: this.createToolbar(),
			viewConfig: {
		        stripeRows: true,
		        forceFit: true
		    },
		    columns: this.createColumns(),
		    selModel: this.createSelectionModel(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: DemandDS,
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
				Pub.ResLoader.jsPack(me.getExtRes("biz/cads/myTasks/demand/ui/crud/DemandCreate.js"), function(){
					me.createInstance("App.biz.cads.myTasks.demand.crud.DemandCreate", Msg.App.add, btn.iconCls).show(btn);
				});
			}},
			"-",
			{text: me.edit, iconCls: Ext.ux.Icons.page_edit, handler:function(btn){
				me.handleSelectedRecord("DemandReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/myTasks/demand/ui/crud/DemandUpdate.js"), function(){
						me.createInstance("App.biz.cads.myTasks.demand.crud.DemandUpdate", me.edit, btn.iconCls, p.sm[0]).show(btn);
					});
				});
			}},
			"-",
			{text: Msg.App.showAttachments, iconCls: Ext.ux.Icons.page_attach, handler:function(btn){
				me.handleSelectedRecord("DemandReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/myTasks/demand/ui/crud/DemandAttachments.js"), function(){
						me.createInstance("App.biz.cads.myTasks.demand.crud.DemandAttachments", Msg.App.showAttachments, btn.iconCls, p.sm[0]).show(btn);
					});
				});
			}},
			"-",
			{text: Msg.App.archive, iconCls: Ext.ux.Icons.page_go, handler:function(btn){
				me.handleSelectedRecord("DemandReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/myTasks/demand/ui/crud/DemandArchive.js"), function(){
						Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, Msg.Prompt.confirmArchiveRec, null, function(r){
							if(r == "yes"){
								me.createInstance("App.biz.cads.myTasks.demand.crud.DemandArchive", Msg.App.archive, btn.iconCls, p.sm);
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
				store: DemandDS
			}
		];
	},

	
	//创建列
	createColumns : function(){
		var me = this;
		return [
			{text: this.id, dataIndex: "id", width:45, renderer: function(v){
				return Ext.String.leftPad(v, 5, "0");
				
			}},
		 	{text: this.prjName, dataIndex: "prjName", width: 220, renderer: Pub.Utils.cellTips},  
	    	{text: this.recvDate, dataIndex: "recvDate", width: 80, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.priority, dataIndex: "priority", width: 60, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.requireFinishTime, dataIndex: "requireFinishTime", width:80, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.estimateDev, dataIndex: "estimateDev", width:120},
	    	/*{text: this.estimateTest, dataIndex: "estimateTest", width:120},*/
	    	{text: this.preCond, dataIndex: "preCond", width:130, renderer: Pub.Utils.cellTips},
	    	{text: this.implTeam, dataIndex: "implTeam", width:130, renderer: Pub.Utils.cellTips},
	    	{text: this.startDate, dataIndex: "startDate", width:80, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.devFinishDate, dataIndex: "devFinishDate", width:82, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.sitWorkload, dataIndex: "sitWorkload", width:120},
	    	{text: this.sitFinishDate, dataIndex: "sitFinishDate", width:80, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.status, dataIndex: "status", width:70, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.attachType, dataIndex: "attachType", xtype:"actioncolumn", width: 80,  menuText: this.attachType, items:[
	    		{
	    			getClass: function(value, meta){
	    				if(!value)return value;
	    				if(value.indexOf("doc") > -1 || value.indexOf("docx") > -1){
	    					return Ext.ux.Icons.fugue_document_word_text;
	    				}
	    			}
	    		},
	    		{
	    			getClass: function(value, meta){
	    				if(!value)return value;
	    				if(value.indexOf("pdf") > -1){
		    				return Ext.ux.Icons.fugue_document_pdf_text;
		    			}
	    			}
	    		},
	    		{
	    			getClass: function(value, meta){
	    				if(!value)return value;
		    			if(value.indexOf("zip") > -1 || value.indexOf("rar") > -1){
		    				return Ext.ux.Icons.fugue_folder_zipper;
		    			}
	    			}
	    		}
	    	]},
	    	{text: this.remark, dataIndex: "remark", flex: 1, minWidth:160, renderer: Pub.Utils.cellTips}
		];
	},
	
	//日期格式化
	formatDate: function(value){
		if(!value)return value;
	   	return Ext.Date.format(new Date(value), this.format)
	},
	
	//数据字典映射
	dictMapping: function(value){
	    if(!value)return value;
	    var o = Ext.Array.findBy(dictDetailMgrDS, function(item){
	    	return item.id == value;    
	    });
	    return o.name;
	},
	
	//创建选择框
	createSelectionModel : function(){
		return Ext.create("Ext.selection.CheckboxModel",{
			allowDeselect: true,
			mode: "SINGLE"
		});
	}
	
});