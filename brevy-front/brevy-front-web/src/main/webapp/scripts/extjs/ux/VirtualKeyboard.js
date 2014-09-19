/**
 * @author: Mask (modify by caobin)
 * @desc: VirtualKeyboard base on ExtJs4
 */
Ext.define('Ext.ux.VirtualKeyboard', {
	extend: 'Ext.tip.ToolTip',
	alias: 'widget.virtualkeyboard',
 	minWidth: 332,
 	maxWidth: 332,
	height: 114,
	//是否混淆键盘布局
	confound: true,
	autoHide: false,
	ClearText: "Clear",
	ShiftText: "Shift",
	CapsLockText: "Caps Lock",

	chars : {"`":"`", "1":"!", "2":"@", "3":"#", "4":"$", "5":"%", "6":"^", "7":"&", "8":"*", "9":"(", "0":")",
 		"-":"_", "=":"+", ",":"< ", ".":">", "/":"?", ";":":", "\"":"\"","[":"{", "]":"}", "\\":"|", "←":"←"},
	

	posC: ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'],
	posN: ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'],
	keyboard: null,
	showEvent: 'click',
	init: function(field, imageEl) {
		this.chars[this.ShiftText] = this.ShiftText;
 		this.chars[this.CapsLockText] = this.CapsLockText;
 		this.chars[this.ClearText] = this.ClearText;
		var me = this;
		if(!Ext.isIE) {
 			me.autoWidth = true;
 		}
		Ext.suspendLayouts();
		me.createKeyboard();
		me.add(me.keyboard);
		Ext.resumeLayouts(true);
		me.setKeyHandler(field, imageEl);
		me.mon(me, 'beforeshow', me.bulidConfound, me);
	},
	createKeyboard: function() {
		this.keyboard = Ext.create('Ext.container.Container', {
 			layout: 'absolute',
 			defaults: {
 				width: 22
 			},
 			defaultType: 'button',
 			items: [
				{ text: "1", x: 2, y: 3 }, { text: "2", x: 25, y: 3 }, { text: "3", x: 48, y: 3 },
				{ text: "4", x: 71, y: 3 }, { text: "5", x: 94, y: 3 }, { text: "6", x: 117, y: 3 },
				{ text: "7", x: 140, y: 3 }, { text: "8", x: 163, y: 3 }, { text: "9", x: 186, y: 3 },
				{ text: "0", x: 209, y: 3 }, { text: "a", x: 68, y: 28 }, { text: "c", x: 114, y: 28 },
				{ text: "b", x: 91, y: 28}, { text: "d", x: 137, y: 28 }, { text: "e", x: 160, y: 28 },
				{ text: "g", x: 206, y: 28 }, { text: "f", x: 183, y: 28 }, { text: "i", x: 252, y: 28 },
				{ text: "j", x: 275, y: 28 }, { text: "h", x: 229, y: 28 }, { text: "k", x: 298, y: 28 },
				{ text: "l", x: 68, y: 53 }, { text: "p", x: 160, y: 53 }, { text: "m", x: 91, y: 53 },
				{ text: "n", x: 114, y: 53 }, { text: "o", x: 137, y: 53 }, { text: "r", x: 206, y: 53 },
				{ text: "s", x: 229, y: 53 }, { text: "t", x: 252, y: 53 }, { text: "q", x: 183, y: 53 },
				{ text: "u", x: 275, y: 53 }, { text: "v", x: 298, y: 53 }, { text: "w", x: 68, y: 78 },
				{ text: "x", x: 45, y: 78 }, { text: "z", x: 114, y: 78 }, { text: "y", x: 91, y: 78 },
				{ text: "=", x: 255, y: 3 }, { text: "-", x: 232, y: 3 }, { text: "`", x: 137, y: 78 },
				{ text: "[", x: 252, y: 78 }, { text: "]", x: 275, y: 78 }, { text: ";", x: 160, y: 78 },
				{ text: "'", x: 183, y: 78 }, { text: ",", x: 206, y: 78 }, { text: ".", x: 229, y: 78 },
				{ text: "/", x: 298, y: 78 }, { text: "←", width: 40, x: 278, y: 3 },
				{ text: this.ClearText, width: 40, x: 2, y: 78 },
				{ text: this.ShiftText, itemId: 'shiftBtn', enableToggle: true, width: 63, x: 2, y: 53 },
				{ text: this.CapsLockText, itemId: 'capsLockBtn', enableToggle: true, width: 63 ,x: 2, y: 28 }
			]
 		});
	},
	setKeyHandler: function(field, imageEl) {
		var me = this,
			btns = me.keyboard.items,
			btn, i, count;
		for(i = 0, count = btns.length; i < count; i++) {
			btns.get(i).on('click', function(btn, e) {
				switch(btn.text) {
 					case this.CapsLockText:
 						me.onClickCapsLock(field);
 						break;
 					case this.ShiftText:
 						me.onClickShift(field);
 						break;
 					case this.ClearText:
 						me.onClickClean(field);
 						break;
 					case '←':
 						me.onClickBackspace(field);
 						break;
 					default:
 						me.onClickChar(btn, field);
				}
			}, this);
		}
		
 		imageEl.on(me.showEvent, function() {
			var inputEl = field.inputEl,
				inputHeight = inputEl.getHeight(),
				x = inputEl.getX(),
				y = inputEl.getY() + inputHeight + 2,
				posArr = [x, y];
			
			if(me.isHidden()) {
				me.showAt(posArr);
			}
 		}, me);
	},
	onClickCapsLock: function(field) {
		var me = this,
			capsLockBtn = me.down('container').child('#capsLockBtn'),
			isPressed = capsLockBtn.pressed,
			regex = /^[a-z]{1}$/i,
			btns = me.keyboard.items,
			btn, i, count, text;
		for(i = 0, count = btns.length; i < count; i++) {
			btn = btns.get(i);
			text = btn.text;
			if(text.match(regex)) {
				btn.setText(isPressed ? text.toUpperCase() : text.toLowerCase());
			}
		}
	},
	onClickShift: function(field) {
		var me = this,
			shiftBtn = me.down('container').child('#shiftBtn'),
			isPressed = shiftBtn.pressed,
			regex = /^[a-z]{1}$/i,
			btns = me.keyboard.items,
			btn, i, count, text, temp;
		for(i = 0, count = btns.length; i < count; i++) {
			btn = btns.get(i);
			text = btn.text;
			if(text.match(regex)) {
				btn.setText(isPressed ? text.toUpperCase() : text.toLowerCase());
			} else {
				temp = me.chars[text];
				btn.setText(temp);
				delete me.chars[text];
				me.chars[temp] = text;
			}
		}
	},
	onClickClean: function(field) {
		field.setValue('');
	},
	onClickBackspace: function(field) {
		var oldValue = field.getValue(),
			newValue;
		newValue = oldValue.substring(0, oldValue.length - 1);
		field.setValue(newValue);
 	},
	onClickChar: function(btn, field) {
		field.setValue(field.getValue() + btn.text);
	},
	bulidConfound: function() {
		var me = this;
		if(!me.confound) {
			return;
		} else {
			var posC_ = me.posC,
				posN_ = me.posN,
				regexC = /^[a-zA-Z]{1}$/,
				regexN = /^[0-9]{1}$/,
				btns = me.keyboard.items,
				btn, i, len, index;
			for(i = 0, len = btns.length; i < len; i++) {
				btn = btns.get(i);
				if((btn.text.length == 1) && btn.text.match(regexC)) {
					index = me.getRandomNum(0, me.posC.length - 1);
					btn.setText(me.posC[index]);
					me.posC = me.delArrayEl(me.posC, index);
				}
				if((btn.text.length == 1) && btn.text.match(regexN)) {
					index = me.getRandomNum(0, me.posN.length - 1);
					btn.setText(me.posN[index]);
					me.posN = me.delArrayEl(me.posN, index);
				}
			}
			me.posC = posC_;
			me.posN = posN_;
		}
	},
	getRandomNum: function(start, end) {
		return Math.floor(Math.random()*(end - start + 1) + start);
	},
    delArrayEl: function(arr, index) {
        if(index < 0) {
            return arr;
		}
        return arr.slice(0, index).concat(arr.slice(index + 1, arr.length));  
    }
});