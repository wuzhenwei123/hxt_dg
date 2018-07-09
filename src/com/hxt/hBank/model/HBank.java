package com.hxt.hBank.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2016年08月31日 00:17:11
 */
public class HBank extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String name;
		private Integer state;
		private String bigImg;
		private String smallImg;
		private String docUrl;
		private String remark1;
		private String remark2;
		private String remark3;
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
		public Integer getState() {
			return state;
		}
	
		public void setState(Integer state) {
			this.state = state;
		}
		public String getBigImg() {
			return bigImg;
		}
	
		public void setBigImg(String bigImg) {
			this.bigImg = bigImg;
		}
		public String getSmallImg() {
			return smallImg;
		}
	
		public void setSmallImg(String smallImg) {
			this.smallImg = smallImg;
		}
		public String getDocUrl() {
			return docUrl;
		}
	
		public void setDocUrl(String docUrl) {
			this.docUrl = docUrl;
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