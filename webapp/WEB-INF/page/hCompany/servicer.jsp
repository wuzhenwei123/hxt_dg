<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div id="stack4" class="modal hide fade" tabindex="-1" data-focus-on="input:first" >
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择服务人员</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fServicerName" class="span2 margin5" placeholder="服务人员名称">
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
	     <a type="button" id="servicerClose" data-dismiss="modal" class="btn btn-boo">关闭</a>
    </div>
</div>
<input type="hidden" id="currPage4" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum4" value="10"><!-- 分页返回 -->
<input type="hidden" id="sortColumn4" value=""><!-- 排序字段 -->
<script type="text/javascript">
	function searchData4(pageNo){
		var returnNum = $("#returnNum4").val();
		var sortColumn = $("#sortColumn4").val();
	    var name = $("#fServicerName").val(); 
	    $.getJSON("<c:url value='/hCompany/getServicerList'/>",
        {
        	sortColumn:sortColumn,
    		realName : name,
    		state : 1,
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
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">服务人员姓名</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">服务人员手机号</th>";
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
	        if($("#mServicerId")){
	        	var mServicerId = $("#mServicerId").val();
	        	if(mServicerId==result.items[k].adminId){
	        		tableStr += "<td style='float:right;'><input type=\"radio\" checked value='"+result.items[k].adminId+"' id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"servicerRadio\" onclick=\"servicerSure("+result.items[k].adminId+",\'"+result.items[k].realName+"\');\"/></td>";
	        	}else{
	        		tableStr += "<td style='float:right;'><input type=\"radio\" value='"+result.items[k].adminId+"' id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"servicerRadio\" onclick=\"servicerSure("+result.items[k].adminId+",\'"+result.items[k].realName+"\');\"/></td>";
	        	}
	        }else{
	        	tableStr += "<td style='float:right;'><input type=\"radio\" value='"+result.items[k].adminId+"' id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"servicerRadio\" onclick=\"servicerSure("+result.items[k].adminId+",\'"+result.items[k].realName+"\');\"/></td>";
	        }
	        tableStr += "<td>" + result.items[k].realName + "</td>";
	        tableStr += "<td>" + result.items[k].mobile + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody>";
	    $("#exampleDTC4").html(tableStr);
	    $("#currPage4").val(pageNo);	
	    initTh();
	    genPageTag4(pageNo,result.totalResults,returnNum,'kkpager4');
	}
	function servicerSure(id,name){
		if(modelType==1){
			$("#servicerIdQuery").val(id);
			$("#servicerNameQuery").val(name);
			$("#aServicerNameQuery").val(name);
// 			$("#servicerCloseQuery").click();
		}else if(modelType==2){
			$("#servicerId").val(id);
			$("#servicerName").val(name);
			$("#aServicerName").val(name);
// 			$("#servicerClose").click();
		}else if(modelType==3){
			$("#mServicerId").val(id);
			$("#mServicerName").val(name);
			$("#MServicerName").val(name);
// 			$("#servicerClose").click();
		}
		$stack4.modal('hide');
	}
</script>
