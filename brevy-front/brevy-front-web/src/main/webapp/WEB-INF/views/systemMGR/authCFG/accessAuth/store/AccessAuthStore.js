//access auth model/
Ext.define("accessAuthModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
    	{name: "appId", mapping: "appId"},
	    {name: "name", mapping: "name"},  
	    {name: "code", mapping: "code"},
	    {name: "urlPattern", mapping: "urlPattern"},
	    {name: "authenFilter", mapping: "authenticationFilter"},
	    {name: "authorFilter", mapping: "authorizationFilter"},
	    {name: "status", mapping: "status"},
	    {name: "sort", mapping: "sort"}
    ]
});


//sort_ascend
var accessAuthDS = Ext.create("Ext.data.Store", {
	model: accessAuthModel,
	autoLoad: false,	
	proxy: {
        type: "jsonpaging",
        url: "../maintenance/accessauth/getAccessAuthList.json",
        reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20
});


//authentication filter store
var authenFilterDS = Ext.create("Ext.data.Store", {
    fields: ["name", "text"],
    data : [
        {"name":"anon", "text":Msg.Constants.anonFilter},
        {"name":"auth", "text":Msg.Constants.authenFilter}
    ]
});

//authorization filter store
var authorFilterDS = Ext.create("Ext.data.Store", {
    fields: ["name", "text"],
    data : [
        {"name":"roles", "text":Msg.Constants.authorFilter}
    ]
});

//status store
var statusDS = Ext.create("Ext.data.Store", {
    fields: ["name", "text"],
    data : [
    	{"name":"1", "text":Msg.Constants.valid},
        {"name":"0", "text":Msg.Constants.invalid}
    ]
});

