<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加对公网站新闻</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aTitle">标题</label>
             <div class="controls">
			    <input type="text" placeholder="标题" id="aTitle" isNotNull="true" warnName="标题" />
                <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aContent">内容</label>
             <div class="controls">
            	<div id="editor" style="width:100%;height:300px;"></div>
            	<input type="hidden" placeholder="内容" id="aContent" isNotNull="true" warnName="内容" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aState">状态</label>
             <div class="controls">
		    	<select id="aState"> 
                    <option value="1">正常</option> 
                    <option value="0">禁用</option> 
            	</select> 
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="sortId">排序</label>
             <div class="controls">
			    <input type="text" placeholder="排序" id="sortId" />
                 <span class="help-inline text-red">公告列表按此值从大到小排列</span>
             </div>
         </div>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="add()">保存</button>
 </div>
<script type="text/javascript">
	var ue ;
	$(document).ready(function(){
		if(ue){
			ue.destroy();
		}
		ue = UE.getEditor('editor');
	});
	//执行添加
	function add(){
		$("#aContent").val(escape(ue.getContent()));
		var id = getVal("aId");
		var title = getVal("aTitle");
		var content = getVal("aContent");
		var createTime = getVal("aCreateTime");
		var createId = getVal("aCreateId");
		var state = getVal("aState");
		var source = getVal("aSource");
		var sortId = getVal("sortId");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/hNewNotice/addHNewNotice'/>",
	        	{
		    		id : id,
		    		title : title,
		    		content : content,
		    		sortId:sortId,
		    		createTime : createTime,
		    		createId : createId,
		    		state : state,
		    		source : source,
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