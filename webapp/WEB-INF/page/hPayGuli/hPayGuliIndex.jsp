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
                                            <label class="margin5">交易类型:</label>
	                                    	<input type="text" id="fTrade_code" class="span2 margin5" placeholder="trade_code">
                                            <label class="margin5">简称:</label>
	                                    	<input type="text" id="fName" class="span2 margin5" placeholder="name">
                                            <label class="margin5">类型:</label>
	                                    	<select id="fType" class="span2 margin5"> 
						                        <option value="">全部</option> 
						                        <option value="1">按笔</option> 
						                        <option value="2">按实际交易金额比例</option> 
						                	</select> 
                                            <label class="margin5">状态:</label>
                                            <select id="fState" class="span2 margin5"> 
						                        <option value="">全部</option> 
						                        <option value="1">正常</option> 
						                        <option value="0">暂停</option> 
						                	</select> 
                                            <label class="margin5">是否是默认:</label>
                                            <select id="fIs_default" class="span2 margin5"> 
						                        <option value="">全部</option> 
						                        <option value="1">是</option> 
						                        <option value="0">否</option> 
						                	</select>
						                	
								             <label  class="margin5">使用对象</label>
							                 <select id="fOpObject" class="span2 margin5"> 
							                 	<option value="">全部</option> 
							                    <option value="1">客户经理</option> 
							                    <option value="2">代理</option> 
							                    <option value="3">服务人员</option> 
							            	</select> 
							            	<label class="margin5">有效期开始时间:</label>
	                                    	<input type="text" id="fStartTime" placeholder="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">有效期结束时间:</label>
	                                    	<input type="text" id="fEndTime" placeholder="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
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
	                                 	 	<perm:tag permPath="/hPayGuli/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/hPayGuli/removeAllHPayGuli" >
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
		    var trade_code = $("#fTrade_code").val();
		    var name = $("#fName").val();
		    var style = 1;
		    var startTime = $("#fStartTime").val();
		    var endTime = $("#fEndTime").val();
		    var type = $("#fType").val();
		    var fee = $("#fFee").val();
		    var rate = $("#fRate").val();
		    var state = $("#fState").val();
		    var is_default = $("#fIs_default").val();
		    var createTime = $("#fCreateTime").val();
		    var createId = $("#fCreateId").val();
		    var updateTime = $("#fUpdateTime").val();
		    var updateId = $("#fUpdateId").val();
		    var stopTime = $("#fStopTime").val();
		    var info = $("#fInfo").val();
		    var opObject = $("#fOpObject").val();
		    $.getJSON("<c:url value='/hPayGuli/getHPayGuliList'/>",
	        {
	        	sortColumn:sortColumn,
	    		id : id,
	    		trade_code : trade_code,
	    		name : name,
	    		style : style,
	    		startTime : startTime,
	    		endTime : endTime,
	    		type : type,
	    		fee : fee,
	    		opObject : opObject,
	    		rate : rate,
	    		state : state,
	    		is_default : is_default,
	    		createTime : createTime,
	    		createId : createId,
	    		updateTime : updateTime,
	    		updateId : updateId,
	    		stopTime : stopTime,
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
	    	str+= "<th class=\"sortTh\" id=\"th_trade_code\" column='trade_code' onselectstart='return false' scope=\"col\">交易类型</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">简称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_startTime\" width='135' column='startTime' onselectstart='return false' scope=\"col\">有效期开始时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_endTime\" width='135' column='endTime' onselectstart='return false' scope=\"col\">有效期结束时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_type\" column='type' onselectstart='return false' scope=\"col\">类型</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_fee\" column='fee' onselectstart='return false' scope=\"col\">金额</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_rate\" column='rate' onselectstart='return false' scope=\"col\">分润比例</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_state\" column='state' onselectstart='return false' scope=\"col\">状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_state\" column='state' onselectstart='return false' scope=\"col\">鼓励金对象</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_is_default\" column='is_default' onselectstart='return false' scope=\"col\">是否是默认</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_createTime\" width='135' column='createTime' onselectstart='return false' scope=\"col\">创建时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_createId\" column='createId' onselectstart='return false' scope=\"col\">创建人</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_updateTime\" width='135' column='updateTime' onselectstart='return false' scope=\"col\">最有一次修改时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_updateId\" column='updateId' onselectstart='return false' scope=\"col\">最后一次修改人</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_stopTime\" width='135' column='stopTime' onselectstart='return false' scope=\"col\">最后一次暂停时间</th>";
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
	        
		        tableStr += "<td>" + result.items[k].trade_code + "</td>";
		        tableStr += "<td>" + result.items[k].name + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].startTime) + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].endTime) + "</td>";
		        tableStr += "<td>" + (result.items[k].type==1?"按笔":"按实际交易金额比例") + "</td>";
		        if(result.items[k].fee!=""){
		        	tableStr += "<td>" + (result.items[k].fee).toFixed(2) + "</td>";
		        }else{
		        	tableStr += "<td></td>";
		        }
		        if(result.items[k].rate!=""){
		        	tableStr += "<td>" + (result.items[k].rate).toFixed(5) + "</td>";
		        }else{
		        	tableStr += "<td></td>";
		        }
		        tableStr += "<td>" + (result.items[k].state==1?"正常":"暂停") + "</td>";
		        if(result.items[k].opObject==1){
		        	tableStr += "<td>客户经理</td>";
		        }else if(result.items[k].opObject==2){
		        	tableStr += "<td>代理</td>";
		        }else if(result.items[k].opObject==3){
		        	tableStr += "<td>缴费指导人员</td>";
		        }else{
		        	tableStr += "<td></td>";
		        }
		        tableStr += "<td>" + (result.items[k].is_default==1?"是":"否") + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].createTime) + "</td>";
		        tableStr += "<td>" + result.items[k].createName + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].updateTime) + "</td>";
		        tableStr += "<td>" + result.items[k].updateName + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].stopTime) + "</td>";
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	tableStr += "<perm:tag permPath='/hPayGuli/updateHPayGuli' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hPayGuli/removeHPayGuli' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
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
		show('<c:url value="/hPayGuli/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hPayGuli/toAdd"/>','');
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
					$.post("<c:url value='/hPayGuli/removeAllHPayGuli'/>",
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
				$.post("<c:url value='/hPayGuli/removeHPayGuli'/>",
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
