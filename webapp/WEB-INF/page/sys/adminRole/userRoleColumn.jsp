<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
  <style type="text/css">
   	.ztree li a.curSelectedNode{
   		background-color: #FFFFFF;
   		border: 0px solid #FFFFFF;
   		text-decoration: none;
   	}
   	.ztree li a{
   		text-decoration: none;
   	}
   	.ztree li a:hover{
   		text-decoration: none;
   	}
   	.ztree li ul .med{
   		color:blue;
   	}
   	
   </style>
   <div id="temModal" class="modal hide fade" tabindex="-1" data-width="50%"></div>
   <SCRIPT type="text/javascript">
   var zTree ;
   var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			view: {
				addDiyDom: addDiyDom,
				fontCss : {color:"#797979"}
			}
		};
	// 设置操作方法
   function addDiyDom(treeId, treeNode) {
	   
	   var medHtml ='';
	   var meds = JSON.parse(treeNode.meds);//操作集合
	   if(meds.length == 0)
		   return;
	   for(var i =0 ; i< meds.length;i++){
		   var med = meds[i];
		   if(med.checked){// 是否选中
			   medHtml += "|<span class='med' >"+med.name+"</span><input type='checkbox' checked='checked' value='"+med.mid+"' name='opName' />";
		   }else{
			   medHtml += "|<span class='med' >"+med.name+"</span><input type='checkbox' value='"+med.mid+"' name='opName' />";
		   }
	   }
	   	
		var aObj = $("#" + treeNode.tId + "_a");
		if ($("#diyBtn_"+treeNode.id).length>0) return;
		var editStr = medHtml;
		aObj.append(editStr);
	};
	     	
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, ${nodes});
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
		type = { "Y":"ps", "N":"ps"};
		zTree.setting.check.chkboxType = type;
		
	});
	// 获取选中菜单
	function getSelectedNodes(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);
		return nodes;
	}
	
	//获取选中操作方法
	function getSelectMed(){
		var mids = '';
		$("[name='opName']").each(function(){
			if($(this).attr("checked")){
				mids += $(this).attr("value")+",";
			}
		})
		return mids;
	}
	function save(){
		var nodes = getSelectedNodes();
        var mids = getSelectMed();
        var nodeStr = JSON.stringify(nodes);
	    $("#saveBtn").addClass("fontello-icon-spin5");
	    $("#saveBtn").addClass("disabled");
	    $("#saveBtn").html("正在更新...");
       	$.post("<c:url value='/adminRole/updateRoleColumn'/>",
       	{
    		roleId : '${roleId}',
    		nodeStr : nodeStr,
    		mids : mids,
			 _t:Math.random()},
        	function(data){
	        	var result = eval('('+data+')'); 
	            if (result.code == '1') {
	              	tipOk('更新成功');
	             } else {
	            	 tipError(result.message);
	             }
	            $modal.modal('hide');
       	 });
	}
</SCRIPT>
   
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>维护角色</h4>
</div>
<div class="modal-body">
   <form id="addForm" class="well well-nice form-horizontal">
   		<ul id="treeDemo" class="ztree"></ul>
   </form>
</div>
<div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
    <button type="submit" id="saveBtn" class="btn btn-green" onclick="save()">保存</button>
</div>
