/**
 * @module 技术中心已归档工单读操作
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.archive.gd.crud.ArchivedGDRead", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "ArchivedGDReadMainGridID",
		    title: this.moduleText,
			iconCls: this.moduleIcon,
			store: ArchivedGDDS,
			tbar: this.createToolbar(),
			viewConfig: {
		        stripeRows: true,
		        forceFit: true
		    },
		    columns: this.createColumns(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: ArchivedGDDS,
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
				store: ArchivedGDDS
			}
		];
	},

	
	//创建列
	createColumns : function(){
		var me = this;
		return [
		 	{text: this.name, dataIndex: "name", flex: 9.6, renderer: function(v, m){return me.valueTip(v, m);}},  
	    	{text: this.recvDate, dataIndex: "recvDate", width:70, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.execType, dataIndex: "execType", flex: 1.8, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.type, dataIndex: "type", flex: 1.8, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.briefName, dataIndex: "briefName", flex: 5.2, renderer: function(v, m){return me.valueTip(v, m);}},
	    	{text: this.priority, dataIndex: "priority", width:45, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.requireFinishTime, dataIndex: "requireFinishTime", width: 75, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.estimateJob, dataIndex: "estimateJob", width: 65},
	    	{text: this.preCond, dataIndex: "preCond", flex: 4, renderer: function(v, m){return me.valueTip(v, m);}},
	    	{text: this.implTeam, dataIndex: "implTeam", flex: 4, renderer: function(v, m){return me.valueTip(v, m);}},
	    	{text: this.pmName, dataIndex: "pmName", width: 65, renderer: function(v, m){return me.valueTip(v, m);}},
	    	{text: this.startDate, dataIndex: "startDate", width:70, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.ini, dataIndex: "ini", width: 28, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.rdp, dataIndex: "rdp", width: 28, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.ad, dataIndex: "ad", width: 28, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.scp, dataIndex: "scp", width: 28, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.sit, dataIndex: "sit", width: 28, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.uat, dataIndex: "uat", width: 28, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.pip, dataIndex: "pip", width: 27, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.smp, dataIndex: "smp", width: 28, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.progress, dataIndex: "progress", width: 45, renderer: function(v){if(!v)return v;return v + "%";}},
	    	{text: this.finishDate, dataIndex: "finishDate", width:70, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.usingResource, dataIndex: "usingResource", width: 60},
	    	{text: this.usingTime, dataIndex: "usingTime", width: 60},
	    	{text: this.attachType, dataIndex: "attachType", xtype:"actioncolumn", width:80, items:[
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
	    	{text: this.remark, dataIndex: "remark", flex: 3, renderer: function(v, m){return me.valueTip(v, m);}}
		];
	},
	
	//信息提示
	valueTip: function(value, metadata){
		if(!value)return "";
		metadata.tdAttr = 'data-qtip="' + value +'"';  
        return value;  
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