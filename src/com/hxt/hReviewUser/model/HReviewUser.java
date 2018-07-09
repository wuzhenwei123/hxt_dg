package com.hxt.hReviewUser.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zhangyiyang
 * @time	2016年08月01日 22:41:42
 */
public class HReviewUser extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private Integer isDefault;
		private Integer state;
		private String userLabel;
		private String userName;
		private Integer sex;
		private String mobil;
		private Integer msgSwitch;
		private Date createTime;
		private Integer createId;
		private Date updateTime;
		private Integer updateId;
		private String remark;
		private Integer companyId;
		private String companyName;
	    private String contact_name;//业务联系人姓名
	    private String contact_phone;//业务联系人手机（短信验证）
	    
	    private String oneAgentOpenId;//一级代理openID
		private String oneAgentName;//一级代理名称
		private String twoAgentOpenID;//二级代理openID
		private String twoAgentName;//二级代理名称
		private Integer servicerId;//服务人员ID（adminID）
		private String servicerName;//服务人员姓名
	    
		public String getOneAgentOpenId() {
			return oneAgentOpenId;
		}

		public void setOneAgentOpenId(String oneAgentOpenId) {
			this.oneAgentOpenId = oneAgentOpenId;
		}

		public String getOneAgentName() {
			return oneAgentName;
		}

		public void setOneAgentName(String oneAgentName) {
			this.oneAgentName = oneAgentName;
		}

		public String getTwoAgentOpenID() {
			return twoAgentOpenID;
		}

		public void setTwoAgentOpenID(String twoAgentOpenID) {
			this.twoAgentOpenID = twoAgentOpenID;
		}

		public String getTwoAgentName() {
			return twoAgentName;
		}

		public void setTwoAgentName(String twoAgentName) {
			this.twoAgentName = twoAgentName;
		}

		public Integer getServicerId() {
			return servicerId;
		}

		public void setServicerId(Integer servicerId) {
			this.servicerId = servicerId;
		}

		public String getServicerName() {
			return servicerName;
		}

		public void setServicerName(String servicerName) {
			this.servicerName = servicerName;
		}

		public String getContact_name() {
			return contact_name;
		}

		public void setContact_name(String contact_name) {
			this.contact_name = contact_name;
		}

		public String getContact_phone() {
			return contact_phone;
		}

		public void setContact_phone(String contact_phone) {
			this.contact_phone = contact_phone;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getIsDefault() {
			return isDefault;
		}
	
		public void setIsDefault(Integer isDefault) {
			this.isDefault = isDefault;
		}
		public Integer getState() {
			return state;
		}
	
		public void setState(Integer state) {
			this.state = state;
		}
		public String getUserLabel() {
			return userLabel;
		}
	
		public void setUserLabel(String userLabel) {
			this.userLabel = userLabel;
		}
		public String getUserName() {
			return userName;
		}
	
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public Integer getSex() {
			return sex;
		}
	
		public void setSex(Integer sex) {
			this.sex = sex;
		}
		public String getMobil() {
			return mobil;
		}
	
		public void setMobil(String mobil) {
			this.mobil = mobil;
		}
		public Integer getMsgSwitch() {
			return msgSwitch;
		}
	
		public void setMsgSwitch(Integer msgSwitch) {
			this.msgSwitch = msgSwitch;
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
		public Date getUpdateTime() {
			return updateTime;
		}
	
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
		public Integer getUpdateId() {
			return updateId;
		}
	
		public void setUpdateId(Integer updateId) {
			this.updateId = updateId;
		}
		public String getRemark() {
			return remark;
		}
	
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public Integer getCompanyId() {
			return companyId;
		}
	
		public void setCompanyId(Integer companyId) {
			this.companyId = companyId;
		}
}