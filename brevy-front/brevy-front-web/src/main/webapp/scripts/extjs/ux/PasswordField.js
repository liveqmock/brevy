/**
 * @description 密码域
 * @author caobin
 */
Ext.define("Ext.ux.form.PasswordField", {
	extend : "Ext.form.field.Text",
	alias : "widget.uxpasswordfield",
	
	CapsLockText : "Caps Lock is On",
	
	initComponent : function(){
		Ext.apply(this, {
			inputType : "password",
			//混淆键盘布局
			confound : true
		});
		if(this.enableCapsLockWarning){
			Ext.apply(this, {
				enableKeyEvents : true
			});
		}
		this.callParent(arguments);
	},
	
	onRender : function() {			
		this.callParent(arguments);
		
		if(this.showVirtualKeyboard){
			this._showVirtualKeyboard();
		}
		
		if(this.enableCapsLockWarning){
			this._enableCapsLockWarning();
		}
    },
    
    //显示虚拟键盘
    _showVirtualKeyboard : function(){
     	this.bodyEl.down("input").applyStyles({
	    	float : "left",
	    	maxWidth : "90%"
	    });	
	    
        this.imageEl = this.bodyEl.createChild({ tag: "img", src: "resources/extjs/images/ux/passwordfield/keyboard.png"});
        this.imageEl.applyStyles({
        	width : "16px",
        	height :"16px",
        	verticalAlign : "middle",
        	cursor : "pointer",
        	marginTop : "2px",
        	float : "right"
        });
        
		if(Ext.form.Field.prototype.msgTarget == "under"){
			this.imageEl.insertBefore(this.bodyEl.down("div"));
      		//fix position of error message
      		this.bodyEl.down("div").applyStyles({marginTop:"23px"});
		}
		
		Ext.create("Ext.ux.VirtualKeyboard", {confound: this.confound}).init(this, this.imageEl);
    },
    
    //显示大写提示
    _enableCapsLockWarning : function(){
    	this.on("afterrender", function(){
			this.capsWarningTooltip = new Ext.create("Ext.tip.ToolTip", {
				target: this.getInputId(),
				anchor: "top",  
			    dismissDelay: 0,
			    //约束到控件
			    constrainTo: this.getId(),
			    //调整约束到控件时的位置
			    constraintInsets: "26",
			    width: 212,
			    html: '<div class="ux-passwordfield-warning">'+ this.CapsLockText +'</div><br />', 
			 		listeners : {
				 		beforeshow: function(c, eo){
				 			//通过判断自定义事件，屏蔽mouserover事件触发
				 			return c.parentTriggerEvent == "keypress";
				 		}
				 	}
			 	});	
		});
		
		this.on("keypress", function(field, e){
			var charCode = e.getCharCode();
			if((e.shiftKey && charCode >= 97 && charCode <= 122) ||
			    (!e.shiftKey && charCode >= 65 && charCode <= 90)) {
			   	field.capsWarningTooltip.parentTriggerEvent = "keypress";
			    field.capsWarningTooltip.show();
			}
			else {
			    if(field.capsWarningTooltip.hidden == false) {
			        field.capsWarningTooltip.hide();
			        field.capsWarningTooltip.parentTriggerEvent = null;
			    }
			}	
		}, this);
    	
		this.on("blur", function(field){
			if(!this.capsWarningTooltip.hidden) {
			    this.capsWarningTooltip.hide();
			}	
		});
    }

});