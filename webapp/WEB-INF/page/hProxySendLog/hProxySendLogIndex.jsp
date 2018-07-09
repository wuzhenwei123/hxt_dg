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
                                            <label class="margin5">操作类型:</label>
                                            <select id="fStyle" class="span2 margin5">
                                            	<option value="">--请选择--</option>
                                            	<option value="1">新增</option>
                                            	<option value="2">修改</option>
                                            	<option value="3">撤销</option>
                                            	<option value="4">代付</option>
                                            	<option value="5">冲正</option>
                                            	<option value="6">代收</option>
                                            </select>
                                            <label class="margin5">合同号:</label>
	                                    	<input type="text" id="fContractNumber" class="span2 margin5" placeholder="contractNumber">
                                            <label class="margin5">用户编号:</label>
	                                    	<input type="text" id="fUserNumber" class="span2 margin5" placeholder="userNumber">
                                            <label class="margin5">开户行行号:</label>
	                                    	<input type="text" id="fBank_number" class="span2 margin5" placeholder="bank_number">
                                            <label class="margin5">支付行行号:</label>
	                                    	<input type="text" id="fPayBankNumber" class="span2 margin5" placeholder="payBankNumber">
                                            <label class="margin5">发送类型:</label>
                                            <select id="fSendStyle" class="span2 margin5">
                                            	<option value="">--请选择--</option>
                                            	<option value="1">发送</option>
                                            	<option value="2">接收</option>
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
	                                 	 	<perm:tag permPath="/hProxySendLog/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/hProxySendLog/removeAllHProxySendLog" >
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

<script type="text/javascript">
	var $modal = $('#temModal');
	
	$(document).ready(function(){
    	searchData(1);
    });
	function searchData(pageNo){
		var returnNum = $("#returnNum").val();
			var sortColumn = $("#sortColumn").val();
		    var id = $("#fId").val();
		    var style = $("#fStyle").val();
		    var contractNumber = $("#fContractNumber").val();
		    var userNumber = $("#fUserNumber").val();
		    var content = $("#fContent").val();
		    var bank_number = $("#fBank_number").val();
		    var payBankNumber = $("#fPayBankNumber").val();
		    var remark1 = $("#fRemark1").val();
		    var remark2 = $("#fRemark2").val();
		    var remark3 = $("#fRemark3").val();
		    var sendStyle = $("#fSendStyle").val();
		    $.getJSON("<c:url value='/hProxySendLog/getHProxySendLogList'/>",
	        {
	        	sortColumn:sortColumn,
	    		id : id,
	    		style : style,
	    		contractNumber : contractNumber,
	    		userNumber : userNumber,
	    		content : content,
	    		bank_number : bank_number,
	    		payBankNumber : payBankNumber,
	    		remark1 : remark1,
	    		remark2 : remark2,
	    		remark3 : remark3,
	    		sendStyle : sendStyle,
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
	    	str+= "<th class=\"sortTh\" id=\"th_style\" column='style' onselectstart='return false' scope=\"col\">操作类型</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_contractNumber\" column='contractNumber' onselectstart='return false' scope=\"col\">合同号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_userNumber\" column='userNumber' onselectstart='return false' scope=\"col\">用户编号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_bank_number\" column='bank_number' onselectstart='return false' scope=\"col\">开户行行号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_payBankNumber\" column='payBankNumber' onselectstart='return false' scope=\"col\">开户账号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_remark1\" column='remark1' onselectstart='return false' scope=\"col\">用户名</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_remark2\" column='remark2' onselectstart='return false' scope=\"col\">手机号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_sendStyle\" column='sendStyle' onselectstart='return false' scope=\"col\">发送类型</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_sendStyle\" column='sendStyle' onselectstart='return false' scope=\"col\">时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_remark3\" column='remark3' onselectstart='return false' scope=\"col\">交易流水号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_content\" column='content' onselectstart='return false' scope=\"col\">报文</th>";
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
	        
		        if(result.items[k].style=="1"){
		        	 tableStr += "<td>新增</td>";
		        }else if(result.items[k].style=="2"){
		        	tableStr += "<td>修改</td>";
		        }else if(result.items[k].style=="3"){
		        	tableStr += "<td>撤销</td>";
		        }else if(result.items[k].style=="4"){
		        	tableStr += "<td>代付</td>";
		        }else if(result.items[k].style=="5"){
		        	tableStr += "<td>冲正</td>";
		        }else if(result.items[k].style=="6"){
		        	tableStr += "<td>代收</td>";
		        }else{
		        	tableStr += "<td></td>";
		        }
		        tableStr += "<td>" + result.items[k].contractNumber + "</td>";
		        tableStr += "<td>" + result.items[k].userNumber + "</td>";
		        tableStr += "<td>" + result.items[k].bank_number + "</td>";
		        tableStr += "<td>" + result.items[k].payBankNumber + "</td>";
		        tableStr += "<td>" + result.items[k].remark1 + "</td>";
		        tableStr += "<td>" + result.items[k].remark2 + "</td>";
		        if(result.items[k].sendStyle=="1"){
		        	tableStr += "<td>发送</td>";
		        }else{
		        	tableStr += "<td>接收</td>";
		        }
		        tableStr += "<td>" + genDateTimeAll(result.items[k].createTime) + "</td>";
		        tableStr += "<td>" + result.items[k].remark3 + "</td>";
		        tableStr += "<td>" + result.items[k].content + "</td>";
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	tableStr += "<perm:tag permPath='/hProxySendLog/updateHProxySendLog' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hProxySendLog/removeHProxySendLog' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
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
		show('<c:url value="/hProxySendLog/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hProxySendLog/toAdd"/>','');
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
					$.post("<c:url value='/hProxySendLog/removeAllHProxySendLog'/>",
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
				$.post("<c:url value='/hProxySendLog/removeHProxySendLog'/>",
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
</script>
</body>
</html>
