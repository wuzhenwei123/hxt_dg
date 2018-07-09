package com.hxt.hUserAccount.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zlb
 * @time	2016年08月16日 10:58:52
 */
public class HUserAccount extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String openId;
		private java.math.BigDecimal totalFee;
		private Date createTime;
		private Integer status;
		private String oneAgentOpenId;
		private String twoAgentOpenId;
		private String oneAgentName;
		private String twoAgentName;
		private String nickName;
		private String phone;
		private String mobile;
		private String company_name;
		private Integer role_id;
		private String role_name;
		private Integer fp_state;
		private String remark1;
		private String remark2;
		private String remark3;
		private Integer servicerId;
		private String servicerName;
		
		
		public Integer getServicerId() {
			return servicerId;
		}

		public void setServicerId(Integer servicerId) {
			this.servicerId = servicerId;
		}

		public String getServicerName() {
			return servicerName;
		}

		public void setServicerName(String servicerName) {
			this.servicerName = servicerName;
		}

		public String getRole_name() {
			return role_name;
		}

		public void setRole_name(String role_name) {
			this.role_name = role_name;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getOpenId() {
			return openId;
		}
	
		public void setOpenId(String openId) {
			this.openId = openId;
		}
		public java.math.BigDecimal getTotalFee() {
			return totalFee;
		}
	
		public void setTotalFee(java.math.BigDecimal totalFee) {
			this.totalFee = totalFee;
		}
		public Date getCreateTime() {
			return createTime;
		}
	
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public Integer getStatus() {
			return status;
		}
	
		public void setStatus(Integer status) {
			this.status = status;
		}
		public String getOneAgentOpenId() {
			return oneAgentOpenId;
		}
	
		public void setOneAgentOpenId(String oneAgentOpenId) {
			this.oneAgentOpenId = oneAgentOpenId;
		}
		public String getTwoAgentOpenId() {
			return twoAgentOpenId;
		}
	
		public void setTwoAgentOpenId(String twoAgentOpenId) {
			this.twoAgentOpenId = twoAgentOpenId;
		}
		public String getOneAgentName() {
			return oneAgentName;
		}
	
		public void setOneAgentName(String oneAgentName) {
			this.oneAgentName = oneAgentName;
		}
		public String getTwoAgentName() {
			return twoAgentName;
		}
	
		public void setTwoAgentName(String twoAgentName) {
			this.twoAgentName = twoAgentName;
		}
		public String getNickName() {
			return nickName;
		}
	
		public void setNickName(String nickName) {
			this.nickName = nickName;
		}
		public String getPhone() {
			return phone;
		}
	
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getMobile() {
			return mobile;
		}
	
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getCompany_name() {
			return company_name;
		}
	
		public void setCompany_name(String company_name) {
			this.company_name = company_name;
		}
		public Integer getRole_id() {
			return role_id;
		}
	
		public void setRole_id(Integer role_id) {
			this.role_id = role_id;
		}
		public Integer getFp_state() {
			return fp_state;
		}
	
		public void setFp_state(Integer fp_state) {
			this.fp_state = fp_state;
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