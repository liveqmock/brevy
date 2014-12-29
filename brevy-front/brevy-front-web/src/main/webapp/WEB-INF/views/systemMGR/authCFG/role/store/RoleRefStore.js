/** 角色关联菜单部分DS **/
Ext.define("RoleRefMenuModel", {
    extend: "Ext.data.Model",
    fields:  [
    	"appId",
    	"id", 
    	"parentId", 
    	"hierarchy",
    	"url", 
    	"moduleLocation", 
    	"sort",	
    	"status",
    	{name: "isLeaf", mapping: "leaf"},
	    {name: "name"},  
	    {name: "iconCls", mapping: "icon"}	    
    ]
}); 

var roleRefMenuSelectedDS = new Ext.data.TreeStore({
	autoLoad : false,
	model : RoleRefMenuModel,
	proxy : {
		type : "jsonajax",
		url : "../maintenance/role/getRefRoleDynamicMenu.json"
	},
	root: {
    	id : -1,
    	name : Msg.Constants.relMenu
    },
    listeners : {
    	beforeload : function(store, oper){
    		store.getProxy().extraParams = {roleId: Ext.getCmp("RoleReadMainGridID").getSelectionModel().getSelection()[0].get("id")};
    		Ext.getCmp("RefMenuCandidateGridID").hide();  
    	},
    	//防止切换角色时重复选中而不切出待选项
    	expand : function(node){
    		if(node.get("id") - 0 == -1){
    			Ext.getCmp("RefMenuWestPanelID").getSelectionModel().deselect(node);
    		}
    		Ext.getCmp("RefMenuWestPanelID").getSelectionModel().deselect(node.childNodes);
    	}
    }
});



var roleRefMenuCandidateDS = Ext.create("Ext.data.Store", {
	model: RoleRefMenuModel,
	autoLoad: false,	
	proxy: {
        type: "jsonajax",
        url: "../maintenance/role/getCandidateMenu.json",
        reader: {
            type: "json",
            root: "apMenu"
        }
    }
});


/** 角色关联访问权限部分DS **/
Ext.define("RoleRefAccessAuthModel", {
    extend: "Ext.data.Model",
    fields:  [
    	"appId",
    	"id", 
    	"name", 
    	"code",
    	"urlPattern"   
    ]
}); 
var roleRefAccessAuthSelectedDS = Ext.create("Ext.data.Store", {
	model: RoleRefAccessAuthModel,
	autoLoad: false,	
	proxy: {
        type: "jsonpaging",
        url: "../maintenance/role/getRefRoleAccessAuth.json",
        reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20
});



var roleRefAccessAuthCandidateDS = Ext.create("Ext.data.Store", {
	model: RoleRefAccessAuthModel,
	autoLoad: false,	
	proxy: {
        type: "jsonpaging",
        url: "../maintenance/role/getCandidateAccessAuth.json",
       	reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20,
    listeners : {
    	load : function(store, rs){
    		if(rs.length == 0 && !store.filters.items[0]){
    			Ext.getCmp("RefAccessAuthCandidateGridID").hide();  
    		}else{
    			Ext.getCmp("RefAccessAuthCandidateGridID").show();  
    		}
    	}
    }
});



/** 角色关联操作权限部分DS **/
Ext.define("RoleRefOperAuthModel", {
    extend: "Ext.data.Model",
    fields:  [
    	"appId",
    	"id", 
    	"name", 
    	"code",
    	"authorizedOper"   
    ]
}); 
var roleRefOperAuthSelectedDS = Ext.create("Ext.data.Store", {
	model: RoleRefOperAuthModel,
	autoLoad: false,	
	proxy: {
        type: "jsonpaging",
        url: "../maintenance/role/getRefRoleOperAuth.json",
        reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20
});



var roleRefOperAuthCandidateDS = Ext.create("Ext.data.Store", {
	model: RoleRefOperAuthModel,
	autoLoad: false,	
	proxy: {
        type: "jsonpaging",
        url: "../maintenance/role/getCandidateOperAuth.json",
       	reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20,
    listeners : {
    	load : function(store, rs){
    		if(rs.length == 0 && !store.filters.items[0]){
    			Ext.getCmp("RefOperAuthCandidateGridID").hide();  
    		}else{
    			Ext.getCmp("RefOperAuthCandidateGridID").show();  
    		}
    	}
    }
});
