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
				Pub.ResLoader.jsPack(me.getExtRes("biz/cads/myTasks/gd/ui/crud/GDCreate.js"), function(){
					me.createInstance("App.biz.cads.myTasks.gd.crud.GDCreate", Msg.App.add, btn.iconCls).show(btn);
				});
			}},
			"-",
			{text: me.edit, iconCls: Ext.ux.Icons.page_edit, handler:function(btn){
				me.handleSelectedRecord("GDReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/myTasks/gd/ui/crud/GDUpdate.js"), function(){
						me.createInstance("App.biz.cads.myTasks.gd.crud.GDUpdate", me.edit, btn.iconCls, p.sm[0]).show(btn);
					});
				});
			}},
			"-",
			{text: Msg.App.showAttachments, iconCls: Ext.ux.Icons.page_attach, handler:function(btn){
				me.handleSelectedRecord("GDReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/myTasks/gd/ui/crud/GDAttachments.js"), function(){
						me.createInstance("App.biz.cads.myTasks.gd.crud.GDAttachments", Msg.App.showAttachments, btn.iconCls, p.sm[0]).show(btn);
					});
				});
			}},
			"-",
			{text: Msg.App.archive, iconCls: Ext.ux.Icons.page_go, handler:function(btn){
				me.handleSelectedRecord("GDReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/myTasks/gd/ui/crud/GDArchive.js"), function(){
						Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, Msg.Prompt.confirmArchiveRec, null, function(r){
							if(r == "yes"){
								me.createInstance("App.biz.cads.myTasks.gd.crud.GDArchive", Msg.App.archive, btn.iconCls, p.sm);
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
			{text: this.id, dataIndex: "id", width:45, renderer: function(v){
				return Ext.String.leftPad(v, 5, "0");
				
			}},
		 	{text: this.name, dataIndex: "name", width:200, renderer: function(v, m){return me.valueTip(v, m);}},  
	    	{text: this.recvDate, dataIndex: "recvDate", width:80, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.execType, dataIndex: "execType", width:40, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.type, dataIndex: "type", width:40, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.briefName, dataIndex: "briefName", width:140, renderer: function(v, m){return me.valueTip(v, m);}},
	    	{text: this.gdType, dataIndex: "gdType", width:70, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.prjType, dataIndex: "prjType", width:70, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.priority, dataIndex: "priority", width:45, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.requireFinishTime, dataIndex: "requireFinishTime", width: 80, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.estimateJob, dataIndex: "estimateJob", width: 65},
	    	{text: this.preCond, dataIndex: "preCond", width:100, renderer: function(v, m){return me.valueTip(v, m);}},
	    	{text: this.implTeam, dataIndex: "implTeam", width:100, renderer: function(v, m){return me.valueTip(v, m);}},
	    	{text: this.pmName, dataIndex: "pmName", width: 70, renderer: function(v, m){return me.valueTip(v, m);}},
	    	{text: this.startDate, dataIndex: "startDate", width:70, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.ini, dataIndex: "ini", width: 32, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.rdp, dataIndex: "rdp", width: 32, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.ad, dataIndex: "ad", width: 32, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.scp, dataIndex: "scp", width: 32, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.sit, dataIndex: "sit", width: 32, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.uat, dataIndex: "uat", width: 32, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.pip, dataIndex: "pip", width: 32, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.smp, dataIndex: "smp", width: 32, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.devFinishDate, dataIndex: "devFinishDate", width:80, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.sitWorkload, dataIndex: "sitWorkload", width:70},
	    	{text: this.sitFinishDate, dataIndex: "sitFinishDate", width:72, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.status, dataIndex: "status", width:70, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.progress, dataIndex: "progress", width: 45, renderer: function(v){if(!v)return v;return v + "%";}},
	    	{text: this.finishDate, dataIndex: "finishDate", width:80, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.usingResource, dataIndex: "usingResource", width: 60, renderer: function(v, m){return me.valueTip(v, m);}},
	    	{text: this.usingTime, dataIndex: "usingTime", width: 60, renderer: function(v, m){return me.valueTip(v, m);}},
	    	{text: this.attachType, dataIndex: "attachType", xtype:"actioncolumn", menuText: this.attachType, width:85, items:[
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
	    	{text: this.remark, dataIndex: "remark", width: 160, renderer: function(v, m){return me.valueTip(v, m);}}
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
	    if(!value)return "";
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