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
	<input type="hidden" id="currPage3" value="1"><!-- 当前页码 -->
	<input type="hidden" id="returnNum3" value="10"><!-- 分页返回 -->
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
<!--                                             <label class="margin5">a_id:</label>
	                                    	<input type="text" id="fA_id" class="span2 margin5" placeholder="a_id">
                                            <label class="margin5">c_id:</label>
	                                    	<input type="text" id="fC_id" class="span2 margin5" placeholder="c_id">
                                            <label class="margin5">s_id:</label>
	                                    	<input type="text" id="fS_id" class="span2 margin5" placeholder="s_id"> -->
                                            <label class="margin5">缴费号:</label>
	                                    	<input type="text" id="fAmmeter_no" class="span2 margin5" placeholder="缴费号">
                                            <label class="margin5">客户名称:</label>
	                                    	<input type="text" id="fAmmeter_name" class="span2 margin5" placeholder="客户名称">
                                            <label class="margin5">电表类型:</label>
<!-- 	                                    	<input type="text" id="fAmmeter_type" class="span2 margin5" placeholder="电表类型"> -->
	                                    	<select id="fAmmeter_type">
	                                    		<option value="">请选择</option> 
	                                    		<option value="A">抄表</option> 
	                                    		<option value="B">智能表</option> 
	                                    	</select>
	                                    	<label class="margin5">是否设为代付:</label>
	                                    	<select id="fProxy_flag">
	                                    		<option value="">请选择</option> 
	                                    		<option value="0">否</option> 
	                                    		<option value="1">是</option> 
	                                    	</select>
<!--                                             <label class="margin5">最后缴费日期:</label> -->
<!-- 	                                    	<input type="text" id="fLast_pay_day" class="span2 margin5" placeholder="最后缴费日期"> -->
                                            <label class="margin5">缴费状态:</label>
<!-- 	                                    	<input type="text" id="fPay_status" class="span2 margin5" placeholder="pay_status"> -->
	                                    	<select id="fPay_status">
	                                    		<option value="">请选择</option>
	                                    		<option value="0">暂停</option>
	                                    		<option value="1">正常</option>
	                                    	</select>
                                            <label class="margin5">发票类型:</label>
<!-- 	                                    	<input type="text" id="fFp_style" class="span2 margin5" placeholder="fp_style"> -->
	                                    	<select id="fFp_style">
	                                    		<option value="">请选择</option>
	                                    		<option value="1">恒信通发票</option>
	                                    		<option value="2">电力增值税普票</option>
	                                    		<option value="3">电力增值税专票</option>
	                                    		<option value="4">电力定额发票</option>
	                                    	</select>
                                            <label class="margin5">客户详细地址:</label>
	                                    	<input type="text" id="fAmmeter_address" class="span2 margin5" placeholder="客户详细地址">
                                            <label class="margin5">省份:</label>
<!-- 	                                    	<input type="text" id="fProvince_code" class="span2 margin5" placeholder="省份代码"> -->
	                                    	<select id="fProvince_code"><option value="">请选择</option></select>
                                            <label class="margin5">城市:</label>
<!-- 	                                    	<input type="text" id="fCity_code" class="span2 margin5" placeholder="城市代码"> -->
	                                    	<select id="fCity_code"><option value="">请选择</option></select>
                                            <label class="margin5">地区:</label>
<!-- 	                                    	<input type="text" id="fArea_code" class="span2 margin5" placeholder="地区代码"> -->
	                                    	<select id="fArea_code"><option value="">请选择</option></select>
<!--                                             <label class="margin5">分润id:</label> -->
<!-- 	                                    	<input type="text" id="fProfit_id" class="span2 margin5" placeholder="分润id"> -->
                                            <label class="margin5">是否缴过费:</label>
	                                    	<input type="text" id="fIs_payed" class="span2 margin5" placeholder="是否缴过费">
                                            <label class="margin5">单位登录账户:</label>
	                                    	<input type="text" id="fAdminName" class="span2 margin5" placeholder="单位登录账户">
                                            <label class="margin5">单位联系人手机号:</label>
	                                    	<input type="text" id="fContact_phone" class="span2 margin5" placeholder="单位联系人手机号">
                                            <label class="margin5">删除状态:</label>
<!-- 	                                    	<input type="text" id="fDelete_state" class="span2 margin5" placeholder="delete_state"> -->
	                                    	<select id="fDelete_state">
	                                    		<option value="">请选择</option>
	                                    		<option value="1">未删除</option>
	                                    		<option value="0">已删除</option>
	                                    	</select>
	                                    	<label class="margin5">所属单位：</label>
							                <input type="text" id="oneAgentindex" readonly="readonly" onclick="selOneindex()" data-toggle="modal">
							           		<a type="button" class="btn btn-boo" onclick="cleanOnIndex()">清除</a>
								           	<a href="#stack3" id="xxxindex" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
								           	<input type="hidden" id="oneAgentOpenIdIndex"/>
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
	                                 	 	<perm:tag permPath="/hAmmeterInfo/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/hAmmeterInfo/removeAllHAmmeterInfo" >
	                                 	 	<a href="javascript:delAll();" class="btn btn-glyph"><i class="fontello-icon-minus-1"></i>逻辑删除</a> 
	                                 	 	</perm:tag>
	                                 	 	<a href="javascript:exportExcel();" class="btn btn-glyph">导出</a>
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
<div id="stack3" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择单位</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fOneAgentNameIndex" class="span2 margin5" placeholder="单位名称">
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
<script type="text/javascript">
	var $modal = $('#temModal');
	
	$(document).ready(function(){
    	searchData(1);
    });
	function searchData(pageNo){
		var returnNum = $("#returnNum").val();
		var sortColumn = $("#sortColumn").val();
	    var ammeter_no = $("#fAmmeter_no").val();
	    var ammeter_name = $("#fAmmeter_name").val();
	    var ammeter_type = $("#fAmmeter_type").val();
	    var last_pay_day = $("#fLast_pay_day").val();
	    var pay_status = $("#fPay_status").val();
	    var operator_id = $("#fOperator_id").val();
	    var create_time = $("#fCreate_time").val();
	    var update_time = $("#fUpdate_time").val();
	    var fp_style = $("#fFp_style").val();
	    var proxy_flag = $("#fProxy_flag").val();
	    var ammeter_address = $("#fAmmeter_address").val();
	    var province_code = $("#fProvince_code").val();
	    var city_code = $("#fCity_code").val();
	    var area_code = $("#fArea_code").val();
// 		    var profit_id = $("#fProfit_id").val();
	    var is_payed = $("#fIs_payed").val();
	    var delete_state = $("#fDelete_state").val();
	    var adminName = $("#fAdminName").val();
	    var contact_phone = $("#fContact_phone").val();
	    var c_id = $("#oneAgentOpenIdIndex").val();
	    $.getJSON("<c:url value='/hAmmeterInfo/getHAmmeterInfoList'/>",
        {
        	sortColumn:sortColumn,
    		ammeter_no : ammeter_no,
    		contact_phone : contact_phone,
    		c_id : c_id,
    		adminName : adminName,
    		ammeter_name : ammeter_name,
    		ammeter_type : ammeter_type,
    		last_pay_day : last_pay_day,
    		pay_status : pay_status,
    		operator_id : operator_id,
    		create_time : create_time,
    		proxy_flag : proxy_flag,
    		update_time : update_time,
    		fp_style : fp_style,
    		ammeter_address : ammeter_address,
    		province_code : province_code,
    		city_code : city_code,
    		area_code : area_code,
// 	    		profit_id : profit_id,
    		is_payed : is_payed,
    		delete_state : delete_state,
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
// 	    	str+= "<th class=\"sortTh\" id=\"th_a_id\" column='a_id' onselectstart='return false' scope=\"col\">a_id</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_c_id\" column='c_id' onselectstart='return false' scope=\"col\">c_id</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_s_id\" column='s_id' onselectstart='return false' scope=\"col\">s_id</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_ammeter_no\" column='ammeter_no' onselectstart='return false' scope=\"col\">缴费号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_ammeter_name\" column='ammeter_name' onselectstart='return false' scope=\"col\">客户名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_ammeter_type\" column='ammeter_type' onselectstart='return false' scope=\"col\">电表类型</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_last_pay_day\" column='last_pay_day' onselectstart='return false' scope=\"col\">最后缴费时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_pay_status\" column='pay_status' onselectstart='return false' scope=\"col\">缴费状态</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_operator_id\" column='operator_id' onselectstart='return false' scope=\"col\">operator_id</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_create_time\" width='135' column='create_time' onselectstart='return false' scope=\"col\">create_time</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_update_time\" width='135' column='update_time' onselectstart='return false' scope=\"col\">update_time</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_fp_style\" column='fp_style' onselectstart='return false' scope=\"col\">发票类型</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_ammeter_address\" column='ammeter_address' onselectstart='return false' scope=\"col\">客户详细地址</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_province_code\" column='province_code' onselectstart='return false' scope=\"col\">province_code</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_city_code\" column='city_code' onselectstart='return false' scope=\"col\">city_code</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_area_code\" column='area_code' onselectstart='return false' scope=\"col\">area_code</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_province_name\" column='province_name' onselectstart='return false' scope=\"col\">省份</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_city_name\" column='city_name' onselectstart='return false' scope=\"col\">城市</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_area_name\" column='area_name' onselectstart='return false' scope=\"col\">地区</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_profit_id\" column='profit_id' onselectstart='return false' scope=\"col\">profit_id</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_profit_id\" column='profit_id' onselectstart='return false' scope=\"col\">客户经理</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_profit_id\" column='profit_id' onselectstart='return false' scope=\"col\">客户经理分润</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_profit_id\" column='profit_id' onselectstart='return false' scope=\"col\">代理人</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_profit_id\" column='profit_id' onselectstart='return false' scope=\"col\">代理分润</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_profit_id\" column='profit_id' onselectstart='return false' scope=\"col\">支持人员</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_profit_id\" column='profit_id' onselectstart='return false' scope=\"col\">支持人员分润</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_is_payed\" column='is_payed' onselectstart='return false' scope=\"col\">is_payed</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_delete_state\" column='delete_state' onselectstart='return false' scope=\"col\">是否删除</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_delete_state\" column='delete_state' onselectstart='return false' scope=\"col\">所属单位</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_delete_state\" column='delete_state' onselectstart='return false' scope=\"col\">单位联系人手机号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_delete_state\" column='delete_state' onselectstart='return false' scope=\"col\">单位登录账户</th>";
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
	        tableStr += "<td ><input type=\"checkbox\" id='"+result.items[k].a_id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" /></td>";
	        tableStr += "<td >" + (number + k + 1) + "</td>";
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	tableStr += "<perm:tag permPath='/hAmmeterInfo/updateHAmmeterInfo' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].a_id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hAmmeterInfo/removeHAmmeterInfo' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].a_id + ");return false;'><i class='fontello-icon-minus-1' />逻辑删除</a></perm:tag>";
        	tableStr += "</div>";
        	tableStr += "</td>";
// 		        tableStr += "<td>" + result.items[k].a_id + "</td>";
// 		        tableStr += "<td>" + result.items[k].c_id + "</td>";
// 		        tableStr += "<td>" + result.items[k].s_id + "</td>";
		        tableStr += "<td>" + result.items[k].ammeter_no + "</td>";
		        tableStr += "<td>" + result.items[k].ammeter_name + "</td>";
		        tableStr += "<td>" + (result.items[k].ammeter_type=='A'?'抄表':'智能表') + "</td>";
// 		        tableStr += "<td>" + genDateTimeAll(result.items[k].last_pay_day) + "</td>";
		        tableStr += "<td>" + (result.items[k].pay_status==0?'暂停':'正常') + "</td>";
// 		        tableStr += "<td>" + result.items[k].operator_id + "</td>";
// 		        tableStr += "<td>" + genDateTimeAll(result.items[k].create_time) + "</td>";
// 		        tableStr += "<td>" + genDateTimeAll(result.items[k].update_time) + "</td>";
				if(result.items[k].fp_style==1){
					tableStr += "<td>恒信通发票</td>";
				}else if(result.items[k].fp_style==2){
					tableStr += "<td>电力增值税普票</td>";
				}else if(result.items[k].fp_style==3){
					tableStr += "<td>电力增值税专票</td>";
				}else if(result.items[k].fp_style==4){
					tableStr += "<td>电力定额发票</td>";
				}else{
					tableStr += "<td></td>";
				}
		        tableStr += "<td>" + result.items[k].ammeter_address + "</td>";
// 		        tableStr += "<td>" + result.items[k].province_code + "</td>";
// 		        tableStr += "<td>" + result.items[k].city_code + "</td>";
// 		        tableStr += "<td>" + result.items[k].area_code + "</td>";
		        tableStr += "<td>" + result.items[k].province_name + "</td>";
		        tableStr += "<td>" + result.items[k].city_name + "</td>";
		        tableStr += "<td>" + result.items[k].area_name + "</td>";
// 		        tableStr += "<td>" + result.items[k].profit_id + "</td>";
		        tableStr += "<td>" + result.items[k].oneAgentName + "</td>";
		        tableStr += "<td>" + result.items[k].profit_one + "</td>";
		        tableStr += "<td>" + result.items[k].twoAgentName + "</td>";
		        tableStr += "<td>" + result.items[k].profit_two + "</td>";
		        tableStr += "<td>" + result.items[k].servicerName + "</td>";
		        tableStr += "<td>" + result.items[k].profit_servicer + "</td>";
// 		        tableStr += "<td>" + result.items[k].is_payed + "</td>";
		        tableStr += "<td>" + (result.items[k].delete_state==1?'正常':'已删除') + "</td>";
		        tableStr += "<td>" + result.items[k].companyName + "</td>";
		        tableStr += "<td>" + result.items[k].adminName + "</td>";
		        tableStr += "<td>" + result.items[k].contact_phone + "</td>";
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
		show('<c:url value="/hAmmeterInfo/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hAmmeterInfo/toAdd"/>','');
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
					$.post("<c:url value='/hAmmeterInfo/removeAllHAmmeterInfo'/>",
		        	{
						a_ids :ids,
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
	function del(a_id){
	   if (a_id != ""){
		   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要删除吗?","取消","确定", function(result) {
			if(result){
				$.post("<c:url value='/hAmmeterInfo/removeHAmmeterInfo'/>",
	        	{
					a_id	:a_id,
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
	function exportExcel(){
	    var ammeter_no = $("#fAmmeter_no").val();
	    var ammeter_name = $("#fAmmeter_name").val();
	    var ammeter_type = $("#fAmmeter_type").val();
	    var last_pay_day = $("#fLast_pay_day").val();
	    var pay_status = $("#fPay_status").val();
	    var fp_style = $("#fFp_style").val();
	    var ammeter_address = $("#fAmmeter_address").val();
	    var province_code = $("#fProvince_code").val();
	    var city_code = $("#fCity_code").val();
	    var area_code = $("#fArea_code").val();
	    var is_payed = $("#fIs_payed").val();
	    var delete_state = $("#fDelete_state").val();
	    var sortColumn = $("#sortColumn").val();
	    var adminName = $("#fAdminName").val();
	    var contact_phone = $("#fContact_phone").val();
	    var c_id = $("#fC_id").val();
	    $.getJSON("<c:url value='/hAmmeterInfo/exportExcel'/>",
        {
        	sortColumn:sortColumn,
        	adminName:adminName,
        	contact_phone:contact_phone,
    		ammeter_no : ammeter_no,
    		ammeter_name : ammeter_name,
    		ammeter_type : ammeter_type,
    		last_pay_day : last_pay_day,
    		c_id : c_id,
    		pay_status : pay_status,
    		fp_style : fp_style,
    		ammeter_address : ammeter_address,
    		province_code : province_code,
    		city_code : city_code,
    		area_code : area_code,
    		is_payed : is_payed,
    		delete_state : delete_state,
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
	        		tableStr += "<td ><input type=\"radio\" checked id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex(\""+result.items[k].id+"\",\""+result.items[k].contact_phone+"\")'/></td>";
	        	}else{
	        		tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex(\""+result.items[k].id+"\",\""+result.items[k].contact_phone+"\")'/></td>";
	        	}
	        }else{
	        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex(\""+result.items[k].id+"\",\""+result.items[k].contact_phone+"\")'/></td>";
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
</script>
</body>
</html>
