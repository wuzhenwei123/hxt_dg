package com.hxt.hLoginLog.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2016年09月19日 11:08:42
 */
public class HLoginLog extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String adminName;
		private Date loginTIme;
		private String loginIp;
		private Integer deviceType;
		private String remark1;
		private String remark2;
		private String remark3;
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getAdminName() {
			return adminName;
		}
	
		public void setAdminName(String adminName) {
			this.adminName = adminName;
		}
		public Date getLoginTIme() {
			return loginTIme;
		}
	
		public void setLoginTIme(Date loginTIme) {
			this.loginTIme = loginTIme;
		}
		public String getLoginIp() {
			return loginIp;
		}
	
		public void setLoginIp(String loginIp) {
			this.loginIp = loginIp;
		}
		public Integer getDeviceType() {
			return deviceType;
		}
	
		public void setDeviceType(Integer deviceType) {
			this.deviceType = deviceType;
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