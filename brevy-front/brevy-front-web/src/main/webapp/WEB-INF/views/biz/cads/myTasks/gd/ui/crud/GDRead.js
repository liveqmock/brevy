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
						Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, Msg.Prompt.confirmDelRec, null, function(r){
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
		 	{text: this.name, dataIndex: "name", flex: 9.6, renderer: function(value, metadata) {  
                metadata.tdAttr = 'data-qtip="' + value +'"';  
                return value;  
            }},  
	    	{text: this.recvDate, dataIndex: "recvDate", flex: 4, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.execType, dataIndex: "execType", flex: 1.8, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.type, dataIndex: "type", flex: 1.8, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.briefName, dataIndex: "briefName", flex: 5.2, renderer: function(value, metadata) {  
                metadata.tdAttr = 'data-qtip="' + value +'"';  
                return value;  
            }},
	    	{text: this.priority, dataIndex: "priority", flex: 2.5, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.requireFinishTime, dataIndex: "requireFinishTime", flex: 4.5, renderer: function(v){return me.formatDate(v);}},
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
	    	{text: this.startDate, dataIndex: "startDate", flex: 4, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.ini, dataIndex: "ini", width: 30, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.rdp, dataIndex: "rdp", width: 30, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.ad, dataIndex: "ad", width: 30, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.scp, dataIndex: "scp", width: 30, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.sit, dataIndex: "sit", width: 30, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.uat, dataIndex: "uat", width: 30, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.pip, dataIndex: "pip", width: 30, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.smp, dataIndex: "smp", width: 30, renderer: function(v){return me.dictMapping(v);}},
	    	{text: this.progress, dataIndex: "progress", flex: 2, renderer: function(v){if(!v)return v;return v + "%";}},
	    	{text: this.finishDate, dataIndex: "finishDate", flex: 4, renderer: function(v){return me.formatDate(v);}},
	    	{text: this.usingResource, dataIndex: "usingResource", flex: 3},
	    	{text: this.usingTime, dataIndex: "usingTime", flex: 3},
	    	/*{text: this.attachType, dataIndex: "attachType", flex: 3, renderer: function(value, metadata){
	    		if(value){
	    			var icons = "";
	    			if(value.indexOf("doc") > -1 || value.indexOf("docx") > -1){
	    				icons = "<span class='icon-fugue-document_word_text'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>";
	    			}
	    			
	    			if(value.indexOf("pdf") > -1){
	    				icons += "<span class='icon-fugue-document_pdf_text'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>";
	    			}
	    			
	    			if(value.indexOf("zip") > -1 || value.indexOf("rar") > -1){
	    				icons += "<span class='icon-fugue-folder_zipper'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>";	
	    			}
	    			return icons;
	    		}
				return value;
	    	}}*/
	    	{text: this.attachType, dataIndex: "attachType", xtype:"actioncolumn", flex: 3, items:[
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
	   // return dictDetailMgrDS.findRecord("id", value).get("name")
	},
	
	//创建选择框
	createSelectionModel : function(){
		return Ext.create("Ext.selection.CheckboxModel",{
			//allowDeselect: true,
			mode: "MULTI"
		});
	}
	
});