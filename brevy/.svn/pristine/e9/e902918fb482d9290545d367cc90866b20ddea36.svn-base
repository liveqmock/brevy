/**
 * @description 带图标的文本域
 * @author caobin
 */
Ext.define("Ext.ux.IconTextfield", {
	extend : "Ext.form.field.Trigger",
	alias : "widget.icontextfield",
	onRender : function(c, p) {	
		this.callParent(arguments);
		var triggerDiv = this.bodyEl.down("div");
		triggerDiv.applyStyles({	
			cursor: "default",
			overflow: "hidden",
			background: "url(".concat(this.iconUrl,")"),
			backgroundRepeat: "no-repeat",
			"height": (this.iconHeight || "16") + "px",
			"width": (this.iconWidth || "16") + "px",	
			"marginTop": (this.iconMarginTop || "3") + "px",
			"marginLeft": (this.iconMarginLeft || "3") + "px"
		});
		//移除所有在此div上的事件
		triggerDiv.removeAllListeners();
		//icon对齐方式
		this.iconAlign = this.iconAlign || "left";
		if(this.iconAlign == "left" || this.iconAlign != "right"){
			var first = this.bodyEl.down("input").up("td");
			var last = triggerDiv.up("td");
			last.insertBefore(first);
		}
	}
});