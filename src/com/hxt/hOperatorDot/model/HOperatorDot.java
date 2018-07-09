package com.hxt.hOperatorDot.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zlb
 * @time	2016年08月06日 21:52:19
 */
public class HOperatorDot extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String operator_name;
		private Integer operator_id;
		private String operator_level;
		private String window_name;
		private String name;
		private String province_name;
		private String province_code;
		private String city_code;
		private String city_name;
		private String area_code;
		private String area_name;
		private Integer state;
		private Date createTime;
		private Integer full_invoice;
		private Integer add_invoice;
		private Integer adminId;
		private Date lastTime;
		private Integer lastAdminId;
		private String mobile;
		private String phone;
		private String phone_post;
		private Date work_start;
		private Date work_end;
		private String address;
		private String lastAdminName;
		private String adminName;
		
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
		public String getOperator_name() {
			return operator_name;
		}
	
		public void setOperator_name(String operator_name) {
			this.operator_name = operator_name;
		}
		public Integer getOperator_id() {
			return operator_id;
		}
	
		public void setOperator_id(Integer operator_id) {
			this.operator_id = operator_id;
		}
		public String getOperator_level() {
			return operator_level;
		}
	
		public void setOperator_level(String operator_level) {
			this.operator_level = operator_level;
		}
		public String getWindow_name() {
			return window_name;
		}
	
		public void setWindow_name(String window_name) {
			this.window_name = window_name;
		}
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
		public String getProvince_name() {
			return province_name;
		}
	
		public void setProvince_name(String province_name) {
			this.province_name = province_name;
		}
		public String getProvince_code() {
			return province_code;
		}
	
		public void setProvince_code(String province_code) {
			this.province_code = province_code;
		}
		public String getCity_code() {
			return city_code;
		}
	
		public void setCity_code(String city_code) {
			this.city_code = city_code;
		}
		public String getCity_name() {
			return city_name;
		}
	
		public void setCity_name(String city_name) {
			this.city_name = city_name;
		}
		public String getArea_code() {
			return area_code;
		}
	
		public void setArea_code(String area_code) {
			this.area_code = area_code;
		}
		public String getArea_name() {
			return area_name;
		}
	
		public void setArea_name(String area_name) {
			this.area_name = area_name;
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
		public Integer getFull_invoice() {
			return full_invoice;
		}
	
		public void setFull_invoice(Integer full_invoice) {
			this.full_invoice = full_invoice;
		}
		public Integer getAdd_invoice() {
			return add_invoice;
		}
	
		public void setAdd_invoice(Integer add_invoice) {
			this.add_invoice = add_invoice;
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
		public String getMobile() {
			return mobile;
		}
	
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getPhone() {
			return phone;
		}
	
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getPhone_post() {
			return phone_post;
		}
	
		public void setPhone_post(String phone_post) {
			this.phone_post = phone_post;
		}
		public Date getWork_start() {
			return work_start;
		}
	
		public void setWork_start(Date work_start) {
			this.work_start = work_start;
		}
		public Date getWork_end() {
			return work_end;
		}
	
		public void setWork_end(Date work_end) {
			this.work_end = work_end;
		}
		public String getAddress() {
			return address;
		}
	
		public void setAddress(String address) {
			this.address = address;
		}
}