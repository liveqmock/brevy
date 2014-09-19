/**
 * @description 时间验证
 * @author caobin
 */
var timeTest = /^([1-9]|1[0-9]):([0-5][0-9])(\s[a|p]m)$/i;
Ext.apply(Ext.form.field.VTypes, {
    //  vtype validation function
    time: function(val, field) {
        return timeTest.test(val);
    },
    // vtype Text property: The error text to display when the validation function returns false
    timeText: 'Not a valid time.  Must be in the format "12:34 PM".',
    // vtype Mask property: The keystroke filter mask
    timeMask: /[\d\s:amp]/i
});

/**
 * @description 通配符验证
 * @author caobin
 */
var wildcardTest = /^(?:[a-z]|[0-9]|[\+\*\-\/\.\?_\#])*$/i
Ext.apply(Ext.form.field.VTypes, {
	wildcard: function(val, field){
		return wildcardTest.test(val);
	},
	wildcardText: 'Not a valid wildcard.',
	wildcardMask: /(?:[a-z]|[0-9]|[\+\*\-\/\.\?_\#])/i
});


