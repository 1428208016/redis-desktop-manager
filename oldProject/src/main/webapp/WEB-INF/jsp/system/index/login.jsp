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
    <base href="<%=basePath%>">
    <title>Project登录</title>
    <%@ include file="top.jsp"%>
    <style type="text/css">
		.container{
			border:1px solid #ddd;
			border-radius:3px;
			margin-top: 140px;
			background-color:rgba(255,255,255,0.9);  
			width:300px;
			min-width:300px;
			height:280px;
			min-height: 280px;
		}
		body{
			background: url('static/images/system/index/banner.png') no-repeat;
			background-size:100%;
		}
		h3{
			padding: 0px auto;
		}
    </style>
</head>

<body onkeydown="key13()">
	<div class="container" style="">
		<form action="">
			<h3 class="col-md-offset-4">Project</h3>
			<div class="form-group">
				<label for="userName">用户名</label>
      			<input type="text" class="form-control" id="userName" name="userName" placeholder="请输入用户名">
			</div>
			<div class="form-group">
				<label for="passWord">密码</label>
      			<input type="password" class="form-control" id="passWord" name="passWord" placeholder="请输入密码">
			</div>
			<button type="button" id="login" class="btn btn-info btn-block">登录</button>
			<div class="form-group" style="margin-top: 10px;">
				<a href="main/toRegister.do" class="pull-right">立即注册</a>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#login").click(function(){
				if(veri()){
					$.ajax({
						type:"post",
						url:"main/login.do",
						data:{userName:$("#userName").val(),password:$("#passWord").val()},
						dataType : 'json',
						success:function(res){
							alert(res.msg);
							if(res.result == "1"){
								window.location.href = "<%=dataPath%>/project/main/toIndex.do";
							}else{
								alert(res.msg);
							}
						}
					});
				}
			});
		});
		function veri(){
			var userName = $("#userName").val();
			var passWord = $("#passWord").val();
			if(!notNull(userName)){
				$("#userName").parent().addClass("has-error");
				$("#userName").focus();
				return false;
			}else{
				$("#userName").parent().removeClass("has-error");
			}
			if(!notNull(passWord)){
				$("#passWord").parent().addClass("has-error");
				$("#passWord").focus();
				return false;
			}else{
				$("#passWord").parent().removeClass("has-error");
			}
			return true;
		}
		function key13(){
			if (event.keyCode == 13)
			  {
			    event.returnValue=false;
			    event.cancel = true;
			    $("#login").click();
			  }
		}
		/*判断是否为null*/
		function notNull(obj){
			if(obj == null || obj == 'undeined' || obj == '' || obj.length <=0){
				return false;
			}
			return true;
		}
	</script>
</body>
</html>