package com.hxt.hEwm.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	wuzhenwei
 * @time	2015年08月31日 19:04:33
 */
public class HEwm extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String scene_id;
		private String imgUrl;
		private Integer userId;
		private String remark1;
		private String remark2;
		private String remark3;
		private Date createTime;
		private Integer style;
		private String openId;
		private String nickName;
		
		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getOpenId() {
			return openId;
		}

		public void setOpenId(String openId) {
			this.openId = openId;
		}

		public Integer getStyle() {
			return style;
		}

		public void setStyle(Integer style) {
			this.style = style;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getScene_id() {
			return scene_id;
		}
	
		public void setScene_id(String scene_id) {
			this.scene_id = scene_id;
		}
		public String getImgUrl() {
			return imgUrl;
		}
	
		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}
		public Integer getUserId() {
			return userId;
		}
	
		public void setUserId(Integer userId) {
			this.userId = userId;
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