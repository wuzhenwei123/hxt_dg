<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>查看电表号</h4>
    </div>
    <div class="modal-body">
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
    <div class="modal-footer"> <a type="button" id="closeOne" data-dismiss="modal" class="btn btn-boo">关闭</a></div>

<input type="hidden" id="s_id" value="${s_id}"/>
<input type="hidden" id="ammeter_no"/>
<input type="hidden" id="currPage1" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum1" value="10"><!-- 分页返回 -->
<a href="#stack13" id="xxxindex" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal">Demo - Modal Stacled</a>
<div id="stack13" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择发票类型</h4>
    </div>
    <div class="modal-body">
    	<form id="addForm33333" class="well well-nice form-horizontal">
	         <div class="control-group">
	         	<input type="radio" name="fpStyle" value="1">&nbsp;&nbsp;恒信通发票&nbsp;&nbsp;&nbsp;&nbsp;
		    	<input type="radio" name="fpStyle" value="2">&nbsp;&nbsp;电力增值税普票
		    	<input type="radio" name="fpStyle" value="3">&nbsp;&nbsp;电力增值税专票
		    	<input type="radio" name="fpStyle" value="4">&nbsp;&nbsp;电力额定发票
		    	<input type="hidden" id="fpStyle" isNotNull="true"/>
		    	<span class="help-inline text-red">*</span>
	         </div>
         </form>
    </div>
    <div class="modal-footer"> <a type="button" id="closeOneIndex" class="btn btn-boo" onclick="saveFPStyle()">保存</a></div>
</div>
<script>
$(document).ready(function(){
	searchData1(1);
});
function searchData1(pageNo){
		var returnNum = $("#returnNum1").val();
		var sortColumn = $("#sortColumn1").val();
	    $.getJSON("<c:url value='/hSubCompany/showDB'/>",
        {
	    	s_id : $("#s_id").val(),
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
	str+= "<th class=\"sortTh\" id=\"th_ammeter_no\" column='ammeter_no' onselectstart='return false' scope=\"col\">电表号</th>";
	str+= "<th class=\"sortTh\" id=\"th_ammeter_name\" column='ammeter_name' onselectstart='return false' scope=\"col\">客户名称</th>";
	str+= "<th class=\"sortTh\" id=\"th_electric_address\" column='electric_address' onselectstart='return false' scope=\"col\">用电地址</th>";
	str+= "<th class=\"sortTh\" id=\"th_ammeter_type\" column='ammeter_type' onselectstart='return false' scope=\"col\">缴费类型</th>";
	str+= "<th class=\"sortTh\" id=\"th_now_totalFee\" column='now_totalFee' onselectstart='return false' scope=\"col\">当前欠费</th>";
	str+= "<th class=\"sortTh\" id=\"th_pay_status\" column='pay_status' onselectstart='return false' scope=\"col\">状态</th>";
	str+= "<th class=\"sortTh\" id=\"th_pay_status\" column='pay_status' onselectstart='return false' scope=\"col\">发票类型</th>";
	str+= "<th class=\"sortTh\" id=\"th_pay_status\" column='pay_status' onselectstart='return false' scope=\"col\">操作</th>";
	str+="</tr></thead>";
	return str;
}
function setTableStr1(result, pageNo, returnNum){
 	var tableStr = "<table id='dataTable' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
    tableStr += genTableHeader1();
    var number = (pageNo - 1) * returnNum;
    tableStr += "<tfoot></tfoot>";
    tableStr += "<tbody>";
    var oneAgentOpenId = $("#aCreate_openId").val();
    for (var k=0; k<result.items.length; k++){      
        tableStr += "<tr id='DataRow"+k+"'>";
        tableStr += "<td>" + result.items[k].ammeter_no + "</td>";
        tableStr += "<td>" + result.items[k].ammeter_name + "</td>";
        tableStr += "<td>" + result.items[k].electric_address + "</td>";
        tableStr += "<td>抄表电</td>";
        tableStr += "<td>" + result.items[k].now_totalFee + "</td>";
        if(result.items[k].pay_status=='1'){
        	 tableStr += "<td>正常</td>";
        }else{
        	tableStr += "<td>停用</td>";
        }
        if(result.items[k].fp_style=='1'){
        	 tableStr += "<td>恒信通发票</td>";
        }else if(result.items[k].fp_style=='2'){
        	tableStr += "<td>电力增值税普票</td>";
        }else if(result.items[k].fp_style=='3'){
        	tableStr += "<td>电力增值税专票</td>";
        }else if(result.items[k].fp_style=='4'){
        	tableStr += "<td>电力定额发票</td>";
        }else{
        	tableStr += "<td></td>";
        }
        tableStr += "<td>";
        tableStr += "<div class=\"btn-group\">";
    	tableStr += "<perm:tag permPath='/hSubCompany/addFPStyle' ><a class='btn btn-yellow btn-mini no-wrap' href='javascript:void(0);' onclick='addFPStyle(" + result.items[k].s_id + ","+result.items[k].fp_style+",\""+result.items[k].ammeter_no+"\");return false;'>选择发票类型</a></perm:tag>";
    	tableStr += "</div>";
    	tableStr += "</td>";
        tableStr += "</tr>";            
    }
    tableStr += "</tbody>";
    $("#exampleDTC1").html(tableStr);
    $("#currPage1").val(pageNo);	
    $("#demo2").find("radio").uniform();//初始化复选框
    genPageTag1(pageNo,result.totalResults,returnNum,'kkpager1');
}
function selonagent(openId,name){
	$("#ywyname").val(name);
	$("#aYwyId").val(openId);
	$("#closeOne").click();
}
function addFPStyle(s_id,fp_style,ammeter_no){
	$("#xxxindex").click();
	$("input[name='fpStyle']").each(function(){
		if($(this).val()==fp_style){
			$(this).attr("checked","checked");
			$("#fpStyle").val(fp_style);
		}
	});
	$("#ammeter_no").val(ammeter_no);
}
function saveFPStyle(){
	var s_id = $("#s_id").val();
	var ammeter_no = $("#ammeter_no").val();
	$("input[name='fpStyle']").each(function(){
		if($(this).attr("checked")=="checked"){
			$("#fpStyle").val($(this).val());
		}
	});
	var flag = validateForm('addForm33333');
	if(flag){
		$.post("<c:url value='/hAmmeterInfo/saveFPStyle'/>",
	        	{
		    		s_id : s_id,
		    		fpStyle : $("#fpStyle").val(),
		    		ammeter_no : ammeter_no,
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	var pageNo = $("#currPage1").val(); 
		              	searchData1(pageNo);
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
		            $("#xxxindex").click();
	        });
	}
}
</script>