<%@page import="com.base.utils.FileUploadConstants"%>
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
<title>${_title}</title>
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
	<input type="hidden" id="sortColumn" value=""><!-- 排序字段 -->
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
                                            <label class="margin5">状态:</label>
                                            <select id="fStatus">
                                            <option value="">--全部--</option>
                                            <option value="1">正常</option>
                                            <option value="0">冻结</option>
                                            </select>
                                            <label class="margin5">客户经理名称:</label>
	                                    	<input type="text" id="fOneAgentName" class="span2 margin5" placeholder="一级代理名称">
                                            <label class="margin5">代理名称:</label>
	                                    	<input type="text" id="fTwoAgentName" class="span2 margin5" placeholder="二级代理名称">
	                                    	<label class="margin5">服务人员名称:</label>
	                                    	<input type="text" id="fServerName" class="span2 margin5" placeholder="服务人员名称">
                                            <label class="margin5">昵称:</label>
	                                    	<input type="text" id="fNickName" class="span2 margin5" placeholder="昵称">
                                            <label class="margin5">电话:</label>
	                                    	<input type="text" id="fPhone" class="span2 margin5" placeholder="电话">
                                            <label class="margin5">手机:</label>
	                                    	<input type="text" id="fMobile" class="span2 margin5" placeholder="手机">
                                            <label class="margin5">单位名称:</label>
	                                    	<input type="text" id="fCompany_name" class="span2 margin5" placeholder="单位名称">
                                            <label class="margin5">角色:</label>
                                            <select id="fRole_id" class="span2 margin5">
                                            	<option value="">--请选择--</option>
                                            	<option value="<%=FileUploadConstants.ONE_AGENT_MANAGE_ROLEID%>">客户经理</option>
                                            	<option value="<%=FileUploadConstants.TWO_AGENT_MANAGE_ROLEID%>">代理</option>
                                            	<option value="<%=FileUploadConstants.SERVICER_MANAGE_ROLEID%>">服务人员</option>
                                            </select>
<!-- 	                                    	<input type="text" id="fRole_id" class="span2 margin5" placeholder="role_id"> -->
<!--                                             <label class="margin5">fp_state:</label> -->
<!-- 	                                    	<input type="text" id="fFp_state" class="span2 margin5" placeholder="fp_state"> -->
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
<%-- 	                                 	 	<perm:tag permPath="/hUserAccount/toAdd" > --%>
<!-- 	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a>  -->
<%-- 	                                 	 	</perm:tag> --%>
<%-- 	                                 	 	<perm:tag permPath="/hUserAccount/removeAllHUserAccount" > --%>
<!-- 	                                 	 	<a href="javascript:delAll();" class="btn btn-glyph"><i class="fontello-icon-minus-1"></i>删除</a>  -->
<%-- 	                                 	 	</perm:tag> --%>
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
<div id="temModal" class="modal hide fade" tabindex="-1" data-width="50%"></div>
<script type="text/javascript">
	var $modal = $('#temModal');
	
	$(document).ready(function(){
    	searchData(1);
    });
	function searchData(pageNo){
		var returnNum = $("#returnNum").val();
			var sortColumn = $("#sortColumn").val();
		    var id = $("#fId").val();
		    var openId = $("#fOpenId").val();
		    var totalFee = $("#fTotalFee").val();
		    var createTime = $("#fCreateTime").val();
		    var status = $("#fStatus").val();
		    var oneAgentOpenId = $("#fOneAgentOpenId").val();
		    var twoAgentOpenId = $("#fTwoAgentOpenId").val();
		    var oneAgentName = $("#fOneAgentName").val();
		    var twoAgentName = $("#fTwoAgentName").val();
		    var nickName = $("#fNickName").val();
		    var phone = $("#fPhone").val();
		    var mobile = $("#fMobile").val();
		    var company_name = $("#fCompany_name").val();
		    var servicerName = $("#fServerName").val();
		    var role_id = $("#fRole_id").val();
		    var fp_state = $("#fFp_state").val();
		    var remark1 = $("#fRemark1").val();
		    var remark2 = $("#fRemark2").val();
		    var remark3 = $("#fRemark3").val();
		    $.getJSON("<c:url value='/hUserAccount/getHUserAccountList'/>",
	        {
	        	sortColumn:sortColumn,
	    		id : id,
	    		openId : openId,
	    		totalFee : totalFee,
	    		servicerName : servicerName,
	    		createTime : createTime,
	    		status : status,
	    		oneAgentOpenId : oneAgentOpenId,
	    		twoAgentOpenId : twoAgentOpenId,
	    		oneAgentName : oneAgentName,
	    		twoAgentName : twoAgentName,
	    		nickName : nickName,
	    		phone : phone,
	    		mobile : mobile,
	    		company_name : company_name,
	    		role_id : role_id,
	    		fp_state : fp_state,
	    		remark1 : remark1,
	    		remark2 : remark2,
	    		remark3 : remark3,
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
	    	str+= "<th onselectstart='return false' scope=\"col\">序号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_totalFee\" column='totalFee' onselectstart='return false' scope=\"col\">账户余额</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_createTime\" width='135' column='createTime' onselectstart='return false' scope=\"col\">创建日期</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">状态</th>";
	    	str+= "<th>客户经理</th>";
	    	str+= "<th>代理</th>";
	    	str+= "<th>昵称</th>";
	    	str+= "<th>电话</th>";
	    	str+= "<th>手机</th>";
	    	str+= "<th>单位名称</th>";
	    	str+= "<th>角色</th>";
	    	str+= "<th>发票状态</th>";
			str+="<th>操	作</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader();
	    var number = (pageNo - 1) * returnNum;
        tableStr += "<tfoot></tfoot>";
        tableStr += "<tbody>";
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        tableStr += "<td ><input type=\"checkbox\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" /></td>";
	        tableStr += "<td >" + (number + k + 1) + "</td>";
	        
		        tableStr += "<td>" + result.items[k].totalFee + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].createTime) + "</td>";
		        tableStr += "<td>" + (result.items[k].status==1?"正常":"冻结") + "</td>";
		        tableStr += "<td>" + result.items[k].oneAgentName + "</td>";
		        tableStr += "<td>" + result.items[k].twoAgentName + "</td>";
		        tableStr += "<td>" + result.items[k].nickName + "</td>";
		        tableStr += "<td>" + result.items[k].phone + "</td>";
		        tableStr += "<td>" + result.items[k].mobile + "</td>";
		        tableStr += "<td>" + result.items[k].company_name + "</td>";
		        tableStr += "<td>" + result.items[k].role_name + "</td>";
		        if(result.items[k].remark1=="1"&&result.items[k].role_id=="149"){
		        	tableStr += "<td>" + (result.items[k].fp_state==1?"已开":"未开") + "</td>";
		        }else{
		        	tableStr += "<td></td>";
		        }
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	tableStr += "<a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toDeatail(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />查看明细</a>";
        	tableStr += "<perm:tag permPath='/hUserAccount/updateHUserAccount' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
//         	tableStr += "<perm:tag permPath='/hUserAccount/removeHUserAccount' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
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
	//查看详情
	function toDeatail(id){
		$("#temModal").attr("data-width","70%");
		show('<c:url value="/hUserAccount/showDetail?id='+id+'"/>','');
	}
	// 更新
	function toUpdate(id) {
		show('<c:url value="/hUserAccount/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hUserAccount/toAdd"/>','');
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
		if(ids!=''){
			bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要删除吗?","取消","确定", function(result) {
				if(result){
					$.post("<c:url value='/hUserAccount/removeAllHUserAccount'/>",
		        	{
						ids :ids,
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
			
		}else{
			tip("请选择待删除条目!");
		}
	}
		// 单条删除
	function del(id){
	   if (id != ""){
		   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要删除吗?","取消","确定", function(result) {
			if(result){
				$.post("<c:url value='/hUserAccount/removeHUserAccount'/>",
	        	{
					id	:id,
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
</script>
</body>
</html>
