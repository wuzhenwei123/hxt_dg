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
	<input type="hidden" id="sortColumn" value=" a.create_time desc "><!-- 排序字段 -->
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
                                            <label class="margin5">客户名称:</label>
	                                    	<input type="text" id="fName" class="span2 margin5" placeholder="客户名称">
                                            <label class="margin5">业务联系人名称 :</label>
	                                    	<input type="text" id="fContact_name" class="span2 margin5" placeholder="业务联系人名称">
                                            <label class="margin5">业务联系人电话:</label>
	                                    	<input type="text" id="fContact_phone" class="span2 margin5" placeholder="业务联系人电话">
                                            <label class="margin5">业务联系人邮箱:</label>
	                                    	<input type="text" id="fContact_mail" class="span2 margin5" placeholder="业务联系人邮箱">
                                           <!--  <label class="margin5">fp_name:</label>
	                                    	<input type="text" id="fFp_name" class="span2 margin5" placeholder="fp_name">
                                            <label class="margin5">fp_phone:</label>
	                                    	<input type="text" id="fFp_phone" class="span2 margin5" placeholder="fp_phone">
                                            <label class="margin5">fp_telephone:</label>
	                                    	<input type="text" id="fFp_telephone" class="span2 margin5" placeholder="fp_telephone">
                                            <label class="margin5">fp_address:</label>
	                                    	<input type="text" id="fFp_address" class="span2 margin5" placeholder="fp_address"> -->
                                            <label class="margin5">传真总机:</label>
	                                    	<input type="text" id="fFax" class="span2 margin5" placeholder="传真总机">
                                            <label class="margin5">传真分机:</label>
	                                    	<input type="text" id="fFax_ext" class="span2 margin5" placeholder="传真分机">
                                            <!-- <label class="margin5">ywyId:</label> -->
	                                    	<input type="hidden" id="fYwyId" value='${ywyadmin }'>
                                            <label class="margin5">状态:</label>
                                            <select id="fStatus">
	                                    		<option value="">全部</option>
	                                    		<option value="1">正常</option>
	                                    		<option value="0">禁用</option>
	                                    	</select>
<!--                                             <label class="margin5">审核状态:</label> -->
<!-- 	                                    	<input type="text" id="fVerify_status" class="span2 margin5" placeholder="verify_status"> -->
<!-- 	                                    	<select id="fVerify_status"> -->
<!-- 	                                    		<option value="">全部</option> -->
<!-- 	                                    		<option value="1">审核通过</option> -->
<!-- 	                                    		<option value="2">审核驳回</option> -->
<!-- 	                                    		<option value="0">待审核</option> -->
<!-- 	                                    	</select> -->
											<input type='hidden' value='${verifyStatus }' id='fVerify_status'>
                                            <label class="margin5">创建时间:</label>
	                                    	<input type="text" id="fCreate_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <!--<label class="margin5">verify_reason:</label>
	                                    	<input type="text" id="fVerify_reason" class="span2 margin5" placeholder="verify_reason">
                                            <label class="margin5">verify_user_id:</label>
	                                    	<input type="text" id="fVerify_user_id" class="span2 margin5" placeholder="verify_user_id">
                                            <label class="margin5">com_bank_code:</label>
	                                    	<input type="text" id="fCom_bank_code" class="span2 margin5" placeholder="com_bank_code">
                                            <label class="margin5">com_license_no:</label>
	                                    	<input type="text" id="fCom_license_no" class="span2 margin5" placeholder="com_license_no">
                                            <label class="margin5">com_license_img:</label>
	                                    	<input type="text" id="fCom_license_img" class="span2 margin5" placeholder="com_license_img">
                                            <label class="margin5">com_tax_no:</label>
	                                    	<input type="text" id="fCom_tax_no" class="span2 margin5" placeholder="com_tax_no">
                                            <label class="margin5">com_tax_img:</label>
	                                    	<input type="text" id="fCom_tax_img" class="span2 margin5" placeholder="com_tax_img">
                                            <label class="margin5">com_dept_code:</label>
	                                    	<input type="text" id="fCom_dept_code" class="span2 margin5" placeholder="com_dept_code">
                                            <label class="margin5">com_dept_img:</label>
	                                    	<input type="text" id="fCom_dept_img" class="span2 margin5" placeholder="com_dept_img">
                                            <label class="margin5">com_duty_no:</label>
	                                    	<input type="text" id="fCom_duty_no" class="span2 margin5" placeholder="com_duty_no">
                                            <label class="margin5">com_bank_name:</label>
	                                    	<input type="text" id="fCom_bank_name" class="span2 margin5" placeholder="com_bank_name">
                                            <label class="margin5">com_account_name:</label>
	                                    	<input type="text" id="fCom_account_name" class="span2 margin5" placeholder="com_account_name">
                                            <label class="margin5">com_account_no:</label>
	                                    	<input type="text" id="fCom_account_no" class="span2 margin5" placeholder="com_account_no">
                                            <label class="margin5">remark1:</label>
	                                    	<input type="text" id="fRemark1" class="span2 margin5" placeholder="remark1">
                                            <label class="margin5">remark2:</label>
	                                    	<input type="text" id="fRemark2" class="span2 margin5" placeholder="remark2">
                                            <label class="margin5">remark3:</label>
	                                    	<input type="text" id="fRemark3" class="span2 margin5" placeholder="remark3">
                                            <label class="margin5">remark4:</label>
	                                    	<input type="text" id="fRemark4" class="span2 margin5" placeholder="remark4">
                                            <label class="margin5">remark5:</label>
	                                    	<input type="text" id="fRemark5" class="span2 margin5" placeholder="remark5">
                                            <label class="margin5">remark6:</label>
	                                    	<input type="text" id="fRemark6" class="span2 margin5" placeholder="remark6"> -->
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
	                                 	 	<perm:tag permPath="/hCompany/toAdd2" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/hCompany/removeAllHCompany2" >
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
		    var name = $("#fName").val();
		    var user_id = $("#fUser_id").val();
		    var contact_name = $("#fContact_name").val();
		    var contact_phone = $("#fContact_phone").val();
		    var contact_mail = $("#fContact_mail").val();
		    var fp_name = $("#fFp_name").val();
		    var fp_phone = $("#fFp_phone").val();
		    var fp_telephone = $("#fFp_telephone").val();
		    var fp_address = $("#fFp_address").val();
		    var fax = $("#fFax").val();
		    var fax_ext = $("#fFax_ext").val();
		    var ywyId = $("#fYwyId").val();
		    var status = $("#fStatus").val();
		    var create_time = $("#fCreate_time").val();
		    var verify_reason = $("#fVerify_reason").val();
		    var verify_user_id = $("#fVerify_user_id").val();
		    var com_bank_code = $("#fCom_bank_code").val();
		    var verify_status = $("#fVerify_status").val();
		    var com_license_no = $("#fCom_license_no").val();
		    var com_license_img = $("#fCom_license_img").val();
		    var com_tax_no = $("#fCom_tax_no").val();
		    var com_tax_img = $("#fCom_tax_img").val();
		    var com_dept_code = $("#fCom_dept_code").val();
		    var com_dept_img = $("#fCom_dept_img").val();
		    var com_duty_no = $("#fCom_duty_no").val();
		    var com_bank_name = $("#fCom_bank_name").val();
		    var com_account_name = $("#fCom_account_name").val();
		    var com_account_no = $("#fCom_account_no").val();
		    var remark1 = $("#fRemark1").val();
		    var remark2 = $("#fRemark2").val();
		    var remark3 = $("#fRemark3").val();
		    var remark4 = $("#fRemark4").val();
		    var remark5 = $("#fRemark5").val();
		    var remark6 = $("#fRemark6").val();
		    $.getJSON("<c:url value='/hCompany/getHCompanyList'/>",
	        {
	        	sortColumn:sortColumn,
	    		id : id,
	    		name : name,
	    		user_id : user_id,
	    		contact_name : contact_name,
	    		contact_phone : contact_phone,
	    		contact_mail : contact_mail,
	    		fp_name : fp_name,
	    		fp_phone : fp_phone,
	    		fp_telephone : fp_telephone,
	    		fp_address : fp_address,
	    		fax : fax,
	    		fax_ext : fax_ext,
	    		ywyId : ywyId,
	    		status : status,
	    		create_time : create_time,
	    		verify_reason : verify_reason,
	    		verify_user_id : verify_user_id,
	    		com_bank_code : com_bank_code,
	    		verify_status : verify_status,
	    		com_license_no : com_license_no,
	    		com_license_img : com_license_img,
	    		com_tax_no : com_tax_no,
	    		com_tax_img : com_tax_img,
	    		com_dept_code : com_dept_code,
	    		com_dept_img : com_dept_img,
	    		com_duty_no : com_duty_no,
	    		com_bank_name : com_bank_name,
	    		com_account_name : com_account_name,
	    		com_account_no : com_account_no,
	    		remark1 : remark1,
	    		remark2 : remark2,
	    		remark3 : remark3,
	    		remark4 : remark4,
	    		remark5 : remark5,
	    		remark6 : remark6,
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
	    	str+= "<th onselectstart='return false' scope=\"col\">序号</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_id\" column='id' onselectstart='return false' scope=\"col\">id</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">单位名称</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_user_id\" column='user_id' onselectstart='return false' scope=\"col\">user_id</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_contact_name\" column='contact_name' onselectstart='return false' scope=\"col\">业务联系人</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_contact_phone\" column='contact_phone' onselectstart='return false' scope=\"col\">业务联系人手机</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_contact_mail\" column='contact_mail' onselectstart='return false' scope=\"col\">业务联系人邮箱</th>";
	    	/* str+= "<th class=\"sortTh\" id=\"th_fp_name\" column='fp_name' onselectstart='return false' scope=\"col\">发票收件人姓名</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_fp_phone\" column='fp_phone' onselectstart='return false' scope=\"col\">发票收件人手机</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_fp_telephone\" column='fp_telephone' onselectstart='return false' scope=\"col\">发票收件人电话</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_fp_address\" column='fp_address' onselectstart='return false' scope=\"col\">发票收件人地址</th>"; */
	    	str+= "<th class=\"sortTh\" id=\"th_fax\" column='fax' onselectstart='return false' scope=\"col\">传真总机</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_fax_ext\" column='fax_ext' onselectstart='return false' scope=\"col\">传真分机</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_ywyId\" column='ywyId' onselectstart='return false' scope=\"col\">业务员</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_verify_status\" column='verify_status' onselectstart='return false' scope=\"col\">审核状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_create_time\" width='135' column='create_time' onselectstart='return false' scope=\"col\">创建时间</th>";
	    	/* str+= "<th class=\"sortTh\" id=\"th_verify_reason\" column='verify_reason' onselectstart='return false' scope=\"col\">verify_reason</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_verify_user_id\" column='verify_user_id' onselectstart='return false' scope=\"col\">verify_user_id</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_com_bank_code\" column='com_bank_code' onselectstart='return false' scope=\"col\">com_bank_code</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_verify_status\" column='verify_status' onselectstart='return false' scope=\"col\">verify_status</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_com_license_no\" column='com_license_no' onselectstart='return false' scope=\"col\">com_license_no</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_com_license_img\" column='com_license_img' onselectstart='return false' scope=\"col\">com_license_img</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_com_tax_no\" column='com_tax_no' onselectstart='return false' scope=\"col\">com_tax_no</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_com_tax_img\" column='com_tax_img' onselectstart='return false' scope=\"col\">com_tax_img</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_com_dept_code\" column='com_dept_code' onselectstart='return false' scope=\"col\">com_dept_code</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_com_dept_img\" column='com_dept_img' onselectstart='return false' scope=\"col\">com_dept_img</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_com_duty_no\" column='com_duty_no' onselectstart='return false' scope=\"col\">com_duty_no</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_com_bank_name\" column='com_bank_name' onselectstart='return false' scope=\"col\">com_bank_name</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_com_account_name\" column='com_account_name' onselectstart='return false' scope=\"col\">com_account_name</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_com_account_no\" column='com_account_no' onselectstart='return false' scope=\"col\">com_account_no</th>"; */
/* 	    	str+= "<th class=\"sortTh\" id=\"th_remark1\" column='remark1' onselectstart='return false' scope=\"col\">remark1</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_remark2\" column='remark2' onselectstart='return false' scope=\"col\">remark2</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_remark3\" column='remark3' onselectstart='return false' scope=\"col\">remark3</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_remark4\" column='remark4' onselectstart='return false' scope=\"col\">remark4</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_remark5\" column='remark5' onselectstart='return false' scope=\"col\">remark5</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_remark6\" column='remark6' onselectstart='return false' scope=\"col\">remark6</th>"; */
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
		        tableStr += "<td>" + result.items[k].name + "</td>";
// 		        tableStr += "<td>" + result.items[k].user_id + "</td>";
		        tableStr += "<td>" + result.items[k].contact_name + "</td>";
		        tableStr += "<td>" + result.items[k].contact_phone + "</td>";
		        tableStr += "<td>" + result.items[k].contact_mail + "</td>";
		        /* tableStr += "<td>" + result.items[k].fp_name + "</td>";
		        tableStr += "<td>" + result.items[k].fp_phone + "</td>";
		        tableStr += "<td>" + result.items[k].fp_telephone + "</td>";
		        tableStr += "<td>" + result.items[k].fp_address + "</td>"; */
		        tableStr += "<td>" + result.items[k].fax + "</td>";
		        tableStr += "<td>" + result.items[k].fax_ext + "</td>";
		        tableStr += "<td>" + result.items[k].ywyNick + "</td>";
		        var verify_status = result.items[k].verify_status;
		        var ver_sta_str='';
		        if(verify_status==1){ver_sta_str='通过';}else if(verify_status==2){ver_sta_str='驳回';}else{ver_sta_str='待审核';}
		        tableStr += "<td>" + ver_sta_str + "</td>";
		        var statusStr = result.items[k].status;
		        if(statusStr==1){
		        	statusStr = '正常';
		        }else if (statusStr==2){
		        	statusStr = "暂停";
		        }else{
		        	statusStr = "无效";
		        }
		        tableStr += "<td>" + statusStr + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].create_time) + "</td>";
		        /* tableStr += "<td>" + result.items[k].verify_reason + "</td>";
		        tableStr += "<td>" + result.items[k].verify_user_id + "</td>";
		        tableStr += "<td>" + result.items[k].com_bank_code + "</td>";
		        tableStr += "<td>" + result.items[k].com_license_no + "</td>";
		        tableStr += "<td>" + result.items[k].com_license_img + "</td>";
		        tableStr += "<td>" + result.items[k].com_tax_no + "</td>";
		        tableStr += "<td>" + result.items[k].com_tax_img + "</td>";
		        tableStr += "<td>" + result.items[k].com_dept_code + "</td>";
		        tableStr += "<td>" + result.items[k].com_dept_img + "</td>";
		        tableStr += "<td>" + result.items[k].com_duty_no + "</td>";
		        tableStr += "<td>" + result.items[k].com_bank_name + "</td>";
		        tableStr += "<td>" + result.items[k].com_account_name + "</td>";
		        tableStr += "<td>" + result.items[k].com_account_no + "</td>"; */
/* 		        tableStr += "<td>" + result.items[k].remark1 + "</td>";
		        tableStr += "<td>" + result.items[k].remark2 + "</td>";
		        tableStr += "<td>" + result.items[k].remark3 + "</td>";
		        tableStr += "<td>" + result.items[k].remark4 + "</td>";
		        tableStr += "<td>" + result.items[k].remark5 + "</td>";
		        tableStr += "<td>" + result.items[k].remark6 + "</td>"; */
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	if(verify_status==0){
	        	tableStr += "<perm:tag permPath='/hCompany/toInfo2' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toInfo(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />审核</a></perm:tag>";
        	}
        	var _status = result.items[k].status;
        	if(_status==1){
	        	tableStr += "<perm:tag permPath='/hCompany/verify2' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toVerify(" + result.items[k].id + ",2);return false;'><i class='fontello-icon-edit' />暂停</a></perm:tag>";
        	}else if(_status==2){
	        	tableStr += "<perm:tag permPath='/hCompany/verify2' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toVerify(" + result.items[k].id + ",1);return false;'><i class='fontello-icon-edit' />恢复</a></perm:tag>";
        	}
        	tableStr += "<perm:tag permPath='/hCompany/updateHCompany2' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hCompany/removeHCompany2' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
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
		show('<c:url value="/hCompany/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hCompany/toAdd"/>','');
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
					$.post("<c:url value='/hCompany/removeAllHCompany'/>",
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
				$.post("<c:url value='/hCompany/removeHCompany'/>",
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
	//审核	
	function toInfo(id){
		show('<c:url value="/hCompany/toInfo/'+id+'"/>','');
	}
	
	//暂停恢复
	function toVerify(id,type){
		if (id != ""){
			var verStr = '暂停';
			if(type==1){
				verStr = '恢复';
			}
			var html = "";
			   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要"+verStr+"吗?","取消","确定", function(result) {
				if(result){
					$.post("<c:url value='/hCompany/stop'/>",
		        	{
						id	:id,
						type:type,
						ranNum:Math.random()
					},
		        	function(data){
			        	var result = eval('('+data+')'); 
			            if (result.code == '1') {
			              	var pageNo = $("#currPage").val();           
			              	searchData(pageNo);
			              	tipOk(verStr+"成功!");
			             } else {
			              	tipError(verStr+"失败!");
			             }
			        });
				}else{
					//取消
				}
			});
	   	   }
	}

	function checkFax(o) {
		var pattern = /\d{7,8}/;
		//var pattern =/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/; 
		if (o != "") {
			if (!pattern.exec(o)) {
				return false;
			}
		}
		return true;
	}
	function exportExcel(){
		var returnNum = $("#returnNum").val();
		var sortColumn = $("#sortColumn").val();
	    var id = $("#fId").val();
	    var name = $("#fName").val();
	    var user_id = $("#fUser_id").val();
	    var contact_name = $("#fContact_name").val();
	    var contact_phone = $("#fContact_phone").val();
	    var contact_mail = $("#fContact_mail").val();
	    var fp_name = $("#fFp_name").val();
	    var fp_phone = $("#fFp_phone").val();
	    var fp_telephone = $("#fFp_telephone").val();
	    var fp_address = $("#fFp_address").val();
	    var fax = $("#fFax").val();
	    var fax_ext = $("#fFax_ext").val();
	    var ywyId = $("#fYwyId").val();
	    var status = $("#fStatus").val();
	    var create_time = $("#fCreate_time").val();
	    var verify_reason = $("#fVerify_reason").val();
	    var verify_user_id = $("#fVerify_user_id").val();
	    var com_bank_code = $("#fCom_bank_code").val();
	    var verify_status = $("#fVerify_status").val();
	    var com_license_no = $("#fCom_license_no").val();
	    var com_license_img = $("#fCom_license_img").val();
	    var com_tax_no = $("#fCom_tax_no").val();
	    var com_tax_img = $("#fCom_tax_img").val();
	    var com_dept_code = $("#fCom_dept_code").val();
	    var com_dept_img = $("#fCom_dept_img").val();
	    var com_duty_no = $("#fCom_duty_no").val();
	    var com_bank_name = $("#fCom_bank_name").val();
	    var com_account_name = $("#fCom_account_name").val();
	    var com_account_no = $("#fCom_account_no").val();
	    var remark1 = $("#fRemark1").val();
	    var remark2 = $("#fRemark2").val();
	    var remark3 = $("#fRemark3").val();
	    var remark4 = $("#fRemark4").val();
	    var remark5 = $("#fRemark5").val();
	    var remark6 = $("#fRemark6").val();
	    $.getJSON("<c:url value='/hCompany/exportExcel'/>",
        {
        	sortColumn:sortColumn,
    		id : id,
    		name : name,
    		user_id : user_id,
    		contact_name : contact_name,
    		contact_phone : contact_phone,
    		contact_mail : contact_mail,
    		fp_name : fp_name,
    		fp_phone : fp_phone,
    		fp_telephone : fp_telephone,
    		fp_address : fp_address,
    		fax : fax,
    		fax_ext : fax_ext,
    		ywyId : ywyId,
    		status : status,
    		create_time : create_time,
    		verify_reason : verify_reason,
    		verify_user_id : verify_user_id,
    		com_bank_code : com_bank_code,
    		verify_status : verify_status,
    		com_license_no : com_license_no,
    		com_license_img : com_license_img,
    		com_tax_no : com_tax_no,
    		com_tax_img : com_tax_img,
    		com_dept_code : com_dept_code,
    		com_dept_img : com_dept_img,
    		com_duty_no : com_duty_no,
    		com_bank_name : com_bank_name,
    		com_account_name : com_account_name,
    		com_account_no : com_account_no,
    		remark1 : remark1,
    		remark2 : remark2,
    		remark3 : remark3,
    		remark4 : remark4,
    		remark5 : remark5,
    		remark6 : remark6,
    		exportType:2
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
