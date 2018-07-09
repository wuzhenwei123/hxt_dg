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
.maskbg{background:rgba(0,0,0,.8);display:none;height:100%;left:0;position:fixed;top:0;width:100%;z-index:9999999;overflow: auto;}
.loading{text-align:center;margin:1em auto;width:100%;}
.loading-box img{left:50%;margin-left:-30px;position:absolute;top:50%; margin-top: -30px;}
</style>
<style type="text/css">
.preview1{vertical-align:middle;text-align:center;}
</style>
</head>

<body class="sidebar-left">
    <input type="hidden" id="currPage" value="1"><!-- 当前页码 -->
    <input type="hidden" id="pic_front" value="${pic_front}"><!-- 当前页码 -->
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
                                            <label class="margin5">联系人姓名:</label>
	                                    	<input type="text" id="fProxyName" class="span2 margin5">
                                            <label class="margin5">联系人电话:</label>
	                                    	<input type="text" id="fProxyPhone" class="span2 margin5">
                                            <label class="margin5">合同编号:</label>
	                                    	<input type="text" id="fContractNumber" class="span2 margin5">
                                            <label class="margin5">付款行行号:</label>
	                                    	<input type="text" id="fBankName" class="span2 margin5" readonly="readonly" onclick="selOneindex4()">
	                                    	<a type="button" class="btn btn-boo" onclick="cleanOnIndex4()">清除</a>
								           	<a href="#stack4" id="xxxindex4" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
								           	<input type="hidden" id="fBank_number" isNotNull="true" warnName="付款行行号"/>
                                            <label class="margin5">隶属客户:</label>
	                                    	<input type="text" id="fCName" class="span2 margin5" readonly="readonly" onclick="selOneindex1()">
	                                    	<a type="button" class="btn btn-boo" onclick="cleanOnIndex1()">清除</a>
								           	<a href="#stack1" id="xxxindex1" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
								           	<input type="hidden" id="fCId" isNotNull="true" warnName="隶属客户"/>
                                            <label class="margin5">审核状态:</label>
                                            <select id="fCheckState" class="span2 margin5"> 
						                        <option value="">全部</option> 
						                        <option value="0">开通审核中</option> 
						                        <option value="1">开通审核通过</option>
						                        <option value="2">开通审核驳回</option> 
						                        <option value="3">终止审核中</option>  
						                        <option value="4">终止审核通过</option>  
						                        <option value="5">终止审核驳回</option>
						                        <option value="6">变更审核中</option>  
						                        <option value="7">变更审核通过</option>  
						                        <option value="8">变更审核驳回</option>    
						                	</select> 
                                            <label class="margin5">用户编号:</label>
	                                    	<input type="text" id="fUserNumber" class="span2 margin5">
                                            <label class="margin5">联系人邮编:</label>
	                                    	<input type="text" id="fProxyCode" class="span2 margin5">
                                            <label class="margin5">付款人账号:</label>
	                                    	<input type="text" id="fPayBankNumber" class="span2 margin5">
                                            <label class="margin5">付款人姓名:</label>
	                                    	<input type="text" id="fPayName" class="span2 margin5">
                                            <label class="margin5">付款人证件号:</label>
	                                    	<input type="text" id="fPayCard" class="span2 margin5">
                                            <label class="margin5">付款人电话:</label>
	                                    	<input type="text" id="fPayPhoneNumber" class="span2 margin5">
                                            <label class="margin5">付款人邮箱:</label>
	                                    	<input type="text" id="fPayMail" class="span2 margin5">
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
	                                 	 	<perm:tag permPath="/hProxyMessage/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/hProxyMessage/removeAllHProxyMessage" >
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
<div id="temModal" class="modal hide fade" tabindex="-1" data-width="50%"></div>

<div id="stack1" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择客户</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fOneAgentNameIndex1" class="span2 margin5" placeholder="客户名称">
	    <a href="javascript:searchData1('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo3">
	         <div class="widget-body">
	             <table id="exampleDTC1" class="table table-striped table-content table-condensed boo-table table-hover bg-blue-light">
		         </table>
		         <div class="widget-footer">
		             <div class="btn-toolbar pull-left">
		             </div>
		             <div class="pagination pagination-btn pull-right">
		             	<div id="kkpager1"></div>
		             </div>
		         </div>
	         </div>
		</div>
    </div>
    <div class="modal-footer"> <a type="button" id="closeOneIndex1" data-dismiss="modal" class="btn btn-boo">关闭</a></div>
    <input id="returnNum1" type="hidden" value="10" />
    <input id="currPage1" type="hidden" value="1"/>
    <input id="sortColumn1" type="hidden" value=""/>
</div>
<div id="stack4" class="modal hide fade" tabindex="-1" data-focus-on="input:first" style="widows: 600px;">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择付款行</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fOneAgentNameIndex4" class="span2 margin5" placeholder="付款行名称">
	    <a href="javascript:searchData4('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo4">
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
    <div class="modal-footer"> <a type="button" id="closeOneIndex4" data-dismiss="modal" class="btn btn-boo">关闭</a></div>
    <input id="returnNum4" type="hidden" value="10" />
    <input id="currPage4" type="hidden" value="1"/>
</div>
<div class="loading-box maskbg">
	<img src="${ctx}/images/loading.gif" />
</div>
<div class="preview1 maskbg" data-p="" onclick="closePreview()">
	<img src="${ctx}/images/loading.gif" />
</div>
<script type="text/javascript">
	var $modal = $('#temModal');
	
	$(document).ready(function(){
    	searchData(1);
    });
	function searchData(pageNo){
		var returnNum = $("#returnNum").val();
			var sortColumn = $("#sortColumn").val();
		    var id = $("#fId").val();
		    var proxyName = $("#fProxyName").val();
		    var proxyPhone = $("#fProxyPhone").val();
		    var createYime = $("#fCreateYime").val();
		    var contractNumber = $("#fContractNumber").val();
		    var contractStartTime = $("#fContractStartTime").val();
		    var contractEndTime = $("#fContractEndTime").val();
		    var remindStartDate = $("#fRemindStartDate").val();
		    var remindEndDate = $("#fRemindEndDate").val();
		    var bank_number = $("#fBank_number").val();
		    var info = $("#fInfo").val();
		    var remark1 = $("#fRemark1").val();
		    var remark2 = $("#fRemark2").val();
		    var remark3 = $("#fRemark3").val();
		    var cId = $("#fCId").val();
		    var userId = $("#fUserId").val();
		    var checkState = $("#fCheckState").val();
		    var userNumber = $("#fUserNumber").val();
		    var proxyAddress = $("#fProxyAddress").val();
		    var proxyCode = $("#fProxyCode").val();
		    var payBankNumber = $("#fPayBankNumber").val();
		    var payName = $("#fPayName").val();
		    var payCardStyle = $("#fPayCardStyle").val();
		    var payCard = $("#fPayCard").val();
		    var payPhoneNumber = $("#fPayPhoneNumber").val();
		    var payMail = $("#fPayMail").val();
		    $.getJSON("<c:url value='/hProxyMessage/getHProxyMessageList'/>",
	        {
	        	sortColumn:sortColumn,
	    		id : id,
	    		proxyName : proxyName,
	    		proxyPhone : proxyPhone,
	    		createYime : createYime,
	    		contractNumber : contractNumber,
	    		contractStartTime : contractStartTime,
	    		contractEndTime : contractEndTime,
	    		remindStartDate : remindStartDate,
	    		remindEndDate : remindEndDate,
	    		bank_number : bank_number,
	    		info : info,
	    		remark1 : remark1,
	    		remark2 : remark2,
	    		remark3 : remark3,
	    		cId : cId,
	    		userId : userId,
	    		checkState : checkState,
	    		userNumber : userNumber,
	    		proxyAddress : proxyAddress,
	    		proxyCode : proxyCode,
	    		payBankNumber : payBankNumber,
	    		payName : payName,
	    		payCardStyle : payCardStyle,
	    		payCard : payCard,
	    		payPhoneNumber : payPhoneNumber,
	    		payMail : payMail,
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
	    	str+= "<th class=\"sortTh\" id=\"th_proxyName\" column='proxyName' onselectstart='return false' scope=\"col\">单位名称</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_proxyPhone\" column='proxyPhone' onselectstart='return false' scope=\"col\">联系人电话</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_userNumber\" column='userNumber' onselectstart='return false' scope=\"col\">用户编号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_contractNumber\" column='contractNumber' onselectstart='return false' scope=\"col\">合同编号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_cId\" column='cId' onselectstart='return false' scope=\"col\">所属客户</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_remindStartDate\" column='remindStartDate' onselectstart='return false' scope=\"col\">信息提醒日期</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_payCard\" column='payCard' onselectstart='return false' scope=\"col\">账户负责人证件号码</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_bank_number\" column='bank_number' onselectstart='return false' scope=\"col\">开户行行号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_bank_number\" column='bank_number' onselectstart='return false' scope=\"col\">账户名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_payBankNumber\" column='payBankNumber' onselectstart='return false' scope=\"col\">开户账号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_payBankNumber\" column='payBankNumber' onselectstart='return false' scope=\"col\">财务负责人姓名</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_payPhoneNumber\" column='payPhoneNumber' onselectstart='return false' scope=\"col\">账户负责人手机号码</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_payMail\" column='payMail' onselectstart='return false' scope=\"col\">账户负责人电子邮箱</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_payMail\" column='payMail' onselectstart='return false' scope=\"col\">账户负责人地址</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_proxyCode\" column='proxyCode' onselectstart='return false' scope=\"col\">账户负责人地址邮编</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_createYime\" width='135' column='createYime' onselectstart='return false' scope=\"col\">操作时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_contractStartTime\" width='135' column='contractStartTime' onselectstart='return false' scope=\"col\">合同开始时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_contractEndTime\" width='135' column='contractEndTime' onselectstart='return false' scope=\"col\">合同结束时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_state\" column='state' onselectstart='return false' scope=\"col\">审核状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_state\" column='state' onselectstart='return false' scope=\"col\">客户经理</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_state\" column='state' onselectstart='return false' scope=\"col\">代理</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_state\" column='state' onselectstart='return false' scope=\"col\">服务人员</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_state\" column='state' onselectstart='return false' scope=\"col\">单位名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_state\" column='state' onselectstart='return false' scope=\"col\">单位ID</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_state\" column='state' onselectstart='return false' scope=\"col\">注册人账号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_state\" column='state' onselectstart='return false' scope=\"col\">注册人姓名</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_payName\" column='payName' onselectstart='return false' scope=\"col\">付款人姓名</th>";
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
        	tableStr += "<perm:tag permPath='/hProxyMessage/updateHProxyMessage' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hProxyMessage/removeHProxyMessage' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
        	if(result.items[k].checkState=="0"){
        		tableStr += "<perm:tag permPath='/hProxyMessage/shenhe' ><a class='btn btn-red btn-mini no-wrap' href='javascript:void(0);' onclick='shenhe(" + result.items[k].id + ");return false;'><i class='fontello-icon-eye-1' />开通审核</a></perm:tag>";
        	}else if(result.items[k].checkState=="3"){
        		tableStr += "<perm:tag permPath='/hProxyMessage/zzshenhe' ><a class='btn btn-red btn-mini no-wrap' href='javascript:void(0);' onclick='zzshenhe(" + result.items[k].id + ");return false;'><i class='fontello-icon-eye-1' />终止审核</a></perm:tag>";
        	}else if(result.items[k].checkState=="6"){
        		tableStr += "<perm:tag permPath='/hProxyMessage/bgshenhe' ><a class='btn btn-red btn-mini no-wrap' href='javascript:void(0);' onclick='bgshenhe(" + result.items[k].id + ");return false;'><i class='fontello-icon-eye-1' />变更审核</a></perm:tag>";
        	}
        	if(result.items[k].hetongUrl!=""){
        		tableStr += "<a class='btn btn-red btn-mini no-wrap' href='javascript:void(0);' onclick='preview11(this,\"" + result.items[k].hetongUrl + "\");return false;'><i class='fontello-icon-eye-1' />查看开通协议</a>";
        	}
        	if(result.items[k].chexiaoUrl!=""){
        		tableStr += "<a class='btn btn-red btn-mini no-wrap' href='javascript:void(0);' onclick='preview11(this,\"" + result.items[k].chexiaoUrl + "\");return false;'><i class='fontello-icon-eye-1' />查看终止协议</a>";
        	}
        	if(result.items[k].biangengUrl!=""){
        		tableStr += "<a class='btn btn-red btn-mini no-wrap' href='javascript:void(0);' onclick='preview11(this,\"" + result.items[k].biangengUrl + "\");return false;'><i class='fontello-icon-eye-1' />查看变更协议</a>";
        	}
        	tableStr += "</div>";
        	tableStr += "</td>";
		        tableStr += "<td>" + result.items[k].proxyName + "</td>";
// 		        tableStr += "<td>" + result.items[k].proxyPhone + "</td>";
		        tableStr += "<td>" + result.items[k].userNumber + "</td>";
		        tableStr += "<td>" + result.items[k].contractNumber + "</td>";
		        tableStr += "<td>" + result.items[k].cName + "</td>";
// 		        tableStr += "<td>" + result.items[k].remindStartDate + "~" + result.items[k].remindEndDate + "</td>";
		        tableStr += "<td>" + result.items[k].payCard + "</td>";
		        tableStr += "<td>" + result.items[k].bank_number + "</td>";
		        tableStr += "<td>" + result.items[k].payAccountName + "</td>";
		        tableStr += "<td>" + result.items[k].payBankNumber + "</td>";
		        tableStr += "<td>" + result.items[k].payName + "</td>";
		        tableStr += "<td>" + result.items[k].payPhoneNumber + "</td>";
		        tableStr += "<td>" + result.items[k].payMail + "</td>";
		        tableStr += "<td>" + result.items[k].proxyAddress + "</td>";
		        tableStr += "<td>" + result.items[k].proxyCode + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].createYime) + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].contractStartTime) + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].contractEndTime) + "</td>";
		        if(result.items[k].checkState==1){
		        	tableStr += "<td>开通审核通过</td>";
		        }else if(result.items[k].checkState==0){
		        	tableStr += "<td>开通审核中</td>";
		        }else if(result.items[k].checkState==2){
		        	tableStr += "<td>开通审核驳回</td>";
		        }else if(result.items[k].checkState==3){
		        	tableStr += "<td>终止审核中</td>";
		        }else if(result.items[k].checkState==4){
		        	tableStr += "<td>终止审核通过</td>";
		        }else if(result.items[k].checkState==5){
		        	tableStr += "<td>终止审核驳回</td>";
		        }else if(result.items[k].checkState==6){
		        	tableStr += "<td>变更审核中</td>";
		        }else if(result.items[k].checkState==7){
		        	tableStr += "<td>变更审核通过</td>";
		        }else if(result.items[k].checkState==8){
		        	tableStr += "<td>变更审核驳回</td>";
		        }else{
		        	tableStr += "<td>其他</td>";
		        }
		        
		        tableStr += "<td>" + result.items[k].oneAgentName + "</td>";
		        tableStr += "<td>" + result.items[k].twoAgentName + "</td>";
		        tableStr += "<td>" + result.items[k].servicerName + "</td>";
		        tableStr += "<td>" + result.items[k].cName + "</td>";
		        tableStr += "<td>" + result.items[k].cId + "</td>";
		        tableStr += "<td>" + result.items[k].contact_phone + "</td>";
		        tableStr += "<td>" + result.items[k].contact_name + "</td>";
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
		show('<c:url value="/hProxyMessage/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hProxyMessage/toAdd"/>','');
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
					$(".loading-box").show();
					$.post("<c:url value='/hProxyMessage/removeAllHProxyMessage'/>",
		        	{
						ids :ids,
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
			
		}else{
			tip("请选择待删除条目!");
		}
	}
		// 单条删除
	function del(id){
	   if (id != ""){
		   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要删除吗?","取消","确定", function(result) {
			if(result){
				$(".loading-box").show();
				$.post("<c:url value='/hProxyMessage/removeHProxyMessage'/>",
	        	{
					id	:id,
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
	function shenhe(id){
		show('<c:url value="/hProxyMessage/toShenhe/'+id+'"/>','');
// 	   if (id != ""){
// 		   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要审核吗?","取消","确定", function(result) {
// 			if(result){
// 				$(".loading-box").show();
// 				$.post("<c:url value='/hProxyMessage/shenhe'/>",
// 	        	{
// 					id	:id,
// 					cId	:id,
// 					ranNum:Math.random()
// 				},
// 	        	function(data){
// 					$(".loading-box").hide();
// 		        	var result = eval('('+data+')'); 
// 		            if (result.code == '1') {
// 		              	var pageNo = $("#currPage").val();           
// 		              	searchData(pageNo);
// 		              	tipOk("审核成功!");
// 		             } else {
// 		              	tipError(result.message);
// 		             }
// 		        });
// 			}else{
// 				取消
// 			}
// 		});
//    	   }
    }
		
	function selOneindex1(){
		$("#xxxindex1").click();
		searchData1(1);
	}
	function cleanOnIndex1(){
		$("#fCName").val("");
		$("#fCId").val("");
	}
	function searchData1(pageNo){
		var returnNum = $("#returnNum1").val();
		var name = $("#fOneAgentNameIndex1").val();
			var sortColumn = $("#sortColumn1").val();
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
	            setTableStr1(result, pageNo, returnNum);
	        } else {
	        	tipError("系统异常!");
	        } 
	    });
	}
	function genTableHeader1(){
		var str = "<thead><tr>" ;
	    	str+= "<th scope=\"col\" class=\"check-col\"></th>";
	    	str+= "<th onselectstart='return false' scope=\"col\">序号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">客户名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">联系人手机</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">审核状态</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr1(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader1();
	    var number = (pageNo - 1) * returnNum;
	    tableStr += "<tfoot></tfoot>";
	    tableStr += "<tbody>";
// 	    var oneAgentId = '';
	    var cId = $("#fCId").val();
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        if(cId!=""){
	        	if(cId==result.items[k].id){
	        		tableStr += "<td ><input type=\"radio\" checked id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex1(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
	        	}else{
	        		tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex1(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
	        	}
	        }else{
	        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex1(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
	        }
	        tableStr += "<td >" + (number + k + 1) + "</td>";
		        var status = result.items[k].status;
		    	if(status==1){status = '正常'}else if(status==0){status = '终止'}else if(status==2){status = '暂停'}
		    	var verify_status = result.items[k].verify_status;
		    	if(verify_status ==0){verify_status='待审核'}else if (verify_status==1){verify_status='审核通过'}else if (verify_status==2){verify_status='审核不通过'}
		        tableStr += "<td>" + result.items[k].name + "</td>";
		        tableStr += "<td>" + result.items[k].contact_phone + "</td>";
		        tableStr += "<td>" + status + "</td>";
		        tableStr += "<td>" + verify_status + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody></table>";
	    $("#exampleDTC1").html(tableStr);
	    $("#currPage1").val(pageNo);	
// 	    $("#demo4").find("radio").uniform();//初始化复选框
	    genPageTag1(pageNo,result.totalResults,returnNum,'kkpager1');
	}
	function selonagentindex1(id,name,openId){
		$("#fCName").val(name);
		$("#fCId").val(id);
		$("#closeOneIndex1").click();
	}
	
	function selOneindex4(){
		$("#xxxindex4").click();
		searchData4(1);
	}
	function cleanOnIndex4(){
		$("#fBankName").val("");
		$("#fBank_number").val("");
    	
	}
	function searchData4(pageNo){
		var returnNum = $("#returnNum4").val();
		var name = $("#fOneAgentNameIndex4").val();
			var sortColumn = $("#sortColumn4").val();
		    $.getJSON("<c:url value='/hBankInfo/getHBankInfoList'/>",
	        {
	        	sortColumn:sortColumn,
	        	name : name,
	    		pageNo: pageNo,
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
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">开户行行号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">开户行名称</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr4(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader4();
	    var number = (pageNo - 1) * returnNum;
	    tableStr += "<tfoot></tfoot>";
	    tableStr += "<tbody>";
	    var bank_number = $("#aBank_number").val();
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        if(bank_number!=""){
	        	if(bank_number==result.items[k].bankNum){
	        		tableStr += "<td ><input type=\"radio\" checked id='"+result.items[k].bankNum+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex4(\""+result.items[k].bankNum+"\",\""+result.items[k].name+"\")'/></td>";
	        	}else{
	        		tableStr += "<td ><input type=\"radio\" id='"+result.items[k].bankNum+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex4(\""+result.items[k].bankNum+"\",\""+result.items[k].name+"\")'/></td>";
	        	}
	        }else{
	        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].bankNum+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex4(\""+result.items[k].bankNum+"\",\""+result.items[k].name+"\")'/></td>";
	        }
	        tableStr += "<td >" + (number + k + 1) + "</td>";
		        tableStr += "<td>" + result.items[k].bankNum + "</td>";
		        tableStr += "<td>" + result.items[k].name + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody></table>";
	    $("#exampleDTC4").html(tableStr);
	    $("#currPage4").val(pageNo);	
// 	    $("#demo4").find("radio").uniform();//初始化复选框
	    genPageTag4(pageNo,result.totalResults,returnNum,'kkpager4');
	}
	function selonagentindex4(id,name){
		$("#fBankName").val(name+"("+id+")");
		$("#fBank_number").val(id);
		$("#closeOneIndex4").click();
	}
	
	function zzshenhe(id){
		show('<c:url value="/hProxyMessage/tozzShenhe/'+id+'"/>','');
	}
	
	function bgshenhe(id){
		show('<c:url value="/hProxyMessage/tobgShenhe/'+id+'"/>','');
	}
	
	function preview11(obj,hetongUrl){
		//获取图片的宽和高
		
		var image = new Image();
	    image.src = $("#pic_front").val()+hetongUrl;
	    $(".preview1").show();
	    image.onload = function(){
	    	var naturalWidth = image.width;
	 	    var naturalHight = image.height;
	 		$(".preview1").find("img").attr('src',$("#pic_front").val()+hetongUrl);
	 		$(".preview1").find("img").attr('width',naturalWidth);
	 		$(".preview1").find("img").attr('height',naturalHight);
	    };
	   
	}
	
	function closePreview(){
		$(".preview1").hide();
		$(".preview1").find("img").attr('src',"${ctx}/images/loading.gif");
		$(".preview1").find("img").attr('width','32');
 		$(".preview1").find("img").attr('height','32');
	}
</script>
</body>
</html>
