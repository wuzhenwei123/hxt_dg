<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div id="stack4" class="modal hide fade" tabindex="-1" data-focus-on="input:first" data-width="80%">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择银联B2B交易鼓励金</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fAgentName" class="span2 margin5" placeholder="简称">
	    <a href="javascript:searchData4('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo4">
	         <div class="widget-body">
	             <table id="exampleDTC4" class="table table-striped table-content table-condensed boo-table table-hover bg-blue-light">
		         </table>
		         <div class="widget-footer">
		             <div class="btn-toolbar pull-left">
		             </div>
		             <div class="pagination pagination-btn pull-right">
		             	<div id="kkpager4"></div>
		             </div>
		         </div>
	         </div>
		</div>
    </div>
    <div class="modal-footer"> 
	     <a type="button" id="agentOneClose13" data-dismiss="modal" class="btn btn-boo">关闭</a>
	     <button type="submit" class="btn btn-green" onclick="savegl()">保存</button>
    </div>
</div>
<input type="hidden" id="currPage4" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum4" value="10"><!-- 分页返回 -->
<input type="hidden" id="sortColumn4" value=""><!-- 排序字段 -->
<script type="text/javascript">

	function savegl(){
		var id = "";
		var glylname = "";
		
		$("[name='glName']").each(function(){
	    	var ck = $(this).attr("checked");
	    	if(ck == 'checked'){
	    		id = $(this).attr("id");
	    		glylname = $(this).val();
	    	}
		})
		
		agentRegSure(id,glylname);
	}
	
	function searchData4(pageNo){
		var returnNum = $("#returnNum4").val();
		var sortColumn = $("#sortColumn4").val();
	    var name = $("#fAgentName").val(); 
	    var type = $("#sType").val(); 
	    $.getJSON("<c:url value='/hRegGuli/getHRegGuliList'/>",
        {
        	sortColumn:sortColumn,
    		name : name,
    		state : 1,
    		style : 1,
    		opObject : 2,
    		type : type,
    		pageNo: pageNo,
    		rowCount: returnNum, 
			_t: Math.random()
        },function(data){
           var result = data;
           if (result.code == 1) {
               setTableStr4(result, pageNo, returnNum);
           } else {
           	tipError("系统异常!");
           } 
   		});
	}
	function genTableHeader4(){
		var str = "<thead><tr>" ;
	    	str+= "<th scope=\"col\" class=\"check-col\"></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_trade_code\" column='trade_code' onselectstart='return false' scope=\"col\">交易类型</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">简称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_startTime\" width='135' column='startTime' onselectstart='return false' scope=\"col\">有效期开始时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_endTime\" width='135' column='endTime' onselectstart='return false' scope=\"col\">有效期结束时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_fee\" column='fee' onselectstart='return false' scope=\"col\">金额</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_state\" column='state' onselectstart='return false' scope=\"col\">状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_is_default\" column='is_default' onselectstart='return false' scope=\"col\">是否是默认</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr4(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader4();
	    var number = (pageNo - 1) * returnNum;
        tableStr += "<tfoot></tfoot>";
        tableStr += "<tbody>";
        for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        if(reg_gl_id==result.items[k].id){
		        tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' value='"+result.items[k].name+"' checked class=\"checkbox check-row\" name=\"glName\" /></td>";
	        }else{
	        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].id+"' value='"+result.items[k].name+"' class=\"checkbox check-row\" name=\"glName\" /></td>";
	        }
	        tableStr += "<td >" + (number + k + 1) + "</td>";
	        
		        tableStr += "<td>" + result.items[k].name + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].startTime) + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].endTime) + "</td>";
		        
		        tableStr += "<td>" + (result.items[k].fee).toFixed(2) + "</td>";
		        tableStr += "<td>" + (result.items[k].state==1?"正常":"暂停") + "</td>";
		        tableStr += "<td>" + (result.items[k].isDefault==1?"是":"否") + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody>";
	    $("#exampleDTC4").html(tableStr);
	    $("#currPage4").val(pageNo);	
	    initTh();
	    genPageTag4(pageNo,result.totalResults,returnNum,'kkpager4');
	}
	function agentRegSure(id,glylname){
		$.post("<c:url value='/hAgentTwo/setRegGl'/>",
	        	{
					glylid	:id,
					id	:agentId,
					glylname	:glylname,
					ranNum:Math.random()
				},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	tipOk("匹配成功!");
		              	$("#agentOneClose13").click();
		              	searchData(1);
// 		              	$stack2.modal('hide');
		             } else {
		              	tipError("匹配失败!");
		             }
		        });
	}
</script>
