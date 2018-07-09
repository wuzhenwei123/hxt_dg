package com.hxt.hBankInfo.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2016年10月20日 17:50:50
 */
public class HBankInfo extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String name;
		private String code;
		private String bankNum;
		private String clearBankNum;
		private String remark1;
		private String remark2;
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}
	
		public void setCode(String code) {
			this.code = code;
		}
		public String getBankNum() {
			return bankNum;
		}
	
		public void setBankNum(String bankNum) {
			this.bankNum = bankNum;
		}
		public String getClearBankNum() {
			return clearBankNum;
		}
	
		public void setClearBankNum(String clearBankNum) {
			this.clearBankNum = clearBankNum;
		}
		public String getRemark1() {
			return remark1;
		}
	
		public void setRemark1(String remark1) {
			this.remark1 = remark1;
		}
		public String getRemark2() {
			return remark2;
		}
	
		public void setRemark2(String remark2) {
			this.remark2 = remark2;
		}
}