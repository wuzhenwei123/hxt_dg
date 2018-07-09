<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<style>
.preview{vertical-align:middle;text-align:center;}
.maskbg{background:rgba(0,0,0,.8);display:none;height:100%;left:0;position:fixed;top:0;width:100%;z-index:19999;overflow: auto;}
</style>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>手机资料中止审核</h4>
</div>
<div class="modal-body">
<input type="hidden" id="currPage1" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum1" value="10"><!-- 分页返回 -->
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aContact_name">合同</label>
             <div class="controls">
             	<c:if test="${empty hproxymessage.chexiaoUrl}">
             		暂无合同
             	</c:if>
             	<c:if test="${not empty hproxymessage.chexiaoUrl}">
			    	<img alt="" src="${pic_front}${hproxymessage.chexiaoUrl}" style="width:300px;height:100px;" onclick="preview('${hproxymessage.chexiaoUrl}',this)">
             	</c:if>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="verify_type">审核意见</label>
             <div class="controls">
			   <input type="radio" name='verify_type' checked="checked" value="1" onclick="changeType(4)">通过
			   <input type="radio" name='verify_type' value="5" onclick="changeType(5)">驳回
             </div>
         </div>
         <div class="control-group" id="reason_div" style="display:none;">
             <label class="control-label" for="msg">驳回原因</label>
             <div class="controls">
			  <textarea rows="4" id="msg"></textarea>
             </div>
         </div>
     </form>
 </div>
 <input type="hidden" id="projectPath" value="${ctx}">
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="add(${hproxymessage.id})">确定</button>
 </div>
 <div class="preview maskbg" data-p="" onclick="closePreview()">
	<img src="images/icon.jpg" />
</div>
<div class="loading-box maskbg">
	<img src="${ctx}/images/weixin/loading.gif" />
</div>
<script type="text/javascript">
	//执行添加
	var _verify_type = 4;
	var flag = true;
	function add(id){
		if(_verify_type==5){
			var msg = $("#msg").val();
			if(msg==""){
				tipError("请填写驳回原因");
				return;
			}
		}
		if(flag){
			flag = false;
			$(".loading-box").show();
			$.post("<c:url value='/hProxyMessage/shenhe'/>",
		        	{
						id	:id,
						cId	:id,
						checkState:_verify_type,
						msg:$("#msg").val(),
						ranNum:Math.random()
					},
		        	function(data){
						flag = true;
						$(".loading-box").hide();
			        	var result = eval('('+data+')'); 
			            if (result.code == '1') {
			              	searchData("1");
			              	tipOk("操作成功!");
			              	$(".close").click();
			             } else {
			              	tipError(result.message);
			             }
			        });
		}
	}
	function changeType(type){
		_verify_type = type;
		if(type==5){
			$("#reason_div").show();
		}else{
			$("#reason_div").hide();
		}
	}
	function preview(newFileName,obj){
		//获取图片的宽和高
		var image = new Image();
	    image.src = obj.src;
	    var naturalWidth = image.width;
	    var naturalHight = image.height;
		$(".preview").find("img").attr('src',"${pic_front}"+newFileName);
		$(".preview").find("img").attr('width',naturalWidth);
		$(".preview").find("img").attr('height',naturalHight);
		$(".preview").show();
	}
	
	function closePreview(){
		$(".preview").hide();
	}
</script>