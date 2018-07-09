<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑方法</h4>
</div>
<div class="modal-body">
   <form id="updateForm" class="well well-nice form-horizontal">
        <div class="control-group">
            <label class="control-label" for="mMid">方法ID</label>
            <div class="controls">
                <input type="text" readonly="readonly" id="mMid" value="${columnmethod.mid}" placeholder="方法ID" isNotNull="true" warnName="方法ID">
                <span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="mMethodName">方法名称</label>
            <div class="controls">
                <input type="text" id="mMethodName" value="${columnmethod.methodName}" placeholder="方法名称" isNotNull="true" warnName="方法名称">
                <span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="mActionPath">方法路径</label>
            <div class="controls">
                <input type="text" id="mActionPath" placeholder="方法路径" value="${columnmethod.actionPath}" isNotNull="true" warnName="方法路径">
                <span class="help-inline text-red">*</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="mCreateTime">创建日期</label>
            <div class="controls">
                <input type="text" id="mCreateTime" placeholder="创建日期" readonly="readonly" value="<fmt:formatDate value="${columnmethod.createTime}" type="both"/>" />
            </div>
        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
    <button type="submit" class="btn btn-green" onclick="update()">更新</button>
</div>
<script type="text/javascript">
	//执行更新
	function update(){
		var mid = getVal("mMid");
		var columnId = getVal("mColumnId");
		var methodName = getVal("mMethodName");
		var actionPath = getVal("mActionPath");
		var createTime = getVal("mCreateTime");
		var flag = true ;
		   if (flag){ 
		       $.post("<c:url value='/columnMethod/updateColumnMethod'/>",
		       	{
		   		mid : mid,
		   		columnId : columnId,
		   		methodName : encodeURI(methodName,"UTF-8"),
		   		actionPath : actionPath,
				 _t:Math.random()},
		       	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		            	getColumnTreeNodes();
		            	tipOk("更新成功");
		             } else {
		            	 tipError(result.message);
		             }
		            $modal.modal('hide');
		       });
		   }
	}
</script>
