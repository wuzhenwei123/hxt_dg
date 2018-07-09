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
                                            <label class="margin5">c_id:</label>
	                                    	<input type="text" id="fC_id" class="span2 margin5" placeholder="c_id">
                                            <label class="margin5">check_no:</label>
	                                    	<input type="text" id="fCheck_no" class="span2 margin5" placeholder="check_no">
                                            <label class="margin5">check_url:</label>
	                                    	<input type="text" id="fCheck_url" class="span2 margin5" placeholder="check_url">
                                            <label class="margin5">pay_url:</label>
	                                    	<input type="text" id="fPay_url" class="span2 margin5" placeholder="pay_url">
                                            <label class="margin5">status:</label>
	                                    	<input type="text" id="fStatus" class="span2 margin5" placeholder="status">
                                            <label class="margin5">create_date:</label>
	                                    	<input type="text" id="fCreate_date" class="span2 margin5" placeholder="create_date">
                                            <label class="margin5">create_time:</label>
	                                    	<input type="text" id="fCreate_time" placeholder="create_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">check_time:</label>
	                                    	<input type="text" id="fCheck_time" placeholder="check_time" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="span2 margin5" size="15" readonly="readonly">
                                            <label class="margin5">open_ip:</label>
	                                    	<input type="text" id="fOpen_ip" class="span2 margin5" placeholder="open_ip">
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
	                                 	 	<perm:tag permPath="/hPayurlCheck/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/hPayurlCheck/removeAllHPayurlCheck" >
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
		    var c_id = $("#fC_id").val();
		    var check_no = $("#fCheck_no").val();
		    var check_url = $("#fCheck_url").val();
		    var pay_url = $("#fPay_url").val();
		    var status = $("#fStatus").val();
		    var create_date = $("#fCreate_date").val();
		    var create_time = $("#fCreate_time").val();
		    var check_time = $("#fCheck_time").val();
		    var open_ip = $("#fOpen_ip").val();
		    $.getJSON("<c:url value='/hPayurlCheck/getHPayurlCheckList'/>",
	        {
	        	sortColumn:sortColumn,
	    		id : id,
	    		c_id : c_id,
	    		check_no : check_no,
	    		check_url : check_url,
	    		pay_url : pay_url,
	    		status : status,
	    		create_date : create_date,
	    		create_time : create_time,
	    		check_time : check_time,
	    		open_ip : open_ip,
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
	    	str+= "<th class=\"sortTh\" id=\"th_c_id\" column='c_id' onselectstart='return false' scope=\"col\">c_id<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_check_no\" column='check_no' onselectstart='return false' scope=\"col\">check_no<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_check_url\" column='check_url' onselectstart='return false' scope=\"col\">check_url<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_pay_url\" column='pay_url' onselectstart='return false' scope=\"col\">pay_url<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">status<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_create_date\" column='create_date' onselectstart='return false' scope=\"col\">create_date<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_create_time\" width='135' column='create_time' onselectstart='return false' scope=\"col\">create_time<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_check_time\" width='135' column='check_time' onselectstart='return false' scope=\"col\">check_time<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_open_ip\" column='open_ip' onselectstart='return false' scope=\"col\">open_ip<span class=\"pull-right aweso-icon-sort\"></span></th>";
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
		        tableStr += "<td>" + result.items[k].c_id + "</td>";
		        tableStr += "<td>" + result.items[k].check_no + "</td>";
		        tableStr += "<td>" + result.items[k].check_url + "</td>";
		        tableStr += "<td>" + result.items[k].pay_url + "</td>";
		        tableStr += "<td>" + result.items[k].status + "</td>";
		        tableStr += "<td>" + result.items[k].create_date + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].create_time) + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].check_time) + "</td>";
		        tableStr += "<td>" + result.items[k].open_ip + "</td>";
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	tableStr += "<perm:tag permPath='/hPayurlCheck/updateHPayurlCheck' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hPayurlCheck/removeHPayurlCheck' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
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
		show('<c:url value="/hPayurlCheck/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hPayurlCheck/toAdd"/>','');
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
					$.post("<c:url value='/hPayurlCheck/removeAllHPayurlCheck'/>",
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
				$.post("<c:url value='/hPayurlCheck/removeHPayurlCheck'/>",
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
