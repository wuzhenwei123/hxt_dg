<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑菜单</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mColumnId">菜单ID</label>
             <div class="controls">
                 <input type="text" readonly="readonly" id="mColumnId" value="${managecolumn.columnId}" placeholder="菜单ID" isNotNull="true" warnName="菜单ID">
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mColumnName">菜单名称</label>
             <div class="controls">
                 <input type="text" id="mColumnName" placeholder="菜单名称" value="${managecolumn.columnName}" isNotNull="true" warnName="菜单名称">
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mColumnUrl">菜单路径</label>
             <div class="controls">
                 <input type="text" id="mColumnUrl" value="${managecolumn.columnUrl}" placeholder="菜单路径" isNotNull="true" warnName="菜单路径">
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group"> 
             <label class="control-label" for="mParentColumnID">父菜单</label>
             <div class="controls">
                 <select id="mParentColumnID"> 
                   <option <c:if test="${managecolumn.parentColumnID == column.columnId }" >selected="selected"</c:if> value="-1">--根目录--</option> 
                   <c:forEach items="${parentColumnList }" var="column">
			    	<option <c:if test="${managecolumn.parentColumnID == column.columnId }" >selected="selected"</c:if> value="${column.columnId }">${column.columnName }</option>                     
                   </c:forEach>
               	</select>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mState">状态</label>
             <div class="controls">
                 <select id="mState" isNotNull="true" warnName="状态"> 
                 	<option <c:if test="${managecolumn.state == 1}" >selected="selected"</c:if>  value="1">正常</option> 
                    <option <c:if test="${managecolumn.state == 0}" >selected="selected"</c:if> value="0">禁用</option> 
               	 </select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group input-append">
             <label class="control-label" for="mColumnImg">菜单图片</label>
             <div class="controls">
                <input type="hidden" value="${managecolumn.columnImg}" id="mColumnImg" placeholder="菜单图片"><!-- active -->
                <c:set value="${managecolumn.columnImg}" var="columnImg"></c:set>
                <c:if test="${empty columnImg }">
                <c:set value="fontello-icon-monitor" var="columnImg"></c:set>
                </c:if>
				<div class="btn-group" data-toggle="buttons-radio">
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-monitor')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-monitor'}">active</c:if>"><i class="fontello-icon-monitor"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-menu')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-menu'}">active</c:if>"><i class="fontello-icon-menu"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-book-open')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-book-open'}">active</c:if>"><i class="fontello-icon-book-open"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-cog-alt')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-cog-alt'}">active</c:if>"><i class="fontello-icon-cog-alt"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-comment')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-comment'}">active</c:if>"><i class="fontello-icon-comment"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-database')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-database'}">active</c:if>"><i class="fontello-icon-database"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-download')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-download'}">active</c:if>"><i class="fontello-icon-download"></i></button>
	                <br>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-chart-pie-3')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-chart-pie-3'}">active</c:if>"><i class="fontello-icon-chart-pie-3"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-chart')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-chart'}">active</c:if>"><i class="fontello-icon-chart"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-folder')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-folder'}">active</c:if>"><i class="fontello-icon-folder"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-list')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-list'}">active</c:if>"><i class="fontello-icon-list"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-user')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-user'}">active</c:if>"><i class="fontello-icon-user"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-trash-1')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-trash-1'}">active</c:if>"><i class="fontello-icon-trash-1"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-th-large')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-th-large'}">active</c:if>"><i class="fontello-icon-th-large"></i></button>
	                <br>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-th-1')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-th-1'}">active</c:if>"><i class="fontello-icon-th-1"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-table')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-table'}">active</c:if>"><i class="fontello-icon-table"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-sitemap')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-sitemap'}">active</c:if>"><i class="fontello-icon-sitemap"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-mail')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-mail'}">active</c:if>"><i class="fontello-icon-mail"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-phone-squared')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-phone-squared'}">active</c:if>"><i class="fontello-icon-phone-squared"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-picture-1')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-picture-1'}">active</c:if>"><i class="fontello-icon-picture-1"></i></button>
	                 <button type="button" onclick="setVal('mColumnImg','fontello-icon-lock-3')" class="btn btn-glyph <c:if test="${columnImg eq 'fontello-icon-lock-3'}">active</c:if>"><i class="fontello-icon-lock-3"></i></button>
                 </div>
             </div>
         </div>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="update()">更新</button>
 </div>
<script type="text/javascript">
	function setVal(id,val){
		$("#"+id).val(val);
	}
	// 执行更新
	function update(){
		var columnId = getVal("mColumnId");
		var columnName = getVal("mColumnName");
		var columnUrl = getVal("mColumnUrl");
		var parentColumnID = getVal("mParentColumnID");
		var state = getVal("mState");
		var columnOrder = getVal("mColumnOrder");
		var columnImg = getVal("mColumnImg");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/manageColumn/updateManageColumn'/>",
	        	{
	    		columnId : columnId,
	    		columnName : columnName,
	    		columnUrl : columnUrl,
	    		parentColumnID : parentColumnID,
	    		state : state,
	    		columnOrder : columnOrder,
	    		columnImg : columnImg,
				 _t:Math.random()},
	        	function(data){
		        	var result = eval('('+data+')'); 
		            if (result.code == '1') {
		              	var pageNo = $("#currPage").val();           
		              	searchData(pageNo);
		              	tipOk("更新成功!");
		             } else {
		            	 tipError(result.message);
		             }
		            $modal.modal('hide');
	        });
	    }
	}
</script>