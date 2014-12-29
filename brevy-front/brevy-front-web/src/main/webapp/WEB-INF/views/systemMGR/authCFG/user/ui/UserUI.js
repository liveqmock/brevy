/**
 * @module 用户维护
 * @author caobin
 */
Ext.define("App.systemMGR.authCFG.user.UserUI", {
	extend : "App.Module",
	
	gridID : "UserReadMainGridID",
	
	init : function(){
		
		var refAppTab = this.createSimpleInstance("App.systemMGR.authCFG.user.refApp.RefApp", this);
		var refGroupTab = this.createSimpleInstance("App.systemMGR.authCFG.user.refGroup.RefGroup", this);
		var refRoleTab = this.createSimpleInstance("App.systemMGR.authCFG.user.refRole.RefRole", this);
		
		this.addRefAppTabActivedListener(refAppTab);
		this.addRefGroupTabActivedListener(refGroupTab);
		this.addRefRoleTabActivedListener(refRoleTab);

		return Ext.create("Ext.tab.Panel", {
			items: [
				this.createSimpleInstance("App.systemMGR.authCFG.user.crud.UserRead", this),
				refGroupTab,
				refRoleTab,
				refAppTab
			]
			
		});
	},
	
	//添加用户关联组页签（激活）监听
	addRefGroupTabActivedListener : function(panel){
		var gridID = this.gridID;
		var groupText;
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
				var newGroupText = Ext.String.format(this.currentUser, r.get("name"), r.get("code"));
				Ext.getCmp("RefGroupNorthPanelID").update(newGroupText);
				if(groupText != newGroupText){
					userRefGroupSelectedDS.getProxy().extraParams = {
						userId: r.get("id")
					};
					userRefGroupCandidateDS.getProxy().extraParams = {
						userId: r.get("id")
					};
					userRefGroupSelectedDS.load();
					userRefGroupCandidateDS.load();
				}
				groupText = newGroupText;				
			},			
			scope: this
		})
	},
	
	//添加用户关联角色页签（激活）监听
	addRefRoleTabActivedListener : function(panel){
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
				var newRoleText = Ext.String.format(this.currentUser, r.get("name"), r.get("code"));
				Ext.getCmp("RefRoleNorthPanelID").update(newRoleText);
				if(roleText != newRoleText){
					userRefRoleSelectedDS.getProxy().extraParams = {
						userId: r.get("id")
					};
					userRefRoleCandidateDS.getProxy().extraParams = {
						userId: r.get("id")
					};
					userRefRoleSelectedDS.load();
					userRefRoleCandidateDS.load();
				}
				roleText = newRoleText;				
			},			
			scope: this
		})
	},
	
	//添加用户关联应用系统页签（激活）监听
	addRefAppTabActivedListener : function(panel){
		var gridID = this.gridID;
		var appText;
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
				var newAppText = Ext.String.format(this.currentUser, r.get("chName"), r.get("username"));
				Ext.getCmp("RefAppNorthPanelID").update(newAppText);
				if(appText != newAppText){
					userRefAppSelectedDS.getProxy().extraParams = {
						userId: r.get("id")
					};
					userRefAppCandidateDS.getProxy().extraParams = {
						userId: r.get("id")
					};
					userRefAppSelectedDS.load();
					userRefAppCandidateDS.load();
				}
				appText = newAppText;				
			},			
			scope: this
		})
	}
});