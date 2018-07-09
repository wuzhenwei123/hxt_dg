package com.hxt.hOperator.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zlb
 * @time	2016年08月05日 14:30:13
 */
public class HOperator extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String full_name;
		private String name;
		private String province_name;
		private String province_code;
		private String city_code;
		private String city_name;
		private String area_code;
		private String area_name;
		private Integer state;
		private Date createTime;
		private Integer adminId;
		private Date lastTime;
		private Integer lastAdminId;
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
		public String getFull_name() {
			return full_name;
		}
	
		public void setFull_name(String full_name) {
			this.full_name = full_name;
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
}