package com.hxt.hDispatchRecord.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2015年11月18日 22:41:22
 */
public class HDispatchRecord extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String ammeter_no;
		private Integer totalFee;
		private String accountName;
		private String address;
		private Integer status;
		private Date createTime;
		private Date updateTime;
		private String remark1;
		private String remark2;
		private String remark3;
		
		private String accountFee;//应交金额
		private String lateFee;//滞纳金
		private String accountTime;//对账日期
		
		
		public String getAccountFee() {
			return accountFee;
		}

		public void setAccountFee(String accountFee) {
			this.accountFee = accountFee;
		}

		public String getLateFee() {
			return lateFee;
		}

		public void setLateFee(String lateFee) {
			this.lateFee = lateFee;
		}

		public String getAccountTime() {
			return accountTime;
		}

		public void setAccountTime(String accountTime) {
			this.accountTime = accountTime;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getAmmeter_no() {
			return ammeter_no;
		}
	
		public void setAmmeter_no(String ammeter_no) {
			this.ammeter_no = ammeter_no;
		}
		public Integer getTotalFee() {
			return totalFee;
		}
	
		public void setTotalFee(Integer totalFee) {
			this.totalFee = totalFee;
		}
		public String getAccountName() {
			return accountName;
		}
	
		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}
		public String getAddress() {
			return address;
		}
	
		public void setAddress(String address) {
			this.address = address;
		}
		public Integer getStatus() {
			return status;
		}
	
		public void setStatus(Integer status) {
			this.status = status;
		}
		public Date getCreateTime() {
			return createTime;
		}
	
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public Date getUpdateTime() {
			return updateTime;
		}
	
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
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
}