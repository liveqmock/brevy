/**
 * @description 验证码域
 * @author caobin
 */
Ext.define("Ext.ux.form.CaptchaField", {
	extend : "Ext.form.field.Text",
	alias : "widget.captchafield",
	
	onRender : function(c, p) {	
		this.callParent(arguments);
		
		Ext.apply(this, {
	       	imageUrl : this.imageUrl
	    });

	    
	    this.bodyEl.down("input").applyStyles({
	    	float : "left",
	    	maxWidth : Ext.isIE ? "49%" : "55%"
	    });
	    
        this.imageEl = this.bodyEl.createChild({ tag: "img", src: ""});
        this.imageEl.applyStyles({
        	width : "42%",
        	height :"23px",
        	verticalAlign : "middle",
        	float : "right"
        });
        
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
        this.imageEl.set({ 
      		src: this.imageUrl.concat(this.imageUrl.indexOf("?") == -1 ? "?" : "&", "_rc=", Pub.utils.IdentityUtils.getUUID(false))
      	});
    }
});