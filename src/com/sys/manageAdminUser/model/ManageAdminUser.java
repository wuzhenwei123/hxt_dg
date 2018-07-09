package com.sys.manageAdminUser.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	keeny
 * @time	2015年02月04日 11:09:21
 */
public class ManageAdminUser extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer adminId;
		private String adminName;
		private String nickName;
		private String passwd;
		private String realName;
		private String mobile;
		private String phone;
		private String headImg;
		private Date lastLogin;
		private String loginIP;
		private Date pwdModifyTime;
		private Integer state;
		private Date createTime;
		private Integer createrId;
		private Integer roleId;
		private String roleName;
		private String createrName;
		private String openId;//微信唯一识别
		private String agentCode;//代理商代码
		private Integer roleType;
		private String oneAgentOpenId;
		private String twoAgentOpenId;
		private Integer isFirst;//是否是首次登陆:0首次/1非首次
		
		private Integer companyId;//单位ID
		private String companyName;//单位名称
		private Integer ywyId;//业务员ID 
		private Integer servicerId;
		private Integer servicerCount;
		
		private String oneAgentName;//一级代理名称
		private String twoAgentName;//二级代理名称
		private String servicerName;//服务人员姓名
		
		private Date scanTime;//扫码时间
		private Date cancelTime;//解绑时间
		
		private String oneAgentPhone;
		private String twoAgentPhone;
		private String servicerPhone;
		
		private Integer otherServicerId;
		
		private Date startTime;//创建开始时间
		private Date endTime;//创建结束
		
		private Integer gl_yl_id;//银联鼓励金
		private String gl_yl_name;//银联鼓励金
		private Integer gl_sj_id;//手机鼓励金
		private String gl_sj_name;//手机鼓励金
		
		private Integer gl_yl_type;//银联鼓励金
		private Double gl_yl_fee;//银联鼓励金
		private Double gl_yl_rate;//银联鼓励金
		private Integer gl_sj_type;//手机鼓励金
		private Double gl_sj_fee;//手机鼓励金
		private Double gl_sj_rate;//手机鼓励金
		
		private Integer gl_yl_id1;
		private Integer gl_sj_id1;
		
		public Integer getGl_yl_id1() {
			return gl_yl_id1;
		}

		public void setGl_yl_id1(Integer gl_yl_id1) {
			this.gl_yl_id1 = gl_yl_id1;
		}

		public Integer getGl_sj_id1() {
			return gl_sj_id1;
		}

		public void setGl_sj_id1(Integer gl_sj_id1) {
			this.gl_sj_id1 = gl_sj_id1;
		}

		public Integer getGl_yl_id() {
			return gl_yl_id;
		}

		public void setGl_yl_id(Integer gl_yl_id) {
			this.gl_yl_id = gl_yl_id;
		}

		public String getGl_yl_name() {
			return gl_yl_name;
		}

		public void setGl_yl_name(String gl_yl_name) {
			this.gl_yl_name = gl_yl_name;
		}

		public Integer getGl_sj_id() {
			return gl_sj_id;
		}

		public void setGl_sj_id(Integer gl_sj_id) {
			this.gl_sj_id = gl_sj_id;
		}

		public String getGl_sj_name() {
			return gl_sj_name;
		}

		public void setGl_sj_name(String gl_sj_name) {
			this.gl_sj_name = gl_sj_name;
		}

		public Integer getGl_yl_type() {
			return gl_yl_type;
		}

		public void setGl_yl_type(Integer gl_yl_type) {
			this.gl_yl_type = gl_yl_type;
		}

		public Double getGl_yl_fee() {
			return gl_yl_fee;
		}

		public void setGl_yl_fee(Double gl_yl_fee) {
			this.gl_yl_fee = gl_yl_fee;
		}

		public Double getGl_yl_rate() {
			return gl_yl_rate;
		}

		public void setGl_yl_rate(Double gl_yl_rate) {
			this.gl_yl_rate = gl_yl_rate;
		}

		public Integer getGl_sj_type() {
			return gl_sj_type;
		}

		public void setGl_sj_type(Integer gl_sj_type) {
			this.gl_sj_type = gl_sj_type;
		}

		public Double getGl_sj_fee() {
			return gl_sj_fee;
		}

		public void setGl_sj_fee(Double gl_sj_fee) {
			this.gl_sj_fee = gl_sj_fee;
		}

		public Double getGl_sj_rate() {
			return gl_sj_rate;
		}

		public void setGl_sj_rate(Double gl_sj_rate) {
			this.gl_sj_rate = gl_sj_rate;
		}

		public Date getStartTime() {
			return startTime;
		}

		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}

		public Date getEndTime() {
			return endTime;
		}

		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}

		public Integer getOtherServicerId() {
			return otherServicerId;
		}

		public void setOtherServicerId(Integer otherServicerId) {
			this.otherServicerId = otherServicerId;
		}

		public String getOneAgentPhone() {
			return oneAgentPhone;
		}

		public void setOneAgentPhone(String oneAgentPhone) {
			this.oneAgentPhone = oneAgentPhone;
		}

		public String getTwoAgentPhone() {
			return twoAgentPhone;
		}

		public void setTwoAgentPhone(String twoAgentPhone) {
			this.twoAgentPhone = twoAgentPhone;
		}

		public String getServicerPhone() {
			return servicerPhone;
		}

		public void setServicerPhone(String servicerPhone) {
			this.servicerPhone = servicerPhone;
		}

		public Date getScanTime() {
			return scanTime;
		}

		public void setScanTime(Date scanTime) {
			this.scanTime = scanTime;
		}

		public Date getCancelTime() {
			return cancelTime;
		}

		public void setCancelTime(Date cancelTime) {
			this.cancelTime = cancelTime;
		}

		public String getOneAgentName() {
			return oneAgentName;
		}

		public void setOneAgentName(String oneAgentName) {
			this.oneAgentName = oneAgentName;
		}

		public String getTwoAgentName() {
			return twoAgentName;
		}

		public void setTwoAgentName(String twoAgentName) {
			this.twoAgentName = twoAgentName;
		}

		public String getServicerName() {
			return servicerName;
		}

		public void setServicerName(String servicerName) {
			this.servicerName = servicerName;
		}

		public Integer getServicerId() {
			return servicerId;
		}

		public void setServicerId(Integer servicerId) {
			this.servicerId = servicerId;
		}

		public Integer getServicerCount() {
			return servicerCount;
		}

		public void setServicerCount(Integer servicerCount) {
			this.servicerCount = servicerCount;
		}

		public Integer getYwyId() {
			return ywyId;
		}

		public void setYwyId(Integer ywyId) {
			this.ywyId = ywyId;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public Integer getCompanyId() {
			return companyId;
		}

		public void setCompanyId(Integer companyId) {
			this.companyId = companyId;
		}

		public Integer getIsFirst() {
			return isFirst;
		}

		public void setIsFirst(Integer isFirst) {
			this.isFirst = isFirst;
		}

		public String getOneAgentOpenId() {
			return oneAgentOpenId;
		}

		public void setOneAgentOpenId(String oneAgentOpenId) {
			this.oneAgentOpenId = oneAgentOpenId;
		}

		public String getTwoAgentOpenId() {
			return twoAgentOpenId;
		}

		public void setTwoAgentOpenId(String twoAgentOpenId) {
			this.twoAgentOpenId = twoAgentOpenId;
		}

		public Integer getRoleType() {
			return roleType;
		}

		public void setRoleType(Integer roleType) {
			this.roleType = roleType;
		}

		public String getAgentCode() {
			return agentCode;
		}

		public void setAgentCode(String agentCode) {
			this.agentCode = agentCode;
		}

		public String getOpenId() {
			return openId;
		}

		public void setOpenId(String openId) {
			this.openId = openId;
		}

		public String getRoleName() {
			return roleName;
		}

		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

		public String getCreaterName() {
			return createrName;
		}

		public void setCreaterName(String createrName) {
			this.createrName = createrName;
		}

		public Integer getRoleId() {
			return roleId;
		}

		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}

		public String getHeadImg() {
			return headImg;
		}

		public void setHeadImg(String headImg) {
			this.headImg = headImg;
		}

		public Integer getAdminId() {
			return adminId;
		}
	
		public void setAdminId(Integer adminId) {
			this.adminId = adminId;
		}
		public String getAdminName() {
			return adminName;
		}
	
		public void setAdminName(String adminName) {
			this.adminName = adminName;
		}
		public String getNickName() {
			return nickName;
		}
	
		public void setNickName(String nickName) {
			this.nickName = nickName;
		}
		public String getPasswd() {
			return passwd;
		}
	
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
		public String getRealName() {
			return realName;
		}
	
		public void setRealName(String realName) {
			this.realName = realName;
		}
		public String getMobile() {
			return mobile;
		}
	
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getPhone() {
			return phone;
		}
	
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public Date getLastLogin() {
			return lastLogin;
		}
	
		public void setLastLogin(Date lastLogin) {
			this.lastLogin = lastLogin;
		}
		public String getLoginIP() {
			return loginIP;
		}
	
		public void setLoginIP(String loginIP) {
			this.loginIP = loginIP;
		}
		public Date getPwdModifyTime() {
			return pwdModifyTime;
		}
	
		public void setPwdModifyTime(Date pwdModifyTime) {
			this.pwdModifyTime = pwdModifyTime;
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
		public Integer getCreaterId() {
			return createrId;
		}
	
		public void setCreaterId(Integer createrId) {
			this.createrId = createrId;
		}
}