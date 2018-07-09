<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>查看</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
           <div class="control-group">
             <label class="control-label" for="mTitle">标题</label>
             <div class="controls">
			   ${hnotice.title }
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContent">内容</label>
             <div class="controls">
			    ${hnotice.content }
             </div>
         </div>
     </form>
 </div>
