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
                                            <label class="margin5">NBKCODE:</label>
	                                    	<input type="text" id="fNBKCODE" class="span2 margin5" placeholder="NBKCODE">
                                            <label class="margin5">SABKCODE:</label>
	                                    	<input type="text" id="fSABKCODE" class="span2 margin5" placeholder="SABKCODE">
                                            <label class="margin5">BNKCITY:</label>
	                                    	<input type="text" id="fBNKCITY" class="span2 margin5" placeholder="BNKCITY">
                                            <label class="margin5">NBKNAME:</label>
	                                    	<input type="text" id="fNBKNAME" class="span2 margin5" placeholder="NBKNAME">
                                            <label class="margin5">NBKSNAME:</label>
	                                    	<input type="text" id="fNBKSNAME" class="span2 margin5" placeholder="NBKSNAME">
                                            <label class="margin5">NBKADDRESS:</label>
	                                    	<input type="text" id="fNBKADDRESS" class="span2 margin5" placeholder="NBKADDRESS">
                                            <label class="margin5">CNTTEL:</label>
	                                    	<input type="text" id="fCNTTEL" class="span2 margin5" placeholder="CNTTEL">
                                            <label class="margin5">CNTPER:</label>
	                                    	<input type="text" id="fCNTPER" class="span2 margin5" placeholder="CNTPER">
                                            <label class="margin5">POSTCODE:</label>
	                                    	<input type="text" id="fPOSTCODE" class="span2 margin5" placeholder="POSTCODE">
                                            <label class="margin5">NBKSTATE:</label>
	                                    	<input type="text" id="fNBKSTATE" class="span2 margin5" placeholder="NBKSTATE">
                                            <label class="margin5">BKEMAIL:</label>
	                                    	<input type="text" id="fBKEMAIL" class="span2 margin5" placeholder="BKEMAIL">
                                            <label class="margin5">CONTENT:</label>
	                                    	<input type="text" id="fCONTENT" class="span2 margin5" placeholder="CONTENT">
                                            <label class="margin5">PARTTYPE:</label>
	                                    	<input type="text" id="fPARTTYPE" class="span2 margin5" placeholder="PARTTYPE">
                                            <label class="margin5">BANKCATCODE:</label>
	                                    	<input type="text" id="fBANKCATCODE" class="span2 margin5" placeholder="BANKCATCODE">
                                            <label class="margin5">HIGHPART:</label>
	                                    	<input type="text" id="fHIGHPART" class="span2 margin5" placeholder="HIGHPART">
                                            <label class="margin5">BEARBANKCODE:</label>
	                                    	<input type="text" id="fBEARBANKCODE" class="span2 margin5" placeholder="BEARBANKCODE">
                                            <label class="margin5">CHARGEBANKCODE:</label>
	                                    	<input type="text" id="fCHARGEBANKCODE" class="span2 margin5" placeholder="CHARGEBANKCODE">
                                            <label class="margin5">NODECODE:</label>
	                                    	<input type="text" id="fNODECODE" class="span2 margin5" placeholder="NODECODE">
                                            <label class="margin5">SIGN:</label>
	                                    	<input type="text" id="fSIGN" class="span2 margin5" placeholder="SIGN">
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
	                                 	 	<perm:tag permPath="/openingBank/toAdd" >
	                                 	 	<a href="javascript:toAdd();" class="btn btn-glyph"><i class="fontello-icon-plus-1"></i>新增</a> 
	                                 	 	</perm:tag>
	                                 	 	<perm:tag permPath="/openingBank/removeAllOpeningBank" >
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
		    var NBKCODE = $("#fNBKCODE").val();
		    var SABKCODE = $("#fSABKCODE").val();
		    var BNKCITY = $("#fBNKCITY").val();
		    var NBKNAME = $("#fNBKNAME").val();
		    var NBKSNAME = $("#fNBKSNAME").val();
		    var NBKADDRESS = $("#fNBKADDRESS").val();
		    var CNTTEL = $("#fCNTTEL").val();
		    var CNTPER = $("#fCNTPER").val();
		    var POSTCODE = $("#fPOSTCODE").val();
		    var NBKSTATE = $("#fNBKSTATE").val();
		    var BKEMAIL = $("#fBKEMAIL").val();
		    var CONTENT = $("#fCONTENT").val();
		    var PARTTYPE = $("#fPARTTYPE").val();
		    var BANKCATCODE = $("#fBANKCATCODE").val();
		    var HIGHPART = $("#fHIGHPART").val();
		    var BEARBANKCODE = $("#fBEARBANKCODE").val();
		    var CHARGEBANKCODE = $("#fCHARGEBANKCODE").val();
		    var NODECODE = $("#fNODECODE").val();
		    var SIGN = $("#fSIGN").val();
		    $.getJSON("<c:url value='/openingBank/getOpeningBankList'/>",
	        {
	        	sortColumn:sortColumn,
	    		id : id,
	    		NBKCODE : NBKCODE,
	    		SABKCODE : SABKCODE,
	    		BNKCITY : BNKCITY,
	    		NBKNAME : NBKNAME,
	    		NBKSNAME : NBKSNAME,
	    		NBKADDRESS : NBKADDRESS,
	    		CNTTEL : CNTTEL,
	    		CNTPER : CNTPER,
	    		POSTCODE : POSTCODE,
	    		NBKSTATE : NBKSTATE,
	    		BKEMAIL : BKEMAIL,
	    		CONTENT : CONTENT,
	    		PARTTYPE : PARTTYPE,
	    		BANKCATCODE : BANKCATCODE,
	    		HIGHPART : HIGHPART,
	    		BEARBANKCODE : BEARBANKCODE,
	    		CHARGEBANKCODE : CHARGEBANKCODE,
	    		NODECODE : NODECODE,
	    		SIGN : SIGN,
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
	    	str+= "<th class=\"sortTh\" id=\"th_NBKCODE\" column='NBKCODE' onselectstart='return false' scope=\"col\">NBKCODE<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_SABKCODE\" column='SABKCODE' onselectstart='return false' scope=\"col\">SABKCODE<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_BNKCITY\" column='BNKCITY' onselectstart='return false' scope=\"col\">BNKCITY<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_NBKNAME\" column='NBKNAME' onselectstart='return false' scope=\"col\">NBKNAME<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_NBKSNAME\" column='NBKSNAME' onselectstart='return false' scope=\"col\">NBKSNAME<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_NBKADDRESS\" column='NBKADDRESS' onselectstart='return false' scope=\"col\">NBKADDRESS<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_CNTTEL\" column='CNTTEL' onselectstart='return false' scope=\"col\">CNTTEL<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_CNTPER\" column='CNTPER' onselectstart='return false' scope=\"col\">CNTPER<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_POSTCODE\" column='POSTCODE' onselectstart='return false' scope=\"col\">POSTCODE<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_NBKSTATE\" column='NBKSTATE' onselectstart='return false' scope=\"col\">NBKSTATE<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_BKEMAIL\" column='BKEMAIL' onselectstart='return false' scope=\"col\">BKEMAIL<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_CONTENT\" column='CONTENT' onselectstart='return false' scope=\"col\">CONTENT<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_PARTTYPE\" column='PARTTYPE' onselectstart='return false' scope=\"col\">PARTTYPE<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_BANKCATCODE\" column='BANKCATCODE' onselectstart='return false' scope=\"col\">BANKCATCODE<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_HIGHPART\" column='HIGHPART' onselectstart='return false' scope=\"col\">HIGHPART<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_BEARBANKCODE\" column='BEARBANKCODE' onselectstart='return false' scope=\"col\">BEARBANKCODE<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_CHARGEBANKCODE\" column='CHARGEBANKCODE' onselectstart='return false' scope=\"col\">CHARGEBANKCODE<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_NODECODE\" column='NODECODE' onselectstart='return false' scope=\"col\">NODECODE<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_SIGN\" column='SIGN' onselectstart='return false' scope=\"col\">SIGN<span class=\"pull-right aweso-icon-sort\"></span></th>";
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
		        tableStr += "<td>" + result.items[k].NBKCODE + "</td>";
		        tableStr += "<td>" + result.items[k].SABKCODE + "</td>";
		        tableStr += "<td>" + result.items[k].BNKCITY + "</td>";
		        tableStr += "<td>" + result.items[k].NBKNAME + "</td>";
		        tableStr += "<td>" + result.items[k].NBKSNAME + "</td>";
		        tableStr += "<td>" + result.items[k].NBKADDRESS + "</td>";
		        tableStr += "<td>" + result.items[k].CNTTEL + "</td>";
		        tableStr += "<td>" + result.items[k].CNTPER + "</td>";
		        tableStr += "<td>" + result.items[k].POSTCODE + "</td>";
		        tableStr += "<td>" + result.items[k].NBKSTATE + "</td>";
		        tableStr += "<td>" + result.items[k].BKEMAIL + "</td>";
		        tableStr += "<td>" + result.items[k].CONTENT + "</td>";
		        tableStr += "<td>" + result.items[k].PARTTYPE + "</td>";
		        tableStr += "<td>" + result.items[k].BANKCATCODE + "</td>";
		        tableStr += "<td>" + result.items[k].HIGHPART + "</td>";
		        tableStr += "<td>" + result.items[k].BEARBANKCODE + "</td>";
		        tableStr += "<td>" + result.items[k].CHARGEBANKCODE + "</td>";
		        tableStr += "<td>" + result.items[k].NODECODE + "</td>";
		        tableStr += "<td>" + result.items[k].SIGN + "</td>";
	        tableStr += "<td>";
	        tableStr += "<div class=\"btn-group\">";
        	tableStr += "<perm:tag permPath='/openingBank/updateOpeningBank' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='toUpdate(" + result.items[k].id + ");return false;'><i class='fontello-icon-edit' />编辑</a></perm:tag>";
        	tableStr += "<perm:tag permPath='/openingBank/removeOpeningBank' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='del(" + result.items[k].id + ");return false;'><i class='fontello-icon-minus-1' />删除</a></perm:tag>";
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
		show('<c:url value="/openingBank/toUpdate/'+id+'"/>','');
	}
		
	// 添加
	function toAdd(){
		show('<c:url value="/openingBank/toAdd"/>','');
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
					$.post("<c:url value='/openingBank/removeAllOpeningBank'/>",
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
				$.post("<c:url value='/openingBank/removeOpeningBank'/>",
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
