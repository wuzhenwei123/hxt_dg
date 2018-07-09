package com.hxt.hDiaodan.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2016年09月22日 15:07:03
 */
public class HDiaodan extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String electricNum;
		private Integer style;
		private String content;
		private String remark1;
		private String remark2;
		private String remark3;
		private String remark4;
		private Date createTime;
		
		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getElectricNum() {
			return electricNum;
		}
	
		public void setElectricNum(String electricNum) {
			this.electricNum = electricNum;
		}
		public Integer getStyle() {
			return style;
		}
	
		public void setStyle(Integer style) {
			this.style = style;
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
		public String getRemark4() {
			return remark4;
		}
	
		public void setRemark4(String remark4) {
			this.remark4 = remark4;
		}
		public Date getCreateTime() {
			return createTime;
		}
	
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
}