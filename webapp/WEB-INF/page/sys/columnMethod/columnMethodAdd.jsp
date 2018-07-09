<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
 <!doctype html>
 <html lang="zh-CN">
 <head>
   <meta charset="UTF-8">
   <link rel="stylesheet" href="<c:url value='/css/common.css'/>">
   <link rel="stylesheet" href="<c:url value='/css/main.css'/>">
   <script type="text/javascript" src="<c:url value='/js/jquery.min.js'/>"></script>
   <script type="text/javascript" src="<c:url value='/js/colResizable-1.3.min.js'/>"></script>
   <script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
   
   <link rel="stylesheet" href="<c:url value='/plus/date/skin/WdatePicker.css'/>">
   <script type="text/javascript" src="<c:url value='/plus/date/WdatePicker.js'/>"></script>
   
   <title>后台管理</title>
 </head>
 <body>
 <div id="forms">
   <div class="box">
     <div class="box_border">
       <div class="box_center">
         <form action="" class="jqtransform" id="addForm">
          <table class="form_table pb15 pr110" width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
		        <th>菜单名称</th>
			    <td><input type="text" id="aColumnId" class="input-text lh30" size="40" isNotNull="true" warnName="菜单名称" /><font color='red'>*</font></td>
            </tr>
			<tr>
		        <th>方法名称</th>
			    <td><input type="text" id="aMethodName" class="input-text lh30" size="40" isNotNull="true" warnName="方法名称" /><font color='red'>*</font></td>
            </tr>
			<tr>
		        <th>方法路径</th>
			    <td><input type="text" id="aActionPath" class="input-text lh30" size="40" isNotNull="true" warnName="方法路径" /><font color='red'>*</font></td>
            </tr>
          </table>
          </form>
       </div>
     </div>
   </div>
</div>
 </body>
 </html>
