//application model/
Ext.define("appModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
	    {name: "name", mapping: "name"},  
	    {name: "code", mapping: "code"},
	    {name: "status", mapping: "status"},
	    {name: "desc", mapping: "desc"}
    ]
});



var appDS = Ext.create("Ext.data.Store", {
	model: appModel,
	autoLoad: false,	
	proxy: {
        type: "jsonpaging",
        url: "../maintenance/app/getAppList.json",
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

