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
    <meta charset="UTF-8">
	<title>${_title }</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="description" content="">
	<meta name="author" content="">

<!-- Le styles -->
<%@ include file="/WEB-INF/page/common/css.jsp" %>
   <style type="text/css">
	   	.ztree li a.curSelectedNode{
	   		background-color: #FFFFFF;
	   		border: 0px solid #FFFFFF;
	   		text-decoration: none;
	   	}
	   	.ztree li a{
	   		text-decoration: none;
	   	}
	   	.ztree li a:hover{
	   		text-decoration: none;
	   	}
	 	.ztree li ul .med{
	   		color:blue;
	   	}
   </style>
 </head>
 <body class="sidebar-left">
   	<input type="hidden" id="currPage" value="1"><!-- 当前页码 -->
	<input type="hidden" id="returnNum" value="10"><!-- 分页返回 -->
	<input type="hidden" id="sortColumn" value=" createTime ASC "><!-- 排序字段 -->
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
	                            <div class="widget-content in collapse" id="demoB" style="height: auto;">
	                                <div class="widget-body">
	                                	<ul id="treeDemo" class="ztree" style="overflow: auto;"></ul>
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
   <div id="temModal" class="modal hide fade" tabindex="-1" data-width="50%"></div>
   <script type="text/javascript">
   		var $modal = $('#temModal');
   	    var zTree ;
   	    var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			view: {
				addDiyDom: addDiyDom,
				fontCss : {color:"#797979"}
			}
		};
	    $(document).ready(function(){
	    	getColumnTreeNodes();
	    });
		// 设置操作方法
	    function addDiyDom(treeId, treeNode) {
	 	   
			var addHtml = "<perm:tag permPath='/columnMethod/toAddMed' ><span title='添加' class='fontello-icon-plus-circle' onclick='toAdd("+treeNode.id+");' /></perm:tag>";//添加按钮 
			
	 	   	var medHtml ='';
			if(!treeNode.meds)
				return;
	 	  	var meds = JSON.parse(treeNode.meds);//操作集合
	 	  	
	 	   	for(var i =0 ; i< meds.length;i++){
	 		   var med = meds[i];
 			   medHtml += "|<span class='med' >"+med.name+"</span><perm:tag permPath='/columnMethod/toUpdate' ><span class='fontello-icon-edit' title='编辑' onclick='toUpdate("+med.mid+")'/></perm:tag><perm:tag permPath='/columnMethod/toAddMed' ><span class='fontello-icon-minus-squared' title='删除' onclick='del("+med.mid+")' /></perm:tag>";
	 	   	}
	 	   if(treeNode.level == 2){
		 	  	medHtml += "|"+addHtml;
	 	   }
	 	   	
	 		var aObj = $("#" + treeNode.tId + "_a");
	 		if ($("#diyBtn_"+treeNode.id).length>0) return;
	 		var editStr = medHtml;
	 		aObj.append(editStr);
	 	};
	 	// 获取菜单树
	 	function getColumnTreeNodes(){
	 		$.getJSON("<c:url value='/columnMethod/getColumnTree'/>",
		        {
					_t: Math.random()
		        },function(data){
		            var result = data;
		            if (result.code == 1) {
		            	$.fn.zTree.init($("#treeDemo"), setting, result.items);
		    			zTree = $.fn.zTree.getZTreeObj("treeDemo");
		    			type = { "Y":"ps", "N":"ps"};
		    			zTree.setting.check.chkboxType = type;
		            } else {
		            	tipError(result.message);
		            } 
			    });
	 	}
		function searchData(pageNo){
			var returnNum = $("#returnNum").val();
			var sortColumn = $("#sortColumn").val();
		    var mid = $("#fMid").val();
		    var columnId = $("#fColumnId").val();
		    var methodName = $("#fMethodName").val();
		    var actionPath = $("#fActionPath").val();
		    var createTime = $("#fCreateTime").val();
		    $.getJSON("<c:url value='/columnMethod/getColumnMethodList'/>",
	        {
	        	sortColumn:sortColumn,
	    		mid : mid,
	    		columnId : columnId,
	    		methodName : methodName,
	    		actionPath : actionPath,
	    		createTime : createTime,
	    		pageNo: pageNo,
	    		rowCount: returnNum, 
				_t: Math.random()
	        },function(data){
	            var result = data;
	            if (result.code == 1) {
	                setTableStr(result, pageNo, returnNum);
	            } else {
	            	tipError(result.message);
	            } 
		    });
		}
		function genTableHeader(){
			var str = "<tr>" ;
		    	str+= "<th width='30'>#</th>";
		    	str+= "<th width='50'>序号</th>";
		    	str+= "<th onselectstart='return false' class='sortTh' id='th_mid' column='mid' >方法ID</th>";
		    	str+= "<th onselectstart='return false' class='sortTh' id='th_columnId' column='columnId' >菜单名称</th>";
		    	str+= "<th onselectstart='return false' class='sortTh' id='th_methodName' column='methodName' >方法名称</th>";
		    	str+= "<th onselectstart='return false' class='sortTh' id='th_actionPath' column='actionPath' >方法路径</th>";
		    	str+= "<th onselectstart='return false' class='sortTh' width='135' id='th_createTime' column='createTime' >创建日期</th>";
				str+="<th>操	作</th>";
				str+="</tr>";
			return str;
		}
		function setTableStr(result, pageNo, returnNum){
		 	var tableStr = "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"list_table\">";
		    tableStr += genTableHeader();
		    var number = (pageNo - 1) * returnNum;
		    for (var k=0; k<result.items.length; k++){      
		        tableStr += "<tr  class=\"tr\">";
		        tableStr += "<td class='td_center'><input type='checkbox' name='checkName' id='" + result.items[k].mid + "'/></td>";
		        tableStr += "<td class='td_center'>" + (number + k + 1) + "</td>";
		        tableStr += "<td class='td_center'>" + result.items[k].mid + "</td>";
		        tableStr += "<td class='td_center'>" + result.items[k].columnId + "</td>";
		        tableStr += "<td class='td_center'>" + result.items[k].methodName + "</td>";
		        tableStr += "<td class='td_center'>" + result.items[k].actionPath + "</td>";
		        tableStr += "<td class='td_center'>" + genDateTimeAll(result.items[k].createTime) + "</td>";
		        tableStr += "<td class=\"op_class td_center\"><a href='javascript:void(0);' onclick='toUpdate(" + result.items[k].mid + ");return false;'>编辑</a><a href='javascript:void(0);' onclick='del(" + result.items[k].mid + ");return false;'>删除</a></TD>";
		        tableStr += "</tr>";            
		    }
		    
		    $("#tableBody").html(tableStr);
		    $("#currPage").val(pageNo);	
		    
		    initTh();
		    initTr();
		    
		    genPageTag(pageNo,result.totalResults,returnNum);
		}

   		// 更新
		function toUpdate(id) {
			show('<c:url value="/columnMethod/toUpdate/'+id+'"/>','');
		}
   		
   		// 添加 方法
   		function toAdd(columnId){
   			show('<c:url value="/columnMethod/toAddMed/'+columnId+'"/>','');
   		}
   		
   		function add(iframe){
			var mid = getIframeVal(iframe,"aMid");
			var columnId = getIframeVal(iframe,"aColumnId");
			var methodName = getIframeVal(iframe,"aMethodName");
			var actionPath = getIframeVal(iframe,"aActionPath");
			var createTime = getIframeVal(iframe,"aCreateTime");
			var flag = true ;
		    if (flag){ 
		        $.post("<c:url value='/columnMethod/addColumnMethod'/>",
		        	{
		    		mid : mid,
		    		columnId : columnId,
		    		methodName : methodName,
		    		actionPath : actionPath,
		    		createTime : createTime,
					 _t:Math.random()},
		        	function(data){
			        	var result = eval('('+data+')'); 
			            if (result.code == '1') {
			            	getColumnTreeNodes();
			              	top.art.dialog.tips('保存成功');
			             } else {
			            	 top.art.dialog.alert(result.message);
			             }
		        });
		    }
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
   		    if(ids == ''){
   		 		top.art.dialog.tips('请选择');
   		    }else{
	   			top.art.dialog({
	   				title:'警告',
	   			    lock: true,
	   			    background: '#600', // 背景色
	   			    opacity: 0.87,	// 透明度
	   			    content: '你确定要删除吗?',
	   			    icon: 'error',
	   			    ok: function () {
	   			    	$.post("<c:url value='/columnMethod/removeAllColumnMethod'/>",
			        	{
							mids :ids,
							ranNum:Math.random()},
				        	function(data){
					        	var result = eval('('+data+')'); 
					            if (result.code == '1') {
					            	getColumnTreeNodes();
					            	tipOk("删除成功!");
					             } else {
					            	 tipError("删除失败!"+result.message);
					             }
				        });
	   			        return true;
	   			    },
	   			    cancel: true
	   			});
   		    }
   			
   		}
   		// 单条删除
   		function del(mid){
   		   if (mid != ""){
   			 	bootbox.confirm("你确定要删除吗?","取消","确定", function(result) {
    				if(result){
    					$.post("<c:url value='/columnMethod/removeColumnMethod'/>",
    		        	{
    						mid	:mid,
    						ranNum:Math.random()
    					},
    		        	function(data){
    			        	var result = eval('('+data+')'); 
    			            if (result.code == '1') {
    			            	getColumnTreeNodes();
    			              	tipOk("删除成功!");
    			             } else {
    			              	tipError("删除失败!"+result.message);
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
