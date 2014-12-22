/**
 * @module 角色维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.role.RoleUI", {
	extend : "App.Module",
	
	gridID : "RoleReadMainGridID",
	
	init : function(){
		
		var refMenuTab = this.createSimpleInstance("App.systemMGR.authCFG.role.refMenu.RefMenu", this);
		var refAccessAuthTab = this.createSimpleInstance("App.systemMGR.authCFG.role.refAccessAuth.RefAccessAuth", this);
		var refOperAuthTab = this.createSimpleInstance("App.systemMGR.authCFG.role.refOperAuth.RefOperAuth", this);
		
		this.addRefMenuTabActivedListener(refMenuTab);
		this.addRefAccessAuthTabActivedListener(refAccessAuthTab);
		this.addRefOperAuthTabActivedListener(refOperAuthTab);

		return Ext.create("Ext.tab.Panel", {
			items: [
				this.createSimpleInstance("App.systemMGR.authCFG.role.crud.RoleRead", this),
				refMenuTab,
				refAccessAuthTab,
				refOperAuthTab
			]
			
		});
	},
	
	//添加角色关联菜单页签（激活）监听
	addRefMenuTabActivedListener : function(panel){
		var gridID = this.gridID;
		var isReActivate = false;
		var roleText;
		panel.on({
			beforeactivate: function(c){			
				if(Ext.getCmp(gridID).getSelectionModel().hasSelection()){
					if(Ext.getCmp(gridID).getSelectionModel().getCount() == 1){
						Ext.getCmp("RefMenuWestPanelID").getRootNode().expand();
						return true;
					}else{
						Pub.MsgBox.showMsgBox(Pub.MsgBox.WARN, Msg.Prompt.warnCheck);
						return false;
					}	
						
				}
				Pub.MsgBox.showMsgBox(Pub.MsgBox.WARN, Msg.Prompt.warnChecks);
				return false;
			},
			
			activate: function(c){
				var r = Ext.getCmp(gridID).getSelectionModel().getSelection()[0];
				var newRoleText = Ext.String.format(this.currentRole, r.get("name"), r.get("code"));
				Ext.getCmp("RefMenuNorthPanelID").update(newRoleText);
				if(isReActivate && roleText != newRoleText){
					roleRefMenuSelectedDS.load();
				}else{
					isReActivate = true;			
				}
				roleText = newRoleText;
				
			},			
			scope: this
		})
	},
	
	//添加角色关联访问权限页签（激活）监听
	addRefAccessAuthTabActivedListener : function(panel){
		var gridID = this.gridID;
		var roleText;
		panel.on({
			beforeactivate: function(c){
				if(Ext.getCmp(gridID).getSelectionModel().hasSelection()){
					if(Ext.getCmp(gridID).getSelectionModel().getCount() != 1){
						Pub.MsgBox.showMsgBox(Pub.MsgBox.WARN, Msg.Prompt.warnCheck);
						return false;
					}else{
						return true;
					}
				}
				Pub.MsgBox.showMsgBox(Pub.MsgBox.WARN, Msg.Prompt.warnCheck);
				return false;
			},
			
			activate: function(c){
				var r = Ext.getCmp(gridID).getSelectionModel().getSelection()[0];
				var newRoleText = Ext.String.format(this.currentRole, r.get("name"), r.get("code"));
				Ext.getCmp("RefAccessAuthNorthPanelID").update(newRoleText);
				if(roleText != newRoleText){
					roleRefAccessAuthSelectedDS.getProxy().extraParams = {
						roleId: r.get("id")
					};
					roleRefAccessAuthCandidateDS.getProxy().extraParams = {
						appId: r.get("appId"),
						roleId: r.get("id")
					};
					roleRefAccessAuthSelectedDS.load();
					roleRefAccessAuthCandidateDS.load();
				}
				roleText = newRoleText;				
			},			
			scope: this
		})
	},
	
	//添加角色关联操作权限页签（激活）监听
	addRefOperAuthTabActivedListener : function(panel){
		var gridID = this.gridID;
		var roleText;
		panel.on({
			beforeactivate: function(c){
				if(Ext.getCmp(gridID).getSelectionModel().hasSelection()){
					if(Ext.getCmp(gridID).getSelectionModel().getCount() != 1){
						Pub.MsgBox.showMsgBox(Pub.MsgBox.WARN, Msg.Prompt.warnCheck);
						return false;
					}else{
						return true;
					}
				}
				Pub.MsgBox.showMsgBox(Pub.MsgBox.WARN, Msg.Prompt.warnCheck);
				return false;
			},
			
			activate: function(c){
				var r = Ext.getCmp(gridID).getSelectionModel().getSelection()[0];
				var newRoleText = Ext.String.format(this.currentRole, r.get("name"), r.get("code"));
				Ext.getCmp("RefOperAuthNorthPanelID").update(newRoleText);
				if(roleText != newRoleText){
					roleRefOperAuthSelectedDS.getProxy().extraParams = {
						roleId: r.get("id")
					};
					roleRefOperAuthCandidateDS.getProxy().extraParams = {
						appId: r.get("appId"),
						roleId: r.get("id")
					};
					roleRefOperAuthSelectedDS.load();
					roleRefOperAuthCandidateDS.load();
				}
				roleText = newRoleText;				
			},			
			scope: this
		})
	}
});