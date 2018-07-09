package com.hxt.hCity.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:23
 */
public class HCity extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String cityCode;
		private String cityName;
		private String provinceCode;
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getCityCode() {
			return cityCode;
		}
	
		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
		public String getCityName() {
			return cityName;
		}
	
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public String getProvinceCode() {
			return provinceCode;
		}
	
		public void setProvinceCode(String provinceCode) {
			this.provinceCode = provinceCode;
		}
}