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
	<input type="hidden" id="sortColumn" value=" a.id desc, a.check_status asc "><!-- 排序字段 -->
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
	                                    	<select id="fSex" style="width: 100px;">
	                                    		<option value="">--请选择--</option>
	                                    		<option value="1">男</option>
	                                    		<option value="0">女</option>
	                                    	</select>
                                            <label class="margin5">手机:</label>
	                                    	<input type="text" id="fMobile1" class="span2 margin5" placeholder="手机">
<!--                                             <label class="margin5">style:</label> -->
<!-- 	                                    	<input type="text" id="fStyle" class="span2 margin5" placeholder="style"> -->
                                            <label class="margin5">状态:</label>
                                            <select id="fStatus" style="width: 100px;">
                                            <option >--请选择--</option>
                                            <option value="0">终止</option>
                                            <option value="1">正常</option>
                                            <option value="2">暂停</option>
                                            </select>
	                                    </div>
	                                    <div class="widget-row form-inline">
	                                    	<label class="margin5">一级代理:</label>
	                                    	<c:if test="${roleFlag==0 }">
		                                    	<input type="text" id="oneAgentindex" readonly="readonly" onclick="selOneindex()" data-toggle="modal">
								           		<a type="button" class="btn btn-boo" onclick="cleanOnIndex()">清除</a>
									           	<a href="#stack1" id="xxxindex1" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a> 
									           	<input type="hidden" id="fAgent_id"/>
								           	</c:if>
								           	<c:if test="${roleFlag==1 }">
								           		<input type="text" id="oneAgentindex" readonly="readonly" value="${hAgent.name }" data-toggle="modal">
								           	</c:if>
<!-- 								           	<label class="margin5">注册鼓励金金额:</label> -->
<!-- 	                                    	<input type="text" id="fReg_gl_fee" class="span2 margin5"> -->
<!-- 	                                    	<label class="margin5">注册鼓励金有效期开始时间:</label> -->
<!-- 	                                    	<input type="text" id="fRStartTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly"> -->
<!--                                             <label class="margin5">注册鼓励金有效期结束时间:</label> -->
<!-- 	                                    	<input type="text" id="fREndTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly"> -->
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
	                                 	 	<perm:tag permPath="/hAgentTwo/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
<%-- 	                                 	 	<perm:tag permPath="/hAgentTwo/removeAllHAgentTwo" > --%>
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
<%@ include file="/WEB-INF/page/hAgentTwoPP/guliYL.jsp" %>
 <%@ include file="/WEB-INF/page/hAgentTwoPP/guliSJ.jsp" %>
 <%@ include file="/WEB-INF/page/hAgentTwoPP/regGL.jsp" %>
 <script type="text/javascript" src="${ctx}/js/page.js"></script> 
 <a href="#stack2" id="sss" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a>
<a href="#stack3" id="ssss" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a>
<a href="#stack4" id="sssss" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a>
<!-- Only This Demo Page --> 
<div id="temModal" class="modal hide fade" tabindex="-1" data-width="80%"></div>
<div id="stack1" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择一级代理机构</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fOneAgentNameIndex" class="span2 margin5" placeholder="代理名称">
	    <a href="javascript:searchData1('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo1">
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
    <div class="modal-footer"> <a type="button" id="closeOneIndex" data-dismiss="modal" class="btn btn-boo">关闭</a></div>
    <input id="returnNum1" type="hidden" value="10"/>
    <input id="currPage1" type="hidden" value="1"/>
</div>
<script type="text/javascript">
	var $modal = $('#temModal');
	
	$(document).ready(function(){
    	searchData(1);
    });
	var flag = true;
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
		    var agent_id = $("#fAgent_id").val();
		    var agent_name = $("#fAgent_name").val();
		    var reg_gl_fee = $("#fReg_gl_fee").val();
		    var rStartTime = $("#fRStartTime").val();
		    var rEndTime = $("#fREndTime").val();
		    var reg1 = /^\d+(\.\d{2})?$/;
// 			if(reg_gl_fee!=""&&!reg1.test(reg_gl_fee)){
// 				tipError("注册鼓励金金额只能输入数字并且保留两位小数");
// 				flag = false;
// 			}
			if(flag){
				$.getJSON("<c:url value='/hAgentTwo/getHAgentTwoPP'/>",
				        {
				        	sortColumn:sortColumn,
				    		id : id,
				    		rStartTime : rStartTime,
				    		rEndTime : rEndTime,
				    		openId : openId,
				    		name : name,
				    		reg_gl_fee : reg_gl_fee,
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
				    		agent_id : agent_id,
				    		agent_name : agent_name,
				    		pageNo: pageNo,
				    		rowCount: returnNum, 
							_t: Math.random()
				        },function(data){
				        	flag = true;
			            var result = data;
			            if (result.code == 1) {
			                setTableStr(result, pageNo, returnNum);
			            } else {
			            	tipError("系统异常!");
			            } 
				    });
			}
	}
	function genTableHeader(){
		var str = "<thead><tr>" ;
    	str+= "<th scope=\"col\" class=\"check-col\"><input id=\"checkAllBtn\" type='checkbox' class='checkbox check-all' value='ON' onclick=\"checkAll('checkAllBtn','checkName');\" name='check-all'></th>";
    	str+= "<th onselectstart='return false' scope=\"col\">序号</th>";
    	str+="<th>操	作</th>";
    	str+= "<th width='50'>名称</th>";
    	str+= "<th width='50'>所属一级代理</th>";
//     	str+= "<th width='100'>营业执照编号</th>";
//     	str+= "<th width='50'>法人姓名</th>";
    	str+= "<th width='10'>性别</th>";
    	str+= "<th width='50'>手机</th>";
    	str+= "<th width='10' class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">类型</th>";
    	str+= "<th width='50' class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">状态</th>";
    	str+= "<th class=\"sortTh\" id=\"th_create_time\" width='135' column='create_time' onselectstart='return false' scope=\"col\">创建时间</th>";
    	str+= "<th width='10' class=\"sortTh\" id=\"th_check_status\" column='check_status' onselectstart='return false' scope=\"col\">审核状态</th>";
    	str+= "<th>联系人姓名</th>";
//     	str+= "<th class=\"sortTh\" id=\"th_contract_number\" column='contract_number' onselectstart='return false' scope=\"col\">合同编号</th>";
//     	str+= "<th class=\"sortTh\" id=\"th_contract_start_time\" width='135' column='contract_start_time' onselectstart='return false' scope=\"col\">合同开始时间</th>";
//     	str+= "<th class=\"sortTh\" id=\"th_contract_end_time\" width='135' column='contract_end_time' onselectstart='return false' scope=\"col\">合同截止时间</th>";
    	str+= "<th>银联B2B鼓励金简称</th>";
    	str+= "<th>银联B2B鼓励金类型</th>";
    	str+= "<th>银联B2B鼓励金按笔金额</th>";
    	str+= "<th>银联B2B鼓励分润比例</th>";
    	str+= "<th>手机支付鼓励金简称</th>";
    	str+= "<th>手机支付鼓励金类型</th>";
    	str+= "<th>手机支付鼓励金按笔金额</th>";
    	str+= "<th>手机支付鼓励分润比例</th>";
    	str+= "<th>注册鼓励金简称</th>";
    	str+= "<th>注册鼓励金金额</th>";
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
        	tableStr += "<perm:tag permPath='/hAgentTwo/payGuliYL' ><a class='btn btn-green btn-mini no-wrap' href='javascript:void(0);' onclick='payGuliYL(" + result.items[k].id + "," + result.items[k].gl_yl_id + ");return false;'>银联缴费鼓励金匹配</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hAgentTwo/payGuliSJ' ><a class='btn btn-blue btn-mini no-wrap' href='javascript:void(0);' onclick='payGuliSJ(" + result.items[k].id + "," + result.items[k].gl_sj_id + ");return false;'>手机支付鼓励金匹配</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hAgentTwo/regGL' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='regGL(" + result.items[k].id + "," + result.items[k].reg_gl_id + ");return false;'>注册鼓励金匹配</a></perm:tag>";
        	tableStr += "</div>";
        	tableStr += "</td>";
	        tableStr += "<td>" + result.items[k].name + "</td>";
	        tableStr += "<td>" + result.items[k].agent_name + "</td>";
// 	        tableStr += "<td>" + result.items[k].licence_code + "</td>";
// 	        tableStr += "<td>" + result.items[k].legal_person + "</td>";
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
// 	        tableStr += "<td>" + result.items[k].contract_number + "</td>";
// 	        tableStr += "<td>" + genDateTimeAll(result.items[k].contract_start_time) + "</td>";
// 	        tableStr += "<td>" + genDateTimeAll(result.items[k].contract_end_time) + "</td>";
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
	       	tableStr += "<td>" + result.items[k].reg_gl_name + "</td>";
	       	tableStr += "<td>" + result.items[k].reg_gl_fee + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody></table>";
	    $("#exampleDTC").html(tableStr);
	    $("#currPage").val(pageNo);	
        $("input.checkbox, input.radio, input:file.input-file").uniform();//初始化复选框
	    initTh();
	    genPageTag(pageNo,result.totalResults,returnNum,'kkpager');
	}
	// 更新
	function toUpdate(id) {
		show('<c:url value="/hAgentTwo/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hAgentTwo/toAdd"/>','');
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
					$.post("<c:url value='/hAgentTwo/removeAllHAgentTwo'/>",
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
				$.post("<c:url value='/hAgentTwo/removeHAgentTwo'/>",
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
		
	function selOneindex(){
		$("#xxxindex1").click();
		searchData1(1);
	}
	function cleanOnIndex(){
		$("#oneAgentindex").val("");
		$("#fAgent_id").val("");
	}
	function searchData1(pageNo){
		var returnNum = $("#returnNum1").val();
		var name = $("#fOneAgentNameIndex").val();
			var sortColumn = $("#sortColumn1").val();
		    $.getJSON("<c:url value='/hAgent/getHAgentList'/>",
	        {
	        	sortColumn:sortColumn,
	        	name : name,
	    		status : 1,
	    		check_status : 1,
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
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">代理名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">类型</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">手机</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">备注</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_check_status\" column='check_status' onselectstart='return false' scope=\"col\">审核状态</th>";
	    	str+="</tr></thead>";
		return str;
	}
	function setTableStr1(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader1();
	    var number = (pageNo - 1) * returnNum;
	    tableStr += "<tfoot></tfoot>";
	    tableStr += "<tbody>";
	    var oneAgentId = '';
// 	    var oneAgentId = $("#fAgent_id").val();
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        if(oneAgentId!=""){
	        	if(oneAgentId==result.items[k].id){
	        		tableStr += "<td ><input type=\"radio\" checked id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
	        	}else{
	        		tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
	        	}
	        }else{
	        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagentindex(\""+result.items[k].id+"\",\""+result.items[k].name+"\")'/></td>";
	        }
	        tableStr += "<td >" + (number + k + 1) + "</td>";
		        var ss = result.items[k].style;
		    	if(ss==1){ss='公司'}else if (ss==2){ss='个人'}
		    	var st = result.items[k].status;
		    	if(st==1){st = '正常'}else if(st==0){st = '终止'}else if(st==2){st = '暂停'}
		    	var cst = result.items[k].check_status;
		    	if(cst ==0){cst='待审核'}else if (cst==1){cst='审核通过'}
		        tableStr += "<td>" + result.items[k].name + "</td>";
		        tableStr += "<td>" + ss + "</td>";
		        tableStr += "<td>" + result.items[k].mobile1 + "</td>";
		        tableStr += "<td>" + result.items[k].remark1 + "</td>";
		        tableStr += "<td>" + st + "</td>";
		        tableStr += "<td>" + cst + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody>";
	    $("#exampleDTC1").html(tableStr);
	    $("#currPage1").val(pageNo);	
// 	    $("#demo4").find("radio").uniform();//初始化复选框
	    genPageTag1(pageNo,result.totalResults,returnNum,'kkpager1');
	}
	function selonagentindex(id,name){
		$("#oneAgentindex").val(name);
		$("#fAgent_id").val(id);
		$("#closeOneIndex").click();
	}
	var flag = true;
	function review(id){
		   if (id != ""){
			   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要审核通过吗?","取消","确定", function(result) {
				if(result&&flag){
					flag = false;
					$.post("<c:url value='/hAgentTwo/review'/>",
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
	function qrcode(id,revStatus){
		 if (id != ""&&revStatus==1){
				$.post("<c:url value='/hAgentTwo/qrcode'/>",
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
		            	html+= '<a class="btn btn-mini" href="${ctx}/hAgentTwo/getpic?id='+id+'&type=1">8CM</a>';
		            	html+= '<a class="btn btn-mini" href="${ctx}/hAgentTwo/getpic?id='+id+'&type=2">12CM</a>';
		            	html+= '<a class="btn btn-mini" href="${ctx}/hAgentTwo/getpic?id='+id+'&type=3">15CM</a>';
		            	html+= '<a class="btn btn-mini" href="${ctx}/hAgentTwo/getpic?id='+id+'&type=4">30CM</a>';
		            	html+= '<a class="btn btn-mini" href="${ctx}/hAgentTwo/getpic?id='+id+'&type=5">50CM</a>';
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
	
	var agentId="";
	var gl_yl_id="";
	var gl_sj_id="";
	var reg_gl_id="";
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
	function regGL(id,reg_gl_id1){
		$("#sssss").click();
		agentId=id;
		searchData4(1);
		reg_gl_id = reg_gl_id1;
	}
</script>
</body>
</html>
