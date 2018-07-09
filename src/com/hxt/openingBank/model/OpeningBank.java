package com.hxt.openingBank.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2016年09月09日 00:01:16
 */
public class OpeningBank extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String NBKCODE;
		private String SABKCODE;
		private String BNKCITY;
		private String NBKNAME;
		private String NBKSNAME;
		private String NBKADDRESS;
		private String CNTTEL;
		private String CNTPER;
		private String POSTCODE;
		private String NBKSTATE;
		private String BKEMAIL;
		private String CONTENT;
		private String PARTTYPE;
		private String BANKCATCODE;
		private String HIGHPART;
		private String BEARBANKCODE;
		private String CHARGEBANKCODE;
		private String NODECODE;
		private String SIGN;
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getNBKCODE() {
			return NBKCODE;
		}
	
		public void setNBKCODE(String NBKCODE) {
			this.NBKCODE = NBKCODE;
		}
		public String getSABKCODE() {
			return SABKCODE;
		}
	
		public void setSABKCODE(String SABKCODE) {
			this.SABKCODE = SABKCODE;
		}
		public String getBNKCITY() {
			return BNKCITY;
		}
	
		public void setBNKCITY(String BNKCITY) {
			this.BNKCITY = BNKCITY;
		}
		public String getNBKNAME() {
			return NBKNAME;
		}
	
		public void setNBKNAME(String NBKNAME) {
			this.NBKNAME = NBKNAME;
		}
		public String getNBKSNAME() {
			return NBKSNAME;
		}
	
		public void setNBKSNAME(String NBKSNAME) {
			this.NBKSNAME = NBKSNAME;
		}
		public String getNBKADDRESS() {
			return NBKADDRESS;
		}
	
		public void setNBKADDRESS(String NBKADDRESS) {
			this.NBKADDRESS = NBKADDRESS;
		}
		public String getCNTTEL() {
			return CNTTEL;
		}
	
		public void setCNTTEL(String CNTTEL) {
			this.CNTTEL = CNTTEL;
		}
		public String getCNTPER() {
			return CNTPER;
		}
	
		public void setCNTPER(String CNTPER) {
			this.CNTPER = CNTPER;
		}
		public String getPOSTCODE() {
			return POSTCODE;
		}
	
		public void setPOSTCODE(String POSTCODE) {
			this.POSTCODE = POSTCODE;
		}
		public String getNBKSTATE() {
			return NBKSTATE;
		}
	
		public void setNBKSTATE(String NBKSTATE) {
			this.NBKSTATE = NBKSTATE;
		}
		public String getBKEMAIL() {
			return BKEMAIL;
		}
	
		public void setBKEMAIL(String BKEMAIL) {
			this.BKEMAIL = BKEMAIL;
		}
		public String getCONTENT() {
			return CONTENT;
		}
	
		public void setCONTENT(String CONTENT) {
			this.CONTENT = CONTENT;
		}
		public String getPARTTYPE() {
			return PARTTYPE;
		}
	
		public void setPARTTYPE(String PARTTYPE) {
			this.PARTTYPE = PARTTYPE;
		}
		public String getBANKCATCODE() {
			return BANKCATCODE;
		}
	
		public void setBANKCATCODE(String BANKCATCODE) {
			this.BANKCATCODE = BANKCATCODE;
		}
		public String getHIGHPART() {
			return HIGHPART;
		}
	
		public void setHIGHPART(String HIGHPART) {
			this.HIGHPART = HIGHPART;
		}
		public String getBEARBANKCODE() {
			return BEARBANKCODE;
		}
	
		public void setBEARBANKCODE(String BEARBANKCODE) {
			this.BEARBANKCODE = BEARBANKCODE;
		}
		public String getCHARGEBANKCODE() {
			return CHARGEBANKCODE;
		}
	
		public void setCHARGEBANKCODE(String CHARGEBANKCODE) {
			this.CHARGEBANKCODE = CHARGEBANKCODE;
		}
		public String getNODECODE() {
			return NODECODE;
		}
	
		public void setNODECODE(String NODECODE) {
			this.NODECODE = NODECODE;
		}
		public String getSIGN() {
			return SIGN;
		}
	
		public void setSIGN(String SIGN) {
			this.SIGN = SIGN;
		}
}