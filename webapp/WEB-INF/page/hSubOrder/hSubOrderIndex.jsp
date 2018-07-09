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
                                            <label class="margin5">o_sub_id:</label>
	                                    	<input type="text" id="fO_sub_id" class="span2 margin5" placeholder="o_sub_id">
                                            <label class="margin5">o_id:</label>
	                                    	<input type="text" id="fO_id" class="span2 margin5" placeholder="o_id">
                                            <label class="margin5">c_id:</label>
	                                    	<input type="text" id="fC_id" class="span2 margin5" placeholder="c_id">
                                            <label class="margin5">sub_id:</label>
	                                    	<input type="text" id="fSub_id" class="span2 margin5" placeholder="sub_id">
                                            <label class="margin5">amount:</label>
	                                    	<input type="text" id="fAmount" class="span2 margin5" placeholder="amount">
                                            <label class="margin5">electric:</label>
	                                    	<input type="text" id="fElectric" class="span2 margin5" placeholder="electric">
                                            <label class="margin5">pay_status:</label>
	                                    	<input type="text" id="fPay_status" class="span2 margin5" placeholder="pay_status">
                                            <label class="margin5">pay_time:</label>
	                                    	<input type="text" id="fPay_time" placeholder="pay_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">tick_off_status:</label>
	                                    	<input type="text" id="fTick_off_status" class="span2 margin5" placeholder="tick_off_status">
                                            <label class="margin5">tick_off_time:</label>
	                                    	<input type="text" id="fTick_off_time" placeholder="tick_off_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">create_time:</label>
	                                    	<input type="text" id="fCreate_time" placeholder="create_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
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
	                                 	 	<perm:tag permPath="/hSubOrder/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/hSubOrder/removeAllHSubOrder" >
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
		    var o_sub_id = $("#fO_sub_id").val();
		    var o_id = $("#fO_id").val();
		    var c_id = $("#fC_id").val();
		    var sub_id = $("#fSub_id").val();
		    var amount = $("#fAmount").val();
		    var electric = $("#fElectric").val();
		    var pay_status = $("#fPay_status").val();
		    var pay_time = $("#fPay_time").val();
		    var tick_off_status = $("#fTick_off_status").val();
		    var tick_off_time = $("#fTick_off_time").val();
		    var create_time = $("#fCreate_time").val();
		    $.getJSON("<c:url value='/hSubOrder/getHSubOrderList'/>",
	        {
	        	sortColumn:sortColumn,
	    		o_sub_id : o_sub_id,
	    		o_id : o_id,
	    		c_id : c_id,
	    		sub_id : sub_id,
	    		amount : amount,
	    		electric : electric,
	    		pay_status : pay_status,
	    		pay_time : pay_time,
	    		tick_off_status : tick_off_status,
	    		tick_off_time : tick_off_time,
	    		create_time : create_time,
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
	    	str+= "<th class=\"sortTh\" id=\"th_o_sub_id\" column='o_sub_id' onselectstart='return false' scope=\"col\">o_sub_id<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_o_id\" column='o_id' onselectstart='return false' scope=\"col\">o_id<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_c_id\" column='c_id' onselectstart='return false' scope=\"col\">c_id<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_sub_id\" column='sub_id' onselectstart='return false' scope=\"col\">sub_id<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_amount\" column='amount' onselectstart='return false' scope=\"col\">amount<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_electric\" column='electric' onselectstart='return false' scope=\"col\">electric<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_pay_status\" column='pay_status' onselectstart='return false' scope=\"col\">pay_status<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_pay_time\" width='135' column='pay_time' onselectstart='return false' scope=\"col\">pay_time<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_tick_off_status\" column='tick_off_status' onselectstart='return false' scope=\"col\">tick_off_status<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_tick_off_time\" width='135' column='tick_off_time' onselectstart='return false' scope=\"col\">tick_off_time<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_create_time\" width='135' column='create_time' onselectstart='return false' scope=\"col\">create_time<span class=\"pull-right aweso-icon-sort\"></span></th>";
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
	        tableStr += "<td ><input type=\"checkbox\" id='"+result.items[k].o_sub_id+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" /></td>";
	        tableStr += "<td >" + (number + k + 1) + "</td>";
	        
		        tableStr += "<td>" + result.items[k].o_sub_id + "</td>";
		        tableStr += "<td>" + result.items[k].o_id + "</td>";
		        tableStr += "<td>" + result.items[k].c_id + "</td>";
		        tableStr += "<td>" + result.items[k].sub_id + "</td>";
		        tableStr += "<td>" + result.items[k].amount + "</td>";
		        tableStr += "<td>" + result.items[k].electric + "</td>";
		        tableStr += "<td>" + result.items[k].pay_status + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].pay_time) + "</td>";
		        tableStr += "<td>" + result.items[k].tick_off_status + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].tick_off_time) + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].create_time) + "</td>";
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	tableStr += "<perm:tag permPath='/hSubOrder/updateHSubOrder' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].o_sub_id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hSubOrder/removeHSubOrder' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].o_sub_id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
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
		show('<c:url value="/hSubOrder/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hSubOrder/toAdd"/>','');
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
					$.post("<c:url value='/hSubOrder/removeAllHSubOrder'/>",
		        	{
						o_sub_ids :ids,
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
	function del(o_sub_id){
	   if (o_sub_id != ""){
		   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要删除吗?","取消","确定", function(result) {
			if(result){
				$.post("<c:url value='/hSubOrder/removeHSubOrder'/>",
	        	{
					o_sub_id	:o_sub_id,
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
