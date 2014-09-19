//oper auth model/
Ext.define("operAuthModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
    	{name: "appId", mapping: "appId"},
	    {name: "name", mapping: "name"},  
	    {name: "code", mapping: "code"},
	    {name: "authorizedOper", mapping: "authorizedOper"},
	    {name: "status", mapping: "status"},
	    {name: "sort", mapping: "sort"}
    ]
});


//sort_ascend
var operAuthDS = Ext.create("Ext.data.Store", {
	model: operAuthModel,
	autoLoad: false,	
	proxy: {
        type: "jsonpaging",
        url: "../maintenance/operauth/getOperAuthList.json",
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

