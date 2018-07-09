<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>编辑OpeningBank</h4>
</div>
<div class="modal-body">
    <form id="updateForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="mId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" value="${openingbank.id}" id="mId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mNBKCODE">NBKCODE</label>
             <div class="controls">
			    <input type="text" placeholder="NBKCODE" value="${openingbank.NBKCODE}" id="mNBKCODE" isNotNull="true" warnName="NBKCODE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mSABKCODE">SABKCODE</label>
             <div class="controls">
			    <input type="text" placeholder="SABKCODE" value="${openingbank.SABKCODE}" id="mSABKCODE" isNotNull="true" warnName="SABKCODE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mBNKCITY">BNKCITY</label>
             <div class="controls">
			    <input type="text" placeholder="BNKCITY" value="${openingbank.BNKCITY}" id="mBNKCITY" isNotNull="true" warnName="BNKCITY" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mNBKNAME">NBKNAME</label>
             <div class="controls">
			    <input type="text" placeholder="NBKNAME" value="${openingbank.NBKNAME}" id="mNBKNAME" isNotNull="true" warnName="NBKNAME" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mNBKSNAME">NBKSNAME</label>
             <div class="controls">
			    <input type="text" placeholder="NBKSNAME" value="${openingbank.NBKSNAME}" id="mNBKSNAME" isNotNull="true" warnName="NBKSNAME" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mNBKADDRESS">NBKADDRESS</label>
             <div class="controls">
			    <input type="text" placeholder="NBKADDRESS" value="${openingbank.NBKADDRESS}" id="mNBKADDRESS" isNotNull="true" warnName="NBKADDRESS" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCNTTEL">CNTTEL</label>
             <div class="controls">
			    <input type="text" placeholder="CNTTEL" value="${openingbank.CNTTEL}" id="mCNTTEL" isNotNull="true" warnName="CNTTEL" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCNTPER">CNTPER</label>
             <div class="controls">
			    <input type="text" placeholder="CNTPER" value="${openingbank.CNTPER}" id="mCNTPER" isNotNull="true" warnName="CNTPER" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPOSTCODE">POSTCODE</label>
             <div class="controls">
			    <input type="text" placeholder="POSTCODE" value="${openingbank.POSTCODE}" id="mPOSTCODE" isNotNull="true" warnName="POSTCODE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mNBKSTATE">NBKSTATE</label>
             <div class="controls">
			    <input type="text" placeholder="NBKSTATE" value="${openingbank.NBKSTATE}" id="mNBKSTATE" isNotNull="true" warnName="NBKSTATE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mBKEMAIL">BKEMAIL</label>
             <div class="controls">
			    <input type="text" placeholder="BKEMAIL" value="${openingbank.BKEMAIL}" id="mBKEMAIL" isNotNull="true" warnName="BKEMAIL" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCONTENT">CONTENT</label>
             <div class="controls">
			    <input type="text" placeholder="CONTENT" value="${openingbank.CONTENT}" id="mCONTENT" isNotNull="true" warnName="CONTENT" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mPARTTYPE">PARTTYPE</label>
             <div class="controls">
			    <input type="text" placeholder="PARTTYPE" value="${openingbank.PARTTYPE}" id="mPARTTYPE" isNotNull="true" warnName="PARTTYPE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mBANKCATCODE">BANKCATCODE</label>
             <div class="controls">
			    <input type="text" placeholder="BANKCATCODE" value="${openingbank.BANKCATCODE}" id="mBANKCATCODE" isNotNull="true" warnName="BANKCATCODE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mHIGHPART">HIGHPART</label>
             <div class="controls">
			    <input type="text" placeholder="HIGHPART" value="${openingbank.HIGHPART}" id="mHIGHPART" isNotNull="true" warnName="HIGHPART" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mBEARBANKCODE">BEARBANKCODE</label>
             <div class="controls">
			    <input type="text" placeholder="BEARBANKCODE" value="${openingbank.BEARBANKCODE}" id="mBEARBANKCODE" isNotNull="true" warnName="BEARBANKCODE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mCHARGEBANKCODE">CHARGEBANKCODE</label>
             <div class="controls">
			    <input type="text" placeholder="CHARGEBANKCODE" value="${openingbank.CHARGEBANKCODE}" id="mCHARGEBANKCODE" isNotNull="true" warnName="CHARGEBANKCODE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mNODECODE">NODECODE</label>
             <div class="controls">
			    <input type="text" placeholder="NODECODE" value="${openingbank.NODECODE}" id="mNODECODE" isNotNull="true" warnName="NODECODE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="mSIGN">SIGN</label>
             <div class="controls">
			    <input type="text" placeholder="SIGN" value="${openingbank.SIGN}" id="mSIGN" isNotNull="true" warnName="SIGN" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="update()">更新</button>
 </div>
<script type="text/javascript">
	// 执行更新
	function update(){
		var id = getVal("mId");
		var NBKCODE = getVal("mNBKCODE");
		var SABKCODE = getVal("mSABKCODE");
		var BNKCITY = getVal("mBNKCITY");
		var NBKNAME = getVal("mNBKNAME");
		var NBKSNAME = getVal("mNBKSNAME");
		var NBKADDRESS = getVal("mNBKADDRESS");
		var CNTTEL = getVal("mCNTTEL");
		var CNTPER = getVal("mCNTPER");
		var POSTCODE = getVal("mPOSTCODE");
		var NBKSTATE = getVal("mNBKSTATE");
		var BKEMAIL = getVal("mBKEMAIL");
		var CONTENT = getVal("mCONTENT");
		var PARTTYPE = getVal("mPARTTYPE");
		var BANKCATCODE = getVal("mBANKCATCODE");
		var HIGHPART = getVal("mHIGHPART");
		var BEARBANKCODE = getVal("mBEARBANKCODE");
		var CHARGEBANKCODE = getVal("mCHARGEBANKCODE");
		var NODECODE = getVal("mNODECODE");
		var SIGN = getVal("mSIGN");
		var flag = validateForm('updateForm');
	    if (flag){ 
	        $.post("<c:url value='/openingBank/updateOpeningBank'/>",
	        	{
		    		id : id,
		    		NBKCODE : NBKCODE,
		    		SABKCODE : SABKCODE,
		    		BNKCITY : BNKCITY,
		    		NBKNAME : NBKNAME,
		    		NBKSNAME : NBKSNAME,
		    		NBKADDRESS : NBKADDRESS,
		    		CNTTEL : CNTTEL,
		    		CNTPER : CNTPER,
		    		POSTCODE : POSTCODE,
		    		NBKSTATE : NBKSTATE,
		    		BKEMAIL : BKEMAIL,
		    		CONTENT : CONTENT,
		    		PARTTYPE : PARTTYPE,
		    		BANKCATCODE : BANKCATCODE,
		    		HIGHPART : HIGHPART,
		    		BEARBANKCODE : BEARBANKCODE,
		    		CHARGEBANKCODE : CHARGEBANKCODE,
		    		NODECODE : NODECODE,
		    		SIGN : SIGN,
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