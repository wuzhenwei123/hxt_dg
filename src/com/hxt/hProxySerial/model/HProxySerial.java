package com.hxt.hProxySerial.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2016年09月08日 22:18:15
 */
public class HProxySerial extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String payBankNumber;
		private String userNumber;
		private String contractNumber;
		private String bank_number;
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getPayBankNumber() {
			return payBankNumber;
		}
	
		public void setPayBankNumber(String payBankNumber) {
			this.payBankNumber = payBankNumber;
		}
		public String getUserNumber() {
			return userNumber;
		}
	
		public void setUserNumber(String userNumber) {
			this.userNumber = userNumber;
		}
		public String getContractNumber() {
			return contractNumber;
		}
	
		public void setContractNumber(String contractNumber) {
			this.contractNumber = contractNumber;
		}
		public String getBank_number() {
			return bank_number;
		}
	
		public void setBank_number(String bank_number) {
			this.bank_number = bank_number;
		}
}