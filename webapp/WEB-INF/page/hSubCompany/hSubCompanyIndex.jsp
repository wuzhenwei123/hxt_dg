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
                                            <label class="margin5">单位名称:</label>
	                                    	<input type="text" id="c_name" class="span2 margin5" >
                                            <label class="margin5">子单位名称:</label>
	                                    	<input type="text" id="fSub_name" class="span2 margin5">
                                            <label class="margin5">电表号:</label>
	                                    	<input type="text" id="ammeter_no" class="span2 margin5">
	                                    	<label class="margin5">发票抬头:</label>
	                                    	<input type="text" id="fInvoice_title" class="span2 margin5">
	                                    	<label class="margin5">收件人姓名:</label>
	                                    	<input type="text" id="fConsignee" class="span2 margin5">
	                                    	<label class="margin5">收件人手机:</label>
	                                    	<input type="text" id="fConsignee_phone" class="span2 margin5">
	                                    	<label class="margin5">收件人座机:</label>
	                                    	<input type="text" id="fConsignee_tel1" class="span2 margin5">
	                                    	<label class="margin5">收件人地址:</label>
	                                    	<input type="text" id="fConsignee_address" class="span2 margin5">
	                                    	<label class="margin5">添加时间:</label>
	                                    	<input type="text" id="fCreate_time1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
	                                    	-
	                                    	<input type="text" id="fCreate_time2" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
	                                    	<label class="margin5">邮编:</label>
	                                    	<input type="text" id="fZip_code" class="span2 margin5">
	                                    	<label class="margin5">省份:</label>
	                                    	<select id="fProvince_code"><option value="">请选择</option></select>
                                            <label class="margin5">城市:</label>
	                                    	<select id="fCity_code"><option value="">请选择</option></select>
                                            <label class="margin5">地区:</label>
	                                    	<select id="fArea_code"><option value="">请选择</option></select>
	                                    	<label class="margin5">单位联系人手机:</label>
	                                    	<input type="text" id="fContact_phone" class="span2 margin5">
	                                    </div>
	                                    <div class="widget-row form-inline row-fluid">
	                                   		<label class="margin5">选择一级代理:</label>
	                                   		<c:if test="${roleType==1||roleType==4}">
		                                    	<input type="text" id="agentOneNameQuery" readonly="readonly" onclick="modelType=1;showAgentOne()" data-toggle="modal">
									           	<a type="button" class="btn btn-boo" onclick="modelType=1;cleanOn2()">清除</a>
										        <a href="#stack2" id="sss" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
										        <input type="hidden" id="agentOneOpenIdQuery"/>
	                                   		</c:if>
	                                   		<c:if test="${roleType==2||roleType==3}">
	                                   			<input type="text" id="agentOneNameQuery" readonly="readonly" value="${oneAgentOpenName}">
	                                   			<input type="hidden" id="agentOneOpenIdQuery" value="${oneAgentOpenId}"/>
	                                   		</c:if>
	                                   		
	                                   		<label class="margin5">选择二级代理:</label>
	                                   		<c:if test="${roleType==2||roleType==1||roleType==4}">
		                                    	<input type="text" id="agentTwoNameQuery" readonly="readonly" onclick="modelType=1;showAgentTwo()" data-toggle="modal">
									           	<a type="button" class="btn btn-boo" onclick="modelType=1;cleanOn3()">清除</a>
										        <a href="#stack3" id="ddd" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
										        <input type="hidden" id="agentTwoOpenIdQuery"/>
	                                   		</c:if>
	                                   		<c:if test="${roleType==3}">
	                                   			<input type="text" id="agentTwoNameQuery" readonly="readonly" value="${twoAgentOpenName}">
	                                   			<input type="hidden" id="agentTwoOpenIdQuery" value="${twoAgentOpenId}"/>
	                                   		</c:if>
	                                   		<label class="margin5">选择服务人员:</label>
	                                   		<c:if test="${roleType==2||roleType==1||roleType==3}">
		                                    	<input type="text" id="aServicerNameQuery" readonly="readonly" onclick="modelType=1;showServicer()" data-toggle="modal">
									           	<a type="button" class="btn btn-boo" onclick="modelType=1;cleanOn4()">清除</a>
										        <a href="#stack4" id="ccc" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
										        <input type="hidden" id="servicerIdQuery"/>
									        </c:if>
									        <c:if test="${roleType==4}">
									        	<input type="text" id="aServicerNameQuery" readonly="readonly" value="${serviceName}">
									        	<input type="hidden" id="servicerIdQuery" value="${serviceId}"/>
									        </c:if>
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
	                                 	 	<perm:tag permPath="/hSubCompany/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/hSubCompany/removeAllHSubCompany" >
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
<%@ include file="/WEB-INF/page/hCompany/agentOne.jsp" %>
 <%@ include file="/WEB-INF/page/hCompany/agentTwo.jsp" %>
 <%@ include file="/WEB-INF/page/hCompany/servicer.jsp" %>
<!-- Only This Demo Page --> 
<div id="temModal" class="modal hide fade" tabindex="-1" data-width="70%"></div>

<script type="text/javascript">
	var $modal = $('#temModal');
	
	$(document).ready(function(){
    	searchData(1);
    });
	function searchData(pageNo){
		var returnNum = $("#returnNum").val();
			var servicerId = $("#servicerIdQuery").val();
			var oneAgentOpenId = $("#agentOneOpenIdQuery").val();
			var twoAgentOpenID = $("#agentTwoOpenIdQuery").val();
			
			var sortColumn = $("#sortColumn").val();
		    var s_id = $("#fS_id").val();
		    var c_id = $("#fC_id").val();
		    var ammeter_no = $("#ammeter_no").val();
		    var c_name = $("#c_name").val();
		    var sub_name = $("#fSub_name").val();
		    var invoice_title = $("#fInvoice_title").val();
		    var consignee = $("#fConsignee").val();
		    var consignee_phone = $("#fConsignee_phone").val();
		    var consignee_tel1 = $("#fConsignee_tel1").val();
		    var consignee_tel2 = $("#fConsignee_tel2").val();
		    var consignee_address = $("#fConsignee_address").val();
		    var create_time1 = $("#fCreate_time1").val();
		    var create_time2 = $("#fCreate_time2").val();
		    var update_time = $("#fUpdate_time").val();
		    var zip_code = $("#fZip_code").val();
		    var province_code = $("#fProvince_code").val();
		    var city_code = $("#fCity_code").val();
		    var area_code = $("#fArea_code").val();
		    var contact_phone = $("#fContact_phone").val();
		    $.getJSON("<c:url value='/hSubCompany/getHSubCompanyList'/>",
	        {
		    	oneAgentOpenId:oneAgentOpenId,
		    	twoAgentOpenID:twoAgentOpenID,
		    	servicerId:servicerId,
		    	create_time1:create_time1,
		    	create_time2:create_time2,
	        	sortColumn:sortColumn,
	        	contact_phone:contact_phone,
	    		s_id : s_id,
	    		c_id : c_id,
	    		sub_name : sub_name,
	    		invoice_title : invoice_title,
	    		consignee : consignee,
	    		consignee_phone : consignee_phone,
	    		consignee_tel1 : consignee_tel1,
	    		consignee_tel2 : consignee_tel2,
	    		consignee_address : consignee_address,
// 	    		create_time : create_time,
	    		ammeter_no : ammeter_no,
	    		update_time : update_time,
	    		zip_code : zip_code,
	    		province_code : province_code,
	    		city_code : city_code,
	    		area_code : area_code,
	    		pageNo: pageNo,
	    		c_name: c_name,
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
	    	str+="<th>操	作</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_sub_name\" column='sub_name' onselectstart='return false' scope=\"col\">单位名称</th>";
	    	str+= "<th>单位ID</th>";
	    	str+= "<th>单位联系人手机</th>";
	    	str+= "<th>单位联系人姓名</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_invoice_title\" column='invoice_title' onselectstart='return false' scope=\"col\">子单位名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_invoice_title\" column='invoice_title' onselectstart='return false' scope=\"col\">发票抬头</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_consignee\" column='consignee' onselectstart='return false' scope=\"col\">收件人名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_consignee_phone\" column='consignee_phone' onselectstart='return false' scope=\"col\">收件人手机</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_consignee_tel1\" column='consignee_tel1' onselectstart='return false' scope=\"col\">收件人座机</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_consignee_tel2\" column='consignee_tel2' onselectstart='return false' scope=\"col\">座机分机号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_consignee_address\" column='consignee_address' onselectstart='return false' scope=\"col\">收件人地址</th>";
	    	str+= "<th class=\"sortTh\" id=\"zip_code\" column='zip_code' onselectstart='return false' scope=\"col\">邮编</th>";
	    	str+= "<th class=\"sortTh\" id=\"province_code\" column='province_code' onselectstart='return false' scope=\"col\">省</th>";
	    	str+= "<th class=\"sortTh\" id=\"city_code\" column='city_code' onselectstart='return false' scope=\"col\">市</th>";
	    	str+= "<th class=\"sortTh\" id=\"area_code\" column='area_code' onselectstart='return false' scope=\"col\">区</th>";
	    	str+= "<th class=\"sortTh\" id=\"area_code\" column='area_code' onselectstart='return false' scope=\"col\">客户经理</th>";
	    	str+= "<th class=\"sortTh\" id=\"area_code\" column='area_code' onselectstart='return false' scope=\"col\">代理</th>";
	    	str+= "<th class=\"sortTh\" id=\"area_code\" column='area_code' onselectstart='return false' scope=\"col\">后台支持</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_create_time\" width='135' column='create_time' onselectstart='return false' scope=\"col\">添加时间</th>";
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
	        tableStr += "<td ><input type=\"checkbox\" id='"+result.items[k].s_id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" /></td>";
	        tableStr += "<td >" + (number + k + 1) + "</td>";
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	tableStr += "<perm:tag permPath='/hSubCompany/updateHSubCompany' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].s_id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hSubCompany/removeHSubCompany' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].s_id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hSubCompany/showDB' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='showDB(" + result.items[k].s_id + ");return false;'><i class='fontello-icon-minus-1' />查看电表号</a></perm:tag>";
        	tableStr += "</div>";
        	tableStr += "</td>";
		        tableStr += "<td>" + result.items[k].c_name + "</td>";
		        tableStr += "<td>" + result.items[k].c_id + "</td>";
		        tableStr += "<td>" + result.items[k].contact_phone + "</td>";
		        tableStr += "<td>" + result.items[k].contact_name + "</td>";
		        tableStr += "<td>" + result.items[k].sub_name + "</td>";
		        tableStr += "<td>" + result.items[k].invoice_title + "</td>";
		        tableStr += "<td>" + result.items[k].consignee + "</td>";
		        tableStr += "<td>" + result.items[k].consignee_phone + "</td>";
		        tableStr += "<td>" + result.items[k].consignee_tel1 + "</td>";
		        tableStr += "<td>" + result.items[k].consignee_tel2 + "</td>";
		        tableStr += "<td>" + result.items[k].consignee_address + "</td>";
		        tableStr += "<td>" + result.items[k].zip_code + "</td>";
		        tableStr += "<td>" + result.items[k].province_name + "</td>";
		        tableStr += "<td>" + result.items[k].city_name + "</td>";
		        tableStr += "<td>" + result.items[k].area_name + "</td>";
		        tableStr += "<td>" + result.items[k].oneName + "</td>";
		        tableStr += "<td>" + result.items[k].twoName + "</td>";
		        tableStr += "<td>" + result.items[k].servicerName + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].create_time) + "</td>";
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
		show('<c:url value="/hSubCompany/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hSubCompany/toAdd"/>','');
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
					$.post("<c:url value='/hSubCompany/removeAllHSubCompany'/>",
		        	{
						s_ids :ids,
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
	function del(s_id){
	   if (s_id != ""){
		   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要删除吗?","取消","确定", function(result) {
			if(result){
				$.post("<c:url value='/hSubCompany/removeHSubCompany'/>",
	        	{
					s_id	:s_id,
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
		
	function showDB(id){
		show('<c:url value="/hSubCompany/toShowDB/'+id+'"/>','');
	}
	
	function exportExcel(){
		var returnNum = $("#returnNum").val();
		var sortColumn = $("#sortColumn").val();
	    var s_id = $("#fS_id").val();
	    var c_id = $("#fC_id").val();
	    var ammeter_no = $("#ammeter_no").val();
	    var c_name = $("#c_name").val();
	    var sub_name = $("#fSub_name").val();
	    var invoice_title = $("#fInvoice_title").val();
	    var consignee = $("#fConsignee").val();
	    var consignee_phone = $("#fConsignee_phone").val();
	    var consignee_tel1 = $("#fConsignee_tel1").val();
	    var consignee_tel2 = $("#fConsignee_tel2").val();
	    var consignee_address = $("#fConsignee_address").val();
	    var create_time = $("#fCreate_time").val();
	    var update_time = $("#fUpdate_time").val();
	    var zip_code = $("#fZip_code").val();
	    var province_code = $("#fProvince_code").val();
	    var city_code = $("#fCity_code").val();
	    var area_code = $("#fArea_code").val();
	    var contact_phone = $("#fContact_phone").val();
	    $.getJSON("<c:url value='/hSubCompany/exportExcel'/>",
        {
        	sortColumn:sortColumn,
    		s_id : s_id,
    		c_id : c_id,
    		contact_phone : contact_phone,
    		sub_name : sub_name,
    		invoice_title : invoice_title,
    		consignee : consignee,
    		consignee_phone : consignee_phone,
    		consignee_tel1 : consignee_tel1,
    		consignee_tel2 : consignee_tel2,
    		consignee_address : consignee_address,
    		create_time : create_time,
    		ammeter_no : ammeter_no,
    		update_time : update_time,
    		zip_code : zip_code,
    		province_code : province_code,
    		city_code : city_code,
    		area_code : area_code,
    		c_name: c_name,
        },function(data){
            var result = data;
        	if(result.code=='1'){
        		window.open('${ctx}'+result.items);
        	}else{
        		alert(result.message);
        	}
	    });
	}
	setInterval(function(){
		$("html").getNiceScroll().resize();
	}, 300);
	
	
	var $stack3 = $('#stack3');
	var $stack2 = $('#stack2');
	var $stack4 = $('#stack4');
	function showAgentOne(){
		$("#sss").click();
		searchData2(1);
	}
	function showAgentTwo(){
		$("#ddd").click();
		searchData3(1);
	}
	function showServicer(){
		$("#ccc").click();
		searchData4(1);
	}
	function cleanOn2(){
		if(modelType == 1){
			$("#aAgentOneNameQuery").val('');
			$("#agentOneOpenIdQuery").val('');
			$("#agentOneNameQuery").val('');
		}else{
			$("#aAgentOneName").val('');
			$("#agentOneOpenId").val('');
			$("#agentOneName").val('');
		}
	}
	function cleanOn3(){
		if(modelType == 1){
			$("#aAgentTwoNameQuery").val('');
			$("#agentTwoOpenIdQuery").val('');
			$("#agentTwoNameQuery").val('');
		}else{
			$("#aAgentTwoName").val('');
			$("#agentTwoOpenId").val('');
			$("#agentTwoName").val('');
		}
	}
	function cleanOn4(){
		if(modelType == 1){
			$("#aServicerNameQuery").val('');
			$("#servicerIdQuery").val('');
			$("#servicerNameQuery").val('');
		}else{
			$("#aServicerName").val('');
			$("#servicerId").val('');
			$("#servicerName").val('');
		}
	}
</script>
</body>
</html>
