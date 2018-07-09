package com.hxt.hLbImg.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zhanglibo
 * @time	2015年09月22日 10:05:40
 */
public class HLbImg extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String img_name;
		private String img_path;
		private String link_address;
		private Integer add_user_id;
		private Date add_time;
		private Integer status;
		private Integer sort_id;
		
		private String add_user_name;
		
		public String getAdd_user_name() {
			return add_user_name;
		}

		public void setAdd_user_name(String add_user_name) {
			this.add_user_name = add_user_name;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getImg_name() {
			return img_name;
		}
	
		public void setImg_name(String img_name) {
			this.img_name = img_name;
		}
		public String getImg_path() {
			return img_path;
		}
	
		public void setImg_path(String img_path) {
			this.img_path = img_path;
		}
		public String getLink_address() {
			return link_address;
		}
	
		public void setLink_address(String link_address) {
			this.link_address = link_address;
		}
		public Integer getAdd_user_id() {
			return add_user_id;
		}
	
		public void setAdd_user_id(Integer add_user_id) {
			this.add_user_id = add_user_id;
		}
		public Date getAdd_time() {
			return add_time;
		}
	
		public void setAdd_time(Date add_time) {
			this.add_time = add_time;
		}
		public Integer getStatus() {
			return status;
		}
	
		public void setStatus(Integer status) {
			this.status = status;
		}
		public Integer getSort_id() {
			return sort_id;
		}
	
		public void setSort_id(Integer sort_id) {
			this.sort_id = sort_id;
		}
}