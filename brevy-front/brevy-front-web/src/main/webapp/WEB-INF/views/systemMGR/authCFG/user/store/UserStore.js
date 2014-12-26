//user model/
Ext.define("userModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
	    {name: "username", mapping: "username"},  
	    {name: "password", mapping: "password"},  
	    {name: "chName", mapping: "chName"},  
	    {name: "deptId", mapping: "deptId"},
	    {name: "positionId", mapping: "positionId"},
	    {name: "dept", mapping: "dept"},
	    {name: "position", mapping: "position"},
	    {name: "status", mapping: "status"}
    ]
});

//user dict model
Ext.define("userDictModel", {
	extend: "Ext.data.Model",
   	fields: ["id", "name"]
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




var userDictPositionDS = Ext.create("Ext.data.Store", {
	model: userDictModel,
	autoLoad: false,	
	proxy: {
	    type: "jsonajax",
	    url: _ctxPath + "/maintenance/user/getDictStore.json",
	    extraParams: {
	    	dictId: 1	    
	    },
	    reader: {
	        type: "json"
	    }
	}
});

var userDictDeptDS = Ext.create("Ext.data.Store", {
	model: userDictModel,
	autoLoad: false,	
	proxy: {
	    type: "jsonajax",
	    url: _ctxPath + "/maintenance/user/getDictStore.json",
	    extraParams: {
	    	dictId: 2	    
	    },
	    reader: {
	        type: "json"
	    }
	}
});


