//role model/
Ext.define("roleModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
    	{name: "appId", mapping: "appId"},
	    {name: "name", mapping: "name"},  
	    {name: "code", mapping: "code"},
	    {name: "desc", mapping: "desc"},
	    {name: "status", mapping: "status"}
    ]
});


//sort_ascend
var roleDS = Ext.create("Ext.data.Store", {
	model: roleModel,
	autoLoad: false,	
	proxy: {
        type: "jsonpaging",
        url: "../maintenance/role/getRoleList.json",
        reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20
});

//status store
var statusDS = Ext.create("Ext.data.Store", {
    fields: ["name", "text"],
    data : [
        {"name":"1", "text":Msg.Constants.valid},
        {"name":"0", "text":Msg.Constants.invalid}
    ]
});

