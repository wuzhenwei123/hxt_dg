<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div id="stack5" class="modal hide fade" tabindex="-1" data-focus-on="input:first" data-width="80%">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择手机支付鼓励金</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fAgentName1" class="span2 margin5" placeholder="简称">
	    <select id="sType1" class="span2 margin5">
	    	<option value="">--全部类型--</option>
	    	<option value="1">按笔</option>
	    	<option value="2">按实际交易金额比例</option>
	    </select>
	    <a href="javascript:searchData5('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo5">
	         <div class="widget-body">
	             <table id="exampleDTC5" class="table table-striped table-content table-condensed boo-table table-hover bg-blue-light">
		         </table>
		         <div class="widget-footer">
		             <div class="btn-toolbar pull-left">
		             </div>
		             <div class="pagination pagination-btn pull-right">
		             	<div id="kkpager5"></div>
		             </div>
		         </div>
	         </div>
		</div>
    </div>
    <div class="modal-footer"> 
	     <a type="button" id="agentOneClose1" data-dismiss="modal" class="btn btn-boo">关闭</a>
	     <button type="submit" class="btn btn-green" onclick="savesj()">保存</button>
    </div>
</div>
<input type="hidden" id="currPage5" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum5" value="10"><!-- 分页返回 -->
<input type="hidden" id="sortColumn5" value=""><!-- 排序字段 -->
<script type="text/javascript">
	function savesj(){
		var glsjid = "";
		var glsjname = "";
		
		$("[name='ylOneRadio']").each(function(){
	    	var ck = $(this).attr("checked");
	    	if(ck == 'checked'){
	    		glsjid = $(this).attr("id");
	    		glsjname = $(this).val();
	    	}
		})
		
		agentTwoSure1(glsjid,glsjname);
	}
	function searchData5(pageNo){
		var returnNum = $("#returnNum5").val();
		var sortColumn = $("#sortColumn5").val();
	    var name = $("#fAgentName1").val(); 
	    var type = $("#sType1").val(); 
	    $.getJSON("<c:url value='/hPayGuli/getHPayGuliList'/>",
        {
        	sortColumn:sortColumn,
    		name : name,
    		state : 1,
    		style : 2,
    		opObject : 3,
    		type : type,
    		pageNo: pageNo,
    		rowCount: returnNum, 
			_t: Math.random()
        },function(data){
           var result = data;
           if (result.code == 1) {
               setTableStr5(result, pageNo, returnNum);
           } else {
           	tipError("系统异常!");
           } 
   		});
	}
	function genTableHeader5(){
		var str = "<thead><tr>" ;
	    	str+= "<th scope=\"col\" class=\"check-col\"></th>";
	    	str+= "<th class=\"sortTh\" id=\"th_trade_code\" column='trade_code' onselectstart='return false' scope=\"col\">交易类型</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">简称</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_startTime\" width='135' column='startTime' onselectstart='return false' scope=\"col\">有效期开始时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_endTime\" width='135' column='endTime' onselectstart='return false' scope=\"col\">有效期结束时间</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_type\" column='type' onselectstart='return false' scope=\"col\">类型</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_fee\" column='fee' onselectstart='return false' scope=\"col\">金额</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_rate\" column='rate' onselectstart='return false' scope=\"col\">分润比例</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_state\" column='state' onselectstart='return false' scope=\"col\">状态</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_is_default\" column='is_default' onselectstart='return false' scope=\"col\">是否是默认</th>";
			str+="</tr></thead>";
		return str;
	}
	function setTableStr5(result, pageNo, returnNum){
	 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader5();
	    var number = (pageNo - 1) * returnNum;
        tableStr += "<tfoot></tfoot>";
        tableStr += "<tbody>";
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        if(result.items[k].id==gl_sj_id){
		        tableStr += "<td style='float:right;'><input type=\"radio\" checked value='"+result.items[k].name+"' id='"+result.items[k].id+"' class=\"checkbox check-row\" name=\"ylOneRadio\"/></td>";
	        }else{
		        tableStr += "<td style='float:right;'><input type=\"radio\" value='"+result.items[k].name+"' id='"+result.items[k].id+"' class=\"checkbox check-row\" name=\"ylOneRadio\"/></td>";
	        }
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
	        tableStr += "<td>" + (result.items[k].is_default==1?"是":"否") + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody>";
	    $("#exampleDTC5").html(tableStr);
	    $("#currPage5").val(pageNo);	
	    initTh();
	    genPageTag5(pageNo,result.totalResults,returnNum,'kkpager5');
	}
	function agentTwoSure1(glsjid,glsjname){
		$.post("<c:url value='/manageAdminUser/setGlSJ'/>",
	        	{
					glsjid	:glsjid,
					id	:userId,
					glsjname	:glsjname,
					ranNum:Math.random()
				},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	tipOk("匹配成功!");
		              	$("#agentOneClose1").click();
		              	searchData(1);
		             } else {
		              	tipError("匹配失败!");
		             }
		        });
			
	}
</script>
