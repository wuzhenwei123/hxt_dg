<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加${domainName?cap_first}</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
    	<#list columns as item>
         <div class="control-group">
             <label class="control-label" for="a${item.name?cap_first}">${item.name}</label>
             <div class="controls">
             	<#if item.type == "Date">
		     	<input type="text" placeholder="${item.name}" id="a${item.name?cap_first}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" isNotNull="true" warnName="${item.name}" />
           		<#else>
           		<#if item.name == "state">
		    	<select id="aState"> 
                    <option value="1">正常</option> 
                    <option value="0">禁用</option> 
            	</select> 
           		<#else>
			    <input type="text" placeholder="${item.name}" id="a${item.name?cap_first}" isNotNull="true" warnName="${item.name}" />
           		</#if>
           		</#if>
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
    	</#list>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="add()">保存</button>
 </div>
<script type="text/javascript">
	//执行添加
	function add(){
	<#list columns as item>
		var ${item.name} = getVal("a${item.name?cap_first}");
	</#list> 
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/${domainName}/add${domainName?cap_first}'/>",
	        	{
	    		<#list columns as item>
		    		${item.name} : ${item.name},
				</#list> 
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	var pageNo = $("#currPage").val();           
		              	searchData(pageNo);
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>