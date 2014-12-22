/**
 * @module 用户组维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.userGroup.UserGroupUI", {
	extend : "App.Module",
	
	gridID : "UserGroupReadMainGridID",
	
	init : function(){
		
		var refRoleTab = this.createSimpleInstance("App.systemMGR.authCFG.userGroup.refRole.RefRole", this);
		
		this.addRefRoleTabActivedListener(refRoleTab);

		return Ext.create("Ext.tab.Panel", {
			items: [
				this.createSimpleInstance("App.systemMGR.authCFG.userGroup.crud.UserGroupRead", this),
				refRoleTab
			]
			
		});
	},
	
	//添加用户组关联角色页签（激活）监听
	addRefRoleTabActivedListener : function(panel){
		var gridID = this.gridID;
		var isReActivate = false;
		var userGroupText;
		panel.on({
			beforeactivate: function(c){			
				if(Ext.getCmp(gridID).getSelectionModel().hasSelection()){
					if(Ext.getCmp(gridID).getSelectionModel().getCount() == 1){
						Ext.getCmp("RefRoleWestPanelID").getRootNode().expand();
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
				var newUserGroupText = Ext.String.format(this.currentRole, r.get("name"), r.get("code"));
				Ext.getCmp("RefRoleNorthPanelID").update(newRoleText);
				if(isReActivate && roleText != newRoleText){
					userGroupRefRoleSelectedDS.load();
				}else{
					isReActivate = true;			
				}
				userGroupText = newUserGroupText;
				
			},			
			scope: this
		})
	}
});