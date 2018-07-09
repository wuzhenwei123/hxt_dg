package com.hxt.hPersonType.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zlb
 * @time	2016年08月06日 14:03:07
 */
public class HPersonType extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String name;
		private Integer state;
		private Date createTime;
		private Integer adminId;
		private Date lastTime;
		private Integer lastAdminId;
		private String remark;
		private String adminName;
		private String lastAdminName;
		
		public String getLastAdminName() {
			return lastAdminName;
		}

		public void setLastAdminName(String lastAdminName) {
			this.lastAdminName = lastAdminName;
		}

		public String getAdminName() {
			return adminName;
		}

		public void setAdminName(String adminName) {
			this.adminName = adminName;
		}

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
		public Integer getState() {
			return state;
		}
	
		public void setState(Integer state) {
			this.state = state;
		}
		public Date getCreateTime() {
			return createTime;
		}
	
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public Integer getAdminId() {
			return adminId;
		}
	
		public void setAdminId(Integer adminId) {
			this.adminId = adminId;
		}
		public Date getLastTime() {
			return lastTime;
		}
	
		public void setLastTime(Date lastTime) {
			this.lastTime = lastTime;
		}
		public Integer getLastAdminId() {
			return lastAdminId;
		}
	
		public void setLastAdminId(Integer lastAdminId) {
			this.lastAdminId = lastAdminId;
		}
		public String getRemark() {
			return remark;
		}
	
		public void setRemark(String remark) {
			this.remark = remark;
		}
}