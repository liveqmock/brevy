/**
  * Ext.ux.GridCombo
  *
  * @class Ext.ux.GridCombo
  * @extends Ext.form.field.ComboBox
  * @author caobin
  * 
  * 
  *		Ext.create('Ext.data.Store', {
  *		    storeId:'simpsonsStore',
  *		    fields:['name', 'email', 'phone'],
  *		    data:{'items':[
  *		        { 'name': 'Lisa',  "email":"lisa@simpsons.com",  "phone":"555-111-1224"  },
  *		        { 'name': 'Bart',  "email":"bart@simpsons.com",  "phone":"555-222-1234" },
  *		        { 'name': 'Homer', "email":"home@simpsons.com",  "phone":"555-222-1244"  },
  *		        { 'name': 'Marge', "email":"marge@simpsons.com", "phone":"555-222-1254"  }
  *		    ]},
  *		    proxy: {
  *		        type: 'memory',
  *		        reader: {
  *		            type: 'json',
  *		            root: 'items'
  *		        }
  *		    }
  *		});
  *		
  *		var icon = {
  *			fieldLabel: "菜单图标",
  *			flex: 1, 
  *			newLine : true,
  *			xtype: "gridcombo",
  *			maxWidth: 560,
  *			mainField: "email",
  *		    gridConfig : {
  *		    	xtype: "grid",
  *		    	viewConfig: {
  *			        stripeRows: true
  *			    },	    
  *			    store: Ext.data.StoreManager.lookup('simpsonsStore'),
  *			    columns: [
  *			        { text: 'Name',  dataIndex: 'name' },
  *			        { text: 'Email', dataIndex: 'email', flex: 1 },
  *			        { text: 'Phone', dataIndex: 'phone' }
  *			    ]
  *			}
  *		}
  */
Ext.define("GridComboBox", {
    extend: "Ext.form.field.ComboBox",
    alias:'widget.gridcombo',
    
    initComponent: function(){
    	Ext.apply(this, {
            editable: false,
           	queryMode: "local",
            select: Ext.emptyFn,
            triggerAction: "all"
        });
        
    	this.displayField = this.displayField || "text",
        this.gridid = Ext.String.format("grid-combobox-{0}", Ext.id());
        this.tpl = Ext.String.format("<div id='{0}'></div>", this.gridid);
		this.store = this.gridConfig.store;
		
        this.grid = Ext.create("Ext.grid.Panel", this.gridConfig);
        
        this.grid.on("itemclick", function(g,r,i,idx,e,eo){
        	this.setValue(r.get(this.mainField));
        	this.collapse();
        }, this);
        
    	this.on("expand", function () {
            if (!this.grid.rendered) {
                this.grid.render(this.gridid);
            }
        }, this); 
        
        this.callParent(arguments);  
    }
});