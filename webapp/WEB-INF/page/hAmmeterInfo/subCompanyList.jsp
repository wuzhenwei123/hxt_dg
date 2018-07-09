<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div id="stack4" class="modal hide fade" tabindex="-1" data-focus-on="input:first" data-width="50%">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择分组</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fSubComany" class="span2 margin5" placeholder="代理名称">
	    <a href="javascript:searchData4('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo3">
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
	     <a type="button" id="subCompanyClose" data-dismiss="modal" class="btn btn-boo">关闭</a>
    </div>
</div>
  <input type="hidden" id="currPage4" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum4" value="10"><!-- 分页返回 -->
<input type="hidden" id="sortColumn4" value=" a.create_time desc "><!-- 排序字段 -->
<script type="text/javascript">
	function searchData4(pageNo){
		var returnNum = $("#returnNum4").val();
		var sortColumn = $("#sortColumn4").val();
	    var sub_name = $("#fSubComany").val();
	    var c_id = $("#aC_id").val();
	    $.getJSON("<c:url value='/hSubCompany/getHSubCompanyList'/>",
        {
    		sub_name : sub_name,
    		pageNo: pageNo,
    		sub_name: sub_name,
    		c_id : c_id,
    		rowCount: returnNum, 
    		sortColumn : sortColumn,
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
    	str+= "<th class=\"sortTh\" id=\"th_sub_name\" column='sub_name' onselectstart='return false' scope=\"col\">单位名称</th>";
    	str+= "<th class=\"sortTh\" id=\"th_invoice_title\" column='invoice_title' onselectstart='return false' scope=\"col\">分组位名称</th>";
    	str+= "<th class=\"sortTh\" id=\"th_invoice_title\" column='invoice_title' onselectstart='return false' scope=\"col\">发票抬头</th>";
    	str+= "<th class=\"sortTh\" id=\"th_consignee\" column='consignee' onselectstart='return false' scope=\"col\">收件人名称</th>";
    	str+= "<th class=\"sortTh\" id=\"th_consignee_phone\" column='consignee_phone' onselectstart='return false' scope=\"col\">收件人手机</th>";
//     	str+= "<th class=\"sortTh\" id=\"th_consignee_tel1\" column='consignee_tel1' onselectstart='return false' scope=\"col\">收件人座机</th>";
//     	str+= "<th class=\"sortTh\" id=\"th_consignee_tel2\" column='consignee_tel2' onselectstart='return false' scope=\"col\">座机分机号</th>";
//     	str+= "<th class=\"sortTh\" id=\"th_consignee_address\" column='consignee_address' onselectstart='return false' scope=\"col\">收件人地址</th>";
//     	str+= "<th class=\"sortTh\" id=\"th_create_time\" width='135' column='create_time' onselectstart='return false' scope=\"col\">添加时间</th>";
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
	        tableStr += "<td style='float:right;'><input type=\"radio\" value='"+result.items[k].s_id+"' id='"+result.items[k].s_id+"' class=\"checkbox check-row\" value=\"0\" name=\"profitRadio\" onclick=\"subCompanySure("+result.items[k].s_id+",\'"+result.items[k].sub_name+"\');\"/></td>";
	        
		        tableStr += "<td>" + result.items[k].c_name + "</td>";
		        tableStr += "<td>" + result.items[k].sub_name + "</td>";
		        tableStr += "<td>" + result.items[k].invoice_title + "</td>";
		        tableStr += "<td>" + result.items[k].consignee + "</td>";
		        tableStr += "<td>" + result.items[k].consignee_phone + "</td>";
// 		        tableStr += "<td>" + result.items[k].consignee_tel1 + "</td>";
// 		        tableStr += "<td>" + result.items[k].consignee_tel2 + "</td>";
// 		        tableStr += "<td>" + result.items[k].consignee_address + "</td>";
// 		        tableStr += "<td>" + genDateTimeAll(result.items[k].create_time) + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody>";
	    $("#exampleDTC4").html(tableStr);
	    $("#currPage4").val(pageNo);	
	    initTh();
	    genPageTag4(pageNo,result.totalResults,returnNum,'kkpager4');
	}
	function subCompanySure(id,name){
		$("#aS_id").val(id);
		$("#subCompanyName").val(name);
		$("#subCompanyClose").click();
	}
</script>
