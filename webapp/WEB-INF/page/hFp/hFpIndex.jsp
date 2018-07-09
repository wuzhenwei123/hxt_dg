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
	<input type="hidden" id="currPage1" value="1"><!-- 当前页码 -->
	<input type="hidden" id="returnNum1" value="10"><!-- 分页返回 -->
    <input type="hidden" id="currPage" value="1"><!-- 当前页码 -->
	<input type="hidden" id="returnNum" value="10"><!-- 分页返回 -->
	<input type="hidden" id="sortColumn" value=" a.createTime desc "><!-- 排序字段 -->
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
                                            <!-- <label class="margin5">id:</label>
	                                    	<input type="text" id="fId" class="span2 margin5" placeholder="id"> -->
                                            <label class="margin5">订单号:</label>
	                                    	<input type="text" id="fOrderNumber" class="span2 margin5" placeholder="订单号">
                                             <label class="margin5">发票金额:</label>
	                                    	<input type="text" id="fMoney" class="span2 margin5" placeholder="发票金额">
                                            <label class="margin5">发票抬头:</label>
	                                    	<input type="text" id="fTitle" class="span2 margin5" placeholder="发票抬头">
                                            <label class="margin5">收件人姓名:</label>
	                                    	<input type="text" id="fUserName" class="span2 margin5" placeholder="收件人姓名">
                                            <label class="margin5">收件人电话:</label>
	                                    	<input type="text" id="fPhone" class="span2 margin5" placeholder="收件人电话">
                                            <label class="margin5">收件人地址:</label>
	                                    	<input type="text" id="fAddress" class="span2 margin5" placeholder="收件人地址"> 
                                            <label class="margin5">电表号:</label>
	                                    	<input type="text" id="fRemark2" class="span2 margin5" placeholder="电表号">
<!--                                             <label class="margin5">createTime:</label> -->
<!-- 	                                    	<input type="text" id="fCreateTime" placeholder="createTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly"> -->
                                            <label class="margin5">快递单号</label>
	                                    	<input type="text" id="fExpress_no" class="span2 margin5" placeholder="快递单号">
                                            <label class="margin5">快递状态:</label>
	                                    	<select id="fExpress_status">
	                                    		<option value="">全部</option>
	                                    		<option value="1">已发</option>
	                                    		<option value="2">未发</option>
	                                    	</select>
                                            <label class="margin5">快递名称:</label>
	                                    	<input type="text" id="fExpress_name" class="span2 margin5" placeholder="快递名称">
<!--                                             <label class="margin5">邮寄方式:</label> -->
<!-- 	                                    	<select id="fMailType"> -->
<!-- 	                                    		<option value="">全部</option> -->
<!-- 	                                    		<option value="1">快递</option> -->
<!-- 	                                    		<option value="2">挂号信</option> -->
<!-- 	                                    		<option value="3">平信</option> -->
<!-- 	                                    	</select> -->
	                                    	<input type="hidden" id="fYwyId" value='${ywyadmin }'>
	                                    	<label class="margin5">发票类型:</label>
	                                    	<select id="fFP_style">
	                                    		<option value="">全部</option>
	                                    		<option value="1">恒信通发票</option>
	                                    		<option value="2">电力增值税发票</option>
	                                    		<option value="3">电力普通票</option>
	                                    	</select>
                                            <!-- <label class="margin5">remark1:</label>
	                                    	<input type="text" id="fRemark1" class="span2 margin5" placeholder="remark1">
                                            <label class="margin5">remark2:</label>
	                                    	<input type="text" id="fRemark2" class="span2 margin5" placeholder="remark2">
                                            <label class="margin5">remark3:</label>
	                                    	<input type="text" id="fRemark3" class="span2 margin5" placeholder="remark3"> -->
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
<!-- 	                                 	 	<perm:tag permPath="/hFp/toAdd" > -->
<!-- 	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a>  -->
<!-- 	                                 	 	</perm:tag> -->
	                                 	 	<perm:tag permPath="/hFp/removeAllHFp" >
	                                 	 	<a href="javascript:delAll();" class="btn btn-glyph"><i class="fontello-icon-minus-1"></i>删除</a> 
	                                 	 	</perm:tag>
	                                 	 	<a href="javascript:exportExcel();" class="btn btn-glyph">导出</a>
	                                 	 	<a href="javascript:exportExcelTag();" class="btn btn-glyph">标签excel</a>
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
<a href="#stack1" id="xxx" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal"> </a>
<div id="stack1" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
		    <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
		        <h4>追加发票详情</h4>
		    </div>
		    <div class="modal-body">
<!-- 			    <input type="text" id="fOneAgentName" class="span2 margin5" placeholder="业务员名称"> -->
<!-- 			    <a href="javascript:searchData1('1');" class="btn btn-green">查询</a> -->
		    	<div class="widget-content" id="demo2">
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
		    <div class="modal-footer"> <a type="button" id="closeOne" data-dismiss="modal" class="btn btn-boo">关闭</a></div>
		</div>
<script type="text/javascript">
	var $modal = $('#temModal');
	
	$(document).ready(function(){
    	searchData(1);
    });
	function searchData(pageNo){
		var returnNum = $("#returnNum").val();
			var sortColumn = $("#sortColumn").val();
// 		    var id = $("#fId").val();
		    var orderNumber = $("#fOrderNumber").val();
		    var money = $("#fMoney").val();
		    var title = $("#fTitle").val();
		    var userName = $("#fUserName").val();
		    var phone = $("#fPhone").val();
		    var address = $("#fAddress").val();
		    var createTime = $("#fCreateTime").val();
		    var express_no = $("#fExpress_no").val();
		    var express_status = $("#fExpress_status").val();
		    var express_name = $("#fExpress_name").val();
		    var mailType = $("#fMailType").val();
		    var fp_style = $("#fFP_style").val();
// 		    var remark1 = $("#fRemark1").val();
		    var remark2 = $("#fRemark2").val();
// 		    var remark3 = $("#fRemark3").val();
			var ywyId = $("#fYwyId").val();
		    $.getJSON("<c:url value='/hFp/getHFpList'/>",
	        {
	        	sortColumn:sortColumn,
// 	    		id : id,
	    		orderNumber : orderNumber,
	    		money : money,
	    		title : title,
	    		userName : userName,
	    		phone : phone,
	    		address : address,
	    		createTime : createTime,
	    		express_no : express_no,
	    		express_status : express_status,
	    		express_name : express_name,
	    		mailType : mailType,
	    		fp_style : fp_style,
// 	    		remark1 : remark1,
	    		remark2 : remark2,
// 	    		remark3 : remark3,
				ywyId : ywyId,
	    		pageNo: pageNo,
	    		queryType : 1,
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
	    	str+= "<th class=\"sortTh\" id=\"th_orderNumber\" column='orderNumber' onselectstart='return false' scope=\"col\">订单号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_orderNumber\" column='orderNumber' onselectstart='return false' scope=\"col\">电表号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_money\" column='money' onselectstart='return false' scope=\"col\">发票金额</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_title\" column='title' onselectstart='return false' scope=\"col\">发票抬头</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_userName\" column='userName' onselectstart='return false' scope=\"col\">收件人姓名</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_phone\" column='phone' onselectstart='return false' scope=\"col\">收件人电话</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_address\" column='address' onselectstart='return false' scope=\"col\">收件人地址</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_createTime\" width='135' column='createTime' onselectstart='return false' scope=\"col\">申请时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_express_no\" column='express_no' onselectstart='return false' scope=\"col\">快递单号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_express_status\" column='express_status' onselectstart='return false' scope=\"col\">快递状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_express_name\" column='express_name' onselectstart='return false' scope=\"col\">快递名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_mailType\" column='mailType' onselectstart='return false' scope=\"col\">邮寄方式</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_remark1\" column='remark1' onselectstart='return false' scope=\"col\">发票类型</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_remark2\" column='remark2' onselectstart='return false' scope=\"col\">remark2</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_remark3\" column='remark3' onselectstart='return false' scope=\"col\">remark3</th>";
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
	        
		        tableStr += "<td>" + result.items[k].orderNumber + "</td>";
		        tableStr += "<td>" + result.items[k].remark2 + "</td>";
		        tableStr += "<td>" + result.items[k].totalFeeStr + "</td>";
		        tableStr += "<td>" + result.items[k].title + "</td>";
		        tableStr += "<td>" + result.items[k].userName + "</td>";
		        tableStr += "<td>" + result.items[k].phone + "</td>";
		        tableStr += "<td>" + result.items[k].address + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].createTime) + "</td>";
		        tableStr += "<td>" + result.items[k].express_no + "</td>";
		        var est = result.items[k].express_status;
		        if(est==1){
		        	est='已发送';
		        }else{
		        	est='未发送';
		        }
		        tableStr += "<td>" + est + "</td>";
		        tableStr += "<td>" + result.items[k].express_name + "</td>";
		        var mailtype = result.items[k].mailType;
		        if(mailtype ==1){mailtype='快递';}else if(mailtype ==2){mailtype='挂号信'}else if(mailtype==3){mailtype='平信'}else{mailtype=''}
		        tableStr += "<td>" + mailtype + "</td>";
		        if(result.items[k].fp_style=="1"){
		        	tableStr += "<td>恒信通发票</td>";
		        }else if(result.items[k].fp_style=="2"){
		        	tableStr += "<td>电力增值税普票</td>";
		        }else if(result.items[k].fp_style=="3"){
		        	tableStr += "<td>电力增值税专票</td>";
		        }else if(result.items[k].fp_style=="4"){
		        	tableStr += "<td>电力定额发票</td>";
		        }else{
		        	tableStr += "<td></td>";
		        }
// 		        tableStr += "<td>" + result.items[k].remark2 + "</td>";
// 		        tableStr += "<td>" + result.items[k].remark3 + "</td>";
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	if(result.items[k].express_status!=1){
	        	tableStr += "<perm:tag permPath='/hFp/updateHFp' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toSend(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />发出快递</a></perm:tag>";
	        	tableStr += "<perm:tag permPath='/hFp/updateHFp' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	}else{
        		if(!result.items[k].remark3){
// 		        	tableStr += "<perm:tag permPath='/hFp/toAdd' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toAddd(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />追加</a></perm:tag>";
        		}
        	}
//         	tableStr += "<a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toShowAdd(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />追加详情</a>";
//         	tableStr += "<perm:tag permPath='/hFp/removeHFp' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
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
		show('<c:url value="/hFp/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hFp/toAdd"/>','');
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
					$.post("<c:url value='/hFp/removeAllHFp'/>",
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
				$.post("<c:url value='/hFp/removeHFp'/>",
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
		// 发送快递
	function toSend(id){
	   if (id != ""){
// 		   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要发送吗?","取消","确定", function(result) {
// 			if(result){
				
// 			}else{
// 				取消
// 			}
// 		});
		   $.post("<c:url value='/hFp/toSend'/>",
		        	{
						id	:id,
						ranNum:Math.random()
					},
		        	function(data){
			        	var result = eval('('+data+')'); 
			            if (result.code == '1') {
			              	var pageNo = $("#currPage").val();           
			              	searchData(pageNo);
			              	tipOk("发送 成功!");
			             } else {
			              	tipError(result.message);
			             }
			        });
   	   }
    }
	// 追加
	function toAddd(id){
		show('<c:url value="/hFp/toAdd?pid='+id+'"/>','');
	}
	// 追加
	function toShowAdd(id){
		$("#xxx").click();
		searchData1(1,id);
	}
	function searchData1(pageNo,pid){
		var returnNum = $("#returnNum1").val();
			var sortColumn = $("#sortColumn1").val();
		    $.getJSON("<c:url value='/hFp/toShowAdd'/>",
	        {
		    	id:pid,
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
// 	    	str+= "<th scope=\"col\" class=\"check-col\"></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">订单号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">发票金额</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">快递单号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">快递名称</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr1(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader1();
	    var number = (pageNo - 1) * returnNum;
	    tableStr += "<tfoot></tfoot>";
	    tableStr += "<tbody>";
	    var oneAgentOpenId = $("#aCreate_openId").val();
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
        	tableStr += "<td >" + result.items[k].orderNumber  + "</td>";
        	tableStr += "<td >" + result.items[k].money  + "</td>";
        	tableStr += "<td >" + result.items[k].express_no  + "</td>";
	        tableStr += "<td >" + result.items[k].express_name + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody>";
	    $("#exampleDTC1").html(tableStr);
	    $("#currPage1").val(pageNo);	
	    $("#demo2").find("radio").uniform();//初始化复选框
	    genPageTag1(pageNo,result.totalResults,returnNum,'kkpager1');
	}
	function exportExcel(){
		var returnNum = $("#returnNum").val();
		var sortColumn = $("#sortColumn").val();
//		    var id = $("#fId").val();
	    var orderNumber = $("#fOrderNumber").val();
	    var money = $("#fMoney").val();
	    var title = $("#fTitle").val();
	    var userName = $("#fUserName").val();
	    var phone = $("#fPhone").val();
	    var address = $("#fAddress").val();
	    var createTime = $("#fCreateTime").val();
	    var express_no = $("#fExpress_no").val();
	    var express_status = $("#fExpress_status").val();
	    var express_name = $("#fExpress_name").val();
	    var mailType = $("#fMailType").val();
	    var fp_style = $("#fFP_style").val();
//		    var remark1 = $("#fRemark1").val();
//		    var remark2 = $("#fRemark2").val();
//		    var remark3 = $("#fRemark3").val();
		var ywyId = $("#fYwyId").val();
	    $.getJSON("<c:url value='/hFp/exportExcel'/>",
        {
        	sortColumn:sortColumn,
    		orderNumber : orderNumber,
    		money : money,
    		title : title,
    		userName : userName,
    		phone : phone,
    		address : address,
    		createTime : createTime,
    		express_no : express_no,
    		express_status : express_status,
    		express_name : express_name,
    		mailType : mailType,
    		fp_style : fp_style,
			ywyId : ywyId,
    		queryType : 1,
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
	function exportExcelTag(){
		var returnNum = $("#returnNum").val();
		var sortColumn = $("#sortColumn").val();
//		    var id = $("#fId").val();
	    var orderNumber = $("#fOrderNumber").val();
	    var money = $("#fMoney").val();
	    var title = $("#fTitle").val();
	    var userName = $("#fUserName").val();
	    var phone = $("#fPhone").val();
	    var address = $("#fAddress").val();
	    var createTime = $("#fCreateTime").val();
	    var express_no = $("#fExpress_no").val();
	    var express_status = $("#fExpress_status").val();
	    var express_name = $("#fExpress_name").val();
	    var mailType = $("#fMailType").val();
	    var fp_style = $("#fFP_style").val();
//		    var remark1 = $("#fRemark1").val();
//		    var remark2 = $("#fRemark2").val();
//		    var remark3 = $("#fRemark3").val();
		var ywyId = $("#fYwyId").val();
	    $.getJSON("<c:url value='/hFp/exportExcelTag'/>",
        {
        	sortColumn:sortColumn,
    		orderNumber : orderNumber,
    		money : money,
    		title : title,
    		userName : userName,
    		phone : phone,
    		address : address,
    		createTime : createTime,
    		express_no : express_no,
    		express_status : express_status,
    		express_name : express_name,
    		mailType : mailType,
    		fp_style : fp_style,
			ywyId : ywyId,
    		queryType : 1,
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
