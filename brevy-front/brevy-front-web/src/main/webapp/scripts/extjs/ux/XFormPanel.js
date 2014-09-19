/**
 * @description 增强表单(Formlayout + HBoxlayout)
 * @author caobin
 */
Ext.define("Ext.ux.XFormPanel", {
	extend : "Ext.form.Panel",
	alias : "widget.xform",
	
 	initComponent: function () {
 		Ext.apply(this, {
            fieldDefaults: {   
            	labelAlign: this.labelAlign || "right",
                labelWidth: this.labelWidth || 90,
                msgTarget: this.msgTarget || "side",
                //关闭自适应提示错误位置
				autoFitErrors: false
            },
            items: this.getItemsLayout()
        });
       
        this.callParent();
    },
    
    //获取ITEMS的布局
    getItemsLayout: function(){
    	var o = [];
    	var row = [];
    	Ext.each(this.items, function(item){	
    		item.maxWidth = item.maxWidth || 1280;
		    if(item.newLine){
		    	var fcCFG = this.getFieldContainer();
		    	fcCFG.items = row;
		    	o.push(fcCFG);
		    	row = [];
		    }
		    row.push(item);
    	}, this);
    	if(row.length > 0){
    		var fcCFG = this.getFieldContainer();
    		fcCFG.items = row;
    		o.push(fcCFG);
    	}
    	if(this.fieldSetConfig){
    		o = {
		        xtype: "fieldset",
		        margin: "10 10 0 10",
				padding: "0 0 10 10",
		        items: o
	    	}
	    	Ext.applyIf(o, this.fieldSetConfig);
	    }
    	return o;
    },
    
    getFieldContainer: function(){
    	return {
		    xtype: "fieldcontainer",
		    layout: "hbox",
		    style: {marginTop: "6px;"},
		    items: []
        }
    }

});