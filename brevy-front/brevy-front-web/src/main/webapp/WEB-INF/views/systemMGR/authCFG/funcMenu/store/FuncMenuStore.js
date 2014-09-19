Ext.define("treeModel", {
	extend: "Ext.data.Model",
    fields: [
    	"appId",
    	"id", 
    	"parentId", 
    	"hierarchy",
    	"url", 
    	"moduleLocation", 
    	"sort",	
    	"status",
	    {name: "text", mapping: "name", convert: function(v,r){
	    	with(r.data){
	    		return v + "&nbsp;<span class='icon-sort_ascend_mini' style='display:inline-block;width:10px;height:10px;'></span><font color='#008202'>&nbsp;" + sort + "</font>"  
	    	} 	
	    }},  
	    {name: "iconCls", mapping: "icon"}	    
    ]
});


Ext.define("treeComboModel", {
	extend: "Ext.data.Model",
    fields: [
    	"id", 
	    {name: "text", mapping: "name"},  
	    {name: "iconCls", mapping: "icon"}	    
    ]
});


//sort_ascend
var treeDS = Ext.create("Ext.data.TreeStore", {
	model: treeModel,
	proxy: {
        type: "jsonajax",
        url: "../maintenance/getDynamicMenu.json"
    },
    root: {
    	id : -1,
        text: Msg.Constants.rootMenu,
        expanded: true
    }
});


var treeComboDS = Ext.create("Ext.data.TreeStore", {
	model: treeComboModel,
	proxy: {
        type: "jsonajax",
        url: "../maintenance/getDynamicParentMenu.json"
    },
    root: {
    	id : -1,
        text: Msg.Constants.rootMenu,
        expanded: true
    }
});





//menu icon store
var menuIconStore = new Ext.data.SimpleStore({
	fields: ["code", "name"],
    data: Pub.Data.menuIcons()
});

//leaf item stroe
var leafStore = Ext.create("Ext.data.Store", {
    fields: ["code", "name"],
    data : [
        {"code":"1", "name":Msg.Constants.yes},
        {"code":"0", "name":Msg.Constants.no}
    ]
});

//status item stroe
var statusStore = Ext.create("Ext.data.Store", {
    fields: ["code", "name"],
    data : [
        {"code":"1", "name":Msg.Constants.enable},
        {"code":"0", "name":Msg.Constants.disable}
    ]
});


