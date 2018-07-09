package com.hxt.hPayOrder.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.base.model.BaseModel;
import com.hxt.hSubOrder.model.HSubOrder;
/**
 * @author	wuzhenwei
 * @time	2015年11月24日 23:49:11
 */
public class HPayOrder extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer o_id;
		private String o_no;
		private String query_id;
		private String pay_ip;
		private Integer c_id;
		private Integer o_sub_id;
		private Integer amount;
		private Integer actual_payment;
		private Integer account_payment;
		private String pay_type;
		private String pay_account;
		private Integer operator_id;
		private String payee;
		private Date create_time;
		private Date pay_time;
		private String pay_status;
		private String tick_off_status;
		private Date tick_off_time;
		private String back_fee_status;
		private Date back_time;
		
		private String c_name;//单位名称
		private String o_sub_name;//子单位名称
		private String yw_name;//业务员名称
		private Integer yw_id;//业务员名称
		
		private Date pay_start_time;// 支付开始时间
		private Date pay_end_time;// 支付结束时间
		private Date back_start_time;// 销账开始时间
		private Date back_end_time;// 销账结束时间
		private Date tick_start_time;// 退费开始时间
		private Date tick_end_time;// 退费结束时间
		
		
		
		private String electric_number;//电表号
		
		
		private List<HSubOrder> listSubOrder;
		
		private String respMsg;//失败原因
		
		private Integer fp_order_id;//发票订单关系
		private Integer is_zz;//是否包含增值税发票 0不包含 1包含
		
		
		private String amountStr;
		private Long totalFee;
		private String totalFeeStr;
		private String notTotalFeeStr;
		
		
		private Date create_start_time;// 订单开始时间
		private Date create_end_time;// 订单结束时间
		//新增子单位id
		private Integer sub_id;//
		//是否有评价
		private Integer evaluateFlag;
		
		
		private String oneAgentOpenId;//一级代理openID
		private String oneAgentName;//一级代理名称
		private String twoAgentOpenID;//二级代理openID
		private String twoAgentName;//二级代理名称
		private Integer servicerId;//服务人员ID（adminID）
		private String servicerName;//服务人员姓名
		private String contact_name;//客户联系人姓名
		private String contact_phone;//客户联系人电话
		
		private Integer trans_count;//交易次数
		private Integer is_fp;//是否开票 1 开票 0未开票
		
		
		private BigDecimal oneRate;
		private BigDecimal twoRate;
		private BigDecimal serverRate;
		private Integer roleType;
		private Integer role_id;
		private Integer queryType;
		private Long allOrderFee;//订单总额
		private Date startTime;
		private Date endTime;
		private Integer allCount;
		private Long allDetailFee;
		private String allMoney;
		private BigDecimal allRateFee;//总分润金额
		private String agentName;
		private String rateMoney;
		private String payTimeStr;
		private Integer order_type;//订单类型 1 支付订单 2 提现订单
		private Integer apply_id;//提现申请ID
		
		private Integer startTotalFee;
		private Integer endTotalFee;
		private Integer fr;//是否分润 1 分润  0不分润
		
		public Integer getFr() {
			return fr;
		}

		public void setFr(Integer fr) {
			this.fr = fr;
		}

		public Integer getStartTotalFee() {
			return startTotalFee;
		}

		public void setStartTotalFee(Integer startTotalFee) {
			this.startTotalFee = startTotalFee;
		}

		public Integer getEndTotalFee() {
			return endTotalFee;
		}

		public void setEndTotalFee(Integer endTotalFee) {
			this.endTotalFee = endTotalFee;
		}

		public Integer getApply_id() {
			return apply_id;
		}

		public void setApply_id(Integer apply_id) {
			this.apply_id = apply_id;
		}

		public Integer getOrder_type() {
			return order_type;
		}

		public void setOrder_type(Integer order_type) {
			this.order_type = order_type;
		}

		public String getPayTimeStr() {
			return payTimeStr;
		}

		public void setPayTimeStr(String payTimeStr) {
			this.payTimeStr = payTimeStr;
		}

		public String getRateMoney() {
			return rateMoney;
		}

		public void setRateMoney(String rateMoney) {
			this.rateMoney = rateMoney;
		}

		public String getAgentName() {
			return agentName;
		}

		public void setAgentName(String agentName) {
			this.agentName = agentName;
		}

		public Long getAllOrderFee() {
			return allOrderFee;
		}

		public void setAllOrderFee(Long allOrderFee) {
			this.allOrderFee = allOrderFee;
		}

		public Integer getAllCount() {
			return allCount;
		}

		public void setAllCount(Integer allCount) {
			this.allCount = allCount;
		}

		public Long getAllDetailFee() {
			return allDetailFee;
		}

		public void setAllDetailFee(Long allDetailFee) {
			this.allDetailFee = allDetailFee;
		}

		public String getAllMoney() {
			return allMoney;
		}

		public void setAllMoney(String allMoney) {
			this.allMoney = allMoney;
		}

		public BigDecimal getAllRateFee() {
			return allRateFee;
		}

		public void setAllRateFee(BigDecimal allRateFee) {
			this.allRateFee = allRateFee;
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

		public Integer getQueryType() {
			return queryType;
		}

		public void setQueryType(Integer queryType) {
			this.queryType = queryType;
		}

		public Integer getRoleType() {
			return roleType;
		}

		public void setRoleType(Integer roleType) {
			this.roleType = roleType;
		}

		public Integer getRole_id() {
			return role_id;
		}

		public void setRole_id(Integer role_id) {
			this.role_id = role_id;
		}

		public BigDecimal getOneRate() {
			return oneRate;
		}

		public void setOneRate(BigDecimal oneRate) {
			this.oneRate = oneRate;
		}

		public BigDecimal getTwoRate() {
			return twoRate;
		}

		public void setTwoRate(BigDecimal twoRate) {
			this.twoRate = twoRate;
		}

		public BigDecimal getServerRate() {
			return serverRate;
		}

		public void setServerRate(BigDecimal serverRate) {
			this.serverRate = serverRate;
		}

		public Integer getIs_fp() {
			return is_fp;
		}

		public void setIs_fp(Integer is_fp) {
			this.is_fp = is_fp;
		}

		public Integer getTrans_count() {
			return trans_count;
		}

		public void setTrans_count(Integer trans_count) {
			this.trans_count = trans_count;
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

		public Integer getEvaluateFlag() {
			return evaluateFlag;
		}

		public void setEvaluateFlag(Integer evaluateFlag) {
			this.evaluateFlag = evaluateFlag;
		}

		public Integer getSub_id() {
			return sub_id;
		}

		public void setSub_id(Integer sub_id) {
			this.sub_id = sub_id;
		}

		public Date getCreate_start_time() {
			return create_start_time;
		}

		public void setCreate_start_time(Date create_start_time) {
			this.create_start_time = create_start_time;
		}

		public Date getCreate_end_time() {
			return create_end_time;
		}

		public void setCreate_end_time(Date create_end_time) {
			this.create_end_time = create_end_time;
		}

		public String getNotTotalFeeStr() {
			return notTotalFeeStr;
		}

		public void setNotTotalFeeStr(String notTotalFeeStr) {
			this.notTotalFeeStr = notTotalFeeStr;
		}

		public String getTotalFeeStr() {
			return totalFeeStr;
		}

		public void setTotalFeeStr(String totalFeeStr) {
			this.totalFeeStr = totalFeeStr;
		}

		public Long getTotalFee() {
			return totalFee;
		}

		public void setTotalFee(Long totalFee) {
			this.totalFee = totalFee;
		}

		public String getAmountStr() {
			return amountStr;
		}

		public void setAmountStr(String amountStr) {
			this.amountStr = amountStr;
		}

		public Integer getIs_zz() {
			return is_zz;
		}

		public void setIs_zz(Integer is_zz) {
			this.is_zz = is_zz;
		}

		public Integer getFp_order_id() {
			return fp_order_id;
		}

		public void setFp_order_id(Integer fp_order_id) {
			this.fp_order_id = fp_order_id;
		}

		public String getRespMsg() {
			return respMsg;
		}

		public void setRespMsg(String respMsg) {
			this.respMsg = respMsg;
		}

		public List<HSubOrder> getListSubOrder() {
			return listSubOrder;
		}

		public void setListSubOrder(List<HSubOrder> listSubOrder) {
			this.listSubOrder = listSubOrder;
		}

		public String getElectric_number() {
			return electric_number;
		}

		public void setElectric_number(String electric_number) {
			this.electric_number = electric_number;
		}

		public Date getPay_start_time() {
			return pay_start_time;
		}

		public void setPay_start_time(Date pay_start_time) {
			this.pay_start_time = pay_start_time;
		}

		public Date getPay_end_time() {
			return pay_end_time;
		}

		public void setPay_end_time(Date pay_end_time) {
			this.pay_end_time = pay_end_time;
		}

		public Date getBack_start_time() {
			return back_start_time;
		}

		public void setBack_start_time(Date back_start_time) {
			this.back_start_time = back_start_time;
		}

		public Date getBack_end_time() {
			return back_end_time;
		}

		public void setBack_end_time(Date back_end_time) {
			this.back_end_time = back_end_time;
		}

		public Date getTick_start_time() {
			return tick_start_time;
		}

		public void setTick_start_time(Date tick_start_time) {
			this.tick_start_time = tick_start_time;
		}

		public Date getTick_end_time() {
			return tick_end_time;
		}

		public void setTick_end_time(Date tick_end_time) {
			this.tick_end_time = tick_end_time;
		}

		public String getC_name() {
			return c_name;
		}

		public void setC_name(String c_name) {
			this.c_name = c_name;
		}

		public String getO_sub_name() {
			return o_sub_name;
		}

		public void setO_sub_name(String o_sub_name) {
			this.o_sub_name = o_sub_name;
		}

		public String getYw_name() {
			return yw_name;
		}

		public void setYw_name(String yw_name) {
			this.yw_name = yw_name;
		}

		public Integer getYw_id() {
			return yw_id;
		}

		public void setYw_id(Integer yw_id) {
			this.yw_id = yw_id;
		}

		public Integer getO_id() {
			return o_id;
		}
	
		public void setO_id(Integer o_id) {
			this.o_id = o_id;
		}
		public String getO_no() {
			return o_no;
		}
	
		public void setO_no(String o_no) {
			this.o_no = o_no;
		}
		public String getQuery_id() {
			return query_id;
		}
	
		public void setQuery_id(String query_id) {
			this.query_id = query_id;
		}
		public String getPay_ip() {
			return pay_ip;
		}
	
		public void setPay_ip(String pay_ip) {
			this.pay_ip = pay_ip;
		}
		public Integer getC_id() {
			return c_id;
		}
	
		public void setC_id(Integer c_id) {
			this.c_id = c_id;
		}
		public Integer getO_sub_id() {
			return o_sub_id;
		}
	
		public void setO_sub_id(Integer o_sub_id) {
			this.o_sub_id = o_sub_id;
		}
		public Integer getAmount() {
			return amount;
		}
	
		public void setAmount(Integer amount) {
			this.amount = amount;
		}
		public Integer getActual_payment() {
			return actual_payment;
		}
	
		public void setActual_payment(Integer actual_payment) {
			this.actual_payment = actual_payment;
		}
		public Integer getAccount_payment() {
			return account_payment;
		}
	
		public void setAccount_payment(Integer account_payment) {
			this.account_payment = account_payment;
		}
		public String getPay_type() {
			return pay_type;
		}
	
		public void setPay_type(String pay_type) {
			this.pay_type = pay_type;
		}
		public String getPay_account() {
			return pay_account;
		}
	
		public void setPay_account(String pay_account) {
			this.pay_account = pay_account;
		}
		public Integer getOperator_id() {
			return operator_id;
		}
	
		public void setOperator_id(Integer operator_id) {
			this.operator_id = operator_id;
		}
		public String getPayee() {
			return payee;
		}
	
		public void setPayee(String payee) {
			this.payee = payee;
		}
		public Date getCreate_time() {
			return create_time;
		}
	
		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}
		public Date getPay_time() {
			return pay_time;
		}
	
		public void setPay_time(Date pay_time) {
			this.pay_time = pay_time;
		}
		public String getPay_status() {
			return pay_status;
		}
	
		public void setPay_status(String pay_status) {
			this.pay_status = pay_status;
		}
		public String getTick_off_status() {
			return tick_off_status;
		}
	
		public void setTick_off_status(String tick_off_status) {
			this.tick_off_status = tick_off_status;
		}
		public Date getTick_off_time() {
			return tick_off_time;
		}
	
		public void setTick_off_time(Date tick_off_time) {
			this.tick_off_time = tick_off_time;
		}
		public String getBack_fee_status() {
			return back_fee_status;
		}
	
		public void setBack_fee_status(String back_fee_status) {
			this.back_fee_status = back_fee_status;
		}
		public Date getBack_time() {
			return back_time;
		}
	
		public void setBack_time(Date back_time) {
			this.back_time = back_time;
		}
}