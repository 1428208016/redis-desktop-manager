$.extend( $.fn.dataTable.defaults, {
	sDom:'<"top"<"#btnsDiv.col-md-8"><"col-md-4"f>>rt<"bottom"<"col-md-4"i><"col-md-7"p><"col-md-1"l>>',	//自定义布局sdom：l- 每行显示的记录数;f- 搜索框;i- 表格信息;p- 分页条;r- 加载时的进度条;t- 表格;
	//"sDom" : "<'dt-toolbar'<'col-lg-10 col-md-10 col-sm-9'<'top'>r><'col-lg-2 col-md-2 col-sm-3 hidden-xs'<'showOrHideDiv'>f><'hidden-xs hidden-sm hidden-md hidden-lg'C>>t<'dt-toolbar-footer'<'col-md-4 col-sm-4'i><'col-md-8 col-sm-8'pl>>",
	language: {											//设置中文
	    "sProcessing": "处理中...",
	    "sLengthMenu": "_MENU_",
	    "sZeroRecords": "没有匹配结果",
	    "sInfo": "第 _START_ 至 _END_ 项，共 _TOTAL_ 项",
	    "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
	    "sInfoFiltered": "", //"(由 _MAX_ 项结果过滤)"
	    "sInfoPostFix": "",
	    "sSearch": "搜索:",
	    "sUrl": "",
	    "sEmptyTable": "数据为空",
	    "sLoadingRecords": "载入中...",
	    "sInfoThousands": ",",
	    "oPaginate": {
	        "sFirst": "首页",
	        "sPrevious": "上页",
	        "sNext": "下页",
	        "sLast": "末页"
	    },
	    "oAria": {
	        "sSortAscending": ": 以升序排列此列",
	        "sSortDescending": ": 以降序排列此列"
	    },
	    "search":"",
	    filter:""
	},
	aLengthMenu : [10,20,30,50,100],					//设置分页
	processing : true,									//是否显示加载中提示	true显示
	paging : true,										//是否启用分页	true 启用
	searching : true,									//是否开启本地搜索 true 开启
	displayStart : 0,									//需要显示的数据行
	pagingType : "simple_numbers",						//分页按钮布局 numbers simple simple_numbers full full_numbers first_last_numbers
	renderer: "bootstrap",								//组件接受渲染的方式
	serverSide: true,									//开启服务器处理数据
	headerCallback : function( thead, data, start, end, display ) {
	    //可以分别打印 thead, data, start, end, display 看看究竟是什么
	    //$(thead).find('th').eq(0).html( '显示 '+(end-start)+' 条记录' );
		$(thead).find('th').css("background-color","#FBFBFB");
	}
});

$(function(){
	
});