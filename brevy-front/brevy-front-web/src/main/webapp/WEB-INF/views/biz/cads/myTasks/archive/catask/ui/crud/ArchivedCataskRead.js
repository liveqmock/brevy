/**
 * @module 技术中心已归档综合管理任务读操作
 * @author caobin
 */			
Ext.define("App.biz.cads.myTasks.archive.catask.crud.ArchivedCataskRead", {
	extend : "App.Module",
	
	beforeInit : function(){
		this.callParent();
        dictDS_28.load();
	},
	
	init : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "CataskReadMainGridID",
		    title: this.moduleText,
			iconCls: this.moduleIcon,
			store: CataskDS,
			tbar: this.createToolbar(),
			viewConfig: {
		        stripeRows: true,
		        forceFit: true
		    },
		    columns: this.createColumns(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: CataskDS,
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
			"->",
			{
				fieldLabel: this.keywordSearch,
				labelWidth: Ext.isChrome ? 70 : 60,
				xtype: "searchfield",
				width: 260,
				store: CataskDS
			}
		];
	},

	
	//创建列
	createColumns : function(){
		var me = this;
		return [			
		 	{text: this.operLv, dataIndex: "operLv", width:65},  
	    	{text: this.importance, dataIndex: "importance", width:60, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.category, dataIndex: "category", width:65, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.source, dataIndex: "source", width:90, renderer: Pub.Utils.cellTips},	    	
	    	{text: this.id, dataIndex: "id", width:45, renderer: function(v){
				return Ext.String.leftPad(v, 5, "0");			
			}},
			{text: this.title, dataIndex: "title", width:170, renderer: Pub.Utils.cellTips},	   
	    	{text: this.reqFinishDate, dataIndex: "reqFinishDate", width: 80, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.jobContent, dataIndex: "jobContent", width:300, renderer: Pub.Utils.cellTips},	   
			{text: this.progress, dataIndex: "progress", width: 45, renderer: function(v){return v + "%";}},
	    	{text: this.finishDate, dataIndex: "finishDate", width:70, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.finishStatus, dataIndex: "finishStatus", width:75, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.usingResource, dataIndex: "usingResource", width:90, renderer: Pub.Utils.cellTips},
	    	{text: this.attachType, dataIndex: "attachType", xtype:"actioncolumn", menuText: this.attachType, width:95, items:[
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
	    				if(value.indexOf("xls") > -1 || value.indexOf("xlsx") > -1){
	    					return Ext.ux.Icons.fugue_document_excel_table;
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
	    		}
	    	]},
	    	{
	    		text: this.result, dataIndex: "result", width:95, 
	    		renderer: function(v){return me.dictMapping(v);}
	    	},
	    	{text: this.remark, dataIndex: "remark", flex:1, minWidth:160, renderer: Pub.Utils.cellTips}
		];
	},
	
	
	//日期格式化
	formatDate: function(value){
		if(!value)return value;
	   	return Ext.Date.format(new Date(value), this.format)
	},
	
	//数据字典映射
	dictMapping: function(value){
	    if(!value)return "";
	    var o = Ext.Array.findBy(dictDetailMgrDS, function(item){
	    	return item.id == value;    
	    });
	    return o.name;
	}
	
});