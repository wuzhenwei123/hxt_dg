package com.hxt.hVerificate.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2015年09月30日 14:11:21
 */
public class HVerificate extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String openId;
		private String agentOpenId;
		private String phone;
		private Integer state;
		private Integer level;
		private Date createTime;
		private String remark1;
		private String remark2;
		private String remark3;
		
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
		public String getAgentOpenId() {
			return agentOpenId;
		}
	
		public void setAgentOpenId(String agentOpenId) {
			this.agentOpenId = agentOpenId;
		}
		public String getPhone() {
			return phone;
		}
	
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public Integer getState() {
			return state;
		}
	
		public void setState(Integer state) {
			this.state = state;
		}
		public Integer getLevel() {
			return level;
		}
	
		public void setLevel(Integer level) {
			this.level = level;
		}
		public Date getCreateTime() {
			return createTime;
		}
	
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
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