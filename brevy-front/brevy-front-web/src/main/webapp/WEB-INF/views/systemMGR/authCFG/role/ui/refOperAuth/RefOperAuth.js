/**
 * @module 角色关联访问权限维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.role.refOperAuth.RefOperAuth", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.panel.Panel", {
			id: "RefOperAuthPanelID",
			title: this.refOperAuthTitle,
			iconCls: Ext.ux.Icons.table_gear,
			layout: "border",
			bodyStyle: Pub.Style.grayBody,
			items: [
				this.createRoleInfoPanel(),
				this.createSelectedGrid(),
				this.createCandidateGrid()
			]
		});
	},
	
	//创建角色信息显示Panel
	createRoleInfoPanel : function(){
		return Ext.create("Ext.panel.Panel", {
			id: "RefOperAuthNorthPanelID",
			region: "north",	
			style: {
				paddingLeft: "5px",
				paddingTop: "5px",
				paddingBottom: "5px"
			},
			border: 0,
			bodyStyle: Pub.Style.grayBody,
			html: "N/A"
		});
		
	},
	
	//创建已选Grid
	createSelectedGrid : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "RefOperAuthWestPanelID",
			flex: 1,
			region: "center",
			tbar: this.createSelectedToolbar(),
			store: roleRefOperAuthSelectedDS,
			viewConfig: {
		        stripeRows: true
		    },
		    columns: this.createSelectedColumns(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: roleRefOperAuthSelectedDS,
		        dock: "bottom",
		        displayInfo: true,
		        plugins : [Ext.create("Ext.ux.PagingToolbarResizer", {options : this.pageSizeOptions, width: 70})]
		    }]
		});
	},
	
	//创建已选Grid的工具栏
	createSelectedToolbar : function(){
		var me = this;
		return [
			{text: this.cancelRefs, iconCls: Ext.ux.Icons.fugue_cross_octagon, handler: function(btn){
				Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, me.confirmCancelAllRefs, null, function(c){
					if(c == "yes"){
						var selection = Ext.getCmp("RoleReadMainGridID").getSelectionModel().getSelection()[0];
						var count = roleRefOperAuthSelectedDS.getCount();
						var operPermIds = [];
						if(count > 0){
							roleRefOperAuthSelectedDS.each(function(r){
								operPermIds.push(r.get("id"))
							});
							Ext.Ajax.request({
								url: "../maintenance/role/delOperAuthsRefRole.json",
								method: "POST",
								jsonData: {
							        roleId: selection.get("id"),
							        operPermIds: operPermIds.join(",")
							    },
								loadMask: true,
								loadMaskEl: Ext.getCmp("RefOperAuthWestPanelID").getEl(),
								loadMaskMsg: Msg.App.updating,	
								success: function(response){ 
									Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
									roleRefOperAuthSelectedDS.reload();
									roleRefOperAuthCandidateDS.reload();
								},
								scope: this
							});
						}	
					}					
				});
			}},
			"-",
			"->",
			{
				fieldLabel: this.keywordSearch,
				labelWidth: Ext.isChrome ? 70 : 60,
				xtype: "searchfield",
				width: 260,
				store: roleRefOperAuthSelectedDS
			}
		];
	},
	
	//创建已选Grid的Columns
	createSelectedColumns : function(){
		var me = this;
		return [
				{xtype: "rownumberer"},
		    	{text: this.operAuthName, dataIndex: "name", flex: 2},
		    	{text: this.operAuthCode, dataIndex: "code", flex: 2},
		    	{text: this.operAuthOper, dataIndex: "authorizedOper", flex: 5},
	            {
	                text: this.refOperAuthWestText,
	                width: 115,
	                menuDisabled: true,
	                xtype: "actioncolumn",
	                tooltip: me.refOperAuthWestTooltip,
	                align: "center",
	                handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
	                	var selection = Ext.getCmp("RoleReadMainGridID").getSelectionModel().getSelection()[0];
						Ext.Ajax.request({
							url: "../maintenance/role/delOperAuthRefRole.json",
							method: "POST",
							jsonData: {
						        roleId: selection.get("id"),
						        operPermId: record.get("id")
						    },
							loadMask: true,
							loadMaskEl: Ext.getCmp("RefOperAuthWestPanelID").getEl(),
							loadMaskMsg: Msg.App.updating,	
							success: function(response){ 
								Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
								roleRefOperAuthSelectedDS.reload();
								roleRefOperAuthCandidateDS.reload();
							},
							scope: this
						});
	                },
	                getClass: function(v, meta, r){
	                	return Ext.ux.Icons.fugue_cross_circle;      	
	                }
	            }
		    ];
	},
	
	//创建待选Grid
	createCandidateGrid : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "RefOperAuthCandidateGridID",
			flex: 1,
			title:"",
			region: "east",
			tbar: this.createCandidateToolbar(),
			store: roleRefOperAuthCandidateDS,
			viewConfig: {
		        stripeRows: true,
		        forceFit: true
		    },
		    style:{
		    	borderStyle: "solid",
		    	borderLeft: "20",
		    	borderTop: "0",
		    	borderBottom: "0",
		    	borderRight: "0"
		    },
		    columns: this.createCandidateColumns(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: roleRefOperAuthCandidateDS,
		        dock: "bottom",
		        displayInfo: true,
		        plugins : [Ext.create("Ext.ux.PagingToolbarResizer", {options : this.pageSizeOptions, width: 70})]
		    }]
		});	
	},
	
	//创建已选Grid的工具栏
	createCandidateToolbar : function(){
		var me = this;
		return [
			{text: this.addRefs, iconCls: Ext.ux.Icons.fugue_plus_octagon, handler: function(btn){
				Pub.MsgBox.showMsgBox(Pub.MsgBox.CONFIRM, me.confirmAddAllRefs, null, function(c){
					if(c == "yes"){
						var selection = Ext.getCmp("RoleReadMainGridID").getSelectionModel().getSelection()[0];
						var count = roleRefOperAuthCandidateDS.getCount();
						var operPermIds = [];
						if(count > 0){
							roleRefOperAuthCandidateDS.each(function(r){
								operPermIds.push(r.get("id"))
							});
							Ext.Ajax.request({
								url: "../maintenance/role/addOperAuthsRefRole.json",
								method: "POST",
								jsonData: {
							        roleId: selection.get("id"),
							        operPermIds: operPermIds.join(",")
							    },
								loadMask: true,
								loadMaskEl: Ext.getCmp("RefOperAuthWestPanelID").getEl(),
								loadMaskMsg: Msg.App.updating,	
								success: function(response){ 
									Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
									roleRefOperAuthSelectedDS.reload();
									roleRefOperAuthCandidateDS.reload();
								},
								scope: this
							});
						}	
					}
					
				});
			}},
			"-",
			"->",
			{
				fieldLabel: this.keywordSearch,
				labelWidth: Ext.isChrome ? 70 : 60,
				xtype: "searchfield",
				width: 260,
				store: roleRefOperAuthCandidateDS
			}
		];
	},
	
	//创建待选Grid的Columns
	createCandidateColumns : function(){
		var me = this;
		return [
			{xtype: "rownumberer"},
		 	{text: this.operAuthName, dataIndex: "name", flex: 2},
		    {text: this.operAuthCode, dataIndex: "code", flex: 2},
		    {text: this.operAuthOper, dataIndex: "authorizedOper", flex: 5},
		 	{
				text: this.refOperAuthEastActionText,
				width: 115,
				menuDisabled: true,
				xtype: "actioncolumn",
				tooltip: me.refOperAuthEastActionTooltip,
				align: "center",
				handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
					var selection = Ext.getCmp("RoleReadMainGridID").getSelectionModel().getSelection()[0];
					Ext.Ajax.request({
						url: "../maintenance/role/addOperAuthRefRole.json",
						method: "POST",
						jsonData: {
					        roleId: selection.get("id"),
					        operPermId: record.get("id")
					    },
						loadMask: true,
						loadMaskEl: Ext.getCmp("RefOperAuthCandidateGridID").getEl(),
						loadMaskMsg: Msg.App.updating,	
						success: function(response){ 
							Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");
							roleRefOperAuthSelectedDS.reload();
							roleRefOperAuthCandidateDS.reload();
						},
						scope: this
					});
				},
				getClass: function(){
					return Ext.ux.Icons.fugue_plus_circle;                	
				}
	        }        
		];
	}
});