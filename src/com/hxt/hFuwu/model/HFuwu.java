package com.hxt.hFuwu.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zlb
 * @time	2015年09月19日 19:01:41
 */
public class HFuwu extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String content;
		private Integer state;
		private Date createTime;
		private Integer createId;
		private String adminName;
		
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
		public String getContent() {
			return content;
		}
	
		public void setContent(String content) {
			this.content = content;
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
		public Integer getCreateId() {
			return createId;
		}
	
		public void setCreateId(Integer createId) {
			this.createId = createId;
		}
}