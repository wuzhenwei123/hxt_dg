<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
 <!DOCTYPE html>
<!--[if lt IE 7 ]> <html class="ie6"> <![endif]-->
<!--[if IE 7 ]>    <html class="ie7"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html>
<!--<![endif]-->

<head>
<meta charset="utf-8">
<title>${_title }</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<%@ include file="/WEB-INF/page/common/css.jsp" %>

</head>

<body class="sidebar-left">
  	<input type="hidden" id="currPage" value="1"><!-- 当前页码 -->
	<input type="hidden" id="returnNum" value="10"><!-- 分页返回 -->
	<input type="hidden" id="sortColumn" value=" createTime ASC "><!-- 排序字段 -->
	<div class="page-container">
		<!-- 头开始 -->
	    <%@ include file="/WEB-INF/page/common/header.jsp" %>
	   	<!--  头结束 -->
	    <!-- // header-container -->
	    
	    <div id="main-container">
	        <div id="main-sidebar" class="sidebar sidebar-inverse">
	        	<!-- // 左边 -->
	        	<%@ include file="/WEB-INF/page/common/left.jsp" %>
		        <!-- // 左边 -->
	        </div>
	        <!-- // sidebar -->
	        <!--  右边开始 -->
	        <div id="main-content" class="main-content container-fluid">
	            <div id="page-content" class="page-content">
	                <section>
	                    <div class="row-fluid" style=" margin-top: 20px;">
	                    	<div class="span12 widget widget-simple widget-collapsible bg-gray-light">
	                            <div data-target="#demoB" data-toggle="collapse" class="widget-header header-small clickable collapsed">
	                                <h4><i class="fontello-icon-search-4"></i>查询</h4>
	                                <div class="widget-tool"><span class="btn btn-glyph btn-link widget-toggle-btn fontello-icon-publish"></span></div>
	                            </div>
	                            <div class="widget-content collapse" id="demoB" style="height: 0px;">
	                                <div class="widget-body">
	                                    <div class="widget-row form-inline row-fluid">
                                    		<label class="margin5">角色名称：</label>
                                            <input type="text" id="fRoleName" class="span2 margin5" placeholder="角色名称">
                                            <label class="margin5">角色类型：</label>
						                	<select id="fRoleType" class="span2 margin5"> 
						                        <option value="">全部</option> 
						                        <option value="1">超级管理</option> 
						                        <option value="0">普通管理</option> 
						                	</select> 
	                                    </div>
	                                </div>
	                                <div class="widget-footer text-center">
	                                    <div class="btn-toolbar"> 
	                                    	<a href="javascript:searchData('1');" class="btn btn-green">查询</a> 
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </section>
	                <section>
	                    <div class="row-fluid">
	                    	<div class="span12 widget widget-simple bg-gray-light">
	                             <div class="widget-header">
	                                 <div class="btn-toolbar"> 
	                                 	 <div class="btn-group"> 
	                                 	 	<perm:tag permPath="/adminRole/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/adminRole/removeAllAdminRole" >
	                                 	 	<a href="javascript:delAll();" class="btn btn-glyph"><i class="fontello-icon-minus-1"></i>删除</a> 
	                                 	 	</perm:tag>
	                                 	</div>
	                                 </div>
	                             </div>
	                             <div class="widget-content" id="demo1">
	                                 <div class="widget-body">
	                                     <table id="exampleDTC" class="table table-striped table-content table-condensed boo-table table-hover bg-blue-light">
		                                </table>
		                                <div class="widget-footer">
		                                    <div class="btn-toolbar pull-left">
		                                    </div>
		                                    <div class="pagination pagination-btn pull-right">
		                                    	<div id="kkpager"></div>
		                                    </div>
		                                    <!-- // pagination -->
		                                </div>
	                                 </div>
	                             </div>
	                         </div>
	                    </div>
	                </section>
	            </div>
	            <!-- // page content --> 
	            
	        </div>
	       	<!--  右边结束 -->
	        <!-- // main-content --> 
	        
	    </div>
	    <!-- // main-container  -->
	    
	    <footer id="footer-fix">
	        <div id="footer-sidebar" class="footer-sidebar">
	            <div class="navbar">
	                <div class="btn-toolbar"> <a class="btn btn-glyph btn-link" href="javascript:void(0);"><i class="fontello-icon-up-open-1"></i></a> </div>
	            </div>
	        </div>
	        <!-- // footer sidebar -->
	        	<%@ include file="/WEB-INF/page/common/footer.jsp" %>
	        <!-- // footer content --> 
	        
	    </footer>
	    <!-- // footer-fix  --> 
	    
	</div>
<!-- // page-container  --> 

<!-- Le javascript --> 
<%@ include file="/WEB-INF/page/common/js.jsp" %>
<!-- Only This Demo Page --> 
<div id="temModal" class="modal hide fade" tabindex="-1" data-width="80%"></div>
   
   <script type="text/javascript">
   		var $modal = $('#temModal');
	    $(document).ready(function(){
	    	searchData(1);
	    });
		function searchData(pageNo){
			var returnNum = $("#returnNum").val();
			var sortColumn = $("#sortColumn").val();
		    var roleId = $("#fRoleId").val();
		    var roleName = $("#fRoleName").val();
		    var createTime = $("#fCreateTime").val();
		    var state = $("#fState").val();
		    var defaule = $("#fDefaule").val();
		    var roleType = $("#fRoleType").val();
		    $.getJSON("<c:url value='/adminRole/getAdminRoleList'/>",
	        {
	        	sortColumn:sortColumn,
	    		roleId : roleId,
	    		roleName : roleName,
	    		createTime : createTime,
	    		state : state,
	    		defaule : defaule,
	    		roleType : roleType,
	    		pageNo: pageNo,
	    		rowCount: returnNum, 
				_t: Math.random()
	        },function(data){
	            var result = data;
	            if (result.code == 1) {
	                setTableStr(result, pageNo, returnNum);
	            } else {
	            	tipError("系统异常!");
	            } 
		    });
		}
		function genTableHeader(){
			var str = "<thead><tr>" ;
			str+= "<th scope=\"col\" class=\"check-col\"><input id=\"checkAllBtn\" type='checkbox' class='checkbox check-all' value='ON' onclick=\"checkAll('checkAllBtn','checkName');\" name='check-all'></th>";
		    	str+= "<th width='50'>序号</th>";
		    	str+= "<th onselectstart='return false' id='th_roleId' column='roleId' >角色ID<span class=\"pull-right\"></span></th>";
		    	str+= "<th onselectstart='return false' id='th_roleName' column='roleName' >角色名称<span class=\"pull-right\"></span></th>";
		    	str+= "<th onselectstart='return false' id='th_state' column='state' >状态<span class=\"pull-right\"></span></th>";
		    	str+= "<th onselectstart='return false' id='th_defaule' column='defaule' >是否默认<span class=\"pull-right\"></span></th>";
		    	str+= "<th onselectstart='return false' id='th_roleType' column='roleType' >类型<span class=\"pull-right\"></span></th>";
		    	str+= "<th onselectstart='return false' class='sortTh' width='135' id='th_createTime' column='createTime' >创建日期<span class=\"pull-right aweso-icon-sort\"></span></th>";
				str+="<th>操	作</th>";
				str+= "</tr></thead>";
			return str;
		}
		function setTableStr(result, pageNo, returnNum){
			var tableStr = "<table class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
			tableStr += genTableHeader();
		    tableStr += "<tfoot></tfoot>";
	        tableStr += "<tbody>";
		    var number = (pageNo - 1) * returnNum;
		    for (var k=0; k<result.items.length; k++){      
		        tableStr += "<tr  class=\"tr\">";
		        tableStr += "<td ><input type=\"checkbox\" id='"+result.items[k].roleId+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" /></td>";
		        tableStr += "<td >" + (number + k + 1) + "</td>";
		        tableStr += "<td >" + result.items[k].roleId + "</td>";
		        tableStr += "<td >" + result.items[k].roleName + "</td>";
		        tableStr += "<td >" + (result.items[k].state==1?"正常":"禁用") + "</td>";
		        tableStr += "<td >" + (result.items[k].defaule==1?"默认":"非默认") + "</td>";
		        tableStr += "<td >" + (result.items[k].roleType==1?"超级管理":"普通管理") + "</td>";
		        tableStr += "<td >" + genDateTimeAll(result.items[k].createTime) + "</td>";
		        tableStr += "<td>";
		        tableStr += "<div class=\"btn-group\">";
		        tableStr += "<perm:tag permPath='/adminRole/toUpdate' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].roleId + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
	        	tableStr += "<perm:tag permPath='/adminRole/removeRoleColumn' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].roleId + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
		        tableStr += "<perm:tag permPath='/adminRole/toUserRoleColumn' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='column(" + result.items[k].roleId + ");return false;'><i class='fontello-icon-menu' />菜单</a></perm:tag>";
		        tableStr += "</div>";
		        tableStr += "</td>";
		        tableStr += "</tr>";            
		    }
		    tableStr += "</tbody>";
		    $("#exampleDTC").html(tableStr);
		    $("#currPage").val(pageNo);	
	        $("input.checkbox, input.radio, input:file.input-file").uniform();//初始化复选框
		    initTh();
		    genPageTag(pageNo,result.totalResults,returnNum,'kkpager');
		}
	
   		// 设置菜单权限
		function column(id) {
			show('<c:url value="/adminRole/toUserRoleColumn/'+id+'"/>','');
		}
   		// 更新
		function toUpdate(id) {
			show('<c:url value="/adminRole/toUpdate/'+id+'"/>','');
		}
   		// 添加
   		function toAdd(){
   			show('<c:url value="/adminRole/toAdd"/>','');
   		}
   		// 删除所选
   		function delAll(){
   			var ids = '';
   			$("[name='checkName']").each(function(){
	   		    	var ck = $(this).attr("checked");
	   		    	if(ck == 'checked'){
	   		    		ids+=$(this).attr("id")+",";
	   		    	}else{
	   		    	}
	   		})
   		    if(ids == ''){
   		    	tip("请选择待删除条目!");
   		    }else{
   		    	bootbox.confirm("你确定要删除吗?","取消","确定", function(result) {
   					if(result){
   						$.post("<c:url value='/adminRole/removeAllAdminRole'/>",
   			        	{
   							roleIds :ids,
   							ranNum:Math.random()
   						},
   			        	function(data){
   				        	var result = eval('('+data+')'); 
   				            if (result.code == '1') {
   				              	var pageNo = $("#currPage").val();           
   				              	searchData(pageNo);
   				              	tipOk("删除成功!");
   				             } else {
   				            	tipError("删除失败!"+result.message);
   				             }
   				        });
   					}else{
   						//取消
   					}
   				});
   		    }
   			
   		}
   		// 单条删除
   		function del(roleId){
   		   if (roleId != ""){
   			 bootbox.confirm("你确定要删除吗?","取消","确定", function(result) {
   				if(result){
   					$.post("<c:url value='/adminRole/removeAdminRole'/>",
   		        	{
   						roleId	:roleId,
   						ranNum:Math.random()
   					},
   		        	function(data){
   			        	var result = eval('('+data+')'); 
   			            if (result.code == '1') {
   			              	var pageNo = $("#currPage").val();           
   			              	searchData(pageNo);
   			              	tipOk("删除成功!");
   			             } else {
   			              	tipError("删除失败!"+result.message);
   			             }
   			        });
   				}else{
   					//取消
   				}
   			});
	   	 }
   				
   	    }
   </script>
 </body>
 </html>
