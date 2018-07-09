package com.hxt.hUserAccountDetail.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.base.model.BaseModel;
import com.ibatis.sqlmap.engine.datasource.SimpleDataSourceFactory;
/**
 * @author	zlb
 * @time	2016年08月16日 10:58:42
 */
public class HUserAccountDetail extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private Integer userAccountId;
		private Integer type;
		private java.math.BigDecimal totalFee;
		private java.math.BigDecimal realFee;
		private Date createTime;
		private Integer operaterId;
		private Float rate;
		private Integer orderId;
		private String ammeterNum;
		private Integer orderDetailId;
		private java.math.BigDecimal orderDetailMoney;
		private java.math.BigDecimal taxRate;
		private java.math.BigDecimal taxMoney;
		private String remark1;
		private String remark2;
		private String remark3;
		
		private String electric;//电表号
		private Integer amount;//子订单金额
		private Long oneFee;//子订单分润一级代理金额
		private Long twoFee;//子订单分润二级代理金额
		private Long personalFee;//子订单分润服务员金额
		private String oneAgentName;//子订单分润一级代理名称
		private String twoAgentName;//子订单分润二级代理名称
		private String servicerName;//子订单分润服务员名称
		private String c_name;//公司名称
		private Date startTime;//开始时间
		private Date endTime;//结束时间
		private Integer queryType;//查询类型：空：全部，1 自己 2 代理
		private Integer roleType;//查询角色类型 1 1级代理 2 2级代理 3 服务员
		private java.math.BigDecimal allOrderFee;//订单总额
		private String agentName;//显示的代理名称
		private Date pay_time;//子订单支付时间
		private String orderTime;//订单详情时间字符串 yyyy-MM-dd 支付时间
		private SimpleDateFormat sf = new SimpleDateFormat();
		private String oneAgentOpenId;//一级代理openId
		private String twoAgentOpenID;//二级代理openId
		private Integer servicerId;//服务员Id
		
		private String o_id;//订单号
		private String contact_name;//联系人名称
		private String contact_phone;//联系人电话
		private Date orderCreateTime;//订单创建时间
		private Date tick_off_time;//销账时间
		
		private Integer allCount;
		private Integer allDetailFee;
		private String allMoney;
		
		private BigDecimal allRateFee;//总分润金额
		
		private Integer role_id;//账户角色
		
		private String pay_type;
		
		private Integer style;//分润模式 1 按笔 2 按比例
		private Integer pay_style;//支付类型 1 银联 2 手机
		
		private Date startTime1;//开始时间
		private Date endTime1;//结束时间
		
		public Date getStartTime1() {
			return startTime1;
		}

		public void setStartTime1(Date startTime1) {
			this.startTime1 = startTime1;
		}

		public Date getEndTime1() {
			return endTime1;
		}

		public void setEndTime1(Date endTime1) {
			this.endTime1 = endTime1;
		}

		public Integer getPay_style() {
			return pay_style;
		}

		public void setPay_style(Integer pay_style) {
			this.pay_style = pay_style;
		}

		public Integer getStyle() {
			return style;
		}

		public void setStyle(Integer style) {
			this.style = style;
		}

		public String getPay_type() {
			return pay_type;
		}

		public void setPay_type(String pay_type) {
			this.pay_type = pay_type;
		}

		public Integer getRole_id() {
			return role_id;
		}

		public void setRole_id(Integer role_id) {
			this.role_id = role_id;
		}

		public BigDecimal getAllRateFee() {
			return allRateFee;
		}

		public void setAllRateFee(BigDecimal allRateFee) {
			this.allRateFee = allRateFee;
		}

		public Integer getAllCount() {
			return allCount;
		}

		public void setAllCount(Integer allCount) {
			this.allCount = allCount;
		}
		public Integer getAllDetailFee() {
			return allDetailFee;
		}

		public void setAllDetailFee(Integer allDetailFee) {
			this.allDetailFee = allDetailFee;
		}

		public String getAllMoney() {
			return allMoney;
		}

		public void setAllMoney(String allMoney) {
			this.allMoney = allMoney;
		}

		public SimpleDateFormat getSf() {
			return sf;
		}

		public void setSf(SimpleDateFormat sf) {
			this.sf = sf;
		}

		public String getO_id() {
			return o_id;
		}

		public void setO_id(String o_id) {
			this.o_id = o_id;
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

		public Date getOrderCreateTime() {
			return orderCreateTime;
		}

		public void setOrderCreateTime(Date orderCreateTime) {
			this.orderCreateTime = orderCreateTime;
		}

		public Date getTick_off_time() {
			return tick_off_time;
		}

		public void setTick_off_time(Date tick_off_time) {
			this.tick_off_time = tick_off_time;
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

		public Date getPay_time() {
			return pay_time;
		}
		
		public void setPay_time(Date pay_time) {
			this.pay_time = pay_time;
		}
		
		public String getOrderTime() {
			if(orderTime!=null&&!"".equals(orderTime)){
				return orderTime.replace(".0", "");
			}
			return orderTime;
		}

		public void setOrderTime(String orderTime) {
			this.orderTime = orderTime;
		}

		public String getAgentName() {
			return agentName;
		}

		public void setAgentName(String agentName) {
			this.agentName = agentName;
		}

		public java.math.BigDecimal getAllOrderFee() {
			return allOrderFee;
		}

		public void setAllOrderFee(java.math.BigDecimal allOrderFee) {
			this.allOrderFee = allOrderFee;
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

		public Integer getRoleType() {
			return roleType;
		}

		public void setRoleType(Integer roleType) {
			this.roleType = roleType;
		}

		public Integer getQueryType() {
			return queryType;
		}

		public void setQueryType(Integer queryType) {
			this.queryType = queryType;
		}

		public String getElectric() {
			return electric;
		}

		public void setElectric(String electric) {
			this.electric = electric;
		}

		public Integer getAmount() {
			return amount;
		}

		public void setAmount(Integer amount) {
			this.amount = amount;
		}

		public Long getOneFee() {
			return oneFee;
		}

		public void setOneFee(Long oneFee) {
			this.oneFee = oneFee;
		}

		public Long getTwoFee() {
			return twoFee;
		}

		public void setTwoFee(Long twoFee) {
			this.twoFee = twoFee;
		}

		public Long getPersonalFee() {
			return personalFee;
		}

		public void setPersonalFee(Long personalFee) {
			this.personalFee = personalFee;
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

		public String getC_name() {
			return c_name;
		}

		public void setC_name(String c_name) {
			this.c_name = c_name;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getUserAccountId() {
			return userAccountId;
		}
	
		public void setUserAccountId(Integer userAccountId) {
			this.userAccountId = userAccountId;
		}
		public Integer getType() {
			return type;
		}
	
		public void setType(Integer type) {
			this.type = type;
		}
		public java.math.BigDecimal getTotalFee() {
			return totalFee;
		}
	
		public void setTotalFee(java.math.BigDecimal totalFee) {
			this.totalFee = totalFee;
		}
		public java.math.BigDecimal getRealFee() {
			return realFee;
		}
	
		public void setRealFee(java.math.BigDecimal realFee) {
			this.realFee = realFee;
		}
		public Date getCreateTime() {
			return createTime;
		}
	
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public Integer getOperaterId() {
			return operaterId;
		}
	
		public void setOperaterId(Integer operaterId) {
			this.operaterId = operaterId;
		}
		public Float getRate() {
			return rate;
		}
	
		public void setRate(Float rate) {
			this.rate = rate;
		}
		public Integer getOrderId() {
			return orderId;
		}
	
		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}
		public String getAmmeterNum() {
			return ammeterNum;
		}
	
		public void setAmmeterNum(String ammeterNum) {
			this.ammeterNum = ammeterNum;
		}
		public Integer getOrderDetailId() {
			return orderDetailId;
		}
	
		public void setOrderDetailId(Integer orderDetailId) {
			this.orderDetailId = orderDetailId;
		}
		public java.math.BigDecimal getOrderDetailMoney() {
			return orderDetailMoney;
		}
	
		public void setOrderDetailMoney(java.math.BigDecimal orderDetailMoney) {
			this.orderDetailMoney = orderDetailMoney;
		}
		public java.math.BigDecimal getTaxRate() {
			return taxRate;
		}
	
		public void setTaxRate(java.math.BigDecimal taxRate) {
			this.taxRate = taxRate;
		}
		public java.math.BigDecimal getTaxMoney() {
			return taxMoney;
		}
	
		public void setTaxMoney(java.math.BigDecimal taxMoney) {
			this.taxMoney = taxMoney;
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