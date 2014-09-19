/**
 * @module 角色关联菜单维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.role.refMenu.RefMenu", {
	extend : "App.Module",
	
	init : function(){
		return Ext.create("Ext.panel.Panel", {
			id: "RefMenuPanelID",
			title: this.refMenuTitle,
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
			id: "RefMenuNorthPanelID",
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
		var currentClickedItem = -1;
		return Ext.create("Ext.tree.Panel", {
			id: "RefMenuWestPanelID",
			flex: 1,
			region: "center",
			store: roleRefMenuSelectedDS,
			viewConfig: {
		        stripeRows: true
		    },
		    columns: [
		        {
	                xtype: "treecolumn", 
	                text: this.refMenuWestName,
	                flex: 2,
	                menuDisabled: true,
	                dataIndex: "name"
	            },
	            {
	                text: this.refMenuWestText,
	                width: 115,
	                menuDisabled: true,
	                xtype: "actioncolumn",
	                tooltip: this.refMenuWestTooltip,
	                align: "center",
	                handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
	                	var selection = Ext.getCmp("RoleReadMainGridID").getSelectionModel().getSelection()[0];
						Ext.Ajax.request({
							url: "../maintenance/role/delMenuRefRole.json",
							method: "POST",
							jsonData: {
						        roleId: selection.get("id"),
						        menuId: record.get("id")
						    },
							loadMask: true,
							loadMaskEl: Ext.getCmp("RefMenuWestPanelID").getEl(),
							loadMaskMsg: Msg.App.updating,	
							success: function(response){ 
								Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");	
								var grid = Ext.getCmp("RefMenuCandidateGridID");								
								if(!grid.isHidden()){
									grid.hide();
								}
								roleRefMenuSelectedDS.load({node: roleRefMenuSelectedDS.getNodeById(record.get("parentId"))});
							},
							scope: this
						});
	                },
	                getClass: function(v, meta, r){
	                	if(r.get("id") != -1){
	                		return Ext.ux.Icons.fugue_cross_circle;
	                	}	                	
	                }
	            }
		    ],
			listeners : {
				itemclick : {
					fn : function(t, r, item, index){
						if(currentClickedItem == index){
							return;					
						}else{
							currentClickedItem = index;
						}
						
						roleRefMenuCandidateDS.on({
							beforeload : function(store, oper){
								var selection = Ext.getCmp("RoleReadMainGridID").getSelectionModel().getSelection()[0];
								store.getProxy().extraParams = {
									appId: selection.get("appId"),
									node: r.get("id"),
									roleId: selection.get("id")
								};
							},
							
							load : function(t, r){
								var grid = Ext.getCmp("RefMenuCandidateGridID");
								r.length == 0 ? grid.hide() : grid.show();
							}
							
						});
						roleRefMenuCandidateDS.load();
					},
					scope : this
				},
				
				deselect : function(){
					currentClickedItem = -1;
				}
			}
		});
	},
	
	//创建待选Grid
	createCandidateGrid : function(){
		return Ext.create("Ext.grid.Panel", {
			id: "RefMenuCandidateGridID",
			flex: 1,
			title:"",
			region: "east",
			store: roleRefMenuCandidateDS,
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
		    columns: this.createColumns()
		});	
	},

	createColumns : function(){
		var me = this;
		return [
			{xtype: "rownumberer"},
			{
				text: me.refMenuEastIconText,
				width: 55,
				menuDisabled: true,
				xtype: "actioncolumn",
				tooltip: me.refMenuEastIconTooltip,
				align: "center",
				getClass: function(v, m, r){
					return r.get("icon");           	
				}
	        },
		 	{text: me.refMenuEastNameText, dataIndex: "name", flex: 3},
		 	{text: me.refMenuEastLeafText, dataIndex: "isLeaf", flex: 3, renderer: function(value) {
	            return value == 0 ? me.refMenuEastLeafTextDir : me.refMenuEastLeafTextLeaf;
	        }},
		 	{
				text: me.refMenuWestText,
				width: 115,
				menuDisabled: true,
				xtype: "actioncolumn",
				tooltip: me.refMenuEastActionTooltip,
				align: "center",
				handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
					var selection = Ext.getCmp("RoleReadMainGridID").getSelectionModel().getSelection()[0];
					Ext.Ajax.request({
						url: "../maintenance/role/addMenuRefRole.json",
						method: "POST",
						jsonData: {
					        roleId: selection.get("id"),
					        menuId: record.get("id")
					    },
						loadMask: true,
						loadMaskEl: Ext.getCmp("RefMenuCandidateGridID").getEl(),
						loadMaskMsg: Msg.App.updating,	
						success: function(response){ 
							Pub.Notification.showNotification(Pub.Notification.INFO, Msg.Prompt.updateSuccess, "br");

							roleRefMenuCandidateDS.reload();
							if(roleRefMenuCandidateDS.getCount() == 0){
								Ext.getCmp("RefMenuCandidateGridID").hide();
							}
							roleRefMenuSelectedDS.load({node: roleRefMenuSelectedDS.getNodeById(record.get("parentId"))});
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