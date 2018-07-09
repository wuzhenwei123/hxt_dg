package com.hxt.hEvaluate.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zhangyiyang
 * @time	2016年08月22日 10:55:38
 */
public class HEvaluate extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer id;
		private String orderNo;
		private Integer star;
		private String content;
		private Date createTime;
		
		
		private String electric_number;
		private String oneAgentName;
		private String twoAgentName;
		private String servicerName;
		private String contact_name;
		private String contact_phone;
		private String c_name;
		private Integer amount;
		private String amountStr;
		
		public String getAmountStr() {
			return amountStr;
		}

		public void setAmountStr(String amountStr) {
			this.amountStr = amountStr;
		}

		public Integer getAmount() {
			return amount;
		}

		public void setAmount(Integer amount) {
			this.amount = amount;
		}

		public String getC_name() {
			return c_name;
		}

		public void setC_name(String c_name) {
			this.c_name = c_name;
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

		public String getServicerName() {
			return servicerName;
		}

		public void setServicerName(String servicerName) {
			this.servicerName = servicerName;
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

		public String getElectric_number() {
			return electric_number;
		}

		public void setElectric_number(String electric_number) {
			this.electric_number = electric_number;
		}

		public Integer getId() {
			return id;
		}
	
		public void setId(Integer id) {
			this.id = id;
		}
		public String getOrderNo() {
			return orderNo;
		}
	
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}
		public Integer getStar() {
			return star;
		}
	
		public void setStar(Integer star) {
			this.star = star;
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
}