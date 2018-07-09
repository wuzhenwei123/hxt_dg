<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加菜单</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aColumnName">菜单名称</label>
             <div class="controls">
                 <input type="text" id="aColumnName" placeholder="菜单名称" isNotNull="true" warnName="菜单名称">
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aColumnUrl">菜单路径</label>
             <div class="controls">
                 <input type="text" id="aColumnUrl" placeholder="菜单路径" isNotNull="true" warnName="菜单路径">
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group"> 
             <label class="control-label" for="aParentColumnID">父菜单</label>
             <div class="controls">
                 <select id="aParentColumnID"> 
                    <option value="-1">--根目录--</option> 
                    <c:forEach items="${parentColumnList }" var="column">
		    		<option value="${column.columnId }">${column.columnName }</option>                     
                    </c:forEach>
               	</select>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aState">状态</label>
             <div class="controls">
                 <select id="aState" isNotNull="true" warnName="状态"> 
                 	<option value="1">正常</option> 
                    <option value="0">禁用</option> 
               	 </select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group input-append">
             <label class="control-label" for="aColumnImg">菜单图片</label>
             <div class="controls">
                <input type="hidden" value="fontello-icon-monitor" id="aColumnImg" placeholder="菜单图片">
				<div class="btn-group" data-toggle="buttons-radio">
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-monitor')" class="btn btn-glyph active"><i class="fontello-icon-monitor"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-menu')" class="btn btn-glyph"><i class="fontello-icon-menu"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-book-open')" class="btn btn-glyph"><i class="fontello-icon-book-open"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-cog-alt')" class="btn btn-glyph"><i class="fontello-icon-cog-alt"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-comment')" class="btn btn-glyph"><i class="fontello-icon-comment"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-database')" class="btn btn-glyph"><i class="fontello-icon-database"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-download')" class="btn btn-glyph"><i class="fontello-icon-download"></i></button>
	                 <br>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-chart-pie-3')" class="btn btn-glyph"><i class="fontello-icon-chart-pie-3"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-chart')" class="btn btn-glyph"><i class="fontello-icon-chart"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-folder')" class="btn btn-glyph"><i class="fontello-icon-folder"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-list')" class="btn btn-glyph"><i class="fontello-icon-list"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-user')" class="btn btn-glyph"><i class="fontello-icon-user"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-trash-1')" class="btn btn-glyph"><i class="fontello-icon-trash-1"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-th-large')" class="btn btn-glyph"><i class="fontello-icon-th-large"></i></button>
	                 <br>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-th-1')" class="btn btn-glyph"><i class="fontello-icon-th-1"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-table')" class="btn btn-glyph"><i class="fontello-icon-table"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-sitemap')" class="btn btn-glyph"><i class="fontello-icon-sitemap"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-mail')" class="btn btn-glyph"><i class="fontello-icon-mail"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-phone-squared')" class="btn btn-glyph"><i class="fontello-icon-phone-squared"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-picture-1')" class="btn btn-glyph"><i class="fontello-icon-picture-1"></i></button>
	                 <button type="button" onclick="setVal('aColumnImg','fontello-icon-lock-3')" class="btn btn-glyph"><i class="fontello-icon-lock-3"></i></button>
                 </div>
             </div>
         </div>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="add()">保存</button>
 </div>
<script type="text/javascript">
	
	function setVal(id,val){
		$("#"+id).val(val);
	}
	
	//执行添加
	function add(){
		var columnId = getVal("aColumnId");
		var columnName = getVal("aColumnName");
		var columnUrl = getVal("aColumnUrl");
		var parentColumnID = getVal("aParentColumnID");
		var state = getVal("aState");
		var columnOrder = getVal("aColumnOrder");
		var columnImg = getVal("aColumnImg");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/manageColumn/addManageColumn'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>