<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加操作方法</h4>
</div>
<div class="modal-body">
   <form id="addForm" class="well well-nice form-horizontal">
        <div class="control-group">
            <label class="control-label" for="aColumnName">菜单名称</label>
            <div class="controls">
                <input type="text" id="aColumnName" readonly="readonly" value="${column.columnName }"  isNotNull="true" warnName="菜单名称">
                <span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="aMethodName">方法名称</label>
            <div class="controls">
                <input type="text" id="aMethodName" placeholder="方法名称" isNotNull="true" warnName="方法名称">
                <span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="aActionPath">方法路径</label>
            <div class="controls">
                <input type="text" id="aActionPath" placeholder="方法路径" isNotNull="true" warnName="方法路径">
                <span class="help-inline text-red">*</span>
            </div>
        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
    <button type="submit" class="btn btn-green" onclick="addMed()">保存</button>
</div>
<script type="text/javascript">
//执行添加
	function addMed(){
		var methodName = getVal("aMethodName");
		var actionPath = getVal("aActionPath");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/columnMethod/addColumnMethod'/>",
	        	{
	    		columnId : ${column.columnId},
	    		methodName : encodeURI(methodName,"UTF-8"),
	    		actionPath : actionPath,
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		            	getColumnTreeNodes();
		            	tipOk('保存成功');
		             } else {
		            	 tipError(result.message);
		             }
		            $modal.modal('hide');
	        });
	    }
	}
</script>
