/**
 * @module 技术中心综合管理任务读操作
 * @author caobin
 */
Ext.define("App.biz.cads.myTasks.catask.crud.CataskRead", {
	extend : "App.Module",
	
	beforeInit : function(){
		this.callParent();
		this.cellEditing = new Ext.grid.plugin.CellEditing({
            clicksToEdit: 1
        });
        dictDS_28.load();
        userDS.load();
	},
	
	init : function(){
		var me = this;
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
		    plugins: [this.cellEditing],
		    columns: this.createColumns(),
		    selModel: this.createSelectionModel(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: CataskDS,
		        dock: "bottom",
		        displayInfo: true,
		        plugins : [Ext.create("Ext.ux.PagingToolbarResizer", {options : this.pageSizeOptions, width: 70})]
		    }],
		    listeners: {
		    	edit: function(e, o){
		    		if(o.originalValue == o.value)return;	    		
		    		Ext.Ajax.request({
						url: "../biz/cads/myTasks/catask/confirm.json",
						method: "POST",
						jsonData: {
					        id: o.record.get("id"),
					        dictDetailId: o.record.get("result")
					    },
						loadMask: true,
						loadMaskEl: Ext.getCmp("CataskReadMainGridID").getEl(),
						loadMaskMsg: Msg.App.updating,	
						success: function(response){ 
							var r = eval("(" + response.responseText + ")");
							if(r.RSP_HEAD.TRAN_SUCCESS == "0"){
								o.record.reject();
								Pub.MsgBox.showMsgBox(Pub.MsgBox.ERROR, r.RSP_HEAD.ERROR_MESSAGE);
							}else{
								o.record.commit();
								Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");
							}					
						},
						scope: this
					});
		    	},
		    	
		    	itemdblclick : function(grid, record, item, index){
		    		var btn = Ext.getCmp("CataskRead.updateBtn");
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/myTasks/catask/ui/crud/CataskUpdate.js"), function(){
						me.createInstance("App.biz.cads.myTasks.catask.crud.CataskUpdate", me.edit, btn.iconCls, record).show(btn);
					});
		    	}		    	
		    }
		});
	},
	
	//创建工具栏
	createToolbar : function(){
		var me = this;
		return [
			" ", 
			{text: Msg.App.add, iconCls: Ext.ux.Icons.page_add, handler: function(btn){
				Pub.ResLoader.jsPack(me.getExtRes("biz/cads/myTasks/catask/ui/crud/CataskCreate.js"), function(){
					me.createInstance("App.biz.cads.myTasks.catask.crud.CataskCreate", Msg.App.add, btn.iconCls).show(btn);
				});
			}},
			"-",
			{ id:"CataskRead.updateBtn", text: me.edit, iconCls: Ext.ux.Icons.page_edit, handler:function(btn){
				me.handleSelectedRecord("CataskReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/myTasks/catask/ui/crud/CataskUpdate.js"), function(){
						me.createInstance("App.biz.cads.myTasks.catask.crud.CataskUpdate", me.edit, btn.iconCls, p.sm[0]).show(btn);
					});
				});
			}},
			"-",
			{text: Msg.App.showAttachments, iconCls: Ext.ux.Icons.page_attach, handler:function(btn){
				me.handleSelectedRecord("CataskReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/myTasks/catask/ui/crud/CataskAttachments.js"), function(){
						me.createInstance("App.biz.cads.myTasks.catask.crud.CataskAttachments", Msg.App.showAttachments, btn.iconCls, p.sm[0]).show(btn);
					});
				});
			}},
			"-",
			{text: Msg.App.archive, iconCls: Ext.ux.Icons.page_go, handler:function(btn){
				me.handleSelectedRecord("CataskReadMainGridID", function(p){
					Pub.ResLoader.jsPack(me.getExtRes("biz/cads/myTasks/catask/ui/crud/CataskArchive.js"), function(){
						Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, Msg.Prompt.confirmArchiveRec, null, function(r){
							if(r == "yes"){
								me.createInstance("App.biz.cads.myTasks.catask.crud.CataskArchive", Msg.App.archive, btn.iconCls, p.sm);
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
				store: CataskDS
			}
		];
	},

	
	//创建列
	createColumns : function(){
		var me = this;
		return [		
			{text: this.userId, dataIndex: "userId", width:65, renderer: function(value){
				if(!value)return "";
			    return userDS.getById(value).get("chName")			 	
			}},
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
	    		renderer: function(v){return me.dictMapping(v);},
	    		editor: new Ext.form.field.ComboBox({
                    typeAhead: true,
                    triggerAction: "all",
                    store: dictDS_28,
                    forceSelection: true,
					editable: false,
					emptyText: me.emptyResult,
					queryMode: "local",
					displayField: "name",
					valueField: "id",
					plugins: ["clearbutton"]
                })
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
	},
	
	//创建选择框
	createSelectionModel : function(){
		return Ext.create("Ext.selection.CheckboxModel",{
			allowDeselect: true,
			mode: "SINGLE"
		});
	}
	
});