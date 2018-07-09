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
                                            <label class="margin5">缴费号:</label>
	                                    	<input type="text" id="fAmmeterNo" class="span2 margin5" >
                                            <label class="margin5">手机:</label>
	                                    	<input type="text" id="fPhone" class="span2 margin5">
                                            <label class="margin5">客户地址:</label>
	                                    	<input type="text" id="fAmmeter_address" class="span2 margin5">
                                            <label class="margin5">客户名称:</label>
	                                    	<input type="text" id="fAmmeter_name" class="span2 margin5">
	                                    	<label class="margin5">查询时间:</label>
	                                    	<input type="text" id="fCreateTime1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
	                                    	-
	                                    	<input type="text" id="fCreateTime2" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
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
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	<perm:tag permPath="/hAmmeterQueryLog/toAdd" >
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/hAmmeterQueryLog/removeAllHAmmeterQueryLog" >
	                                 	 	<a href="javascript:delAll();" class="btn btn-glyph"><i class="fontello-icon-minus-1"></i>删除</a> 
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

<script type="text/javascript">
	var $modal = $('#temModal');
	
	$(document).ready(function(){
    	searchData(1);
    });
	function searchData(pageNo){
		var returnNum = $("#returnNum").val();
			var sortColumn = $("#sortColumn").val();
		    var id = $("#fId").val();
		    var createTime = $("#fCreateTime").val();
		    var ammeterNo = $("#fAmmeterNo").val();
		    var phone = $("#fPhone").val();
		    var ip = $("#fIp").val();
		    var ammeter_address = $("#fAmmeter_address").val();
		    var ammeter_name = $("#fAmmeter_name").val();
		    var fee = $("#fFee").val();
		    var znFee = $("#fZnFee").val();
		    var totalFee = $("#fTotalFee").val();
		    var remark1 = $("#fRemark1").val();
		    var remark2 = $("#fRemark2").val();
		    var remark3 = $("#fRemark3").val();
		    
		    var createTime2 = $("#fCreateTime2").val();
		    var createTime1 = $("#fCreateTime1").val();
		    $.getJSON("<c:url value='/hAmmeterQueryLog/getHAmmeterQueryLogList'/>",
	        {
		    	createTime1:createTime1,
		    	createTime2:createTime2,
	        	sortColumn:sortColumn,
	    		id : id,
	    		createTime : createTime,
	    		ammeterNo : ammeterNo,
	    		phone : phone,
	    		ip : ip,
	    		ammeter_address : ammeter_address,
	    		ammeter_name : ammeter_name,
	    		fee : fee,
	    		znFee : znFee,
	    		totalFee : totalFee,
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
	    	str+= "<th class=\"sortTh\" id=\"th_ammeterNo\" column='ammeterNo' onselectstart='return false' scope=\"col\">缴费号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_phone\" column='phone' onselectstart='return false' scope=\"col\">手机</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_ip\" column='ip' onselectstart='return false' scope=\"col\">ip</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_ammeter_address\" column='ammeter_address' onselectstart='return false' scope=\"col\">客户地址</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_ammeter_name\" column='ammeter_name' onselectstart='return false' scope=\"col\">客户名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_fee\" column='fee' onselectstart='return false' scope=\"col\">账单金额</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_znFee\" column='znFee' onselectstart='return false' scope=\"col\">滞纳金</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_totalFee\" column='totalFee' onselectstart='return false' scope=\"col\">欠费总金额</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_createTime\" width='135' column='createTime' onselectstart='return false' scope=\"col\">查询时间</th>";
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
	        
		        tableStr += "<td>" + result.items[k].ammeterNo + "</td>";
		        tableStr += "<td>" + result.items[k].phone + "</td>";
		        tableStr += "<td>" + result.items[k].ip + "</td>";
		        tableStr += "<td>" + result.items[k].ammeter_address + "</td>";
		        tableStr += "<td>" + result.items[k].ammeter_name + "</td>";
		        tableStr += "<td>" + result.items[k].fee.replace("元","") + "</td>";
		        tableStr += "<td>" + result.items[k].znFee.replace("元","").replace("。","") + "</td>";
		        if(result.items[k].totalFee!=""){
		        	tableStr += "<td>" + result.items[k].totalFee + "</td>";
		        }else{
		        	tableStr += "<td>" + result.items[k].totalFee + "</td>";
		        }
		        tableStr += "<td>" + genDateTimeAll(result.items[k].createTime) + "</td>";
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	tableStr += "<perm:tag permPath='/hAmmeterQueryLog/updateHAmmeterQueryLog' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hAmmeterQueryLog/removeHAmmeterQueryLog' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
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
		show('<c:url value="/hAmmeterQueryLog/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hAmmeterQueryLog/toAdd"/>','');
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
					$.post("<c:url value='/hAmmeterQueryLog/removeAllHAmmeterQueryLog'/>",
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
				$.post("<c:url value='/hAmmeterQueryLog/removeHAmmeterQueryLog'/>",
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
	function exportExcel(){
		var sortColumn = $("#sortColumn").val();
	    var id = $("#fId").val();
	    var createTime = $("#fCreateTime").val();
	    var ammeterNo = $("#fAmmeterNo").val();
	    var phone = $("#fPhone").val();
	    var ip = $("#fIp").val();
	    var ammeter_address = $("#fAmmeter_address").val();
	    var ammeter_name = $("#fAmmeter_name").val();
	    var fee = $("#fFee").val();
	    var znFee = $("#fZnFee").val();
	    var totalFee = $("#fTotalFee").val();
	    var remark1 = $("#fRemark1").val();
	    var remark2 = $("#fRemark2").val();
	    var remark3 = $("#fRemark3").val();
	    
	    var createTime2 = $("#fCreateTime2").val();
	    var createTime1 = $("#fCreateTime1").val();
	    $.getJSON("<c:url value='/hAmmeterQueryLog/exportExcel'/>",
        {
	    	createTime1:createTime1,
	    	createTime2:createTime2,
        	sortColumn:sortColumn,
    		id : id,
    		createTime : createTime,
    		ammeterNo : ammeterNo,
    		phone : phone,
    		ip : ip,
    		ammeter_address : ammeter_address,
    		ammeter_name : ammeter_name,
    		fee : fee,
    		znFee : znFee,
    		totalFee : totalFee,
    		remark1 : remark1,
    		remark2 : remark2,
    		remark3 : remark3,
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
