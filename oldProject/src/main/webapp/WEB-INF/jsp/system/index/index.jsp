<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
    String scheme = request.getScheme();
	String basePath = scheme + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String dataPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();        
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
	<title>Project首页</title>
    <%@ include file="top.jsp"%>
    <style type="text/css">
    #nav{
    	border-radius:0px;
    	margin-bottom: 0px;
    }
 /*   #user_img{
    	display:block;
    	width:30px;
    	height:30px;
    	border-radius:20px;
    	overflow:hidden;
    	background: url('static/images/system/index/user_img.jpg') no-repeat;
    	background-size:30px auto;
    }*/
    #user_img{
    	width:auto;
    	height:20px;
    	border-radius: 20px;
    }
    </style>
</head>
<body>
	<!--  nav 页面top  -->
	<nav id="nav" class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
	    	<div class="navbar-header">
	      		<a class="navbar-brand" href="/project/main/toIndex.do" style="font-size: 28px;">Project</a>
	    	</div>
	    	<ul class="nav navbar-nav navbar-right">
	      		<li>
	      			<a href="#">
		      			<div><img id="user_img" alt="用户头像" src="static/images/system/index/user_img.jpg" > admin</div>
	      			</a>
	      		</li>
	      		<li><a onclick="showOut();" style="cursor: pointer;"><i class="iconfont icon-tuichu"></i> 退出</a></li>
	    	</ul>
		</div>
	</nav>
	
	<!--  左侧导航栏  -->
	<div class="container-fluid">
		<div class="row" id="row">
			<div id="left" class="col-md-2 col-sm-2 col-xs-4 bg-success" style="padding: 0px;overflow: auto;">
				<ul id="main-nav" class="nav nav-tabs nav-stacked">
					<c:forEach var="temp" items="${menuList}">
						<li>
						<a href="#${temp.menuId}" class="nav-header collapsed" class="nav-header collapsed" data-toggle="collapse">
							<i class="iconfont ${temp.icon}"></i> ${temp.menuName}
							<i class="pull-right iconfont icon-xiala"></i>  
						</a>
						<ul id="${temp.menuId}" class="nav nav-list collapse secondmenu" style="height: 0px;"> 
							<c:forEach var="stemp" items="${temp.sonList}">
	                            <li><a href="${stemp.url}" target="mainFrame"> ${stemp.menuName}</a></li>  
							</c:forEach>
                        </ul>
					</li>
					</c:forEach>
				</ul>					
			</div>
			
			<!--  中间部分  -->
			<div class="col-md-10" id="center" style="padding:0px;">
				<iframe name="mainFrame" class="container-fluid" id="mainFrame" frameborder="0" src="" style="margin:0px;padding:0px;width:100%;"></iframe>
			</div>
		</div>
	</div>
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="outLoginModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">消息框</h4>
	            </div>
	            <div class="modal-body">确认退出？</div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button id="outLoginBtn" type="button" class="btn btn-primary">确认</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>


	<script type="text/javascript">
	window.onresize = function(){
		changeRoweight();
	}
	$(function(){
		changeRoweight();
	});
	/*left center mainFrame 自适应高宽*/
	function changeRoweight(){
		var height = $(window).height();
		var width = $(window).width();
		var nvaHeight = $("#nav").height();
		var leftHeight = $("#left").width();
		$("#left").css("height",(height-nvaHeight-2)+"px");
		$("#center").css("height",(height-nvaHeight-2)+"px");
		$("#mainFrame").css("height",(height-nvaHeight-6)+"px");
		$("#mainFrame").css("width",(width-leftHeight-2)+"px");
	}
	
	function showOut(){
		$('#outLoginModel').modal('show')
	}
	$("#outLoginBtn").click(function(){
		$.ajax({
			type:"post",
			url:"main/outLogin.do",
			dataType : 'json',
			success:function(res){
				if(res.result == "1"){
					window.location.href = "<%=dataPath%>/project/main/toLogin.do";
				}else{
					alert(res.msg);
				}
			}
		});
	});
	</script>
	
</body>
</html>