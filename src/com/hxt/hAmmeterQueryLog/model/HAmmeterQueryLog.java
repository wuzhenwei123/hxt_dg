package com.hxt.hAmmeterQueryLog.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2016年09月20日 12:15:06
 */
public class HAmmeterQueryLog extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private Date createTime;
		private String ammeterNo;
		private String phone;
		private String ip;
		private String ammeter_address;
		private String ammeter_name;
		private String fee;
		private String znFee;
		private String totalFee;
		private String remark1;
		private String remark2;
		private String remark3;
	    private Date createTime1;
	    private Date createTime2;
	    
		public Date getCreateTime2() {
			return createTime2;
		}

		public void setCreateTime2(Date createTime2) {
			this.createTime2 = createTime2;
		}

		public Date getCreateTime1() {
			return createTime1;
		}

		public void setCreateTime1(Date createTime1) {
			this.createTime1 = createTime1;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public Date getCreateTime() {
			return createTime;
		}
	
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public String getAmmeterNo() {
			return ammeterNo;
		}
	
		public void setAmmeterNo(String ammeterNo) {
			this.ammeterNo = ammeterNo;
		}
		public String getPhone() {
			return phone;
		}
	
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getIp() {
			return ip;
		}
	
		public void setIp(String ip) {
			this.ip = ip;
		}
		public String getAmmeter_address() {
			return ammeter_address;
		}
	
		public void setAmmeter_address(String ammeter_address) {
			this.ammeter_address = ammeter_address;
		}
		public String getAmmeter_name() {
			return ammeter_name;
		}
	
		public void setAmmeter_name(String ammeter_name) {
			this.ammeter_name = ammeter_name;
		}
		public String getFee() {
			return fee;
		}
	
		public void setFee(String fee) {
			this.fee = fee;
		}
		public String getZnFee() {
			return znFee;
		}
	
		public void setZnFee(String znFee) {
			this.znFee = znFee;
		}
		public String getTotalFee() {
			return totalFee;
		}
	
		public void setTotalFee(String totalFee) {
			this.totalFee = totalFee;
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