package com.hxt.hPresentApply.model;

import java.math.BigDecimal;
import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zhangyiyang
 * @time	2016年08月29日 23:04:38
 */
public class HPresentApply extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String openId;
		private BigDecimal totalFee;
		private Date createTime;
		private Integer status;
		private String oneAgentOpenId;
		private String oneAgentName;
		private String twoAgentOpenId;
		private String twoAgentName;
		private Integer accountDetailId;
		private String nickName;
		private String batchCode;
		private String remark1;
		private String remark2;
		private String remark3;
		private Integer servicerId;
		private String servicerName;
		private String taxRate;
		private BigDecimal taxFee;
		private BigDecimal allFee;
		
		private Integer flag;
		
		private String personalName;
		private String card_no;
		private String bank_account;
		private Integer agentTwoStyle;
		private Integer role_id;
		private String card_no_1;
		
		private Integer agentOneStyle;
		
		private String twoName;
		private String oneName;
		private String oneMobile1;
		private String twoMobile1;
		private String oneBank_name;
		private String twoBank_name;
		private String twoBank_account;
		private String oneBank_account;
		
		private Date checkTime;
		
		private Date checkTime_start;
		private Date checkTime_end;
		private Date createTime_start;
		private Date createTime_end;
		
		public Date getCreateTime_start() {
			return createTime_start;
		}

		public void setCreateTime_start(Date createTime_start) {
			this.createTime_start = createTime_start;
		}

		public Date getCreateTime_end() {
			return createTime_end;
		}

		public void setCreateTime_end(Date createTime_end) {
			this.createTime_end = createTime_end;
		}

		public Date getCheckTime_start() {
			return checkTime_start;
		}

		public void setCheckTime_start(Date checkTime_start) {
			this.checkTime_start = checkTime_start;
		}

		public Date getCheckTime_end() {
			return checkTime_end;
		}

		public void setCheckTime_end(Date checkTime_end) {
			this.checkTime_end = checkTime_end;
		}

		public Date getCheckTime() {
			return checkTime;
		}

		public void setCheckTime(Date checkTime) {
			this.checkTime = checkTime;
		}

		public String getOneBank_name() {
			return oneBank_name;
		}

		public void setOneBank_name(String oneBank_name) {
			this.oneBank_name = oneBank_name;
		}

		public String getTwoBank_name() {
			return twoBank_name;
		}

		public void setTwoBank_name(String twoBank_name) {
			this.twoBank_name = twoBank_name;
		}

		public String getTwoBank_account() {
			return twoBank_account;
		}

		public void setTwoBank_account(String twoBank_account) {
			this.twoBank_account = twoBank_account;
		}

		public String getOneBank_account() {
			return oneBank_account;
		}

		public void setOneBank_account(String oneBank_account) {
			this.oneBank_account = oneBank_account;
		}

		public String getOneMobile1() {
			return oneMobile1;
		}

		public void setOneMobile1(String oneMobile1) {
			this.oneMobile1 = oneMobile1;
		}

		public String getTwoMobile1() {
			return twoMobile1;
		}

		public void setTwoMobile1(String twoMobile1) {
			this.twoMobile1 = twoMobile1;
		}

		public String getTwoName() {
			return twoName;
		}

		public void setTwoName(String twoName) {
			this.twoName = twoName;
		}

		public String getOneName() {
			return oneName;
		}

		public void setOneName(String oneName) {
			this.oneName = oneName;
		}

		public Integer getAgentOneStyle() {
			return agentOneStyle;
		}

		public void setAgentOneStyle(Integer agentOneStyle) {
			this.agentOneStyle = agentOneStyle;
		}

		public String getCard_no_1() {
			return card_no_1;
		}

		public void setCard_no_1(String card_no_1) {
			this.card_no_1 = card_no_1;
		}

		public Integer getRole_id() {
			return role_id;
		}

		public void setRole_id(Integer role_id) {
			this.role_id = role_id;
		}

		public Integer getAgentTwoStyle() {
			return agentTwoStyle;
		}

		public void setAgentTwoStyle(Integer agentTwoStyle) {
			this.agentTwoStyle = agentTwoStyle;
		}

		public String getBank_account() {
			return bank_account;
		}

		public void setBank_account(String bank_account) {
			this.bank_account = bank_account;
		}

		public String getCard_no() {
			return card_no;
		}

		public void setCard_no(String card_no) {
			this.card_no = card_no;
		}

		public String getPersonalName() {
			return personalName;
		}

		public void setPersonalName(String personalName) {
			this.personalName = personalName;
		}

		public Integer getFlag() {
			return flag;
		}

		public void setFlag(Integer flag) {
			this.flag = flag;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getOpenId() {
			return openId;
		}
	
		public void setOpenId(String openId) {
			this.openId = openId;
		}
		public BigDecimal getTotalFee() {
			return totalFee;
		}
	
		public void setTotalFee(BigDecimal totalFee) {
			this.totalFee = totalFee;
		}
		public Date getCreateTime() {
			return createTime;
		}
	
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public Integer getStatus() {
			return status;
		}
	
		public void setStatus(Integer status) {
			this.status = status;
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
		public String getTwoAgentOpenId() {
			return twoAgentOpenId;
		}
	
		public void setTwoAgentOpenId(String twoAgentOpenId) {
			this.twoAgentOpenId = twoAgentOpenId;
		}
		public String getTwoAgentName() {
			return twoAgentName;
		}
	
		public void setTwoAgentName(String twoAgentName) {
			this.twoAgentName = twoAgentName;
		}
		public Integer getAccountDetailId() {
			return accountDetailId;
		}
	
		public void setAccountDetailId(Integer accountDetailId) {
			this.accountDetailId = accountDetailId;
		}
		public String getNickName() {
			return nickName;
		}
	
		public void setNickName(String nickName) {
			this.nickName = nickName;
		}
		public String getBatchCode() {
			return batchCode;
		}
	
		public void setBatchCode(String batchCode) {
			this.batchCode = batchCode;
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
		public String getTaxRate() {
			return taxRate;
		}
	
		public void setTaxRate(String taxRate) {
			this.taxRate = taxRate;
		}
		public BigDecimal getTaxFee() {
			return taxFee;
		}
	
		public void setTaxFee(BigDecimal taxFee) {
			this.taxFee = taxFee;
		}
		public BigDecimal getAllFee() {
			return allFee;
		}
	
		public void setAllFee(BigDecimal allFee) {
			this.allFee = allFee;
		}
}