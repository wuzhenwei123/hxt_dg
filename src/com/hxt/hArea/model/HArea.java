package com.hxt.hArea.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:12
 */
public class HArea extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String areaCode;
		private String areaName;
		private String cityCode;
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getAreaCode() {
			return areaCode;
		}
	
		public void setAreaCode(String areaCode) {
			this.areaCode = areaCode;
		}
		public String getAreaName() {
			return areaName;
		}
	
		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}
		public String getCityCode() {
			return cityCode;
		}
	
		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
}