package com.hxt.hFp.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	yy
 * @time	2015年11月17日 23:58:32
 */
public class HFp extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String orderNumber;
		private Integer money;
		private String title;
		private String userName;
		private String phone;
		private String address;
		private Date createTime;
		private String express_no;
		private Integer express_status;
		private String express_name;
		private Integer mailType;
		private String remark1;
		private String remark2;
		private String remark3;
		private Integer queryType;//1 父发票 2 子发票
		private Integer comId;//订单所属公司ID 
		private Integer ywyId;//业务员ID 
		private Integer fp_style;//发票类型 1恒信通发票 2增值税发票 
		private String companyName;//公司名称
		private String areaName;//换票地址
		private Integer fpCount;//发票张数
		private Integer serialNum;//当前记录发票序号
		private String fpExportTime;//导出时间
		
		private String oneAgentOpenId;
		private String twoAgentOpenID;
		private Integer servicerId;
		private String oneAgentName;
		private String twoAgentName;
		private String servicerName;
		private String orderNo;//订单号
		private String ammeterNo;//电表号
		
		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public String getAmmeterNo() {
			return ammeterNo;
		}

		public void setAmmeterNo(String ammeterNo) {
			this.ammeterNo = ammeterNo;
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

		public String getOneAgentOpenId() {
			return oneAgentOpenId;
		}

		public void setOneAgentOpenId(String oneAgentOpenId) {
			this.oneAgentOpenId = oneAgentOpenId;
		}

		public String getTwoAgentOpenID() {
			return twoAgentOpenID;
		}

		public void setTwoAgentOpenID(String twoAgentOpenID) {
			this.twoAgentOpenID = twoAgentOpenID;
		}

		public Integer getServicerId() {
			return servicerId;
		}

		public void setServicerId(Integer servicerId) {
			this.servicerId = servicerId;
		}

		public String getFpExportTime() {
			return fpExportTime;
		}

		public void setFpExportTime(String fpExportTime) {
			this.fpExportTime = fpExportTime;
		}

		public Integer getSerialNum() {
			return serialNum;
		}

		public void setSerialNum(Integer serialNum) {
			this.serialNum = serialNum;
		}

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public Integer getFpCount() {
			return fpCount;
		}

		public void setFpCount(Integer fpCount) {
			this.fpCount = fpCount;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		private String totalFeeStr;
		
		public String getTotalFeeStr() {
			return totalFeeStr;
		}

		public void setTotalFeeStr(String totalFeeStr) {
			this.totalFeeStr = totalFeeStr;
		}

		public Integer getFp_style() {
			return fp_style;
		}

		public void setFp_style(Integer fp_style) {
			this.fp_style = fp_style;
		}

		public Integer getYwyId() {
			return ywyId;
		}

		public void setYwyId(Integer ywyId) {
			this.ywyId = ywyId;
		}

		public Integer getComId() {
			return comId;
		}

		public void setComId(Integer comId) {
			this.comId = comId;
		}

		public Integer getQueryType() {
			return queryType;
		}

		public void setQueryType(Integer queryType) {
			this.queryType = queryType;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getOrderNumber() {
			return orderNumber;
		}
	
		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}
		public Integer getMoney() {
			return money;
		}
	
		public void setMoney(Integer money) {
			this.money = money;
		}
		public String getTitle() {
			return title;
		}
	
		public void setTitle(String title) {
			this.title = title;
		}
		public String getUserName() {
			return userName;
		}
	
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPhone() {
			return phone;
		}
	
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getAddress() {
			return address;
		}
	
		public void setAddress(String address) {
			this.address = address;
		}
		public Date getCreateTime() {
			return createTime;
		}
	
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public String getExpress_no() {
			return express_no;
		}
	
		public void setExpress_no(String express_no) {
			this.express_no = express_no;
		}
		public Integer getExpress_status() {
			return express_status;
		}
	
		public void setExpress_status(Integer express_status) {
			this.express_status = express_status;
		}
		public String getExpress_name() {
			return express_name;
		}
	
		public void setExpress_name(String express_name) {
			this.express_name = express_name;
		}
		public Integer getMailType() {
			return mailType;
		}
	
		public void setMailType(Integer mailType) {
			this.mailType = mailType;
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