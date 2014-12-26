/**
 * @module 用户关联用户组维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.user.refGroup.RefGroup", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.panel.Panel", {
			id: "RefGroupPanelID",
			title: this.refGroupTitle,
			iconCls: Ext.ux.Icons.table_gear,
			layout: "border",
			bodyStyle: Pub.Style.grayBody,
			items: [
				//this.createRoleInfoPanel(),
				//this.createSelectedGrid(),
				//this.createCandidateGrid()
			]
		});
	},
	
	//创建角色信息显示Panel
	createRoleInfoPanel : function(){
		return Ext.create("Ext.panel.Panel", {
			id: "RefAccessAuthNorthPanelID",
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
			id: "RefAccessAuthWestPanelID",
			flex: 1,
			region: "center",
			tbar: this.createSelectedToolbar(),
			store: roleRefAccessAuthSelectedDS,
			viewConfig: {
		        stripeRows: true
		    },
		    columns: this.createSelectedColumns(),
		    dockedItems: [{
		        xtype: "pagingtoolbar",
		        store: roleRefAccessAuthSelectedDS,
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
						var count = roleRefAccessAuthSelectedDS.getCount();
						var accessPermIds = [];
						if(count > 0){
							roleRefAccessAuthSelectedDS.each(function(r){
								accessPermIds.push(r.get("id"))
							});
							Ext.Ajax.request({
								url: "../maintenance/role/delAccessAuthsRefRole.json",
								method: "POST",
								jsonData: {
							        roleId: selection.get("id"),
							        accessPermIds: accessPermIds.join(",")
							    },
								loadMask: true,
								loadMaskEl: Ext.getCmp("RefAccessAuthWestPanelID").getEl(),
								loadMaskMsg: Msg.App.updating,	
								success: function(response){ 
									Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
									roleRefAccessAuthSelectedDS.reload();
									roleRefAccessAuthCandidateDS.reload();
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
				store: roleRefAccessAuthSelectedDS
			}
		];
	},
	
	//创建已选Grid的Columns
	createSelectedColumns : function(){
		var me = this;
		return [
				{xtype: "rownumberer"},
		    	{text: this.accessAuthName, dataIndex: "name", flex: 2},
		    	{text: this.accessAuthCode, dataIndex: "code", flex: 2},
		    	{text: this.accessAuthUrlPattern, dataIndex: "urlPattern", flex: 5},
	            {
	                text: this.refAccessAuthWestText,
	                width: 115,
	                menuDisabled: true,
	                xtype: "actioncolumn",
	                tooltip: me.refAccessAuthWestTooltip,
	                align: "center",
	                handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
	                	var selection = Ext.getCmp("RoleReadMainGridID").getSelectionModel().getSelection()[0];
						Ext.Ajax.request({
							url: "../maintenance/role/delAccessAuthRefRole.json",
							method: "POST",
							jsonData: {
						        roleId: selection.get("id"),
						        accessPermId: record.get("id")
						    },
							loadMask: true,
							loadMaskEl: Ext.getCmp("RefAccessAuthWestPanelID").getEl(),
							loadMaskMsg: Msg.App.updating,	
							success: function(response){ 
								Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
								roleRefAccessAuthSelectedDS.reload();
								roleRefAccessAuthCandidateDS.reload();
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
			id: "RefAccessAuthCandidateGridID",
			flex: 1,
			title:"",
			region: "east",
			tbar: this.createCandidateToolbar(),
			store: roleRefAccessAuthCandidateDS,
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
		        store: roleRefAccessAuthCandidateDS,
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
						var count = roleRefAccessAuthCandidateDS.getCount();
						var accessPermIds = [];
						if(count > 0){
							roleRefAccessAuthCandidateDS.each(function(r){
								accessPermIds.push(r.get("id"))
							});
							Ext.Ajax.request({
								url: "../maintenance/role/addAccessAuthsRefRole.json",
								method: "POST",
								jsonData: {
							        roleId: selection.get("id"),
							        accessPermIds: accessPermIds.join(",")
							    },
								loadMask: true,
								loadMaskEl: Ext.getCmp("RefAccessAuthWestPanelID").getEl(),
								loadMaskMsg: Msg.App.updating,	
								success: function(response){ 
									Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
									roleRefAccessAuthSelectedDS.reload();
									roleRefAccessAuthCandidateDS.reload();
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
				store: roleRefAccessAuthCandidateDS
			}
		];
	},
	
	//创建待选Grid的Columns
	createCandidateColumns : function(){
		var me = this;
		return [
			{xtype: "rownumberer"},
		 	{text: this.accessAuthName, dataIndex: "name", flex: 2},
		    {text: this.accessAuthCode, dataIndex: "code", flex: 2},
		    {text: this.accessAuthUrlPattern, dataIndex: "urlPattern", flex: 5},
		 	{
				text: this.refAccessAuthEastActionText,
				width: 115,
				menuDisabled: true,
				xtype: "actioncolumn",
				tooltip: me.refAccessAuthEastActionTooltip,
				align: "center",
				handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
					var selection = Ext.getCmp("RoleReadMainGridID").getSelectionModel().getSelection()[0];
					Ext.Ajax.request({
						url: "../maintenance/role/addAccessAuthRefRole.json",
						method: "POST",
						jsonData: {
					        roleId: selection.get("id"),
					        accessPermId: record.get("id")
					    },
						loadMask: true,
						loadMaskEl: Ext.getCmp("RefAccessAuthCandidateGridID").getEl(),
						loadMaskMsg: Msg.App.updating,	
						success: function(response){ 
							Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");
							roleRefAccessAuthSelectedDS.reload();
							roleRefAccessAuthCandidateDS.reload();
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