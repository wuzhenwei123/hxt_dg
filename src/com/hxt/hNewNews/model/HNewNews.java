package com.hxt.hNewNews.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zhangyiyang
 * @time	2016年08月01日 21:15:26
 */
public class HNewNews extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String title;
		private String content;
		private Date createTime;
		private Integer createId;
		private Integer state;
		private String source;
		private Integer sortId;
		private String imgUrl;
		
		public String getImgUrl() {
			return imgUrl;
		}

		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}

		public Integer getSortId() {
			return sortId;
		}

		public void setSortId(Integer sortId) {
			this.sortId = sortId;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
	
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
	
		public void setContent(String content) {
			this.content = content;
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
		public Integer getState() {
			return state;
		}
	
		public void setState(Integer state) {
			this.state = state;
		}
		public String getSource() {
			return source;
		}
	
		public void setSource(String source) {
			this.source = source;
		}
}