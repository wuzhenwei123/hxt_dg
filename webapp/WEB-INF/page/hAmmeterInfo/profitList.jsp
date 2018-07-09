<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div id="stack1" class="modal hide fade" tabindex="-1" data-focus-on="input:first" >
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择分润比例</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fProfitName" class="span2 margin5" placeholder="分润比例名称">
	    <a href="javascript:searchData1('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo2">
	         <div class="widget-body">
	             <table id="exampleDTC1" class="table table-striped table-content table-condensed boo-table table-hover bg-blue-light">
		         </table>
		         <div class="widget-footer">
		             <div class="btn-toolbar pull-left">
		             </div>
		             <div class="pagination pagination-btn pull-right">
		             	<div id="kkpager1"></div>
		             </div>
		         </div>
	         </div>
		</div>
    </div>
    <div class="modal-footer"> 
	     <a type="button" id="profitClose" data-dismiss="modal" class="btn btn-boo">关闭</a>
    </div>
</div>
<input type="hidden" id="currPage1" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum1" value="10"><!-- 分页返回 -->
<input type="hidden" id="sortColumn1" value=""><!-- 排序字段 -->
<script type="text/javascript">
	function searchData1(pageNo){
		var returnNum = $("#returnNum1").val();
		var sortColumn = $("#sortColumn1").val();
	    var name = $("#fProfitName").val(); 
	    $.getJSON("<c:url value='/hProfitRatio/getHProfitRatioList'/>",
        {
        	sortColumn:sortColumn,
    		name : name,
    		state : 1,
    		pageNo: pageNo,
    		rowCount: returnNum, 
			_t: Math.random()
        },function(data){
           var result = data;
           if (result.code == 1) {
               setTableStr1(result, pageNo, returnNum);
           } else {
           	tipError("系统异常!");
           } 
   		});
	}
	function genTableHeader1(){
		var str = "<thead><tr>" ;
	    	str+= "<th scope=\"col\" class=\"check-col\"></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">分润比例名称</th>";
// 	    	str+= "<th class=\"sortTh\" id=\"th_manager_ratio\" column='manager_ratio' onselectstart='return false' scope=\"col\">manager_ratio<span class=\"pull-right aweso-icon-sort\"></span></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_ont_agent_ratio\" column='ont_agent_ratio' onselectstart='return false' scope=\"col\">一级代理比例</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_two_agent_ratio\" column='two_agent_ratio' onselectstart='return false' scope=\"col\">二级代理比例</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_personal_ratio\" column='personal_ratio' onselectstart='return false' scope=\"col\">服务人员比例</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr1(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader1();
	    var number = (pageNo - 1) * returnNum;
        tableStr += "<tfoot></tfoot>";
        tableStr += "<tbody>";
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        tableStr += "<td style='float:right;'><input type=\"radio\" value='"+result.items[k].id+"' id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"profitRadio\" onclick=\"profitSure("+result.items[k].id+",\'"+result.items[k].name+"\');\"/></td>";
	        
		        tableStr += "<td>" + result.items[k].name + "</td>";
// 		        tableStr += "<td>" + result.items[k].manager_ratio + "</td>";
		        tableStr += "<td>" + result.items[k].ont_agent_ratio + "</td>";
		        tableStr += "<td>" + result.items[k].two_agent_ratio + "</td>";
		        tableStr += "<td>" + result.items[k].personal_ratio + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody>";
	    $("#exampleDTC1").html(tableStr);
	    $("#currPage1").val(pageNo);	
	    initTh();
	    genPageTag1(pageNo,result.totalResults,returnNum,'kkpager1');
	}
	function profitSure(id,name){
		$("#aProfit_id").val(id);
		$("#profitName").val(name);
		$("#profitClose").click();
	}
</script>
