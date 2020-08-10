var common = {};
/**
 * 对象非空判断
 * @param obj
 * @returns {Boolean}
 */
common.isNotNull = function(obj){
	if('undefined' == obj || '' == obj || obj.length <= 0){
		return false;
	}
	return true;
}

/**
 * 表单重置
 * @param formId 表单Id
 */
common.resetForm = function(formId){
	if(common.isNotNull($("#"+formId))){
		//hidden
		$("#"+formId).find("input[type='hidden']").val("");
		// text 
		$("#"+formId).find(":text").val("");
		// password
		$("#"+formId).find(":password").val("");
	}else{
		console.error("common.restForm方法参数错误！");
	}
}
/*

<input type="checkbox">
<input type="file">
<input type="radio">*/

common.tabCheckClick = function(obj){
	var chs = $(obj).parents("table").find("tbody>tr>td>input[type='checkbox']");
	$(chs).each(function(i,temp){
		$(temp).prop("checked", $(obj).prop("checked"));
	});
}







