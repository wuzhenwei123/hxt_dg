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
	<input type="hidden" id="sortColumn" value=" a.id desc "><!-- 排序字段 -->
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
                                            <label class="margin5">名称:</label>
	                                    	<input type="text" id="fName" class="span2 margin5" placeholder="名称">
                                            <label class="margin5">证件号:</label>
	                                    	<input type="text" id="fCard_no" class="span2 margin5" placeholder="证件号">
                                            <label class="margin5">性别:</label>
	                                    	<select id="fSex">
	                                    		<option value="">--请选择--</option>
	                                    		<option value="1">男</option>
	                                    		<option value="0">女</option>
	                                    	</select>
                                            <label class="margin5">手机:</label>
	                                    	<input type="text" id="fMobile1" class="span2 margin5" placeholder="手机">
<!--                                             <label class="margin5">style:</label> -->
<!-- 	                                    	<input type="text" id="fStyle" class="span2 margin5" placeholder="style"> -->
                                            <label class="margin5">状态:</label>
                                            <select id="fStatus">
                                            <option >--请选择--</option>
                                            <option value="0">终止</option>
                                            <option value="1">正常</option>
                                            <option value="2">暂停</option>
                                            </select>
<!--                                             <label class="margin5">create_time:</label> -->
<!-- 	                                    	<input type="text" id="fCreate_time" placeholder="create_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly"> -->
<!--                                             <label class="margin5">create_openId:</label> -->
<!-- 	                                    	<input type="text" id="fCreate_openId" class="span2 margin5" placeholder="create_openId"> -->
<!--                                             <label class="margin5">check_status:</label> -->
<!-- 	                                    	<input type="text" id="fCheck_status" class="span2 margin5" placeholder="check_status"> -->
<!--                                             <label class="margin5">contact_person:</label> -->
<!-- 	                                    	<input type="text" id="fContact_person" class="span2 margin5" placeholder="contact_person"> -->
<!--                                             <label class="margin5">tax_code:</label> -->
<!-- 	                                    	<input type="text" id="fTax_code" class="span2 margin5" placeholder="tax_code"> -->
<!--                                             <label class="margin5">bank_name:</label> -->
<!-- 	                                    	<input type="text" id="fBank_name" class="span2 margin5" placeholder="bank_name"> -->
<!--                                             <label class="margin5">bank_number:</label> -->
<!-- 	                                    	<input type="text" id="fBank_number" class="span2 margin5" placeholder="bank_number"> -->
<!--                                             <label class="margin5">bank_account:</label> -->
<!-- 	                                    	<input type="text" id="fBank_account" class="span2 margin5" placeholder="bank_account"> -->
<!--                                             <label class="margin5">contract_number:</label> -->
<!-- 	                                    	<input type="text" id="fContract_number" class="span2 margin5" placeholder="contract_number"> -->
<!--                                             <label class="margin5">contract_start_time:</label> -->
<!-- 	                                    	<input type="text" id="fContract_start_time" placeholder="contract_start_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly"> -->
<!--                                             <label class="margin5">contract_end_time:</label> -->
<!-- 	                                    	<input type="text" id="fContract_end_time" placeholder="contract_end_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly"> -->
<!--                                             <label class="margin5">remark1:</label> -->
<!-- 	                                    	<input type="text" id="fRemark1" class="span2 margin5" placeholder="remark1"> -->
<!--                                             <label class="margin5">remark2:</label> -->
<!-- 	                                    	<input type="text" id="fRemark2" class="span2 margin5" placeholder="remark2"> -->
<!--                                             <label class="margin5">remark3:</label> -->
<!-- 	                                    	<input type="text" id="fRemark3" class="span2 margin5" placeholder="remark3"> -->
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
	                                 	 	<perm:tag permPath="/hAgent/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
<%-- 	                                 	 	<perm:tag permPath="/hAgent/removeAllHAgent" > --%>
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
<%@ include file="/WEB-INF/page/hAgentPP/guliYL.jsp" %>
 <%@ include file="/WEB-INF/page/hAgentPP/guliSJ.jsp" %>
 <script type="text/javascript" src="${ctx}/js/page.js"></script> 
<!-- Only This Demo Page --> 
<div id="temModal" class="modal hide fade" tabindex="-1" data-width="80%"></div>
<a href="#stack2" id="sss" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a>
<a href="#stack3" id="ssss" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a>
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
		    var name = $("#fName").val();
		    var licence_url = $("#fLicence_url").val();
		    var licence_code = $("#fLicence_code").val();
		    var tax_url = $("#fTax_url").val();
		    var legal_person = $("#fLegal_person").val();
		    var card_style = $("#fCard_style").val();
		    var card_no = $("#fCard_no").val();
		    var sex = $("#fSex").val();
		    var mobile1 = $("#fMobile1").val();
		    var mobile2 = $("#fMobile2").val();
		    var style = $("#fStyle").val();
		    var status = $("#fStatus").val();
		    var create_time = $("#fCreate_time").val();
		    var create_openId = $("#fCreate_openId").val();
		    var check_status = $("#fCheck_status").val();
		    var contact_person = $("#fContact_person").val();
		    var tax_code = $("#fTax_code").val();
		    var bank_name = $("#fBank_name").val();
		    var bank_number = $("#fBank_number").val();
		    var bank_account = $("#fBank_account").val();
		    var contract_number = $("#fContract_number").val();
		    var contract_start_time = $("#fContract_start_time").val();
		    var contract_end_time = $("#fContract_end_time").val();
		    var remark1 = $("#fRemark1").val();
		    var remark2 = $("#fRemark2").val();
		    var remark3 = $("#fRemark3").val();
		    $.getJSON("<c:url value='/hAgent/getAgentPP'/>",
	        {
	        	sortColumn:sortColumn,
	    		id : id,
	    		openId : openId,
	    		name : name,
	    		licence_url : licence_url,
	    		licence_code : licence_code,
	    		tax_url : tax_url,
	    		legal_person : legal_person,
	    		card_style : card_style,
	    		card_no : card_no,
	    		sex : sex,
	    		mobile1 : mobile1,
	    		mobile2 : mobile2,
	    		style : style,
	    		status : status,
	    		create_time : create_time,
	    		create_openId : create_openId,
	    		check_status : check_status,
	    		contact_person : contact_person,
	    		tax_code : tax_code,
	    		bank_name : bank_name,
	    		bank_number : bank_number,
	    		bank_account : bank_account,
	    		contract_number : contract_number,
	    		contract_start_time : contract_start_time,
	    		contract_end_time : contract_end_time,
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
	    	str+="<th>操	作</th>";
	    	str+= "<th width='50'>名称</th>";
// 	    	str+= "<th width='100'>营业执照编号</th>";
// 	    	str+= "<th width='50'>法人姓名</th>";
	    	str+= "<th width='10'>性别</th>";
	    	str+= "<th width='50'>手机</th>";
	    	str+= "<th width='10' class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">类型</th>";
	    	str+= "<th width='50' class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_create_time\" width='135' column='create_time' onselectstart='return false' scope=\"col\">创建时间</th>";
	    	str+= "<th width='10' class=\"sortTh\" id=\"th_check_status\" column='check_status' onselectstart='return false' scope=\"col\">审核状态</th>";
	    	str+= "<th>联系人姓名</th>";
	    	str+= "<th>银联B2B鼓励金简称</th>";
	    	str+= "<th>银联B2B鼓励金类型</th>";
	    	str+= "<th>银联B2B鼓励金按笔金额</th>";
	    	str+= "<th>银联B2B鼓励分润比例</th>";
	    	str+= "<th>手机支付鼓励金简称</th>";
	    	str+= "<th>手机支付鼓励金类型</th>";
	    	str+= "<th>手机支付鼓励金按笔金额</th>";
	    	str+= "<th>手机支付鼓励分润比例</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_tax_code\" column='tax_code' onselectstart='return false' scope=\"col\">tax_code</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_bank_name\" column='bank_name' onselectstart='return false' scope=\"col\">bank_name</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_bank_number\" column='bank_number' onselectstart='return false' scope=\"col\">bank_number</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_bank_account\" column='bank_account' onselectstart='return false' scope=\"col\">bank_account</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_contract_number\" column='contract_number' onselectstart='return false' scope=\"col\">合同编号</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_contract_start_time\" width='135' column='contract_start_time' onselectstart='return false' scope=\"col\">合同开始时间</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_contract_end_time\" width='135' column='contract_end_time' onselectstart='return false' scope=\"col\">合同截止时间</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_remark1\" column='remark1' onselectstart='return false' scope=\"col\">remark1</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_remark2\" column='remark2' onselectstart='return false' scope=\"col\">remark2</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_remark3\" column='remark3' onselectstart='return false' scope=\"col\">remark3</th>";
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
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	tableStr += "<perm:tag permPath='/hAgent/payGuliYL' ><a class='btn btn-green btn-mini no-wrap' href='javascript:void(0);' onclick='payGuliYL(" + result.items[k].id + "," + result.items[k].gl_yl_id + ");return false;'>银联缴费鼓励金匹配</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hAgent/payGuliSJ' ><a class='btn btn-blue btn-mini no-wrap' href='javascript:void(0);' onclick='payGuliSJ(" + result.items[k].id + "," + result.items[k].gl_sj_id + ");return false;'>手机支付鼓励金匹配</a></perm:tag>";
        	tableStr += "</div>";
        	tableStr += "</td>";
	        
		        tableStr += "<td>" + result.items[k].name + "</td>";
// 		        tableStr += "<td>" + result.items[k].licence_code + "</td>";
// 		        tableStr += "<td>" + result.items[k].legal_person + "</td>";
		        tableStr += "<td>" + (result.items[k].sex==1?"男":"女") + "</td>";
		        tableStr += "<td>" + result.items[k].mobile1 + "</td>";
		        tableStr += "<td>" + (result.items[k].style==1?"公司":"个人") + "</td>";
		        if(result.items[k].status == 0){
		        tableStr += "<td>终止</td>";
		        }
		        if(result.items[k].status == 1){
		        tableStr += "<td>正常</td>";
		        }
		        if(result.items[k].status == 2){
		        tableStr += "<td>暂停</td>";
		        }
		        tableStr += "<td>" + genDateTimeAll(result.items[k].create_time) + "</td>";
		        tableStr += "<td>" + (result.items[k].check_status ==0?"待审核":"已审核") + "</td>";
		        tableStr += "<td>" + result.items[k].contact_person + "</td>";
		        tableStr += "<td>" + result.items[k].gl_yl_name + "</td>";
		       	if(result.items[k].gl_yl_type == "1"){
			    	tableStr += "<td>按笔</td>";
			    }else if(result.items[k].gl_yl_type == "2"){
			    	tableStr += "<td>按实际支付金额比例</td>";
			    }else{
			        tableStr += "<td></td>";
			    }
		       	if(result.items[k].gl_yl_fee==""){
		       		tableStr += "<td></td>";
		       	}else{
		       		tableStr += "<td>" + (result.items[k].gl_yl_fee).toFixed(2) + "</td>";
		       	}
		       	if(result.items[k].gl_yl_rate==""){
		       		tableStr += "<td></td>";
		       	}else{
		       		tableStr += "<td>" + (result.items[k].gl_yl_rate).toFixed(5) + "</td>";
		       	}
		        tableStr += "<td>" + result.items[k].gl_sj_name + "</td>";
		        if(result.items[k].gl_sj_type == "1"){
			    	tableStr += "<td>按笔</td>";
			    }else if(result.items[k].gl_sj_type == "2"){
			    	tableStr += "<td>按实际支付金额比例</td>";
			    }else{
			        tableStr += "<td></td>";
			    }
		       	if(result.items[k].gl_sj_fee==""){
		       		tableStr += "<td></td>";
		       	}else{
		       		tableStr += "<td>" + (result.items[k].gl_sj_fee).toFixed(2) + "</td>";
		       	}
		       	if(result.items[k].gl_sj_rate==""){
		       		tableStr += "<td></td>";
		       	}else{
		       		tableStr += "<td>" + (result.items[k].gl_sj_rate).toFixed(5) + "</td>";
		       	}
// 		        tableStr += "<td>" + result.items[k].tax_code + "</td>";
// 		        tableStr += "<td>" + result.items[k].bank_name + "</td>";
// 		        tableStr += "<td>" + result.items[k].bank_number + "</td>";
// 		        tableStr += "<td>" + result.items[k].bank_account + "</td>";
// 		        tableStr += "<td>" + result.items[k].contract_number + "</td>";
// 		        tableStr += "<td>" + genDateTimeAll(result.items[k].contract_start_time) + "</td>";
// 		        tableStr += "<td>" + genDateTimeAll(result.items[k].contract_end_time) + "</td>";
// 		        tableStr += "<td>" + result.items[k].remark1 + "</td>";
// 		        tableStr += "<td>" + result.items[k].remark2 + "</td>";
// 		        tableStr += "<td>" + result.items[k].remark3 + "</td>";
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
		show('<c:url value="/hAgent/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hAgent/toAdd"/>','');
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
					$.post("<c:url value='/hAgent/removeAllHAgent'/>",
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
				$.post("<c:url value='/hAgent/removeHAgent'/>",
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
		//设为默认
	function isDefault(id){
	   if (id != ""){
		   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要将此代理设为默认吗?","取消","确定", function(result) {
			if(result){
				$.post("<c:url value='/hAgent/isDefault'/>",
	        	{
					id	:id,
					ranNum:Math.random()
				},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	var pageNo = $("#currPage").val();           
		              	searchData(pageNo);
		              	tipOk("设置成功!");
		             } else {
		              	tipError("设置失败!");
		             }
		        });
			}else{
				//取消
			}
		});
   	   }
    }
	function qrcode(id,revStatus){
		 if (id != ""&&revStatus==1){
				$.post("<c:url value='/hAgent/qrcode'/>",
	        	{
					id	:id,
					ranNum:Math.random()
				},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		            	var html = "<img src='"+result.items.url+"'><br>";
		            	html+='<br>';
		            	html+='下载二维码：';
		            	html+= '<a class="btn btn-mini" href="${ctx}/hAgent/getpic?id='+id+'&type=1">8CM</a>';
		            	html+= '<a class="btn btn-mini" href="${ctx}/hAgent/getpic?id='+id+'&type=2">12CM</a>';
		            	html+= '<a class="btn btn-mini" href="${ctx}/hAgent/getpic?id='+id+'&type=3">15CM</a>';
		            	html+= '<a class="btn btn-mini" href="${ctx}/hAgent/getpic?id='+id+'&type=4">30CM</a>';
		            	html+= '<a class="btn btn-mini" href="${ctx}/hAgent/getpic?id='+id+'&type=5">50CM</a>';
		            	bootbox.alert(html,"返回",function(){
		    	   			
		    	   		})
		            } else {
		              	tipError("获取失败!");
		             }
		        });
	   	   }else{
	   		bootbox.confirm("<i class=' fontello-icon-emo-happy' />需通过审核才可获取二维码!","返回",function(){
	   			
	   		})
	   	   }
	}
	var flag = true;
	function review(id){
		   if (id != ""){
			   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要审核通过吗?","取消","确定", function(result) {
				if(result&&flag){
					flag = false;
					$.post("<c:url value='/hAgent/review'/>",
		        	{
						id	:id,
						ranNum:Math.random()
					},
		        	function(data){
			        	var result = eval('('+data+')'); 
			        	flag = true;
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
	var agentId="";
	var gl_yl_id="";
	var gl_sj_id="";
	function payGuliYL(id,gl_yl_id1){
		$("#sss").click();
		agentId=id;
		searchData2(1);
		gl_yl_id = gl_yl_id1;
	}
	function payGuliSJ(id,gl_sj_id1){
		$("#ssss").click();
		agentId=id;
		searchData3(1);
		gl_sj_id = gl_sj_id1;
	}
</script>
</body>
</html>
