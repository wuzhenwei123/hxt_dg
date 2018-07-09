package com.hxt.hProxyMessageLog.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2016年11月03日 09:29:09
 */
public class HProxyMessageLog extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String proxyName;
		private String proxyPhone;
		private Date createYime;
		private String contractNumber;
		private Date contractStartTime;
		private Date contractEndTime;
		private Integer remindStartDate;
		private Integer remindEndDate;
		private String bank_number;
		private String info;
		private String remark1;
		private String remark2;
		private String remark3;
		private Integer cId;
		private Integer userId;
		private Integer state;
		private String userNumber;
		private String proxyAddress;
		private String proxyCode;
		private String payBankNumber;
		private String payName;
		private Integer payCardStyle;
		private String payCard;
		private String payPhoneNumber;
		private String payMail;
		private Integer sex;
		private Integer checkState;
		private String hetongUrl;
		private String chexiaoUrl;
		private String msg;
		private String bank_name;
		private String payAccountName;
		private String biangengUrl;
		
		private String cName;//隶属单位名称
		
		private Date nowDate;
		
		public Integer getcId() {
			return cId;
		}

		public void setcId(Integer cId) {
			this.cId = cId;
		}

		public String getcName() {
			return cName;
		}

		public void setcName(String cName) {
			this.cName = cName;
		}

		public Date getNowDate() {
			return nowDate;
		}

		public void setNowDate(Date nowDate) {
			this.nowDate = nowDate;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getProxyName() {
			return proxyName;
		}
	
		public void setProxyName(String proxyName) {
			this.proxyName = proxyName;
		}
		public String getProxyPhone() {
			return proxyPhone;
		}
	
		public void setProxyPhone(String proxyPhone) {
			this.proxyPhone = proxyPhone;
		}
		public Date getCreateYime() {
			return createYime;
		}
	
		public void setCreateYime(Date createYime) {
			this.createYime = createYime;
		}
		public String getContractNumber() {
			return contractNumber;
		}
	
		public void setContractNumber(String contractNumber) {
			this.contractNumber = contractNumber;
		}
		public Date getContractStartTime() {
			return contractStartTime;
		}
	
		public void setContractStartTime(Date contractStartTime) {
			this.contractStartTime = contractStartTime;
		}
		public Date getContractEndTime() {
			return contractEndTime;
		}
	
		public void setContractEndTime(Date contractEndTime) {
			this.contractEndTime = contractEndTime;
		}
		public Integer getRemindStartDate() {
			return remindStartDate;
		}
	
		public void setRemindStartDate(Integer remindStartDate) {
			this.remindStartDate = remindStartDate;
		}
		public Integer getRemindEndDate() {
			return remindEndDate;
		}
	
		public void setRemindEndDate(Integer remindEndDate) {
			this.remindEndDate = remindEndDate;
		}
		public String getBank_number() {
			return bank_number;
		}
	
		public void setBank_number(String bank_number) {
			this.bank_number = bank_number;
		}
		public String getInfo() {
			return info;
		}
	
		public void setInfo(String info) {
			this.info = info;
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
		public Integer getCId() {
			return cId;
		}
	
		public void setCId(Integer cId) {
			this.cId = cId;
		}
		public Integer getUserId() {
			return userId;
		}
	
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public Integer getState() {
			return state;
		}
	
		public void setState(Integer state) {
			this.state = state;
		}
		public String getUserNumber() {
			return userNumber;
		}
	
		public void setUserNumber(String userNumber) {
			this.userNumber = userNumber;
		}
		public String getProxyAddress() {
			return proxyAddress;
		}
	
		public void setProxyAddress(String proxyAddress) {
			this.proxyAddress = proxyAddress;
		}
		public String getProxyCode() {
			return proxyCode;
		}
	
		public void setProxyCode(String proxyCode) {
			this.proxyCode = proxyCode;
		}
		public String getPayBankNumber() {
			return payBankNumber;
		}
	
		public void setPayBankNumber(String payBankNumber) {
			this.payBankNumber = payBankNumber;
		}
		public String getPayName() {
			return payName;
		}
	
		public void setPayName(String payName) {
			this.payName = payName;
		}
		public Integer getPayCardStyle() {
			return payCardStyle;
		}
	
		public void setPayCardStyle(Integer payCardStyle) {
			this.payCardStyle = payCardStyle;
		}
		public String getPayCard() {
			return payCard;
		}
	
		public void setPayCard(String payCard) {
			this.payCard = payCard;
		}
		public String getPayPhoneNumber() {
			return payPhoneNumber;
		}
	
		public void setPayPhoneNumber(String payPhoneNumber) {
			this.payPhoneNumber = payPhoneNumber;
		}
		public String getPayMail() {
			return payMail;
		}
	
		public void setPayMail(String payMail) {
			this.payMail = payMail;
		}
		public Integer getSex() {
			return sex;
		}
	
		public void setSex(Integer sex) {
			this.sex = sex;
		}
		public Integer getCheckState() {
			return checkState;
		}
	
		public void setCheckState(Integer checkState) {
			this.checkState = checkState;
		}
		public String getHetongUrl() {
			return hetongUrl;
		}
	
		public void setHetongUrl(String hetongUrl) {
			this.hetongUrl = hetongUrl;
		}
		public String getChexiaoUrl() {
			return chexiaoUrl;
		}
	
		public void setChexiaoUrl(String chexiaoUrl) {
			this.chexiaoUrl = chexiaoUrl;
		}
		public String getMsg() {
			return msg;
		}
	
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public String getBank_name() {
			return bank_name;
		}
	
		public void setBank_name(String bank_name) {
			this.bank_name = bank_name;
		}
		public String getPayAccountName() {
			return payAccountName;
		}
	
		public void setPayAccountName(String payAccountName) {
			this.payAccountName = payAccountName;
		}
		public String getBiangengUrl() {
			return biangengUrl;
		}
	
		public void setBiangengUrl(String biangengUrl) {
			this.biangengUrl = biangengUrl;
		}
}