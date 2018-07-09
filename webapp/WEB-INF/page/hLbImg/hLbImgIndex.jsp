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
	                    <div class="row-fluid">
	                    	<div class="span12 widget widget-simple bg-gray-light">
	                             <div class="widget-header">
	                                 <div class="btn-toolbar"> 
	                                 	 <div class="btn-group"> 
	                                 	 	<perm:tag permPath="/hLbImg/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/hLbImg/removeAllHLbImg" >
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
		    var img_name = $("#fImg_name").val();
		    var img_path = $("#fImg_path").val();
		    var link_address = $("#fLink_address").val();
		    var add_user_id = $("#fAdd_user_id").val();
		    var add_time = $("#fAdd_time").val();
		    var status = $("#fStatus").val();
		    var sort_id = $("#fSort_id").val();
		    $.getJSON("<c:url value='/hLbImg/getHLbImgList'/>",
	        {
	        	sortColumn:sortColumn,
	    		id : id,
	    		img_name : img_name,
	    		img_path : img_path,
	    		link_address : link_address,
	    		add_user_id : add_user_id,
	    		add_time : add_time,
	    		status : status,
	    		sort_id : sort_id,
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
	    	str+= "<th class=\"sortTh\" id=\"th_img_name\" column='img_name' onselectstart='return false' scope=\"col\">图片名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_img_path\" column='img_path' onselectstart='return false' scope=\"col\">图片路径</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_add_user_id\" column='add_user_id' onselectstart='return false' scope=\"col\">添加人</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_add_time\" width='135' column='add_time' onselectstart='return false' scope=\"col\">添加时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_sort_id\" column='sort_id' onselectstart='return false' scope=\"col\">排序</th>";
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
	        
		        tableStr += "<td>" + result.items[k].img_name + "</td>";
		        tableStr += "<td>" + result.items[k].img_path + "</td>";
		        tableStr += "<td>" + result.items[k].add_user_name + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].add_time) + "</td>";
		        if(result.items[k].status==1){
		        	 tableStr += "<td>正常</td>";
		        }else{
		        	 tableStr += "<td>冻结</td>";
		        }
		        tableStr += "<td>";
		        tableStr += "<div class=\"btn-group\">";
		        var count1 = 0;
	        	var count2 = 0;
	        	var count3 = 0;
	        	var len = result.items.length-1;
	        	if(k>0 && k<len){
	        		count1 = result.items[k-1].id;
	        		count2 = result.items[k].id;
	        		count3 = result.items[k+1].id;
		        	tableStr += "<a class=\"btn btn-mini\" onclick=\"move("+ count1 +", " + count2 + ")\"><i class=\"aweso-icon-caret-up\" title=\"上移动\"></i></a>";
		        	tableStr += "<a class=\"btn btn-mini\" onclick=\"move("+ count3 +", " + count2 + ")\"><i class=\"aweso-icon-caret-down\" title=\"下移动\"></i></a>";
	        	}else if(k==0 && k<len){
	        		count2 = result.items[k].id;
	        		count3 = result.items[k+1].id;
		        	tableStr += "<a class=\"btn btn-mini\" onclick=\"move("+ count3 +", " + count2 + ")\"><i class=\"aweso-icon-caret-down\" title=\"下移动\"></i></a>";
	        	}else if(k>0 && k==len){
	        		count1 = result.items[k-1].id;
	        		count2 = result.items[k].id;
		        	tableStr += "<a class=\"btn btn-mini\" onclick=\"move("+ count1 +", " + count2 + ")\"><i class=\"aweso-icon-caret-up\" title=\"上移动\"></i></a>";
	        	}else{
	        		tableStr += "<a class=\"btn btn-mini\" href=\"javascript:void(0);\"><i class=\"aweso-icon-caret-up\" title=\"上移动\"></i></a>";
		        	tableStr += "<a class=\"btn btn-mini\" href=\"javascript:void(0);\"><i class=\"aweso-icon-caret-down\" title=\"下移动\"></i></a>";
	        	}
	        	tableStr += "</div>";
	        	tableStr += "</td>";
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	tableStr += "<perm:tag permPath='/hLbImg/updateHLbImg' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/hLbImg/removeHLbImg' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
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
		show('<c:url value="/hLbImg/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/hLbImg/toAdd"/>','');
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
					$.post("<c:url value='/hLbImg/removeAllHLbImg'/>",
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
				$.post("<c:url value='/hLbImg/removeHLbImg'/>",
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
	//上下移动
	function move(id1, id){
		$.post("<c:url value='/hLbImg/moveLb'/>",
       	{
			id1:id1,
			id:id,
			ranNum:Math.random()
		},
       	function(data){
        	var result = eval('('+data+')'); 
            if (result.code == '1') {
              	var pageNo = $("#currPage").val();           
              	searchData(pageNo);
             } else {
            	 tipError("操作失败!");
             }
        });
	}
</script>
</body>
</html>
