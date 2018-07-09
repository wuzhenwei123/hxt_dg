package com.hxt.hRegGuliSend.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2017年04月06日 18:01:33
 */
public class HRegGuliSend extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private Date sendTime;
		private Date createTime;
		private Integer companyId;
		private String companyName;
		private String ammeter;
		private Integer agentTwoId;
		private String agentTwoName;
		private Integer agentId;
		private String agentName;
		private Integer guliId;
		private Double fee;
		private Integer state;
		private String info;
		
		private Date c_create_time;
		private String contact_phone;
		
		public String getContact_phone() {
			return contact_phone;
		}

		public void setContact_phone(String contact_phone) {
			this.contact_phone = contact_phone;
		}

		public Date getC_create_time() {
			return c_create_time;
		}

		public void setC_create_time(Date c_create_time) {
			this.c_create_time = c_create_time;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public Date getSendTime() {
			return sendTime;
		}
	
		public void setSendTime(Date sendTime) {
			this.sendTime = sendTime;
		}
		public Date getCreateTime() {
			return createTime;
		}
	
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public Integer getCompanyId() {
			return companyId;
		}
	
		public void setCompanyId(Integer companyId) {
			this.companyId = companyId;
		}
		public String getCompanyName() {
			return companyName;
		}
	
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		public String getAmmeter() {
			return ammeter;
		}
	
		public void setAmmeter(String ammeter) {
			this.ammeter = ammeter;
		}
		public Integer getAgentTwoId() {
			return agentTwoId;
		}
	
		public void setAgentTwoId(Integer agentTwoId) {
			this.agentTwoId = agentTwoId;
		}
		public String getAgentTwoName() {
			return agentTwoName;
		}
	
		public void setAgentTwoName(String agentTwoName) {
			this.agentTwoName = agentTwoName;
		}
		public Integer getAgentId() {
			return agentId;
		}
	
		public void setAgentId(Integer agentId) {
			this.agentId = agentId;
		}
		public String getAgentName() {
			return agentName;
		}
	
		public void setAgentName(String agentName) {
			this.agentName = agentName;
		}
		public Integer getGuliId() {
			return guliId;
		}
	
		public void setGuliId(Integer guliId) {
			this.guliId = guliId;
		}
		public Double getFee() {
			return fee;
		}
	
		public void setFee(Double fee) {
			this.fee = fee;
		}
		public Integer getState() {
			return state;
		}
	
		public void setState(Integer state) {
			this.state = state;
		}
		public String getInfo() {
			return info;
		}
	
		public void setInfo(String info) {
			this.info = info;
		}
}