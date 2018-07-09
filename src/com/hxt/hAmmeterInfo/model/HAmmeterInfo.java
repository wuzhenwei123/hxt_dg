package com.hxt.hAmmeterInfo.model;

import java.util.Date;

import com.base.model.BaseModel;
/**
 * @author	zhanglibo
 * @time	2015年11月18日 00:55:00
 */
public class HAmmeterInfo extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Integer a_id;
		private Integer c_id;
		private Integer s_id;
		private String ammeter_no;
		private String ammeter_type;
		private Integer last_pay_day;
		private String pay_status;
		private Integer operator_id;
		private Date create_time;
		private Date update_time;
		
		private Integer c_status;//单位状态
		private Integer c_verify_status;//单位审核状态
		private Integer totalFee;//欠费总金额
		private Date dispatch_create_time;//生成调单日期
		private String c_name;//单位名称
		private String sub_name;//子单位名称
		private String contact_phone;//单位联系人手机
		
		private String ammeter_name;
		private Integer fp_style;//发票类型 1恒信通发票 2增值税发票
		
		private String electric_address;// 用电地址
		private String now_totalFee;// 当前欠费
		
		private Integer dispatch_id;
		private String ammeter_address;//客户详细地址
		private String province_code;
		private String province_name;
		private String city_code;
		private String city_name;
		private String area_code;
		private String area_name;
		private Integer profit_id;//分润ID
		private String profit_name;//分润名称
		private Integer is_payed;//是否交过费 1 是0 否
		private Integer delete_state;//删除标记 0 删除 1 正常
		private String profit_one;//一级代理分润比例
		private String profit_two;//二级代理分润比例
		private String profit_servicer;//服务人员分润比例
		private Integer ammeterinfo_type;//缴费号类型 1 电表号 
		private Integer proxy_flag;//是否是代缴费电表号 0 否 1 是
		
		private String companyName;//所属单位名称
		private String adminName;//登录账户
		
		private String bill_img;//缴费单图片
		
		private String oneAgentName;
		private String twoAgentName;
		private String servicerName;
		private String delete_state_str;
		private String pay_status_str;
		
		private String proxyName;
		private String proxyPhone;
		private String contact_name;
		
		public String getProxyName() {
			return proxyName;
		}

		public void setProxyName(String proxyName) {
			this.proxyName = proxyName;
		}

		public String getProxyPhone() {
			return proxyPhone;
		}

		public void setProxyPhone(String proxyPhone) {
			this.proxyPhone = proxyPhone;
		}

		public String getContact_name() {
			return contact_name;
		}

		public void setContact_name(String contact_name) {
			this.contact_name = contact_name;
		}

		public String getPay_status_str() {
			return pay_status_str;
		}

		public void setPay_status_str(String pay_status_str) {
			this.pay_status_str = pay_status_str;
		}

		public String getDelete_state_str() {
			return delete_state_str;
		}

		public void setDelete_state_str(String delete_state_str) {
			this.delete_state_str = delete_state_str;
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

		public String getBill_img() {
			return bill_img;
		}

		public void setBill_img(String bill_img) {
			this.bill_img = bill_img;
		}

		public String getAdminName() {
			return adminName;
		}

		public void setAdminName(String adminName) {
			this.adminName = adminName;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public Integer getProxy_flag() {
			return proxy_flag;
		}

		public void setProxy_flag(Integer proxy_flag) {
			this.proxy_flag = proxy_flag;
		}

		public Integer getAmmeterinfo_type() {
			return ammeterinfo_type;
		}

		public void setAmmeterinfo_type(Integer ammeterinfo_type) {
			this.ammeterinfo_type = ammeterinfo_type;
		}

		public String getProfit_name() {
			return profit_name;
		}

		public void setProfit_name(String profit_name) {
			this.profit_name = profit_name;
		}

		public String getProvince_code() {
			return province_code;
		}

		public void setProvince_code(String province_code) {
			this.province_code = province_code;
		}

		public String getProvince_name() {
			return province_name;
		}

		public void setProvince_name(String province_name) {
			this.province_name = province_name;
		}

		public String getProfit_one() {
			return profit_one;
		}

		public void setProfit_one(String profit_one) {
			this.profit_one = profit_one;
		}

		public String getProfit_two() {
			return profit_two;
		}

		public void setProfit_two(String profit_two) {
			this.profit_two = profit_two;
		}

		public String getProfit_servicer() {
			return profit_servicer;
		}

		public void setProfit_servicer(String profit_servicer) {
			this.profit_servicer = profit_servicer;
		}

		public String getAmmeter_address() {
			return ammeter_address;
		}

		public void setAmmeter_address(String ammeter_address) {
			this.ammeter_address = ammeter_address;
		}

		public String getCity_code() {
			return city_code;
		}

		public void setCity_code(String city_code) {
			this.city_code = city_code;
		}

		public String getCity_name() {
			return city_name;
		}

		public void setCity_name(String city_name) {
			this.city_name = city_name;
		}

		public String getArea_code() {
			return area_code;
		}

		public void setArea_code(String area_code) {
			this.area_code = area_code;
		}

		public String getArea_name() {
			return area_name;
		}

		public void setArea_name(String area_name) {
			this.area_name = area_name;
		}

		public Integer getProfit_id() {
			return profit_id;
		}

		public void setProfit_id(Integer profit_id) {
			this.profit_id = profit_id;
		}

		public Integer getIs_payed() {
			return is_payed;
		}

		public void setIs_payed(Integer is_payed) {
			this.is_payed = is_payed;
		}

		public Integer getDelete_state() {
			return delete_state;
		}

		public void setDelete_state(Integer delete_state) {
			this.delete_state = delete_state;
		}

		public Integer getDispatch_id() {
			return dispatch_id;
		}

		public void setDispatch_id(Integer dispatch_id) {
			this.dispatch_id = dispatch_id;
		}

		public String getElectric_address() {
			return electric_address;
		}
	
		public void setElectric_address(String electric_address) {
			this.electric_address = electric_address;
		}
	
		public String getNow_totalFee() {
			return now_totalFee;
		}
	
		public void setNow_totalFee(String now_totalFee) {
			this.now_totalFee = now_totalFee;
		}

		public Integer getFp_style() {
			return fp_style;
		}

		public void setFp_style(Integer fp_style) {
			this.fp_style = fp_style;
		}

		public String getAmmeter_name() {
			return ammeter_name;
		}

		public void setAmmeter_name(String ammeter_name) {
			this.ammeter_name = ammeter_name;
		}

		public String getContact_phone() {
			return contact_phone;
		}

		public void setContact_phone(String contact_phone) {
			this.contact_phone = contact_phone;
		}

		public String getSub_name() {
			return sub_name;
		}

		public void setSub_name(String sub_name) {
			this.sub_name = sub_name;
		}

		public String getC_name() {
			return c_name;
		}

		public void setC_name(String c_name) {
			this.c_name = c_name;
		}

		public Date getDispatch_create_time() {
			return dispatch_create_time;
		}

		public void setDispatch_create_time(Date dispatch_create_time) {
			this.dispatch_create_time = dispatch_create_time;
		}

		public Integer getTotalFee() {
			return totalFee;
		}

		public void setTotalFee(Integer totalFee) {
			this.totalFee = totalFee;
		}

		public Integer getOperator_id() {
			return operator_id;
		}

		public void setOperator_id(Integer operator_id) {
			this.operator_id = operator_id;
		}
		public Integer getC_verify_status() {
			return c_verify_status;
		}

		public void setC_verify_status(Integer c_verify_status) {
			this.c_verify_status = c_verify_status;
		}

		public Integer getC_status() {
			return c_status;
		}

		public void setC_status(Integer c_status) {
			this.c_status = c_status;
		}

		public Integer getA_id() {
			return a_id;
		}
	
		public void setA_id(Integer a_id) {
			this.a_id = a_id;
		}
		public Integer getC_id() {
			return c_id;
		}
	
		public void setC_id(Integer c_id) {
			this.c_id = c_id;
		}
		public Integer getS_id() {
			return s_id;
		}
	
		public void setS_id(Integer s_id) {
			this.s_id = s_id;
		}
		public String getAmmeter_no() {
			return ammeter_no;
		}
	
		public void setAmmeter_no(String ammeter_no) {
			this.ammeter_no = ammeter_no;
		}
		public String getAmmeter_type() {
			return ammeter_type;
		}
	
		public void setAmmeter_type(String ammeter_type) {
			this.ammeter_type = ammeter_type;
		}
		public Integer getLast_pay_day() {
			return last_pay_day;
		}
	
		public void setLast_pay_day(Integer last_pay_day) {
			this.last_pay_day = last_pay_day;
		}
		public String getPay_status() {
			return pay_status;
		}
	
		public void setPay_status(String pay_status) {
			this.pay_status = pay_status;
		}
		public Date getCreate_time() {
			return create_time;
		}
	
		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}
		public Date getUpdate_time() {
			return update_time;
		}
	
		public void setUpdate_time(Date update_time) {
			this.update_time = update_time;
		}
}