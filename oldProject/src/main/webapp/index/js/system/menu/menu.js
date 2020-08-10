var menu = {}; 
var setting = {
	checkable: false,
	showLine: true,
	edit:{
		showRemoveBtn: false,
		showRenameBtn: false,
		enable:true,
		isCopy:false,
		isMove:false,
		prev:false,
		next:false,
		inner:true,
		autoExpandTrigger:true
	},
	callback: {
		onClick:ztreeClick
	}
};
/*ztree节点单击事件*/
function ztreeClick(event, treeId, treeNode){
	$("#menuId").val(treeNode.id);
	$("#Q_menuName").val(treeNode.name);
	
	$("#menuTable").DataTable().draw(false);
}

$(function(){
	//加载树 
	$.ajax({
		type:"post",
		url:"menu/ztreelist.do",
		dataType:"JSON",
		success:function(data){
			if(data.result == "1"){
				var ztreeId = "menuzTree";
				$.fn.zTree.init($("#"+ztreeId), setting, data.data);
				var myTree=$.fn.zTree.getZTreeObj(ztreeId);
				myTree.expandAll(true); 
			}else{
				console.info("初始化树失败！");
			}
		}
		
	});
	/*设置datatables*/
	$("#menuTable").DataTable({
		"order": [ 5, 'desc' ],	//默认排序
		"columns":[
		           {"title": "<input  type='checkbox' id='tabcheckbox' onclick='common.tabCheckClick(this)' />", "data": "1",  "targets": 0,"sWidth":"15px","orderable": false},
	               {"title": "菜单名称", "data": "menuName",  "targets": 1,"sWidth":"100px"},
	               {"title": "父级菜单", "data": "parentName", "targets": 2,"sWidth":"100px"},
	               {"title": "图标", "data": "icon",  "targets": 3,"sWidth":"50px" },
	               {"title": "URL",   "data": "url",    "targets": 4 },
	               {"title": "排序",   "data": "sort",    "targets": 5,"sWidth":"30px"},
	               {"title": "创建时间","targets": 6, "visible": false},
	               {"title": "操作",  "targets": 7,"sWidth":"105px","orderable": false }
		],
		"columnDefs": [{
    			targets: 0,
    			render: function(data, type, row, meta) {
    				return '<input type="checkbox" name="menuIds" value="' + row.menuId + '" />'
			}},{
    			targets: 7,
    			render: function(data, type, row, meta) {
    				var btn = "";
    				if(row.isDisable == 1){
	    				btn += "<button type='button' class='btn btn-primary btn-xs' style='margin-right:5px;'>禁用</button>";
    				}else{
	    				btn += "<button type='button' class='btn btn-primary btn-xs' style='margin-right:5px;'>启用</button>";
    				}
    				btn += "<button type='button' class='btn btn-primary btn-xs' style='margin-right:5px;'>编辑</button>";
    				btn += "<button type='button' class='btn btn-default btn-xs'>删除</button>";
    				return btn;
			}}
		],
         ajax:{
        	 url:"menu/list.do",
        	 type:"post",
        	 "data": function (d) {
        		 var searchParams = {
            		 menuId:$("#menuId").val()
        		 };
                 //删除多余请求参数
                 for(var key in d){
                     if(key.indexOf("columns")==0||key.indexOf("order")==0){ //以columns开头的参数删除
                         delete d[key];
                     }
                 }
                 //附加查询参数
                 if(searchParams){
                     $.extend(d,searchParams); //给d扩展参数
                 }
             },
        	 dataSrc:function(result){
                 //result.draw = 5;
                 result.recordsFiltered = 0;
        		 return result.data;
             }
         }
	});
	
	/*设置按钮*/
	var btn = "";
	btn += "<button type='button' id='saveBtn' class='btn btn-primary btn-sm' style='margin-right:5px;'>新增</button>";
	btn += "<div class='btn-group' style='margin-right:5px;'>";
	btn += "	<button type='button' class='btn btn-primary dropdown-toggle btn-sm' data-toggle='dropdown'>启用" 
	btn += "		<span class='caret'></span>";
	btn += "	</button>";
	btn += "	<ul class='dropdown-menu' role='menu'>";
	btn += "		<li><a href='#'>启用</a></li>";
	btn += "		<li><a href='#'>禁用</a></li>";
	btn += "	</ul>";
	btn += "</div>";
	btn += "<button type='button' id='delsBtn' class='btn btn-default btn-sm'>删除</button>";
	$("#btnsDiv").html(btn);
	
	$("#saveBtn").click(function(){
		common.resetForm("menuForm");
		$("#parentId").val($("#menuId").val());
		$("#parentName").val($("#Q_menuName").val());
		if(common.isNotNull($("#parentId").val())){
			$("#saveOrEdit").modal("show");
		}else{
			layer.msg("请选择父级节点");
		}
	});
	
	$("#delsBtn").click(function(){
		var objs = $("input[name=menuIds]:checked");
		if(objs.length>0){
			var ids = "";
			$(objs).each(function(i,temp){
				ids += $(temp).val() + ",";
			});
			layer.confirm('确认删除?', {
				btn: ['确认','取消'] //按钮
				}, function(){
					var ii = layer.load();
					$.ajax({
						type:"post",
						data:{ids:ids},
						dataType:"JSON",
						url:"menu/dels.do",
						success:function(data){
							layer.close(ii);
							if(data.result == "1"){
								layer.msg("操作成功！");
								$("#menuTable").DataTable().draw(false);
							}else{
								layer.msg("操作失败！"+data.otherMsg);
							}
						}
					});
				});
		}else{
			layer.msg("请选择节点");
		}
	});
	
	
});

/*新增或修改*/
function saveOrEdit(){
	if(formValidate().form()){
		var param = $("#menuForm").serialize();
		var ii = layer.load();
		$.ajax({
			type:"post",
			url:"menu/save.do",
			data:param,
			dataType:"JSON",
			success:function(data){
				layer.close(ii);
				if(data.result == "1"){
					layer.msg("操作成功！"); 
				}else{
					layer.msg("操作失败！"+data.otherMsg);
				}
				$("#saveOrEdit").modal("hide");
			}
		});
	}
}

function formValidate(){
	return $("#menuForm").validate({
		rules: {
			menuName: "required",
			sort: {
				required: true,
				digits: true
			}
		},
		messages: {
			menuName: "请输入菜单名称",
			sort: {
				required: "请输入排序",
				digits: "请输入正确的整数"
			}
		}
	});
}

