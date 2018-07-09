package com.hxt.hDispatchRecordC.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2016年07月21日 11:17:01
 */
public class HDispatchRecordC extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String contact_phone;
		private String ammeter_no;
		private Long totalFee;
		private Integer status;
		private Date createTime;
		private Date updateTime;
		private String content;
		private String remark1;
		private String remark2;
		private String remark3;
		private Integer c_id;
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getContact_phone() {
			return contact_phone;
		}
	
		public void setContact_phone(String contact_phone) {
			this.contact_phone = contact_phone;
		}
		public String getAmmeter_no() {
			return ammeter_no;
		}
	
		public void setAmmeter_no(String ammeter_no) {
			this.ammeter_no = ammeter_no;
		}
		public Long getTotalFee() {
			return totalFee;
		}
	
		public void setTotalFee(Long totalFee) {
			this.totalFee = totalFee;
		}
		public Integer getStatus() {
			return status;
		}
	
		public void setStatus(Integer status) {
			this.status = status;
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
		public String getContent() {
			return content;
		}
	
		public void setContent(String content) {
			this.content = content;
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
		public Integer getC_id() {
			return c_id;
		}
	
		public void setC_id(Integer c_id) {
			this.c_id = c_id;
		}
}