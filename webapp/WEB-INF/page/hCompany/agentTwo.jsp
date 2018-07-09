<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div id="stack3" class="modal hide fade" tabindex="-1" data-focus-on="input:first" >
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择代理</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fAgentTwoName" class="span2 margin5" placeholder="代理名称">
	    <a href="javascript:searchData3('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo3">
	         <div class="widget-body">
	             <table id="exampleDTC3" class="table table-striped table-content table-condensed boo-table table-hover bg-blue-light">
		         </table>
		         <div class="widget-footer">
		             <div class="btn-toolbar pull-left">
		             </div>
		             <div class="pagination pagination-btn pull-right">
		             	<div id="kkpager3"></div>
		             </div>
		         </div>
	         </div>
		</div>
    </div>
    <div class="modal-footer"> 
	     <a type="button" id="agentTwoClose" data-dismiss="modal" class="btn btn-boo">关闭</a>
    </div>
</div>
<input type="hidden" id="currPage3" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum3" value="10"><!-- 分页返回 -->
<input type="hidden" id="sortColumn3" value=""><!-- 排序字段 -->
<script type="text/javascript">
	function searchData3(pageNo){
		var returnNum = $("#returnNum3").val();
		var sortColumn = $("#sortColumn3").val();
	    var name = $("#fAgentTwoName").val(); 
	    var oneAgentOpenId = "";
	    if(modelType==1){
	    	oneAgentOpenId = $("#agentOneOpenIdQuery").val();
	    }else if(modelType==2){
	    	oneAgentOpenId = $("#agentOneOpenId").val();
	    }else if(modelType==3){
	    	oneAgentOpenId = $("#mAgentOneOpenId").val();
	    }
	    if(oneAgentOpenId!=""){
	    	$.getJSON("<c:url value='/hAgentTwo/getHAgentTwoList'/>",
	    	        {
	    	        	sortColumn:sortColumn,
	    	    		name : name,
	    	    		roleId : '${twoAgentRoleId}',
	    	    		create_openId : oneAgentOpenId,
	    	    		oneAgentOpenId : oneAgentOpenId,
	    	    		status : 1,
	    	    		pageNo: pageNo,
	    	    		rowCount: returnNum, 
	    				_t: Math.random()
	    	        },function(data){
	    	           var result = data;
	    	           if (result.code == 1) {
	    	               setTableStr3(result, pageNo, returnNum);
	    	           } else {
	    	           	tipError("系统异常!");
	    	           } 
	    	   		});
	    }else{
	    	tipError("系统异常!");
	    }
	    
	}
	function genTableHeader3(){
		var str = "<thead><tr>" ;
	    	str+= "<th scope=\"col\" class=\"check-col\"></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">代理名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_personal_ratio\" column='personal_ratio' onselectstart='return false' scope=\"col\">联系人电话</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr3(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader3();
	    var number = (pageNo - 1) * returnNum;
        tableStr += "<tfoot></tfoot>";
        tableStr += "<tbody>";
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        tableStr += "<td style='float:right;'><input type=\"radio\" value='"+result.items[k].id+"' id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"agentTwoRadio\" onclick=\"agentTwoSure(\'"+result.items[k].openId+"\',\'"+result.items[k].name+"\');\"/></td>";
	        tableStr += "<td>" + result.items[k].name + "</td>";
	        tableStr += "<td>" + result.items[k].mobile1 + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody>";
	    $("#exampleDTC3").html(tableStr);
	    $("#currPage3").val(pageNo);	
	    initTh();
	    genPageTag3(pageNo,result.totalResults,returnNum,'kkpager3');
	}
	function agentTwoSure(id,name){
		if(modelType==1){
			$("#agentTwoOpenIdQuery").val(id);
			$("#agentTwoNameQuery").val(name);
			$("#aAgentTwoNameQuery").val(name);
// 			$("#agentTwoCloseQuery").click();
			$stack3.modal('hide');
		}else if(modelType==2){
			$("#agentTwoOpenId").val(id);
			$("#agentTwoName").val(name);
			$("#aAgentTwoName").val(name);
// 			$("#agentTwoClose").click();
			$stack3.modal('hide');
		}else if(modelType==3){
			$("#mAgentTwoOpenId").val(id);
			$("#mAgentTwoName").val(name);
			$("#mAgentTwoName").val(name);
// 			$("#agentTwoClose").click();
			$stack3.modal('hide');
		}
	}
	function searchData5(pageNo){
		var returnNum = $("#returnNum3").val();
		var sortColumn = $("#sortColumn3").val();
	    var name = $("#fAgentTwoName").val();
	    $.getJSON("<c:url value='/hAgentTwo/getHAgentTwoList'/>",
        {
        	sortColumn:sortColumn,
    		name : name,
    		roleId : '${twoAgentRoleId}',
    		create_openId : $("#agentOneOpenId").val(),
    		status : 1,
    		pageNo: pageNo,
    		rowCount: returnNum, 
			_t: Math.random()
        },function(data){
           var result = data;
           if (result.code == 1) {
               setTableStr3(result, pageNo, returnNum);
           } else {
           	tipError("系统异常!");
           } 
   		});
	}
	function searchData6(pageNo){
		var returnNum = $("#returnNum3").val();
		var sortColumn = $("#sortColumn3").val();
	    var name = $("#fAgentTwoName").val(); 
	    var oneAgentOpenId = "";
	    if(modelType==1){
	    	oneAgentOpenId = $("#agentOneOpenIdQuery").val();
	    }else if(modelType==2){
	    	oneAgentOpenId = $("#agentOneOpenId").val();
	    }else if(modelType==3){
	    	oneAgentOpenId = $("#mAgentOneOpenId").val();
	    }
	    if(oneAgentOpenId!=""){
	    	$.getJSON("<c:url value='/hAgentTwo/getHAgentTwoList'/>",
	    	        {
	    	        	sortColumn:sortColumn,
	    	    		name : name,
	    	    		roleId : '${twoAgentRoleId}',
	    	    		create_openId : oneAgentOpenId,
	    	    		oneAgentOpenId : oneAgentOpenId,
	    	    		status : 1,
	    	    		pageNo: pageNo,
	    	    		rowCount: returnNum, 
	    				_t: Math.random()
	    	        },function(data){
	    	           var result = data;
	    	           if (result.code == 1) {
	    	               setTableStr3(result, pageNo, returnNum);
	    	           } else {
	    	           	tipError("系统异常!");
	    	           } 
	    	   		});
	    }else{
	    	tipError("系统异常!");
	    }
	}
</script>
