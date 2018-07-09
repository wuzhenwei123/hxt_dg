package com.hxt.hProvince.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zhangyiyang
 * @time	2016年08月02日 22:11:34
 */
public class HProvince extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String provinceCode;
		private String provinceName;
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getProvinceCode() {
			return provinceCode;
		}
	
		public void setProvinceCode(String provinceCode) {
			this.provinceCode = provinceCode;
		}
		public String getProvinceName() {
			return provinceName;
		}
	
		public void setProvinceName(String provinceName) {
			this.provinceName = provinceName;
		}
}