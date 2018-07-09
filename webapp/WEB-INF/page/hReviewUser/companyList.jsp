<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div id="stack2" class="modal hide fade" tabindex="-1" data-focus-on="input:first" data-width="80%">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
        <h4>选择企业</h4>
    </div>
    <div class="modal-body">
	    <input type="text" id="fCompanyName" class="span2 margin5" placeholder="企业名称">
	    <a href="javascript:searchData2('1');" class="btn btn-green">查询</a>
    	<div class="widget-content" id="demo3">
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
	     <a type="button" id="companyClose" data-dismiss="modal" class="btn btn-boo">关闭</a>
    </div>
</div>
<input type="hidden" id="currPage2" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum2" value="10"><!-- 分页返回 -->
<input type="hidden" id="sortColumn2" value=" a.create_time desc "><!-- 排序字段 -->
<script type="text/javascript">
	function searchData2(pageNo){
		var returnNum = $("#returnNum2").val();
		var sortColumn = $("#sortColumn2").val();
	    var name = $("#fCompanyName").val();
	    $.getJSON("<c:url value='/hCompany/getHCompanyList'/>",
        {
        	sortColumn:sortColumn,
    		name : name,
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
    	str+= "<th class=\"sortTh\" id=\"th_name\" column='name' onselectstart='return false' scope=\"col\">单位名称</th>";
    	str+= "<th class=\"sortTh\" id=\"th_contact_name\" column='contact_name' onselectstart='return false' scope=\"col\">业务联系人</th>";
    	str+= "<th class=\"sortTh\" id=\"th_contact_phone\" column='contact_phone' onselectstart='return false' scope=\"col\">业务联系人手机</th>";
    	str+= "<th class=\"sortTh\" id=\"th_contact_mail\" column='contact_mail' onselectstart='return false' scope=\"col\">业务联系人邮箱</th>";
    	str+= "<th class=\"sortTh\" id=\"th_fax\" column='fax' onselectstart='return false' scope=\"col\">传真总机</th>";
    	str+= "<th class=\"sortTh\" id=\"th_fax_ext\" column='fax_ext' onselectstart='return false' scope=\"col\">传真分机</th>";
    	str+= "<th class=\"sortTh\" id=\"th_ywyId\" column='ywyId' onselectstart='return false' scope=\"col\">业务员</th>";
    	str+= "<th class=\"sortTh\" id=\"th_verify_status\" column='verify_status' onselectstart='return false' scope=\"col\">审核状态</th>";
    	str+= "<th class=\"sortTh\" id=\"th_status\" column='status' onselectstart='return false' scope=\"col\">状态</th>";
    	str+= "<th class=\"sortTh\" id=\"th_create_time\" width='135' column='create_time' onselectstart='return false' scope=\"col\">创建时间</th>";
		str+="</tr></thead>";
	return str;
	}
	function setTableStr2(result, pageNo, returnNum){
		var tableStr = "<table id='dataTable2' class=\"table table-striped table-content table-condensed boo-table table-hover bg-blue-light\">";
	    tableStr += genTableHeader2();
	    var number = (pageNo - 1) * returnNum;
        tableStr += "<tfoot></tfoot>";
        tableStr += "<tbody>";
	    for (var k=0; k<result.items.length; k++){      
	        tableStr += "<tr id='DataRow"+k+"'>";
	        tableStr += "<td style='float:right;'><input type=\"radio\" value='"+result.items[k].id+"' id='"+result.items[k].id+"' class=\"checkbox check-row\" value=\"0\" name=\"companyRadio\" onclick=\"companySure("+result.items[k].id+",\'"+result.items[k].name+"\');\"/></td>";
	        
		        tableStr += "<td>" + result.items[k].name + "</td>"; 
		        tableStr += "<td>" + result.items[k].contact_name + "</td>";
		        tableStr += "<td>" + result.items[k].contact_phone + "</td>";
		        tableStr += "<td>" + result.items[k].contact_mail + "</td>";
		        tableStr += "<td>" + result.items[k].fax + "</td>";
		        tableStr += "<td>" + result.items[k].fax_ext + "</td>";
		        tableStr += "<td>" + result.items[k].ywyNick + "</td>";
		        var verify_status = result.items[k].verify_status;
		        var ver_sta_str='';
		        if(verify_status==1){ver_sta_str='通过';}else if(verify_status==2){ver_sta_str='驳回';}else{ver_sta_str='待审核';}
		        tableStr += "<td>" + ver_sta_str + "</td>";
		        var statusStr = result.items[k].status;
		        if(statusStr==1){
		        	statusStr = '正常';
		        }else if (statusStr==2){
		        	statusStr = "暂停";
		        }else{
		        	statusStr = "无效";
		        }
		        tableStr += "<td>" + statusStr + "</td>";
		        tableStr += "<td>" + genDateTimeAll(result.items[k].create_time) + "</td>";
	        tableStr += "</tr>";            
	    }
	    tableStr += "</tbody>";
	    $("#exampleDTC2").html(tableStr);
	    $("#currPage2").val(pageNo);	
	    initTh();
	    genPageTag2(pageNo,result.totalResults,returnNum,'kkpager2');
	}
	function companySure(id,name){
		$("#aCompanyId").val(id);
		$("#companyName").val(name);
		$("#companyClose").click();
	}
</script>
