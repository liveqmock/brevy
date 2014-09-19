/**
 * @description 验证码域(异步)
 * @author caobin
 */
Ext.define("Ext.ux.form.CaptchaField", {
	extend : "Ext.form.field.Text",
	alias : "widget.captchafield",
	
	onRender : function(c, p) {	
		this.callParent(arguments);

	    this.bodyEl.down("input").applyStyles({
	    	float : "left",
	    	maxWidth : "55%"
	    });
	    
        this.imageEl = this.bodyEl.createChild({tag: "img", src: ""});
        
		if(Ext.form.Field.prototype.msgTarget == "under"){
			this.imageEl.insertBefore(this.bodyEl.down("div"));
      		//fix position of error message
      		this.bodyEl.down("div").applyStyles({marginTop:"23px"});
		}

       	this.imageEl.on("click", this.loadImage, this);         
       	this.loadImage();
    },
    
    //load/reload code image
    loadImage: function() {
    	this.imageEl.un("click", this.loadImage, this);
    	var me = this;
    	me.imageEl.set({src: "resources/extjs/themes/images/gray/grid/grid-loading.gif"});
        this.imageEl.applyStyles({
        	marginTop : "3px",
        	marginLeft : "5px",
        	width : "16px",
			height :"16px",
        	verticalAlign : "middle",
        	float : "left"
        });
       
    	Ext.Ajax.request({
    		binary : true,
    		url : this.imageUrl,
    		success : function(r){
    			var imageSrc = "data:image/png;base64," + btoa(String.fromCharCode.apply(null, r.responseBytes));
    			me.imageEl.set({ 
		      		src: imageSrc
		      	});
		      	me.imageEl.applyStyles({
			    	marginTop : "0px",
			    	marginLeft : "0px",
			    	width : "42%",
			    	height :"22px",
			    	float : "right"
			    });
			    me.imageEl.on("click", me.loadImage, me);           	  
    		}
    	});
    }
});