/**
 * @module 技术中心已归档需求评估单读操作
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.archive.demand.crud.ArchivedDemandRead", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "ArchivedDemandReadMainGridID",
		    title: this.moduleText,
			iconCls: this.moduleIcon,
			store: ArchivedDemandDS,
			tbar: this.createToolbar(),
			viewConfig: {
		        stripeRows: true,
		        forceFit: true
		    },
		    columns: this.createColumns(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: ArchivedDemandDS,
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
				store: ArchivedDemandDS
			}
		];
	},

	
	//创建列
	//创建列
	createColumns : function(){
		var me = this;
		return [
			{xtype: 'rownumberer'},
		 	{text: this.prjName, dataIndex: "prjName", flex: 10, renderer: function(value, metadata) {  
                metadata.tdAttr = 'data-qtip="' + value +'"';  
                return value;  
            }},  
	    	{text: this.recvDate, dataIndex: "recvDate", flex: 4, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.priority, dataIndex: "priority", flex: 2.5, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.requireFinishTime, dataIndex: "requireFinishTime", flex: 4.5, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.estimateDev, dataIndex: "estimateDev", flex: 3.8},
	    	{text: this.estimateTest, dataIndex: "estimateTest", flex: 3.8},
	    	{text: this.preCond, dataIndex: "preCond", flex: 4, renderer: function(value, metadata) {  
                metadata.tdAttr = 'data-qtip="' + value +'"';  
                return value;  
            }},
	    	{text: this.implTeam, dataIndex: "implTeam", flex: 4, renderer: function(value, metadata) {  
                metadata.tdAttr = 'data-qtip="' + value +'"';  
                return value;  
            }},
	    	{text: this.startDate, dataIndex: "startDate", flex: 4, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.status, dataIndex: "status", flex: 4, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.remark, dataIndex: "remark", flex: 6, renderer: function(value, metadata) {  
                metadata.tdAttr = 'data-qtip="' + value +'"';  
                return value;  
            }},
	    	{text: this.attachType, dataIndex: "attachType", xtype:"actioncolumn", flex: 4, items:[
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
	    	]}
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
	}
	
});