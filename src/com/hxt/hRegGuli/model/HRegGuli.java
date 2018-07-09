package com.hxt.hRegGuli.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2017年04月06日 17:50:28
 */
public class HRegGuli extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String name;
		private Date startTime;
		private Date endTime;
		private Double fee;
		private Integer isDefault;
		private Integer state;
		private Date createTime;
		private Date updateTime;
		private Date stopTime;
		private Integer createId;
		private Integer updateId;
		private String info;
		
		private String createName;
		private String updateName;
		
		private Integer flag;
		
		public Integer getFlag() {
			return flag;
		}

		public void setFlag(Integer flag) {
			this.flag = flag;
		}

		public String getCreateName() {
			return createName;
		}

		public void setCreateName(String createName) {
			this.createName = createName;
		}

		public String getUpdateName() {
			return updateName;
		}

		public void setUpdateName(String updateName) {
			this.updateName = updateName;
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
		public Date getStartTime() {
			return startTime;
		}
	
		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}
		public Date getEndTime() {
			return endTime;
		}
	
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
		public Double getFee() {
			return fee;
		}
	
		public void setFee(Double fee) {
			this.fee = fee;
		}
		public Integer getIsDefault() {
			return isDefault;
		}
	
		public void setIsDefault(Integer isDefault) {
			this.isDefault = isDefault;
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
		public Date getUpdateTime() {
			return updateTime;
		}
	
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
		public Date getStopTime() {
			return stopTime;
		}
	
		public void setStopTime(Date stopTime) {
			this.stopTime = stopTime;
		}
		public Integer getCreateId() {
			return createId;
		}
	
		public void setCreateId(Integer createId) {
			this.createId = createId;
		}
		public Integer getUpdateId() {
			return updateId;
		}
	
		public void setUpdateId(Integer updateId) {
			this.updateId = updateId;
		}
		public String getInfo() {
			return info;
		}
	
		public void setInfo(String info) {
			this.info = info;
		}
}