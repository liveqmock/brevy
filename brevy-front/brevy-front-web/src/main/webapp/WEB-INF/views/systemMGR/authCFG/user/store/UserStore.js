//user model/
Ext.define("userModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
	    {name: "username", mapping: "username"},  
	    {name: "chName", mapping: "chName"},  
	    {name: "dept", mapping: "dept"},
	    {name: "position", mapping: "position"},
	    {name: "status", mapping: "status"}
    ]
});


var userDS = Ext.create("Ext.data.Store", {
	model: userModel,
	autoLoad: true,	
	proxy: {
        type: "jsonpaging",
        url: "../maintenance/user/getUserList.json",
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

