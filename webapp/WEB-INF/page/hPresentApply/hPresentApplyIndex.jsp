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
	<input type="hidden" id="sortColumn" value="a.createTime desc"><!-- 排序字段 -->
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
<!--                                             <label class="margin5">id:</label> -->
<!-- 	                                    	<input type="text" id="fId" class="span2 margin5" placeholder="id"> -->
<!--                                             <label class="margin5">openId:</label> -->
<!-- 	                                    	<input type="text" id="fOpenId" class="span2 margin5" placeholder="openId"> -->
<!--                                             <label class="margin5">totalFee:</label> -->
<!-- 	                                    	<input type="text" id="fTotalFee" class="span2 margin5" placeholder="totalFee"> -->
<!--                                             <label class="margin5">createTime:</label> -->
<!-- 	                                    	<input type="text" id="fCreateTime" placeholder="createTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly"> -->
                                            <label class="margin5">批次:</label>
	                                    	<input type="text" id="fBatchCode" class="span2 margin5" placeholder="批次">
                                            <label class="margin5">审核状态:</label>
<!-- 	                                    	<input type="text" id="fStatus" class="span2 margin5" placeholder="status"> -->
	                                    	<select id="fStatus">
	                                    		<option value="">请选择</option>
	                                    		<option value="1">审核通过</option>
	                                    		<option value="0">未审核</option>
	                                    	</select>
<!--                                             <label class="margin5">oneAgentOpenId:</label>
	                                    	<input type="text" id="fOneAgentOpenId" class="span2 margin5" placeholder="oneAgentOpenId"> -->
                                            <label class="margin5">客户经理名称:</label>
	                                    	<input type="text" id="fOneAgentName" class="span2 margin5" placeholder="客户经理名称">
                                         <!--    <label class="margin5">twoAgentOpenId:</label>
	                                    	<input type="text" id="fTwoAgentOpenId" class="span2 margin5" placeholder="twoAgentOpenId"> -->
                                            <label class="margin5">代理名称:</label>
	                                    	<input type="text" id="fTwoAgentName" class="span2 margin5" placeholder="代理名称">
                            <!--                 <label class="margin5">accountDetailId:</label>
	                                    	<input type="text" id="fAccountDetailId" class="span2 margin5" placeholder="accountDetailId"> -->
                                            <!-- <label class="margin5">nice:</label>
	                                    	<input type="text" id="fNickName" class="span2 margin5" placeholder="nickName">
                                            <label class="margin5">batchCode:</label>
	                                    	<input type="text" id="fBatchCode" class="span2 margin5" placeholder="batchCode">
                                            <label class="margin5">remark1:</label>
	                                    	<input type="text" id="fRemark1" class="span2 margin5" placeholder="remark1">
                                            <label class="margin5">remark2:</label>
	                                    	<input type="text" id="fRemark2" class="span2 margin5" placeholder="remark2">
                                            <label class="margin5">remark3:</label>
	                                    	<input type="text" id="fRemark3" class="span2 margin5" placeholder="remark3">
                                            <label class="margin5">servicerId:</label>
	                                    	<input type="text" id="fServicerId" class="span2 margin5" placeholder="servicerId"> -->
                                            <label class="margin5">服务人员名称:</label>
	                                    	<input type="text" id="fServicerName" class="span2 margin5" placeholder="服务人员名称">
                                        <!-- 	    <label class="margin5">taxRate:</label>
	                                    	<input type="text" id="fTaxRate" class="span2 margin5" placeholder="taxRate">
                                            <label class="margin5">taxFee:</label>
	                                    	<input type="text" id="fTaxFee" class="span2 margin5" placeholder="taxFee">
                                            <label class="margin5">allFee:</label>
	                                    	<input type="text" id="fAllFee" class="span2 margin5" placeholder="allFee"> -->
	                                    	<label class="margin5">角色:</label>
                                            <select id="fRole_id" class="span2 margin5">
                                            	<option value="">--请选择--</option>
                                            	<option value="<%=FileUploadConstants.ONE_AGENT_MANAGE_ROLEID%>">客户经理</option>
                                            	<option value="<%=FileUploadConstants.TWO_AGENT_MANAGE_ROLEID%>">代理</option>
                                            	<option value="<%=FileUploadConstants.SERVICER_MANAGE_ROLEID%>">服务人员</option>
                                            </select>
                                            <label class="margin5">代理类型:</label>
                                            <select id="fAgentTwoStyle" class="span2 margin5">
                                            	<option value="">--请选择--</option>
                                            	<option value="1">公司</option>
                                            	<option value="2">个人</option>
                                            </select>
                                            <label class="margin5">申请开始时间:</label>
	                                    	<input type="text" id="createTime_start" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">申请结束时间:</label>
	                                    	<input type="text" id="createTime_end" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">审核开始时间:</label>
	                                    	<input type="text" id="checkTime_start" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">审核结束时间:</label>
	                                    	<input type="text" id="checkTime_end" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
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
	                                 	 	<a href="javascript:exportExport();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>导出</a> 
	                                 	 	<%-- <perm:tag permPath="/hPresentApply/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/hPresentApply/removeAllHPresentApply" >
	                                 	 	<a href="javascript:delAll();" class="btn btn-glyph"><i class="fontello-icon-minus-1"></i>删除</a> 
	                                 	 	</perm:tag> --%>
<!-- 	                                 	 	<perm:tag permPath="/hPresentApply/exportExport" > -->
<!-- 	                                 	 	</perm:tag> -->
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
		    var oneAgentName = $("#fOneAgentName").val();
		    var twoAgentOpenId = $("#fTwoAgentOpenId").val();
		    var twoAgentName = $("#fTwoAgentName").val();
		    var accountDetailId = $("#fAccountDetailId").val();
		    var role_id = $("#fRole_id").val();
		    var nickName = $("#fNickName").val();
		    var batchCode = $("#fBatchCode").val();
		    var remark1 = $("#fRemark1").val();
		    var remark2 = $("#fRemark2").val();
		    var remark3 = $("#fRemark3").val();
		    var createTime_start = $("#createTime_start").val();
		    var createTime_end = $("#createTime_end").val();
		    var checkTime_start = $("#checkTime_start").val();
		    var checkTime_end = $("#checkTime_end").val();
		    var agentTwoStyle = $("#fAgentTwoStyle").val();
		    var servicerId = $("#fServicerId").val();
		    var servicerName = $("#fServicerName").val();
		    var taxRate = $("#fTaxRate").val();
		    var taxFee = $("#fTaxFee").val();
		    var allFee = $("#fAllFee").val();
		    $.getJSON("<c:url value='/hPresentApply/getHPresentApplyList'/>",
	        {
	        	sortColumn:sortColumn,
	    		id : id,
	    		openId : openId,
	    		createTime_start : createTime_start,
	    		createTime_end : createTime_end,
	    		checkTime_start : checkTime_start,
	    		checkTime_end : checkTime_end,
	    		agentTwoStyle : agentTwoStyle,
	    		totalFee : totalFee,
	    		createTime : createTime,
	    		status : status,
	    		oneAgentOpenId : oneAgentOpenId,
	    		oneAgentName : oneAgentName,
	    		twoAgentOpenId : twoAgentOpenId,
	    		twoAgentName : twoAgentName,
	    		accountDetailId : accountDetailId,
	    		nickName : nickName,
	    		batchCode : batchCode,
	    		remark1 : remark1,
	    		remark2 : remark2,
	    		role_id : role_id,
	    		remark3 : remark3,
	    		servicerId : servicerId,
	    		servicerName : servicerName,
	    		taxRate : taxRate,
	    		taxFee : taxFee,
	    		allFee : allFee,
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
// 	    	str+= "<th class=\"sortTh\" id=\"th_id\" column='id' onselectstart='return false' scope=\"col\">id</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_openId\" column='openId' onselectstart='return false' scope=\"col\">openId</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_createTime\" width='135' column='createTime' onselectstart='return false' scope=\"col\">申请时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_totalFee\" column='totalFee' onselectstart='return false' scope=\"col\">金额</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">审核状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">类型</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">角色</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_oneAgentOpenId\" column='oneAgentOpenId' onselectstart='return false' scope=\"col\">oneAgentOpenId</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_oneAgentName\" column='oneAgentName' onselectstart='return false' scope=\"col\">名称</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_twoAgentOpenId\" column='twoAgentOpenId' onselectstart='return false' scope=\"col\">twoAgentOpenId</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_twoAgentName\" column='twoAgentName' onselectstart='return false' scope=\"col\">代理名称</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_accountDetailId\" column='accountDetailId' onselectstart='return false' scope=\"col\">accountDetailId</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_nickName\" column='nickName' onselectstart='return false' scope=\"col\">nickName</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_batchCode\" column='batchCode' onselectstart='return false' scope=\"col\">batchCode</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_remark1\" column='remark1' onselectstart='return false' scope=\"col\">remark1</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_remark2\" column='remark2' onselectstart='return false' scope=\"col\">remark2</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_remark3\" column='remark3' onselectstart='return false' scope=\"col\">remark3</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_servicerId\" column='servicerId' onselectstart='return false' scope=\"col\">servicerId</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_servicerName\" column='servicerName' onselectstart='return false' scope=\"col\">服务人员名称</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_taxRate\" column='taxRate' onselectstart='return false' scope=\"col\">税率</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_taxFee\" column='taxFee' onselectstart='return false' scope=\"col\">税费</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_allFee\" column='allFee' onselectstart='return false' scope=\"col\">个人身份证号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_allFee\" column='allFee' onselectstart='return false' scope=\"col\">手机号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_allFee\" column='allFee' onselectstart='return false' scope=\"col\">持卡人名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_allFee\" column='allFee' onselectstart='return false' scope=\"col\">开户行名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_allFee\" column='allFee' onselectstart='return false' scope=\"col\">个人银行卡卡号/公司账号</th>";
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
	        
// 		        tableStr += "<td>" + result.items[k].id + "</td>";
// 		        tableStr += "<td>" + result.items[k].openId + "</td>";
		        tableStr += "<td>" + genDate(result.items[k].createTime) + "</td>";
		        tableStr += "<td>" + result.items[k].totalFee + "</td>";
		        tableStr += "<td>" + (result.items[k].status==1?'已审核':'未审核') + "</td>";
// 		        tableStr += "<td>" + result.items[k].oneAgentOpenId + "</td>";
		        
		       
// 		        tableStr += "<td>" + result.items[k].twoAgentOpenId + "</td>";
		        if(result.items[k].twoAgentOpenId!=""){
		        	if(result.items[k].agentTwoStyle=="1"){
		        		tableStr += "<td>公司</td>";
		        	}else{
		        		tableStr += "<td>个人</td>";
		        	}
	        	 	if(result.items[k].twoAgentOpenId!=""){
			        	tableStr += "<td>代理</td>";
			        }else if(result.items[k].servicerId!=""){
			        	tableStr += "<td>服务人员</td>";
			        }else{
			        	tableStr += "<td>客户经理</td>";
			        }
		        	tableStr += "<td>" + result.items[k].twoAgentName + "</td>";
		        	tableStr += "<td>" + result.items[k].card_no + "</td>";
		        	tableStr += "<td>" + result.items[k].twoMobile1 + "</td>";
		        	tableStr += "<td>" + result.items[k].twoName + "</td>";
		        	tableStr += "<td>" + result.items[k].twoBank_name + "</td>";
		        	tableStr += "<td>" + result.items[k].twoBank_account + "</td>";
		        }else if(result.items[k].servicerId!=""){
		        	tableStr += "<td></td>";
		        	if(result.items[k].twoAgentOpenId!=""){
			        	tableStr += "<td>代理</td>";
			        }else if(result.items[k].servicerId!=""){
			        	tableStr += "<td>服务人员</td>";
			        }else{
			        	tableStr += "<td>客户经理</td>";
			        }
		        	tableStr += "<td>" + result.items[k].servicerName + "</td>";
		        	tableStr += "<td></td>";
		        	tableStr += "<td></td>";
		        	tableStr += "<td></td>";
		        	tableStr += "<td></td>";
		        	tableStr += "<td></td>";
		        }else{
		        	if(result.items[k].agentOneStyle=="1"){
		        		tableStr += "<td>公司</td>";
		        	}else{
		        		tableStr += "<td>个人</td>";
		        	}
		        	if(result.items[k].twoAgentOpenId!=""){
			        	tableStr += "<td>代理</td>";
			        }else if(result.items[k].servicerId!=""){
			        	tableStr += "<td>服务人员</td>";
			        }else{
			        	tableStr += "<td>客户经理</td>";
			        }
		        	tableStr += "<td>" + result.items[k].oneAgentName + "</td>";
		        	tableStr += "<td>" + result.items[k].card_no_1 + "</td>";
		        	tableStr += "<td>" + result.items[k].oneMobile1 + "</td>";
		        	tableStr += "<td>" + result.items[k].oneName + "</td>";
		        	tableStr += "<td>" + result.items[k].oneBank_name + "</td>";
		        	tableStr += "<td>" + result.items[k].oneBank_account + "</td>";
		        }
		        
// 		        tableStr += "<td>" + result.items[k].accountDetailId + "</td>";
// 		        tableStr += "<td>" + result.items[k].nickName + "</td>";
// 		        tableStr += "<td>" + result.items[k].batchCode + "</td>";
// 		        tableStr += "<td>" + result.items[k].remark1 + "</td>";
// 		        tableStr += "<td>" + result.items[k].remark2 + "</td>";
// 		        tableStr += "<td>" + result.items[k].remark3 + "</td>";
// 		        tableStr += "<td>" + result.items[k].servicerId + "</td>";
// 		        tableStr += "<td>" + result.items[k].servicerName + "</td>";
// 		        tableStr += "<td>" + result.items[k].taxRate + "</td>";
// 		        tableStr += "<td>" + result.items[k].taxFee + "</td>";
// 		        tableStr += "<td>" + result.items[k].allFee + "</td>";
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
//         	tableStr += "<perm:tag permPath='/hPresentApply/updateHPresentApply' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	if(result.items[k].status!=1){
        		tableStr += "<perm:tag permPath='/hPresentApply/removeHPresentApply' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
	        	tableStr += "<perm:tag permPath='/hPresentApply/review' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='review(" + result.items[k].id + ");return false;'><i class='fontello-icon-minus-1' />审核</a></perm:tag>";
        	}
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
	// 更新
	function toUpdate(id) {
		show('<c:url value="/hPresentApply/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hPresentApply/toAdd"/>','');
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
					$.post("<c:url value='/hPresentApply/removeAllHPresentApply'/>",
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
				$.post("<c:url value='/hPresentApply/removeHPresentApply'/>",
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
	function review(id){
	   if (id != ""){
		   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定审核通过吗?","取消","确定", function(result) {
			if(result){
				$.post("<c:url value='/hPresentApply/review'/>",
	        	{
					id	:id,
					ranNum:Math.random()
				},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	var pageNo = $("#currPage").val();           
		              	searchData(pageNo);
		              	tipOk("审核成功!");
		             } else {
		              	tipError("审核失败!");
		             }
		        });
			}else{
				//取消
			}
		});
   	   }
    }
	
	function exportExport(){
		var returnNum = $("#returnNum").val();
		var sortColumn = $("#sortColumn").val();
	    var id = $("#fId").val();
	    var openId = $("#fOpenId").val();
	    var totalFee = $("#fTotalFee").val();
	    var createTime = $("#fCreateTime").val();
	    var status = $("#fStatus").val();
	    var oneAgentOpenId = $("#fOneAgentOpenId").val();
	    var oneAgentName = $("#fOneAgentName").val();
	    var twoAgentOpenId = $("#fTwoAgentOpenId").val();
	    var twoAgentName = $("#fTwoAgentName").val();
	    var accountDetailId = $("#fAccountDetailId").val();
	    var nickName = $("#fNickName").val();
	    var batchCode = $("#fBatchCode").val();
	    var remark1 = $("#fRemark1").val();
	    var role_id = $("#fRole_id").val();
	    var remark2 = $("#fRemark2").val();
	    var remark3 = $("#fRemark3").val();
	    var servicerId = $("#fServicerId").val();
	    var servicerName = $("#fServicerName").val();
	    var taxRate = $("#fTaxRate").val();
	    var taxFee = $("#fTaxFee").val();
	    var allFee = $("#fAllFee").val();
	    var createTime_start = $("#createTime_start").val();
	    var createTime_end = $("#createTime_end").val();
	    var checkTime_start = $("#checkTime_start").val();
	    var checkTime_end = $("#checkTime_end").val();
	    var agentTwoStyle = $("#fAgentTwoStyle").val();
	    $.getJSON("<c:url value='/hPresentApply/exportExport'/>",
        {
        	sortColumn:sortColumn,
    		id : id,
    		openId : openId,
    		role_id : role_id,
    		agentTwoStyle : agentTwoStyle,
    		totalFee : totalFee,
    		createTime : createTime,
    		status : status,
    		oneAgentOpenId : oneAgentOpenId,
    		oneAgentName : oneAgentName,
    		twoAgentOpenId : twoAgentOpenId,
    		twoAgentName : twoAgentName,
    		accountDetailId : accountDetailId,
    		nickName : nickName,
    		batchCode : batchCode,
    		remark1 : remark1,
    		remark2 : remark2,
    		remark3 : remark3,
    		servicerId : servicerId,
    		servicerName : servicerName,
    		taxRate : taxRate,
    		taxFee : taxFee,
    		allFee : allFee,
    		createTime_start : createTime_start,
    		createTime_end : createTime_end,
    		checkTime_start : checkTime_start,
    		checkTime_end : checkTime_end,
			_t: Math.random()
        },function(data){
        var result = data;
        if (result.code == 1) {
        	window.open('${ctx}'+result.items);
        } else {
        	if(result.message="nodata"){
        		tipError("暂无数据!");
        	}else{
        		tipError("系统异常");
        	}
        } 
    });
	}
</script>
</body>
</html>
