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
<!--                                             <label class="margin5">operator_name:</label> -->
<!-- 	                                    	<input type="text" id="fOperator_name" class="span2 margin5" placeholder="operator_name"> -->
<!--                                             <label class="margin5">operator_id:</label> -->
<!-- 	                                    	<input type="text" id="fOperator_id" class="span2 margin5" placeholder="operator_id"> -->
<!--                                             <label class="margin5">operator_level:</label> -->
<!-- 	                                    	<input type="text" id="fOperator_level" class="span2 margin5" placeholder="operator_level"> -->
<!--                                             <label class="margin5">window_name:</label> -->
<!-- 	                                    	<input type="text" id="fWindow_name" class="span2 margin5" placeholder="window_name"> -->
                                            <label class="margin5">名称:</label>
	                                    	<input type="text" id="fName" class="span2 margin5" placeholder="名称">
<!--                                             <label class="margin5">province_name:</label> -->
<!-- 	                                    	<input type="text" id="fProvince_name" class="span2 margin5" placeholder="province_name"> -->
<!--                                             <label class="margin5">province_code:</label> -->
<!-- 	                                    	<input type="text" id="fProvince_code" class="span2 margin5" placeholder="province_code"> -->
<!--                                             <label class="margin5">city_code:</label> -->
<!-- 	                                    	<input type="text" id="fCity_code" class="span2 margin5" placeholder="city_code"> -->
<!--                                             <label class="margin5">city_name:</label> -->
<!-- 	                                    	<input type="text" id="fCity_name" class="span2 margin5" placeholder="city_name"> -->
<!--                                             <label class="margin5">area_code:</label> -->
<!-- 	                                    	<input type="text" id="fArea_code" class="span2 margin5" placeholder="area_code"> -->
<!--                                             <label class="margin5">area_name:</label> -->
<!-- 	                                    	<input type="text" id="fArea_name" class="span2 margin5" placeholder="area_name"> -->
                                            <label class="margin5">状态:</label>
                                            <select id="fState" class="span2 margin5"> 
						                        <option value="">全部</option> 
						                        <option value="1">正常</option> 
						                        <option value="0">暂停</option> 
						                	</select> 
<!--                                             <label class="margin5">createTime:</label> -->
<!-- 	                                    	<input type="text" id="fCreateTime" placeholder="createTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly"> -->
<!--                                             <label class="margin5">full_invoice:</label> -->
<!-- 	                                    	<input type="text" id="fFull_invoice" class="span2 margin5" placeholder="full_invoice"> -->
<!--                                             <label class="margin5">add_invoice:</label> -->
<!-- 	                                    	<input type="text" id="fAdd_invoice" class="span2 margin5" placeholder="add_invoice"> -->
<!--                                             <label class="margin5">adminId:</label> -->
<!-- 	                                    	<input type="text" id="fAdminId" class="span2 margin5" placeholder="adminId"> -->
<!--                                             <label class="margin5">lastTime:</label> -->
<!-- 	                                    	<input type="text" id="fLastTime" placeholder="lastTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly"> -->
<!--                                             <label class="margin5">lastAdminId:</label> -->
<!-- 	                                    	<input type="text" id="fLastAdminId" class="span2 margin5" placeholder="lastAdminId"> -->
<!--                                             <label class="margin5">mobile:</label> -->
<!-- 	                                    	<input type="text" id="fMobile" class="span2 margin5" placeholder="mobile"> -->
<!--                                             <label class="margin5">phone:</label> -->
<!-- 	                                    	<input type="text" id="fPhone" class="span2 margin5" placeholder="phone"> -->
<!--                                             <label class="margin5">phone_post:</label> -->
<!-- 	                                    	<input type="text" id="fPhone_post" class="span2 margin5" placeholder="phone_post"> -->
<!--                                             <label class="margin5">work_start:</label> -->
<!-- 	                                    	<input type="text" id="fWork_start" placeholder="work_start" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly"> -->
<!--                                             <label class="margin5">work_end:</label> -->
<!-- 	                                    	<input type="text" id="fWork_end" placeholder="work_end" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly"> -->
<!--                                             <label class="margin5">address:</label> -->
<!-- 	                                    	<input type="text" id="fAddress" class="span2 margin5" placeholder="address"> -->
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
	                                 	 	<perm:tag permPath="/hOperatorDot/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
<%-- 	                                 	 	<perm:tag permPath="/hOperatorDot/removeAllHOperatorDot" > --%>
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
		    var operator_name = $("#fOperator_name").val();
		    var operator_id = $("#fOperator_id").val();
		    var operator_level = $("#fOperator_level").val();
		    var window_name = $("#fWindow_name").val();
		    var name = $("#fName").val();
		    var province_name = $("#fProvince_name").val();
		    var province_code = $("#fProvince_code").val();
		    var city_code = $("#fCity_code").val();
		    var city_name = $("#fCity_name").val();
		    var area_code = $("#fArea_code").val();
		    var area_name = $("#fArea_name").val();
		    var state = $("#fState").val();
		    var createTime = $("#fCreateTime").val();
		    var full_invoice = $("#fFull_invoice").val();
		    var add_invoice = $("#fAdd_invoice").val();
		    var adminId = $("#fAdminId").val();
		    var lastTime = $("#fLastTime").val();
		    var lastAdminId = $("#fLastAdminId").val();
		    var mobile = $("#fMobile").val();
		    var phone = $("#fPhone").val();
		    var phone_post = $("#fPhone_post").val();
		    var work_start = $("#fWork_start").val();
		    var work_end = $("#fWork_end").val();
		    var address = $("#fAddress").val();
		    $.getJSON("<c:url value='/hOperatorDot/getHOperatorDotList'/>",
	        {
	        	sortColumn:sortColumn,
	    		id : id,
	    		operator_name : operator_name,
	    		operator_id : operator_id,
	    		operator_level : operator_level,
	    		window_name : window_name,
	    		name : name,
	    		province_name : province_name,
	    		province_code : province_code,
	    		city_code : city_code,
	    		city_name : city_name,
	    		area_code : area_code,
	    		area_name : area_name,
	    		state : state,
	    		createTime : createTime,
	    		full_invoice : full_invoice,
	    		add_invoice : add_invoice,
	    		adminId : adminId,
	    		lastTime : lastTime,
	    		lastAdminId : lastAdminId,
	    		mobile : mobile,
	    		phone : phone,
	    		phone_post : phone_post,
	    		work_start : work_start,
	    		work_end : work_end,
	    		address : address,
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
	    	str+= "<th>运营商名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_operator_level\" column='operator_level' onselectstart='return false' scope=\"col\">营业厅等级<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th>窗口名称</th>";
	    	str+= "<th>名称</th>";
	    	str+= "<th>省</th>";
	    	str+= "<th>市</th>";
	    	str+= "<th>区</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_state\" column='state' onselectstart='return false' scope=\"col\">状态<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_createTime\" width='135' column='createTime' onselectstart='return false' scope=\"col\">登记时间<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_full_invoice\" column='full_invoice' onselectstart='return false' scope=\"col\">全区发票<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_add_invoice\" column='add_invoice' onselectstart='return false' scope=\"col\">归属地发票<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th>登记人</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_lastTime\" width='135' column='lastTime' onselectstart='return false' scope=\"col\">最后变更日期<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th>最后变更人</th>";
	    	str+= "<th>联系手机号</th>";
	    	str+= "<th>区号</th>";
	    	str+= "<th>固定电话</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_work_start\" width='135' column='work_start' onselectstart='return false' scope=\"col\">营业开始时间<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_work_end\" width='135' column='work_end' onselectstart='return false' scope=\"col\">营业结束时间<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th>详细地址</th>";
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
	        
		        tableStr += "<td>" + result.items[k].operator_name + "</td>";
		        tableStr += "<td>" + result.items[k].operator_level + "</td>";
		        tableStr += "<td>" + result.items[k].window_name + "</td>";
		        tableStr += "<td>" + result.items[k].name + "</td>";
		        tableStr += "<td>" + result.items[k].province_name + "</td>";
		        tableStr += "<td>" + result.items[k].city_name + "</td>";
		        tableStr += "<td>" + result.items[k].area_name + "</td>";
		        tableStr += "<td>" + (result.items[k].state==1?"正常":"暂停") + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].createTime) + "</td>";
		        tableStr += "<td>" + result.items[k].full_invoice + "</td>";
		        tableStr += "<td>" + result.items[k].add_invoice + "</td>";
		        tableStr += "<td>" + result.items[k].adminName + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].lastTime) + "</td>";
		        tableStr += "<td>" + result.items[k].lastAdminName + "</td>";
		        tableStr += "<td>" + result.items[k].mobile + "</td>";
		        tableStr += "<td>" + result.items[k].phone_post + "</td>";
		        tableStr += "<td>" + result.items[k].phone + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].work_start) + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].work_end) + "</td>";
		        tableStr += "<td>" + result.items[k].address + "</td>";
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	tableStr += "<perm:tag permPath='/hOperatorDot/updateHOperatorDot' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hOperatorDot/removeHOperatorDot' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
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
		show('<c:url value="/hOperatorDot/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hOperatorDot/toAdd"/>','');
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
					$.post("<c:url value='/hOperatorDot/removeAllHOperatorDot'/>",
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
				$.post("<c:url value='/hOperatorDot/removeHOperatorDot'/>",
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
