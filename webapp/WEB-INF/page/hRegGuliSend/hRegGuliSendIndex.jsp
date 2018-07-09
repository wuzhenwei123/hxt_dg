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
	<input type="hidden" id="sortColumn" value=""><!-- 排序字段 -->
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
                                            <label class="margin5">id:</label>
	                                    	<input type="text" id="fId" class="span2 margin5" placeholder="id">
                                            <label class="margin5">sendTime:</label>
	                                    	<input type="text" id="fSendTime" placeholder="sendTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">createTime:</label>
	                                    	<input type="text" id="fCreateTime" placeholder="createTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">companyId:</label>
	                                    	<input type="text" id="fCompanyId" class="span2 margin5" placeholder="companyId">
                                            <label class="margin5">companyName:</label>
	                                    	<input type="text" id="fCompanyName" class="span2 margin5" placeholder="companyName">
                                            <label class="margin5">ammeter:</label>
	                                    	<input type="text" id="fAmmeter" class="span2 margin5" placeholder="ammeter">
                                            <label class="margin5">agentTwoId:</label>
	                                    	<input type="text" id="fAgentTwoId" class="span2 margin5" placeholder="agentTwoId">
                                            <label class="margin5">agentTwoName:</label>
	                                    	<input type="text" id="fAgentTwoName" class="span2 margin5" placeholder="agentTwoName">
                                            <label class="margin5">agentId:</label>
	                                    	<input type="text" id="fAgentId" class="span2 margin5" placeholder="agentId">
                                            <label class="margin5">agentName:</label>
	                                    	<input type="text" id="fAgentName" class="span2 margin5" placeholder="agentName">
                                            <label class="margin5">guliId:</label>
	                                    	<input type="text" id="fGuliId" class="span2 margin5" placeholder="guliId">
                                            <label class="margin5">fee:</label>
	                                    	<input type="text" id="fFee" class="span2 margin5" placeholder="fee">
                                            <label class="margin5">state:</label>
                                            <select id="fState" class="span2 margin5"> 
						                        <option value="">全部</option> 
						                        <option value="1">正常</option> 
						                        <option value="0">禁用</option> 
						                	</select> 
                                            <label class="margin5">info:</label>
	                                    	<input type="text" id="fInfo" class="span2 margin5" placeholder="info">
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
	                                 	 	<perm:tag permPath="/hRegGuliSend/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/hRegGuliSend/removeAllHRegGuliSend" >
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
		    var sendTime = $("#fSendTime").val();
		    var createTime = $("#fCreateTime").val();
		    var companyId = $("#fCompanyId").val();
		    var companyName = $("#fCompanyName").val();
		    var ammeter = $("#fAmmeter").val();
		    var agentTwoId = $("#fAgentTwoId").val();
		    var agentTwoName = $("#fAgentTwoName").val();
		    var agentId = $("#fAgentId").val();
		    var agentName = $("#fAgentName").val();
		    var guliId = $("#fGuliId").val();
		    var fee = $("#fFee").val();
		    var state = $("#fState").val();
		    var info = $("#fInfo").val();
		    $.getJSON("<c:url value='/hRegGuliSend/getHRegGuliSendList'/>",
	        {
	        	sortColumn:sortColumn,
	    		id : id,
	    		sendTime : sendTime,
	    		createTime : createTime,
	    		companyId : companyId,
	    		companyName : companyName,
	    		ammeter : ammeter,
	    		agentTwoId : agentTwoId,
	    		agentTwoName : agentTwoName,
	    		agentId : agentId,
	    		agentName : agentName,
	    		guliId : guliId,
	    		fee : fee,
	    		state : state,
	    		info : info,
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
	    	str+= "<th class=\"sortTh\" id=\"th_id\" column='id' onselectstart='return false' scope=\"col\">id<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_sendTime\" width='135' column='sendTime' onselectstart='return false' scope=\"col\">sendTime<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_createTime\" width='135' column='createTime' onselectstart='return false' scope=\"col\">createTime<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_companyId\" column='companyId' onselectstart='return false' scope=\"col\">companyId<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_companyName\" column='companyName' onselectstart='return false' scope=\"col\">companyName<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_ammeter\" column='ammeter' onselectstart='return false' scope=\"col\">ammeter<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_agentTwoId\" column='agentTwoId' onselectstart='return false' scope=\"col\">agentTwoId<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_agentTwoName\" column='agentTwoName' onselectstart='return false' scope=\"col\">agentTwoName<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_agentId\" column='agentId' onselectstart='return false' scope=\"col\">agentId<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_agentName\" column='agentName' onselectstart='return false' scope=\"col\">agentName<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_guliId\" column='guliId' onselectstart='return false' scope=\"col\">guliId<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_fee\" column='fee' onselectstart='return false' scope=\"col\">fee<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_state\" column='state' onselectstart='return false' scope=\"col\">state<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_info\" column='info' onselectstart='return false' scope=\"col\">info<span class=\"pull-right aweso-icon-sort\"></span></th>";
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
	        
		        tableStr += "<td>" + result.items[k].id + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].sendTime) + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].createTime) + "</td>";
		        tableStr += "<td>" + result.items[k].companyId + "</td>";
		        tableStr += "<td>" + result.items[k].companyName + "</td>";
		        tableStr += "<td>" + result.items[k].ammeter + "</td>";
		        tableStr += "<td>" + result.items[k].agentTwoId + "</td>";
		        tableStr += "<td>" + result.items[k].agentTwoName + "</td>";
		        tableStr += "<td>" + result.items[k].agentId + "</td>";
		        tableStr += "<td>" + result.items[k].agentName + "</td>";
		        tableStr += "<td>" + result.items[k].guliId + "</td>";
		        tableStr += "<td>" + result.items[k].fee + "</td>";
		        tableStr += "<td>" + (result.items[k].state==1?"正常":"禁用") + "</td>";
		        tableStr += "<td>" + result.items[k].info + "</td>";
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	tableStr += "<perm:tag permPath='/hRegGuliSend/updateHRegGuliSend' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hRegGuliSend/removeHRegGuliSend' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
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
		show('<c:url value="/hRegGuliSend/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hRegGuliSend/toAdd"/>','');
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
					$.post("<c:url value='/hRegGuliSend/removeAllHRegGuliSend'/>",
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
				$.post("<c:url value='/hRegGuliSend/removeHRegGuliSend'/>",
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
