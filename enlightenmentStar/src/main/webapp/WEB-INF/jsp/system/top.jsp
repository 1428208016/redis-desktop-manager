<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">

<!-- 以下加载公共css -->
<link rel="stylesheet" type="text/css" href="static/bootstrap-3.3.7/css/bootstrap.css" title="Bootstrap">

<!-- 以下加载公共js -->
<script type="text/javascript" src="static/js/jquery-3.3.1.min.js" ></script><!-- jquery -->
<script src="static/bootstrap-3.3.7/js/bootstrap.min.js"></script><!-- Bootstrap  -->

