<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<%@ include file="top.jsp" %>
	<title>首页</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/system/index-bg.css">
	<style type="text/css">
	
	</style>
</head>
<body>
	<nav class="navbar-default navbar-static-side">
		<ul class="nav metismenu" id="side-menu" style="display: block;">
			<li>菜单1</li>
			<li>菜单2</li>
			<li>菜单3</li>
		</ul>
	</nav>
	<div id="page-wrapper" class="gray-bg">
		<div class="row">
			<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
				top
			</nav>
		</div>
		<div class="row">
			<iframe src="<%=basePath %>bg/12306/12306_list.jsp" style="min-height: 600px;border: none;min-width: 100%;"></iframe>
		</div>
		<div class="row">bottom</div>
	</div>
	<script type="text/javascript">
	
	</script>
</body>
</html>