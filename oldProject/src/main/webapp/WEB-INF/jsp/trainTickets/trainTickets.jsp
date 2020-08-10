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
    <title>12306火车票调用接口</title>
    <%@ include file="/WEB-INF/jsp/system/index/top.jsp"%>
	<!-- datatables -->
	<script type="text/javascript" src="plugins/dataTables/media/dataTablesInit.js"></script>
	
	<style type="text/css">
	</style>
	<style type="text/css">
		#form div{
			margin: 0px;
			padding: 0px;
		}
	</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row" style="border-bottom: 1px solid #E7E7E7;padding: 4px 10px;">
			其他>>火车票>>12306列表
		</div>
		<div class="row" style="border-bottom: 1px solid #DFDFDF;margin-top: 10px;">
			<form id="form">
				<div class="col-md-1" >
					<div class="radio text-center input-sm">
						<label><input type="radio" name="type" checked="checked">单程</label>
					</div>
					<div class="radio text-center input-sm">
			        	<label><input type="radio" name="type">往返</label>
			      	</div>
				</div>
				<div class="col-md-4" >
					<div class="col-md-6">
						<label class="col-md-4 input-sm">
							出发地
						</label>
						<div class="col-md-8 input-sm">
							<input type="text"class="form-control input-sm" id="leftTicketDTO.from_station" name="leftTicketDTO.from_station" placeholder="">
						</div>
					</div>
					<div class="col-md-6">
						<label class="col-md-5 input-sm">
							<a href="#3" class="nav-header collapsed" data-toggle="collapse"><i class="iconfont icon-jiaoseguanli"></i></a>
							目的地
						</label>
						<div class="col-md-7 input-sm">
							<input type="text"class="form-control input-sm" id="leftTicketDTO.from_station" name="leftTicketDTO.from_station" placeholder="">
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="col-md-6">
						<label class="col-md-4 input-sm">
							出发日
						</label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="leftTicketDTO.from_station" name="leftTicketDTO.from_station" placeholder="">
						</div>
					</div>
					<div class="col-md-6">
						<label class="col-md-4 input-sm">
							返程日
						</label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="leftTicketDTO.from_station" name="leftTicketDTO.from_station" placeholder="">
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="col-md-6">
						<div class="radio text-center input-sm">
							<label><input type="radio" name="type" checked="checked">普通</label>
						</div>
						<div class="radio text-center input-sm">
				        	<label><input type="radio" name="type">学生</label>
				      	</div>
					</div>
					<div class="col-md-6">
		      			<button type="submit" class="btn btn-default" style="margin-left: 10px;">查询</button>
						<div class="checkbox text-left input-sm ">
							<label><input type="checkbox" name="type">自助查询</label>
						</div>
					</div>
				</div>
			</form>
       	</div>
       	<div class="col-xs-10 col-sm-10 col-md-10">
       		<table id="ticketTable" class="table table-striped table-hover"></table>
       	</div>
    </div>
</body>
</html>