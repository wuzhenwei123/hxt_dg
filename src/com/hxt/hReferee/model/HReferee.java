package com.hxt.hReferee.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2015年09月04日 14:33:55
 */
public class HReferee extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String tjrId;
		private String beituijianId;
		private String ticket;
		private Integer style;//推荐人类型：1临时/2永久
		private Date createTime;
		private String remark1;
		private String remark2;
		private String remark3;
		private Integer status;
		
		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Integer getStyle() {
			return style;
		}

		public void setStyle(Integer style) {
			this.style = style;
		}
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getTjrId() {
			return tjrId;
		}
	
		public void setTjrId(String tjrId) {
			this.tjrId = tjrId;
		}
		public String getBeituijianId() {
			return beituijianId;
		}
	
		public void setBeituijianId(String beituijianId) {
			this.beituijianId = beituijianId;
		}
		public String getTicket() {
			return ticket;
		}
	
		public void setTicket(String ticket) {
			this.ticket = ticket;
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