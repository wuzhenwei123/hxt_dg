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
                                            <label class="margin5">简称:</label>
	                                    	<input type="text" id="fName" class="span2 margin5" placeholder="name">
                                            <label class="margin5">有效期开始时间:</label>
	                                    	<input type="text" id="fStartTime" placeholder="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">有效期结束时间:</label>
	                                    	<input type="text" id="fEndTime" placeholder="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">是否为默认政策:</label>
                                            <select id="fIsDefault" class="span2 margin5"> 
						                        <option value="">全部</option> 
						                        <option value="1">是</option> 
						                        <option value="0">否</option> 
						                	</select> 
                                            <label class="margin5">状态:</label>
                                            <select id="fState" class="span2 margin5"> 
						                        <option value="">全部</option> 
						                        <option value="1">正常</option> 
						                        <option value="0">暂停</option> 
						                	</select> 
						                	<label class="margin5">金额:</label>
	                                    	<input type="text" id="fFee" class="span2 margin5">
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
	                                 	 	<perm:tag permPath="/hRegGuli/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/hRegGuli/removeAllHRegGuli" >
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
	var flag = true;
	function searchData(pageNo){
		var returnNum = $("#returnNum").val();
			var sortColumn = $("#sortColumn").val();
		    var id = $("#fId").val();
		    var name = $("#fName").val();
		    var startTime = $("#fStartTime").val();
		    var endTime = $("#fEndTime").val();
		    var fee = $("#fFee").val();
		    var isDefault = $("#fIsDefault").val();
		    var state = $("#fState").val();
		    var createTime = $("#fCreateTime").val();
		    var updateTime = $("#fUpdateTime").val();
		    var stopTime = $("#fStopTime").val();
		    var createId = $("#fCreateId").val();
		    var updateId = $("#fUpdateId").val();
		    var info = $("#fInfo").val();
		    var reg1 = /^\d+(\.\d{2})?$/;
			if(fee!=""&&!reg1.test(fee)){
				tipError("注册鼓励金金额只能输入数字并且保留两位小数");
				flag = false;
			}else{
				flag = true;
			}
			if(flag){
				$.getJSON("<c:url value='/hRegGuli/getHRegGuliList'/>",
				        {
				        	sortColumn:sortColumn,
				    		id : id,
				    		name : name,
				    		startTime : startTime,
				    		endTime : endTime,
				    		fee : fee,
				    		isDefault : isDefault,
				    		state : state,
				    		createTime : createTime,
				    		updateTime : updateTime,
				    		stopTime : stopTime,
				    		createId : createId,
				    		updateId : updateId,
				    		info : info,
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
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">简称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_startTime\" width='135' column='startTime' onselectstart='return false' scope=\"col\">有效期开始时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_endTime\" width='135' column='endTime' onselectstart='return false' scope=\"col\">有效期结束时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_fee\" column='fee' onselectstart='return false' scope=\"col\">鼓励金金额</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_isDefault\" column='isDefault' onselectstart='return false' scope=\"col\">是否为默认政策</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_state\" column='state' onselectstart='return false' scope=\"col\">状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_createTime\" width='135' column='createTime' onselectstart='return false' scope=\"col\">创建时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_updateTime\" width='135' column='updateTime' onselectstart='return false' scope=\"col\">最后一次编辑时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_stopTime\" width='135' column='stopTime' onselectstart='return false' scope=\"col\">最后一次暂停时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_createId\" column='createId' onselectstart='return false' scope=\"col\">创建操作员</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_updateId\" column='updateId' onselectstart='return false' scope=\"col\">暂停的操作员</th>";
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
	        tableStr += "<td ><input type=\"checkbox\" id='"+result.items[k].id+"' isDefault='"+result.items[k].isDefault+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" /></td>";
	        tableStr += "<td >" + (number + k + 1) + "</td>";
	        
		        tableStr += "<td>" + result.items[k].name + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].startTime) + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].endTime) + "</td>";
		        tableStr += "<td>" + (result.items[k].fee).toFixed(2) + "</td>";
		        tableStr += "<td>" + (result.items[k].isDefault==1?"是":"否") + "</td>";
		        tableStr += "<td>" + (result.items[k].state==1?"正常":"暂停") + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].createTime) + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].updateTime) + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].stopTime) + "</td>";
		        tableStr += "<td>" + result.items[k].createName + "</td>";
		        tableStr += "<td>" + result.items[k].updateName + "</td>";
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	tableStr += "<perm:tag permPath='/hRegGuli/updateHRegGuli' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hRegGuli/removeHRegGuli' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].id + ","+result.items[k].isDefault+");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
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
		show('<c:url value="/hRegGuli/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hRegGuli/toAdd"/>','');
	}
	// 删除所选
	function delAll(){
		var ids = '';
		var isDefault = 0;
		$("[name='checkName']").each(function(){
		    	var ck = $(this).attr("checked");
		    	if(ck == 'checked'){
		    		ids+=$(this).attr("id")+",";
		    		isDefault = isDefault + parseInt($(this).attr("isDefault"),10);
		    	}else{
		    	}
			})
		
		if(ids!=''){
			if(isDefault>0){
				tipError("删除失败，默认政策无法删除!");
			}else{
				bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要删除吗?","取消","确定", function(result) {
					if(result){
						$.post("<c:url value='/hRegGuli/removeAllHRegGuli'/>",
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
			}
		}else{
			tipError("请选择待删除条目!");
		}
	}
		// 单条删除
	function del(id,isDefault){
		if(isDefault=="1"){
			tipError("删除失败,默认鼓励金政策无法删除!");
		}else{
			if (id != ""){
				   bootbox.confirm("<i class=' fontello-icon-emo-happy' />你确定要删除吗?","取消","确定", function(result) {
					if(result){
						$.post("<c:url value='/hRegGuli/removeHRegGuli'/>",
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
    }
</script>
</body>
</html>
