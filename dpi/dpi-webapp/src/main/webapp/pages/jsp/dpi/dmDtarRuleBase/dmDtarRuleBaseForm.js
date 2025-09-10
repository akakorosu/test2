$(function(){
	topsearchInput.init({
		inputId : "tarBaseClass",
		url : $.cxt + "/dmdtarrulebase/selectlist",
		showName : "tarBaseClass",
		likeName : "likeTarBaseClass",
		aClick : function(o) {
			$("#tarBaseClass").val(o.tarBaseClass);
		},
	})
})