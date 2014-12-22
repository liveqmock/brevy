/** 用户组关联角色部分DS **/
Ext.define("UserGroupRefRoleModel", {
    extend: "Ext.data.Model",
    fields:  [
    	"appId",
    	"id", 
    	"name", 
    	"code" 
    ]
}); 
var userGroupRefRoleSelectedDS = Ext.create("Ext.data.Store", {
	model: UserGroupRefRoleModel,
	autoLoad: false,	
	proxy: {
        type: "jsonpaging",
        url: "../maintenance/userGroup/getRefUserGroupRole.json",
        reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20
});



var userGroupRefRoleCandidateDS = Ext.create("Ext.data.Store", {
	model: UserGroupRefRoleModel,
	autoLoad: false,	
	proxy: {
        type: "jsonpaging",
        url: "../maintenance/userGroup/getCandidateRole.json",
       	reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20,
    listeners : {
    	load : function(store, rs){
    		if(rs.length == 0){
    			Ext.getCmp("RefRoleCandidateGridID").hide();  
    		}else{
    			Ext.getCmp("RefRoleCandidateGridID").show();  
    		}
    	}
    }
});