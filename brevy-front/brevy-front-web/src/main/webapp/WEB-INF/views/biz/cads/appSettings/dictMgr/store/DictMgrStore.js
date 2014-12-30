//dictMgr model
Ext.define("dictMgrModel", {
	extend: "Ext.data.Model",
    fields: [
    	{name: "id", mapping: "id"},
	    {name: "name", mapping: "name"},  
	    {name: "code", mapping: "code"},
	    {name: "desc", mapping: "desc"}
    ]
});


var dictMgrDS = Ext.create("Ext.data.Store", {
	model: dictMgrModel,
	autoLoad: true,	
	proxy: {
        type: "jsonpaging",
        url: "../biz/cads/appSettings/dictMgr/getDictList.json",
        reader: {
            type: "json",
            root: "content",
            totalProperty: "totalElements"
        }
    },
    pageSize: 20
});
