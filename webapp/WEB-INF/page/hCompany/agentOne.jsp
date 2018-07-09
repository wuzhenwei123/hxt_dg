<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div id="stack2" class="modal hide fade" tabindex="-1" data-focus-on="input:first" >
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择客户经理</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fAgentName" class="span2 margin5" placeholder="客户经理名称">
	    <a href="javascript:searchData2('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo2">
	         <div class="widget-body">
	             <table id="exampleDTC2" class="table table-striped table-content table-condensed boo-table table-hover bg-blue-light">
		         </table>
		         <div class="widget-footer">
		             <div class="btn-toolbar pull-left">
		             </div>
		             <div class="pagination pagination-btn pull-right">
		             	<div id="kkpager2"></div>
		             </div>
		         </div>
	         </div>
		</div>
    </div>
    <div class="modal-footer"> 
	     <a type="button" id="agentOneClose" data-dismiss="modal" class="btn btn-boo">关闭</a>
    </div>
</div>
<input type="hidden" id="currPage2" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum2" value="10"><!-- 分页返回 -->
<input type="hidden" id="sortColumn2" value=""><!-- 排序字段 -->
<script type="text/javascript">
	function searchData2(pageNo){
		var returnNum = $("#returnNum2").val();
		var sortColumn = $("#sortColumn2").val();
	    var name = $("#fAgentName").val(); 
	    $.getJSON("<c:url value='/hAgent/getHAgentList'/>",
        {
        	sortColumn:sortColumn,
    		name : name,
    		status : 1,
    		pageNo: pageNo,
    		rowCount: returnNum, 
			_t: Math.random()
        },function(data){
           var result = data;
           if (result.code == 1) {
               setTableStr2(result, pageNo, returnNum);
           } else {
           	tipError("系统异常!");
           } 
   		});
	}
	function genTableHeader2(){
		var str = "<thead><tr>" ;
	    	str+= "<th scope=\"col\" class=\"check-col\"></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">客户经理名称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_ont_agent_ratio\" column='ont_agent_ratio' onselectstart='return false' scope=\"col\">联系人电话</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr2(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader2();
	    var number = (pageNo - 1) * returnNum;
        tableStr += "<tfoot></tfoot>";
        tableStr += "<tbody>";
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        tableStr += "<td style='float:right;'><input type=\"radio\" value='"+result.items[k].id+"' id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"agentOneRadio\" onclick=\"agentOneSure('"+result.items[k].openId+"',\'"+result.items[k].name+"\');\"/></td>";
	        tableStr += "<td>" + result.items[k].name + "</td>";
	        tableStr += "<td>" + result.items[k].mobile1 + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody>";
	    $("#exampleDTC2").html(tableStr);
	    $("#currPage2").val(pageNo);	
	    initTh();
	    genPageTag2(pageNo,result.totalResults,returnNum,'kkpager2');
	}
	function agentOneSure(id,name){
		if(modelType==1){
			$("#agentOneOpenIdQuery").val(id);
			$("#agentOneNameQuery").val(name);
			$("#aAgentOneNameQuery").val(name);
// 			$("#agentOneClose").click();
			$stack2.modal('hide');
		}else if(modelType==2){
			$("#agentOneOpenId").val(id);
			$("#agentOneName").val(name);
			$("#aAgentOneName").val(name);
// 			$("#agentOneClose").click();
			$stack2.modal('hide');
		}else if(modelType==3){
			$("#mAgentOneOpenId").val(id);
			$("#mAgentOneName").val(name);
			$("#mmAgentOneName").val(name);
// 			$("#agentOneClose").click();
			$stack2.modal('hide');
			cleanOn6();
// 			cleanOn7();
		}
	}
</script>
