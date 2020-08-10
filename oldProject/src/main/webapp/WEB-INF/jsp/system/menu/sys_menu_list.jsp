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
    <title>系统菜单列表</title>
    <%@ include file="../index/top.jsp"%>
    <!-- ztree -->
	<script src="plugins/zTree/js/jquery.ztree.all-3.5.js"></script>
	<link rel="stylesheet" href="plugins/zTree/css/metroStyle/metroStyle.css" type="text/css">
    <script type="text/javascript" src="plugins/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="plugins/zTree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="plugins/zTree/js/jquery.ztree.exedit-3.5.js"></script>
	
	<!-- dataTables -->
	<link rel="stylesheet" href="plugins/dataTables/media/css/jquery.dataTables.min.css"/>
	<script type="text/javascript" src="plugins/dataTables/media/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="plugins/dataTables/media/dataTablesInit.js"></script>

	<!-- validate -->
	<script type="text/javascript" src="plugins/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="plugins/validate/validateInit.js"></script>
	
	<!-- 公共方法 -->
	<script type="text/javascript" src="index/js/common/common.js"></script>
	
	<style type="text/css">
		.ztree{
			background-color: #F5F5F5;
		}
	</style>
<body>

	<input id="menuId" name="menuId" type="hidden" value="1">
	<input id="Q_menuName" name="Q_menuName" type="hidden" value="系统菜单">

	<div class="container-fluid">
		<div class="row" style="border-bottom: 1px solid #E7E7E7;padding: 4px 10px;">
			系统管理>>菜单管理>>菜单列表
		</div>
		<div class="row" >
        	<div class="col-xs-2 col-sm-2 col-md-2" style="padding: 0px;">
        		<ul id="menuzTree" class="ztree" style="min-width: 200px;height100%;overflow: auto;"></ul>
        	</div>
        	<div class="col-xs-10 col-sm-10 col-md-10">
        		<table id="menuTable" class="table table-striped table-hover">
        		</table>
        	</div>
       </div>
    </div>
    
    <!-- 模态框（Modal） -->
	<div class="modal fade" id="saveOrEdit" tabindex="-1" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog" style="width:400px">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">新增</h4>
	            </div>
	            <div class="modal-body">
	            	<form action="" id="menuForm">
	            		
	            		<input type="hidden" id="parentId" name="parentId">
	            	
		            	<div class="row my-row text-right">
		            		<label class="col-md-4">父级菜单</label>
		            		<div class="col-md-8 ">
			            		<input id="parentName" name="parentName" disabled="disabled" type="text" class="form-control">
		            		</div>
		            	</div>
		            	<div class="row my-row text-right">
		            		<label class="col-md-4"><span>*</span>菜单名称</label>
		            		<div class="col-md-8 ">
			            		<input id="menuName" name="menuName" type="text" class="form-control">
		            		</div>
		            	</div>
		            	<div class="row my-row text-right">
		            		<label class="col-md-4">菜单URL</label>
		            		<div class="col-md-8 ">
			            		<input id="url" name="url" type="text" class="form-control">
		            		</div>
		            	</div>
		            	<div class="row my-row text-right">
		            		<label class="col-md-4">图标</label>
		            		<div class="col-md-8 ">
			            		<input id="icon" name="icon" type="text" class="form-control">
		            		</div>
		            	</div>
		            	<div class="row my-row text-right">
		            		<label class="col-md-4"><span>*</span>排序</label>
		            		<div class="col-md-8 ">
			            		<input id="sort" name="sort" type="text" class="form-control">
		            		</div>
		            	</div>
		            </form>
	            </div>
	            <div class="modal-footer">
	                <button onclick="saveOrEdit()" type="button" class="btn btn-primary">确认</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	
	<script type="text/javascript" src="index/js/system/menu/menu.js"></script>
    <script type="text/javascript">
    
    </script>
</body>
</html>