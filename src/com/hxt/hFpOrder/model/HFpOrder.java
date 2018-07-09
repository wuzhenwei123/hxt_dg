package com.hxt.hFpOrder.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2016年01月12日 22:02:34
 */
public class HFpOrder extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private Integer fpId;
		private Integer orderId;
		private Date createTime;
		private Integer status;
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getFpId() {
			return fpId;
		}
	
		public void setFpId(Integer fpId) {
			this.fpId = fpId;
		}
		public Integer getOrderId() {
			return orderId;
		}
	
		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
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
}