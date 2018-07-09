package com.hxt.hMessagePhone.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2017年06月22日 14:48:22
 */
public class HMessagePhone extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String phone;
		private String remark1;
		private String remark2;
		private String remark3;
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getPhone() {
			return phone;
		}
	
		public void setPhone(String phone) {
			this.phone = phone;
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