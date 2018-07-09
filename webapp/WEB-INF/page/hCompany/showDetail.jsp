<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<style>
.preview{vertical-align:middle;text-align:center;}
.maskbg{background:rgba(0,0,0,.8);display:none;height:100%;left:0;position:fixed;top:0;width:100%;z-index:19999;overflow: auto;}
</style>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>查看客户单位信息</h4>
</div>
<div class="modal-body">
<input type="hidden" id="currPage1" value="1"><!-- 当前页码 -->
<input type="hidden" id="returnNum1" value="10"><!-- 分页返回 -->
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mName">客户单位名称</label>
             <div class="controls">
			    ${hcompany.name}
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContact_name">业务联系人</label>
             <div class="controls">
			   ${hcompany.contact_name}
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContact_phone">业务联系人手机</label>
             <div class="controls">
			    ${hcompany.contact_phone}
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mContact_mail">业务联系人邮箱</label>
             <div class="controls">
			    ${hcompany.contact_mail}
             </div>
         </div>
          <div class="control-group">
             <label class="control-label" for="aCom_business_doc_type">营业证件类型</label>
             <div class="controls">
             	<c:if test="${ hcompany.com_business_doc_type==1}">三证合一</c:if>
             	<c:if test="${ hcompany.com_business_doc_type==2}">三证合一</c:if>
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aCredit_code">企业统一社会信用代码</label>
             <div class="controls">
			    ${hcompany.credit_code}
                 
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aCom_license_no">营业执照号码</label>
             <div class="controls">
			    ${hcompany.com_license_no}
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aCom_license_no">营业执照副本</label>
             <div class="controls">
			    <img alt="" src="http://test2.chinaepay.com/hxt_dg/${hcompany.com_license_img}" style="width:300px;height:100px;" onclick="preview('${hcompany.com_license_img}',this)">
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aCom_zip_code">营业地址邮编</label>
             <div class="controls">
			    ${hcompany.com_zip_code }
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aZp_code">税务登记证号码</label>
             <div class="controls">
			    ${hcompany.com_tax_no }
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aZp_address">税务登记证照片</label>
             <div class="controls">
			    <img alt="" src="http://test2.chinaepay.com/hxt_dg/${hcompany.com_tax_img}" style="width:300px;height:100px;" onclick="preview('${hcompany.com_tax_img}',this)">
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aZp_phone">组织机构代码证号</label>
             <div class="controls">
                 ${hcompany.com_dept_code }
             </div>
         </div>
     	<div class="control-group">
             <label class="control-label" for="aZp_bank_code">组织机构代码证照</label>
             <div class="controls">
			   <img alt="" src="http://test2.chinaepay.com/hxt_dg/${hcompany.com_dept_img}" style="width:300px;height:100px;" onclick="preview('${hcompany.com_dept_img}',this)">
             </div>
         </div>
         <div class="control-group">
            <label class="control-label" for="mAgentOneName">客户经理</label>
            <div class="controls">
		    ${hcompany.oneAgentName }
            </div>
         </div>
          <div class="control-group">
            <label class="control-label" for="aAgentTwoName">代理</label>
            <div class="controls">
		    ${hcompany.twoAgentName }
            </div>
         </div>
          <div class="control-group">
            <label class="control-label" for="aServicerName">服务人员</label>
            <div class="controls">
		   ${hcompany.servicerName }
            </div>
         </div>
     </form>
 </div>
 <div class="preview maskbg" data-p="" onclick="closePreview()">
	<img src="images/icon.jpg" />
</div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">关闭</button>
 </div>
<script type="text/javascript">
function preview(newFileName,obj){
	//获取图片的宽和高
	var image = new Image();
    image.src = obj.src;
    var naturalWidth = image.width;
    var naturalHight = image.height;
	$(".preview").find("img").attr('src',"http://test2.chinaepay.com/hxt_dg/"+newFileName);
	$(".preview").find("img").attr('width',naturalWidth);
	$(".preview").find("img").attr('height',naturalHight);
	$(".preview").show();
}

function closePreview(){
	$(".preview").hide();
}
</script>