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
<style type="text/css">
.maskbg{background:rgba(0,0,0,.8);display:none;height:100%;left:0;position:fixed;top:0;width:100%;z-index:19999}
.loading{text-align:center;margin:1em auto;width:100%;}
.loading-box img{left:50%;margin-left:-30px;position:absolute;top:50%; margin-top: -30px;}
</style>
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
                                            <label class="margin5">订单号:</label>
	                                    	<input type="text" id="fO_no" class="span2 margin5">
                                            <label class="margin5">交易流水号:</label>
	                                    	<input type="text" id="fQuery_id" class="span2 margin5">
                                            <label class="margin5">单位名称:</label>
	                                    	<input type="text" id="c_name" class="span2 margin5">
                                            <label class="margin5">单位ID:</label>
	                                    	<input type="text" id="c_id" class="span2 margin5">
                                            <label class="margin5">单位联系人手机号:</label>
	                                    	<input type="text" id="contact_phone" class="span2 margin5">
<!--                                             <label class="margin5">子单位名称:</label> -->
<!-- 	                                    	<input type="text" id="o_sub_name" class="span2 margin5"> -->
	                                    </div>
	                                    <div class="widget-row form-inline row-fluid">
                                            <label class="margin5">支付开始时间:</label>
	                                    	<input type="text" id="pay_start_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">支付结束时间:</label>
	                                    	<input type="text" id="pay_end_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">支付状态:</label>
                                            <select id="fPay_status" class="span2 margin5">
                                            	<option value="">请选择</option>
                                            	<option value="1">已支付</option>
                                            	<option value="2">已支付(长短款)</option>
                                            	<option value="0">未支付</option>
                                            </select>
                                            <label class="margin5">销账状态:</label>
                                            <select id="fTick_off_status" class="span2 margin5">
                                            	<option value="">请选择</option>
                                            	<option value="1">已销账</option>
                                            	<option value="0">未销账</option>
                                            </select>
                                        </div>
	                                    <div class="widget-row form-inline row-fluid">
                                            <label class="margin5">销账开始时间:</label>
	                                    	<input type="text" id="tick_start_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">销账结束时间:</label>
	                                    	<input type="text" id="tick_end_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">退费状态:</label>
                                            <select id="fBack_fee_status" class="span2 margin5">
                                            	<option value="">请选择</option>
                                            	<option value="1">已退费</option>
                                            	<option value="0">未退费</option>
                                            </select>
                                        </div>
	                                    <div class="widget-row form-inline row-fluid">
                                            <label class="margin5">退费开始时间:</label>
	                                    	<input type="text" id="back_start_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">退费结算时间:</label>
	                                    	<input type="text" id="back_end_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
	                                    	<label class="margin5">是否包含增值税发票:</label>
                                            <select id="fis_zz" class="span2 margin5">
                                            	<option value="">请选择</option>
                                            	<option value="1">是</option>
                                            	<option value="0">否</option>
                                            </select>
	                                    	<label class="margin5">是否开票:</label>
                                            <select id="fis_fp" class="span2 margin5">
                                            	<option value="">请选择</option>
                                            	<option value="1">是</option>
                                            	<option value="0">否</option>
                                            </select>
	                                    	<label class="margin5">支付类型:</label>
                                            <select id="fpay_type" class="span2 margin5">
                                            	<option value="">请选择</option>
                                            	<option value="1">银联B2B</option>
                                            	<option value="2">手机支付</option>
                                            </select>
	                                    </div>
	                                    <div class="widget-row form-inline row-fluid">
	                                    	<label class="margin5">订单生成开始时间:</label>
	                                    	<input type="text" id="create_start_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">订单生成结束时间:</label>
	                                    	<input type="text" id="create_end_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
	                                    </div>
	                                    <div class="widget-row form-inline row-fluid">
	                                   		<label class="margin5">选择一级代理:</label>
	                                    	<input type="text" id="agentOneName" readonly="readonly" onclick="showAgentOne()" data-toggle="modal" value="${oneName }">
								           	<c:if test="${agentFlag == false ||(agentFlag == true && empty oneName)}">
								           		<a type="button" class="btn btn-boo" onclick="cleanOn2()">清除</a>
								           	</c:if>
									        <a href="${(agentFlag == true&&(not empty oneName ||not empty twoName))?'javascript:void(0);':'#stack2' }" id="sss" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
									        <input type="hidden" id="agentOneOpenId" value="${oneId }"/>
	                                   		<label class="margin5">选择二级代理:</label>
	                                    	<input type="text" id="agentTwoName" readonly="readonly" onclick="showAgentTwo()" data-toggle="modal" value="${twoName }">
	                                    	<c:if test="${agentFlag == false ||(agentFlag == true && empty twoName)}">
								           		<a type="button" class="btn btn-boo" onclick="cleanOn3()">清除</a>
								           	</c:if>
									        <a href="${(agentFlag == true&&(not empty twoName))?'javascript:void(0);':'#stack3' }" id="ddd" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
									        <input type="hidden" id="agentTwoOpenId" value="${twoId }"/>
	                                   		<label class="margin5">选择服务人员:</label>
	                                    	<input type="text" id="aServicerName" readonly="readonly" onclick="showServicer()" data-toggle="modal" value="${servicerName }">
								           	<c:if test="${agentFlag == false ||(agentFlag == true && empty servicerName)}">
								           		<a type="button" class="btn btn-boo" onclick="cleanOn4()">清除</a>
								           	</c:if>
									        <a href="${(agentFlag == true&&(not empty servicerName))?'javascript:void(0);':'#stack4' }" id="ccc" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
									        <input type="hidden" id="servicerId" value="${servicerId }"/>
	                                    </div>
	                                    <div class="widget-row form-inline row-fluid">
	                                     	<label class="margin5">订单面值金额范围:</label>
	                                    	<input type="text" id="startTotalFee" class="span2 margin5">元至
	                                    	<input type="text" id="endTotalFee" class="span2 margin5">元
	                                    	<label class="margin5">是否分润:</label>
                                            <select id="fFR" class="span2 margin5">
                                            	<option value="">请选择</option>
                                            	<option value="0">不分润</option>
                                            	<option value="1">分润</option>
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
	                                 	 	<perm:tag permPath="/hPayOrder/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/hPayOrder/removeAllHPayOrder" >
	                                 	 	<a href="javascript:delAll();" class="btn btn-glyph"><i class="fontello-icon-minus-1"></i>删除</a> 
	                                 	 	</perm:tag>
	                                 	 	<a href="javascript:exportExcel();" class="btn btn-glyph">导出</a>
	                                 	 	<a href="javascript:exportUnpayExcel();" class="btn btn-glyph">导出制单没有缴费订单</a>
	                                 	 	<a href="javascript:void(0);" class="btn btn-glyph" style="cursor:default;" id='totalA'>总数:</a>
	                                 	 	<a href="javascript:void(0);" class="btn btn-glyph" style="cursor:default;" id='totalFeeA'>已支付金额:</a>
	                                 	 	<a href="javascript:void(0);" class="btn btn-glyph" style="cursor:default;" id='totalFeeB'>未支付金额:</a>
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
<!-- <div id="stack3" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择业务员</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fOneAgentNameIndex" class="span2 margin5" placeholder="业务员名称">
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
</div> -->
<!-- Le javascript --> 
<%@ include file="/WEB-INF/page/common/js.jsp" %>
 <%@ include file="/WEB-INF/page/hPayOrder/agentOne.jsp" %>
 <%@ include file="/WEB-INF/page/hPayOrder/agentTwo.jsp" %>
 <%@ include file="/WEB-INF/page/hPayOrder/servicer.jsp" %>
<script type="text/javascript" src="${ctx}/js/page.js"></script> 
<script type="text/javascript" src="${ctx}/js/mymap.js"></script> 
<!-- Only This Demo Page --> 
<div id="temModal" class="modal hide fade" tabindex="-1" data-width="75%"></div>
<div class="loading-box maskbg">
	<img src="${ctx}/images/loading.gif" />
</div>
<script type="text/javascript">
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
	$("#aAgentOneName").val('');
	$("#agentOneOpenId").val('');
	$("#agentOneName").val('');
}
function cleanOn3(){
	$("#aAgentTwoName").val('');
	$("#agentTwoOpenId").val('');
	$("#agentTwoName").val('');
}
function cleanOn4(){
	$("#aServicerName").val('');
	$("#servicerId").val('');
	$("#servicerName").val('');
}
	var $modal = $('#temModal');
	
	$(document).ready(function(){
    	searchData(1);
    });
	function searchData(pageNo){
		var returnNum = $("#returnNum").val();
			var sortColumn = $("#sortColumn").val();
		    var o_id = $("#fO_id").val();
		    var o_no = $("#fO_no").val();
		    var query_id = $("#fQuery_id").val();
		    var pay_ip = $("#fPay_ip").val();
		    var c_name = $("#c_name").val();
		    var c_id = $("#c_id").val();
		    var o_sub_name = $("#o_sub_name").val();
		    var amount = $("#fAmount").val();
		    var actual_payment = $("#fActual_payment").val();
		    var account_payment = $("#fAccount_payment").val();
		    var pay_type = $("#fPay_type").val();
		    var pay_account = $("#fPay_account").val();
		    var operator_id = $("#fOperator_id").val();
		    var payee = $("#fPayee").val();
		    var create_time = $("#fCreate_time").val();
		    var pay_start_time = $("#pay_start_time").val();
		    var pay_end_time = $("#pay_end_time").val();
		    var back_start_time = $("#back_start_time").val();
		    var back_end_time = $("#back_end_time").val();
		    var tick_start_time = $("#tick_start_time").val();
		    var tick_end_time = $("#tick_end_time").val();
		    var yw_id = $("#oneAgentOpenIdIndex").val();
		    var create_start_time = $("#create_start_time").val();
		    var create_end_time = $("#create_end_time").val();
		    var is_fp = $("#fis_fp").val();
		    var pay_type = $("#fpay_type").val();
		    var fr = $("#fFR").val();
		    
		    
		    var pay_status = $("#fPay_status").val();
		    var contact_phone = $("#contact_phone").val();
		    var tick_off_status = $("#fTick_off_status").val();
		    var tick_off_time = $("#fTick_off_time").val();
		    var back_fee_status = $("#fBack_fee_status").val();
		    var back_time = $("#fBack_time").val();
		    var is_zz = $("#fis_zz").val();
		    var agentOneOpenId = $("#agentOneOpenId").val();
		    var agentTwoOpenId = $("#agentTwoOpenId").val();
		    var servicerId = $("#servicerId").val();
		    var startTotalFee = $("#startTotalFee").val();
            var endTotalFee = $("#endTotalFee").val();
            var reg = /^0{1}([.]\d{1,2})?$|^[1-9]\d*([.]{1}[0-9]{1,2})?$/;
            if(startTotalFee!=""){
            	if(!reg.test(startTotalFee)){
                	tipError("订单面值金额范围只能输入数字，小数点后只能保留两位!");
                	return false;
                }
            }
            if(endTotalFee!=""){
            	 if(!reg.test(endTotalFee)){
                 	tipError("订单面值金额范围只能输入数字，小数点后只能保留两位!");
                 	return false;
                 }
            }
		    $.getJSON("<c:url value='/hPayOrder/getHPayOrderList'/>",
	        {
		    	startTotalFee:startTotalFee,
		    	endTotalFee:endTotalFee,
		    	agentOneOpenId :agentOneOpenId,
	    		agentTwoOpenId:agentTwoOpenId,
    			servicerId:servicerId,
	        	sortColumn:sortColumn,
	    		o_id : o_id,
	    		pay_type : pay_type,
	    		o_no : o_no,
	    		c_id : c_id,
	    		fr : fr,
	    		is_fp : is_fp,
	    		contact_phone : contact_phone,
	    		query_id : query_id,
	    		pay_ip : pay_ip,
	    		c_name : c_name,
	    		create_start_time : create_start_time,
	    		create_end_time : create_end_time,
	    		o_sub_name : o_sub_name,
	    		amount : amount,
	    		actual_payment : actual_payment,
	    		account_payment : account_payment,
	    		pay_type : pay_type,
	    		pay_account : pay_account,
	    		yw_id : yw_id,
	    		operator_id : operator_id,
	    		tick_end_time : tick_end_time,
	    		payee : payee,
	    		create_time : create_time,
	    		pay_start_time : pay_start_time,
	    		pay_end_time : pay_end_time,
	    		pay_status : pay_status,
	    		tick_off_status : tick_off_status,
	    		tick_off_time : tick_off_time,
	    		back_fee_status : back_fee_status,
	    		back_time : back_time,
	    		back_start_time : back_start_time,
	    		back_end_time : back_end_time,
	    		tick_start_time : tick_start_time,
	    		is_zz : is_zz,
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
			str+="<th>操	作</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_o_no\" column='o_no' onselectstart='return false' scope=\"col\">订单号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_query_id\" column='query_id' onselectstart='return false' scope=\"col\">交易流水号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_o_no\" column='o_no' onselectstart='return false' scope=\"col\">单位ID</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_c_id\" column='c_id' onselectstart='return false' scope=\"col\">单位名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_amount\" column='amount' onselectstart='return false' scope=\"col\">支付金额</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_pay_type\" column='pay_type' onselectstart='return false' scope=\"col\">业务类型</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_pay_type\" column='pay_type' onselectstart='return false' scope=\"col\">支付类型</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_pay_time\" width='135' column='pay_time' onselectstart='return false' scope=\"col\">支付时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_pay_status\" column='pay_status' onselectstart='return false' scope=\"col\">支付状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_pay_status\" column='pay_status' onselectstart='return false' scope=\"col\">订单生成时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_tick_off_status\" column='tick_off_status' onselectstart='return false' scope=\"col\">销账状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_tick_off_time\" width='135' column='tick_off_time' onselectstart='return false' scope=\"col\">销账时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_back_fee_status\" column='back_fee_status' onselectstart='return false' scope=\"col\">是否退费</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_back_time\" width='135' column='back_time' onselectstart='return false' scope=\"col\">退费时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_back_fee_status\" column='back_fee_status' onselectstart='return false' scope=\"col\">是否开票</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_back_time\" width='135' column='back_time' onselectstart='return false' scope=\"col\">是否开票</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_back_time\" column='back_time' onselectstart='return false' scope=\"col\">所属业务员</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_o_sub_id\" column='o_sub_id' onselectstart='return false' scope=\"col\">相关电表号</th>";
	    	
	    	str+= "<th class=\"sortTh\" id=\"th_o_sub_id\" column='o_sub_id' onselectstart='return false' scope=\"col\">单位联系人电话</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_o_sub_id\" column='o_sub_id' onselectstart='return false' scope=\"col\">联系人姓名</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_o_sub_id\" column='o_sub_id' onselectstart='return false' scope=\"col\">客户经理</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_o_sub_id\" column='o_sub_id' onselectstart='return false' scope=\"col\">代理</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_o_sub_id\" column='o_sub_id' onselectstart='return false' scope=\"col\">服务人员</th>";
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
	    	if(result.items[k].is_zz=="1"){
	    		 tableStr += "<tr id='DataRow"+k+"' style='color:red;'>";
	    	}else{
	    		 tableStr += "<tr id='DataRow"+k+"'>";
	    	}
	        if(result.items[k].fp_order_id!=""){
	        	tableStr += "<td ></td>";
	        }else{
	        	tableStr += "<td ><input type=\"checkbox\" id='"+result.items[k].o_id+"' value='"+result.items[k].o_no+"' company='"+result.items[k].c_id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" /></td>";
	        }
	        tableStr += "<td >" + (number + k + 1) + "</td>";
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	tableStr += "<perm:tag permPath='/hPayOrder/updateHPayOrder' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].o_id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hPayOrder/removeHPayOrder' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].o_id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
//         	if(result.items[k].fp_order_id==""){
//         		tableStr += "<perm:tag permPath='/hPayOrder/kaipiao' ><a class='btn btn-green btn-mini no-wrap' href='javascript:void(0);' onclick='kaipiao(" + result.items[k].o_id + ",\""+result.items[k].o_no+"\");return false;'><i class='aweso-icon-ok' />开票</a></perm:tag>";
//         	}
        	if(result.items[k].tick_off_status!='1'&&(result.items[k].pay_status=='1'||result.items[k].pay_status=='2'||result.items[k].pay_status=='3')){
        		tableStr += "<perm:tag permPath='/hPayOrder/writeOff' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='writeOff(" + result.items[k].o_id + ",\""+result.items[k].o_no+"\",\""+result.items[k].pay_status+"\");return false;'><i class='fontello-icon-minus-1' />销账</a></perm:tag>";
        	}
        	tableStr += "<a class='btn btn-green btn-mini no-wrap' href='javascript:void(0);' onclick='showDetail(" + result.items[k].o_id + ",\""+result.items[k].o_no+"\",\""+result.items[k].pay_status+"\");return false;'><i class='aweso-icon-ok' />查看明细</a>";
        	tableStr += "</div>";
        	tableStr += "</td>";
	       
	        
		        tableStr += "<td>" + result.items[k].o_no + "</td>";
		        tableStr += "<td>" + result.items[k].query_id + "</td>";
		        tableStr += "<td>" + result.items[k].c_id + "</td>";
		        tableStr += "<td>" + result.items[k].c_name + "</td>";
		        tableStr += "<td>" + result.items[k].amountStr + "</td>";
	        	tableStr += "<td>抄表电</td>";
	        	if(result.items[k].pay_type==1){
		        	tableStr += "<td>银联B2B</td>";
	        	}else if(result.items[k].pay_type==2){
		        	tableStr += "<td>手机支付</td>";
	        	}else{
		        	tableStr += "<td></td>";
	        	}
		        tableStr += "<td>" + genDateTimeAll(result.items[k].pay_time) + "</td>";
		        if(result.items[k].pay_status=='1'){
		        	tableStr += "<td>已支付</td>";
		        }else if(result.items[k].pay_status=='2'){
		        	tableStr += "<td>已支付(短款)</td>";
		        }else if(result.items[k].pay_status=='3'){
		        	tableStr += "<td>已支付(长款)</td>";
		        }else{
		        	tableStr += "<td>未支付</td>";
		        }
		        tableStr += "<td>" + genDateTimeAll(result.items[k].create_time) + "</td>";
		        if(result.items[k].tick_off_status=='1'){
		        	tableStr += "<td>已销账</td>";
		        }else{
		        	tableStr += "<td>未销账</td>";
		        }
		        tableStr += "<td>" + genDateTimeAll(result.items[k].tick_off_time) + "</td>";
		        if(result.items[k].back_fee_status=='1'){
		        	tableStr += "<td>退费成功</td>";
		        }else{
		        	tableStr += "<td>&nbsp;</td>";
		        }
		        tableStr += "<td>" + genDateTimeAll(result.items[k].back_time) + "</td>";
// 		        if(result.items[k].fp_order_id==""){
// 		        	tableStr += "<td>未开票</td>";
// 		        }else{
// 		        	tableStr += "<td>已开票</td>";
// 		        }
// 		        tableStr += "<td>" + result.items[k].yw_name + "</td>";
				if(result.items[k].is_fp==1){
					tableStr += "<td>已开票</td>";
				}else{
					tableStr += "<td>未开票</td>";
				}
		        tableStr += "<td>" + result.items[k].electric_number + "</td>";
		        
		        tableStr += "<td>" + result.items[k].contact_phone + "</td>";
		        tableStr += "<td>" + result.items[k].contact_name + "</td>";
		        tableStr += "<td>" + result.items[k].oneAgentName + "</td>";
		        tableStr += "<td>" + result.items[k].twoAgentName + "</td>";
		        tableStr += "<td>" + result.items[k].servicerName + "</td>";
	        tableStr += "</tr>";   
	        $("#totalFeeA").html('已支付金额:'+result.items[k].totalFeeStr+"元");
	        $("#totalFeeB").html('未支付金额:'+result.items[k].notTotalFeeStr+"元");
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
		show('<c:url value="/hPayOrder/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hPayOrder/toAdd"/>','');
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
					$.post("<c:url value='/hPayOrder/removeAllHPayOrder'/>",
		        	{
						o_ids :ids,
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
	function del(o_id){
	   if (o_id != ""){
		   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要删除吗?","取消","确定", function(result) {
			if(result){
				$(".loading-box").show();
				$.post("<c:url value='/hPayOrder/removeHPayOrder'/>",
	        	{
					o_id	:o_id,
					ranNum:Math.random()
				},
	        	function(data){
					$(".loading-box").hide();
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
		
	function writeOff(o_id,o_no,pay_status){
		show('<c:url value="/hPayOrder/toWriteOff?o_no='+o_no+'&o_id='+o_id+'&pay_status='+pay_status+'"/>','');
// 		if (o_id != ""){
// 			   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要销账吗?","取消","确定", function(result) {
// 				if(result){
// 					$.post("<c:url value='/hPayOrder/writeOff'/>",
// 		        	{
// 						o_id	:o_id,
// 						ranNum:Math.random()
// 					},
// 		        	function(data){
// 			        	var result = eval('('+data+')'); 
// 			            if (result.code == '1') {
// 			              	var pageNo = $("#currPage").val();           
// 			              	searchData(pageNo);
// 			              	tipOk("销账成功!");
// 			             } else {
// 			              	tipError(result.message);
// 			             }
// 			        });
// 				}else{
// 					取消
// 				}
// 			});
// 	   	   }
	}
	
	function kaipiaos(){
		var ids = '';
		var o_no = "";
		var map = new getMap();
		$("[name='checkName']").each(function(){
	    	var ck = $(this).attr("checked");
	    	if(ck == 'checked'){
	    		ids+=$(this).attr("id")+",";
	    		if(o_no==""){
	    			o_no = $(this).val();
	    		}else{
	    			o_no = o_no + "," +$(this).val();
	    		}
	    		map.put($(this).attr("company"),$(this).attr("company"))
	    	}
		})
		var key = map.keyset() + "";
		var b = false;
		if(key!=""){
			var keys = key.split(",");
			if(keys.length==1){
				b = true;
			}
		}
		if(ids!=''){
			if(b){
				show('<c:url value="/hPayOrder/addFP?o_no='+o_no+'"/>','');
			}else{
				tip("待开票条目公司必须一致!");
			}
		}else{
			tip("请选择待开票条目!");
		}
	}
	
	function showDetail(o_id,o_no,pay_status){
		show('<c:url value="/hPayOrder/showDetail?o_no='+o_no+'&o_id='+o_id+'&pay_status='+pay_status+'"/>','');
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
			tipError("请选择一级代理!");
		}
		
	}

// 	function searchData3(pageNo){
// 		var returnNum = $("#returnNum3").val();
// 			var sortColumn = $("#sortColumn3").val();
// 		    var name = $("#fOneAgentNameIndex").val();
// 		    $.getJSON("<c:url value='/manageAdminUser/getManageAdminUserList'/>",
// 	        {
// 	        	sortColumn:sortColumn,
// 	        	nickName : name,
// 	    		status : 1,
// 	    		oneAgentOpenId : $("#agentOneOpenId").val(),
// 	    		roleId : '${twoAgentRoleId}',
// 	    		pageNo: pageNo,
// 	    		rowCount: returnNum, 
// 				_t: Math.random()
// 	        },function(data){
// 	        var result = data;
// 	        if (result.code == 1) {
// 	            setTableStr3(result, pageNo, returnNum);
// 	        } else {
// 	        	tipError("系统异常!");
// 	        } 
// 	    });
// 	}
// 	function genTableHeader3(){
// 		var str = "<thead><tr>" ;
// 	    	str+= "<th scope=\"col\" class=\"check-col\"></th>";
// 	    	str+= "<th onselectstart='return false' scope=\"col\">序号</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">代理名称</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">手机</th>";
// 			str+="</tr></thead>";
// 		return str;
// 	}
// 	function setTableStr3(result, pageNo, returnNum){
// 	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
// 	    tableStr += genTableHeader3();
// 	    var number = (pageNo - 1) * returnNum;
// 	    tableStr += "<tfoot></tfoot>";
// 	    tableStr += "<tbody>";
// 	    var oneAgentOpenId = $("#fOneAgentNameIndex").val();
// 	    for (var k=0; k<result.items.length; k++){      
// 	        tableStr += "<tr id='DataRow"+k+"'>";
// 	        if(oneAgentOpenId!=""){
// 	        	if(oneAgentOpenId==result.items[k].adminId){
// 	        		tableStr += "<td ><input type=\"radio\" checked id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex(\""+result.items[k].twoAgentOpenId+"\",\""+result.items[k].nickName+"\")'/></td>";
// 	        	}else{
// 	        		tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex(\""+result.items[k].twoAgentOpenId+"\",\""+result.items[k].nickName+"\")'/></td>";
// 	        	}
// 	        }else{
// 	        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex(\""+result.items[k].twoAgentOpenId+"\",\""+result.items[k].nickName+"\")'/></td>";
// 	        }
// 	        tableStr += "<td >" + (number + k + 1) + "</td>";
// 		        tableStr += "<td>" + result.items[k].realName + "</td>";
// 		        tableStr += "<td>" + result.items[k].mobile + "</td>";
// 	        tableStr += "</tr>";            
// 	    }
// 	    tableStr += "</tbody>";
// 	    $("#exampleDTC3").html(tableStr);
// 	    $("#currPage3").val(pageNo);	
// 	    $("#demo4").find("radio").uniform();//初始化复选框
// 	    genPageTag3(pageNo,result.totalResults,returnNum,'kkpager3');
// 	}
	function selonagentindex(adminId,name){
		$("#agentTwoName").val(name);
		$("#agentTwoOpenId").val(adminId);
		$("#agentTwoClose").click();
	}
	function cleanOnIndex(){
		$("#oneAgentindex").val("");
		$("#oneAgentOpenIdIndex").val("");
		$("#twoAgentIndex").val("");
		$("#twoAgentOpenIdIndex").val("");
	}
	function exportExcel(){
		var returnNum = $("#returnNum").val();
		var sortColumn = $("#sortColumn").val();
	    var o_id = $("#fO_id").val();
	    var o_no = $("#fO_no").val();
	    var query_id = $("#fQuery_id").val();
	    var pay_ip = $("#fPay_ip").val();
	    var c_name = $("#c_name").val();
	    var o_sub_name = $("#o_sub_name").val();
	    var amount = $("#fAmount").val();
	    var actual_payment = $("#fActual_payment").val();
	    var account_payment = $("#fAccount_payment").val();
	    var pay_type = $("#fPay_type").val();
	    var pay_account = $("#fPay_account").val();
	    var operator_id = $("#fOperator_id").val();
	    var payee = $("#fPayee").val();
	    var create_time = $("#fCreate_time").val();
	    var pay_start_time = $("#pay_start_time").val();
	    var pay_end_time = $("#pay_end_time").val();
	    var back_start_time = $("#back_start_time").val();
	    var back_end_time = $("#back_end_time").val();
	    var tick_start_time = $("#tick_start_time").val();
	    var tick_end_time = $("#tick_end_time").val();
	    var yw_id = $("#oneAgentOpenIdIndex").val();
	    var create_start_time = $("#create_start_time").val();
	    var create_end_time = $("#create_end_time").val();
	    var fr = $("#fFR").val();
	    
	    
	    var pay_status = $("#fPay_status").val();
	    var tick_off_status = $("#fTick_off_status").val();
	    var tick_off_time = $("#fTick_off_time").val();
	    var back_fee_status = $("#fBack_fee_status").val();
	    var back_time = $("#fBack_time").val();
	    var is_zz = $("#fis_zz").val();
	    
	    var agentOneOpenId = $("#agentOneOpenId").val();
	    var agentTwoOpenId = $("#agentTwoOpenId").val();
	    var servicerId = $("#servicerId").val();
	    
	    var startTotalFee = $("#startTotalFee").val();
        var endTotalFee = $("#endTotalFee").val();
	    var reg = /^0{1}([.]\d{1,2})?$|^[1-9]\d*([.]{1}[0-9]{1,2})?$/;
        if(startTotalFee!=""){
        	if(!reg.test(startTotalFee)){
            	tipError("订单面值金额范围只能输入数字，小数点后只能保留两位!");
            	return false;
            }
        }
        if(endTotalFee!=""){
        	 if(!reg.test(endTotalFee)){
             	tipError("订单面值金额范围只能输入数字，小数点后只能保留两位!");
             	return false;
             }
        }
	    $.getJSON("<c:url value='/hPayOrder/exportExcel'/>",
        {
	    	startTotalFee:startTotalFee,
	    	endTotalFee:endTotalFee,
        	sortColumn:sortColumn,
    		o_id : o_id,
    		o_no : o_no,
    		query_id : query_id,
    		pay_ip : pay_ip,
    		c_name : c_name,
    		fr : fr,
    		agentOneOpenId :agentOneOpenId,
    		agentTwoOpenId:agentTwoOpenId,
			servicerId:servicerId,
    		o_sub_name : o_sub_name,
    		amount : amount,
    		actual_payment : actual_payment,
    		account_payment : account_payment,
    		pay_type : pay_type,
    		pay_account : pay_account,
    		yw_id : yw_id,
    		operator_id : operator_id,
    		tick_end_time : tick_end_time,
    		payee : payee,
    		create_time : create_time,
    		pay_start_time : pay_start_time,
    		pay_end_time : pay_end_time,
    		pay_status : pay_status,
    		tick_off_status : tick_off_status,
    		tick_off_time : tick_off_time,
    		back_fee_status : back_fee_status,
    		back_time : back_time,
    		back_start_time : back_start_time,
    		back_end_time : back_end_time,
    		tick_start_time : tick_start_time,
    		create_start_time : create_start_time,
    		create_end_time : create_end_time,
    		is_zz : is_zz,
        },function(data){
            var result = data;
        	if(result.code=='1'){
        		window.open('${ctx}'+result.items);
        	}else{
        		alert(result.message);
        	}
	    });
	}
	function exportUnpayExcel(){
		var returnNum = $("#returnNum").val();
		var sortColumn = $("#sortColumn").val();
	    var o_id = $("#fO_id").val();
	    var o_no = $("#fO_no").val();
	    var query_id = $("#fQuery_id").val();
	    var pay_ip = $("#fPay_ip").val();
	    var c_name = $("#c_name").val();
	    var o_sub_name = $("#o_sub_name").val();
	    var amount = $("#fAmount").val();
	    var actual_payment = $("#fActual_payment").val();
	    var account_payment = $("#fAccount_payment").val();
	    var pay_type = $("#fPay_type").val();
	    var pay_account = $("#fPay_account").val();
	    var operator_id = $("#fOperator_id").val();
	    var payee = $("#fPayee").val();
	    var create_time = $("#fCreate_time").val();
	    var pay_start_time = $("#pay_start_time").val();
	    var pay_end_time = $("#pay_end_time").val();
	    var back_start_time = $("#back_start_time").val();
	    var back_end_time = $("#back_end_time").val();
	    var tick_start_time = $("#tick_start_time").val();
	    var tick_end_time = $("#tick_end_time").val();
	    var yw_id = $("#oneAgentOpenIdIndex").val();
	    var create_start_time = $("#create_start_time").val();
	    var create_end_time = $("#create_end_time").val();
	    
	    var pay_status = 0;//未支付
	    var tick_off_status = $("#fTick_off_status").val();
	    var tick_off_time = $("#fTick_off_time").val();
	    var back_fee_status = $("#fBack_fee_status").val();
	    var back_time = $("#fBack_time").val();
	    var is_zz = $("#fis_zz").val();
	    $.getJSON("<c:url value='/hPayOrder/exportExcel'/>",
        {
        	sortColumn:sortColumn,
    		o_id : o_id,
    		o_no : o_no,
    		query_id : query_id,
    		pay_ip : pay_ip,
    		c_name : c_name,
    		o_sub_name : o_sub_name,
    		amount : amount,
    		actual_payment : actual_payment,
    		account_payment : account_payment,
    		pay_type : pay_type,
    		pay_account : pay_account,
    		yw_id : yw_id,
    		operator_id : operator_id,
    		tick_end_time : tick_end_time,
    		payee : payee,
    		create_time : create_time,
    		pay_start_time : pay_start_time,
    		pay_end_time : pay_end_time,
    		pay_status : pay_status,
    		tick_off_status : tick_off_status,
    		tick_off_time : tick_off_time,
    		back_fee_status : back_fee_status,
    		back_time : back_time,
    		back_start_time : back_start_time,
    		back_end_time : back_end_time,
    		tick_start_time : tick_start_time,
    		create_start_time : create_start_time,
    		create_end_time : create_end_time,
    		is_zz : is_zz,
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
