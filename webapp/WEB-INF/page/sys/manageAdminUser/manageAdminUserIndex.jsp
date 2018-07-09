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
	<input type="hidden" id="currPage3" value="1"><!-- 当前页码 -->
	<input type="hidden" id="returnNum3" value="10"><!-- 分页返回 -->
	<input type="hidden" id="currPage4" value="1"><!-- 当前页码 -->
	<input type="hidden" id="returnNum4" value="10"><!-- 分页返回 -->
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
                                    		<label class="margin5">用户ID：</label>
                                            <input type="text" id="fAdminId" class="span2 margin5" placeholder="用户ID">
                                    		<label class="margin5">用户名：</label>
                                            <input type="text" id="fAdminName" class="span2 margin5" placeholder="用户名">
                                    		<label class="margin5">昵称：</label>
                                            <input type="text" id="fNickName" class="span2 margin5" placeholder="昵称">
                                    		<label class="margin5">真实姓名：</label>
                                            <input type="text" id="fRealName" class="span2 margin5" placeholder="真实姓名">
	                                    </div>
	                                    <div class="widget-row form-inline">
	                                    	<label class="margin5">手机：</label>
                                            <input type="text" id="fMobile" class="span2 margin5" placeholder="手机">
                                    		<label class="margin5">电话：</label>
                                            <input type="text" id="fPhone" class="span2 margin5" placeholder="电话">
                                    		<label class="margin5">最后登陆日期：</label>
                                            <input type="text" id="fLastLogin" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="span2 margin5" placeholder="登陆日期">
                                            <label class="margin5">状态：</label>
						                	<select id="fState" class="span2 margin5"> 
						                        <option value="">全部</option> 
						                        <option value="1">正常</option> 
						                        <option value="0">禁用</option> 
						                	</select> 
						                </div>
	                                    <div class="widget-row form-inline">
                                    		<label class="margin5">所属单位：</label>
							                <input type="text" id="oneAgentindex" readonly="readonly" onclick="selOneindex()" data-toggle="modal">
							           		<a type="button" class="btn btn-boo" onclick="cleanOnIndex()">清除</a>
								           	<a href="#stack3" id="xxxindex" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
								           	<input type="hidden" id="oneAgentOpenIdIndex"/>
<!--                                     		<label class="margin5">所属子单位：</label> -->
<!--                                            	<input type="text" id="twoAgentIndex" readonly="readonly" onclick="selTwoIndex()" data-toggle="modal"> -->
<!-- 							            	<a type="button" class="btn btn-boo" onclick="cleanTwoIndex()">清除</a> -->
<!-- 							            	<input type="hidden" id="twoAgentOpenIdIndex"/> -->
<!-- 							            	<a href="#stack4" id="sssIndex" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a>  -->
	                                    	<label class="margin5">角色：</label>
	                                    	<select id="fRoleId"> 
						                       <option value="">--请选择--</option> 
									    		<c:forEach items="${roleList }" var="role">
							                       <option value="${role.roleId }">${role.roleName }</option> 
									    		</c:forEach>
							               	</select> 
							               	<label class="margin5">创建开始时间：</label>
                                            <input type="text" id="fStartTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" class="span2 margin5" placeholder="创建开始时间">
                                            <label class="margin5">创建结束时间：</label>
                                            <input type="text" id="fEndTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" class="span2 margin5" placeholder="创建结束时间">
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
	                                 	 	<perm:tag permPath="/manageAdminUser/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/manageAdminUser/removeAllManageAdminUser" >
	                                 	 	<a href="javascript:delAll();" class="btn btn-glyph"><i class="fontello-icon-minus-1"></i>删除</a> 
	                                 	 	</perm:tag>
	                                 	 	<a href="javascript:exportExcel();" class="btn btn-glyph">导出</a>
	                                 	 	<a href="javascript:void(0);" class="btn btn-glyph" style="cursor:default;" id='totalA'>总数:</a>
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
<div id="stack3" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择单位</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fOneAgentNameIndex" class="span2 margin5" placeholder="代理名称">
	    <a href="javascript:searchData3('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo4">
	         <div class="widget-body">
	             <table id="exampleDTC3" class="table table-striped table-content table-condensed boo-table table-hover bg-blue-light">
		         </table>
		         <div class="widget-footer">
		             <div class="btn-toolbar pull-left">
		             </div>
		             <div class="pagination pagination-btn pull-right">
		             	<div id="kkpager3"></div>
		             </div>
		         </div>
	         </div>
		</div>
    </div>
    <div class="modal-footer"> <a type="button" id="closeOneIndex" data-dismiss="modal" class="btn btn-boo">关闭</a></div>
</div>
<div id="stack4" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择子单位</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fTwoAgentNameIndex" class="span2 margin5" placeholder="代理名称">
	    <a href="javascript:searchData4('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo5">
	         <div class="widget-body">
	             <table id="exampleDTC4" class="table table-striped table-content table-condensed boo-table table-hover bg-blue-light">
		         </table>
		         <div class="widget-footer">
		             <div class="btn-toolbar pull-left">
		             </div>
		             <div class="pagination pagination-btn pull-right">
		             	<div id="kkpager4"></div>
		             </div>
		         </div>
	         </div>
		</div>
    </div>
    <div class="modal-footer"> <a type="button" id="closeTwoIndex" data-dismiss="modal" class="btn btn-boo">关闭</a></div>
</div>
<!-- Le javascript --> 
<%@ include file="/WEB-INF/page/common/js.jsp" %>
<script type="text/javascript" src="<c:url value='/js/page.js'/>"></script>
<!-- Only This Demo Page --> 
<div id="temModal" class="modal hide fade" tabindex="-1" data-width="50%"></div>
   
<script type="text/javascript">
   		var $modal = $('#temModal');
	    $(document).ready(function(){
	    	searchData(1);
	    });
		function searchData(pageNo){
			var returnNum = $("#returnNum").val();
			var sortColumn = $("#sortColumn").val();
		    var adminId = $("#fAdminId").val();
		    var adminName = $("#fAdminName").val();
		    var nickName = $("#fNickName").val();
		    var passwd = $("#fPasswd").val();
		    var realName = $("#fRealName").val();
		    var oneAgentOpenId = $("#oneAgentOpenIdIndex").val();
		    var twoAgentOpenId = $("#twoAgentOpenIdIndex").val();
		    var mobile = $("#fMobile").val();
		    var phone = $("#fPhone").val();
		    var lastLogin = $("#fLastLogin").val();
		    var loginIP = $("#fLoginIP").val();
		    var pwdModifyTime = $("#fPwdModifyTime").val();
		    var state = $("#fState").val();
		    var createTime = $("#fCreateTime").val();
		    var createrId = $("#fCreaterId").val();
		    var roleId = $("#fRoleId").val();
		    var startTime = $("#fStartTime").val();
		    var endTime = $("#fEndTime").val();
		    $.getJSON("<c:url value='/manageAdminUser/getManageAdminUserList'/>",
	        {
	        	sortColumn:sortColumn,
	    		adminId : adminId,
	    		adminName : adminName,
	    		realName : realName,
	    		companyId : oneAgentOpenId,
	    		twoAgentOpenId : twoAgentOpenId,
	    		nickName : nickName,
	    		passwd : passwd,
	    		startTime : startTime,
	    		endTime : endTime,
	    		mobile : mobile,
	    		phone : phone,
	    		roleId : roleId,
	    		lastLogin : lastLogin,
	    		loginIP : loginIP,
	    		pwdModifyTime : pwdModifyTime,
	    		state : state,
	    		createTime : createTime,
	    		createrId : createrId,
	    		pageNo: pageNo,
	    		rowCount: returnNum, 
				_t: Math.random()
	        },function(data){
	            var result = data;
	        	$("#totalA").html('总数:'+result.totalResults);
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
		    	str+= "<th scope=\"col\" width='30'>序号</th>";
// 		    	str+= "<th scope=\"col\" width='50'  id='th_adminId' column='adminId' >用户ID<span class=\"pull-right\"></span></th>";
		    	str+= "<th class=\"sortTh\" scope=\"col\" id='th_adminName' column='adminName' >登录账号</th>";
		    	str+= "<th scope=\"col\" id='th_nickName' column='nickName' >昵称<span class=\"pull-right\"></span></th>";
		    	str+= "<th scope=\"col\" id='th_realName' column='realName' >真实姓名<span class=\"pull-right\"></span></th>";
		    	str+= "<th scope=\"col\" id='th_mobile' column='mobile' >手机<span class=\"pull-right\"></span></th>";
		    	str+= "<th scope=\"col\" id='th_phone' column='phone' >电话<span class=\"pull-right\"></span></th>";
		    	str+= "<th scope=\"col\" id='th_roleName' column='roleName' >角色名称<span class=\"pull-right\"></span></th>";
		    	str+= "<th scope=\"col\" id='th_roleName' column='roleName' >公司名称<span class=\"pull-right\"></span></th>";
		    	str+= "<th class=\"sortTh\" scope=\"col\" width='135' id='th_lastLogin' column='lastLogin' >最后登陆日期</th>";
		    	str+= "<th scope=\"col\" id='th_loginIP' column='loginIP' >最后登录IP</th>";
		    	str+= "<th scope=\"col\" id='th_loginIP' column='loginIP' >微信ID</th>";
// 		    	str+= "<th scope=\"col\" width='135' id='th_pwdModifyTime' column='pwdModifyTime' >pwdModifyTime</th>";
		    	str+= "<th scope=\"col\" width='50'  id='th_state' column='state' >状态</th>";
		    	str+= "<th class=\"sortTh\" scope=\"col\" width='135' id='th_createTime' column='createTime' >创建日期</th>";
// 		    	str+= "<th scope=\"col\" width='50' >创建人</th>";
				str+= "<th width='50'>操	作</th>";
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
		        tableStr += "<td ><input type=\"checkbox\" id='"+result.items[k].adminId+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" /></td>";
		        tableStr += "<td>" + (number + k + 1) + "</td>";
// 		        tableStr += "<td>" + result.items[k].adminId + "</td>";
		        tableStr += "<td>" + result.items[k].adminName + "</td>";
		        tableStr += "<td>" + result.items[k].nickName + "</td>";
		        tableStr += "<td>" + result.items[k].realName + "</td>";
		        tableStr += "<td>" + result.items[k].mobile + "</td>";
		        tableStr += "<td>" + result.items[k].phone + "</td>";
		        tableStr += "<td>" + result.items[k].roleName + "</td>";
		        tableStr += "<td>" + result.items[k].companyName + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].lastLogin) + "</td>";
		        tableStr += "<td>" + result.items[k].loginIP + "</td>";
		        tableStr += "<td>" + result.items[k].openId + "</td>";
// 		        tableStr += "<td>" + genDateTimeAll(result.items[k].pwdModifyTime) + "</td>";
				if(result.items[k].state=="1"){
		        	tableStr += "<td>正常</td>";
				}else{
					tableStr += "<td>禁用</td>";
				}
		        tableStr += "<td>" + genDateTimeAll(result.items[k].createTime) + "</td>";
// 		        tableStr += "<td>" + result.items[k].createrName + "</td>";
		        tableStr += "<td>";
		        tableStr += "<div class=\"btn-group\">";
	        	tableStr += "<perm:tag permPath='/manageAdminUser/updateManageAdminUser' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].adminId + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
	        	tableStr += "<perm:tag permPath='/manageAdminUser/removeManageAdminUser' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].adminId + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
// 	        	tableStr += "<perm:tag permPath='/manageAdminUser/toSkins/*' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='skin(" + result.items[k].adminId + ");return false;'><i class='fontello-icon-aperture' />风格</a></perm:tag>";
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
		// 设置风格
		function skin(adminId){
			top.art.dialog.open('<c:url value="/manageAdminUser/toSkins/'+adminId+'"/>',
			{
				id:123,
				fixed:true,
				esc:true,
				title:'风格设置',
				width: '90%',
				height:'340px',
			    cancelVal: '关闭',
		        cancel: true, //为true等价于function(){}
		        okVal : "保存",
				ok:function(){
					var iframe = this.iframe.contentWindow;
			    	if (!iframe.document.body) {
			        	return false;
			        };
			        var flag = validateForm(iframe,'updateForm');
			        if(flag){
			        	updateSkins(iframe);
			        }
      				return flag;
				}
			});
		}
		
		// 执行风格更新
   		function updateSkins(iframe){
			var skinId = getIframeVal(iframe,"mSkinId");
			var adminId = getIframeVal(iframe,"mAdminId");
			var dialog = getIframeVal(iframe,"mDialog");
			var style = getIframeVal(iframe,"mStyle");
			var kkpager = getIframeVal(iframe,"mKkpager");
			var skStyle = getIframeVal(iframe,"mSkStyle");
			var flag = true ;
		    if (flag){ 
		        $.post("<c:url value='/adminSkins/updateSaveAdminSkins'/>",
		        	{
		    		skinId : skinId,
		    		skStyle:skStyle,
		    		adminId : adminId,
		    		dialog : dialog,
		    		style : style,
		    		kkpager : kkpager,
					 _t:Math.random()},
		        	function(data){
			        	var result = eval('('+data+')'); 
			            if (result.code == '1') {
			              	top.art.dialog.tips('保存成功');
			                window.location.reload();
			             } else {
			            	 top.art.dialog.alert(result.message);
			             }
		        });
		    }
   		}
   		
   		// 更新
		function toUpdate(id) {
			show('<c:url value="/manageAdminUser/toUpdate/'+id+'"/>','');
		}
   		
   		// 添加
   		function toAdd(){
			show('<c:url value="/manageAdminUser/toAdd"/>','');
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
   						$.post("<c:url value='/manageAdminUser/removeAllManageAdminUser'/>",
   			        	{
   							adminIds :ids,
   							ranNum:Math.random()
   						},
   			        	function(data){
   				        	var result = eval('('+data+')'); 
   				            if (result.code == '1') {
   				              	var pageNo = $("#currPage").val();           
   				              	searchData(pageNo);
   				              	tipOk("删除成功!");
   				             } else {
   				            	 tipError("删除失败!");
   				             }
   				        });
   					}else{
   						//取消
   					}
   				});
   		    }
   			
   		}
   		// 单条删除
	function del(adminId){
   		   if (adminId != ""){ 
   			 bootbox.confirm("你确定要删除吗?","取消","确定", function(result) {
   				if(result){
   					$.post("<c:url value='/manageAdminUser/removeManageAdminUser'/>",
   		        	{
   						adminId	:adminId,
   						ranNum:Math.random()
   					},
   		        	function(data){
   			        	var result = eval('('+data+')'); 
   			            if (result.code == '1') {
   			              	var pageNo = $("#currPage").val();           
   			              	searchData(pageNo);
   			              	tipOk("删除成功!");
   			             } else {
   			              	tipError("删除失败!");
   			             }
   			        });
   				}else{
   					//取消
   				}
   			});
	   	 }
  	 }
	function selOneindex(){
		$("#xxxindex").click();
		searchData3(1);
	}
	function selTwoIndex(){
		var oneAgentOpenId = $("#oneAgentOpenIdIndex").val();
		if(oneAgentOpenId!=""){
			$("#sssIndex").click();
			searchData4(1,oneAgentOpenId);
		}else{
			tipError("请选择公司!");
		}
		
	}

	function searchData3(pageNo){
		var returnNum = $("#returnNum3").val();
			var sortColumn = $("#sortColumn3").val();
		    var name = $("#fOneAgentNameIndex").val();
		    $.getJSON("<c:url value='/hCompany/getHCompanyList'/>",
	        {
	        	sortColumn:sortColumn,
	    		name : name,
	    		status : 1,
	    		verify_status : 1,
	    		pageNo: pageNo,
	    		rowCount: returnNum, 
				_t: Math.random()
	        },function(data){
	        var result = data;
	        if (result.code == 1) {
	            setTableStr3(result, pageNo, returnNum);
	        } else {
	        	tipError("系统异常!");
	        } 
	    });
	}
	function genTableHeader3(){
		var str = "<thead><tr>" ;
	    	str+= "<th scope=\"col\" class=\"check-col\"></th>";
	    	str+= "<th onselectstart='return false' scope=\"col\">序号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">公司名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">业务联系人手机</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_check_status\" column='check_status' onselectstart='return false' scope=\"col\">审核状态</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr3(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader3();
	    var number = (pageNo - 1) * returnNum;
	    tableStr += "<tfoot></tfoot>";
	    tableStr += "<tbody>";
	    var oneAgentOpenId = $("#oneAgentOpenIdIndex").val();
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        if(oneAgentOpenId!=""){
	        	if(oneAgentOpenId==result.items[k].id){
	        		tableStr += "<td ><input type=\"radio\" checked id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
	        	}else{
	        		tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
	        	}
	        }else{
	        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
	        }
	        tableStr += "<td >" + (number + k + 1) + "</td>";
		    	var st = result.items[k].status;
		    	if(st==1){st = '正常'}else if(st==0){st = '终止'}else if(st==2){st = '暂停'}
		    	var cst = result.items[k].verify_status;
		    	if(cst ==0){cst='待审核'}else if (cst==1){cst='审核通过'}
		        tableStr += "<td>" + result.items[k].name + "</td>";
		        tableStr += "<td>" + result.items[k].contact_phone + "</td>";
		        tableStr += "<td>" + st + "</td>";
		        tableStr += "<td>" + cst + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody>";
	    $("#exampleDTC3").html(tableStr);
	    $("#currPage3").val(pageNo);	
	    $("#demo4").find("radio").uniform();//初始化复选框
	    genPageTag3(pageNo,result.totalResults,returnNum,'kkpager3');
	}
	function selonagentindex(id,name){
		$("#oneAgentindex").val(name);
		$("#oneAgentOpenIdIndex").val(id);
		$("#closeOneIndex").click();
	}
	function cleanOnIndex(){
		$("#oneAgentindex").val("");
		$("#oneAgentOpenIdIndex").val("");
		$("#twoAgentIndex").val("");
		$("#twoAgentOpenIdIndex").val("");
	}
	function cleanTwoIndex(){
		$("#twoAgentIndex").val("");
		$("#twoAgentOpenIdIndex").val("");
	}
	function seltwoagentIndex(id,name){
		$("#twoAgentIndex").val(name);
		$("#twoAgentOpenIdIndex").val(id);
		$("#closeTwoIndex").click();
	}
	function searchData4(pageNo,oneAgentOpenId){
		var returnNum = $("#returnNum4").val();
			var sortColumn = $("#sortColumn4").val();
		    var name = $("#fTwoAgentNameIndex").val();
		    $.getJSON("<c:url value='/hSubCompany/getHSubCompanyList'/>",
	        {
	        	sortColumn:sortColumn,
	        	sub_name : name,
	    		status : 1,
	    		pageNo: pageNo,
	    		c_id: oneAgentOpenId,
	    		rowCount: returnNum, 
				_t: Math.random()
	        },function(data){
	        var result = data;
	        if (result.code == 1) {
	            setTableStr4(result, pageNo, returnNum);
	        } else {
	        	tipError("系统异常!");
	        } 
	    });
	}
	function genTableHeader4(){
		var str = "<thead><tr>" ;
	    	str+= "<th scope=\"col\" class=\"check-col\"></th>";
	    	str+= "<th onselectstart='return false' scope=\"col\">序号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">代理名称</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">类型</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">状态</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_check_status\" column='check_status' onselectstart='return false' scope=\"col\">审核状态</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr4(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader4();
	    var number = (pageNo - 1) * returnNum;
	    tableStr += "<tfoot></tfoot>";
	    tableStr += "<tbody>";
	    var twoAgentOpenId = $("#twoAgentOpenIdIndex").val();
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        if(twoAgentOpenId!=""){
	        	if(twoAgentOpenId==result.items[k].s_id){
	        		tableStr += "<td ><input type=\"radio\" checked id='"+result.items[k].s_id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='seltwoagentIndex(\""+result.items[k].s_id+"\",\""+result.items[k].sub_name+"\")'/></td>";
	        	}else{
	        		tableStr += "<td ><input type=\"radio\" id='"+result.items[k].s_id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='seltwoagentIndex(\""+result.items[k].s_id+"\",\""+result.items[k].sub_name+"\")'/></td>";
	        	}
	        }else{
	        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].s_id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='seltwoagentIndex(\""+result.items[k].s_id+"\",\""+result.items[k].sub_name+"\")'/></td>";
	        }
	        tableStr += "<td >" + (number + k + 1) + "</td>";
		        var ss = result.items[k].style;
		    	if(ss==1){ss='公司'}else if (ss==2){ss='个人'}
		    	var st = result.items[k].status;
		    	if(st==1){st = '正常'}else if(st==0){st = '终止'}else if(st==2){st = '暂停'}
		    	var cst = result.items[k].check_status;
		    	if(cst ==0){cst='待审核'}else if (cst==1){cst='审核通过'}
		        tableStr += "<td>" + result.items[k].sub_name + "</td>";
// 		        tableStr += "<td>" + ss + "</td>";
// 		        tableStr += "<td>" + st + "</td>";
// 		        tableStr += "<td>" + cst + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody>";
	    $("#exampleDTC4").html(tableStr);
	    $("#currPage4").val(pageNo);	
	    $("#demo5").find("radio").uniform();//初始化复选框
	    genPageTag4(pageNo,result.totalResults,returnNum,'kkpager4');
	}
	function exportExcel(){
		var returnNum = $("#returnNum").val();
		var sortColumn = $("#sortColumn").val();
	    var adminId = $("#fAdminId").val();
	    var adminName = $("#fAdminName").val();
	    var nickName = $("#fNickName").val();
	    var passwd = $("#fPasswd").val();
	    var realName = $("#fRealName").val();
	    var oneAgentOpenId = $("#oneAgentOpenIdIndex").val();
	    var twoAgentOpenId = $("#twoAgentOpenIdIndex").val();
	    var mobile = $("#fMobile").val();
	    var phone = $("#fPhone").val();
	    var lastLogin = $("#fLastLogin").val();
	    var loginIP = $("#fLoginIP").val();
	    var pwdModifyTime = $("#fPwdModifyTime").val();
	    var state = $("#fState").val();
	    var createTime = $("#fCreateTime").val();
	    var createrId = $("#fCreaterId").val();
	    var roleId = $("#fRoleId").val();
	    var startTime = $("#fStartTime").val();
	    var endTime = $("#fEndTime").val();
	    $.getJSON("<c:url value='/manageAdminUser/exportExcel'/>",
        {
        	sortColumn:sortColumn,
    		adminId : adminId,
    		adminName : adminName,
    		companyId : oneAgentOpenId,
    		twoAgentOpenId : twoAgentOpenId,
    		nickName : nickName,
    		passwd : passwd,
    		roleId : roleId,
    		realName : realName,
    		mobile : mobile,
    		startTime : startTime,
    		endTime : endTime,
    		phone : phone,
    		lastLogin : lastLogin,
    		loginIP : loginIP,
    		pwdModifyTime : pwdModifyTime,
    		state : state,
    		createTime : createTime,
    		createrId : createrId,
			_t: Math.random()
        },function(data){
            var result = data;
        	if(result.code=='1'){
        		window.open('${ctx}'+result.items);
        	}else{
        		alert(result.message);
        	}
	    });
	}
</script>
 </body>
 </html>
