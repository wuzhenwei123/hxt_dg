package com.hxt.hAbout.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	keeny
 * @time	2015年08月21日 13:26:17
 */
public class HAbout extends BaseModel implements java.io.Serializable{
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
		private String nickName;
		
		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getAdminName() {
			return adminName;
		}

		public void setAdminName(String adminName) {
			this.adminName = adminName;
		}

		public Integer getCreateId() {
			return createId;
		}

		public void setCreateId(Integer createId) {
			this.createId = createId;
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
}