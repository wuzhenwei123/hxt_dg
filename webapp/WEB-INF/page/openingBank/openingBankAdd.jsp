<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/config.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fontello-icon-cancel-1"></i></button>
     <h4>添加OpeningBank</h4>
</div>
<div class="modal-body">
    <form id="addForm" class="well well-nice form-horizontal">
         <div class="control-group">
             <label class="control-label" for="aId">id</label>
             <div class="controls">
			    <input type="text" placeholder="id" id="aId" isNotNull="true" warnName="id" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aNBKCODE">NBKCODE</label>
             <div class="controls">
			    <input type="text" placeholder="NBKCODE" id="aNBKCODE" isNotNull="true" warnName="NBKCODE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aSABKCODE">SABKCODE</label>
             <div class="controls">
			    <input type="text" placeholder="SABKCODE" id="aSABKCODE" isNotNull="true" warnName="SABKCODE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBNKCITY">BNKCITY</label>
             <div class="controls">
			    <input type="text" placeholder="BNKCITY" id="aBNKCITY" isNotNull="true" warnName="BNKCITY" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aNBKNAME">NBKNAME</label>
             <div class="controls">
			    <input type="text" placeholder="NBKNAME" id="aNBKNAME" isNotNull="true" warnName="NBKNAME" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aNBKSNAME">NBKSNAME</label>
             <div class="controls">
			    <input type="text" placeholder="NBKSNAME" id="aNBKSNAME" isNotNull="true" warnName="NBKSNAME" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aNBKADDRESS">NBKADDRESS</label>
             <div class="controls">
			    <input type="text" placeholder="NBKADDRESS" id="aNBKADDRESS" isNotNull="true" warnName="NBKADDRESS" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCNTTEL">CNTTEL</label>
             <div class="controls">
			    <input type="text" placeholder="CNTTEL" id="aCNTTEL" isNotNull="true" warnName="CNTTEL" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCNTPER">CNTPER</label>
             <div class="controls">
			    <input type="text" placeholder="CNTPER" id="aCNTPER" isNotNull="true" warnName="CNTPER" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPOSTCODE">POSTCODE</label>
             <div class="controls">
			    <input type="text" placeholder="POSTCODE" id="aPOSTCODE" isNotNull="true" warnName="POSTCODE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aNBKSTATE">NBKSTATE</label>
             <div class="controls">
			    <input type="text" placeholder="NBKSTATE" id="aNBKSTATE" isNotNull="true" warnName="NBKSTATE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBKEMAIL">BKEMAIL</label>
             <div class="controls">
			    <input type="text" placeholder="BKEMAIL" id="aBKEMAIL" isNotNull="true" warnName="BKEMAIL" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCONTENT">CONTENT</label>
             <div class="controls">
			    <input type="text" placeholder="CONTENT" id="aCONTENT" isNotNull="true" warnName="CONTENT" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aPARTTYPE">PARTTYPE</label>
             <div class="controls">
			    <input type="text" placeholder="PARTTYPE" id="aPARTTYPE" isNotNull="true" warnName="PARTTYPE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBANKCATCODE">BANKCATCODE</label>
             <div class="controls">
			    <input type="text" placeholder="BANKCATCODE" id="aBANKCATCODE" isNotNull="true" warnName="BANKCATCODE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aHIGHPART">HIGHPART</label>
             <div class="controls">
			    <input type="text" placeholder="HIGHPART" id="aHIGHPART" isNotNull="true" warnName="HIGHPART" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aBEARBANKCODE">BEARBANKCODE</label>
             <div class="controls">
			    <input type="text" placeholder="BEARBANKCODE" id="aBEARBANKCODE" isNotNull="true" warnName="BEARBANKCODE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aCHARGEBANKCODE">CHARGEBANKCODE</label>
             <div class="controls">
			    <input type="text" placeholder="CHARGEBANKCODE" id="aCHARGEBANKCODE" isNotNull="true" warnName="CHARGEBANKCODE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aNODECODE">NODECODE</label>
             <div class="controls">
			    <input type="text" placeholder="NODECODE" id="aNODECODE" isNotNull="true" warnName="NODECODE" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
         <div class="control-group">
             <label class="control-label" for="aSIGN">SIGN</label>
             <div class="controls">
			    <input type="text" placeholder="SIGN" id="aSIGN" isNotNull="true" warnName="SIGN" />
                 <span class="help-inline text-red">*</span>
             </div>
         </div>
     </form>
 </div>
 <div class="modal-footer">
     <button type="button" data-dismiss="modal" class="btn btn-boo">取消</button>
     <button type="submit" class="btn btn-green" onclick="add()">保存</button>
 </div>
<script type="text/javascript">
	//执行添加
	function add(){
		var id = getVal("aId");
		var NBKCODE = getVal("aNBKCODE");
		var SABKCODE = getVal("aSABKCODE");
		var BNKCITY = getVal("aBNKCITY");
		var NBKNAME = getVal("aNBKNAME");
		var NBKSNAME = getVal("aNBKSNAME");
		var NBKADDRESS = getVal("aNBKADDRESS");
		var CNTTEL = getVal("aCNTTEL");
		var CNTPER = getVal("aCNTPER");
		var POSTCODE = getVal("aPOSTCODE");
		var NBKSTATE = getVal("aNBKSTATE");
		var BKEMAIL = getVal("aBKEMAIL");
		var CONTENT = getVal("aCONTENT");
		var PARTTYPE = getVal("aPARTTYPE");
		var BANKCATCODE = getVal("aBANKCATCODE");
		var HIGHPART = getVal("aHIGHPART");
		var BEARBANKCODE = getVal("aBEARBANKCODE");
		var CHARGEBANKCODE = getVal("aCHARGEBANKCODE");
		var NODECODE = getVal("aNODECODE");
		var SIGN = getVal("aSIGN");
		var flag = validateForm('addForm');
	    if (flag){ 
	        $.post("<c:url value='/openingBank/addOpeningBank'/>",
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
		              	tipOk("保存成功!");
		             } else {
		            	 tipError(result.message);
		             }
	              	$modal.modal('hide');
	        });
	    }
	}
</script>