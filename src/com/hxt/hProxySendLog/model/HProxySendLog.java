package com.hxt.hProxySendLog.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2016年11月17日 07:55:47
 */
public class HProxySendLog extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private Integer style;
		private String contractNumber;
		private String userNumber;
		private String content;
		private String bank_number;
		private String payBankNumber;
		private String remark1;
		private String remark2;
		private String remark3;
		private Integer sendStyle;
		private Date createTime;
		
		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getStyle() {
			return style;
		}
	
		public void setStyle(Integer style) {
			this.style = style;
		}
		public String getContractNumber() {
			return contractNumber;
		}
	
		public void setContractNumber(String contractNumber) {
			this.contractNumber = contractNumber;
		}
		public String getUserNumber() {
			return userNumber;
		}
	
		public void setUserNumber(String userNumber) {
			this.userNumber = userNumber;
		}
		public String getContent() {
			return content;
		}
	
		public void setContent(String content) {
			this.content = content;
		}
		public String getBank_number() {
			return bank_number;
		}
	
		public void setBank_number(String bank_number) {
			this.bank_number = bank_number;
		}
		public String getPayBankNumber() {
			return payBankNumber;
		}
	
		public void setPayBankNumber(String payBankNumber) {
			this.payBankNumber = payBankNumber;
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
		public String getRemark3() {
			return remark3;
		}
	
		public void setRemark3(String remark3) {
			this.remark3 = remark3;
		}
		public Integer getSendStyle() {
			return sendStyle;
		}
	
		public void setSendStyle(Integer sendStyle) {
			this.sendStyle = sendStyle;
		}
}