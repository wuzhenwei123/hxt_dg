package com.hxt.hPayGuli.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2017年04月08日 17:00:50
 */
public class HPayGuli extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String trade_code;
		private String name;
		private Integer style;
		private Date startTime;
		private Date endTime;
		private Integer type;
		private Double fee;
		private Double rate;
		private Integer state;
		private Integer is_default;
		private Date createTime;
		private Integer createId;
		private Date updateTime;
		private Integer updateId;
		private Date stopTime;
		private String info;
		private String createName;
		private String updateName;
		private Integer opObject;
		
		private Integer flag;
		
		public Integer getFlag() {
			return flag;
		}

		public void setFlag(Integer flag) {
			this.flag = flag;
		}
		
		public Integer getOpObject() {
			return opObject;
		}

		public void setOpObject(Integer opObject) {
			this.opObject = opObject;
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
		public String getTrade_code() {
			return trade_code;
		}
	
		public void setTrade_code(String trade_code) {
			this.trade_code = trade_code;
		}
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
		public Integer getStyle() {
			return style;
		}
	
		public void setStyle(Integer style) {
			this.style = style;
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
		public Integer getType() {
			return type;
		}
	
		public void setType(Integer type) {
			this.type = type;
		}
		public Double getFee() {
			return fee;
		}
	
		public void setFee(Double fee) {
			this.fee = fee;
		}
		public Double getRate() {
			return rate;
		}
	
		public void setRate(Double rate) {
			this.rate = rate;
		}
		public Integer getState() {
			return state;
		}
	
		public void setState(Integer state) {
			this.state = state;
		}
		public Integer getIs_default() {
			return is_default;
		}
	
		public void setIs_default(Integer is_default) {
			this.is_default = is_default;
		}
		public Date getCreateTime() {
			return createTime;
		}
	
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public Integer getCreateId() {
			return createId;
		}
	
		public void setCreateId(Integer createId) {
			this.createId = createId;
		}
		public Date getUpdateTime() {
			return updateTime;
		}
	
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
		public Integer getUpdateId() {
			return updateId;
		}
	
		public void setUpdateId(Integer updateId) {
			this.updateId = updateId;
		}
		public Date getStopTime() {
			return stopTime;
		}
	
		public void setStopTime(Date stopTime) {
			this.stopTime = stopTime;
		}
		public String getInfo() {
			return info;
		}
	
		public void setInfo(String info) {
			this.info = info;
		}
}