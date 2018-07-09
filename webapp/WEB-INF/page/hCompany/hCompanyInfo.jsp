<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>客户单位审核</h4>
</div>
<div class="modal-body">
<input type="hidden" id="currPage1" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum1" value="10"><!-- 分页返回 -->
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aName">客户单位名称</label>
             <div class="controls">
             ${comInfo.name }
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aContact_name">业务联系人</label>
             <div class="controls">
			    ${comInfo.contact_name }
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aContact_phone">业务联系人手机</label>
             <div class="controls">
			    ${comInfo.contact_phone }
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aContact_mail">业务联系人邮箱</label>
             <div class="controls">
			   ${comInfo.contact_mail }
             </div>
         </div>
        <%--  <div class="control-group">
             <label class="control-label" for="aFp_name">发票收件人姓名</label>
             <div class="controls">
			  ${comInfo.fp_name }
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aFp_phone">发票收件人手机</label>
             <div class="controls">
			   ${comInfo.fp_phone }
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aFp_telephone">发票收件人电话</label>
             <div class="controls">
			    ${comInfo.fp_telephone }
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aFp_address">发票收件人地址</label>
             <div class="controls">
			   ${comInfo.fp_address }
             </div>
         </div> --%>
         <div class="control-group">
             <label class="control-label" for="aFax">传真总机</label>
             <div class="controls">
			    ${comInfo.fax }
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aFax_ext">传真分机</label>
             <div class="controls">
			    ${comInfo.fax_ext }
             </div>
         </div>
         <c:if test="${not empty comInfo.ywyId }">
	         <div class="control-group">
	             <label class="control-label" for="aYwyId">业务员</label>
	             <div class="controls">
				   ${comInfo.ywyNick }
	             </div>
	         </div>
         </c:if>
         <div class="control-group">
             <label class="control-label" for="verify_type">审核意见</label>
             <div class="controls">
			   <input type="radio" name='verify_type' checked="checked" value="1" onclick="changeType(1)">通过
			   <input type="radio" name='verify_type' value="2" onclick="changeType(2)">驳回
             </div>
         </div>
         <div class="control-group" id="reason_div" style="display:none;">
             <label class="control-label" for="verify_reason">驳回原因</label>
             <div class="controls">
			  <input type="text" id="verify_reason" value="">
             </div>
         </div>
         <c:if test="${empty comInfo.ywyId }">
         	<div class="control-group">
             <label class="control-label" for="aYwyId">业务员</label>
             <div class="controls">
			    <input type="text" placeholder="业务员" id="ywyname" isNotNull="true" warnName="业务员" readonly="readonly" onclick="selOne()" />
			    <input type="hidden" placeholder="业务员" id="aYwyId" isNotNull="true" warnName="业务员" value="${comInfo.ywyId }"/>
			    <a href="#stack1" id="xxx" style="display: none;" class="btn btn-primary btn-large btn-block" data-toggle="modal"> </a>
                 <span class="help-inline text-red">*</span>
             </div>
	         </div>
	         <div id="stack1" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
			    <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
			        <h4>选择业务员</h4>
			    </div>
			    <div class="modal-body">
				    <input type="text" id="fOneAgentName" class="span2 margin5" placeholder="业务员名称">
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
			    <div class="modal-footer"> <a type="button" id="closeOne" data-dismiss="modal" class="btn btn-boo">关闭</a></div>
			</div>
         </c:if>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="add(${comInfo.id})">确定</button>
 </div>
<script type="text/javascript">
	//执行添加
	var _verify_type = 1;
	var flag = true;
	function add(id){
		if(flag){
			flag = false;
			$.post("<c:url value='/hCompany/verify'/>",
		        	{
		        	id: id,
		        	type:_verify_type,
		        	ywyId:$("#aYwyId").val(),
		        	reason:$("#verify_reason").val(),
					 _t:Math.random()},
		        	function(data){
			        	var result = eval('('+data+')'); 
			            if (result.code == '1') {
			              	var pageNo = $("#currPage").val();           
			              	searchData(pageNo);
			              	tipOk("审核成功!");
			              	$modal.modal('hide');
			             } else {
			            	 flag = true;
			            	 tipError(result.message);
			             }
		        });
		}
	}
	function changeType(type){
		_verify_type = type;
		if(type==2){
			$("#reason_div").show();
		}else{
			$("#reason_div").hide();
		}
	}
	function selOne(){
		$("#xxx").click();
		searchData1(1);
	}
	function searchData1(pageNo){
		var returnNum = $("#returnNum1").val();
			var sortColumn = $("#sortColumn1").val();
		    var name = $("#fOneAgentName").val().trim();
		    $.getJSON("<c:url value='/manageAdminUser/getManageAdminUserList1'/>",
	        {
		    	realName : $("#fOneAgentName").val(),
	    		status : 1,
	    		pageNo: pageNo,
	    		roleId:143,
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
	    	str+= "<th onselectstart='return false' scope=\"col\">序号</th>";
	    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">业务员名称</th>";
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
        	tableStr += "<td ><input type=\"radio\" id='"+result.items[k].adminId+"' class=\"checkbox check-row\" value=\"0\" name=\"checkName\" onclick='selonagent(\""+result.items[k].adminId+"\",\""+result.items[k].realName+"\")'/></td>";
        	tableStr += "<td >" + (parseInt(number) + k + 1) + "</td>";
	        tableStr += "<td >" + result.items[k].realName + "</td>";
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
</script>